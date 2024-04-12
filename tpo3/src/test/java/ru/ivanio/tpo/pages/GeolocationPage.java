package ru.ivanio.tpo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class GeolocationPage extends Page {
    @FindBy(how = How.XPATH, using = "//button[@class=\"r-btn r-btn_medium primary\"]")
    public WebElement allCitiesButton;


    public static GeolocationPage initialize(WebDriver driver) {
        return Page.initialize(driver, "//*[@class=\"r-region-select__activator r-btn r-btn_flat r-btn_large\"]", GeolocationPage.class);
    }
    public GeolocationPage(WebDriver driver) {
        super(driver);
    }

    public void goToAllCities() {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", allCitiesButton);
    }

    public void goToHomePage() {
        WebElement element = driver.findElement(By.xpath("//*[@class=\"split-wrapper__back hidden-sm-and-down r-btn r-btn_small\"]"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }
    public void goToSocialNetworkPage() {
        WebElement element = driver.findElement(By.xpath("//*[@class=\"r-btn r-btn_icon r-btn_medium social-button social-button_original-icon social-button_type_vk\"]"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void goToSignInPagePage() {
        WebElement element = driver.findElement(By.xpath("//li[@class=\"register-form__login-text\"]/a"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }
}
