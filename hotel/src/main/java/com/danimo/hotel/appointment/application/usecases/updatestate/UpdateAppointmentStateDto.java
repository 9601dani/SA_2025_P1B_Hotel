package com.danimo.hotel.appointment.application.usecases.updatestate;

import com.danimo.hotel.appointment.domain.AppointmentStatus;
import lombok.Value;

import java.util.UUID;
@Value
public class UpdateAppointmentStateDto {
    private final UUID id;
    private final AppointmentStatus status;
}
