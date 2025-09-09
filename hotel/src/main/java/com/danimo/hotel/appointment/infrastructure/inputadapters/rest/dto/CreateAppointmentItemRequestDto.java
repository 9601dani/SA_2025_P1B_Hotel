package com.danimo.hotel.appointment.infrastructure.inputadapters.rest.dto;

import com.danimo.hotel.appointment.application.usecases.createappointment.CreateAppointmentItemDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class CreateAppointmentItemRequestDto {
    @NotBlank
    UUID roomId;
    @NotBlank
    String roomName;
    @NotBlank
    int quantity;
    @NotBlank
    BigDecimal unitPrice;

    public CreateAppointmentItemDto toAppli(){
        return new CreateAppointmentItemDto(
                roomId,
                roomName,
                quantity,
                unitPrice
        );
    }
}
