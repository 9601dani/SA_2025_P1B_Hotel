package com.danimo.hotel.appointment.infrastructure.inputadapters.rest.dto;

import com.danimo.hotel.appointment.application.usecases.updateappointment.UpdateAppointmentItemDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class UpdateAppointmentItemRequestDto {
    @NotBlank
    UUID itemId;
    @NotBlank
    UUID roomId;
    @NotBlank
    String roomName;
    @NotBlank
    int quantity;
    @NotBlank
    BigDecimal unitPrice;

    public UpdateAppointmentItemDto toAppli(){
        return new UpdateAppointmentItemDto(itemId, roomId, roomName, quantity, unitPrice);
    }
}
