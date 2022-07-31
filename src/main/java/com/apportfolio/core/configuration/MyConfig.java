package com.apportfolio.core.configuration;

import java.util.HashSet;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
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
	@Value("${admin.username}")
	String email;
	@Value("${admin.password}")
	String pass;

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
					userService.save(new User(email, pass, new HashSet<Role>())).getId());

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
