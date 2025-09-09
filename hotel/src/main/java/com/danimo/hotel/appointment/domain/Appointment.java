package com.danimo.hotel.appointment.domain;

import com.danimo.hotel.common.domain.annotations.DomainEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@DomainEntity
@Getter
@AllArgsConstructor
public class Appointment {
    private static final BigDecimal TAX_RATE = BigDecimal.valueOf(0.12);

    private AppointmentId id;
    private String description;
    private UUID locationId;
    private String idClient;
    private AppointmentStatus status;
    private AppointmentSubtotal subTotal;
    private AppointmentDiscount discount;
    private AppointmentTax tax;
    private AppointmentTotal total;
    private AppointmentCreatedAt createdAt;
    private AppointmentUpdatedAt updatedAt;
    private AppointmentStartDate startDate;
    private AppointmentEndDate endDate;
    private UUID userEmployeeId;
    private List<Item> items;

    private void calculateSubTotal() {
        BigDecimal sum = items.stream()
                .map(item -> item.calculateLineTotal().toBigDecimal())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.subTotal = AppointmentSubtotal.fromBigDecimal(sum);
    }

    private void calculateTax() {
        if(this.subTotal == null) {
            throw new IllegalStateException("El subtotal es nulo, no se puede calcular los impuestos");
        }
        BigDecimal taxValue = this.subTotal.getSubtotal().multiply(TAX_RATE);
        this.tax = AppointmentTax.fromBigDecimal(taxValue);
    }

    private void calculateTotal() {
        if(this.subTotal == null) {
            throw new IllegalStateException("El subtotal es nulo, no se puede calcular el total");
        }
        if(this.tax == null) {
            throw new IllegalArgumentException("El tax es nulo, no se puede calcular el total");
        }
        this.total = AppointmentTotal.fromBigDecimal(this.subTotal.getSubtotal()
                .add(this.tax.getTax())
                .subtract(this.discount.getDiscount()));
    }

    public void recalculateTotals() {
        calculateSubTotal();
        calculateTax();
        calculateTotal();
    }

    public void changeStatus(AppointmentStatus newStatus) {
        if (this.status == AppointmentStatus.CANCELLED) {
            throw new IllegalStateException("No se puede modificar una orden cancelada");
        }
        this.status = newStatus;
        this.updatedAt = AppointmentUpdatedAt.generate();
    }

    public void changeStatusWhenCreated(){
        this.status = AppointmentStatus.CREATED;
    }


}
