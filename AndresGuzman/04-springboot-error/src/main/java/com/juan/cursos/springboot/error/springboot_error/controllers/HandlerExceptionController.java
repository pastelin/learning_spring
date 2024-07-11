package com.juan.cursos.springboot.error.springboot_error.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.juan.cursos.springboot.error.springboot_error.exceptions.UserNotFoundException;
import com.juan.cursos.springboot.error.springboot_error.models.Error;

@RestControllerAdvice
public class HandlerExceptionController {

	@ExceptionHandler({ ArithmeticException.class })
	public ResponseEntity<Error> divisionByZero(Exception ex) {
		Error error = new Error();
		error.setDate(new Date());
		error.setError("Error división por cero");
		error.setMessage(ex.getMessage());
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}

	@ExceptionHandler({ NumberFormatException.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, Object> numberFormatException(NumberFormatException ex) {
		Map<String, Object> error = new HashMap<>();

		error.put("error", "Error en el formato de número");
		error.put("message", ex.getMessage());
		error.put("date", new Date());
		error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

		return error;
	}

	@ExceptionHandler({ NullPointerException.class, HttpMessageNotWritableException.class,
			UserNotFoundException.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, Object> userNotFoundException(Exception ex) {
		Map<String, Object> error = new HashMap<>();

		error.put("error", "El usuario o role no existe");
		error.put("message", ex.getMessage());
		error.put("date", new Date());
		error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

		return error;
	}

	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<Error> noHandlerFoundException(NoResourceFoundException e) {
		Error error = new Error();
		error.setDate(new Date());
		error.setError("API rest no encontrado");
		error.setMessage(e.getMessage());

		error.setStatus(HttpStatus.NOT_FOUND.value());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

}
