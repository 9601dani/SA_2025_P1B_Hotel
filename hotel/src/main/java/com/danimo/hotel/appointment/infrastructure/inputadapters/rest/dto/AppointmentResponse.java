package com.danimo.hotel.appointment.infrastructure.inputadapters.rest.dto;

import com.danimo.hotel.appointment.domain.Appointment;
import com.danimo.hotel.appointment.domain.AppointmentTotal;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Value
public class AppointmentResponse {
    UUID appointmentId;
    String description;
    String nit;
    UUID locationId;
    UUID userEmployeeId;
    String status;
    BigDecimal subtotal;
    BigDecimal tax;
    BigDecimal discount;
    String code;
    BigDecimal total;
    List<AppointmentItemReponseDto> items;
    LocalDate startDate;
    LocalDate endDate;

    public static AppointmentResponse fromDomain(Appointment appointment) {
        return new AppointmentResponse(
                appointment.getId().getAppointmentId(),
                appointment.getDescription(),
                appointment.getIdClient(),
                appointment.getLocationId(),
                appointment.getUserEmployeeId(),
                appointment.getStatus().name(),
                appointment.getSubTotal().getSubtotal(),
                appointment.getTax().getTax(),
                appointment.getDiscount().getDiscount(),
                appointment.getDiscount().getCode(),
                appointment.getTotal().getTotal(),
                appointment.getItems().stream().map(AppointmentItemReponseDto::fromDomain).toList(),
                appointment.getStartDate().getStartAt(),
                appointment.getEndDate().getEndAt()
        );
    }
}
