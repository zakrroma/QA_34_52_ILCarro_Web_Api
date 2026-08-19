package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import static utils.PropertiesReader.*;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        setDriver(driver);
        driver.get(getProperty("base.properties", "baseUrl"));
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(xpath = "//div[@class='header']/a[6]")
    WebElement navLinkLogin;

    @FindBy(xpath = "//div[@class='header']/a[5]")
    WebElement navLinkSignUp;

    @FindBy(xpath = "//div[@class='header']/a[4]")
    WebElement navLinkTerms;

    @FindBy(xpath = "//div[@class='header']/a[3]")
    WebElement navLinkLet;

    @FindBy(xpath = "//div[@class='header']/a[2]")
    WebElement navLinkSearch;

    @FindBy(xpath = "//div[@class='header']/a[5]")
    WebElement navLinkLogout;

    @FindBy(xpath = "//div[@class='header']/a[6]")
    WebElement navLinkDeleteAccount;

    public void clickNavLinkLogin() {
        navLinkLogin.click();
    }

    public void clickNavLinkSignUp() {
        navLinkSignUp.click();
    }

    public void clickNavLinkTerms() {
        navLinkTerms.click();
    }

    public void clickNavLinkLet() {
        navLinkLet.click();
    }

    public void clickNavLinkSearch() {
        navLinkSearch.click();
    }

    public void clickNavLinkLogout() {
        navLinkLogout.click();
    }

    public void clickNavLinkDeleteAccount() {
        navLinkDeleteAccount.click();
    }
}