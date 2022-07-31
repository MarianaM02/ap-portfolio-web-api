package com.apportfolio.core.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.apportfolio.core.exceptions.DataNotFoundException;
import com.apportfolio.core.models.entities.Project;
import com.apportfolio.core.models.entities.User;
import com.apportfolio.core.repositories.ProjectRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

	private final ProjectRepository projectRepository;
	
	@Override
	public List<Project> findByUser(User user) {
		return projectRepository.findByUser(user);
	}

	@Override
	@Transactional
	public List<Project> findAll() {
		List<Project> entities = projectRepository.findAll();
		if (entities == null)
			throw new DataNotFoundException("No hay contenido");
		return entities;
	}

	@Override
	@Transactional
	public Page<Project> findAll(Pageable pageable) {
		Page<Project> entities = projectRepository.findAll(pageable);
		if (!entities.hasContent())
			throw new DataNotFoundException("No hay contenido");
		return entities;
	}

	@Override
	@Transactional
	public Project findById(Long id) {
		return projectRepository.findById(id).orElseThrow(() -> new DataNotFoundException(id));

	}

	@Override
	@Transactional
	public Project save(Project entity) {
		try {
			entity = projectRepository.save(entity);
			log.info("Guardando {} {}", entity.getId());
			return entity;
		} catch (Exception e) {
			throw new DataNotFoundException(entity.getId());
		}
	}

	@Override
	@Transactional
	public Project update(Long id, Project entity) {
		log.info("Actualizando Proyecto {}", entity.getId());
		Project entityUpdate = projectRepository.findById(id).orElseThrow(() -> new DataNotFoundException(id));
		entityUpdate = projectRepository.save(entity);
		return entityUpdate;

	}

	@Override
	@Transactional
	public boolean delete(Long id) {
		if (!projectRepository.existsById(id)) {
			throw new DataNotFoundException(id);
		}
		log.info("Borrando Proyecto {}", id);
		projectRepository.deleteById(id);
		return true;
	}
	
}
