package com.apportfolio.core.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.apportfolio.core.exceptions.DataNotFoundException;
import com.apportfolio.core.models.entities.Base;
import com.apportfolio.core.models.entities.Role;
import com.apportfolio.core.models.entities.RoleName;
import com.apportfolio.core.models.entities.User;
import com.apportfolio.core.repositories.RoleRepository;
import com.apportfolio.core.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("Buscando usuario: {}", username);
		User user = userRepository.findByEmail(username).orElseThrow(() -> new DataNotFoundException());
		log.info("Usuario encontrado en la dase de datos: {}", username);
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(r -> {
			authorities.add(new SimpleGrantedAuthority(r.getRole().toString()));
		});
		return new org.springframework.security.core.userdetails.User(
				user.getEmail(),
				user.getPass(),
				authorities
				);
	}
	
	@Override
	@Transactional
	public List<User> findAll() {
		List<User> entities = userRepository.findAll();
		if (entities == null)
			throw new DataNotFoundException("No hay contenido");
		return entities;
	}

	@Override
	@Transactional
	public Page<User> findAll(Pageable pageable) {
		Page<User> entities = userRepository.findAll(pageable);
		if (!entities.hasContent())
			throw new DataNotFoundException("No hay contenido");
		return entities;
	}

	@Override
	@Transactional
	public User findById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new DataNotFoundException(id));

	}

	@Override
	@Transactional
	public User update(Long id, User entity) {
		log.info("Actualizando {} {}", Base.class.getName(), entity.getId());
		User entityUpdate = userRepository.findById(id).orElseThrow(() -> new DataNotFoundException(id));
		entityUpdate = userRepository.save(entity);
		return entityUpdate;

	}

	@Override
	@Transactional
	public boolean delete(Long id) {
		if (!userRepository.existsById(id)) {
			throw new DataNotFoundException(id);
		}
		log.info("Borrando {} {}", Base.class.getName(), id);
		userRepository.deleteById(id);
		return true;
	}
	
	@Override
	@Transactional
	public User save(User entity) {
		log.info("Guardando usuario: {}", entity.getEmail());
		entity.setPass(passwordEncoder.encode(entity.getPass()));
		if(entity.getRoles().isEmpty()) {
			entity.getRoles().add(roleRepository.findByRole(RoleName.ROLE_USER));
		}
		return userRepository.save(entity);
	}
	
	
    /**
     * Finds a user in the database by email
     */
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Check is the user exists given the email: naturalId
     */
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    
    @Transactional
    public User addRole(User user, Role role) {
    	log.info("Agregando rol {} a usuario {}", role.getRole(), user.getEmail());
    	User userToUpdate = userRepository.findByEmail(user.getEmail()).orElseThrow();
    	Role roleToAdd = roleRepository.findById(role.getId()).orElseThrow();
    	userToUpdate.getRoles().add(roleToAdd);
		return user;
    }
    
    @Transactional
    public User makeAdmin(Long id) {
    	User user = findById(id);
    	Role role = roleRepository.findByRole(RoleName.ROLE_ADMIN);
    	log.info("Agregando rol admin a usuario {}", user.getEmail());
    	user.getRoles().add(role);
    	return user;
    }
    
    @Transactional
    public User addRoles(User user, Collection<Role> roles) {
    	roles.forEach(role -> this.addRole(user, role));
		return user;
    }
    
    
    
    
    
    @Transactional
    public User removeRole(User user, Role role) {
    	user.getRoles().remove(role);
        role.getUserList().remove(user);
        return user;
    }


}
