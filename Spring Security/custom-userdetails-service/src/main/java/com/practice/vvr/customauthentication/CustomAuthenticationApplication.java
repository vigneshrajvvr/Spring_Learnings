package com.practice.vvr.customauthentication;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class CustomAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomAuthenticationApplication.class, args);
	}

}

@RestController
class GreetingRestController {
	
	@GetMapping("/greeting")
	String greet(Principal p) {
		return "Greetings " + p.getName() + "!";
	}
	
}

@Configuration
@EnableWebSecurity
class CustomSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.httpBasic();
		
		http.authorizeRequests().anyRequest().authenticated();
	
	}
	
}

@Service
class CustomUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
	}
	
}
