package ru.ivanio.tpo.tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ivanio.tpo.pages.HomePage;
import ru.ivanio.tpo.pages.VacanciesPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VacanciesPageTest extends PageTestBase {
    VacanciesPage vacanciesPage;
    @Override
    protected void preparePages(WebDriver driver) {
        var homePage = HomePage.initialize(driver);
        homePage.acceptCookies();
        homePage.goToVacancies();
        vacanciesPage = VacanciesPage.initialize(driver);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void testFiltersCount(WebDriver driver) {
        vacanciesPage.filterVacanciesForInvalids();
        new WebDriverWait(driver, 10).until(d -> vacanciesPage.filtersCount.isDisplayed() );
        assertEquals(vacanciesPage.filtersCount.getText(), "1");
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void testSortsCount(WebDriver driver) {
        vacanciesPage.filterVacanciesForInvalids();
        vacanciesPage.sortByRadius();
        new WebDriverWait(driver, 10).until(d -> vacanciesPage.sortingsCount.isDisplayed() );
        assertEquals(vacanciesPage.sortingsCount.getText(), "1");
    }
}
