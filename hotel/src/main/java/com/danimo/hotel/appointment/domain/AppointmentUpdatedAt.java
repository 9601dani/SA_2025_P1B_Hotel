package com.danimo.hotel.appointment.domain;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class AppointmentUpdatedAt {
    private final LocalDateTime updatedAt;

    public AppointmentUpdatedAt(LocalDateTime updatedAt) {
        if(updatedAt == null) {
            throw new IllegalArgumentException("La fecha de actualizacion no puede ser nula");
        }
        this.updatedAt = updatedAt;
    }

    public static AppointmentUpdatedAt generate(){
        return new AppointmentUpdatedAt(LocalDateTime.now());
    }

    public static AppointmentUpdatedAt fromLocalDateTime(LocalDateTime updatedAt) {
        return new AppointmentUpdatedAt(updatedAt);
    }
}
