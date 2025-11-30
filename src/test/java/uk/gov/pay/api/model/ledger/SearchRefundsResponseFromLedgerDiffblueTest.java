package uk.gov.pay.api.model.ledger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.pay.api.model.links.SearchNavigationLinks;

public class SearchRefundsResponseFromLedgerDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SearchRefundsResponseFromLedger#getCount()}
   *   <li>{@link SearchRefundsResponseFromLedger#getLinks()}
   *   <li>{@link SearchRefundsResponseFromLedger#getPage()}
   *   <li>{@link SearchRefundsResponseFromLedger#getRefunds()}
   *   <li>{@link SearchRefundsResponseFromLedger#getTotal()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int SearchRefundsResponseFromLedger.getCount()",
    "SearchNavigationLinks SearchRefundsResponseFromLedger.getLinks()",
    "int SearchRefundsResponseFromLedger.getPage()",
    "List SearchRefundsResponseFromLedger.getRefunds()",
    "int SearchRefundsResponseFromLedger.getTotal()"
  })
  public void testGettersAndSetters() {
    // Arrange
    SearchRefundsResponseFromLedger searchRefundsResponseFromLedger =
        new SearchRefundsResponseFromLedger();

    // Act
    int actualCount = searchRefundsResponseFromLedger.getCount();
    SearchNavigationLinks actualLinks = searchRefundsResponseFromLedger.getLinks();
    int actualPage = searchRefundsResponseFromLedger.getPage();
    List<RefundTransactionFromLedger> actualRefunds = searchRefundsResponseFromLedger.getRefunds();

    // Assert
    assertNull(actualRefunds);
    assertNull(actualLinks.getFirstPage());
    assertNull(actualLinks.getLastPage());
    assertNull(actualLinks.getNextPage());
    assertNull(actualLinks.getPrevPage());
    assertNull(actualLinks.getSelf());
    assertEquals(0, actualCount);
    assertEquals(0, actualPage);
    assertEquals(0, searchRefundsResponseFromLedger.getTotal());
  }

  /**
   * Test new {@link SearchRefundsResponseFromLedger} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link
   * SearchRefundsResponseFromLedger}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchRefundsResponseFromLedger.<init>()"})
  public void testNewSearchRefundsResponseFromLedger() {
    // Arrange and Act
    SearchRefundsResponseFromLedger actualSearchRefundsResponseFromLedger =
        new SearchRefundsResponseFromLedger();

    // Assert
    assertNull(actualSearchRefundsResponseFromLedger.getRefunds());
    SearchNavigationLinks links = actualSearchRefundsResponseFromLedger.getLinks();
    assertNull(links.getFirstPage());
    assertNull(links.getLastPage());
    assertNull(links.getNextPage());
    assertNull(links.getPrevPage());
    assertNull(links.getSelf());
    assertEquals(0, actualSearchRefundsResponseFromLedger.getCount());
    assertEquals(0, actualSearchRefundsResponseFromLedger.getPage());
    assertEquals(0, actualSearchRefundsResponseFromLedger.getTotal());
  }
}
