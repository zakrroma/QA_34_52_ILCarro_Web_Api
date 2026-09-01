package ui_tests;

import dto.CarData;
import dto.UserData;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LetTheCarWorkPage;
import pages.LoginPage;
import pages.PopUpMessage;
import utils.RetryAnalyzer;
import utils.enums.NavBar;

import static utils.CarFactory.*;
import static utils.PropertiesReader.*;

public class CarAddingTests extends AppManager {
    LetTheCarWorkPage letTheCarWorkPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void loginAndGoToLetTheCarWorkPage() {
        LoginPage loginPage = new HomePage(getDriver())
                .clickNavLink(NavBar.LOGIN);

        UserData user = UserData.builder()
                .username(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();

        loginPage.fillLoginForm(user);
        loginPage.clickBtnSubmit();

        new PopUpMessage(getDriver()).clickBtnOk();

        letTheCarWorkPage = new HomePage(getDriver())
                .clickNavLink(NavBar.LET_THE_CAR_WORK);
    }

    @Test
    public void carAddingPositiveTest() {
        letTheCarWorkPage = new LetTheCarWorkPage(getDriver());

        CarData car = positiveCar();

        letTheCarWorkPage.fillLetTheCarWorkForm(car);
        letTheCarWorkPage.downloadImage("car1.png");
        letTheCarWorkPage.clickBtnSubmit();

        Assert.assertTrue(new PopUpMessage(getDriver())
                .isTextInMessage("{\"city\":\"must not be blank\"}"));
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void carAddingAllFieldsEmptyNotInteractedNegativeTest() {
        letTheCarWorkPage = new LetTheCarWorkPage(getDriver());

        letTheCarWorkPage.clickBtnSubmit();

        Assert.assertTrue(new PopUpMessage(getDriver())
                .isTextInMessage("{\"manufacture\":\"must not be blank\"," +
                        "\"serialNumber\":\"must not be blank\"," +
                        "\"year\":\"must not be blank\"," +
                        "\"city\":\"must not be blank\"," +
                        "\"fuel\":\"must not be blank\"," +
                        "\"model\":\"must not be blank\"," +
                        "\"pricePerDay\":\"must not be null\"," +
                        "\"seats\":\"must not be null\"," +
                        "\"carClass\":\"must not be blank\"}"));
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void carAddingAllFieldsEmptyInteractedNegativeTest() {
        letTheCarWorkPage = new LetTheCarWorkPage(getDriver());

        CarData car = negativeAllEmptyFieldsCar();

        letTheCarWorkPage.fillLetTheCarWorkForm(car);
        letTheCarWorkPage.clickBtnSubmitNoJS();

        softAssert.assertTrue(letTheCarWorkPage.isTextPresentsInError("Wrong address"));
        softAssert.assertTrue(letTheCarWorkPage.isTextPresentsInError("Make is required"));
        softAssert.assertTrue(letTheCarWorkPage.isTextPresentsInError("Model is required"));
        softAssert.assertTrue(letTheCarWorkPage.isTextPresentsInError("Year required"));
        softAssert.assertTrue(letTheCarWorkPage.isTextPresentsInError("Fuel is required"));
        softAssert.assertTrue(letTheCarWorkPage.isTextPresentsInError("Number of seats is required"));
        softAssert.assertTrue(letTheCarWorkPage.isTextPresentsInError("Car class is required"));
        softAssert.assertTrue(letTheCarWorkPage.isTextPresentsInError("Car registration number is required"));
        softAssert.assertTrue(letTheCarWorkPage.isTextPresentsInError("Price is required"));
        softAssert.assertFalse(letTheCarWorkPage.validateIsBtnSubmitActive());
        softAssert.assertAll();
    }

    @Test
    public void carAddingEmptyManufactureFieldNegativeTest() {
        letTheCarWorkPage = new LetTheCarWorkPage(getDriver());

        CarData car = negativeEmptyManufactureFieldCar();

        letTheCarWorkPage.fillLetTheCarWorkForm(car);
        letTheCarWorkPage.clickBtnSubmit();

        Assert.assertTrue(new PopUpMessage(getDriver())
                .isTextInMessage("{\"manufacture\":\"must not be blank\"," +
                        "\"city\":\"must not be blank\"}"));
    }

    @Test
    public void carAddingIncorrectYearFieldNegativeTest() {
        letTheCarWorkPage = new LetTheCarWorkPage(getDriver());

        CarData car = negativeIncorrectYearFieldCar();

        letTheCarWorkPage.fillLetTheCarWorkForm(car);
        letTheCarWorkPage.clickBtnSubmit();

        softAssert.assertTrue(new PopUpMessage(getDriver())
                .isTextInMessage("{\"city\":\"must not be blank\"}"));
        //new PopUpMessage(getDriver()).clickBtnOk();
        softAssert.assertTrue(letTheCarWorkPage.isTextPresentsInError("Wrong year"));
        softAssert.assertAll();
    }
}
