package com.practice.vvr.customauthentication;

import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
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
	
	private final Map<String, UserDetails> users= new ConcurrentHashMap<>();
	
	public CustomUserDetailsService(Collection<UserDetails> seedUsers) {
		seedUsers.forEach(user -> this.users.put(user.getUsername(), user));
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if(this.users.containsKey(username)) {
			return this.users.get(username);
		}
		
		throw new UsernameNotFoundException("Couldn't find " + username);
	}
	
}

class CustomUserDetails implements UserDetails {
	
	private final Set<GrantedAuthority> authorities = new HashSet<>();
	private final String username, password;
	private final boolean active;
	
	public CustomUserDetails(String username, String password, boolean active, String ... authorities) {
		this.username = username;
		this.password = password;
		this.active = active;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
	
}
