package com.danimo.hotel.appointment.infrastructure.outputadapters.persistence;

import com.danimo.hotel.appointment.application.outputports.persistence.*;
import com.danimo.hotel.appointment.domain.Appointment;
import com.danimo.hotel.appointment.domain.AppointmentStatus;
import com.danimo.hotel.appointment.infrastructure.outputadapters.persistence.entity.AppointmentDbEntity;
import com.danimo.hotel.appointment.infrastructure.outputadapters.persistence.entity.mapper.AppointmentPersistenceMapper;
import com.danimo.hotel.appointment.infrastructure.outputadapters.persistence.repository.AppointmentDbEntityJpaRepository;
import com.danimo.hotel.common.infrastructure.annotations.PersistenceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@PersistenceAdapter
public class AppointmentRepositoryOutputAdapter implements FindingAllAppointmentsOutputPort,
        FindingAppointmentByClientId, FindingAppointmentByIdOutputPort, StoringAppointmentOutputPort,
        FindingAppointmentByDatesOutputPort, FindingAppointmentByLocationIdOutputPort {

    private final AppointmentDbEntityJpaRepository appointmentDbEntityJpaRepository;
    private final AppointmentPersistenceMapper appointmentPersistenceMapper;

    @Autowired
    public AppointmentRepositoryOutputAdapter(AppointmentDbEntityJpaRepository appointmentDbEntityJpaRepository, AppointmentPersistenceMapper appointmentPersistenceMapper) {
        this.appointmentDbEntityJpaRepository = appointmentDbEntityJpaRepository;
        this.appointmentPersistenceMapper = appointmentPersistenceMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Appointment> findAll() {
        return appointmentDbEntityJpaRepository.findAll()
                .stream()
                .map(appointmentPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Appointment> findByClientId(String clientId) {
        return appointmentDbEntityJpaRepository.findByIdClient(clientId)
                .stream()
                .map(appointmentPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Appointment> findAppointmentById(UUID id) {
        return appointmentDbEntityJpaRepository.findById(id)
                .map(appointmentPersistenceMapper::toDomain);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Appointment save(Appointment appointment) {
        AppointmentDbEntity appointmentDbEntity = appointmentPersistenceMapper.toDbEntity(appointment);

        AppointmentDbEntity saved = appointmentDbEntityJpaRepository.save(appointmentDbEntity);

        return appointmentPersistenceMapper.toDomain(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsActiveOverlapForRoom(UUID roomId, LocalDate requestedStart, LocalDate requestedEnd) {
        List<AppointmentStatus> activeStatuses = List.of(AppointmentStatus.CREATED, AppointmentStatus.IN_PROGRESS);
        return appointmentDbEntityJpaRepository.existsByItems_RoomIdAndStatusInAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                roomId, activeStatuses, requestedEnd, requestedStart
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<Appointment> findByLocationId(String locationId) {
        return appointmentDbEntityJpaRepository.findByLocationId(UUID.fromString(locationId))
                .stream()
                .map(appointmentPersistenceMapper::toDomain)
                .toList();
    }
}
