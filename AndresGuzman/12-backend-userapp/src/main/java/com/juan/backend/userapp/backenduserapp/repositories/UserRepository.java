package com.juan.backend.userapp.backenduserapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.juan.backend.userapp.backenduserapp.models.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByUsername(String username);

	@Query("SELECT u FROM User u WHERE u.username = ?1")
	Optional<User> getUserByUsername(String username);

}
