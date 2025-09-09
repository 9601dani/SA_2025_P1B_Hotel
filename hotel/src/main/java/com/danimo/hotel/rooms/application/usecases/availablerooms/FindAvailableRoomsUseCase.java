package com.danimo.hotel.rooms.application.usecases.availablerooms;

import com.danimo.hotel.common.application.annotations.UseCase;
import com.danimo.hotel.rooms.application.inputports.FindAvailableRoomsInputPort;
import com.danimo.hotel.rooms.application.outputports.persistence.RoomAvailabiityOutputPort;
import com.danimo.hotel.rooms.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

@UseCase
public class FindAvailableRoomsUseCase implements FindAvailableRoomsInputPort {
    private final RoomAvailabiityOutputPort roomAvailabiityOutputPort;

    @Autowired
    public FindAvailableRoomsUseCase(RoomAvailabiityOutputPort roomAvailabiityOutputPort) {
        this.roomAvailabiityOutputPort = roomAvailabiityOutputPort;
    }

    @Override
    public List<Room> findRoomsAvailabilities(FindAvailableRoomsCoomandDto command) {
        validate(command.getStartDate(), command.getEndDat());
        return roomAvailabiityOutputPort.findAvailableRooms(
                command.getLocationId(),
                command.getStartDate(),
                command.getEndDat()
        );
    }

    private void validate(LocalDate start, LocalDate end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("startDate y endDate son requeridos");
        }
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("endDate no puede ser anterior a startDate");
        }
    }
}
