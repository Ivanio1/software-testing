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

  /*
   * Constructor injecting the WebDriver interface
   * 
   * @param webDriver
   */
  public Page(WebDriver driver) {
    this.driver = driver;
  }

    @FindBy(how = How.XPATH, using = "//button[@data-qa=\"cookies-policy-informer-accept\"]")
    public WebElement cookiesAccept;



    public void acceptCookies() {
        try {
            cookiesAccept.click();
            new WebDriverWait(driver, 1).until(d -> {
                try {
                    return !cookiesAccept.isDisplayed();
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
