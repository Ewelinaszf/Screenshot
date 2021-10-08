import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class MyFirstTest extends TestBase {

    @Test
    public void myFirstTest()  {

        extentTest= extent.startTest("MyFirstTest");
        driver.navigate().to("http://google.pl");

        driver.findElement(By.name("q")).sendKeys("JavaStart");
        driver.findElement(By.name("q")).submit();

        String pageTitle = driver.getTitle();
        assertTrue(pageTitle.contains("JavaStart"));
    }
}
