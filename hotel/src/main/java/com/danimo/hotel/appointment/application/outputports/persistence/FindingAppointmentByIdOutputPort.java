package com.danimo.hotel.appointment.application.outputports.persistence;

import com.danimo.hotel.appointment.domain.Appointment;

import java.util.Optional;
import java.util.UUID;

public interface FindingAppointmentByIdOutputPort {
    Optional<Appointment> findAppointmentById(UUID id);
}
