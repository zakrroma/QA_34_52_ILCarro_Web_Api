package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.enums.NavBar;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {
    static WebDriver driver;

    public Logger logger = LoggerFactory.getLogger(BasePage.class);

    public void setDriver(WebDriver wd) {
        driver = wd;
    }

    public boolean isElementDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    @FindBy(xpath = "//div[@class='error']")
    List<WebElement> errorList;

    public boolean isTextPresentsInError(String text) {
        if (errorList == null || errorList.isEmpty()) return false;
        for (WebElement e : errorList) {
            if (e.getText().contains(text)) {
                //System.out.println(e.getText());
                return true;
            }
        }
        return false;
    }

    public void clickWait(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions
                        .elementToBeClickable(element)).click();
    }

    public <T extends BasePage> T clickNavLink(NavBar item) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions
                        .elementToBeClickable(By.xpath(item.getLocator())))
                .click();
        switch (item) {
            case LOGO -> {
                return (T) new HomePage(driver);
            }
            case SEARCH -> {
                return (T) new HomePage(driver);
            }
            case LOGOUT -> {
                return (T) new HomePage(driver);
            }
            case LET_THE_CAR_WORK ->  {
                return (T) new LetTheCarWorkPage(driver);
            }
            case TERMS_OF_USE -> {
                return (T) new TermsOfUsePage(driver);
            }
            case LOGIN ->  {
                return (T) new LoginPage(driver);
            }
            case SIGN_UP ->  {
                return (T) new SignUpPage(driver);
            }
            case DELETE_ACCOUNT -> {
                return (T) new PopUpMessage(driver);
            }
            default ->  throw new IllegalArgumentException("Wrong item");
        }
    }

    public boolean isTextInElementPresent(
            WebElement element, String text) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions
                            .textToBePresentInElement(element, text));
        } catch (RuntimeException e) {
//            e.printStackTrace();
//            System.out.println("created an exception");
            logger.error("created an exception",e);
        }
        return false;
    }

    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}