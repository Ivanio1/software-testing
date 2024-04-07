package ru.ivanio.tpo.tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import ru.ivanio.tpo.pages.HomePage;

import static org.junit.jupiter.api.Assertions.*;

public class SeleniumTest extends PageTestBase {

    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void executeWithCapabilities(WebDriver driver) {
        String title = driver.getTitle();
        assertEquals("Работа в Москве, поиск персонала и публикация вакансий - hh.ru", title);
    }

    @Override
    protected void preparePages(WebDriver driver) {
        HomePage homePage = HomePage.initialize(driver);
        homePage.acceptCookies();
    }
}
