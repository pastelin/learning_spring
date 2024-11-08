package com.juan.springboot.di.app.springboot_di.repositories;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juan.springboot.di.app.springboot_di.models.Product;

public class ProductRepositoryJson implements ProductRepository {

	private List<Product> list;

	// Forma declarativa
	// Falla por que no está anotada directamente por @Component por lo que se
	// configura desde AppConfig
	// @Value("classpath:json/products.json")
	// private Resource resource;

	public ProductRepositoryJson() {
		// Forma programática
		Resource resource = new ClassPathResource("json/products.json");
		readValueJson(resource);
	}

	public ProductRepositoryJson(Resource resource) {
		readValueJson(resource);
	}

	private void readValueJson(Resource resource) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			list = Arrays.asList(objectMapper.readValue(resource.getInputStream(), Product[].class));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Product> findAll() {
		return list;
	}

	@Override
	public Product findById(Long id) {
		return list.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
	}

}
