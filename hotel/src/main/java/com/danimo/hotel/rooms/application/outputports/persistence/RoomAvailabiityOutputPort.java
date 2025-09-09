package com.danimo.hotel.rooms.application.outputports.persistence;

import com.danimo.hotel.rooms.domain.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface RoomAvailabiityOutputPort {
    List<Room> findAvailableRooms(UUID locationId, LocalDate startDate, LocalDate endDate);
}
