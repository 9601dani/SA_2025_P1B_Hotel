package com.danimo.hotel.rooms.application.usecases.updateroom;

import com.danimo.hotel.category.domain.Category;
import com.danimo.hotel.rooms.domain.*;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Value
public class UpdateRoomDto {
    UUID id;
    UUID locationId;
    String name;
    String description;
    String category;
    String status;
    BigDecimal pricePerDay;
    int capacity;
    int numberOfBed;
    int roomNumber;
    int floorNumber;
    boolean smokingAllowed;
    String imageUrl;
    List<UpdateAmenitiesDto> amenityList;

    public Room toDomain(Room existing) {
        List<Amenity> domainAmenities = amenityList.stream()
                .map(UpdateAmenitiesDto::toDomain)
                .toList();

        return new Room(
                existing.getId(),
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
                existing.getCreatedAt(),
                RoomUpdatedAt.generate(),
                imageUrl
        );
    }
}
