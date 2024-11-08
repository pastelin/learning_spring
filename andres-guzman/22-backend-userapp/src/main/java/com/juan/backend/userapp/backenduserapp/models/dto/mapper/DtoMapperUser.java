package com.juan.backend.userapp.backenduserapp.models.dto.mapper;

import com.juan.backend.userapp.backenduserapp.models.dto.UserDto;
import com.juan.backend.userapp.backenduserapp.models.entities.User;

public class DtoMapperUser {

	private User user;

	private DtoMapperUser() {
	}

	public static DtoMapperUser builder() {
		return new DtoMapperUser();
	}

	public DtoMapperUser setUser(User user) {
		this.user = user;
		return this;
	}

	public UserDto build() {
		if (user == null) {
			throw new RuntimeException("Debe pasar el entity User!");
		}

		boolean isAdmin = user.getRoles().stream().anyMatch(r -> "ROLE_ADMIN".equals(r.getName()));

		return new UserDto(user.getId(), user.getUsername(), user.getEmail(), isAdmin);
	}

}
