package com.danimo.hotel.rooms.domain;

import lombok.Value;

@Value
public class Amenity {
    private final String name;

    public Amenity(String name) {
        if(name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("La amenidad no puede ser nula");
        }
        this.name = name;
    }
}
