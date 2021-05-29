package com.practice.vvr.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ProjectSecurityConfig extends WebSecurityConfigurerAdapter {

	/*
	 * /myAccount - Secured /myBalance - Secured /myLoans - Secured /myCards -
	 * Secured /notices - Not secured /contact - Not secured
	 * 
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/*
		 * Default configuration
		 * 
		 * http .authorizeRequests() .anyRequest().authenticated(); http.formLogin();
		 * http.formBasic();
		 */

		/*
		 * Custom configuration as per our requirement
		 * 
		 * 
		 * http .authorizeRequests() .antMatchers("/myAccount").authenticated()
		 * .antMatchers("/myBalance").authenticated()
		 * .antMatchers("/myLoans").authenticated()
		 * .antMatchers("/myCards").authenticated() .antMatchers("/notices").permitAll()
		 * .antMatchers("/contact").permitAll(); http.formLogin(); http.httpBasic();
		 */
		
		/*
		 * formLogin() - Request from browser login form httpBasic() - Request from
		 * other API
		 */

		/*
		 * Configuration to deny all the requests
		 *
		 * http
		 * 	 .authorizeRequests().anyRequest().denyAll();
		 * http.formLogin();
		 * http.httpBasic();
		 */
		
		/*
		 * Configuration to permit all the requests
		 */
		  http
		  	 .authorizeRequests().anyRequest().permitAll();
		  http.formLogin();
		  http.httpBasic();
		 
	}

}
