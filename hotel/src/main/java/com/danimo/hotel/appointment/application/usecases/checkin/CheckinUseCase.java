package com.danimo.hotel.appointment.application.usecases.checkin;

import com.danimo.hotel.appointment.application.inputports.CheckInAppointmentInputPort;
import com.danimo.hotel.appointment.application.outputports.persistence.FindingAppointmentByIdOutputPort;
import com.danimo.hotel.appointment.application.outputports.persistence.StoringAppointmentOutputPort;
import com.danimo.hotel.appointment.application.outputports.rest.ExistClientOutputPort;
import com.danimo.hotel.appointment.domain.Appointment;
import com.danimo.hotel.common.application.annotations.UseCase;
import com.danimo.hotel.common.application.exceptions.EntityNotFoundException;
import com.danimo.hotel.rooms.application.inputports.UpdatingRoomStatusInputPort;
import com.danimo.hotel.rooms.application.usecases.updatestatusroom.UpdateStatusRoomDto;
import com.danimo.hotel.rooms.domain.RoomStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.UUID;

@UseCase
public class CheckinUseCase implements CheckInAppointmentInputPort {
    private ExistClientOutputPort existClientOutputPort;
    private FindingAppointmentByIdOutputPort findingAppointmentByIdOutputPort;
    private StoringAppointmentOutputPort storingAppointmentOutputPort;
    private UpdatingRoomStatusInputPort updatingRoomStatusInputPort;

    @Autowired
    public CheckinUseCase(ExistClientOutputPort existClientOutputPort,FindingAppointmentByIdOutputPort findingAppointmentByIdOutputPort,StoringAppointmentOutputPort storingAppointmentOutputPort,
                          UpdatingRoomStatusInputPort updatingRoomStatusInputPort) {
        this.existClientOutputPort = existClientOutputPort;
        this.findingAppointmentByIdOutputPort = findingAppointmentByIdOutputPort;
        this.storingAppointmentOutputPort = storingAppointmentOutputPort;
        this.updatingRoomStatusInputPort = updatingRoomStatusInputPort;
    }


    @Override
    public Appointment checkInAppointment(CheckinDto dto) {
        if(!existClientOutputPort.existClient(dto.getClientId())) {
            throw new EntityNotFoundException("El cliente no existe");
        }


        Appointment currrentAppointment = findingAppointmentByIdOutputPort.findAppointmentById(dto.getAppointmentId())
                .orElseThrow(() -> new EntityNotFoundException("La reservacion no existe"));

/*        LocalDate today = LocalDate.now();
        LocalDate startDate = currrentAppointment.getStartDate().getStartAt();

        if (!today.equals(startDate)) {
            throw new IllegalStateException(
                    String.format(
                            "Lo sentimos, el check-in solo puede hacerse el %s (fecha de inicio de la reservación). Hoy es %s.",
                            startDate,
                            today
                    )
            );
        }*/
        currrentAppointment.changeStatusCheckin(dto.getClientId());


        currrentAppointment.getItems().forEach(item -> {
            UUID roomId = item.getRoomId().getId();
            UpdateStatusRoomDto updateRoomDto = new UpdateStatusRoomDto(roomId, RoomStatus.BUSY);
            updatingRoomStatusInputPort.updateStatus(updateRoomDto);
        });

        System.out.println("______________________");
        System.out.println(currrentAppointment.getStatus());

        return storingAppointmentOutputPort.save(currrentAppointment);

    }
}
