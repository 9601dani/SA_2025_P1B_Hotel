package com.danimo.hotel.rooms.application.inputports;

import com.danimo.hotel.common.application.exceptions.EntityNotFoundException;
import com.danimo.hotel.rooms.domain.Room;

import java.util.UUID;

public interface FindingRoomByNumberRoomInputPort {
    Room findRoomByRoomNumber(int roomNumber, UUID locationId) throws EntityNotFoundException;
}
