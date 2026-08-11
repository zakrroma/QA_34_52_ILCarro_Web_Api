package ui_tests;

import dto.UserData;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends AppManager {
    LoginPage loginPage;
    @BeforeMethod
    public void goToLoginPage() {
        new HomePage(getDriver())
                .clickNavLinkLogin();
        loginPage = new LoginPage(getDriver());
    }

    @Test
    public void loginPositiveTest() {
        UserData user = UserData.builder()
                .username("kek@qwer.ty")
                .password("Kek1234!")
                .build();

        loginPage.fillLoginForm(user);
        loginPage.clickBtnSubmit();
    }

    @Test
    public void loginIncorrectPasswordNegativeTest() {
        UserData user = UserData.builder()
                .username("kek@qwer.ty")
                .password("1")
                .build();

        loginPage.fillLoginForm(user);
        loginPage.clickBtnSubmit();

        Assert.assertTrue(loginPage
                .validateTextInMessageLoginFailed("Login or Password incorrect"));

        loginPage.clickBtnOk();
    }

    @Test
    public void loginUnregisteredUsernameNegativeTest() {
        UserData user = UserData.builder()
                .username("kek1@qwer.ty")
                .password("Kek1234!")
                .build();

        loginPage.fillLoginForm(user);
        loginPage.clickBtnSubmit();

        Assert.assertTrue(loginPage
                .validateTextInMessageLoginFailed("Login or Password incorrect"));

        loginPage.clickBtnOk();
    }
}