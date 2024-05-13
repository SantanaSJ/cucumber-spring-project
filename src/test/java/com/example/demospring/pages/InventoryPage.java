package com.example.demospring.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InventoryPage {

    @Autowired
    private WebDriver driver;

    public String getInventoryPageCurrentURL() {
        return driver.getCurrentUrl();
    }

    public WebElement getProductsTitleElement() {
        return driver.findElement(By.xpath("//span[@class='title'][contains(.,'Products')]"));
    }

}
