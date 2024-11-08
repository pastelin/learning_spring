package com.juan.curso.springboot.app.springboo_crud;

import org.springframework.stereotype.Component;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.juan.curso.springboot.app.springboo_crud.entities.Product;


@Component
public class ProductValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, org.springframework.validation.Errors errors) {
		Product product = (Product) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", null, "es un campo requerido!");

		if (product.getDescription() == null || product.getDescription().isBlank()) {
			errors.rejectValue("description", null, "es un campo requerido!");
		}

		if (product.getPrice() == null) {
			errors.rejectValue("price", null, "es un campo requerido!");
		} else if (product.getPrice() < 500) {
			errors.rejectValue("price", null, "debe ser un valor mayor o igual a 500!");
		}

	}

}
