package lib.ui;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUIPageObject extends MainPageObject {

    private static final String
            MY_LIST_SAVED = "//*[@content-desc = 'Saved']";

    public NavigationUIPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void clickSaved() {
        this.waitForElementAndClick(By.xpath(MY_LIST_SAVED), "Cannot go to saved list", 5);
    }
}
