package com.danimo.hotel.appointment.application.usecases.updateappointment;

import com.danimo.hotel.appointment.domain.*;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Value
public class UpdateAppointmentDto {
    private UUID appointmentId;
    private String description;
    private UUID locationId;
    private String nit;
    private UUID userEmployeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<UpdateAppointmentItemDto> items;
    private String code;
    private BigDecimal discount;

    public Appointment applyChanges(Appointment current){
        List<Item> updatedItems = (items != null && !items.isEmpty())
                ? items.stream().map(UpdateAppointmentItemDto::toDomain).toList()
                : current.getItems();

        return new Appointment(
                AppointmentId.generate(),
                description,
                locationId,
                nit,
                current.getStatus(),
                null,
                AppointmentDiscount.fromBigdecimalAndCode(discount, code),
                null,
                AppointmentTotal.fromBigDecimal(BigDecimal.ZERO),
                current.getCreatedAt(),
                AppointmentUpdatedAt.generate(),
                AppointmentStartDate.fromLocalDateTime(startDate),
                AppointmentEndDate.fromLocalDate(endDate),
                userEmployeeId,
                updatedItems
        );
    }
}
