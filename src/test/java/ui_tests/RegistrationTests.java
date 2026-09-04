package ui_tests;

import data_providers.UserDataProvider;
import dto.UserData;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.PopUpMessage;
import pages.SignUpPage;
import utils.TestNGListener;
import utils.enums.NavBar;

import static utils.UserFactory.*;

@Listeners(TestNGListener.class)

public class RegistrationTests extends AppManager {
    SignUpPage signUpPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToSignUpPage() {
        new HomePage(getDriver())
                .clickNavLink(NavBar.SIGN_UP);
        signUpPage = new SignUpPage(getDriver());
    }

    @Test
    public void registrationPositiveTest() {
        UserData user = positiveUser();

        signUpPage.fillRegistrationForm(user);
        signUpPage.setCheckboxAgreement(); //using JavaScript
        signUpPage.clickBtnSubmit();

        Assert.assertTrue(new PopUpMessage(getDriver())
                .isTextInMessage("You are logged in success"));
    }

    @Test
    public void registrationPositiveTest2() {
        UserData user = positiveUser();

        signUpPage.fillRegistrationForm(user);
        signUpPage.setCheckboxAgreementWithActions();
        signUpPage.clickBtnSubmit();

        Assert.assertTrue(new PopUpMessage(getDriver())
                .isTextInMessage("You are logged in success"));
    }

    @Test(dataProvider = "wrongRegistrationDataProvider",
            dataProviderClass = UserDataProvider.class)
    public void registrationIncorrectPasswordNegativeTest(UserData user) {
        signUpPage.fillRegistrationForm(user);
        signUpPage.setCheckboxAgreement();
        signUpPage.clickBtnSubmit();

        softAssert.assertTrue(signUpPage
                .isTextPresentsInError("Password must contain 1 " +
                        "uppercase letter, 1 lowercase letter, 1 " +
                        "number and one special symbol of [@$#^&*!]"));
        softAssert.assertFalse(signUpPage.validateIsBtnSubmitEnabled());
        softAssert.assertAll();
    }
}
