package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
            TITLE = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.widget.TextView[1]",
            FOOTER_ELEMENT = "//*[@text = 'View article in browser']",
            MY_LIST_SAVE_BUTTON = "//*[@text = 'Save']",
            ADD_TO_MY_LIST = "//*[@resource-id = 'org.wikipedia:id/snackbar_action']",
            MY_LIST_NAME_INPUT = "//*[@text = 'Name of this list']",
            SEARCH_MY_LIST_NAME = "//*[@text = '{SUBSTRING}']",
            MY_LIST_OK_BUTTON = "//*[@resource-id = 'android:id/button1']",
            CLOSE_ARTICLE_BUTTON_FIRST = "//*[@content-desc = 'Navigate up']",
            CLOSE_LIST_OF_ARTICLES_BUTTON_SECOND = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.ImageButton";

    /* TEMPLATES METHODS */
    private static String createAndFindMyList(String subString) {
        return SEARCH_MY_LIST_NAME.replace("{SUBSTRING}", subString);
    }
    /*TEMPLATES METHODS */

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(By.xpath(TITLE), "Cannot find article title on page", 15);
    }

    public WebElement withoutWaitForTitleElement() {
        return this.waitForElementPresent(By.xpath(TITLE), "Cannot find article title on page" );
    }

    public String getArticleTitle() {
        WebElement titleElement = withoutWaitForTitleElement();
        return titleElement.getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeUtoFindElement(By.xpath(FOOTER_ELEMENT), "Cannot find the end of article", 20);
    }

    public void addArticleToMyList(String nameOfFolder) {
        this.waitForElementAndClick(By.xpath(MY_LIST_SAVE_BUTTON), "Cannot click save button for article", 15);
        this.waitForElementAndClick(By.xpath(ADD_TO_MY_LIST), "Cannot add article to list", 15);
        this.waitForElementAndSendKeys(By.xpath(MY_LIST_NAME_INPUT), nameOfFolder, "Cannot enter name for save article", 10);
        this.waitForElementAndClick(By.xpath(MY_LIST_OK_BUTTON), "Cannot click OK", 15);
    }

    public void addFewArticleToMyList(String subString) {
        String nameOfMyList = createAndFindMyList(subString);
        this.waitForElementAndClick(By.xpath(MY_LIST_SAVE_BUTTON), "Cannot click save button for article", 15);
        this.waitForElementAndClick(By.xpath(ADD_TO_MY_LIST), "Cannot add article to list", 15);
        this.waitForElementAndClick(By.xpath(nameOfMyList), "Cannot find my list", 15);
    }

    public void closeArticle() {
        this.waitForElementAndClick(By.xpath(CLOSE_ARTICLE_BUTTON_FIRST), "Cannot click to the first arrow", 5);
        this.waitForElementAndClick(By.xpath(CLOSE_LIST_OF_ARTICLES_BUTTON_SECOND), "Cannot click to the second arrow", 15);
    }

    public void closeFewArticle() {
        this.waitForElementAndClick(By.xpath(CLOSE_ARTICLE_BUTTON_FIRST), "Cannot click to the first arrow", 5);
        this.waitForElementAndClick(By.xpath(CLOSE_ARTICLE_BUTTON_FIRST), "Cannot click to the first arrow", 5);
        this.waitForElementAndClick(By.xpath(CLOSE_LIST_OF_ARTICLES_BUTTON_SECOND), "Cannot click to the second arrow", 15);
    }
}
