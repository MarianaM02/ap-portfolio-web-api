package com.apportfolio.core.services;

import java.util.List;

import com.apportfolio.core.models.entities.Project;
import com.apportfolio.core.models.entities.User;

public interface ProjectService extends BaseService<Project, Long>{
	List<Project> findByUser(User user);
}
