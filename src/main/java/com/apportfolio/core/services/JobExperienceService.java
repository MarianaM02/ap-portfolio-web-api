package com.apportfolio.core.services;

import java.util.List;

import com.apportfolio.core.models.entities.JobExperience;
import com.apportfolio.core.models.entities.User;

public interface JobExperienceService extends BaseService<JobExperience, Long>{
	List<JobExperience> findByUser(User user);
}
