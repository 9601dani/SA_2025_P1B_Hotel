package com.danimo.hotel.rooms.application.inputports;

import com.danimo.hotel.rooms.application.usecases.updateroom.UpdateRoomDto;
import com.danimo.hotel.rooms.domain.Room;

public interface UpdatingRoomInputPort {
    Room updateRoom(UpdateRoomDto dto);
}
