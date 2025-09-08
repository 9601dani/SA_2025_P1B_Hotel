package com.danimo.hotel.appointment.domain;

import com.danimo.hotel.common.domain.annotations.DomainEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@DomainEntity
@Getter
@AllArgsConstructor
public class Appointment {
    private final UUID id;

}
