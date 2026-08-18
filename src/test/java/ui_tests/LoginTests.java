package ui_tests;

import dto.UserData;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;

import static utils.PropertiesReader.*;

public class LoginTests extends AppManager {
    LoginPage loginPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToLoginPage() {
        new HomePage(getDriver())
                .clickNavLinkLogin();
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

        Assert.assertTrue(loginPage.isMessageLoggedInDisplayed());
    }

    @Test
    public void loginIncorrectPasswordNegativeTest() {
        UserData user = UserData.builder()
                .username(getProperty("base.properties", "email"))
                .password("Pass1245$")
                .build();

        loginPage.fillLoginForm(user);
        loginPage.clickBtnSubmit();

        Assert.assertTrue(loginPage.isMessageLoginFailedDisplayed());
    }

    @Test
    public void loginIncorrectUsernameNegativeTest() {
        UserData user = UserData.builder()
                .username("username111@qwer.ty")
                .password(getProperty("base.properties", "password"))
                .build();

        loginPage.fillLoginForm(user);
        loginPage.clickBtnSubmit();

        Assert.assertTrue(loginPage.isMessageLoginFailedDisplayed());
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