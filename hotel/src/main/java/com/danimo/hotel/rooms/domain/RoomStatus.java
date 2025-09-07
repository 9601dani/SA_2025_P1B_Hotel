package com.danimo.hotel.rooms.domain;

public enum RoomStatus {
    BUSY,
    AVAILABLE,
    MAINTENANCE;

    public static RoomStatus fromString(String status) {
        if (status == null) {
            throw new IllegalArgumentException("El valor no puede ser null");
        }
        try {
            return RoomStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("RoomStatus inválido: " + status);
        }
    }
}
