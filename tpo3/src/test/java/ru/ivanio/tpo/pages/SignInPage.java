package ru.ivanio.tpo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SignInPage extends Page {
    @FindBy(how = How.XPATH, using = "//input[@name=\"login\"]")
    public WebElement emailField;

    @FindBy(how = How.XPATH, using = "//input[@name=\"password\"]")
    public WebElement passwordField;

    @FindBy(how = How.XPATH, using = "//*[@class=\"auth-main-step__form-button r-btn r-btn_full-width r-btn_large primary\"]")
    public WebElement submitButton;
    @FindBy(how = How.XPATH, using = "//*[@class=\"auth-main-step__error auth-main-step__error_show\"]")
    public WebElement alert;

    @FindBy(how = How.XPATH, using = "//*[@class=\"auth-authorization-step__error\"]")
    public WebElement alertOnPassword;

    @FindBy(how = How.XPATH, using = "//*[@class=\"r-btn r-btn_icon r-btn_medium social-button social-button_original-icon social-button_type_vk\"]")
    public WebElement socialNetworkButton;


    public SignInPage(WebDriver webDriver) {
        super(webDriver);
    }

    public static SignInPage initialize(WebDriver driver) {
        return Page.initialize(driver, "//*[@class=\"auth-main-step__form-button r-btn r-btn_full-width r-btn_large primary\"]", SignInPage.class);
    }

    public void tryLogin(String email) {
        emailField.sendKeys(email);
        WebElement element = driver.findElement(By.xpath("//*[@class=\"auth-main-step__form-button r-btn r-btn_full-width r-btn_large primary\"]"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }
    public void tryLoginWithSocialNetwork() {
        WebElement element = driver.findElement(By.xpath("//*[@class=\"r-btn r-btn_icon r-btn_medium social-button social-button_original-icon social-button_type_vk\"]"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }
    public void sendPassword(String password) {
        passwordField.sendKeys(password);
        WebElement element = driver.findElement(By.xpath("//*[@class=\"auth-authorization-step__form-button r-btn r-btn_full-width r-btn_large primary\"]"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }


}