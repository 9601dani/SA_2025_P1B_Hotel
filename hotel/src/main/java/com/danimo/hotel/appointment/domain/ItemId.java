package com.danimo.hotel.appointment.domain;

import lombok.Value;

import java.util.UUID;

@Value
public class ItemId {
    private final UUID itemId;

    public ItemId(UUID itemId) {
        this.itemId = itemId;
    }

    public static ItemId generate(){
        return new ItemId(UUID.randomUUID());
    }

    public static ItemId fromUUID(UUID itemId) {
        return new ItemId(itemId);
    }
}
