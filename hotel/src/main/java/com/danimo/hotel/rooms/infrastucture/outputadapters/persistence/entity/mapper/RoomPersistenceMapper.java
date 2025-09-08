package com.danimo.hotel.rooms.infrastucture.outputadapters.persistence.entity.mapper;

import com.danimo.hotel.category.domain.Category;
import com.danimo.hotel.rooms.domain.*;
import com.danimo.hotel.rooms.infrastucture.outputadapters.persistence.entity.AmenityDbEntity;
import com.danimo.hotel.rooms.infrastucture.outputadapters.persistence.entity.RoomDbEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class RoomPersistenceMapper {
    public Room toDomain(RoomDbEntity dbEntity) {
        if (dbEntity == null) return null;

        List<Amenity> amenities = dbEntity.getAmenities().stream()
                .map(this::toDomainAmenity)
                .toList();

        return new Room(
                RoomId.fromUuid(dbEntity.getId()),
                dbEntity.getLocationId(),
                dbEntity.getName(),
                dbEntity.getDescription(),
                Category.fromString(dbEntity.getCategory()),
                dbEntity.getStatus(),
                RoomPricePerDay.fromBigDecimal(dbEntity.getPricePerDay()),
                dbEntity.getCapacity(),
                dbEntity.getNumberOfBeds(),
                dbEntity.getRoomNumber(),
                dbEntity.getFloorNumber(),
                dbEntity.isSmokingAllowed(),
                amenities,
                RoomCreatedAt.fromLocalDateTime(dbEntity.getCreatedAt()),
                RoomUpdatedAt.fromLocalDateTime(dbEntity.getUpdatedAt()),
                dbEntity.getImageUrl()
        );
    }

    public RoomDbEntity toDbEntity(Room room) {
        if (room == null) return null;

        RoomDbEntity dbEntity = new RoomDbEntity();
        dbEntity.setId(room.getId().getId());
        dbEntity.setLocationId(room.getLocationId());
        dbEntity.setName(room.getName());
        dbEntity.setDescription(room.getDescription());
        dbEntity.setCategory(room.getCategory().getName());
        dbEntity.setStatus(room.getStatus());
        dbEntity.setPricePerDay(room.getPricePerDay().getPricePerDay());
        dbEntity.setCapacity(room.getCapacity());
        dbEntity.setNumberOfBeds(room.getNumberOfBeds());
        dbEntity.setRoomNumber(room.getRoomNumber());
        dbEntity.setFloorNumber(room.getFloorNumber());
        dbEntity.setSmokingAllowed(room.isSmokingAllowed());
        dbEntity.setCreatedAt(room.getCreatedAt().getCreatedAt());
        dbEntity.setUpdatedAt(room.getUpdatedAt().getUpdatedAt());
        dbEntity.setImageUrl(room.getImageUrl());

        List<AmenityDbEntity> amenities = room.getAmenities().stream()
                .map(a -> toDbEntity(a, dbEntity))
                .toList();
        dbEntity.setAmenities(amenities);

        return dbEntity;
    }

    private Amenity toDomainAmenity(AmenityDbEntity entity) {
        if (entity == null) return null;
        return new Amenity(entity.getName());
    }

    private AmenityDbEntity toDbEntity(Amenity amenity, RoomDbEntity roomDbEntity) {
        if (amenity == null) return null;

        AmenityDbEntity dbEntity = new AmenityDbEntity();
        dbEntity.setId(UUID.randomUUID());
        dbEntity.setName(amenity.getName());
        dbEntity.setRoom(roomDbEntity);

        return dbEntity;
    }
}
