package com.apportfolio.core.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.apportfolio.core.models.entities.HardSkill;
import com.apportfolio.core.models.entities.User;

@Repository
public interface HardSkillRepository extends BaseRepository<HardSkill, Long>{
	List<HardSkill> findByUser(User user);
}
