package ru.ivanio.tpo.tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import ru.ivanio.tpo.Constants;
import ru.ivanio.tpo.pages.*;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterPageTest extends PageTestBase {
    private RegisterPage registerPage;


    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void emptyFields(WebDriver driver) {
        registerPage.tryRegister(Constants.EMPTY_FIELD, Constants.EMPTY_FIELD, Constants.EMPTY_FIELD, Constants.EMPTY_FIELD);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        assertEquals("Обязательное поле", registerPage.alert.getText().trim());
    }
    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void nonCorrectName(WebDriver driver) {
        registerPage.tryRegister(Constants.EXISTING_EMAIL, Constants.WRONG_NAME, Constants.CORRECT_YEAR, Constants.CORRECT_WORK);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        assertEquals("Укажите имя и фамилию через пробел", registerPage.alert.getText().trim());
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void nonCorrectYear(WebDriver driver) {
        registerPage.tryRegister(Constants.EXISTING_EMAIL, Constants.CORRECT_NAME, Constants.WRONG_YEAR, Constants.CORRECT_WORK);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        assertEquals("Неверно указан год", registerPage.alert.getText().trim());
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void youngYear(WebDriver driver) {
        registerPage.tryRegister(Constants.EXISTING_EMAIL, Constants.CORRECT_NAME, Constants.YOUNG_YEAR, Constants.CORRECT_WORK);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        assertEquals("Вам должно быть больше 14 лет", registerPage.alert.getText().trim());
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void existingEmail(WebDriver driver) {
        registerPage.tryRegister(Constants.EXISTING_EMAIL, Constants.CORRECT_NAME, Constants.CORRECT_YEAR, Constants.CORRECT_WORK);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        AlreadyExistsUserPage alreadyExistsUserPage = AlreadyExistsUserPage.initialize(driver);
        assertEquals("Пользователь с таким email уже существует", alreadyExistsUserPage.alert.getText().trim());
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void goToSocialNetworkPage(WebDriver driver) {
        registerPage.goToSocialNetworkPage();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        SocialNetworkPage socialNetworkPage = SocialNetworkPage.initialize(driver);
        assertEquals("VK ID", socialNetworkPage.getTitle());
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void goToSignInPage(WebDriver driver) {
        registerPage.goToSignInPagePage();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        SignInPage signInPage = SignInPage.initialize(driver);
        assertEquals("Работа в Санкт-Петербурге, вакансии и резюме, поиск работы на Rabota.ru", signInPage.getTitle());
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void goToHomePage(WebDriver driver) {
        registerPage.goToHomePage();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        HomePage homePage = HomePage.initialize(driver);
        assertEquals("Работа в Санкт-Петербурге, вакансии и резюме, поиск работы на Rabota.ru", homePage.getTitle());
    }

    @Override
    protected void preparePages(WebDriver driver) {
        HomePage homePage = HomePage.initialize(driver);
        homePage.acceptCookies();
        homePage.goToRegister();
        registerPage = RegisterPage.initialize(driver);
    }
}
