package uk.gov.pay.api.model.search.card;

import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.model.links.RefundLinksForSearch;

class RefundResultDiffblueTest {
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
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RefundResult.<init>()",
    "RefundLinksForSearch RefundResult.getLinks()",
    "uk.gov.pay.api.model.search.card.RefundForSearchRefundsResult RefundResult.getRefunds()"
  })
  void testGettersAndSetters() {
    // Arrange and Act
    RefundResult actualRefundResult = new RefundResult();
    RefundLinksForSearch actualLinks = actualRefundResult.getLinks();

    // Assert
    assertNull(actualLinks);
    assertNull(actualRefundResult.getRefunds());
  }
}
