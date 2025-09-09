package com.danimo.hotel.appointment.application.inputports;

import com.danimo.hotel.appointment.domain.Appointment;

import java.util.List;

public interface ListingAllAppointmentInputPort {
    List<Appointment> getAllAppointments();
}
