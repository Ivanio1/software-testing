package ru.ivanio.tpo.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProfessionPage extends Page {
    public ProfessionPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(how = How.XPATH, using = "//*[@class=\"career-search-header__title\"]")
    public WebElement title;

    @FindBy(how = How.XPATH, using = "//h4[@class=\"career-search-profession-card__title\"]")
    public WebElement jobCard;

    @FindBy(how = How.XPATH, using = "//*[@input=\"career-search-header__title\"]")
    public WebElement searchField;

    @FindBy(how = How.XPATH, using = "//button[@aria-label='Найти']")
    public WebElement submitFilterButton;

    @FindBy(how = How.XPATH, using = "//input[@placeholder='Введите профессию или отрасль']")
    public WebElement jobFilter;

    public void findJob(String job) {
        jobFilter.sendKeys(job);
        jobFilter.sendKeys(Keys.ENTER);
        click(submitFilterButton);
    }

    public static ProfessionPage initialize(WebDriver driver) {
        return Page.initialize(driver, "//*[@class=\"career-search-header__title\"]", ProfessionPage.class);
    }

    private void click(WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", webElement);
    }

}
