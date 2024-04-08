package ru.ivanio.tpo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SignInPage extends Page {
    @FindBy(how = How.XPATH, using = "//input[@name=\"login\"]")
    public WebElement emailField;

    @FindBy(how = How.XPATH, using = "//*[@class=\"auth-main-step__form-button r-btn r-btn_full-width r-btn_large primary\"]")
    public WebElement submitButton;
    @FindBy(how = How.XPATH, using = "//*[@class=\"auth-main-step__error auth-main-step__error_show\"]")
    public WebElement alert;

    public SignInPage(WebDriver webDriver) {
        super(webDriver);
    }

    public static SignInPage initialize(WebDriver driver) {
        return Page.initialize(driver, "//*[@class=\"auth-main-step__form-button r-btn r-btn_full-width r-btn_large primary\"]", SignInPage.class);
    }

    public void tryLogin(String email) {
        emailField.sendKeys(email);
        submitButton.click();
    }

}