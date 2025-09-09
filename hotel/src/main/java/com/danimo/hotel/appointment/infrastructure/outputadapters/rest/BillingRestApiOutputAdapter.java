package com.danimo.hotel.appointment.infrastructure.outputadapters.rest;

import com.danimo.hotel.appointment.application.outputports.rest.CreatingBillOutputPort;
import com.danimo.hotel.appointment.domain.Appointment;
import com.danimo.hotel.appointment.infrastructure.outputadapters.rest.dto.CreateBillRequestDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

@Component
public class BillingRestApiOutputAdapter implements CreatingBillOutputPort {
    private final RestClient billRestClient;

    public BillingRestApiOutputAdapter(@Qualifier("BillRestApi") RestClient billRestClient) {
        this.billRestClient = billRestClient;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public boolean createBill(Appointment appointment) {
        try {
            CreateBillRequestDto dto = CreateBillRequestDto.fromOrder(appointment);

            var response = billRestClient.post()
                    .uri("")
                    .body(dto)
                    .retrieve()
                    .toBodilessEntity();

            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }
    }
}
