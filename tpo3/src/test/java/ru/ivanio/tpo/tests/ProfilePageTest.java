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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


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
        new WebDriverWait(driver, 10).until(d -> homePage1.goToProfileButton.isDisplayed());
        homePage1.goToProfileButton.click();
        profilePage = ProfilePage.initialize(driver);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void testPasswordChangeWrongInput(WebDriver driver) {
        profilePage.tryChangePassword(Constants.EXISTING_PASSWORD, Constants.WRONG_FORMAT_PASSWORD);
        profilePage.savePasswordButton.click();
        new WebDriverWait(driver, 10).until(d -> profilePage.alert.isDisplayed());
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

    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void testCheckboxes(WebDriver driver) {
        profilePage.checkbox.click();
        assertFalse(profilePage.checkbox.isSelected());
    }


    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void testChangingSurnameWithNewSurname(WebDriver driver) {
        profilePage.surnameFieldDelete.click();
        profilePage.surnameField.sendKeys(Constants.NEW_SURNAME);
        profilePage.saveNewSurnameButton.click();
        new WebDriverWait(driver, 10).until(d -> profilePage.alertAfterUpdate.isDisplayed());
        assertEquals(profilePage.alertAfterUpdate.getText().trim(), "Данные профиля успешно сохранены!");
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void testChangingSurnameWithOldSurname(WebDriver driver) {
        profilePage.surnameFieldDelete.click();
        profilePage.surnameField.sendKeys(Constants.NEW_SURNAME);
        profilePage.saveNewSurnameButton.click();
        new WebDriverWait(driver, 10).until(d -> profilePage.alertAfterUpdate.isDisplayed());
        assertEquals(profilePage.alertAfterUpdate.getText().trim(), "Данные профиля успешно сохранены!");

    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void testChangingFieldsEmpty(WebDriver driver) {
        profilePage.click(profilePage.surnameButton);
        profilePage.click(profilePage.surnameUserInfoButton);
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
