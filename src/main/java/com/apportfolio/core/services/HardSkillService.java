package com.apportfolio.core.services;

import java.util.List;

import com.apportfolio.core.models.entities.HardSkill;
import com.apportfolio.core.models.entities.User;

public interface HardSkillService extends BaseService<HardSkill, Long>{
	List<HardSkill> findByUser(User user);
}
