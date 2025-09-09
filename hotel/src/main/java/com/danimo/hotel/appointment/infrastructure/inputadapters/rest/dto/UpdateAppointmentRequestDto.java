package com.danimo.hotel.appointment.infrastructure.inputadapters.rest.dto;

import com.danimo.hotel.appointment.application.usecases.updateappointment.UpdateAppointmentDto;
import com.danimo.hotel.appointment.application.usecases.updateappointment.UpdateAppointmentItemDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Value
public class UpdateAppointmentRequestDto {
    @NotBlank
    UUID appointmentId;
    String description;
    @NotBlank
    UUID locationId;
    @NotBlank
    String nit;
    @NotBlank
    UUID userEmployeeId;
    String status;
    String code;
    BigDecimal discount;
    @NotBlank
    LocalDate startDate;
    @NotBlank
    LocalDate endDate;
    List<UpdateAppointmentItemRequestDto> items;

    public UpdateAppointmentDto toAppli(){
        return new UpdateAppointmentDto(
                appointmentId,
                description,
                locationId,
                nit,
                userEmployeeId,
                startDate,
                endDate,
                items != null
                        ? items.stream().map(UpdateAppointmentItemRequestDto::toAppli).toList()
                        : List.<UpdateAppointmentItemDto>of(),
                code,
                discount

        );
    }
}
