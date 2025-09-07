package com.danimo.hotel.rooms.application.outputports.persistence;

import com.danimo.hotel.rooms.domain.Room;

import java.util.List;
import java.util.UUID;

public interface FindingRoomByLocationOutputPort {
    List<Room> findRoomByLocation(UUID locationId);
}
