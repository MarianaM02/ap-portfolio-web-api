package com.apportfolio.core.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileControllerImpl {

	@GetMapping("/")
	public String getPing() {
		return "ping";
	}
	
}
