package com.danimo.hotel.rooms.application.inputports;

import com.danimo.hotel.rooms.domain.Room;

import java.util.UUID;

public interface FindingRoomByIdInputPort {
    Room findRoomById (UUID id);
}
