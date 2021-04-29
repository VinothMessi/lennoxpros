package com.liidaveqa.lennoxpros.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.liidaveqa.lennoxpros.services.Logger;

import javax.annotation.PostConstruct;

@Logger
public abstract class BasePage {

    @Autowired
    protected WebDriver browser;

    protected JavascriptExecutor js;

    @PostConstruct
    private void init() {
        PageFactory.initElements(browser, this);
        js = (JavascriptExecutor) browser;
    }

    abstract boolean hasItLoaded();

    protected void maximize() {
        browser.manage().window().maximize();
    }

    protected void goTo(String url) {
        browser.get(url);
    }

    protected void clickOn(WebElement ele) {
        ele.click();
    }

    protected void type(String text, WebElement ele) {
        ele.sendKeys(text);
    }

    protected boolean canWeSee(WebElement ele) {
        return ele.isDisplayed();
    }

    protected boolean canWeClickOn(WebElement ele) {
        return ele.isEnabled();
    }

}