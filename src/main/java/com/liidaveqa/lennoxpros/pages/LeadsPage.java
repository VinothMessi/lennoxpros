package com.liidaveqa.lennoxpros.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.liidaveqa.lennoxpros.services.Logger;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.*;

@Lazy
@Component
@Logger
public class LeadsPage extends BasePage {

    @FindBy(xpath = "(//span[text()='ADD LEAD'])[1]")
    private WebElement addLead;

    @Override
    public boolean hasItLoaded() {
        await().atMost(15, TimeUnit.SECONDS).until(this.addLead::isEnabled);
        return canWeClickOn(this.addLead);
    }

    public void addLead() {
        js.executeScript("arguments[0].click();", this.addLead);
    }
}