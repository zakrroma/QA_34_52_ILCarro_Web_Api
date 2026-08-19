package ui_tests;

import dto.UserData;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.PopUpMessage;
import pages.SignUpPage;

import static utils.UserFactory.*;

public class RegistrationTests extends AppManager {
    SignUpPage signUpPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToSignUpPage() {
        new HomePage(getDriver())
                .clickNavLinkSignUp();
        signUpPage = new SignUpPage(getDriver());
    }

    @Test
    public void registrationPositiveTest() {
        UserData user = positiveUser();

        signUpPage.fillRegistrationForm(user);
        signUpPage.setCheckboxAgreement();
        signUpPage.clickBtnSubmit();

        Assert.assertTrue(new PopUpMessage(getDriver())
                .isTextInMessage("You are logged in success"));
    }
}
