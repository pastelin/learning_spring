package com.juan.curso.springboot.app.springboo_crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juan.curso.springboot.app.springboo_crud.entities.Product;
import com.juan.curso.springboot.app.springboo_crud.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public ProductServiceImpl() {
	}

	@Transactional(readOnly = true)
	@Override
	public List<Product> findAll() {
		return (List<Product>) productRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}

	@Transactional
	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Transactional
	@Override
	public Optional<Product> update(Long id, Product product) {
		Optional<Product> productOptional = productRepository.findById(id);

		if (productOptional.isPresent()) {
			Product productDb = productOptional.orElseThrow();

			productDb.setSku(product.getSku());
			productDb.setName(product.getName());
			productDb.setDescription(product.getDescription());
			productDb.setPrice(product.getPrice());
			return Optional.of(productRepository.save(productDb));
		}

		return productOptional;
	}

	@Transactional
	@Override
	public Optional<Product> delete(Long id) {
		Optional<Product> productOptional = productRepository.findById(id);

		productOptional.ifPresent(p -> productRepository.delete(p));

		return productOptional;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existsBySku(String sku) {
		return productRepository.existsBySku(sku);
	}

}
