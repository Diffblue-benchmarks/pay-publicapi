package uk.gov.pay.api.model.links;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class PaymentLinksForEventsDiffblueTest {
  /**
   * Test {@link PaymentLinksForEvents#addSelf(String)}.
   *
   * <p>Method under test: {@link PaymentLinksForEvents#addSelf(String)}
   */
  @Test
  @DisplayName("Test addSelf(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentLinksForEvents.addSelf(String)"})
  void testAddSelf() {
    // Arrange
    PaymentLinksForEvents paymentLinksForEvents = new PaymentLinksForEvents();

    // Act
    paymentLinksForEvents.addSelf("Href");

    // Assert
    Link self = paymentLinksForEvents.getSelf();
    assertEquals("GET", self.getMethod());
    assertEquals("Href", self.getHref());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link PaymentLinksForEvents}
   *   <li>{@link PaymentLinksForEvents#getSelf()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentLinksForEvents.<init>()", "Link PaymentLinksForEvents.getSelf()"})
  void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertNull(new PaymentLinksForEvents().getSelf());
  }
}
