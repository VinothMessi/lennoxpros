package com.liidaveqa.lennoxpros.services;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

	@Autowired
	private LogService logService;

	@Before("@target(logger) && within(com.liidaveqa.lennoxpros..*)")
	public void before(JoinPoint joinPoint, Logger logger) {
		this.logService
				.info("Starting to execute the method: " + joinPoint.getSignature().getName() + "()............");
	}

	@After("@target(logger) && within(com.liidaveqa.lennoxpros..*)")
	public void after(JoinPoint joinPoint, Logger logger) {
		this.logService
				.info("Method: " + joinPoint.getSignature().getName() + "() is executed successfully............");
	}

}