package com.danimo.hotel.appointment.application.usecases.checkin;

import lombok.Value;

import java.util.UUID;

@Value
public class CheckinDto {
    private final UUID appointmentId;
    private final String clientId;
}
