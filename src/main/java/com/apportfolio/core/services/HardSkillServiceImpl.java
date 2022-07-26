package com.apportfolio.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apportfolio.core.models.entities.HardSkill;
import com.apportfolio.core.repositories.BaseRepository;
import com.apportfolio.core.repositories.HardSkillRepository;

@Service
public class HardSkillServiceImpl extends BaseServiceImpl<HardSkill, Long> implements HardSkillService {

	@SuppressWarnings("unused")
	@Autowired
	private HardSkillRepository hardSkillRepository;
	
	public HardSkillServiceImpl(BaseRepository<HardSkill, Long> baseRepository) {
		super(baseRepository);
	}

}
