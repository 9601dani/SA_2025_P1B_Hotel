package com.danimo.hotel.appointment.infrastructure.outputadapters.persistence.repository;

import com.danimo.hotel.appointment.domain.Appointment;
import com.danimo.hotel.appointment.domain.AppointmentStatus;
import com.danimo.hotel.appointment.infrastructure.outputadapters.persistence.entity.AppointmentDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface AppointmentDbEntityJpaRepository extends JpaRepository<AppointmentDbEntity, UUID> {
    List<AppointmentDbEntity> findByIdClient(String idClient);
    boolean existsByItems_RoomIdAndStatusNotAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            UUID roomId, AppointmentStatus status, LocalDate requestedEnd, LocalDate requestedStart
    );

    List<AppointmentDbEntity> findByLocationId(UUID locationId);
}
