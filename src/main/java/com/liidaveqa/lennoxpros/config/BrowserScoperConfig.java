package com.liidaveqa.lennoxpros.config;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Lazy
@Configuration
public class BrowserScoperConfig {
	
	@Bean
	public static BeanFactoryPostProcessor beanFactoryPostProcessor() {
		return new BrowserScopePostProcessor();
	} 

}