package pages;

import dto.CarData;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LetTheCarWorkPage extends BasePage {
    public LetTheCarWorkPage(WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(xpath = "//form/div[1]/input")
    WebElement inputLocation;

    @FindBy(xpath = "//form/div[3]/input")
    WebElement inputManufacture;

    @FindBy(xpath = "//form/div[4]/input")
    WebElement inputModel;

    @FindBy(xpath = "//form/div[5]/input")
    WebElement inputYear;

    @FindBy(xpath = "//form/div/select")
    WebElement selectFuel;

    @FindBy(xpath = "//form/div/select/option[2]")
    WebElement optionPetrol;

    @FindBy(xpath = "//form/div[7]/input")
    WebElement inputSeats;

    @FindBy(xpath = "//form/div[8]/input")
    WebElement inputCarClass;

    @FindBy(xpath = "//form/div[9]/input")
    WebElement inputCarRegistrationNumber;

    @FindBy(xpath = "//form/div[10]/input")
    WebElement inputPrice;

    @FindBy(css = "button[type='submit']")
    WebElement btnSubmit;

    public void clickBtnSubmit() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"button[type='submit']\")" +
                ".removeAttribute('disabled')", btnSubmit);
        btnSubmit.click();
    }

    public void fillLetTheCarWorkForm(CarData car) {
        inputLocation.sendKeys(car.getLocation());
        inputManufacture.sendKeys(car.getManufacture());
        inputModel.sendKeys(car.getModel());
        inputYear.sendKeys(car.getYear());
        selectFuel.click();
        optionPetrol.click();
        inputSeats.sendKeys(car.getSeats());
        inputCarClass.sendKeys(car.getCarClass());
        inputCarRegistrationNumber.sendKeys(car
                .getCarRegistrationNumber());
        inputPrice.sendKeys(car.getPrice());
    }
}
