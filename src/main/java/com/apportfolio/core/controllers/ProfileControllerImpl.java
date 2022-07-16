package com.apportfolio.core.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apportfolio.core.entities.Profile;
import com.apportfolio.core.services.ProfileServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/profile")
public class ProfileControllerImpl extends BaseControllerImpl<Profile, ProfileServiceImpl>{
	
}
