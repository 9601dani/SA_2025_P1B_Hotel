package com.danimo.hotel.appointment.application.outputports.persistence;

import com.danimo.hotel.appointment.domain.Appointment;

public interface StoringAppointmentOutputPort {
    Appointment save(Appointment appointment);
}
