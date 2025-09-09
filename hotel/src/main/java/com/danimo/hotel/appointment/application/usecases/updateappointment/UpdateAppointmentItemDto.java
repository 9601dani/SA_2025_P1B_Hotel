package com.danimo.hotel.appointment.application.usecases.updateappointment;

import com.danimo.hotel.appointment.domain.Item;
import com.danimo.hotel.appointment.domain.ItemId;
import com.danimo.hotel.rooms.domain.RoomId;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class UpdateAppointmentItemDto {
    private UUID itemId;
    private UUID roomId;
    private String roomName;
    private int quantity;
    private BigDecimal unitPrice;

    public Item toDomain(){
        return new Item(
                itemId != null ? itemId : ItemId.generate().getItemId(),
                RoomId.fromUuid(roomId),
                roomName,
                quantity,
                unitPrice,
                null
        );
    }
}
