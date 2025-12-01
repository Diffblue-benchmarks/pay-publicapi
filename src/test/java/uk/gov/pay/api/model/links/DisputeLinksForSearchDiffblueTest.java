package uk.gov.pay.api.model.links;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class DisputeLinksForSearchDiffblueTest {
  /**
   * Test {@link DisputeLinksForSearch#addPayment(String)}.
   *
   * <p>Method under test: {@link DisputeLinksForSearch#addPayment(String)}
   */
  @Test
  @DisplayName("Test addPayment(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DisputeLinksForSearch.addPayment(String)"})
  void testAddPayment() {
    // Arrange
    DisputeLinksForSearch disputeLinksForSearch = new DisputeLinksForSearch();

    // Act
    disputeLinksForSearch.addPayment("Href");

    // Assert
    Link payment = disputeLinksForSearch.getPayment();
    assertEquals("GET", payment.getMethod());
    assertEquals("Href", payment.getHref());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link DisputeLinksForSearch}
   *   <li>{@link DisputeLinksForSearch#getPayment()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DisputeLinksForSearch.<init>()",
    "Link DisputeLinksForSearch.getPayment()"
  })
  void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertNull(new DisputeLinksForSearch().getPayment());
  }
}
