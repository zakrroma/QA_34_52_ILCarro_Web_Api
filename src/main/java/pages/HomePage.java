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
        //driver.get("https://ilcarro.web.app/");
        driver.get(getProperty("base.properties","baseUrl"));
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(xpath = "//a[text()=' Log in ']")
    WebElement navLinkLogin;

    @FindBy(xpath = "//a[text()=' Sign up ']")
    WebElement navLinkSignUp;

    @FindBy(xpath = "//a[text()=' Terms of use ']")
    WebElement navLinkTerms;

    @FindBy(xpath = "//a[text()=' Let the car work ']")
    WebElement navLinkLet;

    @FindBy(xpath = "//a[text()=' Search ']")
    WebElement navLinkSearch;

    @FindBy(xpath = "//a[text()=' Logout ']")
    WebElement navLinkLogout;

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
}