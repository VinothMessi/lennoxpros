package com.liidaveqa.lennoxpros.services;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

@Lazy
@Component
@Logger
@Scope("prototype")
public class TestReport {

	@Value("${user.dir}" + "${report.config}")
	private String reportConfig;

	@Value("${user.dir}" + "${report.path}")
	private String reportPath;

	@Value("${report.name}")
	private String reportName;

	@Lazy
	@Autowired
	private ApplicationContext ctx;

	@PostConstruct
	private void initializeTheReport() throws IOException {
		ExtentSparkReporter html = new ExtentSparkReporter(this.reportPath + this.reportName + "_" + RandomUtils.timeStamp() + ".html");
		html.loadXMLConfig(reportConfig);
		ctx.getBean("report", ExtentReports.class).attachReporter(html);
	}
	
	public ExtentTest createTestCase(String testCaseName) {
		return ctx.getBean("report", ExtentReports.class).createTest(testCaseName);
	}

	public ExtentTest createTestCase(String testCaseName, String testCaseDescription) {
		return ctx.getBean("report", ExtentReports.class).createTest(testCaseName, testCaseDescription);
	}
	
	public ExtentTest createChildTestCase(String testCaseName, ExtentTest parentTestCase) {
		return parentTestCase.createNode(testCaseName);
	}
	
	public ExtentTest createChildTestCase(String testCaseName, String testCaseDescription, ExtentTest parentTestCase) {
		return parentTestCase.createNode(testCaseName, testCaseDescription);
	}
	
	public void writeContents() {
		ctx.getBean("report", ExtentReports.class).flush();
	}

}