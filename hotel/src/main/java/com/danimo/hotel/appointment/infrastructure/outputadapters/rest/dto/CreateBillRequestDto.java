package com.danimo.hotel.appointment.infrastructure.outputadapters.rest.dto;

import com.danimo.hotel.appointment.domain.Appointment;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
public class CreateBillRequestDto {
    private UUID locationId;
    private String clientId;
    private String clientName;
    private String billTypeService;
    private String moneda;
    private List<CreateBillItemRequestDto> items;

    public static CreateBillRequestDto fromOrder(Appointment appointment) {
        return new CreateBillRequestDto(
                appointment.getLocationId(),
                appointment.getIdClient(),
                appointment.getDescription(),
                "HOTEL",
                "GTQ",
                appointment.getItems().stream()
                        .map(i -> new CreateBillItemRequestDto(
                                i.getRoomName(),
                                i.getQuantity(),
                                i.getUnitPrice()
                        )).toList()
        );
    }
}
