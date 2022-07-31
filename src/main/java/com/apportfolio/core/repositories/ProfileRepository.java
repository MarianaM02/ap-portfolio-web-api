package com.apportfolio.core.repositories;

import org.springframework.stereotype.Repository;

import com.apportfolio.core.models.entities.Profile;
import com.apportfolio.core.models.entities.User;

@Repository
public interface ProfileRepository extends BaseRepository<Profile, Long>{
	Profile findByUser(User user);
}
