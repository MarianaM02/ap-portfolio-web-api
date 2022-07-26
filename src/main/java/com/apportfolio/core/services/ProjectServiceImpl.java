package com.apportfolio.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apportfolio.core.models.entities.Project;
import com.apportfolio.core.repositories.BaseRepository;
import com.apportfolio.core.repositories.ProjectRepository;

@Service
public class ProjectServiceImpl extends BaseServiceImpl<Project, Long> implements ProjectService {

	@SuppressWarnings("unused")
	@Autowired
	private ProjectRepository projectRepository;
	
	public ProjectServiceImpl(BaseRepository<Project, Long> baseRepository) {
		super(baseRepository);
	}

}
