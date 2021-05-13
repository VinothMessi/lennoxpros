package com.liidaveqa.lennoxpros.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.liidaveqa.lennoxpros.services.Logger;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.*;

@Lazy
@Component
@Logger
public class SignInPage extends BasePage {
    @Value("${app.username}")
    private String uName;

    @Value("${app.password}")
    private String pWord;

    @FindBy(id = "j_username")
    private WebElement userName;

    @FindBy(id = "j_password")
    private WebElement password;

    @FindBy(id = "loginSubmit")
    private WebElement signIn;

    @Override
    public boolean hasItLoaded() {
        await().atMost(5, TimeUnit.SECONDS).until(() -> canWeSee(this.userName)
                && canWeSee(this.password) && canWeSee(this.signIn));
        return canWeClickOn(this.userName) && canWeClickOn(this.password) && canWeClickOn(this.signIn);
    }

    public void logIn() {
        type(uName,this.userName);
        type(pWord,this.password);
        clickOn(this.signIn);
    }
}