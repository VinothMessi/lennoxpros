package com.liidaveqa.lennoxpros.pages;

import static org.awaitility.Awaitility.await;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.liidaveqa.lennoxpros.services.Logger;

@Lazy
@Component
@Logger
public class UserDetailsPage extends BasePage {

	@FindBy(id = "firstName")
	private WebElement firstName;

	@FindBy(id = "lastName")
	private WebElement lastName;

	@FindBy(id = "phNo")
	private WebElement phNumber;

	@FindBy(id = "email")
	private WebElement email;

	@Override
	public boolean hasItLoaded() {
		await().atMost(5, TimeUnit.SECONDS).until(this.firstName::isDisplayed);
		return canWeClickOn(this.firstName);
	}

	public void fillDetails(String fName, String lName, String pHNum, String mailID) {
		type(fName, this.firstName);
		type(lName, this.lastName);
		type(pHNum, this.phNumber);
		type(mailID, this.email);
	}
}