package ru.ivanio.tpo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * Sample page
 */
public class HomePage extends Page {
    @FindBy(how = How.XPATH, using = "//*[@data-qa=\"login\"]")
    public WebElement signInLink;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public static HomePage initialize(WebDriver driver) {
        return Page.initialize(driver, "//*[@data-qa=\"login\"]", HomePage.class);
    }

    public void goToSignIn() {
        signInLink.click();
    }
}