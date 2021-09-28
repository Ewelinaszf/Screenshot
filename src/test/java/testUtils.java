import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class testUtils extends TestBase{
    public static String screenshotName;
    public void getScreenshot() throws IOException {
        Date currentdate = new Date();
        screenshotName= currentdate.toString().replace(" ", "-").replace(":", "-");
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile, new File(System.getProperty("user.dir") + "\\src\\test\\java\\screenshot2\\" + screenshotName + ".png"));

    }
}
