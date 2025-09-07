package com.danimo.hotel.rooms.application.usecases.findidroom;

import com.danimo.hotel.common.application.annotations.UseCase;
import com.danimo.hotel.common.application.exceptions.EntityNotFoundException;
import com.danimo.hotel.rooms.application.inputports.FindingRoomByIdInputPort;
import com.danimo.hotel.rooms.application.outputports.persistence.FindingRoomByIdOutputPort;
import com.danimo.hotel.rooms.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@UseCase
public class FindRoomByIdUseCase implements FindingRoomByIdInputPort {
    private final FindingRoomByIdOutputPort findingRoomByIdOutputPort;

    @Autowired
    public FindRoomByIdUseCase(FindingRoomByIdOutputPort findingRoomByIdOutputPort) {
        this.findingRoomByIdOutputPort = findingRoomByIdOutputPort;
    }

    @Override
    public Room findRoomById(UUID id) {
        return findingRoomByIdOutputPort.findRoomById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontro la habitacion"));
    }
}
