package com.danimo.hotel.rooms.infrastucture.outputadapters.persistence.repository;

import com.danimo.hotel.appointment.domain.AppointmentStatus;
import com.danimo.hotel.rooms.infrastucture.outputadapters.persistence.entity.RoomDbEntity;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface RoomDbEntityJpaRepository extends JpaRepository<RoomDbEntity, UUID> {
    Optional<RoomDbEntity> findByIdAndLocationId(UUID id, UUID locationId);

    Optional<RoomDbEntity> findByRoomNumberAndLocationId(int roomNumber, UUID locationId);

    List<RoomDbEntity> findByLocationId(UUID locationId);

    @Query("""
      SELECT r
      FROM RoomDbEntity r
      WHERE r.locationId = :locationId
        AND NOT EXISTS (
          SELECT 1
          FROM ItemDbEntity it
            JOIN it.appointment a
          WHERE it.roomId = r.id
            AND a.startDate < :endDate
            AND a.endDate   > :startDate
            AND a.status IN (
              com.danimo.hotel.appointment.domain.AppointmentStatus.CREATED,
              com.danimo.hotel.appointment.domain.AppointmentStatus.IN_PROGRESS
            )
        )
      ORDER BY r.roomNumber ASC
  """)
    List<RoomDbEntity> findAvailableRooms(
            @Param("locationId") UUID locationId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

}
