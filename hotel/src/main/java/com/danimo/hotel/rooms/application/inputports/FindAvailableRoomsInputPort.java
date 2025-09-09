package com.danimo.hotel.rooms.application.inputports;

import com.danimo.hotel.rooms.application.usecases.availablerooms.FindAvailableRoomsCoomandDto;
import com.danimo.hotel.rooms.domain.Room;

import java.util.List;

public interface FindAvailableRoomsInputPort {
    List<Room> findRoomsAvailabilities(FindAvailableRoomsCoomandDto command);
}
