package com.juan.backend.userapp.backenduserapp.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juan.backend.userapp.backenduserapp.models.dto.UserDto;
import com.juan.backend.userapp.backenduserapp.models.entities.User;
import com.juan.backend.userapp.backenduserapp.models.request.UserRequest;
import com.juan.backend.userapp.backenduserapp.services.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping
	public List<UserDto> list() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Optional<UserDto> userOptional = service.findById(id);

		if (userOptional.isPresent()) {
			return ResponseEntity.ok(userOptional.orElseThrow());
		}

		return ResponseEntity.notFound().build();

	}

	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result) {

		if (result.hasErrors()) {
			return validation(result);
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
	}

	private ResponseEntity<?> validation(BindingResult result) {
		Map<String, String> errors = new HashMap<>();

		result.getFieldErrors().forEach(
				err -> errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage()));

		return ResponseEntity.badRequest().body(errors);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody UserRequest user, BindingResult result, @PathVariable Long id) {

		if (result.hasErrors()) {
			return validation(result);
		}

		Optional<UserDto> userOptional = service.update(user, id);

		if (userOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(userOptional.orElseThrow());
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Optional<UserDto> userOptional = service.findById(id);

		if (userOptional.isPresent()) {
			service.remove(id);
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.notFound().build();
	}

}
