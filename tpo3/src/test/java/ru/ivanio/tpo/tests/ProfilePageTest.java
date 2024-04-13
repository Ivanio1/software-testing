package ru.ivanio.tpo.tests;

import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ivanio.tpo.Constants;
import ru.ivanio.tpo.pages.HomePage;
import ru.ivanio.tpo.pages.ProfilePage;
import ru.ivanio.tpo.pages.SignInPage;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;


public class ProfilePageTest extends PageTestBase {
    private ProfilePage profilePage;

    @Override
    protected void preparePages(WebDriver driver) {
        HomePage homePage = HomePage.initialize(driver);
        homePage.acceptCookies();
        homePage.goToSignIn();
        SignInPage signInPage = SignInPage.initialize(driver);
        signInPage.tryLogin(Constants.EXISTING_EMAIL);
        signInPage.sendPassword(Constants.EXISTING_PASSWORD);
        HomePage homePage1 = PageFactory.initElements(driver, HomePage.class);
        homePage1.openProfileMenu();
        HomePage.ProfileMenuPage.initialize(driver);
        homePage1.goToProfile();
        profilePage = ProfilePage.initialize(driver);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void testPasswordChangeWrongInput(WebDriver driver) {
        profilePage.tryChangePassword(Constants.EXISTING_PASSWORD, Constants.WRONG_FORMAT_PASSWORD);
        profilePage.savePasswordButton.click();
        driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
        Assert.assertEquals(profilePage.alert.getText().trim(), "Пароль должен быть не менее 6 символов, включать цифры, строчные и заглавные латинские буквы");
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void testPasswordChangeWithWrongOldPassword(WebDriver driver) {
        profilePage.tryChangePassword(Constants.WRONG_FORMAT_PASSWORD, Constants.EXISTING_PASSWORD);
        profilePage.savePasswordButton.click();
        new WebDriverWait(driver, 10).until(d -> profilePage.alertOldPassword.isDisplayed());
        Assert.assertEquals(profilePage.alertOldPassword.getText().trim(), "Текущий пароль не верен");
    }

//    @ParameterizedTest(name = "{0}")
//    @MethodSource("allDrivers")
//    public void testPasswordChange(WebDriver driver) {
//        Random random = new Random();
//        int num= random.nextInt(10);
//        String newPassword = Constants.EXISTING_PASSWORD.substring(1, Constants.EXISTING_PASSWORD.length() - 1)+ num;
//        profilePage.tryChangePassword(Constants.EXISTING_PASSWORD, newPassword);
//        profilePage.savePasswordButton.click();
//        new WebDriverWait(driver, 10).until(d -> profilePage.alertOldPassword.isDisplayed());
//        Constants.EXISTING_PASSWORD = newPassword;
//    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void testCheckboxes(WebDriver driver) {
        profilePage.checkbox.click();
        assertFalse(profilePage.checkbox.isSelected());
    }


//    @ParameterizedTest(name = "{0}")
//    @MethodSource("allDrivers")
//    public void testChangingSurnameWithNewSurname(WebDriver driver) {
//        profilePage.surnameField.sendKeys(Constants.NEW_SURNAME);
//        profilePage.saveNewSurnameButton.click();
//        assertEquals(profilePage.alertAfterUpdate.getText().trim(),"Данные профиля успешно сохранены!");
//    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void testChangingSurnameWithOldSurname(WebDriver driver) {
        profilePage.surnameField.sendKeys(Constants.OLD_SURNAME);
        profilePage.saveNewSurnameButton.click();
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void testChangingFieldsEmpty(WebDriver driver) {
        profilePage.surnameButton.click();
        profilePage.surnameUserInfoButton.click();
        new WebDriverWait(driver, 10).until(d -> profilePage.surnameAlert.isDisplayed());
        assertEquals("Заполните поле", profilePage.surnameAlert.getText().trim());
    }


    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void testCheckAddingInformationWithEmpty(WebDriver driver) {
        profilePage.openEducationChangePage();
        ProfilePage.AddEducationPage addEducationPage = ProfilePage.AddEducationPage.initialize(driver);
        addEducationPage.addEducation(Constants.UNI_NAME, Constants.SPEC_NAME, Constants.EMPTY_FIELD);
        new WebDriverWait(driver, 10).until(d -> addEducationPage.yearAlert.isDisplayed());
        assertEquals(addEducationPage.yearAlert.getText().trim(), "Укажите год окончания");
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void testCheckAddingInformationWithNotCorrectField(WebDriver driver) {
        profilePage.openEducationChangePage();
        ProfilePage.AddEducationPage addEducationPage = ProfilePage.AddEducationPage.initialize(driver);
        addEducationPage.addEducation(Constants.UNI_NAME, Constants.SPEC_NAME, Constants.UNCORRECT_YEAR);
        new WebDriverWait(driver, 10).until(d -> addEducationPage.yearAlert.isDisplayed());
        assertEquals(addEducationPage.yearAlert.getText().trim(), "Период указан неверно");
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void testCheckAddingInformationWithCorrectField(WebDriver driver) {
        profilePage.openEducationChangePage();
        ProfilePage.AddEducationPage addEducationPage = ProfilePage.AddEducationPage.initialize(driver);
        addEducationPage.addEducation(Constants.UNI_NAME, Constants.SPEC_NAME, Constants.CORRECT_YEAR);
        assertEquals(profilePage.specialization.getText().trim(), "ИВТ");
        profilePage.editEducationButton.click();
        ProfilePage.AddEducationPage addEducationPage1 = ProfilePage.AddEducationPage.initialize(driver);
        new WebDriverWait(driver, 10).until(d -> addEducationPage1.deleteButton.isDisplayed());
        addEducationPage1.deleteButton.click();
    }


}
