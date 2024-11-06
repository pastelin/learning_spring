package com.juan.backend.userapp.backenduserapp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.juan.backend.userapp.backenduserapp.models.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

	Optional<Role> findByName(String name);

}
