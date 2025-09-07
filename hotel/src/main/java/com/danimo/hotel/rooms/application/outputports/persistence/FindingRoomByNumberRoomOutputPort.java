package com.danimo.hotel.rooms.application.outputports.persistence;

import com.danimo.hotel.rooms.domain.Room;

import java.util.Optional;
import java.util.UUID;

public interface FindingRoomByNumberRoomOutputPort {
    Optional<Room> findingRoomByNumberRoom(UUID locationId, int numberRoom);
}
