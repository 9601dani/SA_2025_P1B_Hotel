package com.danimo.hotel.appointment.application.outputports.persistence;

import com.danimo.hotel.appointment.domain.Appointment;

import java.util.List;
import java.util.UUID;

public interface FindingAppointmentByLocationIdOutputPort {
    List<Appointment> findByLocationId(String locationId);
}
