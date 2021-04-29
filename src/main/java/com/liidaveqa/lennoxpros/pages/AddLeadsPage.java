package com.liidaveqa.lennoxpros.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.Uninterruptibles;
import com.liidaveqa.lennoxpros.services.Logger;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

@Lazy
@Component
@Logger
public class AddLeadsPage extends BasePage {

	@Value("${user.dir}" + "${file.path}")
	private String filePath;

	@FindBy(id = "firstName")
	private WebElement firstName;

	@FindBy(id = "lastName")
	private WebElement lastName;

	@FindBy(id = "phNo")
	private WebElement phNumber;

	@FindBy(id = "email")
	private WebElement email;

	@FindBy(id = "calender1")
	private WebElement requestDate;

	@FindBy(id = "calender2")
	private WebElement appointmentDate;

	@FindBy(id = "scheduleRequestTime")
	private WebElement requestTime;

	@FindBy(id = "appointmentRequestTime")
	private WebElement appointmentTime;

	@FindBy(xpath = "//p[text()='Add Document']")
	private WebElement addDocument;

	@FindBy(name = "documents[1].documentType")
	private WebElement docType;

	@FindBy(id = "multipleFileSelect-1")
	private WebElement selectFile;

	@FindBy(name = "imageFiles")
	private WebElement addImage;

	@FindBy(xpath = "//span[text()='Add To Lead']")
	private WebElement addToLead;

	@FindBy(xpath = "//span[text()='SAVE LEAD']")
	private WebElement saveLead;
	
	@FindBy(xpath = "(//ul[@class='success-msg'])[2]/li")
	private WebElement successMessage;
	
	@FindBy(xpath = "//img[@alt='LennoxPros Logo']")
    private WebElement logo;

	@Override
	public boolean hasItLoaded() {
		await().atMost(5, TimeUnit.SECONDS).until(this.firstName::isDisplayed);
		return canWeClickOn(this.firstName);
	}

	public void fillUerDetails(String fName, String lName, String pHNum, String mailID) {
		type(fName, this.firstName);
		type(lName, this.lastName);
		type(pHNum, this.phNumber);
		type(mailID, this.email);
	}

	public void fillRequestDate(String day) {
		clickOn(this.requestDate);
		List<WebElement> rows = browser.findElements(By.xpath("//div[@id='ui-datepicker-div']//tbody/tr"));
		List<WebElement> columns = browser.findElements(By.xpath("//div[@id='ui-datepicker-div']//tbody/tr[1]/td"));
		for (int i = 1; i <= rows.size(); i++) {
			for (int j = 1; j <= columns.size(); j++) {
				Boolean result = browser
						.findElements(By.xpath("//div[@id='ui-datepicker-div']//tbody/tr[" + i + "]/td[" + j + "]/a"))
						.isEmpty();
				if (Boolean.FALSE.equals(result)) {
					WebElement e = browser.findElement(
							By.xpath("//div[@id='ui-datepicker-div']//tbody/tr[" + i + "]/td[" + j + "]/a"));
					String date = e.getText();
					if (date.equals(day)) {
						clickOn(e);
						break;
					}
				}
			}
		}
	}

	public void fillAppointmentDate(String day) {
		clickOn(this.appointmentDate);
		List<WebElement> rows = browser.findElements(By.xpath("//div[@id='ui-datepicker-div']//tbody/tr"));
		List<WebElement> columns = browser.findElements(By.xpath("//div[@id='ui-datepicker-div']//tbody/tr[1]/td"));
		for (int i = 1; i <= rows.size(); i++) {
			for (int j = 1; j <= columns.size(); j++) {
				Boolean result = browser
						.findElements(By.xpath("//div[@id='ui-datepicker-div']//tbody/tr[" + i + "]/td[" + j + "]/a"))
						.isEmpty();
				if (Boolean.FALSE.equals(result)) {
					WebElement e = browser.findElement(
							By.xpath("//div[@id='ui-datepicker-div']//tbody/tr[" + i + "]/td[" + j + "]/a"));
					String date = e.getText();
					if (date.equals(day)) {
						clickOn(e);
						break;
					}
				}
			}
		}
	}

	public void fillRequestTime(String time) {
		Select e = new Select(this.requestTime);
		e.selectByVisibleText(time);
	}

	public void fillAppointmentTime(String time) {
		Select e = new Select(this.appointmentTime);
		e.selectByVisibleText(time);
	}

	public void uploadDocument(String docName) {
		clickOn(this.addDocument);
		Select e = new Select(this.docType);
		e.selectByVisibleText("OTHER");
		this.selectFile.sendKeys(this.filePath + docName);
		js.executeScript("arguments[0].click();", this.addToLead);
	}

	public void uploadImage(String imageName) {
		js.executeScript("arguments[0].scrollIntoView(true);", this.addImage);
		this.addImage.sendKeys(this.filePath + imageName);
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