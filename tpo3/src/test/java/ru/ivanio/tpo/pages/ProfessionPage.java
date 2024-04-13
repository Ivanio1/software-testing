package ru.ivanio.tpo.pages;

import org.openqa.selenium.WebDriver;

public class ProfessionPage extends Page {
    public ProfessionPage(WebDriver driver) {
        super(driver);
    }

    public static CompaniesPage initialize(WebDriver driver) {
        return Page.initialize(driver, "//*[@class=\"career-search-header__title\"]", CompaniesPage.class);
    }

}
