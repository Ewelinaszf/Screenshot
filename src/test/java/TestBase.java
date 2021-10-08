import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TestBase {

    public static WebDriver driver;
    public ExtentReports extent;
    public ExtentTest extentTest;
    ExtentHtmlReporter htmlReporter;

    @BeforeMethod
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "C:/drivers/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @BeforeTest
    public void setExtent() {
        extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/extentReport.html", true);
        extent.addSystemInfo("User Name", "Ewelina Szymaniak");
        extent.addSystemInfo("Environment", "QA");


    }

    @AfterTest
    public void endReport() {
        extent.flush();
        extent.close();
    }


    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {

            extentTest.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
            extentTest.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
            String screenshotPath = getScreenshot();


            extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath));


            extentTest.log(LogStatus.ERROR, extentTest.addScreenCapture("//ABOLUTE/FailedTestsScreenshots/chinese.jpg"));
            extentTest.log(LogStatus.ERROR, extentTest.addScreenCapture("//FailedTestsScreenshots/chinese.jpg"));
            extentTest.log(LogStatus.ERROR, extentTest.addScreenCapture("C://Users//szymanie//IdeaProjects//Screenshot//FailedTestsScreenshots/chinese.jpg"));
            extentTest.log(LogStatus.ERROR, extentTest.addScreenCapture("FailedTestsScreenshots/chinese.jpg"));

            String path = System.getProperty("user.dir") + "/screenshots/"  + "chinese.jpg";
            extentTest.log(LogStatus.ERROR, extentTest.addScreenCapture(path));



        } else if (result.getStatus() == ITestResult.SKIP) {
            extentTest.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.log(LogStatus.PASS, "Test Case Passed is " + result.getName());
            String screenshotPath = getScreenshot();
            extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath));

        }
        extent.endTest(extentTest);

        driver.close();
        driver.quit();

    }

    public static String getScreenshot() throws IOException {
        String dateName = new SimpleDateFormat("yyyy-MM-dd,hh-mm-ss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + dateName + ".jpg";


        File findDestination = new File(destination);
        System.out.println("Source " + source);
        System.out.println("Destination " + destination);
        FileUtils.copyFile(source, findDestination);

        System.out.println("CUrrent time " + System.currentTimeMillis());
        System.out.println("Destination " + destination);
        return destination;
    }


}