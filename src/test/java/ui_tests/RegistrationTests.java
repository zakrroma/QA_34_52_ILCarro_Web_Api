package ui_tests;

import dto.UserData;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.SignUpPage;
import utils.UserFactory;

public class RegistrationTests extends AppManager {
    SignUpPage signUpPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToLoginPage() {
        new HomePage(getDriver())
                .clickNavLinkSignUp();
        signUpPage = new SignUpPage(getDriver());
    }

    @Test
    public void registrationPositiveTest() {
        UserData user = UserFactory.positiveUser();

        signUpPage.fillRegistrationForm(user);
        //signUpPage.setCheckboxAgreement();
        //signUpPage.clickBtnSubmit();

        //Assert.assertTrue(signUpPage.isMessageRegisteredDisplayed());
    }
}
