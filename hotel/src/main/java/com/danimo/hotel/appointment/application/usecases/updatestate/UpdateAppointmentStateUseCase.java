package com.danimo.hotel.appointment.application.usecases.updatestate;

import com.danimo.hotel.appointment.application.inputports.UpdatingStateAppointmentInputPort;
import com.danimo.hotel.appointment.application.outputports.persistence.FindingAppointmentByIdOutputPort;
import com.danimo.hotel.appointment.application.outputports.persistence.StoringAppointmentOutputPort;
import com.danimo.hotel.appointment.application.outputports.rest.CreatingBillOutputPort;
import com.danimo.hotel.appointment.domain.Appointment;
import com.danimo.hotel.appointment.domain.AppointmentStatus;
import com.danimo.hotel.common.application.annotations.UseCase;
import com.danimo.hotel.common.application.exceptions.EntityNotFoundException;
import com.danimo.hotel.rooms.application.inputports.UpdatingRoomStatusInputPort;
import com.danimo.hotel.rooms.application.usecases.updatestatusroom.UpdateStatusRoomDto;
import com.danimo.hotel.rooms.domain.RoomStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@UseCase
public class UpdateAppointmentStateUseCase implements UpdatingStateAppointmentInputPort {
    private final FindingAppointmentByIdOutputPort findingAppointmentByIdOutputPort;
    private final StoringAppointmentOutputPort storingAppointmentOutputPort;
    private final CreatingBillOutputPort creatingBillOutputPort;
    private final UpdatingRoomStatusInputPort updatingRoomStatusInputPort;

    @Autowired
    public UpdateAppointmentStateUseCase(FindingAppointmentByIdOutputPort findingAppointmentByIdOutputPort, StoringAppointmentOutputPort storingAppointmentOutputPort, CreatingBillOutputPort creatingBillOutputPort,
                                         UpdatingRoomStatusInputPort updatingRoomStatusInputPort) {
        this.findingAppointmentByIdOutputPort = findingAppointmentByIdOutputPort;
        this.storingAppointmentOutputPort = storingAppointmentOutputPort;
        this.creatingBillOutputPort = creatingBillOutputPort;
        this.updatingRoomStatusInputPort = updatingRoomStatusInputPort;
    }

    @Override
    public Appointment updateState(UpdateAppointmentStateDto dto) {
        Appointment appointment = findingAppointmentByIdOutputPort.findAppointmentById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("La reservacion no existe"));

        appointment.changeStatus(dto.getStatus());

        Appointment savedAppointment = storingAppointmentOutputPort.save(appointment);

        if(dto.getStatus() == AppointmentStatus.CANCELLED){
            appointment.getItems().forEach(item -> {
                UUID roomId = item.getRoomId().getId();
                UpdateStatusRoomDto updateRoomDto = new UpdateStatusRoomDto(roomId, RoomStatus.AVAILABLE);
                updatingRoomStatusInputPort.updateStatus(updateRoomDto);
            });
        }else if(dto.getStatus() == AppointmentStatus.COMPLETED){
            appointment.getItems().forEach(item -> {
                UUID roomId = item.getRoomId().getId();
                UpdateStatusRoomDto updateRoomDto = new UpdateStatusRoomDto(roomId, RoomStatus.MAINTENANCE);
                updatingRoomStatusInputPort.updateStatus(updateRoomDto);
            });

            if(!creatingBillOutputPort.createBill(savedAppointment)){
                throw new EntityNotFoundException("La factura no pudo generarse para la orden");
            }
        }
        return savedAppointment;
    }
}
