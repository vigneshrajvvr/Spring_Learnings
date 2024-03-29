package com.practice.vvr.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CurrencyExchangeService1Application {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangeService1Application.class, args);
	}

}
