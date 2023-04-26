import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ExSecondAndThird extends CoreTestCase {

    @Test
    public void testSearchTextInInputField() {

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        String actualText = searchPageObject.waitForSearchField();

        assertEquals(
                "Cannot find search field",
                "Search Wikipedia",
                actualText);
    }

    @Test
    public void testSearchArticlesAndClear() {

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("World Peace");
        searchPageObject.waitForSearchResult("World peace");
        searchPageObject.waitForSearchResult("World Peace Council");
        searchPageObject.waitForSearchClear("World Peace");
        searchPageObject.waitForNotPresentSearchCloseButton();
    }
}
