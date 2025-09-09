package com.danimo.hotel.appointment.application.usecases.createappointment;

import com.danimo.hotel.appointment.application.inputports.CreatingAppointmentInputPort;
import com.danimo.hotel.appointment.application.outputports.persistence.FindingAppointmentByDatesOutputPort;
import com.danimo.hotel.appointment.application.outputports.persistence.StoringAppointmentOutputPort;
import com.danimo.hotel.appointment.application.outputports.rest.ExistEmployeeOutputPort;
import com.danimo.hotel.appointment.domain.Appointment;
import com.danimo.hotel.common.application.annotations.UseCase;
import com.danimo.hotel.common.application.exceptions.EntityAlreadyExistException;
import com.danimo.hotel.common.application.exceptions.EntityNotFoundException;
import com.danimo.hotel.rooms.application.outputports.rest.ExistLocationOutputPort;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@UseCase
public class CreateAppointmentUseCase implements CreatingAppointmentInputPort {
    private final StoringAppointmentOutputPort storingAppointmentOutputPort;
    private final FindingAppointmentByDatesOutputPort findingAppointmentByDatesOutputPort;
    private final ExistLocationOutputPort existLocationOutputPort;
    private final ExistEmployeeOutputPort existEmployeeOutputPort;

    @Autowired
    public CreateAppointmentUseCase(StoringAppointmentOutputPort storingAppointmentOutputPort, ExistLocationOutputPort existLocationOutputPort, ExistEmployeeOutputPort existEmployeeOutputPort,
                                    FindingAppointmentByDatesOutputPort findingAppointmentByDatesOutputPort) {
        this.storingAppointmentOutputPort = storingAppointmentOutputPort;
        this.existLocationOutputPort = existLocationOutputPort;
        this.existEmployeeOutputPort = existEmployeeOutputPort;
        this.findingAppointmentByDatesOutputPort = findingAppointmentByDatesOutputPort;
    }

    @Override
    public Appointment createAppointment(CreateAppointmentDto dto) {
        if(!existLocationOutputPort.existLocation(dto.getLocationId())) {
            throw new EntityNotFoundException("El establecimiento no existe");
        }

        if(!existEmployeeOutputPort.existEmployee(dto.getUserEmployeeId())){
            throw new EntityNotFoundException("El empleado no existe");
        }

        LocalDate start = dto.getStartDate();
        LocalDate end   = dto.getEndDate();
        if (start == null || end == null || end.isBefore(start)) {
            throw new IllegalArgumentException("Rango de fechas inválido");
        }

        Set<UUID> roomIds = dto.getItems().stream()
                .map(CreateAppointmentItemDto::getRoomId)
                .collect(Collectors.toSet());

        for (UUID roomId : roomIds) {
            boolean overlaps = findingAppointmentByDatesOutputPort
                    .existsActiveOverlapForRoom(roomId, start, end);
            if (overlaps) {
                throw new EntityAlreadyExistException(
                        "Ya existe una reservación activa para la habitación " + roomId +
                                " en el rango solicitado");
            }
        }

        Appointment appointment = dto.toDomain();

        appointment.recalculateTotals();
        appointment.changeStatusWhenCreated();

        return storingAppointmentOutputPort.save(appointment);
    }
}
