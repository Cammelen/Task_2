import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ExSix extends CoreTestCase {

    @Test
    public void testAssertElementPresent() {

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Far Cry 4");
        searchPageObject.clickByArticleWithSubString("2014 video game");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        String actualTitle = articlePageObject.getArticleTitle();

        assertEquals(
                "Test failed",
                "Far Cry 4",
               actualTitle);
    }
}
