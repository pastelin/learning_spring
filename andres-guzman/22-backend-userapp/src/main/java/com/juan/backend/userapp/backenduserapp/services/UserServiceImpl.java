package com.juan.backend.userapp.backenduserapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juan.backend.userapp.backenduserapp.models.IUser;
import com.juan.backend.userapp.backenduserapp.models.dto.UserDto;
import com.juan.backend.userapp.backenduserapp.models.dto.mapper.DtoMapperUser;
import com.juan.backend.userapp.backenduserapp.models.entities.Role;
import com.juan.backend.userapp.backenduserapp.models.entities.User;
import com.juan.backend.userapp.backenduserapp.models.request.UserRequest;
import com.juan.backend.userapp.backenduserapp.repositories.RoleRepository;
import com.juan.backend.userapp.backenduserapp.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional(readOnly = true)
	public List<UserDto> findAll() {

		List<User> users = (List<User>) userRepository.findAll();

		return users.stream().map(user -> DtoMapperUser.builder().setUser(user).build()).toList();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<UserDto> findById(Long id) {
		return userRepository.findById(id).map(u -> DtoMapperUser.builder()
				.setUser(u)
				.build());
	}

	@Override
	@Transactional
	public UserDto save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(getRoles(user));
		return DtoMapperUser.builder().setUser(userRepository.save(user)).build();
	}

	@Override
	@Transactional
	public void remove(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	@Transactional
	public Optional<UserDto> update(UserRequest user, Long id) {
		Optional<User> o = userRepository.findById(id);
		User userOptional = null;

		if (o.isPresent()) {
			User userDb = o.orElseThrow();
			userDb.setRoles(getRoles(user));
			userDb.setUsername(user.getUsername());
			userDb.setEmail(user.getEmail());
			userOptional = userRepository.save(userDb);
		}

		return Optional.ofNullable(DtoMapperUser.builder().setUser(userOptional).build());
	}

	private List<Role> getRoles(IUser user) {
		Optional<Role> ou = roleRepository.findByName("ROLE_USER");

		List<Role> roles = new ArrayList<>();

		if (ou.isPresent()) {
			roles.add(ou.orElseThrow());
		}

		if (user.isAdmin()) {
			Optional<Role> oa = roleRepository.findByName("ROLE_ADMIN");
			if (oa.isPresent()) {
				roles.add(oa.orElseThrow());
			}
		}

		return roles;
	}

}
