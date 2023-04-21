import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
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

public class ExFive {

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
    public void saveArticlesToList() {

        String searchField = "Search Wikipedia";
        String titleFirstArticle = "Far Cry 4";
        String subtitleFirstArticle = "2014 video game";
        String titleSecondArticle = "Green Hell";
        String subtitleSecondArticle = "2019 video game";
        String nameList = "Games";

        waitForElementAndClick(
                By.xpath("//*[@text = '" + searchField + "']"),
                "Cannot find search field",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[@text = '" + searchField + "']"),
                titleFirstArticle,
                "Cannot enter word to search field",
                10);

        waitForElementAndClick(
                By.xpath("//*[@text = '" + subtitleFirstArticle + "']"),
                "Cannot find and click article in search",
                15);

        waitForElementPresent(
                By.xpath("//*[@text = '" + titleFirstArticle + "']"),
                "Cannot find opened article",
                15);

        waitForElementAndClick(
                By.xpath("//*[@text = 'Save']"),
                "Cannot click save button for article",
                15);

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/snackbar_action']"),
                "Cannot add article to list",
                15);

        waitForElementAndSendKeys(
                By.xpath("//*[@text = 'Name of this list']"),
                nameList,
                "Cannot enter name for save article",
                10);

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'android:id/button1']"),
                "Cannot click OK",
                15);

        waitForElementAndClick(
                By.xpath("//*[@text = '" + searchField + "']"),
                "Cannot find search field",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[@text = '" + searchField + "']"),
                titleSecondArticle,
                "Cannot enter word to search field",
                10);

        waitForElementAndClick(
                By.xpath("//*[@text = '" + subtitleSecondArticle + "']"),
                "Cannot find and click article in search",
                15);

        waitForElementPresent(
                By.xpath("//*[@text = '" + titleSecondArticle + "']"),
                "Cannot find opened article",
                15);

        waitForElementAndClick(
                By.xpath("//*[@text = 'Save']"),
                "Cannot click save button for article",
                15);

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/snackbar_action']"),
                "Cannot add article to list",
                15);

        waitForElementAndClick(
                By.xpath("//*[@text = '" + nameList + "']"),
                "Cannot find package list",
                15);

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/snackbar_action']"),
                "Cannot go to saved list",
                5);

        swipeElementToLeft(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_image'][@content-desc = 'Image: " + titleFirstArticle + "']"),
                "Cannot delete article");

        waitForElementNotPresent(
                By.xpath("//*[@text = '" + titleFirstArticle + "']"),
                "Article displayed",
                5);

        waitForElementPresent(
                By.xpath("//*[@text = '" + subtitleSecondArticle + "']"),
                "Article displayed",
                5);

        waitForElementAndClick(
                By.xpath("//*[@text = '" + subtitleSecondArticle + "']"),
                "Cannon find saved article in list",
                5);

        WebElement element = waitForElementPresent(
                By.xpath("//*[@text = '" + titleSecondArticle + "']"),
                "Faled",
                5);

        String actualTitle = element.getAttribute("text");

        Assert.assertEquals("Test passed", titleSecondArticle, actualTitle);
    }

    protected void swipeElementToLeft(By by, String errorMessage) {
        WebElement element = waitForElementPresent(by, errorMessage, 15);
        int leftX = element.getLocation().getX();
        int rightX = leftX + element.getSize().getWidth();
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getWidth();
        int middleY = (upperY + lowerY) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(rightX, middleY)
                .waitAction(150)
                .moveTo(0, middleY)
                .release()
                .perform();
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

    private WebElement waitForElementAndSendKeys(By by, String value, String errorMessage, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeOutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String errorMessage, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by));
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
