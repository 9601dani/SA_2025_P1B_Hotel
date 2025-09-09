package com.danimo.hotel.appointment.domain;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class AppointmentSubtotal {
    private BigDecimal subtotal;

    public AppointmentSubtotal(BigDecimal subtotal) {
        if(subtotal == null){
            throw new NullPointerException("Subtotal no puede ser null");
        }

        if(subtotal.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Subtotal no puede ser negativo");
        }

        this.subtotal = subtotal;
    }

    public static AppointmentSubtotal fromBigDecimal(BigDecimal subtotal) {
        return new AppointmentSubtotal(subtotal);
    }
}
