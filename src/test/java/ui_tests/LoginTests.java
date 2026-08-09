package ui_tests;

import dto.UserData;
import manager.AppManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoggedMessage;
import pages.LoginPage;

public class LoginTests extends AppManager {
    @BeforeMethod
    public void goToRegistrationLoginPage() {
        new HomePage(getDriver()).clickNavLinkLogin();
    }

    @Test
    public void loginPositiveTest() {
        UserData user = UserData.builder()
                .username("kek@qwer.ty")
                .password("Kek1234!")
                .build();

        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.fillLoginRegistrationForm(user);

        loginPage.pause(2000);

        loginPage.clickBtnLogin();

        loginPage.pause(2000);
        //==========================================
        new LoggedMessage(getDriver()).clickBtnOK();

        HomePage homePage = new HomePage(getDriver());

        homePage.pause(2000);

        homePage.clickNavLinkLogout();

        homePage.pause(2000);
        //==========================================
    }
}