package com.danimo.hotel.rooms.application.usecases.updatestatusroom;

import com.danimo.hotel.appointment.application.outputports.rest.CreateMovementOutputPort;
import com.danimo.hotel.appointment.infrastructure.outputadapters.rest.dto.CreateMovementRequestDto;
import com.danimo.hotel.common.application.annotations.UseCase;
import com.danimo.hotel.common.application.exceptions.EntityNotFoundException;
import com.danimo.hotel.rooms.application.inputports.UpdatingRoomStatusInputPort;
import com.danimo.hotel.rooms.application.outputports.persistence.FindingRoomByIdOutputPort;
import com.danimo.hotel.rooms.application.outputports.persistence.StoringRoomOutputPort;
import com.danimo.hotel.rooms.application.outputports.rest.ExistLocationOutputPort;
import com.danimo.hotel.rooms.domain.Room;
import com.danimo.hotel.rooms.domain.RoomStatus;
import org.springframework.beans.factory.annotation.Autowired;

@UseCase
public class UpdateStatusRoomUseCase implements UpdatingRoomStatusInputPort {
    private final FindingRoomByIdOutputPort findingRoomByIdOutputPort;
    private final StoringRoomOutputPort storingRoomOutputPort;
    private final CreateMovementOutputPort createMovementOutputPort;

    @Autowired
    public UpdateStatusRoomUseCase(FindingRoomByIdOutputPort findingRoomByIdOutputPort, StoringRoomOutputPort storingRoomOutputPort,
                                   CreateMovementOutputPort createMovementOutputPort) {
        this.findingRoomByIdOutputPort = findingRoomByIdOutputPort;
        this.storingRoomOutputPort = storingRoomOutputPort;
        this.createMovementOutputPort = createMovementOutputPort;
    }


    @Override
    public Room updateStatus(UpdateStatusRoomDto dto) {
        Room room = findingRoomByIdOutputPort.findRoomById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("La habitacion no existe"));

        Room newRoom = room.withStatus(dto.getStatus());

        if(newRoom.getStatus() == RoomStatus.AVAILABLE){
            var taxMovement = CreateMovementRequestDto.generateDto(
                    "HOTEL",
                    "DEBIT",
                    "Pago por mantenimiento sobre habitación",
                    room.getCost().getCost(),
                    room.getLocationId(),
                    ""
            );

            if (!createMovementOutputPort.isSuccess(taxMovement)) {
                throw new RuntimeException("No se pudo registrar el movimiento de impuesto en reportes");
            }
        }

        return storingRoomOutputPort.save(newRoom);

    }
}
