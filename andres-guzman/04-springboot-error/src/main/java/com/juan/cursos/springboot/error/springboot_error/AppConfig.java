package com.juan.cursos.springboot.error.springboot_error;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.juan.cursos.springboot.error.springboot_error.models.domain.User;

@Configuration
public class AppConfig {

	@Bean
	List<User> users() {
		return List.of(
				new User(1L, "John", "Doe"),
				new User(2L, "Jane", "Doe"),
				new User(3L, "Tina", "Doe"),
				new User(4L, "Eric", "Doe"),
				new User(5L, "Laura", "Doe"));
	}

}
