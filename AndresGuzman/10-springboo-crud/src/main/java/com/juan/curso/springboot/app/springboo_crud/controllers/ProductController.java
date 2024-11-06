package com.juan.curso.springboot.app.springboo_crud.controllers;

import java.util.List;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juan.curso.springboot.app.springboo_crud.ProductValidation;
import com.juan.curso.springboot.app.springboo_crud.entities.Product;
import com.juan.curso.springboot.app.springboo_crud.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	private ProductService productService;

	// private ProductValidation validation;

	public ProductController(@Autowired ProductService productService) {
		this.productService = productService;
		// this.validation = validation;
	}

	@GetMapping
	public List<Product> List() {
		return productService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> view(@PathVariable Long id) {
		Optional<Product> productOptional = productService.findById(id);
		if (productOptional.isPresent()) {
			return ResponseEntity.ok(productOptional.orElseThrow());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody Product product, BindingResult result) {

		// validation.validate(product, result);

		if (result.hasFieldErrors()) {
			return validation(result);
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Product product, BindingResult result,
			@PathVariable Long id) {

		// validation.validate(product, result);
		
		if (result.hasFieldErrors()) {
			return validation(result);
		}

		Optional<Product> productOptional = productService.update(id, product);

		if (productOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(productOptional.orElseThrow());
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Product> delete(@PathVariable Long id) {
		Optional<Product> productOptional = productService.delete(id);
		if (productOptional.isPresent()) {
			return ResponseEntity.ok(productOptional.orElseThrow());
		}

		return ResponseEntity.notFound().build();
	}

	private ResponseEntity<?> validation(BindingResult result) {
		Map<String, String> errors = new HashMap<>();

		result.getFieldErrors().forEach(
				err -> errors.put(err.getField(), "The field " + err.getField() + " " + err.getDefaultMessage()));

		return ResponseEntity.badRequest().body(errors);
	}
}
