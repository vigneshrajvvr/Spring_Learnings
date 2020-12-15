package com.practice.vvr.customauthentication;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
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

	private final CustomAuthenticationProvider customAuthenticationProvider;
	
	
	CustomSecurityConfiguration(CustomAuthenticationProvider customAuthenticationProvider) {
		this.customAuthenticationProvider = customAuthenticationProvider;
	}
	

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.httpBasic();
		
		http.authorizeRequests().anyRequest().authenticated();
	
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.authenticationProvider(new CustomAuthenticationProvider());
		
	}
	
}

@Component
class CustomAuthenticationProvider implements AuthenticationProvider {
	
	
	private final List<SimpleGrantedAuthority> authorities = Collections.singletonList(
			new SimpleGrantedAuthority("USER")
			);
	
	private boolean isValid(String username, String password) {
		return username.equals("jlong") && password.equals("password");
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		if(isValid(username, password)) {
			return new UsernamePasswordAuthenticationToken(username,password, this.authorities);
		}
		
		throw new BadCredentialsException("Couldn't authenticate using " + username + "!");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
	
}
