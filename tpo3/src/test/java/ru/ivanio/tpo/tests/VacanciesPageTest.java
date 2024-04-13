package ru.ivanio.tpo.tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ivanio.tpo.pages.HomePage;
import ru.ivanio.tpo.pages.VacanciesPage;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        new WebDriverWait(driver, 10).until(d -> vacanciesPage.filtersCount.isDisplayed());
        assertEquals(vacanciesPage.filtersCount.getText(), "1");
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void testSortsCount(WebDriver driver) {
        vacanciesPage.filterVacanciesForInvalids();
        vacanciesPage.sortByRadius();
        new WebDriverWait(driver, 10).until(d -> vacanciesPage.sortingsCount.isDisplayed());
        assertEquals(vacanciesPage.sortingsCount.getText(), "1");
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void testClearFilters(WebDriver driver) {
        vacanciesPage.filterVacanciesForInvalids();
        vacanciesPage.sortByRadius();
        vacanciesPage.clearFilters();
        assertThrows(NotFoundException.class, vacanciesPage.sortingsCount::getText);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void testFilterBySalary(WebDriver driver) {
        vacanciesPage.filterBySalary("100000");
        new WebDriverWait(driver, 10).until(d -> vacanciesPage.firstSalaryField.isDisplayed());
        var wait = new WebDriverWait(driver, 15);
        wait.until(i -> driver.getCurrentUrl().contains("min_salary=100000"));
        driver.navigate().refresh();
        wait.until(i -> vacanciesPage.firstSalaryField.isDisplayed());
        var firstOfferMinSalary = Integer.parseInt(
                vacanciesPage.firstSalaryField.getText().split("—")[0].replace(" ", "")
        );
        var test = vacanciesPage.firstSalaryField.getText();
        assertTrue(firstOfferMinSalary >= 100000);
    }
}
