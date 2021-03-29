package com.practice.vvr.springboot;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class CsvtodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsvtodbApplication.class, args);
	}

}
