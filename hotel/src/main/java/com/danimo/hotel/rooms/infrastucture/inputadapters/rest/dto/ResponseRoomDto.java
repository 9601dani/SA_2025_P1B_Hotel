package com.danimo.hotel.rooms.infrastucture.inputadapters.rest.dto;

import com.danimo.hotel.rooms.domain.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseRoomDto {
    private UUID id;
    private UUID locationId;
    private String name;
    private String description;
    private String category;
    private String status;
    private BigDecimal pricePerDay;
    private int capacity;
    private int numberOfBed;
    private int roomNumber;
    private int floorNumber;
    private boolean smokingAllowed;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<AmenityResponse> amenities;

    public static ResponseRoomDto fromDomain(Room room) {
        return new ResponseRoomDto(
                room.getId().getId(),
                room.getLocationId(),
                room.getName(),
                room.getDescription(),
                room.getCategory().getName(),
                room.getStatus().name(),
                room.getPricePerDay().getPricePerDay(),
                room.getCapacity(),
                room.getNumberOfBeds(),
                room.getRoomNumber(),
                room.getFloorNumber(),
                room.isSmokingAllowed(),
                room.getCreatedAt().getCreatedAt(),
                room.getUpdatedAt().getUpdatedAt(),
                room.getAmenities().stream()
                        .map(a -> new AmenityResponse(a.getName()))
                        .toList()
        );
    }
}
