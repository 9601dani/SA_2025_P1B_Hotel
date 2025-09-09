package com.danimo.hotel.appointment.infrastructure.outputadapters.rest;

import com.danimo.hotel.appointment.application.outputports.rest.ExistClientOutputPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

@Component
public class ClientRestApiOutputAdapter implements ExistClientOutputPort {
    private final RestClient clientRestClient;

    public ClientRestApiOutputAdapter(@Qualifier("ClientRestApi") RestClient clientRestClient) {
        this.clientRestClient = clientRestClient;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existClient(String id) {
        String normalized = id.replace("-", "").trim().toUpperCase();
        try {
            clientRestClient.head()
                    .uri(normalized)
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
