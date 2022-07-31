package com.apportfolio.core.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.apportfolio.core.exceptions.DataNotFoundException;
import com.apportfolio.core.models.entities.Role;
import com.apportfolio.core.models.entities.RoleName;
import com.apportfolio.core.repositories.RoleRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleServiceImpl implements BaseService<Role, Long>{

	private final RoleRepository roleRepository;
	
	Role findByRole(RoleName name) {
		return roleRepository.findByRole(name);
	}
	
	@Transactional
    public Role save(RoleName roleName) {
		Role role = roleRepository.findByRole(roleName);
    	if (role == null) {
    		return roleRepository.save(new Role(roleName));			
		}
    	return role;
    }
	
	@Override
	@Transactional
	public List<Role> findAll() {
		List<Role> entities = roleRepository.findAll();
		if (entities == null)
			throw new DataNotFoundException("No hay contenido");
		return entities;
	}

	@Override
	@Transactional
	public Page<Role> findAll(Pageable pageable) {
		Page<Role> entities = roleRepository.findAll(pageable);
		if (!entities.hasContent())
			throw new DataNotFoundException("No hay contenido");
		return entities;
	}

	@Override
	@Transactional
	public Role findById(Long id) {
		return roleRepository.findById(id).orElseThrow(() -> new DataNotFoundException(id));

	}

	@Override
	@Transactional
	public Role save(Role entity) {
		try {
			entity = roleRepository.save(entity);
			log.info("Guardando {} {}", entity.getId());
			return entity;
		} catch (Exception e) {
			throw new DataNotFoundException(entity.getId());
		}
	}

	@Override
	@Transactional
	public Role update(Long id, Role entity) {
		log.info("Actualizando Rol {}", entity.getId());
		Role entityUpdate = roleRepository.findById(id).orElseThrow(() -> new DataNotFoundException(id));
		entityUpdate = roleRepository.save(entity);
		return entityUpdate;

	}

	@Override
	@Transactional
	public boolean delete(Long id) {
		if (!roleRepository.existsById(id)) {
			throw new DataNotFoundException(id);
		}
		log.info("Borrando Rol {}", id);
		roleRepository.deleteById(id);
		return true;
	}
}
