package com.liidaveqa.lennoxpros.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.Uninterruptibles;
import com.liidaveqa.lennoxpros.services.Logger;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

@Lazy
@Component
@Logger
public class AddLeadsPage extends BasePage {
	@Autowired
	private UserDetailsPage userDetails;

	@Autowired
	private DateTimeDetailsPage dateTimeDetails;

	@Autowired
	private UploadPage upload;

	@FindBy(xpath = "//span[text()='SAVE LEAD']")
	private WebElement saveLead;

	@FindBy(xpath = "(//ul[@class='success-msg'])[2]/li")
	private WebElement successMessage;

	@FindBy(xpath = "//img[@alt='LennoxPros Logo']")
	private WebElement logo;

	public void saveAs(String fName, String lName, String pHNum, String mailID, String reqDay, String appDay,
			String reqTime, String appTime, String uploadItem, String fileName) {
		
		userDetails.hasItLoaded();
		userDetails.fillDetails(fName, lName, pHNum, mailID);
		
		dateTimeDetails.hasItLoaded();
		dateTimeDetails.fillRequestDate(reqDay);
		dateTimeDetails.fillAppointmentDate(appDay);
		dateTimeDetails.fillRequestTime(reqTime);
		dateTimeDetails.fillAppointmentTime(appTime);
		
		upload.hasItLoaded();
		if (uploadItem.equals("doc")) {
			upload.document(fileName);
		}
		if (uploadItem.equals("img")) {
			upload.image(fileName);
		}
	}

	@Override
	public boolean hasItLoaded() {
		await().atMost(5, TimeUnit.SECONDS).until(this.saveLead::isDisplayed);
		return canWeClickOn(this.saveLead);
	}
	public String saveLead() {
		js.executeScript("arguments[0].click();", this.saveLead);
		Uninterruptibles.sleepUninterruptibly(15, TimeUnit.SECONDS);
		canWeSee(this.successMessage);
		return this.successMessage.getText().trim();
	}

	public void goToWelcomePage() {
		clickOn(this.logo);
	}
}