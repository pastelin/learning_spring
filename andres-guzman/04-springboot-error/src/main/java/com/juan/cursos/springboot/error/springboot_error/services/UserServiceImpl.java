package com.juan.cursos.springboot.error.springboot_error.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.juan.cursos.springboot.error.springboot_error.models.domain.User;

@Service
public class UserServiceImpl implements UserService {

	private List<User> users;

	public UserServiceImpl(List<User> users) {
		this.users = users;
	}

	@Override
	public List<User> findAll() {
		return users;
	}

	@Override
	public Optional<User> findById(Long id) {
		return users.stream().filter(user -> user.getId().equals(id)).findFirst();
	}

}
