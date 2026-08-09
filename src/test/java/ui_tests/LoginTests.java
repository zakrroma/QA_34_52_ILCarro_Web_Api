package ui_tests;

import dto.UserData;
import manager.AppManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends AppManager {
    @BeforeMethod
    public void goToLoginPage() {
        new HomePage(getDriver()).clickNavLinkLogin();
    }

    @Test
    public void loginPositiveTest() {
        UserData user = UserData.builder()
                .username("kek@qwer.ty")
                .password("Kek1234!")
                .build();

        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.fillLoginForm(user);

        loginPage.clickBtnSubmit();
    }
}