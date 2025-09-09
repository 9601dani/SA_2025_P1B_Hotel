package com.danimo.hotel.appointment.infrastructure.outputadapters.persistence.entity;

import com.danimo.hotel.appointment.domain.AppointmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDbEntity {
    @Id
    private UUID id;
    @Column
    private String description;
    @Column
    private UUID locationId;
    @Column
    private String idClient;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;
    @Column
    private BigDecimal subTotal;
    @Embedded
    private AppointmentDiscountEmbeddable discount;
    @Column
    private BigDecimal tax;
    @Column
    private BigDecimal total;
    @Column
    private LocalDateTime createdAt;
    @Column
    private LocalDateTime updatedAt;
    @Column
    private LocalDate startDate;
    @Column
    private LocalDate endDate;
    @Column
    private UUID userEmployeeId;

    @OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ItemDbEntity> items = new ArrayList<>();
}
