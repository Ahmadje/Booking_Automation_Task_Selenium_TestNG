
package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class ScreenshotUtils {

    private ScreenshotUtils() {
    }

    public static void takeScreenShot(ITestResult result) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
        byte[] screenshotAs = ts.getScreenshotAs(OutputType.BYTES);
        Files.write(Paths.get("./screenshots/" + result.getName() + ".png"), screenshotAs);
    }

}
