package com.liidaveqa.lennoxpros.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

import com.liidaveqa.lennoxpros.services.Logger;

@Lazy
@Configuration
@Profile("local")
@Logger
public class Local {

    @Value("${browser.version}")
    private String version;

    @Lazy
    @Bean
    @Scope("browser")
    @ConditionalOnProperty(name = "browser", havingValue = "edge")
    public WebDriver edge() {
        WebDriverManager.edgedriver().browserVersion(this.version).setup();
        return new EdgeDriver();
    }

    @Lazy
    @Bean
    @Scope("browser")
    @ConditionalOnProperty(name = "browser", havingValue = "firefox")
    public WebDriver firefox() {
        WebDriverManager.firefoxdriver().browserVersion(this.version).setup();
        return new FirefoxDriver();
    }

    @Lazy
    @Bean
    @Scope("browser")
    @ConditionalOnMissingBean
    public WebDriver chrome() {
        WebDriverManager.chromedriver().browserVersion(this.version).setup();
        return new ChromeDriver();
    }

}