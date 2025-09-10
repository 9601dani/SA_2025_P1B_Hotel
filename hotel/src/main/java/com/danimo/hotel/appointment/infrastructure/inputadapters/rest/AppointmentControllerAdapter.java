package com.danimo.hotel.appointment.infrastructure.inputadapters.rest;

import com.danimo.hotel.appointment.application.inputports.*;
import com.danimo.hotel.appointment.application.usecases.createappointment.CreateAppointmentDto;
import com.danimo.hotel.appointment.domain.Appointment;
import com.danimo.hotel.appointment.infrastructure.inputadapters.rest.dto.*;
import com.danimo.hotel.common.infrastructure.annotations.WebAdapter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Appointments", description = "Operaciones relacionadas a las reservaciones")
@RestController
@RequestMapping("/v1/appointments")
@WebAdapter
public class AppointmentControllerAdapter {
    private final CreatingAppointmentInputPort creatingAppointmentInputPort;
    private final FindingAppointmentByClientIdInputPort findingAppointmentByClientIdInputPort;
    private final FindingAppointmentByIdInputPort findingAppointmentByIdInputPort;
    private final ListingAllAppointmentInputPort listingAllAppointmentInputPort;
    private final UpdatingAppointmentInputPort updatingAppointmentInputPort;
    private final UpdatingStateAppointmentInputPort updatingStateAppointmentInputPort;
    private final FindingAppointmentsByLocationIdInputPort findingAppointmentsByLocationIdInputPort;
    private final CheckInAppointmentInputPort checkInAppointmentInputPort;

    @Autowired
    public AppointmentControllerAdapter(CreatingAppointmentInputPort creatingAppointmentInputPort, FindingAppointmentByIdInputPort findingAppointmentByIdInputPort,
                                        FindingAppointmentByClientIdInputPort findingAppointmentByClientIdInputPort, ListingAllAppointmentInputPort listingAllAppointmentInputPort,
                                        UpdatingAppointmentInputPort updatingAppointmentInputPort, UpdatingStateAppointmentInputPort updatingStateAppointmentInputPort,
                                        FindingAppointmentsByLocationIdInputPort findingAppointmentsByLocationIdInputPort, CheckInAppointmentInputPort checkInAppointmentInputPort) {
        this.creatingAppointmentInputPort = creatingAppointmentInputPort;
        this.findingAppointmentByIdInputPort = findingAppointmentByIdInputPort;
        this.findingAppointmentByClientIdInputPort = findingAppointmentByClientIdInputPort;
        this.listingAllAppointmentInputPort = listingAllAppointmentInputPort;
        this.updatingAppointmentInputPort = updatingAppointmentInputPort;
        this.updatingStateAppointmentInputPort = updatingStateAppointmentInputPort;
        this.findingAppointmentsByLocationIdInputPort = findingAppointmentsByLocationIdInputPort;
        this.checkInAppointmentInputPort = checkInAppointmentInputPort;
    }

    @Operation(
            summary = "Crear nueva reservacion",
            description = "Devuelve la información de la reservacion correspondiente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservacion creada"),
            @ApiResponse(responseCode = "404", description = "Reservacion no creada")
    })
    @PostMapping
    @Transactional
    public ResponseEntity<AppointmentResponse> createAppointment(@RequestBody CreateAppointmentRequestDto dto){
        CreateAppointmentDto objectAdaptedFromRestToDomain = dto.toAppli();


        Appointment appointment = creatingAppointmentInputPort.createAppointment(objectAdaptedFromRestToDomain);

        AppointmentResponse response = AppointmentResponse.fromDomain(appointment);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @Operation(
            summary = "Obtener las reservaciones",
            description = "Devuelve la información de todas las reservaciones correspondientes."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservaciones encontradas"),
            @ApiResponse(responseCode = "404", description = "Reservaciones no encontradas")
    })
    @GetMapping
    @Transactional
    public ResponseEntity<List<AppointmentResponse>> getAllAppointments() {
        List<AppointmentResponse> appointments = listingAllAppointmentInputPort.getAllAppointments()
                .stream()
                .map(AppointmentResponse::fromDomain)
                .toList();

        return ResponseEntity.ok(appointments);
    }

    @Operation(
            summary = "Busca la reservacion",
            description = "Devuelve la reservacion si existe."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservacion encontrada"),
            @ApiResponse(responseCode = "404", description = "Reservacion no encontrada")
    })
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<AppointmentResponse> getAppointmentById(@PathVariable String id) {
        Appointment appointment = findingAppointmentByIdInputPort.findById(id);

        return ResponseEntity.ok(AppointmentResponse.fromDomain(appointment));
    }

    @Operation(
            summary = "Busca la reservacion por nit del cliente",
            description = "Devuelve la reservacion si existe."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservacion encontrada"),
            @ApiResponse(responseCode = "404", description = "Reservacion no encontrada")
    })
    @GetMapping("/clients/{id}")
    @Transactional
    public ResponseEntity<List<AppointmentResponse>> getAppointmentByClientId(@PathVariable String id) {
        List<Appointment> appointment = findingAppointmentByClientIdInputPort.findByClientId(id);

        return ResponseEntity.ok(appointment.stream().map(AppointmentResponse::fromDomain).toList());
    }

    @Operation(
            summary = "Editar una reservacion",
            description = "Devuelve la reservacion actualizada."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservacion actualizada"),
            @ApiResponse(responseCode = "404", description = "Reservacion no actualizada")
    })
    @PutMapping
    @Transactional
    public ResponseEntity<AppointmentResponse> updateAppointment(@RequestBody UpdateAppointmentRequestDto dto) {
        Appointment appointment = updatingAppointmentInputPort.update(dto.toAppli());
        return ResponseEntity.ok(AppointmentResponse.fromDomain(appointment));
    }

    @Operation(
            summary = "Editar el estado de una reservacion",
            description = "Devuelve la reservacion actualizada."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservacion pagada"),
            @ApiResponse(responseCode = "404", description = "Reservacion no pagada")
    })
    @PutMapping("/status")
    @Transactional
    public ResponseEntity<AppointmentResponse> updateStatusAppointment(@RequestBody UpdateStatusAppointmentRequestDto dto) {
        Appointment appointment = updatingStateAppointmentInputPort.updateState(dto.toAppli());
        return ResponseEntity.ok(AppointmentResponse.fromDomain(appointment));
    }
    @Operation(
            summary = "Busca las reservaciones del establecimiento",
            description = "Devuelve las reservaciones si existen."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservaciones encontradas"),
            @ApiResponse(responseCode = "404", description = "Reservaciones no encontradas")
    })
    @GetMapping("/locations/{id}")
    @Transactional
    public ResponseEntity<List<AppointmentResponse>> getAppointmentByLocationId(@PathVariable String id) {
        List<Appointment> appointments = findingAppointmentsByLocationIdInputPort.findByLocationId(id);

        return ResponseEntity.ok(appointments.stream().map(AppointmentResponse::fromDomain).toList());
    }

    @Operation(
            summary = "Hacer checkin de una reservacion",
            description = "Devuelve la reservacion actuzalizada si existe."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservacion actualizada"),
            @ApiResponse(responseCode = "404", description = "Reservacion no actualizada")
    })
    @PostMapping("/checkin")
    @Transactional
    public ResponseEntity<AppointmentResponse> checkInAppointment(@RequestBody CheckinRequestDto dto) {
        Appointment appointment = checkInAppointmentInputPort.checkInAppointment(dto.toAppli());

        return ResponseEntity.ok(AppointmentResponse.fromDomain(appointment));
    }
}
