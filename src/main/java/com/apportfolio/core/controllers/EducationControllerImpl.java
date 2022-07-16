package com.apportfolio.core.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apportfolio.core.entities.Education;
import com.apportfolio.core.services.EducationServiceImpl;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(path = "api/education")
public class EducationControllerImpl extends BaseControllerImpl<Education, EducationServiceImpl>{

}
