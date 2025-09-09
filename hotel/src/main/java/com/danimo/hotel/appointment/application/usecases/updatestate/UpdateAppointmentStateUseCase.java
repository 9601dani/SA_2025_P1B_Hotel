package com.danimo.hotel.appointment.application.usecases.updatestate;

import com.danimo.hotel.appointment.application.inputports.UpdatingStateAppointmentInputPort;
import com.danimo.hotel.appointment.application.outputports.persistence.FindingAppointmentByIdOutputPort;
import com.danimo.hotel.appointment.application.outputports.persistence.StoringAppointmentOutputPort;
import com.danimo.hotel.appointment.application.outputports.rest.CreatingBillOutputPort;
import com.danimo.hotel.appointment.domain.Appointment;
import com.danimo.hotel.appointment.domain.AppointmentStatus;
import com.danimo.hotel.common.application.annotations.UseCase;
import com.danimo.hotel.common.application.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

@UseCase
public class UpdateAppointmentStateUseCase implements UpdatingStateAppointmentInputPort {
    private final FindingAppointmentByIdOutputPort findingAppointmentByIdOutputPort;
    private final StoringAppointmentOutputPort storingAppointmentOutputPort;
    private final CreatingBillOutputPort creatingBillOutputPort;

    @Autowired
    public UpdateAppointmentStateUseCase(FindingAppointmentByIdOutputPort findingAppointmentByIdOutputPort, StoringAppointmentOutputPort storingAppointmentOutputPort, CreatingBillOutputPort creatingBillOutputPort) {
        this.findingAppointmentByIdOutputPort = findingAppointmentByIdOutputPort;
        this.storingAppointmentOutputPort = storingAppointmentOutputPort;
        this.creatingBillOutputPort = creatingBillOutputPort;
    }

    @Override
    public Appointment updateState(UpdateAppointmentStateDto dto) {
        Appointment appointment = findingAppointmentByIdOutputPort.findAppointmentById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("La reservacion no existe"));

        appointment.changeStatus(dto.getStatus());

        Appointment savedAppointment = storingAppointmentOutputPort.save(appointment);

        if(dto.getStatus() == AppointmentStatus.COMPLETED){
            if(!creatingBillOutputPort.createBill(savedAppointment)){
                throw new EntityNotFoundException("La factura no pudo generarse para la orden");
            }
        }
        return savedAppointment;
    }
}
