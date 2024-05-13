package com.example.demospring.actions;

import com.example.demospring.pages.LoginPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PageActions {

    @Autowired
    private LoginPage loginPage;

    public void enterUsername(String username) {
        loginPage.getUsernameFieldElement().sendKeys(username);
    }

    public void enterPassword(String password) {
        loginPage.getPasswordFieldElement().sendKeys(password);
    }

    public void clickLoginButton() {
        loginPage.getLoginButtonElement().click();
    }

}
