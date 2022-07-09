package com.apportfolio.core.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.apportfolio.core.entities.User;
import com.apportfolio.core.repositories.BaseRepository;
import com.apportfolio.core.repositories.UserRepository;

public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	public UserServiceImpl(BaseRepository<User, Long> baseRepository) {
		super(baseRepository);
	}

}
