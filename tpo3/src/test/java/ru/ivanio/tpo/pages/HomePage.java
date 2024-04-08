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
    @FindBy(how = How.XPATH, using = "//*[@class=\"user-profile-header__sign-in r-btn r-btn_flat r-btn_large\"]")
    public WebElement signInButton;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public static HomePage initialize(WebDriver driver) {
        return Page.initialize(driver, "//*[@class=\"user-profile-header__sign-in r-btn r-btn_flat r-btn_large\"]", HomePage.class);
    }

    public void goToSignIn() {
        signInButton.click();
    }
}