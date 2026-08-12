package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public abstract class BasePage {
    static WebDriver driver;

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

    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}