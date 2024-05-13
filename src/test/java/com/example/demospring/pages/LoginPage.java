package com.example.demospring.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginPage {

    @Autowired
    private WebDriver driver;

    public WebElement getUsernameFieldElement() {
        return driver.findElement(By.id("user-name"));
    }


    public WebElement getPasswordFieldElement() {
       return driver.findElement(By.id("password"));
    }

    public WebElement getLoginButtonElement() {
      return driver.findElement(By.id("login-button"));
    }

    public WebElement getErrorMessageElement() {
        return driver.findElement(By.xpath("//h3[contains(@data-test,'error')]"));
    }
}
