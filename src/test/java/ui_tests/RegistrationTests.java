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

    @BeforeMethod
    public void goToSignUpPage() {
        logger.info("Starting registration test");
        new HomePage(getDriver())
                .clickNavLinkSignUp();
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

//        Assert.assertTrue(new PopUpMessage(getDriver())
//                .isTextInMessage("You are logged in success"));
    }
}
