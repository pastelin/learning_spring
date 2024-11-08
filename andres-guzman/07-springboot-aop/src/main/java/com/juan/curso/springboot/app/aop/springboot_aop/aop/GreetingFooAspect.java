package com.juan.curso.springboot.app.aop.springboot_aop.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
@Aspect
public class GreetingFooAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// Si el orden es 1, se ejecuta primero
	@Before("GreetingServicePointcuts.greetingFooLoggerPointCut()")
	public void loggerBefore(JoinPoint joinPoint) {
		String method = joinPoint.getSignature().getName();
		String args = Arrays.toString(joinPoint.getArgs());

		logger.info("Antes Foo: {} , invocado con los parámetros {}", method, args);
	}

	// Si el orden es 1, se ejecuta al final de todos los demás aspectos
	@After("GreetingServicePointcuts.greetingFooLoggerPointCut()")
	public void loggerAfter(JoinPoint joinPoint) {
		String method = joinPoint.getSignature().getName();
		String args = Arrays.toString(joinPoint.getArgs());

		logger.info("Después Foo: {} , con los parámetros: {}", method, args);
	}
}
