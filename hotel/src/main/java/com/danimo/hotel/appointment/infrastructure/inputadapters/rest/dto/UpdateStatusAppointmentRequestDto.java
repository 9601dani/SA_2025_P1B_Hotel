package com.danimo.hotel.appointment.infrastructure.inputadapters.rest.dto;

import com.danimo.hotel.appointment.application.usecases.updateappointment.UpdateAppointmentItemDto;
import com.danimo.hotel.appointment.application.usecases.updatestate.UpdateAppointmentStateDto;
import com.danimo.hotel.appointment.domain.AppointmentStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.util.UUID;

@Value
public class UpdateStatusAppointmentRequestDto {
    @NotBlank
    UUID id;
    @NotBlank
    String status;

    public UpdateAppointmentStateDto toAppli() {
        return new UpdateAppointmentStateDto(id, AppointmentStatus.valueOf(status));
    }
}
