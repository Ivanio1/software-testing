package ru.ivanio.tpo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

//    @FindBy(how = How.XPATH, using = "//input[@placeholder='Должность или ключевые слова']")
//    public WebElement findVacanciesInput;

    @FindBy(how = How.XPATH, using = "//*[@class=\"list__tile__sub-title r-suggester-sub-title-mark\"]")
    public WebElement firstSuggestedVariantText;
    @FindBy(how = How.XPATH, using = "//*[@class=\"list__tile__sub-title r-suggester-sub-title-mark\"]/mark")
    public WebElement firstSuggestedVariantMark;
//*[@class="list__tile__sub-title r-suggester-sub-title-mark"]

//
//    @FindBy(how = How.XPATH, using = "//li[2]/a")
//    public WebElement enteredVacancyName;
    @FindBy(how = How.XPATH, using = "//*[@class=\"user-profile-menu__user-no-avatar\"]")
    public WebElement profileDiv;
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

    public void goToVacancies() {
        WebElement element = driver.findElement(By.xpath("//a[contains(.,'Вакансии')]"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void goToGeolocation() {
        WebElement element = driver.findElement(By.xpath("//*[@class=\"r-region-select__activator r-btn r-btn_flat r-btn_large\"]"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }
}