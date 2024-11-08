package com.juan.curso.springboot.app.springboo_crud.repositories;

import org.springframework.data.repository.CrudRepository;

import com.juan.curso.springboot.app.springboo_crud.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

	boolean existsBySku(String sku);
}
