package com.danimo.hotel.rooms.domain;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class RoomCreatedAt {
    private final LocalDateTime createdAt;

    public RoomCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static RoomCreatedAt generate() {
        return new RoomCreatedAt(LocalDateTime.now());
    }

    public static RoomCreatedAt fromLocalDateTime(LocalDateTime createdAt) {
        return new RoomCreatedAt(createdAt);
    }
}
