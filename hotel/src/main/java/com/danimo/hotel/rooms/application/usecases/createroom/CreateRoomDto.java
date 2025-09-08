package com.danimo.hotel.rooms.application.usecases.createroom;

import com.danimo.hotel.category.domain.Category;
import com.danimo.hotel.rooms.domain.*;
import lombok.Value;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Value
public class CreateRoomDto {
    private UUID locationId;
    private String name;
    private String description;
    private String category;
    private String status;
    private BigDecimal pricePerDay;
    private int capacity;
    private int numberOfBed;
    private int roomNumber;
    private int floorNumber;
    private boolean smokingAllowed;
    private final String imageUrl;
    private final List<CreateAmenitiesDto> amenityList;

    public Room toDomain(){

        List<Amenity> domainAmenities = amenityList.stream()
                .map(CreateAmenitiesDto::toDomain)
                .toList();

        return new Room(
                RoomId.generate(),
                locationId,
                name,
                description,
                Category.fromString(category),
                RoomStatus.fromString(status),
                RoomPricePerDay.fromBigDecimal(pricePerDay),
                capacity,
                numberOfBed,
                roomNumber,
                floorNumber,
                smokingAllowed,
                domainAmenities,
                RoomCreatedAt.generate(),
                RoomUpdatedAt.generate(),
                imageUrl
        );
    }
}
