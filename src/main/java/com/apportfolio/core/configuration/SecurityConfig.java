package com.apportfolio.core.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.apportfolio.core.security.CustomAuthorizationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@SuppressWarnings("deprecation")
@EnableWebSecurity
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final CustomAuthorizationFilter athorizationFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.cors();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers(HttpMethod.GET, "api/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/login/**", "/api/user/token/refresh/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "api/user/**").hasAnyAuthority("ROLE_ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.POST, "api/**").hasAnyAuthority("ROLE_USER");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "api/**").hasAnyAuthority("ROLE_USER");
		// http.authorizeRequests().anyRequest().permitAll();	
		http.addFilterBefore(athorizationFilter, UsernamePasswordAuthenticationFilter.class);		
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	

}