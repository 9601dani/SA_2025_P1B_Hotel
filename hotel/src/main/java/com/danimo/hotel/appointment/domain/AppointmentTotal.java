package com.danimo.hotel.appointment.domain;

import lombok.Value;

import java.math.BigDecimal;
@Value
public class AppointmentTotal {
    private final BigDecimal total;

    public AppointmentTotal(BigDecimal total) {
        if(total == null){
            throw new NullPointerException("El total es null");
        }
        if(total.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("El total es negativo");
        }
        this.total = total;
    }

    public static AppointmentTotal fromBigDecimal(BigDecimal total) {
        return new AppointmentTotal(total);
    }
}
