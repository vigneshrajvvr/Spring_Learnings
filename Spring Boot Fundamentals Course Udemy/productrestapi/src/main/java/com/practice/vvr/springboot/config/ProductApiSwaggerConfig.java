package com.practice.vvr.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class ProductApiSwaggerConfig {

	// springfox expects a Docket bean
	@Bean
	public Docket swaggerConfig() {
		return new Docket(DocumentationType.SWAGGER_2)
				   .apiInfo(apiInfo())
				   .select().apis(RequestHandlerSelectors.basePackage("com.practice.vvr.springboot.controller"))
				   .paths(PathSelectors.regex("/product.*")).build();
	}
	// Docket bean requires type of documentation, APIs to be documented. It can be customized.
	
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder()
					.title("Product API")
					.description("Product CRUD operations")
					.termsOfServiceUrl("Open source")
					.version("2.0")
					.build();
	}

}
