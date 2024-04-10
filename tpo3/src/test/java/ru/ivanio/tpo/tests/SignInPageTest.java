package ru.ivanio.tpo.tests;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.ivanio.tpo.Constants;
import ru.ivanio.tpo.pages.HomePage;
import ru.ivanio.tpo.pages.SignInPage;
import ru.ivanio.tpo.pages.SocialNetworkPage;


public class SignInPageTest extends PageTestBase {
    private SignInPage signInPage;

    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void wrongEmail(WebDriver driver) {
        signInPage.tryLogin(Constants.WRONG_EMAIL);
        assertEquals("Введите ваш телефон или email", signInPage.alert.getText().trim());
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void correctUserPassword(WebDriver driver) {
        signInPage.tryLogin(Constants.EXISTING_EMAIL);
        signInPage.sendPassword(Constants.EXISTING_PASSWORD);
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        assertDoesNotThrow(() -> {
            homePage.profileDiv.getText();
        });
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void wrongUserPassword(WebDriver driver) {
        signInPage.tryLogin(Constants.EXISTING_EMAIL);
        signInPage.sendPassword(Constants.NON_EXISTING_PASSWORD);
        assertEquals("Неверный пароль. Повторите попытку", signInPage.alertOnPassword.getText().trim());
    }


    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void loginWithSocialNetworkPassword(WebDriver driver) {
        signInPage.tryLoginWithSocialNetwork();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        SocialNetworkPage socialNetworkPage = SocialNetworkPage.initialize(driver);
        assertEquals("VK ID", socialNetworkPage.getTitle());
    }

    @Override
    protected void preparePages(WebDriver driver) {
        HomePage homePage = HomePage.initialize(driver);
        //homePage.acceptCookies();
        homePage.goToSignIn();
        signInPage = SignInPage.initialize(driver);
    }
}