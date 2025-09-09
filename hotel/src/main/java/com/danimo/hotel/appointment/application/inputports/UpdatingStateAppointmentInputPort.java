package com.danimo.hotel.appointment.application.inputports;

import com.danimo.hotel.appointment.application.usecases.updateappointment.UpdateAppointmentDto;
import com.danimo.hotel.appointment.application.usecases.updatestate.UpdateAppointmentStateDto;
import com.danimo.hotel.appointment.domain.Appointment;

public interface UpdatingStateAppointmentInputPort {
    Appointment updateState(UpdateAppointmentStateDto dto);
}
