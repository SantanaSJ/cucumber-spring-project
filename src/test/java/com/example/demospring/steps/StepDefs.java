package com.example.demospring.steps;

import com.example.demospring.actions.PageActions;
import com.example.demospring.pages.InventoryPage;
import com.example.demospring.pages.LoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


@CucumberContextConfiguration
@SpringBootTest
public class StepDefs {

    @Autowired
    private WebDriver driver;

    @Autowired
    private PageActions actions;

    @Autowired
    private InventoryPage inventoryPage;

    @Autowired
    private LoginPage loginPage;

    private static String BASE_URL = "https://www.saucedemo.com/";
    private static String INVENTORY_PAGE_URL = "https://www.saucedemo.com/inventory.html";

    @Given("user is on sauceDemo page")
    public void userIsOnSauceDemoPage() {
        driver.get(BASE_URL);
    }

    @When("user enters valid username {string} and password {string}")
    public void userEntersValidUsernameAndPassword(String username, String password) {
        actions.enterUsername(username);
        actions.enterPassword(password);
    }

    @And("user clicks on the login button")
    public void userClicksOnTheLoginButton() {
        actions.clickLoginButton();
    }

    @Then("user should be logged in successfully and on {string}")
    public void userShouldBeLoggedInSuccessfullyAndOn(String productPage) {
        assertAll(
                "Assert user is on Products Page",
                () -> assertEquals(INVENTORY_PAGE_URL, inventoryPage.getInventoryPageCurrentURL(), "Assert inventory page url"),
                () -> assertEquals(productPage, inventoryPage.getProductsTitleElement().getText(), "Assert Products title is present")
        );
    }

    @When("user enters invalid credentials")
    public void userEntersInvalidCredentials(DataTable table) {
        enterCredentials(table);
    }

    @When("locked user enters correct credentials")
    public void userEntersCorrectCredentials(DataTable table) {
        enterCredentials(table);
    }

    @Then("the error message should be displayed")
    public void theErrorMessageShouldBeDisplayed(DataTable table) {
        assertErrorMessage(table);
    }

    private void enterCredentials(DataTable table) {
        List<Map<String, String>> credentials = table.asMaps(String.class, String.class);
        for (Map<String, String> row : credentials) {
            String username = row.get("username");
            String password = row.get("password");

            actions.enterUsername(username);
            actions.enterPassword(password);
        }
    }

    private void assertErrorMessage(DataTable table) {
        List<Map<String, String>> credentials = table.asMaps(String.class, String.class);
        for (Map<String, String> row : credentials) {
            String errorMsgExpected = row.get("error");
            String errorMessageActual = loginPage.getErrorMessageElement().getText();
            assertEquals(errorMsgExpected, errorMessageActual);
        }
    }
}
