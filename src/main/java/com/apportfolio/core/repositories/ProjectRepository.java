package com.apportfolio.core.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.apportfolio.core.models.entities.Project;
import com.apportfolio.core.models.entities.User;

@Repository
public interface ProjectRepository extends BaseRepository<Project, Long>{
	List<Project> findByUser(User user);
}
