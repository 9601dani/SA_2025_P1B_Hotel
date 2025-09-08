package com.danimo.hotel.rooms.domain;

import com.danimo.hotel.category.domain.Category;
import com.danimo.hotel.common.domain.annotations.DomainEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@DomainEntity
@AllArgsConstructor
public class Room {

    private final RoomId id;
    private final UUID locationId;
    private final String name;
    private final String description;
    private final Category category;
    private final RoomStatus status;
    private final RoomPricePerDay pricePerDay;
    private final int capacity;
    private final int numberOfBeds;
    private final int roomNumber;
    private final List<Amenity> amenities;
    private final RoomCreatedAt createdAt;
    private final RoomUpdatedAt updatedAt;
    private final boolean smokingAllowed;
    private final int floorNumber;
    private final String imageUrl;

    public Room(RoomId id,
                UUID locationId,
                String name,
                String description,
                Category category,
                RoomStatus status,
                RoomPricePerDay pricePerDay,
                int capacity,
                int numberOfBeds,
                int roomNumber,
                int floorNumber,
                boolean smokingAllowed,
                List<Amenity> amenities,
                RoomCreatedAt createdAt,
                RoomUpdatedAt updatedAt,
                String imageUrl) {

        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }
        if (numberOfBeds <= 0) {
            throw new IllegalArgumentException("Number of beds must be greater than 0");
        }
        if (roomNumber <= 0) {
            throw new IllegalArgumentException("Room number must be greater than 0");
        }

        this.id = id;
        this.locationId = locationId;
        this.name = name;
        this.description = description;
        this.category = category;
        this.status = status;
        this.pricePerDay = pricePerDay;
        this.capacity = capacity;
        this.numberOfBeds = numberOfBeds;
        this.roomNumber = roomNumber;
        this.floorNumber = floorNumber;
        this.smokingAllowed = smokingAllowed;
        this.amenities = List.copyOf(amenities);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.imageUrl = imageUrl;
    }

    public Room withStatus(RoomStatus newStatus) {
        return new Room(
                this.id,
                this.locationId,
                this.name,
                this.description,
                this.category,
                newStatus,
                this.pricePerDay,
                this.capacity,
                this.numberOfBeds,
                this.roomNumber,
                this.floorNumber,
                this.smokingAllowed,
                this.amenities,
                this.createdAt,
                RoomUpdatedAt.generate(),
                this.imageUrl
        );
    }

}
