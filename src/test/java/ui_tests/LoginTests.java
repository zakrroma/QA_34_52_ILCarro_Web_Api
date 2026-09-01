package ui_tests;

import dto.UserData;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import pages.PopUpMessage;
import utils.TestNGListener;
import utils.enums.NavBar;

import static utils.PropertiesReader.*;

@Listeners(TestNGListener.class)

public class LoginTests extends AppManager {
    LoginPage loginPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToLoginPage() {
        new HomePage(getDriver())
                .clickNavLink(NavBar.LOGIN);
        loginPage = new LoginPage(getDriver());
    }

    @Test
    public void loginPositiveTest() {
        UserData user = UserData.builder()
                .username(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();

        loginPage.fillLoginForm(user);
        loginPage.clickBtnSubmit();

        Assert.assertTrue(new PopUpMessage(getDriver())
                .isTextInMessage("Logged in success"));
    }

    @Test
    public void loginIncorrectPasswordNegativeTest() {
        UserData user = UserData.builder()
                .username(getProperty("base.properties", "email"))
                .password("Pass1245$")
                .build();

        loginPage.fillLoginForm(user);
        loginPage.clickBtnSubmit();

        Assert.assertTrue(new PopUpMessage(getDriver())
                .isTextInMessage("\"Login or Password incorrect\""));
    }

    @Test
    public void loginIncorrectUsernameNegativeTest() {
        UserData user = UserData.builder()
                .username("username111@qwer.ty")
                .password(getProperty("base.properties", "password"))
                .build();

        loginPage.fillLoginForm(user);
        loginPage.clickBtnSubmit();

        Assert.assertTrue(new PopUpMessage(getDriver())
                .isTextInMessage("\"Login or Password incorrect\""));
    }

    @Test
    public void loginAllEmptyNotInteractedFieldsNegativeTest() {
        loginPage.clickBtnSubmit();

        Assert.assertFalse(loginPage.isBtnSubmitEnabled());
    }

    @Test
    public void loginAllEmptyInteractedFieldsNegativeTest() {
        UserData user = UserData.builder()
                .username("")
                .password("")
                .build();

        loginPage.fillLoginForm(user);
        loginPage.clickBtnSubmit();

        softAssert.assertFalse(loginPage.isBtnSubmitEnabled(),
                "validating if submit button is enabled");
        softAssert.assertTrue(loginPage.isTextPresentsInError("Email is required"),
                "validating message: Email is required");
        softAssert.assertTrue(loginPage.isTextPresentsInError("Password is required"),
                "validating message: Password is required");
        softAssert.assertAll();
    }

    @Test
    public void loginEmptyPasswordFieldNegativeTest() {
        UserData user = UserData.builder()
                .username(getProperty("base.properties", "email"))
                .password("")
                .build();

        loginPage.fillLoginForm(user);
        loginPage.clickBtnSubmit();

        Assert.assertFalse(loginPage.isBtnSubmitEnabled());
    }

    @Test
    public void loginEmptyUsernameFieldNegativeTest() {
        UserData user = UserData.builder()
                .username("")
                .password(getProperty("base.properties", "password"))
                .build();

        loginPage.fillLoginForm(user);
        loginPage.clickBtnSubmit();

        Assert.assertFalse(loginPage.isBtnSubmitEnabled());
    }
}