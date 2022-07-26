package com.apportfolio.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apportfolio.core.models.entities.Education;
import com.apportfolio.core.repositories.BaseRepository;
import com.apportfolio.core.repositories.EducationRepository;

@Service
public class EducationServiceImpl extends BaseServiceImpl<Education, Long> implements EducationService {

	@SuppressWarnings("unused")
	@Autowired
	private EducationRepository educationRepository;
	
	public EducationServiceImpl(BaseRepository<Education, Long> baseRepository) {
		super(baseRepository);
	}

}
