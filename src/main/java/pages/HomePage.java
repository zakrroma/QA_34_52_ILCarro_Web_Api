package pages;

import org.openqa.selenium.*;
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

    @FindBy(xpath = "//button[@aria-label='Choose month and year']")
    WebElement btnChooseMonthAndYear;

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

    public void interactWithCalendar(String city, LocalDate startDate, LocalDate endDate) {
        inputCity.sendKeys(city);
        inputDates.click();
        typeCalendar(startDate);
        typeCalendar(endDate);
    }

    private void typeCalendar(LocalDate date) {
        btnChooseMonthAndYear.click();

        String year = date.getYear() + "";
        WebElement btnYear = driver.findElement(By
                .xpath("//td[@aria-label='"+year+"']"));
        btnYear.click();

        String month = createMonth(date.getMonth().toString());
        WebElement btnMonth = driver.findElement(By
                .xpath("//td[@aria-label='"+month+" "+year+"']"));
        btnMonth.click();

        String day = date.getDayOfMonth() + "";
        WebElement btnDay = driver.findElement(By
                .xpath("//td[@aria-label='"+month+" "+day+", "+year+"']"));
        btnDay.click();
    }

    private String createMonth(String month) {
        return new StringBuilder()
                .append(month.substring(0, 1).toUpperCase())
                .append(month.substring(1).toLowerCase()).toString();
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