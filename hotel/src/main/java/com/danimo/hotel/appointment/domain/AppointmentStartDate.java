package com.danimo.hotel.appointment.domain;

import lombok.Value;

import java.time.LocalDate;

@Value
public class AppointmentStartDate {
    private final LocalDate startAt;

    public AppointmentStartDate(LocalDate startAt) {
        if(startAt == null) {
            throw new IllegalArgumentException("La fecha de inicio de la reservacion no puede ser nula");
        }
        this.startAt = startAt;
    }

    public static AppointmentStartDate fromLocalDateTime(LocalDate startAt) {
        return new AppointmentStartDate(startAt);
    }
}
