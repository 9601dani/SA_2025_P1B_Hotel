package com.danimo.hotel.appointment.application.usecases.updateappointment;

import com.danimo.hotel.appointment.application.inputports.UpdatingAppointmentInputPort;
import com.danimo.hotel.appointment.application.outputports.persistence.FindingAppointmentByIdOutputPort;
import com.danimo.hotel.appointment.application.outputports.persistence.StoringAppointmentOutputPort;
import com.danimo.hotel.appointment.application.outputports.rest.ExistEmployeeOutputPort;
import com.danimo.hotel.appointment.domain.Appointment;
import com.danimo.hotel.appointment.domain.AppointmentStatus;
import com.danimo.hotel.common.application.annotations.UseCase;
import com.danimo.hotel.common.application.exceptions.EntityAlreadyExistException;
import com.danimo.hotel.common.application.exceptions.EntityNotFoundException;
import com.danimo.hotel.rooms.application.outputports.rest.ExistLocationOutputPort;
import org.springframework.beans.factory.annotation.Autowired;

@UseCase
public class UpdateAppointmentUseCase implements UpdatingAppointmentInputPort {
    private final FindingAppointmentByIdOutputPort findingAppointmentByIdOutputPort;
    private final StoringAppointmentOutputPort storingAppointmentOutputPort;
    private final ExistLocationOutputPort existLocationOutputPort;
    private final ExistEmployeeOutputPort existEmployeeOutputPort;

    @Autowired
    public UpdateAppointmentUseCase(FindingAppointmentByIdOutputPort findingAppointmentByIdOutputPort, StoringAppointmentOutputPort storingAppointmentOutputPort,
                                    ExistLocationOutputPort existLocationOutputPort, ExistEmployeeOutputPort existEmployeeOutputPort) {
        this.findingAppointmentByIdOutputPort = findingAppointmentByIdOutputPort;
        this.storingAppointmentOutputPort = storingAppointmentOutputPort;
        this.existLocationOutputPort = existLocationOutputPort;
        this.existEmployeeOutputPort = existEmployeeOutputPort;
    }


    @Override
    public Appointment update(UpdateAppointmentDto dto) {
        Appointment current = findingAppointmentByIdOutputPort.findAppointmentById(dto.getAppointmentId())
                .orElseThrow(() -> new EntityNotFoundException("No se encontro la reservacion"));

        if(current.getStatus().equals(AppointmentStatus.COMPLETED) || current.getStatus().equals(AppointmentStatus.CANCELLED) || current.getStatus().equals(AppointmentStatus.IN_PROGRESS)) {
            throw new EntityAlreadyExistException("La reservacion contiene un estado que no permite ser modificada");
        }

        /*if (!existClientOutputPort.existClient(dto.getNit())) {
            throw new EntityNotFoundException("El cliente no existe");
        }*/
        if(!existEmployeeOutputPort.existEmployee(dto.getUserEmployeeId())){
            throw new EntityNotFoundException("El empleado no existe");
        }

        Appointment updated = dto.applyChanges(current);

        updated.recalculateTotals();

        return storingAppointmentOutputPort.save(updated);
    }
}
