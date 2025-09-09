package com.danimo.hotel.appointment.application.outputports.rest;

import com.danimo.restaurant.order.domain.aggregate.Order;

public interface CreatingBillOutputPort {
    boolean createBill(Order order);
}
