import io.qameta.allure.Allure;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.ConfigLoader;
import utils.DriverManager;
import utils.JSONUtils;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

import static org.openqa.selenium.remote.Browser.*;
import static utils.DriverManager.getBrowser;
import static utils.ScreenshotUtils.takeScreenShot;

public class T00_Hooks {
    public static JSONUtils testData;
//    public static WebDriver driver;


    @BeforeSuite
    public void setUp() {
        DriverManager.setDriver(getBrowser(ConfigLoader.getInstance().getBrowser()));
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().get(ConfigLoader.getInstance().getURL());
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public static void attachScreenShot(ITestResult result) throws IOException {
//        if (ITestResult.SUCCESS != result.getStatus()) {
        takeScreenShot(result);
        Allure.attachment(result.getName() + ".png", Files.newInputStream(Paths.get("./screenshots/" + result.getName() + ".png")));
    }

    @AfterSuite
    public static void tearDown() {
        DriverManager.getDriver().quit();
        try (BufferedReader br = new BufferedReader(new FileReader("generate_allure_report.bat"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getTestData(String jsonPath) throws FileNotFoundException {
        return testData.getTestData(jsonPath);
    }

}
