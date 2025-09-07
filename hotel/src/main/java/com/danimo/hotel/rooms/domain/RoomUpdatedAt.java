package com.danimo.hotel.rooms.domain;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class RoomUpdatedAt {
    private final LocalDateTime updatedAt;

    public RoomUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static RoomUpdatedAt generate() {
        return new RoomUpdatedAt(LocalDateTime.now());
    }

    public static RoomUpdatedAt fromLocalDateTime(LocalDateTime updatedAt) {
        return new RoomUpdatedAt(updatedAt);
    }
}
