package com.apportfolio.core.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.apportfolio.core.exceptions.DataNotFoundException;
import com.apportfolio.core.models.entities.SoftSkill;
import com.apportfolio.core.models.entities.User;
import com.apportfolio.core.repositories.SoftSkillRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class SoftSkillServiceImpl implements SoftSkillService {

	private final SoftSkillRepository softSkillRepository;
	
	@Override
	public List<SoftSkill> findByUser(User user) {
		return softSkillRepository.findByUser(user);
	}
	
	@Override
	@Transactional
	public List<SoftSkill> findAll() {
		List<SoftSkill> entities = softSkillRepository.findAll();
		if (entities == null)
			throw new DataNotFoundException("No hay contenido");
		return entities;
	}

	@Override
	@Transactional
	public Page<SoftSkill> findAll(Pageable pageable) {
		Page<SoftSkill> entities = softSkillRepository.findAll(pageable);
		if (!entities.hasContent())
			throw new DataNotFoundException("No hay contenido");
		return entities;
	}

	@Override
	@Transactional
	public SoftSkill findById(Long id) {
		return softSkillRepository.findById(id).orElseThrow(() -> new DataNotFoundException(id));

	}

	@Override
	@Transactional
	public SoftSkill save(SoftSkill entity) {
		try {
			entity = softSkillRepository.save(entity);
			log.info("Guardando Sofk Skill {}", entity.getId());
			return entity;
		} catch (Exception e) {
			throw new DataNotFoundException(entity.getId());
		}
	}

	@Override
	@Transactional
	public SoftSkill update(Long id, SoftSkill entity) {
		log.info("Actualizando Sofk Skill {}", entity.getId());
		SoftSkill entityUpdate = softSkillRepository.findById(id).orElseThrow(() -> new DataNotFoundException(id));
		entityUpdate = softSkillRepository.save(entity);
		return entityUpdate;

	}

	@Override
	@Transactional
	public boolean delete(Long id) {
		if (!softSkillRepository.existsById(id)) {
			throw new DataNotFoundException(id);
		}
		log.info("Borrando Sofk Skill {}", id);
		softSkillRepository.deleteById(id);
		return true;
	}

}
