package com.practice.vvr.inmemory;

import java.security.Principal;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class InmemoryApplication {
	
	// Provides bean for type of authentication which has to be used
	// In memory authentication
	@Bean
	UserDetailsManager inMemoryAuthentication() {
		return new InMemoryUserDetailsManager();
	}
	
	// Creation of users
	@Bean
	InitializingBean initializer(UserDetailsManager manager) {
		return () -> {
			
			UserDetails josh = User.withDefaultPasswordEncoder().username("jlong").password("password").roles("USER").build();
			manager.createUser(josh);
			
			UserDetails rob = User.withUserDetails(josh).username("rob").build();
			manager.createUser(rob);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(InmemoryApplication.class, args);
	}

}

@RestController
class GreentingsRestController {
	
	//A user or application that can authenticate itself is known as a principal. A principal has a name that uniquely identifies it.
	@GetMapping("/greeting")
	String greeting (Principal principal) {
		return "hello, " + principal.getName() + "!";
	}
	
}

@EnableWebSecurity
@Configuration
class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	// http authentication and authorization configuration
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.httpBasic();
		http
			.authorizeRequests().anyRequest().authenticated();
		
	}
	
}
