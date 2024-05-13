package com.example.demospring.config;

import com.example.demospring.browser.BrowserException;
import com.example.demospring.browser.Chrome;
import com.example.demospring.browser.Edge;
import com.example.demospring.browser.Firefox;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class Driver {


    @Bean
    @Scope("singleton")
    public WebDriver getWebDriver() throws BrowserException {
        String browser = ConfigurationReader.getProperty("browser");
        WebDriver driver;
        switch (browser.trim()) {
            case "chrome" -> driver = new Chrome().createInstance();
            case "edge" -> driver = new Edge().createInstance();
            case "firefox" -> driver = new Firefox().createInstance();
            default -> throw new BrowserException(browser + " is not supported");
        }
        driver.manage().window().maximize();
        return driver;
    }

//    @PreDestroy
//    public void closeWebDriver() {
//        if (driver != null) {
//            System.out.println();
//            driver.close();
//            driver.quit();
//        }
//    }

}
