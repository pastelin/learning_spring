package com.juan.springboot.di.app.springboot_di.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juan.springboot.di.app.springboot_di.models.Product;
import com.juan.springboot.di.app.springboot_di.services.ProductService;

@RestController
@RequestMapping("/api")
public class SomeController {

	private ProductService service;

	// A partir de Spring 4.3, si una clase tiene un único constructor,
	// Spring lo usará para la inyección de dependencias incluso sin la anotación
	// @Autowired
	public SomeController(ProductService service) {
		this.service = service;
	}

	@GetMapping
	public List<Product> list() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Product show(@PathVariable Long id) {
		return service.findById(id);
	}
}
