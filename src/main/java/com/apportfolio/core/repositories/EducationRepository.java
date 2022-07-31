package com.apportfolio.core.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.apportfolio.core.models.entities.Education;
import com.apportfolio.core.models.entities.User;

@Repository
public interface EducationRepository extends BaseRepository<Education, Long>{
	List<Education> findByUser(User user);
}
