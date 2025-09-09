package com.danimo.hotel.appointment.application.outputports.rest;

import java.util.UUID;

public interface ExistEmployeeOutputPort {
    boolean existEmployee(UUID employeeId);
}
