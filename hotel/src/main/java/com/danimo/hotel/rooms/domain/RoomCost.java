package com.danimo.hotel.rooms.domain;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class RoomCost {
    private BigDecimal costPerDay;

    public RoomCost(BigDecimal cost) {
        if(cost == null){
            throw new IllegalArgumentException("El costo no puede ser nulo");
        }
        if(cost.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("El costo no puede ser negativo");
        }

        this.costPerDay = cost;
    }

    public static RoomCost fromBigDecimal(BigDecimal cost) {
        return new RoomCost(cost);
    }
}
