package com.danimo.hotel.rooms.infrastucture.outputadapters.persistence.entity;

import com.danimo.hotel.rooms.domain.RoomStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "rooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDbEntity {
    @Id
    private UUID id;
    @Column
    private UUID locationId;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String category;

    @Enumerated(EnumType.STRING)
    private RoomStatus status;
    @Column
    private BigDecimal costPerDay;
    @Column
    private BigDecimal pricePerDay;
    @Column
    private int capacity;
    @Column
    private int numberOfBeds;
    @Column
    private int roomNumber;
    @Column
    private int floorNumber;
    @Column
    private boolean smokingAllowed;
    @Column
    private LocalDateTime createdAt;
    @Column
    private LocalDateTime updatedAt;
    @Column
    private String imageUrl;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AmenityDbEntity> amenities = new ArrayList<>();
}
