package com.liidaveqa.lennoxpros.testcases;

import com.liidaveqa.lennoxpros.TestBase;
import com.liidaveqa.lennoxpros.excel.ExcelReader;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

public class TestLead extends TestBase {

	@Test(dataProvider = "Leads")
	public void createLead(Map<String, String> testdata) {
		testCase = report.createChildTestCase("Create Lead:" + testdata.get("firstName") + testdata.get("lastName"),
				parentTest);

		buildProposal.homePage(p -> {
			p.launchWebsite();
			p.hasItLoaded();
			p.goToSignInPage();
			testCase.info("Navigated to The website successfully");
		}).signInPage(p -> {
			p.hasItLoaded();
			p.logIn();
			testCase.info("User Logged In successfully");
		}).welcomePage(p -> {
			p.hasItLoaded();
			System.out.println("Signed In User:" + p.fetchSignedInUser());
			testCase.info("Signed In User:" + p.fetchSignedInUser());
			p.buildProposal();
		}).leadsPage(p -> {
			p.hasItLoaded();
			p.addLead();
			testCase.info("Trying to add a Lead");
		}).addLeadsPage(p -> {
			p.hasItLoaded();
			p.fillUerDetails(testdata.get("firstName"), testdata.get("lastName"), testdata.get("phoneNumber"),
					testdata.get("emailID"));
			testCase.info("User Details filled successfully");
			p.fillRequestDate(testdata.get("requestDate"));
			p.fillAppointmentDate(testdata.get("appointmentDate"));
			p.fillRequestTime(testdata.get("requestTime"));
			p.fillAppointmentTime(testdata.get("appointmentTime"));
			testCase.info("Date adn Time Details filled successfully");
			if (testdata.get("upload").equals("doc")) {
				p.uploadDocument(testdata.get("fileName"));
				testCase.info("Document:" + testdata.get("fileName") + " " + "uploaded successfully");
			}
			if (testdata.get("upload").equals("img")) {
				p.uploadImage(testdata.get("fileName"));
				testCase.info("Image:" + testdata.get("fileName") + " " + "uploaded successfully");
			}
			testCase.info("Success Message:" + p.saveLead());
			try {
				String snap = takeSnap.saveAs(testdata.get("firstName") + "_" + testdata.get("lastName") + ".png");
				testCase.addScreenCaptureFromPath(snap);
			} catch (IOException e) {
				e.printStackTrace();
			}
			p.goToWelcomePage();
		}).welcomePage(p -> {
			p.logOut();
			testCase.info("User logged out successfully");
		});
	}

	@DataProvider(name = "Leads")
	public Object[][] dataProviderMethod(Method method, ITestContext ctx) throws Exception {
		ExcelReader excelReader = (ExcelReader) ctx.getAttribute("excelData");

		Object[][] testDataArray = null;
		try {
			if (method.getName().equals("createLead")) {
				testDataArray = excelReader.readExcelSheet("Leads");
			}
		} catch (Exception exp) {
			throw new Exception(
					"Unable to read the excelSheet in the form of a DataProvider" + exp.getMessage() + "\n");
		}
		return testDataArray;
	}
}