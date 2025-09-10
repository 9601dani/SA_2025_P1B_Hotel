package com.danimo.hotel.appointment.application.inputports;

import com.danimo.hotel.appointment.application.usecases.checkin.CheckinDto;
import com.danimo.hotel.appointment.domain.Appointment;

public interface CheckInAppointmentInputPort {
    Appointment checkInAppointment(CheckinDto dto);
}
