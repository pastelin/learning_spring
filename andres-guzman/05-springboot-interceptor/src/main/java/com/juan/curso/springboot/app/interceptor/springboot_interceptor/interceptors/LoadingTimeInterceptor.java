package com.juan.curso.springboot.app.interceptor.springboot_interceptor.interceptors;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("timeInterceptor")
public class LoadingTimeInterceptor implements HandlerInterceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoadingTimeInterceptor.class);

	// Declare a class-level Random object
	private static final Random random = new Random();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LOGGER.info("LoadingTimeInterceptor: preHandle() -- Request URL: {}", request.getRequestURL());

		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);

		// Use the class-level Random object
		random.nextInt(500);
		Integer delay = random.nextInt(500);
		Thread.sleep(delay);

		// Map<String, String> json = new HashMap<>();
		// json.put("error", "No tiene acceso a esta pagina!");
		// json.put("date", new Date().toString());

		// ObjectMapper mapper = new ObjectMapper();
		// String jsonString = mapper.writeValueAsString(json);
		// response.setContentType("application/json");
		// response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		// response.getWriter().write(jsonString);
		// return false;

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		long endTime = System.currentTimeMillis();
		long startTime = (long) request.getAttribute("startTime");
		long loadingTime = endTime - startTime;

		LOGGER.info("LoadingTimeInterceptor: postHandle() -- Request URL: {} -- Loading Time: {} ms",
				request.getRequestURL(), loadingTime);
	}

}
