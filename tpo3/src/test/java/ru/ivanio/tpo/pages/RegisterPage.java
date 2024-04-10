package ru.ivanio.tpo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegisterPage extends Page {
    public RegisterPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(how = How.XPATH, using = "//input[@name=\"login\"]")
    public WebElement emailField;
    @FindBy(how = How.XPATH, using = "//input[@name=\"full_name\"]")
    public WebElement nameField;
    @FindBy(how = How.XPATH, using = "//input[@aria-label=\"Желаемая должность\"]")
    public WebElement workField;
    @FindBy(how = How.XPATH, using = "//input[@name=\"birth_year_at\"]")
    public WebElement yearField;
    @FindBy(how = How.XPATH, using = "//*[@class=\"r-btn r-btn_large secondary register-form__submit\"]")
    public WebElement submitButton;

    @FindBy(how = How.XPATH, using = "//*[@class=\"split-wrapper__back hidden-sm-and-down r-btn r-btn_small\"]")
    public WebElement homeButton;

    @FindBy(how = How.XPATH, using = "//li[@class=\"register-form__login-text\"]/a")
    public WebElement signInButton;

    @FindBy(how = How.XPATH, using = "//*[@class=\"input-group__messages input-group__error\"]")
    public WebElement alert;
    @FindBy(how = How.XPATH, using = "//*[@class=\"r-btn r-btn_icon r-btn_medium social-button social-button_original-icon social-button_type_vk\"]")
    public WebElement socialNetworkButton;
    public static RegisterPage initialize(WebDriver driver) {
        return Page.initialize(driver, "//*[@class=\"r-btn r-btn_icon r-btn_medium social-button social-button_original-icon social-button_type_vk\"]", RegisterPage.class);
    }

    public void tryRegister(String email, String name, String year, String work) {
        emailField.sendKeys(email);
        yearField.sendKeys(year);
        workField.sendKeys(work);
        nameField.sendKeys(name);
        WebElement element = driver.findElement(By.xpath("//*[@class=\"r-btn r-btn_large secondary register-form__submit\"]"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
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
