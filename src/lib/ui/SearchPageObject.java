package lib.ui;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INIT_ELEMENT = "//*[@text = 'Search Wikipedia']",
            SEARCH_INPUT = "//*[@text = 'Search Wikipedia']",
            SEARCH_CLOSE_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@text = '{SUBSTRING}']",
            SEARCH_EMPTY_RESULTS_ELEMENT = "//*[@resource-id = 'org.wikipedia:id/results_text'][@text = 'No results']",
            SEARCH_RESULT_ELEMENT = "//*[@resource-id = 'org.wikipedia:id/search_results_display']/*[@resource-id = 'org.wikipedia:id/search_results_list']";

    public SearchPageObject(AppiumDriver driver){
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String subString) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", subString);
    }
    /*TEMPLATES METHODS */

    public void initSearchInput() {
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find searchinput after clicking search init element");
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 5);
    }

    public void typeSearchLine(String searchLine) {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), searchLine, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String subString) {
        String searchResultXPath = getResultSearchElement(subString);
        this.waitForElementPresent(By.xpath(searchResultXPath), "Cannot find search result with result " + subString);
    }

    public void clickByArticleWithSubString(String subString) {
        String searchResultXPath = getResultSearchElement(subString);
        this.waitForElementAndClick(By.xpath(searchResultXPath), "Cannot find and click search result with result " + subString, 10);
    }

    public void waitForSearchClear(String subString) {
        String searchResultXPath = getResultSearchElement(subString);
        this.waitForElementAndClear(By.xpath(searchResultXPath), "Cannot find search clear with clear " + subString, 10);
    }

    public void waitForNotPresentSearchCloseButton() {
        this.waitForElementNotPresent(By.id(SEARCH_CLOSE_BUTTON), "Search cancel button is still present", 10);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(By.xpath(SEARCH_RESULT_ELEMENT), "Cannot find anything by the request ", 15);
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultLabel() {
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULTS_ELEMENT), "Cannot find empty result element", 15);
    }

    public void waitForNotEmptyResultLabel() {
        this.waitForElementNotPresent(By.xpath(SEARCH_EMPTY_RESULTS_ELEMENT), "Find empty result element", 15);
    }

    public void assertThereIsNotResultOfSearch() {
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT), By.xpath(SEARCH_EMPTY_RESULTS_ELEMENT), "We've found some results by request ");
    }

    public String waitForSearchField() {
        WebElement actualtext = this.waitForElementPresent(By.xpath(SEARCH_INPUT), "Cannot find empty result element", 15);
        return actualtext.getAttribute("text");
    }
}
