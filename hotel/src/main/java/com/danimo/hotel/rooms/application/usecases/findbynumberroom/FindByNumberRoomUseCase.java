package com.danimo.hotel.rooms.application.usecases.findbynumberroom;

import com.danimo.hotel.common.application.annotations.UseCase;
import com.danimo.hotel.common.application.exceptions.EntityNotFoundException;
import com.danimo.hotel.rooms.application.inputports.FindingRoomByNumberRoomInputPort;
import com.danimo.hotel.rooms.application.outputports.persistence.FindingRoomByNumberRoomOutputPort;
import com.danimo.hotel.rooms.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@UseCase
public class FindByNumberRoomUseCase implements FindingRoomByNumberRoomInputPort {

    private final FindingRoomByNumberRoomOutputPort findingRoomByNumberRoomOutputPort;

    @Autowired
    public FindByNumberRoomUseCase(FindingRoomByNumberRoomOutputPort findingRoomByNumberRoomOutputPort) {
        this.findingRoomByNumberRoomOutputPort = findingRoomByNumberRoomOutputPort;
    }

    @Override
    public Room findRoomByRoomNumber(int roomNumber, UUID locationId) throws EntityNotFoundException {
        return findingRoomByNumberRoomOutputPort.findingRoomByNumberRoom(locationId,roomNumber)
                .orElseThrow(() -> new EntityNotFoundException("El numero de habitacion no existe en tu establecimiento"));
    }
}
