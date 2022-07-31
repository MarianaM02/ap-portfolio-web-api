package com.apportfolio.core.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.apportfolio.core.models.entities.JobExperience;
import com.apportfolio.core.models.entities.User;

@Repository
public interface JobExperienceRepository extends BaseRepository<JobExperience, Long>{
	List<JobExperience> findByUser(User user);
}
