package uk.gov.pay.api.model.links;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class RefundLinksForSearchDiffblueTest {
  /**
   * Test {@link RefundLinksForSearch#addSelf(String)}.
   *
   * <p>Method under test: {@link RefundLinksForSearch#addSelf(String)}
   */
  @Test
  @DisplayName("Test addSelf(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RefundLinksForSearch.addSelf(String)"})
  void testAddSelf() {
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
  @DisplayName("Test addPayment(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RefundLinksForSearch.addPayment(String)"})
  void testAddPayment() {
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
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RefundLinksForSearch.<init>()",
    "Link RefundLinksForSearch.getPayment()",
    "Link RefundLinksForSearch.getSelf()"
  })
  void testGettersAndSetters() {
    // Arrange and Act
    RefundLinksForSearch actualRefundLinksForSearch = new RefundLinksForSearch();
    Link actualPayment = actualRefundLinksForSearch.getPayment();

    // Assert
    assertNull(actualPayment);
    assertNull(actualRefundLinksForSearch.getSelf());
  }
}
