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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class ChangingPasswordTest extends PageTestBase {
    private ProfilePage profilePage;
    private String oldPassword = "";
    @Override
    protected void preparePages(WebDriver driver) {
        HomePage homePage = HomePage.initialize(driver);
        homePage.acceptCookies();
        homePage.goToSignIn();
        SignInPage signInPage = SignInPage.initialize(driver);
        signInPage.tryLogin(Constants.USER_FOR_CHANGING_PASSWORD);
        try(Scanner scanner = new Scanner(new File("src/test/resources/passwd"))) {
            oldPassword = scanner.nextLine();
        } catch (Exception ignored) {

        }
        signInPage.sendPassword(oldPassword);
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

        String newPassword = oldPassword.substring(0, oldPassword.length()-1) + num;

        try(PrintWriter writer = new PrintWriter("src/test/resources/passwd")) {
            writer.write(newPassword);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        System.out.println(newPassword);

        profilePage.tryChangePassword(oldPassword, newPassword);
        profilePage.savePasswordButton.click();
    }
}




