package com.juan.backend.cartapp.backendcartapp.services;

import java.util.List;

import com.juan.backend.cartapp.backendcartapp.models.entities.Product;

public interface ProductService {
	List<Product> findAll();
}
