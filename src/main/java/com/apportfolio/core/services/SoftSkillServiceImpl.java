package com.apportfolio.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apportfolio.core.models.entities.SoftSkill;
import com.apportfolio.core.repositories.BaseRepository;
import com.apportfolio.core.repositories.SoftSkillRepository;

@Service
public class SoftSkillServiceImpl extends BaseServiceImpl<SoftSkill, Long> implements SoftSkillService {

	@SuppressWarnings("unused")
	@Autowired
	private SoftSkillRepository softSkillRepository;
	
	public SoftSkillServiceImpl(BaseRepository<SoftSkill, Long> baseRepository) {
		super(baseRepository);
	}

}
