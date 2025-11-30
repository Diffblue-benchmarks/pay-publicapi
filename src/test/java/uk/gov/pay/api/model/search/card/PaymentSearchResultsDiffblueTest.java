package uk.gov.pay.api.model.search.card;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.pay.api.model.links.SearchNavigationLinks;

public class PaymentSearchResultsDiffblueTest {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentSearchResults.<init>()",
    "int PaymentSearchResults.getCount()",
    "SearchNavigationLinks PaymentSearchResults.getLinks()",
    "int PaymentSearchResults.getPage()",
    "List PaymentSearchResults.getPayments()",
    "int PaymentSearchResults.getTotal()"
  })
  public void testGettersAndSetters() {
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
