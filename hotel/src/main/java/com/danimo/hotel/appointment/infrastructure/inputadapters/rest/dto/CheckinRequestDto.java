package com.danimo.hotel.appointment.infrastructure.inputadapters.rest.dto;

import com.danimo.hotel.appointment.application.usecases.checkin.CheckinDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.util.UUID;

@Value
public class CheckinRequestDto {
    @NotBlank
    String idAppointment;
    @NotBlank
    String idClient;

    public CheckinDto toAppli() {
        return new CheckinDto(
                UUID.fromString(idAppointment),
                idClient
        );
    }
}
