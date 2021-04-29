package com.liidaveqa.lennoxpros;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.liidaveqa.lennoxpros.excel.ExcelReader;
import com.liidaveqa.lennoxpros.services.TakeASnap;
import com.liidaveqa.lennoxpros.services.TestReport;
import com.liidaveqa.lennoxpros.workflow.CreatingALead;

import java.io.IOException;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

@SpringBootTest
public class TestBase extends AbstractTestNGSpringContextTests {
	
	@Autowired
    private WebDriver browser;

	@Lazy
	@Autowired
	protected TestReport report;
	
	@Lazy
	@Autowired
	protected TakeASnap takeSnap;

	protected ExtentTest parentTest;
	protected ExtentTest testCase;

	@Autowired
	protected CreatingALead buildProposal;

	@Value("${user.dir}" + "${excel.path}")
	private String excelPath;

	@Value("${excel.name}")
	private String excelName;

	private ExcelReader excelReader;

	@BeforeClass
	public void setUp(ITestContext ctx) throws IOException {
		excelReader = new ExcelReader(excelPath, excelName);
		ctx.setAttribute("excelData", excelReader);
		parentTest = report.createTestCase(ctx.getName());
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			String logText = "<b>" + "Test Case Execution Completed Successful" + "</b>";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
			testCase.pass(m);
		} else if (result.getStatus() == ITestResult.FAILURE) {
			String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
			testCase.fail("<details>" + "<summary>" + "<b>" + "<font color=red>"
					+ "Exception Occurred: Click to see details: " + "</font>" + "</b>" + "</summary>"
					+ exceptionMessage.replaceAll(",", "<br>") + "</details>" + " \n");
			String logText = "<b>" + "Test Case Execution Failed" + "</b>";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
			testCase.fail(m);
		} else if (result.getStatus() == ITestResult.SKIP) {
			String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
			testCase.skip("<details>" + "<summary>" + "<b>" + "<font color=orange>"
					+ "Exception Occurred: Click to see details: " + "</font>" + "</b>" + "</summary>"
					+ exceptionMessage.replaceAll(",", "<br>") + "</details>" + " \n");
			String logText = "<b>" + "Test Case Execution Skipped" + "</b>";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.ORANGE);
			testCase.skip(m);
		}
		report.writeContents();
	}
	
	@AfterClass
	public void tearDown() {
		browser.quit();
	}
}