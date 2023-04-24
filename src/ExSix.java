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


public class ExSix {

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
    public void testAssertElementPresent() {

        String searchField = "Search Wikipedia";
        String titleArticle = "Far Cry 4";
        String subtitleArticle = "2014 video game";

        waitForElementAndClick(
                By.xpath("//*[@text = '" + searchField + "']"),
                "Cannot find search field",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[@text = '" + searchField + "']"),
                titleArticle,
                "Cannot enter word to search field",
                10);

        waitForElementAndClick(
                By.xpath("//*[@text = '" + subtitleArticle + "']"),
                "Cannot find and click article in search",
                5);

        assertElementPresent(
                "Test failed",
                titleArticle,
               By.xpath("//*[@text = '" + titleArticle + "']"));
    }

    private void assertElementPresent(String errorMessage, String expectedTitle, By by ) {

        WebElement element = waitForElementPresent(by, "A title is not present");
        String actualTitle = element.getAttribute("text");
        if ( !actualTitle.equals(expectedTitle)) {
            String defaultMessage = "An element is not present";
            throw new AssertionError(defaultMessage + " ." + errorMessage);
        }
        Assert.assertEquals("", expectedTitle, actualTitle);
    }

    private WebElement waitForElementPresent(By by, String errorMessage, long timeOutInSeconds) {

        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by, String errorMessage) {
        return waitForElementPresent(by, errorMessage, 0);
    }

    private WebElement waitForElementAndClick(By by, String errorMessage, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeOutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String errorMessage, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeOutInSeconds);
        element.sendKeys(value);
        return element;
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
