package com.danimo.hotel.rooms.application.usecases.updateroom;

import com.danimo.hotel.common.application.annotations.UseCase;
import com.danimo.hotel.rooms.application.inputports.UpdatingRoomInputPort;
import com.danimo.hotel.rooms.application.outputports.persistence.FindingRoomByIdOutputPort;
import com.danimo.hotel.rooms.application.outputports.persistence.StoringRoomOutputPort;
import com.danimo.hotel.rooms.application.outputports.rest.ExistLocationOutputPort;
import com.danimo.hotel.rooms.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;

@UseCase
public class UpdateRoomUseCase implements UpdatingRoomInputPort {
    private final FindingRoomByIdOutputPort findingRoomByIdOutputPort;
    private final StoringRoomOutputPort storingRoomOutputPort;
    private final ExistLocationOutputPort existLocationOutputPort;

    @Autowired
    public UpdateRoomUseCase(FindingRoomByIdOutputPort findingRoomByIdOutputPort, StoringRoomOutputPort storingRoomOutputPort,
                             ExistLocationOutputPort existLocationOutputPort) {
        this.findingRoomByIdOutputPort = findingRoomByIdOutputPort;
        this.storingRoomOutputPort = storingRoomOutputPort;
        this.existLocationOutputPort = existLocationOutputPort;
    }

    @Override
    public Room updateRoom(UpdateRoomDto dto) {
        boolean locationExists = existLocationOutputPort.existLocation(dto.getLocationId());
        if (!locationExists) {
            throw new IllegalArgumentException("El hotel no existe");
        }

        Room existingRoom = findingRoomByIdOutputPort.findRoomById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException("La habitacion no existe"));

        Room updatedRoom = dto.toDomain(existingRoom);

        storingRoomOutputPort.save(updatedRoom);

        return updatedRoom;
    }
}

