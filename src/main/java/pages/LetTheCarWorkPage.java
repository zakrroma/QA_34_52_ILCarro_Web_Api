package pages;

import dto.CarData;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import utils.enums.Fuel;

import java.io.File;

public class LetTheCarWorkPage extends BasePage {
    public LetTheCarWorkPage(WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(xpath = "//form/div[1]/input")
    WebElement inputCity;

    @FindBy(xpath = "//form/div[3]/input")
    WebElement inputManufacture;

    @FindBy(xpath = "//form/div[4]/input")
    WebElement inputModel;

    @FindBy(xpath = "//form/div[5]/input")
    WebElement inputYear;

    @FindBy(xpath = "//form/div/select")
    WebElement selectFuel;

    @FindBy(xpath = "//form/div[7]/input")
    WebElement inputSeats;

    @FindBy(xpath = "//form/div[8]/input")
    WebElement inputCarClass;

    @FindBy(xpath = "//form/div[9]/input")
    WebElement inputSerialNumber;

    @FindBy(xpath = "//form/div[10]/input")
    WebElement inputPricePerDay;

    @FindBy(xpath = "//form//textarea")
    WebElement inputAbout;

    @FindBy(xpath = "//label[text()='Add photos of your car']/../input")
    WebElement addPhoto;

    @FindBy(css = "button[type='submit']")
    WebElement btnSubmit;

    public void clickBtnSubmit() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"button[type='submit']\")" +
                ".removeAttribute('disabled')", btnSubmit);
        btnSubmit.click();
    }

    public void chooseFuel(Fuel fuel) {
        selectFuel.click();
        driver.findElement(By.xpath(fuel.getLocator())).click();
    }

    public void downloadImage(String fileName) {
        addPhoto.sendKeys(
                new File("src/test/resources/"+fileName)
                        .getAbsolutePath());
    }

    public void fillLetTheCarWorkForm(CarData car) {
        inputCity.sendKeys(car.getCity());
        inputManufacture.sendKeys(car.getManufacture());
        inputModel.sendKeys(car.getModel());
        inputYear.sendKeys(car.getYear());
        chooseFuel(car.getFuel());
        inputSeats.sendKeys(car.getSeats().toString());
        //inputSeats.sendKeys(car.getSeats()+"");
        //inputSeats.sendKeys(String.valueOf(car.getSeats()));
        //inputSeats.sendKeys(Integer.toString(car.getSeats()));
        inputCarClass.sendKeys(car.getCarClass());
        inputSerialNumber.sendKeys(car.getSerialNumber());
        inputPricePerDay.sendKeys(car.getPricePerDay().toString());
        inputAbout.sendKeys(car.getAbout());
    }
}
