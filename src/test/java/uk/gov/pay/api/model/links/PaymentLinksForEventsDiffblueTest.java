package uk.gov.pay.api.model.links;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PaymentLinksForEventsDiffblueTest {
  /**
   * Test {@link PaymentLinksForEvents#addSelf(String)}.
   *
   * <p>Method under test: {@link PaymentLinksForEvents#addSelf(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentLinksForEvents.addSelf(String)"})
  public void testAddSelf() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentLinksForEvents.<init>()", "Link PaymentLinksForEvents.getSelf()"})
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertNull(new PaymentLinksForEvents().getSelf());
  }
}
