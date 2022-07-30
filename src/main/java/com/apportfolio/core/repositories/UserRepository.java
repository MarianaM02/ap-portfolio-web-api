package com.apportfolio.core.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.apportfolio.core.models.entities.User;

@Repository
public interface UserRepository extends BaseRepository<User, Long>{
	Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
}
