package com.apportfolio.core.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.apportfolio.core.exceptions.DataNotFoundException;
import com.apportfolio.core.models.entities.Profile;
import com.apportfolio.core.models.entities.User;
import com.apportfolio.core.repositories.ProfileRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

	private final ProfileRepository profileRepository;

	@Override
	public Profile findByUser(User id) {
		return profileRepository.findByUser(id);
	}

	@Override
	@Transactional
	public List<Profile> findAll() {
		List<Profile> entities = profileRepository.findAll();
		if (entities == null)
			throw new DataNotFoundException("No hay contenido");
		return entities;
	}

	@Override
	@Transactional
	public Page<Profile> findAll(Pageable pageable) {
		Page<Profile> entities = profileRepository.findAll(pageable);
		if (!entities.hasContent())
			throw new DataNotFoundException("No hay contenido");
		return entities;
	}

	@Override
	@Transactional
	public Profile findById(Long id) {
		return profileRepository.findById(id).orElseThrow(() -> new DataNotFoundException(id));

	}

	@Override
	@Transactional
	public Profile save(Profile entity) {
		try {
			entity = profileRepository.save(entity);
			log.info("Guardando Perfil {}", entity.getId());
			return entity;
		} catch (Exception e) {
			throw new DataNotFoundException(entity.getId());
		}
	}

	@Override
	@Transactional
	public Profile update(Long id, Profile entity) {
		log.info("Actualizando Perfil {}", entity.getId());
		Profile entityUpdate = profileRepository.findById(id).orElseThrow(() -> new DataNotFoundException(id));
		entityUpdate = profileRepository.save(entity);
		return entityUpdate;

	}

	@Override
	@Transactional
	public boolean delete(Long id) {
		if (!profileRepository.existsById(id)) {
			throw new DataNotFoundException(id);
		}
		log.info("Borrando Perfil {}", id);
		profileRepository.deleteById(id);
		return true;
	}

}
