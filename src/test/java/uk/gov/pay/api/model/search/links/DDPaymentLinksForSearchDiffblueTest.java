package uk.gov.pay.api.model.search.links;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.model.links.Link;

class DDPaymentLinksForSearchDiffblueTest {
  /**
   * Test {@link DDPaymentLinksForSearch#addSelf(String)}.
   *
   * <p>Method under test: {@link DDPaymentLinksForSearch#addSelf(String)}
   */
  @Test
  @DisplayName("Test addSelf(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DDPaymentLinksForSearch.addSelf(String)"})
  void testAddSelf() {
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
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DDPaymentLinksForSearch.<init>()",
    "Link DDPaymentLinksForSearch.getSelf()"
  })
  void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertNull(new DDPaymentLinksForSearch().getSelf());
  }
}
