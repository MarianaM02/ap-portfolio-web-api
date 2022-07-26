package com.apportfolio.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apportfolio.core.models.entities.Profile;
import com.apportfolio.core.repositories.BaseRepository;
import com.apportfolio.core.repositories.ProfileRepository;

@Service
public class ProfileServiceImpl extends BaseServiceImpl<Profile, Long> implements ProfileService{

	@SuppressWarnings("unused")
	@Autowired
	private ProfileRepository profileRepository;
	
	public ProfileServiceImpl(BaseRepository<Profile, Long> baseRepository) {
		super(baseRepository);
	}

}
