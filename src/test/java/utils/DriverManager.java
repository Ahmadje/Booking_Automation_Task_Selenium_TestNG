package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static utils.DriverManager.BrowserName.*;

public final class DriverManager {

    private DriverManager() {
    }

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driverref) {
        driver.set(driverref);
    }

    public static void unload() {
        driver.remove();
    }

    public static WebDriver getBrowser(BrowserName browserName) throws IllegalArgumentException {
        switch (browserName) {
            case CHROME:
                return new ChromeDriver();
            case FIREFOX:
                return new FirefoxDriver();
            case EDGE:
                return new EdgeDriver();
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);

        }
//        if (browserName.equalsIgnoreCase(FIREFOX.name())) {
//            FirefoxDriver firefoxDriver = new FirefoxDriver();
//            return firefoxDriver;
//        } else if (browserName.equalsIgnoreCase(EDGE.name())) {
//            EdgeDriver edgeDriver = new EdgeDriver();
//            return edgeDriver;
//        } else {
//            ChromeDriver chromeDriver = new ChromeDriver();
//            return chromeDriver;
//        }
    }

    public enum BrowserName {
        CHROME("chrome"), FIREFOX("firefox"), EDGE("edge");

        private final String browserName;

        BrowserName(String browserName) {
            this.browserName = browserName;
        }
    }

}
