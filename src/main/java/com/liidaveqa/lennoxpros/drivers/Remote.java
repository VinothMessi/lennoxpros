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
import org.springframework.context.annotation.Scope;

import com.liidaveqa.lennoxpros.services.Logger;

import java.net.MalformedURLException;
import java.net.URL;

@Lazy
@Configuration
@Profile("remote")
@Logger
public class Remote {

    @Value("${hub.host}")
    private String host;

    @Value("${hub.port}")
    private String port;

    @Lazy
    @Bean
    @Scope("browser")
    @ConditionalOnProperty(name = "browser", value = "firefox")
    public WebDriver firefox() throws MalformedURLException {
        return new RemoteWebDriver(new URL("http://" + this.host + ":" + this.port + "/wd/hub"), DesiredCapabilities.firefox());
    }

    @Lazy
    @Bean
    @Scope("browser")
    @ConditionalOnMissingBean
    public WebDriver chrome() throws MalformedURLException {
        return new RemoteWebDriver(new URL("http://" + this.host + ":" + this.port + "/wd/hub"), DesiredCapabilities.chrome());
    }

}