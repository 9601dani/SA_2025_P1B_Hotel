package com.danimo.hotel.appointment.infrastructure.inputadapters.rest.dto;

import com.danimo.hotel.appointment.application.usecases.createappointment.CreateAppointmentDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Value
public class CreateAppointmentRequestDto {
    String description;
    @NotBlank
    UUID locationId;
    @NotBlank
    String nit;
    @NotBlank
    UUID userEmployeeId;
    @NotBlank
    LocalDate startDate;
    @NotBlank
    LocalDate endDate;
    String code;
    BigDecimal discount;

    List<CreateAppointmentItemRequestDto> items;

    public CreateAppointmentDto toAppli(){
        return new CreateAppointmentDto(
                description,
                locationId,
                nit,
                userEmployeeId,
                startDate,
                endDate,
                items.stream().map(CreateAppointmentItemRequestDto::toAppli).toList(),
                code,
                discount

        );
    }
}
