package uk.gov.pay.api.model.ledger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.model.links.SearchNavigationLinks;

class SearchDisputesResponseFromLedgerDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SearchDisputesResponseFromLedger#getCount()}
   *   <li>{@link SearchDisputesResponseFromLedger#getDisputes()}
   *   <li>{@link SearchDisputesResponseFromLedger#getLinks()}
   *   <li>{@link SearchDisputesResponseFromLedger#getPage()}
   *   <li>{@link SearchDisputesResponseFromLedger#getTotal()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int SearchDisputesResponseFromLedger.getCount()",
    "List SearchDisputesResponseFromLedger.getDisputes()",
    "SearchNavigationLinks SearchDisputesResponseFromLedger.getLinks()",
    "int SearchDisputesResponseFromLedger.getPage()",
    "int SearchDisputesResponseFromLedger.getTotal()"
  })
  void testGettersAndSetters() {
    // Arrange
    SearchDisputesResponseFromLedger searchDisputesResponseFromLedger =
        new SearchDisputesResponseFromLedger();

    // Act
    int actualCount = searchDisputesResponseFromLedger.getCount();
    List<DisputeTransactionFromLedger> actualDisputes =
        searchDisputesResponseFromLedger.getDisputes();
    SearchNavigationLinks actualLinks = searchDisputesResponseFromLedger.getLinks();
    int actualPage = searchDisputesResponseFromLedger.getPage();

    // Assert
    assertNull(actualDisputes);
    assertNull(actualLinks.getFirstPage());
    assertNull(actualLinks.getLastPage());
    assertNull(actualLinks.getNextPage());
    assertNull(actualLinks.getPrevPage());
    assertNull(actualLinks.getSelf());
    assertEquals(0, actualCount);
    assertEquals(0, actualPage);
    assertEquals(0, searchDisputesResponseFromLedger.getTotal());
  }

  /**
   * Test new {@link SearchDisputesResponseFromLedger} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link
   * SearchDisputesResponseFromLedger}
   */
  @Test
  @DisplayName("Test new SearchDisputesResponseFromLedger (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchDisputesResponseFromLedger.<init>()"})
  void testNewSearchDisputesResponseFromLedger() {
    // Arrange and Act
    SearchDisputesResponseFromLedger actualSearchDisputesResponseFromLedger =
        new SearchDisputesResponseFromLedger();

    // Assert
    assertNull(actualSearchDisputesResponseFromLedger.getDisputes());
    SearchNavigationLinks links = actualSearchDisputesResponseFromLedger.getLinks();
    assertNull(links.getFirstPage());
    assertNull(links.getLastPage());
    assertNull(links.getNextPage());
    assertNull(links.getPrevPage());
    assertNull(links.getSelf());
    assertEquals(0, actualSearchDisputesResponseFromLedger.getCount());
    assertEquals(0, actualSearchDisputesResponseFromLedger.getPage());
    assertEquals(0, actualSearchDisputesResponseFromLedger.getTotal());
  }
}
