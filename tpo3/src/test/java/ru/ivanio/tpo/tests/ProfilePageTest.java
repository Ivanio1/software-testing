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

//    @ParameterizedTest(name = "{0}")
//    @MethodSource("allDrivers")
//    public void testPasswordChangeWithWrongOldPassword(WebDriver driver) {
//        profilePage.tryChangePassword(Constants.WRONG_FORMAT_PASSWORD, Constants.EXISTING_PASSWORD);
//        profilePage.savePasswordButton.click();
//        new WebDriverWait(driver, 10).until(d -> profilePage.alertOldPassword.isDisplayed());
//        Assert.assertEquals(profilePage.alertOldPassword.getText().trim(), "Текущий пароль не верен");
//    }


    ///тест нормальной смены пароля, с изменением constants.Existing_psswd

    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void testCheckboxes(WebDriver driver) {
        profilePage.checkbox.click();
        assertFalse(profilePage.checkbox.isSelected());
    }


//    @ParameterizedTest(name = "{0}")
//    @MethodSource("allDrivers")
//    public void testChangingFields(WebDriver driver) {
//        profilePage.surnameField.sendKeys(Constants.NEW_SURNAME);
//        assertFalse(profilePage.checkbox.isSelected());
//    }
//
//    @ParameterizedTest(name = "{0}")
//    @MethodSource("allDrivers")
//    public void testChangingFields(WebDriver driver) {
//        profilePage.surnameField.sendKeys(Constants.OLD_SURNAME);
//        assertFalse(profilePage.checkbox.isSelected());
//    }

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
    public void testCheckAddingInformation(WebDriver driver) {
        profilePage.addInfoAboutUser.click();
        ProfilePage.AddDiplomaPage.initialize(driver);


    }


}
