package com.practice.vvr.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class}) 
//To disable auto configuration for data source
@EnableCaching
@EnableSwagger2            
// To enable usage of swagger 
// Path - localhost:port/contextpath/v2/api-docs - localhost:8081/productsapi/v2/api-docs
// Swagger ui path - localhost:8081/productsapi/swagger-ui.html
public class ProductrestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductrestapiApplication.class, args);
	}

}
