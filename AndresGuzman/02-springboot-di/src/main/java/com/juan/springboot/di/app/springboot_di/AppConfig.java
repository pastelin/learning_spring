package com.juan.springboot.di.app.springboot_di;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

import com.juan.springboot.di.app.springboot_di.repositories.ProductRepository;
import com.juan.springboot.di.app.springboot_di.repositories.ProductRepositoryJson;

@Configuration
@PropertySource("classpath:config.properties")
public class AppConfig {

	// Forma declarativa
	// Se define aquí para que se pueda inyectar en el constructor de
	// ProductRepositoryJson
	@Value("classpath:json/products.json")
	private Resource resource;

	@Bean
	ProductRepository productRepositoryJson() {
		return new ProductRepositoryJson(resource) {
		};
	}

}
