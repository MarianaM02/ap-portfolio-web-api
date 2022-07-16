package com.apportfolio.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apportfolio.core.entities.JobExperience;
import com.apportfolio.core.repositories.BaseRepository;
import com.apportfolio.core.repositories.JobExperienceRepository;

@Service
public class JobExperienceServiceImpl extends BaseServiceImpl<JobExperience, Long> implements JobExperienceService {

	@SuppressWarnings("unused")
	@Autowired
	private JobExperienceRepository jobExperienceRepository;
	
	public JobExperienceServiceImpl(BaseRepository<JobExperience, Long> baseRepository) {
		super(baseRepository);
	}

}
