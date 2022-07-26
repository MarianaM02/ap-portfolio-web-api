package com.apportfolio.core.services;

import java.io.Serializable;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.apportfolio.core.exceptions.DataNotFoundException;
import com.apportfolio.core.models.entities.Base;
import com.apportfolio.core.repositories.BaseRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BaseServiceImpl<E extends Base, ID extends Serializable> implements BaseService<E, ID> {
	protected BaseRepository<E, ID> baseRepository;

	public BaseServiceImpl(BaseRepository<E, ID> baseRepository) {
		this.baseRepository = baseRepository;
	}

	@Override
	@Transactional
	public List<E> findAll() {
		List<E> entities = baseRepository.findAll();
		if (!entities.isEmpty())
			throw new DataNotFoundException("No hay contenido");
		return entities;
	}

	@Override
	@Transactional
	public Page<E> findAll(Pageable pageable) {
		Page<E> entities = baseRepository.findAll(pageable);
		if (!entities.hasContent())
			throw new DataNotFoundException("No hay contenido");
		return entities;
	}

	@Override
	@Transactional
	public E findById(ID id) {
		return baseRepository.findById(id).orElseThrow(() -> new DataNotFoundException(id));

	}

	@Override
	@Transactional
	public E save(E entity) {
		try {
			log.info("Guardando {} {}", Base.class.getName(), entity.getId());
			entity = baseRepository.save(entity);
			return entity;
		} catch (Exception e) {
			throw new DataNotFoundException(entity.getId());
		}
	}

	@Override
	@Transactional
	public E update(ID id, E entity) {
		log.info("Actualizando {} {}", Base.class.getName(), entity.getId());
		E entityUpdate = baseRepository.findById(id).orElseThrow(() -> new DataNotFoundException(id));
		entityUpdate = baseRepository.save(entity);
		return entityUpdate;

	}

	@Override
	@Transactional
	public boolean delete(ID id) {
			if (!baseRepository.existsById(id)) {
				throw new DataNotFoundException(id);
			}
			log.info("Borrando {} {}", Base.class.getName(), id);
			baseRepository.deleteById(id);
			return true;
	}
}
