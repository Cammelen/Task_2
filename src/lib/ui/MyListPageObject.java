package lib.ui;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListPageObject extends MainPageObject{

    private static final String
            FOLDER_BY_NAME_TPL = "//*[@resource-id = 'org.wikipedia:id/item_title'][@text = '{FOLDER_NAME}']",
            ARTICLE_BY_TITLE_TPL = "//*[@resource-id = '{TITLE}']";

    private static String getFolderXPAthByName(String nameOfFolder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", nameOfFolder);
    }

    private static String getSaveArticleXPathByTitle(String articleTitle) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", articleTitle);
    }

    public MyListPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void openFolderByName(String nameOfFolder) {
        String folderNameXPath = getFolderXPAthByName(nameOfFolder);
        this.waitForElementAndClick(By.xpath(folderNameXPath), "Cannon find folder by name " + nameOfFolder, 5);
    }

    public void swipeByArticleToDelete(String articleDelete) {
        String articleXPathDelete = getSaveArticleXPathByTitle(articleDelete);
        this.waitForArticleToAppearByTitle(articleDelete);
        this.swipeElementToLeft(By.xpath(articleXPathDelete), "Cannot delete article");
        this.waitForArticleToDisappearByTitle(articleXPathDelete);
    }

    public void waitForArticleToDisappearByTitle(String articleTitle) {
        String articleXPath = getSaveArticleXPathByTitle(articleTitle);
        this.waitForElementNotPresent(By.xpath(articleXPath), "Saved article still present with title " + articleTitle, 15);
    }

    public void waitForArticleToAppearByTitle(String articleTitle) {
        String articleXPath = getSaveArticleXPathByTitle(articleTitle);
        this.waitForElementPresent(By.xpath(articleXPath), "Cannot find saved article by title " + articleTitle, 15);
    }
}
