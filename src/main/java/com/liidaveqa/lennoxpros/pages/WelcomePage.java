package com.liidaveqa.lennoxpros.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.Uninterruptibles;
import com.liidaveqa.lennoxpros.services.Logger;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.hamcrest.core.Is.is;

@Lazy
@Component
@Logger
public class WelcomePage extends BasePage {
	@Value("${app.username}")
	private String uName;

	@FindBy(xpath = "//img[@alt='LennoxPros Logo']")
	private WebElement logo;

	@FindBy(xpath = "//button[@id='lp-dropdown-schedule']")
	private WebElement signedInUser;

	@FindBy(xpath = "//a[@href='#navigation']")
	private WebElement hamburger;

	@FindBy(xpath = "//a[text()='Sales Tools']")
	private WebElement salesTools;

	@FindBy(xpath = "//a[text()='Build a Proposal']")
	private WebElement buildProposal;

	@FindBy(xpath = "//a[text()='SELECT LEAD']")
	private WebElement selectLead;

	@FindBy(id = "logout_id")
	private WebElement signOut;

	@Override
	public boolean hasItLoaded() {
		await().atMost(5, TimeUnit.SECONDS).until(this.logo::isDisplayed, is(true));
		return canWeSee(this.signedInUser) && canWeClickOn(this.signedInUser);
	}

	public String fetchSignedInUser() {
		return signedInUser.getText();
	}

	public void buildProposal() {
		clickOn(this.hamburger);
		clickOn(this.salesTools);
		clickOn(this.buildProposal);
		await().atMost(10, TimeUnit.SECONDS).until(this.selectLead::getText, is("SELECT LEAD"));
		js.executeScript("arguments[0].click();", this.selectLead);
	}

	public void logOut() {
		clickOn(this.signedInUser);
		js.executeScript("arguments[0].click();", this.signOut);
		Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
	}
}