package com.danimo.hotel.rooms.application.inputports;

import com.danimo.hotel.rooms.application.usecases.createroom.CreateRoomDto;
import com.danimo.hotel.rooms.domain.Room;

public interface CreatingRoomInputPort {
    Room createRoom(CreateRoomDto dto);
}
