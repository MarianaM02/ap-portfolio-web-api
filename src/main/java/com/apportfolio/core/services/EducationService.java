package com.apportfolio.core.services;

import java.util.List;

import com.apportfolio.core.models.entities.Education;
import com.apportfolio.core.models.entities.User;

public interface EducationService extends BaseService<Education, Long>{
	List<Education> findByUser(User user);
}
