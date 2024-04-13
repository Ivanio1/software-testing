package ru.ivanio.tpo.tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ivanio.tpo.Constants;
import ru.ivanio.tpo.pages.HomePage;
import ru.ivanio.tpo.pages.Page;
import ru.ivanio.tpo.pages.ProfilePage;
import ru.ivanio.tpo.pages.SignInPage;

import java.util.Random;

public class ChangingPasswordTest extends PageTestBase {
    private ProfilePage profilePage;

    @Override
    protected void preparePages(WebDriver driver) {
        HomePage homePage = HomePage.initialize(driver);
        homePage.acceptCookies();
        homePage.goToSignIn();
        SignInPage signInPage = SignInPage.initialize(driver);
        signInPage.tryLogin(Constants.USER_FOR_CHANGING_PASSWORD);
        signInPage.sendPassword(Constants.USER_FOR_CHANGING_PASSWORD_PASSWORD);
        HomePage homePage1 = PageFactory.initElements(driver, HomePage.class);
        homePage1.openProfileMenu();
        new WebDriverWait(driver, 10).until(d -> homePage1.goToProfileButton.isDisplayed());
        homePage1.goToProfileButton.click();
        profilePage = ProfilePage.initialize(driver);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void testPasswordChange(WebDriver driver) {
        Random random = new Random();
        int num = random.nextInt(10);
        String newPassword = Constants.USER_FOR_CHANGING_PASSWORD_PASSWORD + num;
        System.out.println(newPassword);
        Constants.USER_FOR_CHANGING_PASSWORD_PASSWORD=newPassword;

        profilePage.tryChangePassword(Constants.USER_FOR_CHANGING_PASSWORD_PASSWORD, newPassword);
        profilePage.savePasswordButton.click();
        Constants.USER_FOR_CHANGING_PASSWORD_PASSWORD=newPassword;

    }
}




