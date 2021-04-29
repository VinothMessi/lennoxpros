package com.liidaveqa.lennoxpros.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class LogService {

	private static final Logger logger = LoggerFactory.getLogger("Lennoxpros : Functional testing");

	public void info(String msg) {
		logger.info(msg);
	}

	public void error(String msg) {
		logger.error(msg);
	}

}