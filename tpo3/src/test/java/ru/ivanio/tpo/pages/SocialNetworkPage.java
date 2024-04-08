package ru.ivanio.tpo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SocialNetworkPage extends Page{
    public SocialNetworkPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.XPATH, using = "//input[@class=\"vkuiInput__el\"]")
    public WebElement emailField;

    @FindBy(how = How.XPATH, using = "//button[@class=\"vkuiButton vkuiButton--sz-l vkuiButton--lvl-primary vkuiButton--clr-accent vkuiButton--aln-center vkuiButton--sizeY-compact vkuiButton--stretched vkuiTappable vkuiTappable--sizeX-regular vkuiTappable--hasHover vkuiTappable--mouse\"]")
    public WebElement submitBtn;

    public static SocialNetworkPage initialize(WebDriver driver) {
        return Page.initialize(driver, "//button[@class=\"vkuiButton vkuiButton--sz-l vkuiButton--lvl-primary vkuiButton--clr-accent vkuiButton--aln-center vkuiButton--sizeY-compact vkuiButton--stretched vkuiTappable vkuiTappable--sizeX-regular vkuiTappable--hasHover vkuiTappable--mouse\"]", SocialNetworkPage.class);
    }


}
