package com.danimo.hotel.rooms.infrastucture.outputadapters.persistence.repository;

import com.danimo.hotel.rooms.infrastucture.outputadapters.persistence.entity.RoomDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoomDbEntityJpaRepository extends JpaRepository<RoomDbEntity, UUID> {
    Optional<RoomDbEntity> findByIdAndLocationId(UUID id, UUID locationId);

    Optional<RoomDbEntity> findByRoomNumberAndLocationId(int roomNumber, UUID locationId);

    List<RoomDbEntity> findByLocationId(UUID locationId);
}
