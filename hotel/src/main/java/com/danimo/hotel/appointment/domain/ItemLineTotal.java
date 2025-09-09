package com.danimo.hotel.appointment.domain;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class ItemLineTotal {
    private final BigDecimal total;

    private ItemLineTotal(BigDecimal total) {
        if(total == null){
            throw new NullPointerException("El total de pedido no puede ser nulo");
        }

        if(total.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("El total de pedido no puede ser negativo");
        }
        this.total = total;
    }

    public static ItemLineTotal fromBigDecimal(BigDecimal total) {
        return new ItemLineTotal(total);
    }

    public BigDecimal toBigDecimal() {
        return total;
    }
}
