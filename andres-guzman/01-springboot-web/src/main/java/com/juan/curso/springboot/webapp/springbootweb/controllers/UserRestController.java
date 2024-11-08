package com.juan.curso.springboot.webapp.springbootweb.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juan.curso.springboot.webapp.springbootweb.models.User;
import com.juan.curso.springboot.webapp.springbootweb.models.dto.UserDto;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class UserRestController {

	@GetMapping("/details")
	public UserDto details(Model model) {
		UserDto userDto = new UserDto();
		User user = new User("Juan", "Pastelin");

		userDto.setUser(user);
		userDto.setTitle("Hola Mundo Spring Boot");

		return userDto;
	}

	@GetMapping("/list")
	public List<User> list() {

		User user = new User("Juan", "Pastelin");
		User user2 = new User("Pepe", "Doe");
		User user3 = new User("Jhon", "Doe");

		return Arrays.asList(user, user2, user3);
	}

	@GetMapping("/details-map")
	public String detailsMap(Model model) {
		User user = new User("Juan", "Pastelin");
		model.addAttribute("title", "Hola Mundo Spring Boot");
		model.addAttribute("user", user);

		return "details";
	}

}
