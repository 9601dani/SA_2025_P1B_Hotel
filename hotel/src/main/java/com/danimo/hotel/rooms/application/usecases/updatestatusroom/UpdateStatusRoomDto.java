package com.danimo.hotel.rooms.application.usecases.updatestatusroom;

import com.danimo.hotel.rooms.domain.RoomStatus;
import lombok.Value;

import java.util.UUID;

@Value
public class UpdateStatusRoomDto {
    private final UUID id;
    private final RoomStatus status;
}
