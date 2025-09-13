package com.danimo.hotel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class HotelApplication {

	@Value("${LOCATION_ROUTE_URI:http://localhost:8004}")
	private String locationRouteUri;

	@Value("${CLIENT_ROUTE_URI:http://localhost:8003}")
	private String clientRouteUri;

	@Value("${USER_ROUTE_URI:http://localhost:8002}")
	private String userRouteUri;

	@Value("${BILLING_ROUTE_URI:http://localhost:8006}")
	private String billingRouteUri;

	@Value("${REPORT_ROUTE_URI:http://localhost:8008}")
	private String reportRouteUri;

	public static void main(String[] args) {
		SpringApplication.run(HotelApplication.class, args);
	}
	@Bean("LocationRestApi")
	public RestClient restLocationClient() {
		return RestClient.builder()
				.baseUrl(locationRouteUri + "/v1/locations/check/")
				.build();
	}

	@Bean("ClientRestApi")
	public RestClient restClient() {
		return RestClient.builder()
				.baseUrl(clientRouteUri + "/v1/clients/check/")
				.build();
	}

	@Bean("UserRestApi")
	public RestClient restUserClient() {
		return RestClient.builder()
				.baseUrl(userRouteUri + "/v1/users/check/")
				.build();
	}

	@Bean("BillRestApi")
	public RestClient restBillClient() {
		return RestClient.builder()
				.baseUrl(billingRouteUri + "/v1/bills")
				.build();
	}

	@Bean("MovementRestApi")
	public RestClient restMovementClient() {
		return RestClient.builder()
				.baseUrl(reportRouteUri + "/v1/reports")
				.build();
	}
}
