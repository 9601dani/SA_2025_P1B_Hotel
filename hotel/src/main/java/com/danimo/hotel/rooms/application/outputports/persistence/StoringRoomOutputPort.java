package com.danimo.hotel.rooms.application.outputports.persistence;

import com.danimo.hotel.rooms.domain.Room;

public interface StoringRoomOutputPort {
    Room save(Room room);
}
