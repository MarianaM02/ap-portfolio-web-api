package com.apportfolio.core.services;

import com.apportfolio.core.models.entities.Profile;
import com.apportfolio.core.models.entities.User;

public interface ProfileService extends BaseService<Profile, Long>{
	Profile findByUser(User user);
}
