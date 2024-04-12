package ru.ivanio.tpo.tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ivanio.tpo.pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePageTest extends PageTestBase {
    HomePage homePage;

    @Override
    protected void preparePages(WebDriver driver) {
        homePage = HomePage.initialize(driver);
        homePage.acceptCookies();
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void testNotFoundText(WebDriver driver) {
        homePage.findVacancy("qweasdzxc");
        new WebDriverWait(driver, 10).until(d -> homePage.notFoundErrorText.isDisplayed());
        assertEquals(homePage.notFoundErrorText.getText(), "К сожалению, мы не нашли вакансий по вашему запросу. Что " +
                "можно сделать?");
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void testNotSelectedMetroText(WebDriver driver) {
        homePage.radiusButton.click();
        new WebDriverWait(driver, 10).until(d -> homePage.notSelectedTextHolder.isDisplayed() &&
                homePage.selectMetroStationText.isDisplayed());
        assertEquals(homePage.selectMetroStationText.getText(), "Выберите станции метро, рядом с которыми хотите " +
                "найти работу");
        assertEquals(homePage.notSelectedTextHolder.getText(), "Пока вы не выбрали ни одной станции");

    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void testAutoComplete(WebDriver driver) {
        homePage.enterVacancy("Свежие");
        new WebDriverWait(driver, 10).until(d -> homePage.firstSuggestedVariantText.isDisplayed());
        assertEquals(homePage.firstSuggestedVariantText.getText(),
                "Свежие вакансии");
    }


}