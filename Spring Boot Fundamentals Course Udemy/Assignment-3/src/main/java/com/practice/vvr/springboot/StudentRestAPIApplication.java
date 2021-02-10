package com.practice.vvr.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class}) 
//To disable auto configuration for data source
public class StudentRestAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentRestAPIApplication.class, args);
	}

}
