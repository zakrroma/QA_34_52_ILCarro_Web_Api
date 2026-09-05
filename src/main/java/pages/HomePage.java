package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.time.LocalDate;

import static utils.PropertiesReader.*;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        setDriver(driver);
        driver.get(getProperty("base.properties", "baseUrl"));
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(xpath = "//form/div[1]/input")
    WebElement inputCity;

    @FindBy(xpath = "//form/div[2]/input")
    WebElement inputDates;

    @FindBy(xpath = "//form/button")
    WebElement btnSubmit;

    public void fillSearchForm(String city, LocalDate startDate, LocalDate endDate) {
        inputCity.sendKeys(city);
        if (startDate != null && endDate != null) {
            String dates =
                    startDate.getMonthValue() + "/" +
                            startDate.getDayOfMonth() + "/" +
                            startDate.getYear() + " - " +
                            endDate.getMonthValue() + "/" +
                            endDate.getDayOfMonth() + "/" +
                            endDate.getYear();
            inputDates.sendKeys(dates);
        } else {
            inputDates.sendKeys("");
        }
    }

    public void clickBtnSubmit() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"button[type='submit']\")" +
                ".removeAttribute('disabled')", btnSubmit);
        btnSubmit.click();
    }

    public void clickBtnSubmitNoJS() {
        btnSubmit.click();
    }
}