package com.danimo.hotel.rooms.application.usecases.updateroom;

import com.danimo.hotel.rooms.domain.Amenity;
import lombok.Value;

@Value
public class UpdateAmenitiesDto {
    private String name;

    public Amenity toDomain() {
        return new Amenity(name);
    }
}
