package com.danimo.hotel.rooms.application.outputports.rest;

import java.util.UUID;

public interface ExistLocationOutputPort {
    boolean existLocation(UUID location);
}
