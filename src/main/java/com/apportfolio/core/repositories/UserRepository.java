package com.apportfolio.core.repositories;

import org.springframework.stereotype.Repository;

import com.apportfolio.core.entities.User;

@Repository
public interface UserRepository extends BaseRepository<User, Long>{

}
