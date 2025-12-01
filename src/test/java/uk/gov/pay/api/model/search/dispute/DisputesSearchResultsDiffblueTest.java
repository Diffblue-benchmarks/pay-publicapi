package uk.gov.pay.api.model.search.dispute;

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

class DisputesSearchResultsDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DisputesSearchResults#DisputesSearchResults(int, int, int, List,
   *       SearchNavigationLinks)}
   *   <li>{@link DisputesSearchResults#getCount()}
   *   <li>{@link DisputesSearchResults#getLinks()}
   *   <li>{@link DisputesSearchResults#getPage()}
   *   <li>{@link DisputesSearchResults#getResults()}
   *   <li>{@link DisputesSearchResults#getTotal()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DisputesSearchResults.<init>(int, int, int, List, SearchNavigationLinks)",
    "int DisputesSearchResults.getCount()",
    "SearchNavigationLinks DisputesSearchResults.getLinks()",
    "int DisputesSearchResults.getPage()",
    "List DisputesSearchResults.getResults()",
    "int DisputesSearchResults.getTotal()"
  })
  void testGettersAndSetters() {
    // Arrange
    ArrayList<DisputeForSearchResult> results = new ArrayList<>();
    SearchNavigationLinks links = new SearchNavigationLinks();

    // Act
    DisputesSearchResults actualDisputesSearchResults =
        new DisputesSearchResults(1, 3, 1, results, links);
    int actualCount = actualDisputesSearchResults.getCount();
    SearchNavigationLinks actualLinks = actualDisputesSearchResults.getLinks();
    int actualPage = actualDisputesSearchResults.getPage();
    List<DisputeForSearchResult> actualResults = actualDisputesSearchResults.getResults();

    // Assert
    assertEquals(1, actualPage);
    assertEquals(1, actualDisputesSearchResults.getTotal());
    assertEquals(3, actualCount);
    assertTrue(actualResults.isEmpty());
    assertSame(results, actualResults);
    assertSame(links, actualLinks);
  }
}
