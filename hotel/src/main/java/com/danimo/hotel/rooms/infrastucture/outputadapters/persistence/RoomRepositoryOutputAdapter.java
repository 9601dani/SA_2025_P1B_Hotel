package com.danimo.hotel.rooms.infrastucture.outputadapters.persistence;

import com.danimo.hotel.common.infrastructure.annotations.PersistenceAdapter;
import com.danimo.hotel.rooms.application.outputports.persistence.FindingRoomByIdOutputPort;
import com.danimo.hotel.rooms.application.outputports.persistence.FindingRoomByLocationOutputPort;
import com.danimo.hotel.rooms.application.outputports.persistence.FindingRoomByNumberRoomOutputPort;
import com.danimo.hotel.rooms.application.outputports.persistence.StoringRoomOutputPort;
import com.danimo.hotel.rooms.domain.Room;
import com.danimo.hotel.rooms.infrastucture.outputadapters.persistence.entity.RoomDbEntity;
import com.danimo.hotel.rooms.infrastucture.outputadapters.persistence.entity.mapper.RoomPersistenceMapper;
import com.danimo.hotel.rooms.infrastucture.outputadapters.persistence.repository.RoomDbEntityJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@PersistenceAdapter
public class RoomRepositoryOutputAdapter implements FindingRoomByIdOutputPort,
        FindingRoomByLocationOutputPort, FindingRoomByNumberRoomOutputPort, StoringRoomOutputPort {

    private final RoomDbEntityJpaRepository roomDbEntityJpaRepository;
    private final RoomPersistenceMapper roomPersistenceMapper;

    @Autowired
    public RoomRepositoryOutputAdapter(RoomDbEntityJpaRepository roomDbEntityJpaRepository, RoomPersistenceMapper mapper) {
        this.roomDbEntityJpaRepository = roomDbEntityJpaRepository;
        this.roomPersistenceMapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Room> findRoomById(UUID id) {
        return roomDbEntityJpaRepository.findById(id)
                .map(roomPersistenceMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Room> findRoomByLocation(UUID locationId) {
        return roomDbEntityJpaRepository.findByLocationId(locationId)
                .stream()
                .map(roomPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Room> findingRoomByNumberRoom(UUID locationId, int numberRoom) {
        return roomDbEntityJpaRepository.findByRoomNumberAndLocationId(numberRoom, locationId)
                .map(roomPersistenceMapper::toDomain);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Room save(Room room) {
        RoomDbEntity roomDbEntity = roomPersistenceMapper.toDbEntity(room);

        RoomDbEntity savedRoomDbEntity = roomDbEntityJpaRepository.save(roomDbEntity);

        return roomPersistenceMapper.toDomain(savedRoomDbEntity);
    }
}
