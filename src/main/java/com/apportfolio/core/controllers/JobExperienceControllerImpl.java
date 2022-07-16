package com.apportfolio.core.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apportfolio.core.entities.JobExperience;
import com.apportfolio.core.services.JobExperienceServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/job-experience")
public class JobExperienceControllerImpl extends BaseControllerImpl<JobExperience, JobExperienceServiceImpl>{

}
