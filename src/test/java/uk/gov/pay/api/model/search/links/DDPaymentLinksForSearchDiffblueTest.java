package uk.gov.pay.api.model.search.links;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.pay.api.model.links.Link;

public class DDPaymentLinksForSearchDiffblueTest {
  /**
   * Test {@link DDPaymentLinksForSearch#addSelf(String)}.
   *
   * <p>Method under test: {@link DDPaymentLinksForSearch#addSelf(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DDPaymentLinksForSearch.addSelf(String)"})
  public void testAddSelf() {
    // Arrange
    DDPaymentLinksForSearch ddPaymentLinksForSearch = new DDPaymentLinksForSearch();

    // Act
    ddPaymentLinksForSearch.addSelf("Href");

    // Assert
    Link self = ddPaymentLinksForSearch.getSelf();
    assertEquals("GET", self.getMethod());
    assertEquals("Href", self.getHref());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link DDPaymentLinksForSearch}
   *   <li>{@link DDPaymentLinksForSearch#getSelf()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DDPaymentLinksForSearch.<init>()",
    "Link DDPaymentLinksForSearch.getSelf()"
  })
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertNull(new DDPaymentLinksForSearch().getSelf());
  }
}
