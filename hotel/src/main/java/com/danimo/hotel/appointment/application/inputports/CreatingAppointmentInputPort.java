package com.danimo.hotel.appointment.application.inputports;

import com.danimo.hotel.appointment.application.usecases.createappointment.CreateAppointmentDto;
import com.danimo.hotel.appointment.domain.Appointment;

public interface CreatingAppointmentInputPort {
    Appointment createAppointment(CreateAppointmentDto dto);
}
