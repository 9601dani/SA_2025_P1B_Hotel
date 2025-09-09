package com.danimo.hotel.appointment.infrastructure.outputadapters.rest.dto;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class CreateBillItemRequestDto {
    private final String dishName;
    private final int quantity;
    private final BigDecimal unitPrice;

    public CreateBillItemRequestDto toDomain(){
        return new CreateBillItemRequestDto(dishName, quantity, unitPrice);
    }
}
