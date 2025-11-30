package uk.gov.pay.api.model.links;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DisputeLinksForSearchDiffblueTest {
  /**
   * Test {@link DisputeLinksForSearch#addPayment(String)}.
   *
   * <p>Method under test: {@link DisputeLinksForSearch#addPayment(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DisputeLinksForSearch.addPayment(String)"})
  public void testAddPayment() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DisputeLinksForSearch.<init>()",
    "Link DisputeLinksForSearch.getPayment()"
  })
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertNull(new DisputeLinksForSearch().getPayment());
  }
}
