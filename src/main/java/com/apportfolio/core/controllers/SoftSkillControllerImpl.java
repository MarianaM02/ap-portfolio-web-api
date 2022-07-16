package com.apportfolio.core.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apportfolio.core.entities.SoftSkill;
import com.apportfolio.core.services.SoftSkillServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/soft-skill")
public class SoftSkillControllerImpl extends BaseControllerImpl<SoftSkill, SoftSkillServiceImpl>{

}
