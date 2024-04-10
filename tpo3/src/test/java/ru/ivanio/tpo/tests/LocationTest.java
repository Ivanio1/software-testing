package ru.ivanio.tpo.tests;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ivanio.tpo.Constants;
import ru.ivanio.tpo.pages.AllCitiesPage;
import ru.ivanio.tpo.pages.GeolocationPage;
import ru.ivanio.tpo.pages.HomePage;
import ru.ivanio.tpo.pages.SignInPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationTest extends PageTestBase {
    GeolocationPage geolocationPage;
    @Override
    protected void preparePages(WebDriver driver) {
        HomePage homePage = HomePage.initialize(driver);
        homePage.acceptCookies();

        homePage.goToSignIn();
        var signInPage = SignInPage.initialize(driver);
        signInPage.tryLogin(Constants.EXISTING_EMAIL);
        signInPage.sendPassword(Constants.EXISTING_PASSWORD);
        homePage.goToGeolocation();
        geolocationPage = GeolocationPage.initialize(driver);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void test1(WebDriver driver) {
        geolocationPage.goToAllCities();
        var allCities = AllCitiesPage.initialize(driver);
        allCities.clickCityButton("Архангельск");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        new WebDriverWait(driver, 20).until(ExpectedConditions.titleIs("Работа в Архангельске, вакансии и резюме, поиск работы на Rabota.ru"));
    }
}
