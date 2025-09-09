package com.danimo.hotel.appointment.application.usecases.createappointment;

import com.danimo.hotel.appointment.domain.*;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Value
public class CreateAppointmentDto {
    private final String description;
    private final UUID locationId;
    private final String nit;
    private final UUID userEmployeeId;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final List<CreateAppointmentItemDto> items;
    private final String code;
    private final BigDecimal discount;
    public Appointment toDomain(){
        List<Item> domainItems = items.stream()
                .map(CreateAppointmentItemDto::toDomain)
                .toList();

        return new Appointment(
                AppointmentId.generate(),
                description,
                locationId,
                nit,
                AppointmentStatus.CREATED,
                null,
                AppointmentDiscount.fromBigdecimalAndCode(discount, code),
                null,
                AppointmentTotal.fromBigDecimal(BigDecimal.ZERO),
                AppointmentCreatedAt.generate(),
                AppointmentUpdatedAt.generate(),
                AppointmentStartDate.fromLocalDateTime(startDate),
                AppointmentEndDate.fromLocalDate(endDate),
                userEmployeeId,
                domainItems
        );
    }
}
