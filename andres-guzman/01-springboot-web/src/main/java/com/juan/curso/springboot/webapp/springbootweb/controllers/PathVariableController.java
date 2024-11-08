package com.juan.curso.springboot.webapp.springbootweb.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juan.curso.springboot.webapp.springbootweb.models.User;
import com.juan.curso.springboot.webapp.springbootweb.models.dto.ParamDto;

@RestController
@RequestMapping("/api/var")
public class PathVariableController {

	@Value("${config.username}")
	private String username;

	@Value("${config.message}")
	private String message;

	@Value("${config.listOfValues}")
	private List<String> listOfValue;

	@Value("${config.code}")
	private Integer code;

	// Uso de SpEL (Spring Expression Language) para manipular valores de
	// propiedades de configuración en el archivo values.properties
	@Value("#{'${config.listOfValues}'.split(',')}")
	private List<String> valueList;

	@Value("#{'${config.listOfValues}'.toUpperCase()}")
	private String valueString;

	@Value("#{${config.valuesMap}}")
	private Map<String, Object> valuesMap;

	@Value("#{${config.valuesMap}.product}")
	private String product;

	@Value("#{${config.valuesMap}.price}")
	private String price;

	@Autowired
	private Environment environment;

	@GetMapping("/baz/{message}")
	public ParamDto baz(@PathVariable String message) {
		ParamDto param = new ParamDto();
		param.setMessage(message);
		return param;
	}

	@GetMapping("/mix/{product}/{id}")
	public Map<String, Object> mixPathVar(@PathVariable String product, @PathVariable Long id) {
		Map<String, Object> json = new HashMap<>();
		json.put("product", product);
		json.put("id", id);
		return json;
	}

	@PostMapping("/create")
	public User create(@RequestBody User user) {
		return user;
	}

	@GetMapping("/values")
	public Map<String, Object> values() {
		Map<String, Object> json = new HashMap<>();
		json.put("username", username);
		json.put("message", message);
		json.put("message2", environment.getProperty("config.message"));
		json.put("listOfValue", listOfValue);
		json.put("valueList", valueList);
		json.put("valueString", valueString);
		json.put("product", product);
		json.put("price", price);
		json.put("code", code);
		json.put("code2", Integer.valueOf(environment.getProperty("config.code")));
		json.put("code3", environment.getProperty("config.code", Long.class));
		return json;
	}

}
