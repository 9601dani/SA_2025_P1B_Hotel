package com.danimo.hotel.appointment.application.outputports.persistence;

import java.time.LocalDate;
import java.util.UUID;

public interface FindingAppointmentByDatesOutputPort {
    boolean existsActiveOverlapForRoom(UUID roomId, LocalDate requestedStart, LocalDate requestedEnd);
}
