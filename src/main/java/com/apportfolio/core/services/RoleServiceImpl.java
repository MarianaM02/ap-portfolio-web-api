package com.apportfolio.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apportfolio.core.models.entities.Role;
import com.apportfolio.core.models.entities.RoleName;
import com.apportfolio.core.repositories.BaseRepository;
import com.apportfolio.core.repositories.RoleRepository;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, Long> implements BaseService<Role, Long>{

	@Autowired
	private RoleRepository roleRepository;
	
	public RoleServiceImpl(BaseRepository<Role, Long> baseRepository) {
		super(baseRepository);
	}
	
	Role findByRole(RoleName name) {
		return roleRepository.findByRole(name);
	}
}
