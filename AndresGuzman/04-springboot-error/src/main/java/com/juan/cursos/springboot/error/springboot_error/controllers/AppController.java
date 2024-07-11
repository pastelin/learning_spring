package com.juan.cursos.springboot.error.springboot_error.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juan.cursos.springboot.error.springboot_error.exceptions.UserNotFoundException;
import com.juan.cursos.springboot.error.springboot_error.models.domain.User;
import com.juan.cursos.springboot.error.springboot_error.services.UserService;

@RestController
@RequestMapping("/app")
public class AppController {

	private UserService service;

	public AppController(UserService service) {
		this.service = service;
	}

	@GetMapping
	public String index() {
		// int a = 1/0;
		int value = Integer.parseInt("10x");
		return "ok 200";
	}

	@GetMapping("/show/{id}")
	public User show(@PathVariable Long id) {
		return service.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
	}
}
