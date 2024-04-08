package ru.ivanio.tpo.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Abstract class representation of a Page in the UI. Page object pattern
 */
public abstract class Page {

  protected WebDriver driver;


  public Page(WebDriver driver) {
    this.driver = driver;
  }

    @FindBy(how = How.XPATH, using = "//*[@class=\"cookie-policy-popup__button r-btn r-btn_full-width r-btn_large primary\"]")
    public WebElement acceptCookiesBtn;

    public void acceptCookies() {
        try {
            acceptCookiesBtn.click();
            new WebDriverWait(driver, 5).until(d -> {
                try {
                    return !acceptCookiesBtn.isDisplayed();
                } catch (NoSuchElementException | StaleElementReferenceException e) {
                    return true;
                }
            });
        } catch (NoSuchElementException e) {
            System.out.println("No cookies accept button!");
        }
    }


    protected static<T> T initialize(WebDriver driver, String checkXpath, Class<T> clazz) {
    Wait<WebDriver> wait = new WebDriverWait(driver, 1).ignoring(StaleElementReferenceException.class);
        wait.until(d -> {
            return d.findElement(By.xpath(checkXpath)).isDisplayed();
        });
        return PageFactory.initElements(driver, clazz);
  }
  public String getTitle() {
    return driver.getTitle();
  }

}
