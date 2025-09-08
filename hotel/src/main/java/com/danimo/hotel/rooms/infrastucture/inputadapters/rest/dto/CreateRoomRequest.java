package com.danimo.hotel.rooms.infrastucture.inputadapters.rest.dto;

import com.danimo.hotel.rooms.application.usecases.createroom.CreateAmenitiesDto;
import com.danimo.hotel.rooms.application.usecases.createroom.CreateRoomDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
public class CreateRoomRequest {
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
    private String imageUrl;
    private List<CreateAmenityRequest> amenities;

    public CreateRoomDto toDomain(){
        return new CreateRoomDto(
               getLocationId(),
               getName(),
               getDescription(),
               getCategory(),
               getStatus(),
               getPricePerDay(),
               getCapacity(),
               getNumberOfBed(),
               getRoomNumber(),
               getFloorNumber(),
               isSmokingAllowed(),
               getImageUrl(),
               getAmenities().stream()
                        .map(a -> new CreateAmenitiesDto(a.getName()))
                        .toList()
        );
    }
}
