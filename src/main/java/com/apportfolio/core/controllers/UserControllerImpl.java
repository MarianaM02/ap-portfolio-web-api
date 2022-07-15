package com.apportfolio.core.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apportfolio.core.entities.User;
import com.apportfolio.core.services.UserServiceImpl;


@RestController
@CrossOrigin(origins="*")
@RequestMapping(path = "api/users")
public class UserControllerImpl extends BaseControllerImpl<User, UserServiceImpl>{

}
