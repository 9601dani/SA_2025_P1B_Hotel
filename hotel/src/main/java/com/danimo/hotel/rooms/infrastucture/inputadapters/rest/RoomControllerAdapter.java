package com.danimo.hotel.rooms.infrastucture.inputadapters.rest;

import com.danimo.hotel.common.infrastructure.annotations.WebAdapter;
import com.danimo.hotel.rooms.application.inputports.*;
import com.danimo.hotel.rooms.application.usecases.availablerooms.FindAvailableRoomsCoomandDto;
import com.danimo.hotel.rooms.application.usecases.createroom.CreateRoomDto;
import com.danimo.hotel.rooms.domain.Room;
import com.danimo.hotel.rooms.infrastucture.inputadapters.rest.dto.CreateRoomRequest;
import com.danimo.hotel.rooms.infrastucture.inputadapters.rest.dto.ResponseRoomDto;
import com.danimo.hotel.rooms.infrastucture.inputadapters.rest.dto.UpdateRoomRequest;
import com.danimo.hotel.rooms.infrastucture.inputadapters.rest.dto.UpdateStatusRoomRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Tag(name = "Rooms", description = "Operaciones relacionadas a las habitaciones")
@RestController
@RequestMapping("/v1/rooms")
@WebAdapter
public class RoomControllerAdapter {
    private final CreatingRoomInputPort creatingRoomInputPort;
    private final FindingRoomByIdInputPort findingRoomByIdInputPort;
    private final FindingRoomByLocationInputPort findingRoomByLocationInputPort;
    private final FindingRoomByNumberRoomInputPort findingRoomByNumberRoomInputPort;
    private final FindAvailableRoomsInputPort findAvailableRoomsInputPort;
    private final UpdatingRoomInputPort updatingRoomInputPort;
    private final UpdatingRoomStatusInputPort updatingRoomStatusInputPort;

    @Autowired
    public RoomControllerAdapter(CreatingRoomInputPort creatingRoomInputPort, FindingRoomByIdInputPort findingRoomByIdInputPort,
                                 FindingRoomByLocationInputPort findingRoomByLocationInputPort, FindingRoomByNumberRoomInputPort findingRoomByNumberRoomInputPort,
                                 UpdatingRoomInputPort updatingRoomInputPort, UpdatingRoomStatusInputPort updatingRoomStatusInputPort,
                                 FindAvailableRoomsInputPort findAvailableRoomsInputPort) {
        this.creatingRoomInputPort = creatingRoomInputPort;
        this.findingRoomByIdInputPort = findingRoomByIdInputPort;
        this.findingRoomByLocationInputPort = findingRoomByLocationInputPort;
        this.findingRoomByNumberRoomInputPort = findingRoomByNumberRoomInputPort;
        this.updatingRoomInputPort = updatingRoomInputPort;
        this.updatingRoomStatusInputPort = updatingRoomStatusInputPort;
        this.findAvailableRoomsInputPort = findAvailableRoomsInputPort;
    }

    @Operation(
            summary = "Crear nueva habitacion",
            description = "Devuelve la información de la habitacion correspondiente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Room creada"),
            @ApiResponse(responseCode = "404", description = "Room no creada")
    })
    @PostMapping
    @Transactional
    public ResponseEntity<ResponseRoomDto> createRoom(@RequestBody CreateRoomRequest dto){
        CreateRoomDto objectAdaptedFromRestToDomain = dto.toDomain();

        Room room = creatingRoomInputPort.createRoom(objectAdaptedFromRestToDomain);

        ResponseRoomDto response = ResponseRoomDto.fromDomain(room);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "Obtener las habitaciones del hotel",
            description = "Devuelve la información de todas las habitaciones correspondientes."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Habitaciones encontradas"),
            @ApiResponse(responseCode = "404", description = "Habitaciones no encontradas")
    })
    @GetMapping("/location/{id}")
    @Transactional
    public ResponseEntity<List<ResponseRoomDto>> getAllRoomByLocation(@PathVariable String  id) {
        List<ResponseRoomDto> orders = findingRoomByLocationInputPort.findByLocation(UUID.fromString(id))
                .stream()
                .map(ResponseRoomDto::fromDomain)
                .toList();

        return ResponseEntity.ok(orders);
    }

    @Operation(
            summary = "Busca la habitacion",
            description = "Devuelve la habitacion si existe."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Habitacion encontrada"),
            @ApiResponse(responseCode = "404", description = "Habitacion no encontrada")
    })
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseRoomDto> getRoomById(@PathVariable String id) {
        Room order = findingRoomByIdInputPort.findRoomById(UUID.fromString(id));

        return ResponseEntity.ok(ResponseRoomDto.fromDomain(order));
    }

    @Operation(
            summary = "Editar el estado de una habitacion",
            description = "Devuelve la habitacion actualizada."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Habitacion actualizada"),
            @ApiResponse(responseCode = "404", description = "Habitacion no actualizada")
    })
    @PutMapping("/status")
    @Transactional
    public ResponseEntity<ResponseRoomDto> updateStatusRoom(@RequestBody UpdateStatusRoomRequestDto dto) {
        Room order = updatingRoomStatusInputPort.updateStatus(dto.toDomain());
        return ResponseEntity.ok(ResponseRoomDto.fromDomain(order));
    }
    @Operation(
            summary = "Editar una habitacion",
            description = "Devuelve la habitacion actualizada."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Habitacion actualizada"),
            @ApiResponse(responseCode = "404", description = "Habitacion no actualizada")
    })
    @PutMapping
    @Transactional
    public ResponseEntity<ResponseRoomDto> updateRoom(@RequestBody UpdateRoomRequest dto) {
        Room room = updatingRoomInputPort.updateRoom(dto.toDomain());
        return ResponseEntity.ok(ResponseRoomDto.fromDomain(room));
    }
    @Operation(
            summary = "Habitaciones disponibles por rango de fechas",
            description = "Devuelve las habitaciones disponibles (sin traslape de reservas) para la ubicación indicada."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Habitaciones disponibles encontradas"),
            @ApiResponse(responseCode = "400", description = "Parámetros inválidos")
    })
    @GetMapping("/availability")
    @Transactional(readOnly = true)
    public ResponseEntity<List<ResponseRoomDto>> getAvailableRooms(
            @RequestParam UUID locationId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        var command = new FindAvailableRoomsCoomandDto(locationId, startDate, endDate);

        var rooms = findAvailableRoomsInputPort.findRoomsAvailabilities(command)
                .stream()
                .map(ResponseRoomDto::fromDomain)
                .toList();

        return ResponseEntity.ok(rooms);
    }

    @Operation(
            summary = "Habitaciones disponibles por rango de fechas para la app de cliente",
            description = "Devuelve las habitaciones disponibles (sin traslape de reservas) para la ubicación indicada."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Habitaciones disponibles encontradas"),
            @ApiResponse(responseCode = "400", description = "Parámetros inválidos")
    })
    @GetMapping("client/availability")
    @Transactional(readOnly = true)
    public ResponseEntity<List<ResponseRoomDto>> getAvailableRoomsbyClient(
            @RequestParam UUID locationId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        var command = new FindAvailableRoomsCoomandDto(locationId, startDate, endDate);

        var rooms = findAvailableRoomsInputPort.findRoomsAvailabilities(command)
                .stream()
                .map(ResponseRoomDto::fromDomain)
                .toList();

        return ResponseEntity.ok(rooms);
    }
}
