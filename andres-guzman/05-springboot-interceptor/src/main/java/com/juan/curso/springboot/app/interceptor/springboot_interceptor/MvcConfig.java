package com.juan.curso.springboot.app.interceptor.springboot_interceptor;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	private HandlerInterceptor timeInterceptor;

	public MvcConfig(@Qualifier("timeInterceptor") HandlerInterceptor timeInterceptor) {
		this.timeInterceptor = timeInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(timeInterceptor).addPathPatterns("/app/bar","/app/foo");
		// registry.addInterceptor(timeInterceptor).excludePathPatterns("/app/bar", "/app/foo");
	}
}
