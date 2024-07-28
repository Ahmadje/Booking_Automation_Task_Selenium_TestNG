import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class P03_hotelDetails {

    WebDriver driver;

    public P03_hotelDetails(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getHotelTitle() {
        return driver.findElement(By.cssSelector("div[data-capla-component-boundary=\"b-property-web-property-page/PropertyHeaderName\"]"));
    }
}
