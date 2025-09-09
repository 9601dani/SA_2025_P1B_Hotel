package com.danimo.hotel.appointment.application.usecases.findappointment;

import com.danimo.hotel.appointment.application.inputports.FindingAppointmentByIdInputPort;
import com.danimo.hotel.appointment.application.outputports.persistence.FindingAppointmentByIdOutputPort;
import com.danimo.hotel.appointment.domain.Appointment;
import com.danimo.hotel.common.application.annotations.UseCase;
import com.danimo.hotel.common.application.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

@UseCase
public class FindAppointmentUseCase implements FindingAppointmentByIdInputPort {

    private final FindingAppointmentByIdOutputPort findingAppointmentByIdOutputPort;

    @Autowired
    public FindAppointmentUseCase(FindingAppointmentByIdOutputPort findingAppointmentByIdOutputPort) {
        this.findingAppointmentByIdOutputPort = findingAppointmentByIdOutputPort;
    }

    @Override
    public Appointment findById(String id) {
        return this.findingAppointmentByIdOutputPort.findAppointmentById(UUID.fromString(id))
                .orElseThrow(() -> new EntityNotFoundException("No se encontro la reservacion"));
    }
}
