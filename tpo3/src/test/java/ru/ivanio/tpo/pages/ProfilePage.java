package ru.ivanio.tpo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ivanio.tpo.Constants;

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

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[2]/div/div[13]/div[2]/div[2]/div[6]/div[1]/div/div/div[2]/div[2]/div[2]/div[1]/button")
    public WebElement addEducationInfoButton;
    @FindBy(how = How.XPATH, using = "//div[@id=\'app\']/div[13]/div[2]/div[2]/div[6]/div/div/div/div[2]/div[2]/div[2]/div/div[2]/div[2]/div[2]")
    public WebElement specialization;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[2]/div/div[13]/div[2]/div[2]/div[6]/div[1]/div/div/div[2]/div[2]/div[2]/div[1]/div[3]/button")
    public WebElement editEducationButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"last_name\"]")
    public WebElement surnameField;
    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[2]/div/div[13]/div[2]/div[2]/div[6]/div[1]/div/div/div[2]/div[1]/div[2]/div[2]/div[2]/div/div[1]/button")
    public WebElement surnameFieldDelete;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[2]/div/div[13]/div[2]/div[2]/div[6]/div[1]/div/div/div[2]/div[1]/div[2]/div[13]/div/button[1]")
    public WebElement saveNewSurnameButton;

    @FindBy(how = How.XPATH, using = "//div[@class=\"messages-pool__item-message\"]")
    public WebElement alertAfterUpdate;

    public static ProfilePage initialize(WebDriver driver) {
        return Page.initialize(driver, "//button[@class=\"r-btn r-btn_disabled r-btn_medium secondary\"]", ProfilePage.class);
    }

    public void openEducationChangePage() {
        WebElement element = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[13]/div[2]/div[2]/div[6]/div[1]/div/div/div[2]/div[2]/div[2]/div[1]/button"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
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

    public void clickSavePasswordButton() {
        WebElement element = driver.findElement(By.xpath("//div[@id=\'app\']/div[13]/div[2]/div[2]/div[6]/div/div/div/div[2]/div[2]/div[2]/div[5]/div/div/div[4]/div[2]/button"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void goToHomePage() {
        WebElement element = driver.findElement(By.xpath("//*[@class=\"page-header__item hidden-md-and-down\"]/a"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public static class AddEducationPage extends Page {
        @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[2]/div/div[14]/div[2]/div[2]/div[6]/div[1]/div/div/div[3]/div[2]/div/div[3]/div/button[1]")
        public WebElement saveUserInfoButton;

        @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[2]/div/div[14]/div[2]/div[2]/div[6]/div[1]/div/div/div[3]/div[2]/div/div[2]/div[2]/div[1]/div[2]/div/div/div[1]/div[1]")
        public WebElement educationLevelList;

        @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[2]/div/div[1]/div/ul/li[1]/a/div/div")
        public WebElement educationLevel;

        @FindBy(how = How.XPATH, using = "//div[@class=\"input-group input-group_required input-group_clearable input-group_text-field\"]/div[@class=\"input-group__input\"]/input[@id=\"name\"]")
        public WebElement nameEducation;

        @FindBy(how = How.XPATH, using = "//*[@id=\"speciality\"]")
        public WebElement speciality;

        @FindBy(how = How.XPATH, using = "//*[@id=\"finished_at\"]")
        public WebElement year;

        @FindBy(how = How.XPATH, using = "//div[@id=\'app\']/div[14]/div[2]/div[2]/div[6]/div/div/div/div[3]/div[2]/div/div[2]/div[2]/div[4]/div[2]/div/div[2]/div")
        public WebElement yearAlert;

        @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[2]/div/div[14]/div[2]/div[2]/div[6]/div[1]/div/div/div[3]/div[2]/div/div[3]/div[2]/button")
        public WebElement deleteButton;

        public AddEducationPage(WebDriver driver) {
            super(driver);
        }

        public static AddEducationPage initialize(WebDriver driver) {
            return Page.initialize(driver, "/html/body/div[1]/div[2]/div/div[14]/div[2]/div[2]/div[6]/div[1]/div/div/div[3]/div[2]/div/div[3]/div/button[1]", AddEducationPage.class);
        }

        public void addEducation(String nameEd, String spec, String yearEd) {
            nameEducation.sendKeys(nameEd);
            speciality.sendKeys(spec);
            year.sendKeys(yearEd);
            educationLevelList.click();
            new WebDriverWait(driver, 10).until(d -> educationLevel.isDisplayed());
            educationLevel.click();
            saveUserInfoButton.click();
        }
    }

    public void click(WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", webElement);
    }

}
