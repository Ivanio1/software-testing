package ru.ivanio.tpo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.ivanio.tpo.Constants;

import java.util.concurrent.TimeUnit;

public class ProfilePage extends Page {

    @FindBy(how = How.XPATH, using = "//button[@class=\"r-btn r-btn_medium primary\"]")
    public WebElement submitButton;

    @FindBy(how = How.XPATH, using = "//div[@id=\'app\']/div[13]/div[2]/div[2]/div[6]/div/div/div/div[2]/div[2]/div[2]/div[5]/div/div/div[4]/div[2]/button")
    public WebElement savePasswordButton;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[2]/div/div[13]/div[2]/div[2]/div[6]/div[1]/div/div/div[2]/div[2]/div[2]/div[5]/div/div[1]/div[1]/div[2]/div/div[1]/input")
    public WebElement oldPasswordField;
    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[2]/div/div[13]/div[2]/div[2]/div[6]/div[1]/div/div/div[2]/div[2]/div[2]/div[5]/div/div[1]/div[2]/div[2]/div/div[1]/input")
    public WebElement newPasswordField;
    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[2]/div/div[13]/div[2]/div[2]/div[6]/div[1]/div/div/div[2]/div[2]/div[2]/div[5]/div/div[1]/div[3]/div[2]/div/div[1]/input")
    public WebElement passwordApproveField;

    @FindBy(how = How.XPATH, using = "//div[@id=\'app\']/div[13]/div[2]/div[2]/div[6]/div/div/div/div[2]/div[2]/div[2]/div[5]/div/div/div[2]/div[2]/div/div[2]/div")
    public WebElement alert;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[2]/div/div[13]/div[2]/div[2]/div[6]/div[1]/div/div/div[2]/div[2]/div[2]/div[5]/div/div[1]/div[1]/div[2]/div/div[2]/div")
    public WebElement alertOldPassword;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[2]/div/div[13]/div[2]/div[2]/div[6]/div[1]/div/div/div[2]/div[2]/div[2]/div[6]/div[1]/div/div[1]/span")
    public WebElement checkbox;

    @FindBy(how = How.XPATH, using = "//button[@class=\"language-list__btn r-btn r-btn_flat r-btn_link r-btn_medium text_secondary\"]")
    public WebElement addInfoAboutUser;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[2]/div/div[13]/div[2]/div[2]/div[6]/div[1]/div/div/div[2]/div[1]/div[2]/div[2]/div[2]/div/div[1]/button")
    public WebElement surnameButton;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[2]/div/div[13]/div[2]/div[2]/div[6]/div[1]/div/div/div[2]/div[1]/div[2]/div[13]/div/button[1]")
    public WebElement surnameUserInfoButton;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[2]/div/div[13]/div[2]/div[2]/div[6]/div[1]/div/div/div[2]/div[1]/div[2]/div[2]/div[2]/div/div[2]/div")
    public WebElement surnameAlert;



    public static ProfilePage initialize(WebDriver driver) {
        return Page.initialize(driver, "//button[@class=\"r-btn r-btn_disabled r-btn_medium secondary\"]", ProfilePage.class);
    }

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public void tryChangePassword(String oldPassword, String newPassword) {
        oldPasswordField.click();
        oldPasswordField.sendKeys(oldPassword);
        newPasswordField.click();
        newPasswordField.sendKeys(newPassword);
        passwordApproveField.click();
        passwordApproveField.sendKeys(newPassword);
    }

    public void clickSavePasswordButton(){
        WebElement element = driver.findElement(By.xpath("//div[@id=\'app\']/div[13]/div[2]/div[2]/div[6]/div/div/div/div[2]/div[2]/div[2]/div[5]/div/div/div[4]/div[2]/button"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public static class AddDiplomaPage extends Page {
        @FindBy(how = How.XPATH, using = "//button[@class=\"profile-sidebar-bottom-sheet__button r-btn r-btn_medium primary\"]")
        public WebElement saveUserInfo;

        @FindBy(how = How.XPATH, using = "//*[@id=\"certificate.name\"]")
        public WebElement name;

        @FindBy(how = How.XPATH, using = "//*[@id=\"certificate.year\"]")
        public WebElement year;

        @FindBy(how = How.XPATH, using = "//*[@id=\"certificate.document\"]")
        public WebElement link;



        public AddDiplomaPage(WebDriver driver) {
            super(driver);
        }
        public static AddDiplomaPage initialize(WebDriver driver) {
            return Page.initialize(driver, "//button[@class=\"profile-sidebar-bottom-sheet__button r-btn r-btn_medium primary\"]", AddDiplomaPage.class);
        }

        public void addDiploma(){
            name.sendKeys(Constants.DIPLOMA_NAME);
            year.sendKeys(Constants.DIPLOMA_YEAR);
            link.sendKeys(Constants.DIPLOMA_LINK);
        }
    }


}
