package com.danimo.hotel.rooms.infrastucture.inputadapters.rest.dto;

import com.danimo.hotel.rooms.application.usecases.updatestatusroom.UpdateStatusRoomDto;
import com.danimo.hotel.rooms.domain.RoomStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.util.UUID;

@Value
public class UpdateStatusRoomRequestDto {
    @NotBlank
    private UUID id;
    @NotBlank
    private String status;

    public UpdateStatusRoomDto toDomain(){
        return new UpdateStatusRoomDto(
                id,
                status != null ? RoomStatus.valueOf(status) : null
        );
    }
}
