package com.danimo.hotel.appointment.application.outputports.rest;

import com.danimo.hotel.appointment.domain.Appointment;

public interface CreatingBillOutputPort {
    boolean createBill(Appointment appointment);
}
