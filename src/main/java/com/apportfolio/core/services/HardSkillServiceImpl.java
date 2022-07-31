package com.apportfolio.core.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.apportfolio.core.exceptions.DataNotFoundException;
import com.apportfolio.core.models.entities.HardSkill;
import com.apportfolio.core.models.entities.User;
import com.apportfolio.core.repositories.HardSkillRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class HardSkillServiceImpl implements HardSkillService {

	private final HardSkillRepository hardSkillRepository;

	@Override
	public List<HardSkill> findByUser(User user) {
		return hardSkillRepository.findByUser(user);
	}
	
	@Override
	@Transactional
	public List<HardSkill> findAll() {
		List<HardSkill> entities = hardSkillRepository.findAll();
		if (entities == null)
			throw new DataNotFoundException("No hay contenido");
		return entities;
	}

	@Override
	@Transactional
	public Page<HardSkill> findAll(Pageable pageable) {
		Page<HardSkill> entities = hardSkillRepository.findAll(pageable);
		if (!entities.hasContent())
			throw new DataNotFoundException("No hay contenido");
		return entities;
	}

	@Override
	@Transactional
	public HardSkill findById(Long id) {
		return hardSkillRepository.findById(id).orElseThrow(() -> new DataNotFoundException(id));

	}

	@Override
	@Transactional
	public HardSkill save(HardSkill entity) {
		try {
			entity = hardSkillRepository.save(entity);
			log.info("Guardando Hard Skill {}", entity.getId());
			return entity;
		} catch (Exception e) {
			throw new DataNotFoundException(entity.getId());
		}
	}

	@Override
	@Transactional
	public HardSkill update(Long id, HardSkill entity) {
		log.info("Actualizando Hard Skill {}", entity.getId());
		HardSkill entityUpdate = hardSkillRepository.findById(id).orElseThrow(() -> new DataNotFoundException(id));
		entityUpdate = hardSkillRepository.save(entity);
		return entityUpdate;

	}

	@Override
	@Transactional
	public boolean delete(Long id) {
		if (!hardSkillRepository.existsById(id)) {
			throw new DataNotFoundException(id);
		}
		log.info("Borrando Hard Skill {}", id);
		hardSkillRepository.deleteById(id);
		return true;
	}

}
