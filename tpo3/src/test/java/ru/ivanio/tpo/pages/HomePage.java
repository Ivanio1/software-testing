package ru.ivanio.tpo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


/**
 * Sample page
 */
public class HomePage extends Page {
    @FindBy(how = How.XPATH, using = "//*[@class=\"user-profile-header__sign-in r-btn r-btn_flat r-btn_large\"]")
    public WebElement signInButton;
    @FindBy(how = How.XPATH, using = "//*[@class=\"r-btn r-btn_large grey-lighter\"]")
    public WebElement registerButton;
    @FindBy(how = How.XPATH, using = "//input[@value='']")
    public WebElement findVacanciesInput;

    @FindBy(how = How.XPATH, using = "//*[@class=\"vacancy-search-page__title-hint\"]/span")
    public WebElement notFoundErrorText;
    @FindBy(how = How.XPATH, using = "//button[contains(.,'Радиус')]")
    public WebElement radiusButton;
    @FindBy(how = How.XPATH, using = "//*[@class=\"metro-select-placeholder__content\"]/h3")
    public WebElement selectMetroStationText;
    @FindBy(how = How.XPATH, using = "//*[@class=\"metro-select-placeholder__content\"]/p")
    public WebElement notSelectedTextHolder;

    @FindBy(how = How.XPATH, using = "//*[@class=\"list__tile__sub-title r-suggester-sub-title-mark\"]")
    public WebElement firstSuggestedVariantText;
    @FindBy(how = How.XPATH, using = "//*[@class=\"list__tile__sub-title r-suggester-sub-title-mark\"]/mark")
    public WebElement firstSuggestedVariantMark;

    @FindBy(how = How.XPATH, using = "//*[@class=\"vacancy-search-form__filters r-btn r-btn_link r-btn_medium\"]")
    public WebElement filters;
    @FindBy(how = How.XPATH, using = "//label[contains(.,'Для людей с инвалидностью')]")
    public WebElement invalidFilter;
    @FindBy(how = How.XPATH, using = "//*[@class=\"layout desktop-filter-main__controls\"]/div/*[@class=\"r-btn r-btn_large primary\"]")
    public WebElement filterSubmit;
    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[2]/div/div[13]/div[2]/div[2]/div[6]/header/div[1]/div[3]/div[3]/div/div/div/div/div/div/div")
    public WebElement profileButton;

    @FindBy(how = How.XPATH, using = "//*[@class=\"user-profile-menu__user-no-avatar\"]")
    public WebElement profileDiv;

    @FindBy(how = How.XPATH, using = "//*[@class=\"user-profile-menu__header\"]/a")
    public WebElement goToProfileButton;

    @FindBy(how = How.XPATH, using = "//*[@class=\"user-profile-menu__upload-resume\"]/div/button")
    public WebElement uploadResumeButton;
    @FindBy(how = How.XPATH, using = "//*[@class=\"resume-dragndrop-dialog__title\"]")
    public WebElement uploadResumeButtonTitle;


    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public static HomePage initialize(WebDriver driver) {
        return Page.initialize(driver, "//*[@class=\"user-profile-header__sign-in r-btn r-btn_flat r-btn_large\"]", HomePage.class);
    }

    public void findVacancy(String vacancy) {
        findVacanciesInput.click();
        findVacanciesInput.sendKeys(vacancy);
        findVacanciesInput.sendKeys(Keys.ENTER);
    }

    public void enterVacancy(String vacancy) {
        findVacanciesInput.click();
        findVacanciesInput.sendKeys(vacancy);
        findVacanciesInput.click();
    }

    public void filterForInvalids() {
        filters.click();
        invalidFilter.click();
        filterSubmit.click();
    }

    public void goToSignIn() {
        WebElement element = driver.findElement(By.xpath("//*[@class=\"user-profile-header__sign-in r-btn r-btn_flat r-btn_large\"]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void goToCompanies() {
        WebElement element = driver.findElement(By.xpath("//a[contains(.,'Компании')]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void goToProfessions() {
        WebElement element = driver.findElement(By.xpath("//a[contains(.,'Профессии')]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void goToRegister() {
        WebElement element = driver.findElement(By.xpath("//*[@class=\"r-btn r-btn_large grey-lighter\"]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void goToVacancies() {
        WebElement element = driver.findElement(By.xpath("//a[contains(.,'Вакансии')]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void goToGeolocation() {
        WebElement element = driver.findElement(By.xpath("//*[@class=\"r-region-select__activator r-btn r-btn_flat r-btn_large\"]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void openProfileMenu() {
        //user-profile-menu__user-no-avatar
        WebElement element = driver.findElement(By.xpath("//*[@class=\"user-profile-menu__user-no-avatar\"]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void goToProfile() {
        WebElement element = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[8]/div/ul/a[1]/li/a/div/div[2]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

}