package uk.gov.pay.api.model.links;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RefundLinksForSearchDiffblueTest {
  /**
   * Test {@link RefundLinksForSearch#addSelf(String)}.
   *
   * <p>Method under test: {@link RefundLinksForSearch#addSelf(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RefundLinksForSearch.addSelf(String)"})
  public void testAddSelf() {
    // Arrange
    RefundLinksForSearch refundLinksForSearch = new RefundLinksForSearch();

    // Act
    refundLinksForSearch.addSelf("Href");

    // Assert
    Link self = refundLinksForSearch.getSelf();
    assertEquals("GET", self.getMethod());
    assertEquals("Href", self.getHref());
  }

  /**
   * Test {@link RefundLinksForSearch#addPayment(String)}.
   *
   * <p>Method under test: {@link RefundLinksForSearch#addPayment(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RefundLinksForSearch.addPayment(String)"})
  public void testAddPayment() {
    // Arrange
    RefundLinksForSearch refundLinksForSearch = new RefundLinksForSearch();

    // Act
    refundLinksForSearch.addPayment("Href");

    // Assert
    Link payment = refundLinksForSearch.getPayment();
    assertEquals("GET", payment.getMethod());
    assertEquals("Href", payment.getHref());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link RefundLinksForSearch}
   *   <li>{@link RefundLinksForSearch#getPayment()}
   *   <li>{@link RefundLinksForSearch#getSelf()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RefundLinksForSearch.<init>()",
    "Link RefundLinksForSearch.getPayment()",
    "Link RefundLinksForSearch.getSelf()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    RefundLinksForSearch actualRefundLinksForSearch = new RefundLinksForSearch();
    Link actualPayment = actualRefundLinksForSearch.getPayment();

    // Assert
    assertNull(actualPayment);
    assertNull(actualRefundLinksForSearch.getSelf());
  }
}
