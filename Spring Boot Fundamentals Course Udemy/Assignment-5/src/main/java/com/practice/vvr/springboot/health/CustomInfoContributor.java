package com.practice.vvr.springboot.health;

import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import com.practice.vvr.springboot.Entity.Details;

@Component
public class CustomInfoContributor implements InfoContributor {

	@Override
	public void contribute(Builder builder) {
		
		Details customDetails = new Details("Assignment-5", "Spring Boot Actuator", 5);
		
		builder.withDetail("Project Details", customDetails);

	}

}
