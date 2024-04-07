package ru.ivanio.tpo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SignInPage extends Page {
    @FindBy(how = How.XPATH, using = "//*[@data-qa=\"account-signup-email\"]")
    public WebElement emailField;

    @FindBy(how = How.XPATH, using = "//*[@data-qa=\"account-signup-submit\"]")
    public WebElement submitButton;
    @FindBy(how = How.XPATH, using = "//*[@data-qa=\"field-error-login field-error-login_BAD_LOGIN\"]")
    public WebElement alert;

    public SignInPage(WebDriver webDriver) {
        super(webDriver);
    }

    public static SignInPage initialize(WebDriver driver) {
        return Page.initialize(driver, "//*[@data-qa=\"account-signup-submit\"]", SignInPage.class);
    }

    public void tryLogin(String email) {
        emailField.sendKeys(email);
        submitButton.click();
    }

}