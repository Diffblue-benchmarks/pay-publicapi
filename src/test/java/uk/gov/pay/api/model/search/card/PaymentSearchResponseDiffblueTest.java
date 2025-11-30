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

public class PaymentSearchResponseDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PaymentSearchResponse#getCount()}
   *   <li>{@link PaymentSearchResponse#getLinks()}
   *   <li>{@link PaymentSearchResponse#getPage()}
   *   <li>{@link PaymentSearchResponse#getPayments()}
   *   <li>{@link PaymentSearchResponse#getTotal()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int PaymentSearchResponse.getCount()",
    "SearchNavigationLinks PaymentSearchResponse.getLinks()",
    "int PaymentSearchResponse.getPage()",
    "List PaymentSearchResponse.getPayments()",
    "int PaymentSearchResponse.getTotal()"
  })
  public void testGettersAndSetters() {
    // Arrange
    PaymentSearchResponse<Object> paymentSearchResponse = new PaymentSearchResponse<>();

    // Act
    int actualCount = paymentSearchResponse.getCount();
    SearchNavigationLinks actualLinks = paymentSearchResponse.getLinks();
    int actualPage = paymentSearchResponse.getPage();
    List<Object> actualPayments = paymentSearchResponse.getPayments();

    // Assert
    assertNull(actualPayments);
    assertNull(actualLinks.getFirstPage());
    assertNull(actualLinks.getLastPage());
    assertNull(actualLinks.getNextPage());
    assertNull(actualLinks.getPrevPage());
    assertNull(actualLinks.getSelf());
    assertEquals(0, actualCount);
    assertEquals(0, actualPage);
    assertEquals(0, paymentSearchResponse.getTotal());
  }

  /**
   * Test new {@link PaymentSearchResponse} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link PaymentSearchResponse}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentSearchResponse.<init>()"})
  public void testNewPaymentSearchResponse() {
    // Arrange and Act
    PaymentSearchResponse<Object> actualPaymentSearchResponse = new PaymentSearchResponse<>();

    // Assert
    assertNull(actualPaymentSearchResponse.getPayments());
    SearchNavigationLinks links = actualPaymentSearchResponse.getLinks();
    assertNull(links.getFirstPage());
    assertNull(links.getLastPage());
    assertNull(links.getNextPage());
    assertNull(links.getPrevPage());
    assertNull(links.getSelf());
    assertEquals(0, actualPaymentSearchResponse.getCount());
    assertEquals(0, actualPaymentSearchResponse.getPage());
    assertEquals(0, actualPaymentSearchResponse.getTotal());
  }
}
