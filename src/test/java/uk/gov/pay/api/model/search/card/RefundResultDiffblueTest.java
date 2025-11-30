package uk.gov.pay.api.model.search.card;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.pay.api.model.links.RefundLinksForSearch;

public class RefundResultDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link RefundResult}
   *   <li>{@link RefundResult#getLinks()}
   *   <li>{@link RefundResult#getRefunds()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RefundResult.<init>()",
    "RefundLinksForSearch RefundResult.getLinks()",
    "uk.gov.pay.api.model.search.card.RefundForSearchRefundsResult RefundResult.getRefunds()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    RefundResult actualRefundResult = new RefundResult();
    RefundLinksForSearch actualLinks = actualRefundResult.getLinks();

    // Assert
    assertNull(actualLinks);
    assertNull(actualRefundResult.getRefunds());
  }
}
