package ui_tests;

import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultPage;

import java.time.LocalDate;

public class CarSearchTests extends AppManager {
    HomePage homePage;

    @BeforeMethod
    public void goToHomePage() {
        homePage = new HomePage(getDriver());
    }

    @Test
    public void searchCarPositiveTest() {
        String city = "Ashkelon";
        LocalDate startDate = LocalDate.now().plusDays(2);
        LocalDate endDate = LocalDate.now().plusDays(8);
        homePage.fillSearchForm(city, startDate, endDate);
        homePage.clickBtnSubmit();

        Assert.assertTrue(new SearchResultPage(getDriver())
                .isTextInSearchResult("No available cars in"));
    }

    @Test
    public void searchCarEmptyDatesFieldNegativeTest() {
        String city = "Ashkelon";
        homePage.fillSearchForm(city, null, null);

        homePage.clickBtnSubmitNoJS();

        Assert.assertTrue(homePage.isTextPresentsInError("Dates are required"));
    }

    @Test
    public void searchCarPlusOneYearNegativeTest() {
        String city = "Ashkelon";
        LocalDate startDate = LocalDate.now().plusYears(1).plusDays(2);
        LocalDate endDate = LocalDate.now().plusYears(1).plusDays(8);
        homePage.fillSearchForm(city, startDate, endDate);

        homePage.clickBtnSubmitNoJS();

        Assert.assertTrue(homePage.isTextPresentsInError("You can't pick date after one year"));
    }

    @Test
    public void searchCarMinusOneMonthNegativeTest() {
        String city = "Ashkelon";
        LocalDate startDate = LocalDate.now().minusMonths(1).plusDays(2);
        LocalDate endDate = LocalDate.now().minusMonths(1).plusDays(8);
        homePage.fillSearchForm(city, startDate, endDate);

        homePage.clickBtnSubmitNoJS();

        Assert.assertTrue(homePage.isTextPresentsInError("You can't pick date before today"));
    }

    @Test
    public void searchCarIncorrectDatesOrderNegativeTest() {
        String city = "Ashkelon";
        LocalDate startDate = LocalDate.now().plusDays(15);
        LocalDate endDate = LocalDate.now().plusDays(5);
        homePage.fillSearchForm(city, startDate, endDate);

        homePage.clickBtnSubmitNoJS();

        Assert.assertTrue(homePage.isTextPresentsInError("Second date must be after first date"));
    }


}