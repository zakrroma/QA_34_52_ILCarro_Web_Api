package ui_tests;

import dto.CarData;
import dto.UserData;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LetTheCarWorkPage;
import pages.LoginPage;
import pages.PopUpMessage;
import utils.enums.NavBar;

import static utils.CarFactory.*;
import static utils.PropertiesReader.*;

public class CarAddingTests extends AppManager {
    LetTheCarWorkPage letTheCarWorkPage;

    @BeforeMethod
    public void loginAndGoToLetTheCarWorkPage() {
        logger.info("Starting car adding test");
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

    @Test
    public void carAddingAllFieldsEmptyNotInteractedNegativeTest() {
        letTheCarWorkPage = new LetTheCarWorkPage(getDriver());

        letTheCarWorkPage.clickBtnSubmit();
    }

    @Test
    public void carAddingAllFieldsEmptyInteractedNegativeTest() {
        letTheCarWorkPage = new LetTheCarWorkPage(getDriver());

        letTheCarWorkPage.clickBtnSubmit();
    }
}
