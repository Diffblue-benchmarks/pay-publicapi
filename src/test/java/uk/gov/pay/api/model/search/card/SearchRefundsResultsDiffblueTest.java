package uk.gov.pay.api.model.search.card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.model.links.SearchNavigationLinks;

class SearchRefundsResultsDiffblueTest {
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
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SearchRefundsResults.<init>(int, int, int, List, SearchNavigationLinks)",
    "int SearchRefundsResults.getCount()",
    "SearchNavigationLinks SearchRefundsResults.getLinks()",
    "int SearchRefundsResults.getPage()",
    "List SearchRefundsResults.getResults()",
    "int SearchRefundsResults.getTotal()"
  })
  void testGettersAndSetters() {
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
