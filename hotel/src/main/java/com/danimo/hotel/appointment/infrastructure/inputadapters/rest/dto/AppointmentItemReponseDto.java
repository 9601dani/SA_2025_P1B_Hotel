package com.danimo.hotel.appointment.infrastructure.inputadapters.rest.dto;

import com.danimo.hotel.appointment.domain.Item;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class AppointmentItemReponseDto {
    @NotBlank
    UUID roomId;
    @NotBlank
    String roomName;
    int quantity;
    BigDecimal unitPrice;
    BigDecimal lineTotal;

    public static AppointmentItemReponseDto fromDomain(Item item) {
        return new AppointmentItemReponseDto(
                item.getRoomId().getId(),
                item.getRoomName(),
                item.getQuantity(),
                item.getUnitPrice(),
                item.calculateLineTotal().toBigDecimal()
        );
    }
}
