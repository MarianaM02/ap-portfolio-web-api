package com.apportfolio.core;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.apportfolio.core.models.entities.Role;
import com.apportfolio.core.models.entities.RoleName;
import com.apportfolio.core.models.entities.User;
import com.apportfolio.core.services.RoleServiceImpl;
import com.apportfolio.core.services.UserServiceImpl;

@SpringBootApplication
public class ApPortfolioWebApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApPortfolioWebApiApplication.class, args);
	}

}
