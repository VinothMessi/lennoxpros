package com.liidaveqa.lennoxpros.services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.aventstack.extentreports.ExtentReports;

@Lazy
@Configuration
@Logger
public class ReporterConfig {
	
	@Bean
	public ExtentReports report() {
		return new ExtentReports();
	}
	
}