package uk.gov.pay.api.model.search.card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.model.links.SearchNavigationLinks;

class PaymentSearchResultsDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link PaymentSearchResults}
   *   <li>{@link PaymentSearchResults#getCount()}
   *   <li>{@link PaymentSearchResults#getLinks()}
   *   <li>{@link PaymentSearchResults#getPage()}
   *   <li>{@link PaymentSearchResults#getPayments()}
   *   <li>{@link PaymentSearchResults#getTotal()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentSearchResults.<init>()",
    "int PaymentSearchResults.getCount()",
    "SearchNavigationLinks PaymentSearchResults.getLinks()",
    "int PaymentSearchResults.getPage()",
    "List PaymentSearchResults.getPayments()",
    "int PaymentSearchResults.getTotal()"
  })
  void testGettersAndSetters() {
    // Arrange and Act
    PaymentSearchResults actualPaymentSearchResults = new PaymentSearchResults();
    int actualCount = actualPaymentSearchResults.getCount();
    SearchNavigationLinks actualLinks = actualPaymentSearchResults.getLinks();
    int actualPage = actualPaymentSearchResults.getPage();
    List<PaymentForSearchResult> actualPayments = actualPaymentSearchResults.getPayments();

    // Assert
    assertNull(actualPayments);
    assertNull(actualLinks);
    assertEquals(0, actualCount);
    assertEquals(0, actualPage);
    assertEquals(0, actualPaymentSearchResults.getTotal());
  }
}
