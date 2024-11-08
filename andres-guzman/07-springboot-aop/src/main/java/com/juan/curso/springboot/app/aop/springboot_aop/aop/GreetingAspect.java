package com.juan.curso.springboot.app.aop.springboot_aop.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Aspect
@Component
public class GreetingAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	

	@Before("GreetingServicePointcuts.greetingLoggerPointCut()")
	public void loggerBefore(JoinPoint joinPoint) {
		String method = joinPoint.getSignature().getName();
		String args = Arrays.toString(joinPoint.getArgs());

		logger.info("Before method: {} , args: {}", method, args);
	}

	@After("GreetingServicePointcuts.greetingLoggerPointCut()")
	public void loggerAfter(JoinPoint joinPoint) {
		String method = joinPoint.getSignature().getName();
		String args = Arrays.toString(joinPoint.getArgs());

		logger.info("After method: {} , args: {}", method, args);
	}

	@AfterReturning("GreetingServicePointcuts.greetingLoggerPointCut()")
	public void loggerAfterReturning(JoinPoint joinPoint) {
		String method = joinPoint.getSignature().getName();
		String args = Arrays.toString(joinPoint.getArgs());

		logger.info("AfterReturning method: {} , args: {}", method, args);
	}

	@AfterThrowing("GreetingServicePointcuts.greetingLoggerPointCut()")
	public void loggerAfterThrowing(JoinPoint joinPoint) {
		String method = joinPoint.getSignature().getName();
		String args = Arrays.toString(joinPoint.getArgs());

		logger.info("AfterThrowing method: {} , args: {}", method, args);
	}

	@Around("GreetingServicePointcuts.greetingLoggerPointCut()")
	public Object loggerAround(ProceedingJoinPoint joinPoint) throws Throwable {
		String method = joinPoint.getSignature().getName();
		String args = Arrays.toString(joinPoint.getArgs());
		Object result = null;

		try {
			logger.info("El método {}() con los parametros {}", method, args);
			result = joinPoint.proceed();
			return result;
		} catch (Throwable e) {
			logger.error("Error en el método {}()", method);
			throw e;
		}
	}
}
