package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


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

/*
    public static WebDriver getBrowser(String browserName) throws IllegalArgumentException {
        return switch (browserName) {
            case "chrome" -> new ChromeDriver();
            case "firefox" -> new FirefoxDriver();
            case "edge" -> new EdgeDriver();
            default -> throw new IllegalArgumentException("Unsupported browser: " + browserName);
        };
*/


    ///// USING ENUMS
    public static WebDriver selectBrowser(BrowsersEnum browserName) throws IllegalArgumentException {
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
    }
}
