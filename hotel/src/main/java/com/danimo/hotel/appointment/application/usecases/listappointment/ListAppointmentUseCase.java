package com.danimo.hotel.appointment.application.usecases.listappointment;

import com.danimo.hotel.appointment.application.inputports.ListingAllAppointmentInputPort;
import com.danimo.hotel.appointment.application.outputports.persistence.FindingAllAppointmentsOutputPort;
import com.danimo.hotel.appointment.domain.Appointment;
import com.danimo.hotel.common.application.annotations.UseCase;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@UseCase
public class ListAppointmentUseCase implements ListingAllAppointmentInputPort {

    private final FindingAllAppointmentsOutputPort findingAllAppointmentsOutputPort;

    @Autowired
    public ListAppointmentUseCase(FindingAllAppointmentsOutputPort findingAllAppointmentsOutputPort) {
        this.findingAllAppointmentsOutputPort = findingAllAppointmentsOutputPort;
    }


    @Override
    public List<Appointment> getAllAppointments() {
        return this.findingAllAppointmentsOutputPort.findAll();
    }
}
