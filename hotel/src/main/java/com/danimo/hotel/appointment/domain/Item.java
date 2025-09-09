package com.danimo.hotel.appointment.domain;

import com.danimo.hotel.common.domain.annotations.DomainEntity;
import com.danimo.hotel.rooms.domain.RoomId;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@DomainEntity
@AllArgsConstructor
public class Item {
    private UUID id;
    private RoomId roomId;
    private String roomName;
    private int quantity;
    private BigDecimal unitPrice;
    private ItemLineTotal lineTotal;

    public ItemLineTotal calculateLineTotal() {
        BigDecimal total = unitPrice.multiply(new BigDecimal(quantity));
        return ItemLineTotal.fromBigDecimal(total);
    }
}
