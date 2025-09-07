package com.danimo.hotel.rooms.application.inputports;

import com.danimo.hotel.rooms.application.usecases.updatestatusroom.UpdateStatusRoomDto;
import com.danimo.hotel.rooms.domain.Room;

public interface UpdatingRoomStatusInputPort {
    Room updateStatus(UpdateStatusRoomDto dto);
}
