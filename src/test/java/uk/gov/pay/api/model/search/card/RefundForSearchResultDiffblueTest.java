package uk.gov.pay.api.model.search.card;

import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.model.links.RefundLinksForSearch;
import uk.gov.pay.api.model.search.card.RefundForSearchResult.Embedded;

class RefundForSearchResultDiffblueTest {
  /**
   * Test Embedded getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Embedded#Embedded(RefundForSearchResult)}
   *   <li>{@link Embedded#getRefunds()}
   * </ul>
   */
  @Test
  @DisplayName("Test Embedded getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Embedded.<init>(RefundForSearchResult)",
    "java.util.List Embedded.getRefunds()"
  })
  void testEmbeddedGettersAndSetters() {
    // Arrange, Act and Assert
    assertNull(new RefundForSearchResult().new Embedded().getRefunds());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link RefundForSearchResult}
   *   <li>{@link RefundForSearchResult#getEmbedded()}
   *   <li>{@link RefundForSearchResult#getLinks()}
   *   <li>{@link RefundForSearchResult#getPaymentId()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RefundForSearchResult.<init>()",
    "Embedded RefundForSearchResult.getEmbedded()",
    "RefundLinksForSearch RefundForSearchResult.getLinks()",
    "java.lang.String RefundForSearchResult.getPaymentId()"
  })
  void testGettersAndSetters() {
    // Arrange and Act
    RefundForSearchResult actualRefundForSearchResult = new RefundForSearchResult();
    Embedded actualEmbedded = actualRefundForSearchResult.getEmbedded();
    RefundLinksForSearch actualLinks = actualRefundForSearchResult.getLinks();

    // Assert
    assertNull(actualRefundForSearchResult.getPaymentId());
    assertNull(actualLinks);
    assertNull(actualEmbedded);
  }
}
