package uk.gov.pay.api.model.links;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class PaymentEventLinkDiffblueTest {
  /**
   * Test {@link PaymentEventLink#PaymentEventLink(String)}.
   *
   * <p>Method under test: {@link PaymentEventLink#PaymentEventLink(String)}
   */
  @Test
  @DisplayName("Test new PaymentEventLink(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentEventLink.<init>(String)"})
  void testNewPaymentEventLink() {
    // Arrange, Act and Assert
    Link paymentLink = new PaymentEventLink("Href").getPaymentLink();
    assertEquals("GET", paymentLink.getMethod());
    assertEquals("Href", paymentLink.getHref());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PaymentEventLink#toString()}
   *   <li>{@link PaymentEventLink#getPaymentLink()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Link PaymentEventLink.getPaymentLink()",
    "String PaymentEventLink.toString()"
  })
  void testGettersAndSetters() {
    // Arrange
    PaymentEventLink paymentEventLink = new PaymentEventLink("Href");

    // Act
    String actualToStringResult = paymentEventLink.toString();
    Link actualPaymentLink = paymentEventLink.getPaymentLink();

    // Assert
    assertEquals("GET", actualPaymentLink.getMethod());
    assertEquals("Href", actualPaymentLink.getHref());
    assertEquals(
        "PaymentEventLink{paymentLink=Link{href='Href', method='GET'}}", actualToStringResult);
  }
}
