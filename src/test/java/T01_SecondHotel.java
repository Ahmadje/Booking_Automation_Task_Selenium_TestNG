import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class T01_SecondHotel extends T00_Hooks {
    P01_homePage home;
    P02_resultPage result;
    P03_hotelDetails details;
    String checkInDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    String checkOutDate = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    String resultTitle;

    @Test()
    public void search() throws InterruptedException {
        home = new P01_homePage(DriverManager.getDriver());
        home.chooseDestination("Alexandria");
        home.selectDate(checkInDate, checkOutDate);
        home.clickSubmitBtn();
        result = new P02_resultPage(DriverManager.getDriver());
        result.scrollTo2ndResult();

        //1st Assertion of Result page
        SoftAssert soft = new SoftAssert();
        soft.assertTrue(DriverManager.getDriver().getTitle().contains("Hotels in Alexandria"), "Verify result page title");
        soft.assertTrue(DriverManager.getDriver().getCurrentUrl().contains("result"), "Verify result page url");

        //2nd Assertion of 2nd Result Rating Good or Above
        System.out.println("2nd Result Rating: " + result.get2ndResultRating().getText());
        List<String> ratings = new ArrayList<>(Arrays.asList("Good", "Very Good", "Wonderful", "Excellent"));
        soft.assertTrue(ratings.contains(result.get2ndResultRatingText().getText()), "Rating is Below Good.");

        //Getting 2nd result Name and Price
        resultTitle = result.get2ndResultTitle().getText();
        System.out.println("2nd Result Name: " + resultTitle);
        System.out.println("2nd Result Price: " + result.get2ndResultPrice().getText());

        //3rd Assertion of 2nd Result Picture
        soft.assertTrue(result.get2ndResultImage().isDisplayed(), "2nd Result Image not Displayed");
        soft.assertAll();
    }

    @Test(dependsOnMethods = {"search"})
    public void HotelDetails() {
        new P02_resultPage(DriverManager.getDriver()).open2ndHotelDetails();
        Assert.assertTrue(new P03_hotelDetails(DriverManager.getDriver()).getHotelTitle().getText().contains(resultTitle));
    }
}

