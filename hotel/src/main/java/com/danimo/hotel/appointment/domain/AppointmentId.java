package com.danimo.hotel.appointment.domain;

import lombok.Value;

import java.util.UUID;

@Value
public class AppointmentId {
    private final UUID appointmentId;

    public AppointmentId(UUID appointmentId) {
        this.appointmentId = appointmentId;
    }

    public static AppointmentId generate() {
        return new AppointmentId(UUID.randomUUID());
    }

    public static AppointmentId fromUUID(UUID uuid) {
        return new AppointmentId(uuid);
    }
}
