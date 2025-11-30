package uk.gov.pay.api.model.search.card;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.pay.api.model.links.SearchNavigationLinks;

public class SearchRefundsResultsDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SearchRefundsResults#SearchRefundsResults(int, int, int, List,
   *       SearchNavigationLinks)}
   *   <li>{@link SearchRefundsResults#getCount()}
   *   <li>{@link SearchRefundsResults#getLinks()}
   *   <li>{@link SearchRefundsResults#getPage()}
   *   <li>{@link SearchRefundsResults#getResults()}
   *   <li>{@link SearchRefundsResults#getTotal()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SearchRefundsResults.<init>(int, int, int, List, SearchNavigationLinks)",
    "int SearchRefundsResults.getCount()",
    "SearchNavigationLinks SearchRefundsResults.getLinks()",
    "int SearchRefundsResults.getPage()",
    "List SearchRefundsResults.getResults()",
    "int SearchRefundsResults.getTotal()"
  })
  public void testGettersAndSetters() {
    // Arrange
    ArrayList<RefundForSearchRefundsResult> results = new ArrayList<>();
    SearchNavigationLinks links = new SearchNavigationLinks();

    // Act
    SearchRefundsResults actualSearchRefundsResults =
        new SearchRefundsResults(1, 3, 1, results, links);
    int actualCount = actualSearchRefundsResults.getCount();
    SearchNavigationLinks actualLinks = actualSearchRefundsResults.getLinks();
    int actualPage = actualSearchRefundsResults.getPage();
    List<RefundForSearchRefundsResult> actualResults = actualSearchRefundsResults.getResults();

    // Assert
    assertEquals(1, actualPage);
    assertEquals(1, actualSearchRefundsResults.getTotal());
    assertEquals(3, actualCount);
    assertTrue(actualResults.isEmpty());
    assertSame(results, actualResults);
    assertSame(links, actualLinks);
  }
}
