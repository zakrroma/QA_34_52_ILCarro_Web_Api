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

import static utils.PropertiesReader.*;

public class CarAddingTests extends AppManager {
    LetTheCarWorkPage letTheCarWorkPage;

    @BeforeMethod
    public void loginAndGoToLetTheCarWorkPage() {
        new HomePage(getDriver()).clickNavLinkLogin();

        LoginPage loginPage = new LoginPage(getDriver());

        UserData user = UserData.builder()
                .username(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();

        loginPage.fillLoginForm(user);
        loginPage.clickBtnSubmit();
        new PopUpMessage(getDriver()).clickBtnOk();

        new HomePage(getDriver()).clickNavLinkLet();
    }

    @Test
    public void carAddingPositiveTest() {
        letTheCarWorkPage = new LetTheCarWorkPage(getDriver());

        CarData car = CarData.builder()
                .location("Ashdod")
                .manufacture("Japan")
                .model("Toyota RAV4")
                .year("2025")
                .seats("5")
                .carClass("Crossover")
                .carRegistrationNumber("qwe12345")
                .price("100.000 $")
                .build();

        letTheCarWorkPage.fillLetTheCarWorkForm(car);
        letTheCarWorkPage.clickBtnSubmit();

        Assert.assertTrue(new PopUpMessage(getDriver())
                .isTextInMessage("{\"city\":\"must not be blank\"}"));
    }
}
