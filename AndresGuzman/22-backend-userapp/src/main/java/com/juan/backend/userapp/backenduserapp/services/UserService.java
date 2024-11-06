package com.juan.backend.userapp.backenduserapp.services;

import java.util.List;
import java.util.Optional;

import com.juan.backend.userapp.backenduserapp.models.dto.UserDto;
import com.juan.backend.userapp.backenduserapp.models.entities.User;
import com.juan.backend.userapp.backenduserapp.models.request.UserRequest;

public interface UserService {

	List<UserDto> findAll();

	Optional<UserDto> findById(Long id);

	UserDto save(User user);

	Optional<UserDto> update(UserRequest user, Long id);

	void remove(Long id);

}
