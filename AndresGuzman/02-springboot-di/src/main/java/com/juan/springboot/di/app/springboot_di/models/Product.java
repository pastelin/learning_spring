package com.juan.springboot.di.app.springboot_di.models;

public class Product {

	private Long id;
	private String name;
	private Long price;

	public Product(Long id, String name, Long price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public Product() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Product(Product product) {
		this.id = product.id;
		this.name = product.name;
		this.price = product.price;
	}

}
