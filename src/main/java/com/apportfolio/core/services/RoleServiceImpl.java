package com.apportfolio.core.services;

import org.springframework.stereotype.Service;

import com.apportfolio.core.models.entities.Role;
import com.apportfolio.core.repositories.BaseRepository;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, Long> implements BaseService<Role, Long>{

	public RoleServiceImpl(BaseRepository<Role, Long> baseRepository) {
		super(baseRepository);
	}
}
