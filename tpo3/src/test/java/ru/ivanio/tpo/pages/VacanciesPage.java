package ru.ivanio.tpo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class VacanciesPage extends Page {
    public VacanciesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.XPATH, using = "//button[contains(.,'Фильтры')]")
    public WebElement filters;

    @FindBy(how = How.XPATH, using = "//label[contains(.,'Для людей с инвалидностью')]")
    public WebElement invalidFilter;

    @FindBy(how = How.XPATH, using = "//*[@class=\"layout desktop-filter-main__controls\"]/div/*[@class=\"r-btn r-btn_large primary\"]")
    public WebElement filterButton;
    @FindBy(how = How.XPATH, using = "//button[contains(.,'Фильтры')]/div/span")
    public WebElement filtersCount;

    @FindBy(how = How.XPATH, using = "//button[contains(.,'Сортировка')]/div/span")
    public WebElement sortingsCount;

    @FindBy(how = How.XPATH, using = "//button[contains(.,'Сортировка')]")
    public WebElement sortings;
    //*[@class="vacancy-preview-location__address-text"]
    @FindBy(how = How.XPATH, using = "//button[contains(.,'Применить')]")
    public WebElement acceptSortingsButton;

    @FindBy(how = How.XPATH, using = "//*[@class=\"search-crumbs-desktop__reset-btn r-btn r-btn_medium\"]")
    public WebElement clearFilters;

    public static VacanciesPage initialize(WebDriver driver) {
        return Page.initialize(driver, "//a[contains(.,'Вакансии')]", VacanciesPage.class);
    }

    public void filterVacanciesForInvalids() {
        click(filters);
        click(invalidFilter);
        click(filterButton);
    }

    public void sortByRadius() {
        click(sortings);
        click(driver.findElement(By.xpath("(//*[@class=\"list__tile__sub-title desktop-sort__sub-title\"])[2]")));
        click(acceptSortingsButton);
    }

    public void clearFilters() {
        click(clearFilters);
    }


    private void click(WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", webElement);
    }

}
