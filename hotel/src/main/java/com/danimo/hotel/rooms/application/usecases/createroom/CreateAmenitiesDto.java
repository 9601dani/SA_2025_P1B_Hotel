package com.danimo.hotel.rooms.application.usecases.createroom;

import com.danimo.hotel.rooms.domain.Amenity;
import lombok.Value;

@Value
public class CreateAmenitiesDto {
    private final String name;

    public Amenity toDomain(){
        return new Amenity(name);
    }
}
