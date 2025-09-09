package com.danimo.hotel.appointment.application.usecases.findbyuserid;

import com.danimo.hotel.appointment.application.inputports.FindingAppointmentByClientIdInputPort;
import com.danimo.hotel.appointment.application.outputports.persistence.FindingAppointmentByClientId;
import com.danimo.hotel.appointment.domain.Appointment;
import com.danimo.hotel.common.application.annotations.UseCase;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@UseCase
public class FindAppointmentUserIdUseCase implements FindingAppointmentByClientIdInputPort {
    private final FindingAppointmentByClientId findingAppointmentByClientId;

    @Autowired
    public FindAppointmentUserIdUseCase(FindingAppointmentByClientId findingAppointmentByClientId) {
        this.findingAppointmentByClientId = findingAppointmentByClientId;
    }

    @Override
    public List<Appointment> findByClientId(String clientId) {
        return findingAppointmentByClientId.findByClientId(clientId);
    }
}
