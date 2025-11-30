package uk.gov.pay.api.model.search.card;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.pay.api.model.links.RefundLinksForSearch;
import uk.gov.pay.api.model.search.card.RefundForSearchResult.Embedded;

public class RefundForSearchResultDiffblueTest {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Embedded.<init>(RefundForSearchResult)",
    "java.util.List Embedded.getRefunds()"
  })
  public void testEmbeddedGettersAndSetters() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RefundForSearchResult.<init>()",
    "Embedded RefundForSearchResult.getEmbedded()",
    "RefundLinksForSearch RefundForSearchResult.getLinks()",
    "java.lang.String RefundForSearchResult.getPaymentId()"
  })
  public void testGettersAndSetters() {
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
