package com.practice.vvr.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ProjectSecurityConfig extends WebSecurityConfigurerAdapter {

	/*
	 * /myAccount - Secured 
	 * /myBalance - Secured 
	 * /myLoans   - Secured 
	 * /myCards   - Secured 
	 * /notices - Not secured 
	 * /contact - Not secured
	 * 
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		    .authorizeRequests()
				.antMatchers("/myAccount").authenticated()
				.antMatchers("/myBalance").authenticated()
				.antMatchers("/myLoans").authenticated()
				.antMatchers("/myCards").authenticated()
				.antMatchers("/notices").permitAll()
				.antMatchers("/contact").permitAll();
		http.formLogin();
		http.httpBasic();
	}

}
