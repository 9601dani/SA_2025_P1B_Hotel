package com.danimo.hotel.rooms.infrastucture.inputadapters.rest.dto;

import com.danimo.hotel.rooms.application.usecases.availablerooms.FindAvailableRoomsCoomandDto;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
public class AvailabilityRequestDto {
    private final UUID locationId;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public FindAvailableRoomsCoomandDto toAppli(){
        return new FindAvailableRoomsCoomandDto(locationId, startDate, endDate);
    }
}
