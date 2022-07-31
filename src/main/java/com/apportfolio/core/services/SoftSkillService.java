package com.apportfolio.core.services;

import java.util.List;

import com.apportfolio.core.models.entities.SoftSkill;
import com.apportfolio.core.models.entities.User;

public interface SoftSkillService extends BaseService<SoftSkill, Long>{
	List<SoftSkill> findByUser(User user);
}
