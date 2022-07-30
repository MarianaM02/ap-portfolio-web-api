package com.apportfolio.core.services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.apportfolio.core.models.entities.Role;
import com.apportfolio.core.models.entities.User;

public interface UserService {
	public Optional<User> findByEmail(String email);
	public Boolean existsByEmail(String email);
	public User addRole(User user, Role role);
	public User addRoles(User user, Collection<Role> roles);
	public User removeRole(User user, Role role);
	Page<User> findAll(Pageable pageable);
	List<User> findAll();
	User findById(Long id);
	User update(Long id, User entity);
	User save(User entity);
	boolean delete(Long id);
}
