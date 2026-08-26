package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class PopUpMessage extends BasePage {
    public PopUpMessage(WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(xpath = "//h2[@class='message']")
    WebElement message;

    @FindBy(xpath = "//button[text()='Ok']")
    WebElement btnOk;

    public boolean isTextInMessage(String text) {
        return isTextInElementPresent(message, text);
    }

    public void clickBtnOk() {
        btnOk.click();
    }
}
