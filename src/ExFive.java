import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUIPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ExFive extends CoreTestCase {

    @Test
    public void testSaveArticlesToList() {

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Far Cry 4");
        searchPageObject.clickByArticleWithSubString("2014 video game");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();

        String nameOfFolder = "Games";
        String articleDelete = "org.wikipedia:id/page_list_item_image";

        articlePageObject.addArticleToMyList(nameOfFolder);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Green Hell");
        searchPageObject.clickByArticleWithSubString("2019 video game");
        articlePageObject.waitForTitleElement();
        articlePageObject.addFewArticleToMyList("Games");
        articlePageObject.closeFewArticle();

        NavigationUIPageObject navigationUIPageObject = new NavigationUIPageObject(driver);
        navigationUIPageObject.clickSaved();

        MyListPageObject myListPageObject = new MyListPageObject(driver);
        myListPageObject.openFolderByName(nameOfFolder);
        myListPageObject.swipeByArticleToDelete(articleDelete);
    }
}
