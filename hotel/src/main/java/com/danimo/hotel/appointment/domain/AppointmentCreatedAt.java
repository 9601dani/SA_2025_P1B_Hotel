package com.danimo.hotel.appointment.domain;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class AppointmentCreatedAt {
    private final LocalDateTime createAt;

    public AppointmentCreatedAt(LocalDateTime createAt) {
        if(createAt == null) {
            throw new IllegalArgumentException("La fecha de creacion de la reservacion no puede ser nula");
        }
        this.createAt = createAt;
    }

    public static AppointmentCreatedAt generate(){
        return new AppointmentCreatedAt(LocalDateTime.now());
    }

    public static AppointmentCreatedAt fromLocalDateTime(LocalDateTime createAt) {
        return new AppointmentCreatedAt(createAt);
    }
}
