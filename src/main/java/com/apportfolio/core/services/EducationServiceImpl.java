package com.apportfolio.core.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.apportfolio.core.exceptions.DataNotFoundException;
import com.apportfolio.core.models.entities.Education;
import com.apportfolio.core.models.entities.User;
import com.apportfolio.core.repositories.EducationRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {

	private final EducationRepository educationRepository;
	
	@Override
	public List<Education> findByUser(User user) {
		return educationRepository.findByUser(user);
	}

	@Override
	@Transactional
	public List<Education> findAll() {
		List<Education> entities = educationRepository.findAll();
		if (entities == null)
			throw new DataNotFoundException("No hay contenido");
		return entities;
	}

	@Override
	@Transactional
	public Page<Education> findAll(Pageable pageable) {
		Page<Education> entities = educationRepository.findAll(pageable);
		if (!entities.hasContent())
			throw new DataNotFoundException("No hay contenido");
		return entities;
	}

	@Override
	@Transactional
	public Education findById(Long id) {
		return educationRepository.findById(id).orElseThrow(() -> new DataNotFoundException(id));

	}

	@Override
	@Transactional
	public Education save(Education entity) {
		try {
			entity = educationRepository.save(entity);
			log.info("Guardando Educación {}", entity.getId());
			return entity;
		} catch (Exception e) {
			throw new DataNotFoundException(entity.getId());
		}
	}

	@Override
	@Transactional
	public Education update(Long id, Education entity) {
		log.info("Actualizando Educación {}", entity.getId());
		Education entityUpdate = educationRepository.findById(id).orElseThrow(() -> new DataNotFoundException(id));
		entityUpdate = educationRepository.save(entity);
		return entityUpdate;

	}

	@Override
	@Transactional
	public boolean delete(Long id) {
		if (!educationRepository.existsById(id)) {
			throw new DataNotFoundException(id);
		}
		log.info("Borrando Educación {}", id);
		educationRepository.deleteById(id);
		return true;
	}
}
