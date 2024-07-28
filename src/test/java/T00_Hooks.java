import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class T00_Hooks {
//    public static WebDriver driver;


    @BeforeSuite
    public void setUp() {
        DriverManager.setDriver(new ChromeDriver());
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().get("https://www.booking.com/");
        DriverManager.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public static void takeScreenShot(ITestResult result) throws IOException {
//        if (ITestResult.SUCCESS != result.getStatus()) {
            TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
            byte[] screenshotAs = ts.getScreenshotAs(OutputType.BYTES);
            Files.write(Paths.get("./screenshots/"+ result.getName() + ".png"),screenshotAs);
            Allure.attachment(result.getName() + ".png",Files.newInputStream(Paths.get("./screenshots/"+ result.getName() + ".png")));
//        }
//            File source = ts.getScreenshotAs(OutputType.FILE);
//            FileUtils.copyFile(source, new File("./screenshots/" + result.getName() + ".png"));
        }

    @AfterSuite
    public static void tearDown() {
        DriverManager.getDriver().quit();
    }
}
