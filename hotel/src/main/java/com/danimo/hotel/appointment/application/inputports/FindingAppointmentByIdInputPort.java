package com.danimo.hotel.appointment.application.inputports;

import com.danimo.hotel.appointment.domain.Appointment;

import java.util.Optional;

public interface FindingAppointmentByIdInputPort {
    Appointment findById(String id);
}
