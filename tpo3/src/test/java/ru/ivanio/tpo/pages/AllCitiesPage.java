package ru.ivanio.tpo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AllCitiesPage extends Page {


    public AllCitiesPage(WebDriver driver) {
        super(driver);
    }

    public static AllCitiesPage initialize(WebDriver driver) {
        return Page.initialize(driver, "//*[@class=\"region-page\"]", AllCitiesPage.class);
    }

    public void clickCityButton(String city) {
        var button = driver.findElement(By.xpath("//a[contains(text(),'" + city + "')]"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", button);

    }
}
