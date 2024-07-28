import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.*;

import java.time.Duration;

public class P01_homePage extends basePage {

public P01_homePage(WebDriver driver) {
    super(driver);
}

    private By closeSignInAlert = By.xpath("//button[@aria-label=\"Dismiss sign-in info.\"]");
    private By close2ndSignInAlert = By.xpath("//div[@id=\"close\"]");
    private By destinationEle = By.id(":rh:");
    private By datepickerEle = By.xpath("//div[@data-testid=\"searchbox-dates-container\"]/span");
    private By submitBtn = By.xpath("//span[text()='Search']");


    @Step("1- Dismiss Login Alert if Appeared")
    public void dismissLoginAlert(){
        driver.switchTo().frame(getSignInFrame());
        driver.findElement(close2ndSignInAlert).click();
        driver.switchTo().defaultContent();
    }

    @Step("2- Enter Destination")
    public void chooseDestination(String destination) {
        try {
            if (driver.findElement(closeSignInAlert).isDisplayed() == true) {
                driver.findElement(closeSignInAlert).click();
            }
            if (driver.findElement(closeSignInAlert).isDisplayed() == true) {
                dismissLoginAlert();
            }
        }
        catch (NoSuchElementException e) {
            System.out.println("Exception Message: " + e.getMessage());
        }
        driver.findElement(destinationEle).sendKeys(destination);
        driver.findElement(destinationEle).sendKeys(Keys.TAB);
    }

    @Step("3- Select Check-in & Check-out Dates")
    public void selectDate(String checkInDate,String checkOutDate){
        String currentDayLocator = "//span[@data-date='" +checkInDate +"']";
        driver.findElement(datepickerEle).click();
        driver.findElement(By.xpath(currentDayLocator)).click();
        String tomorrowDayLocator = "//span[@data-date='" +checkOutDate +"']";
        driver.findElement(By.xpath(tomorrowDayLocator)).click();
    }

    public P02_resultPage clickSubmitBtn(){
        driver.findElement(submitBtn).click();
        return new P02_resultPage(driver);
    }

    public WebElement getSignInFrame(){
        return driver.findElement(By.xpath("//iframe[@title=\"مربع حوار تسجيل الدخول باستخدام حساب Google\" or @title=\"Sign in with Google Dialog\"]"));
    }

}
