package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class SearchResultPage extends BasePage {
    public SearchResultPage(WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(xpath = "//h3")
    private WebElement titleSearchResult;

    public boolean isTextInSearchResult(String text) {
        return isTextInElementPresent(titleSearchResult, text);
    }
}
