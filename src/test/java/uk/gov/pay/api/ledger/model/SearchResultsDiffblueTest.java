package uk.gov.pay.api.ledger.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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

public class SearchResultsDiffblueTest {
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
  @Category(ContributionFromDiffblue.class)
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
  public void testGettersAndSetters_thenReturnResultsIsNull() {
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
  @Category(ContributionFromDiffblue.class)
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
  public void testGettersAndSetters_whenOne_thenReturnPageIsOne() {
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
