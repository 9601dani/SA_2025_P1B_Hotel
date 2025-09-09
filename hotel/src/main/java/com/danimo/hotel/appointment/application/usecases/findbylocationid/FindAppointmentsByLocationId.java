package com.danimo.hotel.appointment.application.usecases.findbylocationid;

import com.danimo.hotel.appointment.application.inputports.FindingAppointmentsByLocationIdInputPort;
import com.danimo.hotel.appointment.application.outputports.persistence.FindingAppointmentByLocationIdOutputPort;
import com.danimo.hotel.appointment.domain.Appointment;
import com.danimo.hotel.common.application.annotations.UseCase;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@UseCase
public class FindAppointmentsByLocationId implements FindingAppointmentsByLocationIdInputPort {

    private final FindingAppointmentByLocationIdOutputPort findingAppointmentByLocationIdOutputPort;

    @Autowired
    public FindAppointmentsByLocationId(FindingAppointmentByLocationIdOutputPort findingAppointmentByLocationIdOutputPort) {
        this.findingAppointmentByLocationIdOutputPort = findingAppointmentByLocationIdOutputPort;
    }

    @Override
    public List<Appointment> findByLocationId(String locationId) {
        return findingAppointmentByLocationIdOutputPort.findByLocationId(locationId);
    }
}
