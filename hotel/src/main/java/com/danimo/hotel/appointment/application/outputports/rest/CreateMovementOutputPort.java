package com.danimo.hotel.appointment.application.outputports.rest;

import com.danimo.hotel.appointment.infrastructure.outputadapters.rest.dto.CreateMovementRequestDto;

public interface CreateMovementOutputPort {
    boolean isSuccess(CreateMovementRequestDto dto);
}
