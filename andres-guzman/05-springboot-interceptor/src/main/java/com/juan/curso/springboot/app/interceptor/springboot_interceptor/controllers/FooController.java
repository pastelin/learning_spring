package com.juan.curso.springboot.app.interceptor.springboot_interceptor.controllers;

import java.util.Collections;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class FooController {

	private static final String MESSAGE_KEY = "message";

	@GetMapping("/foo")
	public Map<String, String> foo() {
		return Collections.singletonMap(MESSAGE_KEY, "handler foo del controlador");
	}

	@GetMapping("/bar")
	public Map<String, String> bar() {
		return Collections.singletonMap(MESSAGE_KEY, "handler bar del controlador");
	}

	@GetMapping("/baz")
	public Map<String, String> baz() {
		return Collections.singletonMap(MESSAGE_KEY, "handler baz del controlador");
	}
}
