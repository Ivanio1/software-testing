package ru.ivanio.tpo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AlreadyExistsUserPage extends Page {
    public AlreadyExistsUserPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.XPATH, using = "//div[@class=\"split-wrapper__title\"]/h1/span")
    public WebElement alert;
    @FindBy(how = How.XPATH, using = "//input[@aria-label=\"Код\"]")
    public WebElement codeField;

    public static AlreadyExistsUserPage initialize(WebDriver driver) {
        return Page.initialize(driver, "//*[@aria-label=\"Код\"]", AlreadyExistsUserPage.class);
    }
}
