package com.juan.springboot.di.app.springboot_di.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.juan.springboot.di.app.springboot_di.models.Product;
import com.juan.springboot.di.app.springboot_di.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	private Environment environment;

	// Primera forma de inyectar propiedades
	private ProductRepository repository;

	// Segunda forma de inyectar propiedades de forma individual
	@Value("${config.price.tax}")
	private Double tax;

	// A partir de Spring 4.3, si una clase tiene un único constructor,
	// Spring lo usará para la inyección de dependencias incluso sin la anotación
	// @Autowired
	public ProductServiceImpl(@Qualifier("productRepositoryJson") ProductRepository repository, Environment environment) {
		this.repository = repository;
		this.environment = environment;
	}

	@Override
	public List<Product> findAll() {
		return repository.findAll().stream().map(p -> {
			Double priceTax = p.getPrice() * environment.getProperty("config.price.tax", Double.class);
			Product newProduct = new Product(p);
			newProduct.setPrice(priceTax.longValue());
			return newProduct;
		}).collect(Collectors.toList());
	}

	@Override
	public Product findById(Long id) {
		return repository.findById(id);
	}

}
