package com.danimo.hotel.rooms.application.usecases.updatestatusroom;

import com.danimo.hotel.common.application.annotations.UseCase;
import com.danimo.hotel.common.application.exceptions.EntityNotFoundException;
import com.danimo.hotel.rooms.application.inputports.UpdatingRoomStatusInputPort;
import com.danimo.hotel.rooms.application.outputports.persistence.FindingRoomByIdOutputPort;
import com.danimo.hotel.rooms.application.outputports.persistence.StoringRoomOutputPort;
import com.danimo.hotel.rooms.application.outputports.rest.ExistLocationOutputPort;
import com.danimo.hotel.rooms.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;

@UseCase
public class UpdateStatusRoomUseCase implements UpdatingRoomStatusInputPort {
    private final FindingRoomByIdOutputPort findingRoomByIdOutputPort;
    private final StoringRoomOutputPort storingRoomOutputPort;

    @Autowired
    public UpdateStatusRoomUseCase(FindingRoomByIdOutputPort findingRoomByIdOutputPort, StoringRoomOutputPort storingRoomOutputPort) {
        this.findingRoomByIdOutputPort = findingRoomByIdOutputPort;
        this.storingRoomOutputPort = storingRoomOutputPort;
    }


    @Override
    public Room updateStatus(UpdateStatusRoomDto dto) {
        Room room = findingRoomByIdOutputPort.findRoomById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("La habitacion no existe"));

        Room newRoom = room.withStatus(dto.getStatus());

        return storingRoomOutputPort.save(newRoom);

    }
}
