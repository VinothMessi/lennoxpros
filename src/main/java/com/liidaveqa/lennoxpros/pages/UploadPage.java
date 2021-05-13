package com.liidaveqa.lennoxpros.pages;

import static org.awaitility.Awaitility.await;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.liidaveqa.lennoxpros.services.Logger;

@Lazy
@Component
@Logger
public class UploadPage extends BasePage {
	@Value("${user.dir}" + "${file.path}")
	private String filePath;

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

	@Override
	boolean hasItLoaded() {
		await().atMost(5, TimeUnit.SECONDS).until(this.addDocument::isDisplayed);
		return canWeClickOn(this.addDocument);
	}

	public void document(String docName) {
		clickOn(this.addDocument);
		Select e = new Select(this.docType);
		e.selectByVisibleText("OTHER");
		System.out.println(this.filePath + docName);
		if(browser instanceof RemoteWebDriver){
			   ((RemoteWebDriver) browser).setFileDetector(new LocalFileDetector());
		}
		this.selectFile.sendKeys(this.filePath + docName);
		js.executeScript("arguments[0].click();", this.addToLead);
	}

	public void image(String imageName) {
		js.executeScript("arguments[0].scrollIntoView(true);", this.addImage);
		System.out.println(this.filePath + imageName);
		if(browser instanceof RemoteWebDriver){
			   ((RemoteWebDriver) browser).setFileDetector(new LocalFileDetector());
		}
		this.addImage.sendKeys(this.filePath + imageName);
	}
}