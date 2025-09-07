package com.danimo.hotel.rooms.domain;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class RoomPricePerDay {
    private final BigDecimal pricePerDay;

    public RoomPricePerDay(BigDecimal pricePerDay) {
        if(pricePerDay == null) {
            throw new IllegalArgumentException("Precio de habitacion no puede ser nula");
        }
        if(pricePerDay.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El precio debe ser positivo");
        }
        this.pricePerDay = pricePerDay;
    }

    public static RoomPricePerDay fromBigDecimal(BigDecimal pricePerDay) {
        return new RoomPricePerDay(pricePerDay);
    }
}
