package ui_tests;

import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;

import java.time.LocalDate;

public class CarSearchTests extends AppManager {
    HomePage homePage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod(alwaysRun = true)
    public void goToHomePage() {
        homePage = new HomePage(getDriver());
    }

    @Test
    public void searchCarWithInputPositiveTest() {
        String city = "Ashkelon";
        LocalDate startDate = LocalDate.now().plusDays(2);
        LocalDate endDate = LocalDate.now().plusDays(8);
        homePage.fillSearchForm(city, startDate, endDate);
        homePage.clickBtnSubmit();

        Assert.assertTrue(homePage
                .isUrlContainsText("/results"));
    }

    @Test
    public void searchCarWithCalendarPositiveTest() {
        String city = "Ashkelon";
        LocalDate startDate = LocalDate.now().plusDays(2);
        LocalDate endDate = LocalDate.now().plusDays(8);
        homePage.interactWithCalendar(city, startDate, endDate);
        homePage.clickBtnSubmit();

        Assert.assertTrue(homePage.isUrlContainsText("/results"));
    }

    @Test
    public void searchCarEmptyDatesFieldNegativeTest() {
        String city = "Ashkelon";
        homePage.fillSearchForm(city, null, null);

        homePage.clickBtnSubmitNoJS();

        Assert.assertTrue(homePage
                .isTextPresentsInError("Dates are required"));
    }

    @Test
    public void searchCarSameDatesNegativeTest() {
        String city = "Ashkelon";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();
        homePage.fillSearchForm(city, startDate, endDate);

        homePage.clickBtnSubmitNoJS();

        Assert.assertTrue(homePage
                .isTextPresentsInError("You can't book car for less than a day"));
    }

    @Test
    public void searchCarPlusOneYearNegativeTest() {
        String city = "Ashkelon";
        LocalDate startDate = LocalDate.now()
                .plusYears(1).plusDays(2);
        LocalDate endDate = LocalDate.now()
                .plusYears(1).plusDays(8);
        homePage.fillSearchForm(city, startDate, endDate);

        homePage.clickBtnSubmitNoJS();

        Assert.assertTrue(homePage
                .isTextPresentsInError("You can't pick date after one year"));
    }

    @Test
    public void searchCarMinusOneMonthNegativeTest() {
        String city = "Ashkelon";
        LocalDate startDate = LocalDate.now()
                .minusMonths(1).plusDays(2);
        LocalDate endDate = LocalDate.now()
                .minusMonths(1).plusDays(8);
        homePage.fillSearchForm(city, startDate, endDate);

        homePage.clickBtnSubmitNoJS();

        Assert.assertTrue(homePage
                .isTextPresentsInError("You can't pick date before today"));
    }

    @Test
    public void searchCarIncorrectDateOrderNegativeTest() {
        String city = "Ashkelon";
        LocalDate startDate = LocalDate.now().plusDays(15);
        LocalDate endDate = LocalDate.now().plusDays(5);
        homePage.fillSearchForm(city, startDate, endDate);

        homePage.clickBtnSubmitNoJS();

        softAssert.assertTrue(homePage
                .isTextPresentsInError("Second date must be after first date"));
        softAssert.assertTrue(homePage
                .isTextPresentsInError("You can't book car for less than a day"));
        softAssert.assertAll();
    }

    @Test
    public void searchCarWithCalendarSameDatesNegativeTest() {
        String city = "Ashkelon";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();
        homePage.interactWithCalendar(city, startDate, endDate);

        homePage.clickBtnSubmitNoJS();

        Assert.assertTrue(homePage
                .isTextPresentsInError2("You can't book car for less than a day"));
    }
}