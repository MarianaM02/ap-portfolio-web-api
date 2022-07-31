package com.apportfolio.core.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.apportfolio.core.models.entities.SoftSkill;
import com.apportfolio.core.models.entities.User;

@Repository
public interface SoftSkillRepository extends BaseRepository<SoftSkill, Long>{
	List<SoftSkill> findByUser(User user);
}
