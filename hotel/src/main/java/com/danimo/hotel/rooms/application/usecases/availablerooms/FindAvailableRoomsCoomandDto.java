package com.danimo.hotel.rooms.application.usecases.availablerooms;

import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
public class FindAvailableRoomsCoomandDto {
    UUID locationId;
    LocalDate startDate;
    LocalDate endDat;
}
