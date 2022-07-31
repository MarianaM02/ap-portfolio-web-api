package com.apportfolio.core.configuration;

import java.util.HashSet;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.apportfolio.core.models.entities.Role;
import com.apportfolio.core.models.entities.RoleName;
import com.apportfolio.core.models.entities.User;
import com.apportfolio.core.services.RoleServiceImpl;
import com.apportfolio.core.services.UserServiceImpl;

@SuppressWarnings("deprecation")
@Configuration
public class MyConfig {

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	CommandLineRunner run(RoleServiceImpl roleService, UserServiceImpl userService) {
		return args -> {
			roleService.save(RoleName.ROLE_ADMIN);
			roleService.save(RoleName.ROLE_USER);

			userService.makeAdmin(
					userService.save(new User("mrn.m.92@gmail.com", "12345678", new HashSet<Role>())).getId());
			userService.makeAdmin(
					userService.save(new User("namira.r.m2@gmail.com", "12345678", new HashSet<Role>())).getId());
			userService.save(new User("marysm92@hotmail.com", "12345678", new HashSet<Role>()));
			userService.save(new User("mrn.m.93@gmail.com", "12345678", new HashSet<Role>()));
			

		};
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**");
			}
		};
	}

}
