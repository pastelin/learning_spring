package com.juan.curso.springboot.app.aop.springboot_aop.controller;

import org.springframework.web.bind.annotation.RestController;

import com.juan.curso.springboot.app.aop.springboot_aop.services.GreetingService;

import java.util.Collections;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class GreetingController {

	private GreetingService greetingService;

	public GreetingController(GreetingService greetingService) {
		this.greetingService = greetingService;
	}

	@GetMapping("/greeting")
	public ResponseEntity<?> greeting() {
		return ResponseEntity
				.ok(Collections.singletonMap("greeting", greetingService.sayHello("Pepe", "Hola que tal")));
	}

	@GetMapping("/greeting-error")
	public ResponseEntity<?> greetingError() {
		return ResponseEntity
				.ok(Collections.singletonMap("greeting", greetingService.sayHelloError("Pepe", "Hola que tal")));
	}

}
