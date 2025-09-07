package com.danimo.hotel.rooms.application.usecases.findroombylocation;

import com.danimo.hotel.common.application.annotations.UseCase;
import com.danimo.hotel.common.application.exceptions.EntityNotFoundException;
import com.danimo.hotel.rooms.application.inputports.FindingRoomByLocationInputPort;
import com.danimo.hotel.rooms.application.outputports.persistence.FindingRoomByLocationOutputPort;
import com.danimo.hotel.rooms.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@UseCase
public class FindRoomsByLocationUseCase implements FindingRoomByLocationInputPort {
    private final FindingRoomByLocationOutputPort findingRoomByLocationOutputPort;

    @Autowired
    public FindRoomsByLocationUseCase(FindingRoomByLocationOutputPort findingRoomByLocationOutputPort) {
        this.findingRoomByLocationOutputPort = findingRoomByLocationOutputPort;
    }


    @Override
    public List<Room> findByLocation(UUID locationId) {
        return findingRoomByLocationOutputPort.findRoomByLocation(locationId);
    }
}
