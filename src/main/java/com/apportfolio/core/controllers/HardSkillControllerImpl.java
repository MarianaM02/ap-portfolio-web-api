package com.apportfolio.core.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apportfolio.core.entities.HardSkill;
import com.apportfolio.core.services.HardSkillServiceImpl;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(path = "api/hard-skill")
public class HardSkillControllerImpl extends BaseControllerImpl<HardSkill, HardSkillServiceImpl>{

}
