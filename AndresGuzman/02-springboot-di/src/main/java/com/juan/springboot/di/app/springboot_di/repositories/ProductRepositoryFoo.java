package com.juan.springboot.di.app.springboot_di.repositories;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.juan.springboot.di.app.springboot_di.models.Product;

@Repository
public class ProductRepositoryFoo implements ProductRepository {

	@Override
	public List<Product> findAll() {
		return Collections.singletonList(new Product(1L, "Product Foo", 100L));
	}

	@Override
	public Product findById(Long id) {
		return new Product(id, "Product Foo", 100L);
	}

}
