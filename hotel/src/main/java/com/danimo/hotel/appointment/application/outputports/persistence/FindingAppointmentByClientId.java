package com.danimo.hotel.appointment.application.outputports.persistence;

import com.danimo.hotel.appointment.domain.Appointment;

import java.util.List;

public interface FindingAppointmentByClientId {
    List<Appointment> findByClientId(String clientId);
}
