package com.practice.vrr.jdbcAuthentication;

import java.security.Principal;

import javax.sql.DataSource;

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
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@SpringBootApplication
//public class JdbcAuthenticationApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(JdbcAuthenticationApplication.class, args);
//	}
//
//}

@SpringBootApplication
public class JdbcAuthenticationApplication {
	
    // Jdbc authentication
	@Bean
	UserDetailsManager memory(DataSource ds) {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
		
		jdbcUserDetailsManager.setDataSource(ds);
		
		return jdbcUserDetailsManager;
	}
	
	// Creation of users.
	// For bean implemented InitializingBean, it will run afterPropertiesSet() after all bean properties have been set.
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
		SpringApplication.run(JdbcAuthenticationApplication.class, args);
	}

}

@RestController
class GreentingsRestController {
	
	//A user or application that can authenticate itself is known as a principal. A principal has a name that uniquely identifies it.
	@GetMapping("/greeting")
	String greeting (Principal principal) {
		return "hello, " + principal.getName() + "! " + principal.toString();
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

