package com.danimo.hotel.rooms.infrastucture.inputadapters.rest.dto;

import com.danimo.hotel.rooms.application.usecases.updateroom.UpdateAmenitiesDto;
import com.danimo.hotel.rooms.application.usecases.updateroom.UpdateRoomDto;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Value
public class UpdateRoomRequest {
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
    private List<UpdateAmenityRequest> amenities;
    private String imageUrl;

    public UpdateRoomDto toDomain() {
        List<UpdateAmenitiesDto> dtoAmenities = amenities.stream()
                .map(a -> new UpdateAmenitiesDto(a.getName()))
                .toList();

        return new UpdateRoomDto(
                id,
                locationId,
                name,
                description,
                category,
                status,
                pricePerDay,
                capacity,
                numberOfBed,
                roomNumber,
                floorNumber,
                smokingAllowed,
                imageUrl,
                dtoAmenities
        );
    }
}
