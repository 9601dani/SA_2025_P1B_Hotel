package com.danimo.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class HotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelApplication.class, args);
	}
	@Bean("LocationRestApi")
	public RestClient restLocationClient() {
		return RestClient.builder()
				.baseUrl("http://localhost:8000/v1/locations/check/")
				.build();
	}
	@Bean("ClientRestApi")
	public RestClient restClient() {
		return RestClient.builder()
				.baseUrl("http://localhost:8000/v1/clients/check/")
				.build();
	}
	@Bean("UserRestApi")
	public RestClient restUserClient() {
		return RestClient.builder()
				.baseUrl("http://localhost:8000/v1/users/check/")
				.build();
	}
	@Bean("BillRestApi")
	public RestClient restBillClient() {
		return RestClient.builder()
				.baseUrl("http://localhost:8000/v1/bills")
				.build();
	}
}
