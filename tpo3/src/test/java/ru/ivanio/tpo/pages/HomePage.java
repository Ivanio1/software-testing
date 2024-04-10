package ru.ivanio.tpo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
    @FindBy(how = How.XPATH, using = "//*[@class=\"r-btn r-btn_large grey-lighter\"]")
    public WebElement registerButton;

    @FindBy(how = How.XPATH, using = "//*[@class=\"user-profile-menu__user-no-avatar\"]")
    public WebElement profileDiv;
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public static HomePage initialize(WebDriver driver) {
        return Page.initialize(driver, "//*[@class=\"user-profile-header__sign-in r-btn r-btn_flat r-btn_large\"]", HomePage.class);
    }

    public void goToSignIn() {
        WebElement element = driver.findElement(By.xpath("//*[@class=\"user-profile-header__sign-in r-btn r-btn_flat r-btn_large\"]"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }
    public void goToRegister() {
        WebElement element = driver.findElement(By.xpath("//*[@class=\"r-btn r-btn_large grey-lighter\"]"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }
}