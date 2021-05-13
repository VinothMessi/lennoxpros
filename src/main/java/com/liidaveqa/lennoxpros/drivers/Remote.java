package com.liidaveqa.lennoxpros.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;

import com.liidaveqa.lennoxpros.services.Logger;

import java.net.MalformedURLException;
import java.net.URL;

@Lazy
@Configuration
@Profile("remote")
@Logger
public class Remote {

    @Value("${hub_host}")
    private String host;

    @Lazy
    @Bean
    @ConditionalOnProperty(name = "browser.name", havingValue = "firefox")
    public WebDriver firefox() throws MalformedURLException {
        return new RemoteWebDriver(new URL("http://" + this.host + ":4444/wd/hub"), DesiredCapabilities.firefox());
    }

    @Lazy
    @Bean
    @ConditionalOnMissingBean
    public WebDriver chrome() throws MalformedURLException {
        return new RemoteWebDriver(new URL("http://" + this.host + ":4444/wd/hub"), DesiredCapabilities.chrome());
    }

}