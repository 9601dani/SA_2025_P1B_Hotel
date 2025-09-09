package com.danimo.hotel.appointment.infrastructure.outputadapters.persistence.entity.mapper;

import com.danimo.hotel.appointment.domain.*;
import com.danimo.hotel.appointment.infrastructure.outputadapters.persistence.entity.AppointmentDbEntity;
import com.danimo.hotel.appointment.infrastructure.outputadapters.persistence.entity.AppointmentDiscountEmbeddable;
import com.danimo.hotel.appointment.infrastructure.outputadapters.persistence.entity.ItemDbEntity;
import com.danimo.hotel.rooms.domain.RoomId;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class AppointmentPersistenceMapper {
    public AppointmentDbEntity toDbEntity(Appointment domain) {
        if (domain == null) {
            return null;
        }

        AppointmentDbEntity db = new AppointmentDbEntity(
                domain.getId() != null ? domain.getId().getAppointmentId() : null,
                domain.getDescription(),
                domain.getLocationId(),
                domain.getIdClient(),
                domain.getStatus(),
                domain.getSubTotal() != null ? domain.getSubTotal().getSubtotal() : null,
                toEmbeddable(domain.getDiscount()),
                domain.getTax() != null ? domain.getTax().getTax() : null,
                domain.getTotal() != null ? domain.getTotal().getTotal() : null,
                domain.getCreatedAt() != null ? domain.getCreatedAt().getCreateAt() : null,
                domain.getUpdatedAt() != null ? domain.getUpdatedAt().getUpdatedAt() : null,
                domain.getStartDate() != null ? domain.getStartDate().getStartAt() : null,
                domain.getEndDate() != null ? domain.getEndDate().getEndAt() : null,
                domain.getUserEmployeeId(),
                new ArrayList<>()
        );

        if (domain.getItems() != null && !domain.getItems().isEmpty()) {
            List<ItemDbEntity> itemEntities = domain.getItems().stream()
                    .filter(Objects::nonNull)
                    .map(i -> {
                        ItemDbEntity ie = new ItemDbEntity();
                        ie.setId(i.getId());
                        ie.setRoomId(i.getRoomId() != null ? i.getRoomId().getId() : null);
                        ie.setRoomName(i.getRoomName());
                        ie.setQuantity(i.getQuantity());
                        ie.setUnitPrice(i.getUnitPrice());
                        ie.setLineTotal(i.calculateLineTotal().toBigDecimal());
                        ie.setAppointment(db);
                        return ie;
                    })
                    .toList();

            db.setItems(new ArrayList<>(itemEntities));
        }

        return db;
    }

    public Appointment toDomain(AppointmentDbEntity db) {
        if (db == null) {
            return null;
        }

        AppointmentDiscount discount = toDomainDiscount(db.getDiscount());

        List<Item> domainItems = (db.getItems() == null || db.getItems().isEmpty())
                ? List.of()
                : db.getItems().stream()
                .filter(Objects::nonNull)
                .map(ie -> new Item(
                        ie.getId(),
                        RoomId.fromUuid(ie.getRoomId()),
                        ie.getRoomName(),
                        ie.getQuantity(),
                        ie.getUnitPrice(),
                        ItemLineTotal.fromBigDecimal(ie.getLineTotal())
                ))
                .toList();

        return new Appointment(
                new AppointmentId(db.getId()),
                db.getDescription(),
                db.getLocationId(),
                db.getIdClient(),
                db.getStatus(),
                db.getSubTotal() != null ? AppointmentSubtotal.fromBigDecimal(db.getSubTotal()) : null,
                discount,
                db.getTax() != null ? AppointmentTax.fromBigDecimal(db.getTax()) : null,
                db.getTotal() != null ? AppointmentTotal.fromBigDecimal(db.getTotal()) : null,
                db.getCreatedAt() != null ? AppointmentCreatedAt.fromLocalDateTime(db.getCreatedAt()) : null,
                db.getUpdatedAt() != null ? AppointmentUpdatedAt.fromLocalDateTime(db.getUpdatedAt()) : null,
                db.getStartDate() != null ? AppointmentStartDate.fromLocalDateTime(db.getStartDate()) : null,
                db.getEndDate() != null ? AppointmentEndDate.fromLocalDate(db.getEndDate()) : null,
                db.getUserEmployeeId(),
                domainItems
        );
    }

    private AppointmentDiscountEmbeddable toEmbeddable(AppointmentDiscount discount) {
        if (discount == null) {
            return new AppointmentDiscountEmbeddable(BigDecimal.ZERO, "");
        }
        BigDecimal amount = discount.getDiscount() != null ? discount.getDiscount() : BigDecimal.ZERO;
        String code = discount.getCode() != null ? discount.getCode() : "";
        return new AppointmentDiscountEmbeddable(amount, code);
    }

    private AppointmentDiscount toDomainDiscount(AppointmentDiscountEmbeddable emb) {
        BigDecimal amount = (emb != null && emb.getAmount() != null) ? emb.getAmount() : BigDecimal.ZERO;
        String code = (emb != null && emb.getCode() != null) ? emb.getCode() : "";
        return new AppointmentDiscount(amount, code);
    }
}
