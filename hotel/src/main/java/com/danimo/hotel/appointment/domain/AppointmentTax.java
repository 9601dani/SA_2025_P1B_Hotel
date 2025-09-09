package com.danimo.hotel.appointment.domain;

import lombok.Value;

import java.math.BigDecimal;
@Value
public class AppointmentTax {
    private BigDecimal tax;

    public AppointmentTax(BigDecimal tax) {
        if(tax == null){
            throw new IllegalArgumentException("El impuesto debe ser mayor que 0");
        }
        if(tax.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El impuesto debe ser mayor que 0");
        }
        this.tax = tax;
    }

    public static AppointmentTax fromBigDecimal(BigDecimal tax) {
        return new AppointmentTax(tax);
    }
}
