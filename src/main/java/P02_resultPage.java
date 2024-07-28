import io.qameta.allure.Step;
import org.openqa.selenium.*;

public class P02_resultPage {
    WebDriver driver;

    P02_resultPage(WebDriver driver){
        this.driver = driver;
    }
    private By closeSignInAlert = By.xpath("//button[@aria-label=\"Dismiss sign-in info.\"]");
    private By close2ndSignInAlert = By.xpath("//div[@id=\"close\"]");


    @Step("Scrolling to Result")
    public void scrollTo2ndResult() throws InterruptedException {
        if (P01_homePage.alertStatus){
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
        }
        Thread.sleep(500);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 400)");
    }

    @Step("Dismiss Login Alert if Appeared")
    public void dismissLoginAlert(){
        driver.switchTo().frame(getSignInFrame());
        driver.findElement(close2ndSignInAlert).click();
        driver.switchTo().defaultContent();
    }

    @Step("Open Result Details")
    public void open2ndHotelDetails(){
        get2ndResultTitle().click();
        String mainWindowHandle = driver.getWindowHandle();
        String newWindowHandle = "";
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(mainWindowHandle)) {
                newWindowHandle = handle;
                break;
            }
        }
        driver.switchTo().window(newWindowHandle);
    }

    public WebElement get2ndResultRating(){
        try {
            return driver.findElement(By.xpath("((//div[@data-testid=\"review-score\"])[2]/div)[1]"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public WebElement get2ndResultRatingText(){
        try {
            return driver.findElement(By.xpath("(((//div[@data-testid=\"review-score\"])[2]/div)[2]/div)[1]"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public WebElement get2ndResultTitle(){
        try {
            return driver.findElement(By.xpath("(//a[@data-testid=\"title-link\"]/div[@data-testid=\"title\"])[2]"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public WebElement get2ndResultPrice(){
        try {
            return driver.findElement(By.xpath("(//span[@data-testid=\"price-and-discounted-price\"])[2]"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public WebElement get2ndResultImage(){
        try {
            return driver.findElement(By.xpath("(//a[@data-testid=\"property-card-desktop-single-image\"])[2]/img"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public WebElement getSignInFrame(){
        try {
            return driver.findElement(By.xpath("//iframe[@title=\"مربع حوار تسجيل الدخول باستخدام حساب Google\" or @title=\"Sign in with Google Dialog\"]"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
