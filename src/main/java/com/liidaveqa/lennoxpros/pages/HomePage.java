package com.liidaveqa.lennoxpros.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.liidaveqa.lennoxpros.services.Logger;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

@Lazy
@Component
@Logger
public class HomePage extends BasePage {

    @Value("${app.url}")
    private String url;

    @FindBy(linkText = "Sign In")
    private WebElement signIn;

    public void launchWebsite() {
        maximize();
        goTo(url);
    }

    @Override
    public boolean hasItLoaded() {
        return this.signIn.isDisplayed() && this.signIn.isEnabled();
    }

    public void goToSignInPage() {
        await().atMost(5, TimeUnit.SECONDS).until(() -> canWeSee(this.signIn)
                && canWeClickOn(this.signIn));
        clickOn(this.signIn);
    }
}