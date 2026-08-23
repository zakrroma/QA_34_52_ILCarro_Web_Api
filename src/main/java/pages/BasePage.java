package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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