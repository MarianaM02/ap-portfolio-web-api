package com.apportfolio.core.repositories;

import org.springframework.stereotype.Repository;

import com.apportfolio.core.models.entities.Role;
import com.apportfolio.core.models.entities.RoleName;

@Repository
public interface RoleRepository extends BaseRepository<Role, Long>{
	Role findByRole(RoleName role);
	boolean existsByRole(RoleName role);
	Role save(RoleName roleName);
}
