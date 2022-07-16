package com.apportfolio.core.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apportfolio.core.entities.Project;
import com.apportfolio.core.services.ProjectServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/project")
public class ProjectControllerImpl extends BaseControllerImpl<Project, ProjectServiceImpl>{

}
