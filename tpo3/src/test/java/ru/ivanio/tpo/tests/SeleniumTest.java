package ru.ivanio.tpo.tests;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import ru.ivanio.tpo.pages.HomePage;


import static org.junit.jupiter.api.Assertions.*;

public class SeleniumTest extends PageTestBase {
    private HomePage homePage;
    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void executeWithCapabilities(WebDriver driver) {
        String title = driver.getTitle();
        assertEquals("Работа в Санкт-Петербурге, вакансии и резюме, поиск работы на Rabota.ru", title);
    }

    @Override
    protected void preparePages(WebDriver driver) {
        //homePage = new HomePage(driver);
        homePage = HomePage.initialize(driver);
        homePage.acceptCookies();
    }
}
