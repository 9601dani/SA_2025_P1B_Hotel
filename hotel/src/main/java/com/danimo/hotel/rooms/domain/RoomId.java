package com.danimo.hotel.rooms.domain;

import lombok.Value;

import java.util.UUID;

@Value
public class RoomId {
    private final UUID id;

    public RoomId(UUID id) {
        this.id = id;
    }

    public static RoomId generate() {
        return new RoomId(UUID.randomUUID());
    }
    public static RoomId fromUuid(UUID uuid) {
        return new RoomId(uuid);
    }
}
