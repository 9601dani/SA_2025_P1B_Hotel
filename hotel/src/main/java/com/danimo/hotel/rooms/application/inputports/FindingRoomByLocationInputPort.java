package com.danimo.hotel.rooms.application.inputports;

import com.danimo.hotel.rooms.domain.Room;

import java.util.List;
import java.util.UUID;

public interface FindingRoomByLocationInputPort {
    List<Room> findByLocation(UUID locationId);
}
