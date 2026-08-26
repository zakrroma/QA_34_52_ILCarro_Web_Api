package pages;

import dto.UserData;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class SignUpPage extends BasePage {
    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(xpath = "//form/div[1]/input")
    WebElement inputFirstName;

    @FindBy(xpath = "//form/div[2]/input")
    WebElement inputLastName;

    @FindBy(xpath = "//form/div[3]/input")
    WebElement inputEmail;

    @FindBy(xpath = "//form/div[4]/input")
    WebElement inputPassword;

    @FindBy(xpath = "//form//button")
    WebElement btnSubmit;

    @FindBy(xpath = "//input[@id='terms-of-use']")
    WebElement checkboxAgreement;

    @FindBy(xpath = "//label[@for='terms-of-use']")
    WebElement checkboxAgreementArea;

    public void fillRegistrationForm(UserData user) {
        inputFirstName.sendKeys(user.getFirstName());
        inputLastName.sendKeys(user.getLastName());
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
    }

    public void setCheckboxAgreement() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", checkboxAgreement);
//        js.executeScript("arguments[0].checked = true;", checkboxAgreement);
    }

    public void setCheckboxAgreementWithActions() {
        int x = checkboxAgreementArea.getSize().getWidth();
        int y = checkboxAgreementArea.getSize().getHeight();
        System.out.println("x: " + x + " y: " + y);
        Actions actions = new Actions(driver);
        actions.moveToElement(checkboxAgreement,-x*3/10,-y/2)
                .click().perform();
    }

    public void clickBtnSubmit() {
        btnSubmit.click();
    }
}
