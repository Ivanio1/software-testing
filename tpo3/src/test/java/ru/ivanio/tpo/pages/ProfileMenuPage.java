package ru.ivanio.tpo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProfileMenuPage extends Page {

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[2]/div/div[31]/div/ul/a[1]/li/a/div")
    public WebElement goToProfileButton;

    public ProfileMenuPage(WebDriver driver) {
        super(driver);
    }

    public static ProfileMenuPage initialize(WebDriver driver) {
        return Page.initialize(driver, "/html/body/div[1]/div[2]/div/div[31]/div/ul/a[1]/li/a/div", ProfileMenuPage.class);
    }


}