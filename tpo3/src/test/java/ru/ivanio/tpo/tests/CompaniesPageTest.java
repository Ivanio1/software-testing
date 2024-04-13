package ru.ivanio.tpo.tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ivanio.tpo.pages.CompaniesPage;
import ru.ivanio.tpo.pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompaniesPageTest extends PageTestBase {
    CompaniesPage companiesPage;
    @Override
    protected void preparePages(WebDriver driver) {
        HomePage homePage = HomePage.initialize(driver);
        homePage.acceptCookies();
        homePage.goToCompanies();
        companiesPage = CompaniesPage.initialize(driver);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void testFilterBySalary(WebDriver driver) {
        companiesPage.enterCompanyName("Сбербанк");
        new WebDriverWait(driver, 10).until(d -> companiesPage.firstSuggestionField.isDisplayed());
        assertTrue(companiesPage.firstSuggestionField.getText().toLowerCase().contains("сбер"));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void testTitle(WebDriver driver) {
        new WebDriverWait(driver, 50).until(d -> companiesPage.title.isDisplayed());
        assertEquals("Компании Санкт-Петербурга", companiesPage.title.getText());
    }
}
