package com.juan.curso.springboot.app.springboo_crud.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.juan.curso.springboot.app.springboo_crud.services.ProductService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IsExistsDbValidation implements ConstraintValidator<IsExistsDb, String> {

	private ProductService service;

	public IsExistsDbValidation(@Autowired ProductService productService) {
		this.service = productService;
	}


	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return !service.existsBySku(value);
	}

}
