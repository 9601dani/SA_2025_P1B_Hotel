package com.danimo.hotel.rooms.application.usecases.createroom;

import com.danimo.hotel.common.application.annotations.UseCase;
import com.danimo.hotel.common.application.exceptions.EntityNotFoundException;
import com.danimo.hotel.rooms.application.inputports.CreatingRoomInputPort;
import com.danimo.hotel.rooms.application.outputports.persistence.FindingRoomByNumberRoomOutputPort;
import com.danimo.hotel.rooms.application.outputports.persistence.StoringRoomOutputPort;
import com.danimo.hotel.rooms.application.outputports.rest.ExistLocationOutputPort;
import com.danimo.hotel.rooms.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;

@UseCase
public class CreateRoomUseCase implements CreatingRoomInputPort {

    private final FindingRoomByNumberRoomOutputPort findingRoomByNumberRoomOutputPort;
    private final StoringRoomOutputPort storingRoomOutputPort;
    private final ExistLocationOutputPort existLocationOutputPort;

    @Autowired
    public CreateRoomUseCase(FindingRoomByNumberRoomOutputPort findingRoomByNumberRoomOutputPort,
                             StoringRoomOutputPort storingRoomOutputPort, ExistLocationOutputPort existLocationOutputPort) {
        this.findingRoomByNumberRoomOutputPort = findingRoomByNumberRoomOutputPort;
        this.storingRoomOutputPort = storingRoomOutputPort;
        this.existLocationOutputPort = existLocationOutputPort;
    }


    @Override
    public Room createRoom(CreateRoomDto dto) {
        if(!existLocationOutputPort.existLocation(dto.getLocationId())){
            throw new EntityNotFoundException("La location no existe");
        }

        if(findingRoomByNumberRoomOutputPort.findingRoomByNumberRoom(dto.getLocationId(), dto.getRoomNumber()).isPresent()) {
            throw new EntityNotFoundException("Ya existe una habitacion con el numero "+ dto.getRoomNumber()+" en el establecimiento ya existe");
        }

        Room room = dto.toDomain();

        return storingRoomOutputPort.save(room);
    }
}
