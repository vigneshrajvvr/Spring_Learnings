package com.practice.vvr.springsecurityldap;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	//ldap configuration using authentication manager builder
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.ldapAuthentication()
				.userDnPatterns("uid={0},ou=people")    // format of dn in ldif file
				.groupSearchBase("ou=groups")
				.contextSource()
				.url("ldap://localhost:8389/dc=springframework,dc=org")
		        .and()
		        .passwordCompare()
		        .passwordEncoder(new BCryptPasswordEncoder())
		        .passwordAttribute("userPassword"); 
			
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
			.anyRequest().fullyAuthenticated()
			.and()
			.formLogin();
		
	}
	
	
	
}
