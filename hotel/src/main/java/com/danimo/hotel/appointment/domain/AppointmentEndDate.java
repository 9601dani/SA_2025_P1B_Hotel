package com.danimo.hotel.appointment.domain;

import lombok.Value;

import java.time.LocalDate;
@Value
public class AppointmentEndDate {
    private final LocalDate endAt;

    public AppointmentEndDate(LocalDate endAt) {
        if(endAt == null) {
            throw new IllegalArgumentException("La fecha de finalizacion de la reservacion no puede ser nula");
        }
        this.endAt = endAt;
    }
    public static AppointmentEndDate fromLocalDate(LocalDate endAt) {
        return new AppointmentEndDate(endAt);
    }
}
