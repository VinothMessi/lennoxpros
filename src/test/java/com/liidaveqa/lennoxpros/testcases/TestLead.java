package com.liidaveqa.lennoxpros.testcases;

import com.liidaveqa.lennoxpros.TestBase;
import com.liidaveqa.lennoxpros.excel.ExcelReader;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Map;

public
class TestLead extends TestBase {

    @Test(dataProvider = "Leads")
    public
    void createLead(Map<String, String> testdata) {
        testCase = report.createChildTestCase(testdata.get("firstName") + testdata.get("lastName"), parentTest);
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
            p.saveAs(testdata.get("firstName"), testdata.get("lastName"), testdata.get("phoneNumber"),
                    testdata.get("emailID"), testdata.get("requestDate"), testdata.get("appointmentDate"),
                    testdata.get("requestTime"), testdata.get("appointmentTime"), testdata.get("upload"),
                    testdata.get("fileName"));
            testCase.info("Lead created successfully:" + testdata.get("firstName") + testdata.get("lastName"));

            testCase.info("Success Message:" + p.saveLead());
            String snap = takeSnap.saveAs(testdata.get("firstName") + "_" + testdata.get("lastName") + ".png");
            testCase.addScreenCaptureFromPath(snap);

            p.goToWelcomePage();
        }).welcomePage(p -> {
            p.logOut();
            testCase.info("User logged out successfully");
        });
    }

    @DataProvider(name = "Leads")
    public
    Object[][] dataProviderMethod(Method method, ITestContext ctx) throws Exception {
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