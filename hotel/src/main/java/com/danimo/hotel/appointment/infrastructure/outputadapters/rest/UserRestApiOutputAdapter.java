package com.danimo.hotel.appointment.infrastructure.outputadapters.rest;

import com.danimo.hotel.appointment.application.outputports.rest.ExistEmployeeOutputPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import java.util.UUID;

@Component
public class UserRestApiOutputAdapter implements ExistEmployeeOutputPort {
    private final RestClient restClient;

    public UserRestApiOutputAdapter(@Qualifier("UserRestApi") RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existEmployee(UUID employeeId) {
        try {
            restClient.head()
                    .uri(employeeId.toString())
                    .retrieve()
                    .toBodilessEntity();

            return true;
        } catch (RestClientResponseException e) {
            if (e.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)) {
                return false;
            } else {
                e.printStackTrace();
            }
        }

        return false;
    }
}
