import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "and80");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:/Users/megorova/Desktop/1/MyJavaFiles/JavaAutomation/Task_2/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        startedPage();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void searchTextInInputField() {

        waitForElementAndClick(
                By.xpath("//*[@text = 'Search Wikipedia']"),
                "Locator not found",
                5);

        WebElement searchText = waitForElementPresent(

                By.xpath("//*[@text = 'Search Wikipedia']"),
                "Locator not found",
                5);

        String actualText = searchText.getAttribute("text");

        assertElementHasText(
                "Test failed",
                "Search Wikipedia",
                actualText);
    }

    private WebElement waitForElementPresent(By by, String errorMessage, long timeOutInSeconds) {

        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementAndClick(By by, String errorMessage, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeOutInSeconds);
        element.click();
        return element;
    }

    private void assertElementHasText(String errorMessage, String expectedText,  String actualText) {

        Assert.assertEquals(
                errorMessage,
                expectedText,
                actualText);
    }

    private void startedPage() {
        WebElement continues = driver.findElementByXPath("//*[contains(@text, 'CONTINUE')]");
        continues.click();
        continues.click();
        continues.click();
        WebElement getStarted = driver.findElementByXPath("//*[contains(@text, 'GET STARTED')]");
        getStarted.click();
    }
}
