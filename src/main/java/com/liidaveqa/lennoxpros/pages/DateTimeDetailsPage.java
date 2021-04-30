package com.liidaveqa.lennoxpros.pages;

import static org.awaitility.Awaitility.await;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.liidaveqa.lennoxpros.services.Logger;

@Lazy
@Component
@Logger
public class DateTimeDetailsPage extends BasePage {

	@FindBy(id = "calender1")
	private WebElement requestDate;

	@FindBy(id = "calender2")
	private WebElement appointmentDate;

	@FindBy(id = "scheduleRequestTime")
	private WebElement requestTime;

	@FindBy(id = "appointmentRequestTime")
	private WebElement appointmentTime;

	@Override
	boolean hasItLoaded() {
		await().atMost(5, TimeUnit.SECONDS).until(this.requestDate::isDisplayed);
		return canWeClickOn(this.requestDate);
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
}