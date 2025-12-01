package uk.gov.pay.api.ledger.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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

class SearchResultsDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>Then return Results is {@code null}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SearchResults#SearchResults()}
   *   <li>{@link SearchResults#getCount()}
   *   <li>{@link SearchResults#getLinks()}
   *   <li>{@link SearchResults#getPage()}
   *   <li>{@link SearchResults#getResults()}
   *   <li>{@link SearchResults#getTotal()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters; then return Results is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SearchResults.<init>()",
    "void SearchResults.<init>(int, int, int, List, SearchNavigationLinks)",
    "int SearchResults.getCount()",
    "SearchNavigationLinks SearchResults.getLinks()",
    "int SearchResults.getPage()",
    "List SearchResults.getResults()",
    "int SearchResults.getTotal()"
  })
  void testGettersAndSetters_thenReturnResultsIsNull() {
    // Arrange and Act
    SearchResults<Object> actualSearchResults = new SearchResults<>();
    int actualCount = actualSearchResults.getCount();
    SearchNavigationLinks actualLinks = actualSearchResults.getLinks();
    int actualPage = actualSearchResults.getPage();
    List<Object> actualResults = actualSearchResults.getResults();

    // Assert
    assertNull(actualResults);
    assertNull(actualLinks);
    assertEquals(0, actualCount);
    assertEquals(0, actualPage);
    assertEquals(0, actualSearchResults.getTotal());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return Page is one.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SearchResults#SearchResults(int, int, int, List, SearchNavigationLinks)}
   *   <li>{@link SearchResults#getCount()}
   *   <li>{@link SearchResults#getLinks()}
   *   <li>{@link SearchResults#getPage()}
   *   <li>{@link SearchResults#getResults()}
   *   <li>{@link SearchResults#getTotal()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters; when one; then return Page is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SearchResults.<init>()",
    "void SearchResults.<init>(int, int, int, List, SearchNavigationLinks)",
    "int SearchResults.getCount()",
    "SearchNavigationLinks SearchResults.getLinks()",
    "int SearchResults.getPage()",
    "List SearchResults.getResults()",
    "int SearchResults.getTotal()"
  })
  void testGettersAndSetters_whenOne_thenReturnPageIsOne() {
    // Arrange
    ArrayList<Object> results = new ArrayList<>();
    SearchNavigationLinks links = new SearchNavigationLinks();

    // Act
    SearchResults<Object> actualSearchResults = new SearchResults<>(1, 3, 1, results, links);
    int actualCount = actualSearchResults.getCount();
    SearchNavigationLinks actualLinks = actualSearchResults.getLinks();
    int actualPage = actualSearchResults.getPage();
    List<Object> actualResults = actualSearchResults.getResults();

    // Assert
    assertEquals(1, actualPage);
    assertEquals(1, actualSearchResults.getTotal());
    assertEquals(3, actualCount);
    assertTrue(actualResults.isEmpty());
    assertSame(results, actualResults);
    assertSame(links, actualLinks);
  }
}
