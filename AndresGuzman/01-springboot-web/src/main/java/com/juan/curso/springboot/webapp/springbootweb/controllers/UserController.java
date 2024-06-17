package com.juan.curso.springboot.webapp.springbootweb.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.juan.curso.springboot.webapp.springbootweb.models.User;

@Controller
public class UserController {

	@GetMapping("/details")
	public String details(Model model) {
		User user = new User("Juan", "Pastelin");
		model.addAttribute("title", "Hola Mundo Spring Boot");
		model.addAttribute("user", user);

		return "details";
	}

	@GetMapping("/list")
	public String list(ModelMap model) {
		model.addAttribute("title", "Listado de usuarios");
		return "list";
	}

	@ModelAttribute("users")
	public List<User> getUsers() {
		return List.of(new User("Juan", "Pastelin", "pastelin@gmail.com"), new User("Pepe", "Doe"),
				new User("Jhon", "Doe"));
	}
}
