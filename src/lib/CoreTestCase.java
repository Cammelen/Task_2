package lib;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;

public class CoreTestCase extends TestCase {

    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception {

        super.setUp();

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "and80");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:/Users/megorova/Desktop/1/MyJavaFiles/JavaAutomation/Task_2/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL(AppiumURL), capabilities);

        startedPage();
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();

        super.tearDown();
    }

    private void startedPage() {
        driver.rotate(ScreenOrientation.PORTRAIT);
        WebElement continues = driver.findElementByXPath("//*[contains(@text, 'CONTINUE')]");
        continues.click();
        continues.click();
        continues.click();
        WebElement getStarted = driver.findElementByXPath("//*[contains(@text, 'GET STARTED')]");
        getStarted.click();
    }

    protected void rotateScreenPortrait() {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(int seconds) {
        driver.runAppInBackground(seconds);
    }
}
