import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class P02_resultPage {
    WebDriver driver;

    P02_resultPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Scrolling to Result")
    public void scrollTo2ndResult() throws InterruptedException {
        Thread.sleep(500);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 400)");
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
        return driver.findElement(By.xpath("((//div[@data-testid=\"review-score\"])[2]/div)[1]"));
    }

    public WebElement get2ndResultRatingText(){
        return driver.findElement(By.xpath("(((//div[@data-testid=\"review-score\"])[2]/div)[2]/div)[1]"));
    }

    public WebElement get2ndResultTitle(){
        return driver.findElement(By.xpath("(//a[@data-testid=\"title-link\"]/div[@data-testid=\"title\"])[2]"));
    }
    public WebElement get2ndResultPrice(){
        return driver.findElement(By.xpath("(//span[@data-testid=\"price-and-discounted-price\"])[2]"));
    }
    public WebElement get2ndResultImage(){
        return driver.findElement(By.xpath("(//a[@data-testid=\"property-card-desktop-single-image\"])[2]/img"));
    }
}
