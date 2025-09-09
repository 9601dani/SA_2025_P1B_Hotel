package com.danimo.hotel.appointment.application.usecases.createappointment;

import com.danimo.hotel.appointment.domain.Item;
import com.danimo.hotel.appointment.domain.ItemId;
import com.danimo.hotel.rooms.domain.RoomId;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class CreateAppointmentItemDto {
    private final UUID roomId;
    private final String roomName;
    private final int quantity;
    private final BigDecimal unitPrice;

    public Item toDomain(){
        return new Item(
                ItemId.generate().getItemId(),
                RoomId.fromUuid(roomId),
                roomName,
                quantity,
                unitPrice,
                null
        );
    }

}
