package ru.ivanio.tpo.tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ivanio.tpo.pages.HomePage;
import ru.ivanio.tpo.pages.ProfessionPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProfessionsTest extends PageTestBase {
    ProfessionPage professionPage;

    @Override
    protected void preparePages(WebDriver driver) {
        HomePage homePage = HomePage.initialize(driver);
        homePage.acceptCookies();
        homePage.goToProfessions();
        professionPage = ProfessionPage.initialize(driver);

    }


    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void testTitle(WebDriver driver) {
        new WebDriverWait(driver, 10).until(d -> professionPage.title.isDisplayed());
        assertEquals("Каталог профессий", professionPage.title.getText());
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("allDrivers")
    public void testFindJob(WebDriver driver) {
        professionPage.findJob("Android");
        new WebDriverWait(driver, 10).until(d -> professionPage.jobCard.isDisplayed());
        var test = professionPage.jobCard.getText();
        assertTrue(professionPage.jobCard.getText().toLowerCase().contains("android"));
    }


}


