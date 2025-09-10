package com.danimo.hotel.appointment.infrastructure.outputadapters.rest.dto;

import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class CreateMovementRequestDto {
    private String serviceType;
    private String paymentType;
    private String description;
    private BigDecimal amount;
    private UUID locationId;
    private String locationName;

    public static CreateMovementRequestDto generateDto(String serviceType, String paymentType, String description, BigDecimal amount, UUID locationId, String locationName) {
        return new CreateMovementRequestDto(
                serviceType,
                paymentType,
                description,
                amount,
                locationId,
                locationName

        );
    }
}
