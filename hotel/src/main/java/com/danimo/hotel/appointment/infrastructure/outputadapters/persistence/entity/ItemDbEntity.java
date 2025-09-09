package com.danimo.hotel.appointment.infrastructure.outputadapters.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDbEntity {
    @Id
    private UUID id;

    @Column
    private UUID roomId;
    @Column
    private String roomName;
    @Column
    private int quantity;
    @Column
    private BigDecimal unitPrice;
    @Column
    private BigDecimal lineTotal;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id", nullable = false)
    private AppointmentDbEntity appointment;
}
