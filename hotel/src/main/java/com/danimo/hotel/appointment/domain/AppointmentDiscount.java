package com.danimo.hotel.appointment.domain;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class AppointmentDiscount {

    private BigDecimal discount;
    private String code;

    public AppointmentDiscount(BigDecimal discount, String code) {
        BigDecimal safeDiscount = (discount == null) ? BigDecimal.ZERO : discount;
        String safeCode = (code == null) ? "" : code;

        if (safeDiscount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El descuento no puede ser negativo");
        }
        this.discount = safeDiscount;
        this.code = safeCode;
    }

    public static AppointmentDiscount fromBigdecimalAndCode(BigDecimal discount, String code) {
        return new AppointmentDiscount(discount, code);
    }
}
