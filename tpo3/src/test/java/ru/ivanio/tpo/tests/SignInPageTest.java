package ru.ivanio.tpo.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.ivanio.tpo.Constants;
import ru.ivanio.tpo.pages.HomePage;
import ru.ivanio.tpo.pages.SignInPage;
import ru.stqa.selenium.factory.WebDriverPool;


public class SignInPageTest extends PageTestBase {
    private SignInPage signInPage;

    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void wrongEmail(WebDriver driver) {
        signInPage.tryLogin(Constants.WRONG_EMAIL);
        assertEquals("Пожалуйста, укажите email или телефон", signInPage.alert.getText().trim());
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void correctUserEmail(WebDriver driver) {
        signInPage.tryLogin(Constants.EXISTING_EMAIL);


        //assertEquals("Пожалуйста, укажите email или телефон");

    }


    @Override
    protected void preparePages(WebDriver driver) {
        HomePage homePage = HomePage.initialize(driver);
        homePage.acceptCookies();
        homePage.acceptRegion();
        homePage.goToSignIn();
        signInPage = SignInPage.initialize(driver);
    }
}