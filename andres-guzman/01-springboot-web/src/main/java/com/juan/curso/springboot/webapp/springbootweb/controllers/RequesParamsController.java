package com.juan.curso.springboot.webapp.springbootweb.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juan.curso.springboot.webapp.springbootweb.models.dto.ParamDto;
import com.juan.curso.springboot.webapp.springbootweb.models.dto.ParamMixDto;

import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/params")
public class RequesParamsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RequesParamsController.class);

	@GetMapping("/foo")
	public ParamDto foo(@RequestParam(required = false, defaultValue = "Hola que tal") String message) {
		ParamDto paramDto = new ParamDto();
		paramDto.setMessage(message);
		return paramDto;
	}

	@GetMapping("/bar")
	public ParamMixDto bar(@RequestParam String text, @RequestParam Integer code) {
		ParamMixDto params = new ParamMixDto();
		params.setMessage(text);
		params.setCode(code);

		return params;
	}

	@GetMapping("/request")
	public ParamMixDto request(HttpServletRequest request) {
		Integer code = 0;

		try {
			code = Integer.parseInt(request.getParameter("code"));
		} catch (NumberFormatException e) {
			LOGGER.error("Error: {}", e.getMessage());

		}

		ParamMixDto params = new ParamMixDto();
		params.setCode(code);
		params.setMessage(request.getParameter("message"));
		return params;
	}

}
