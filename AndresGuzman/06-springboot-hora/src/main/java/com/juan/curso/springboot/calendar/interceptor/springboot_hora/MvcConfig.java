package com.juan.curso.springboot.calendar.interceptor.springboot_hora;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.juan.curso.springboot.calendar.interceptor.springboot_hora.interceptors.CalendarInterceptor;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	private HandlerInterceptor calendarInterceptor;

	public MvcConfig(@Qualifier("calendarInterceptor") CalendarInterceptor calendarInterceptor) {
		this.calendarInterceptor = calendarInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(calendarInterceptor).addPathPatterns("/foo");
	}

}
