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

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[2]/div/div[13]/div[2]/div[2]/div[6]/header/div[1]/div[3]/div[3]/div/div/div/div/div/div/div")
    public WebElement profileButton;

    @FindBy(how = How.XPATH, using = "//*[@class=\"user-profile-menu__user-no-avatar\"]")
    public WebElement profileDiv;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[2]/div/div[8]/div/ul/a[1]/li/a/div/div[2]")
    public WebElement goToProfileButton;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public static HomePage initialize(WebDriver driver) {
        return Page.initialize(driver, "//*[@class=\"user-profile-header__sign-in r-btn r-btn_flat r-btn_large\"]", HomePage.class);
    }

    public void goToSignIn() {
        WebElement element = driver.findElement(By.xpath("//*[@class=\"user-profile-header__sign-in r-btn r-btn_flat r-btn_large\"]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void goToRegister() {
        WebElement element = driver.findElement(By.xpath("//*[@class=\"r-btn r-btn_large grey-lighter\"]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void goToGeolocation() {
        WebElement element = driver.findElement(By.xpath("//*[@class=\"r-region-select__activator r-btn r-btn_flat r-btn_large\"]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void openProfileMenu(){
        driver.findElement(By.cssSelector(".user-profile-menu__user-no-avatar")).click();
    }

    public void goToProfile(){
        goToProfileButton.click();
    }
    public static class ProfileMenuPage extends Page{

        @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[2]/div/div[8]/div/ul/a[1]/li/a/div/div[2]")
        public WebElement goToProfileButton;
        public ProfileMenuPage(WebDriver driver) {
            super(driver);
        }

        public static HomePage initialize(WebDriver driver) {
            return Page.initialize(driver, "/html/body/div[1]/div[2]/div/div[8]/div/ul/a[1]/li/a/div/div[2]", HomePage.class);
        }


    }
}