package ru.ivanio.tpo.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CompaniesPage extends Page {

    @FindBy(how = How.XPATH, using = "//input[@placeholder='Название компании']")
    public WebElement companyNameFilter;
    @FindBy(how = How.XPATH, using = "//button[@aria-label='Найти']")
    public WebElement submitFilterButton;
    @FindBy(how = How.XPATH, using = "//*[@class=\"company-card\"][1]/div/h3")
    public WebElement firstSuggestionField;

    @FindBy(how = How.XPATH, using = "//*[@class=career-search-header__title]")
    public WebElement title;

    public CompaniesPage(WebDriver driver) {
        super(driver);
    }

    public static CompaniesPage initialize(WebDriver driver) {
        return Page.initialize(driver, "//*[@class=\"companies-page__title\"]", CompaniesPage.class);
    }

    public void enterCompanyName(String name) {
        companyNameFilter.sendKeys(name);
        click(submitFilterButton);
    }

    private void click(WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", webElement);
    }
}
