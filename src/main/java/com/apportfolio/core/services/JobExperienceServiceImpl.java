package com.apportfolio.core.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.apportfolio.core.exceptions.DataNotFoundException;
import com.apportfolio.core.models.entities.JobExperience;
import com.apportfolio.core.models.entities.User;
import com.apportfolio.core.repositories.JobExperienceRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class JobExperienceServiceImpl implements JobExperienceService {

	private final JobExperienceRepository jobExperienceRepository;

	@Override
	public List<JobExperience> findByUser(User user) {
		return jobExperienceRepository.findByUser(user);
	}

	@Override
	@Transactional
	public List<JobExperience> findAll() {
		List<JobExperience> entities = jobExperienceRepository.findAll();
		if (entities == null)
			throw new DataNotFoundException("No hay contenido");
		return entities;
	}

	@Override
	@Transactional
	public Page<JobExperience> findAll(Pageable pageable) {
		Page<JobExperience> entities = jobExperienceRepository.findAll(pageable);
		if (!entities.hasContent())
			throw new DataNotFoundException("No hay contenido");
		return entities;
	}

	@Override
	@Transactional
	public JobExperience findById(Long id) {
		return jobExperienceRepository.findById(id).orElseThrow(() -> new DataNotFoundException(id));

	}

	@Override
	@Transactional
	public JobExperience save(JobExperience entity) {
		try {
			entity = jobExperienceRepository.save(entity);
			log.info("Guardando Experiencia laboral {}", entity.getId());
			return entity;
		} catch (Exception e) {
			throw new DataNotFoundException(entity.getId());
		}
	}

	@Override
	@Transactional
	public JobExperience update(Long id, JobExperience entity) {
		log.info("Actualizando Experiencia laboral {}", entity.getId());
		JobExperience entityUpdate = jobExperienceRepository.findById(id).orElseThrow(() -> new DataNotFoundException(id));
		entityUpdate = jobExperienceRepository.save(entity);
		return entityUpdate;

	}

	@Override
	@Transactional
	public boolean delete(Long id) {
		if (!jobExperienceRepository.existsById(id)) {
			throw new DataNotFoundException(id);
		}
		log.info("Borrando Experiencia laboral {}", id);
		jobExperienceRepository.deleteById(id);
		return true;
	}
	
}
