package uk.gov.pay.api.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.model.links.Link;
import uk.gov.pay.api.model.links.PaymentEventLink;

class PaymentEventResponseDiffblueTest {
  /**
   * Test {@link PaymentEventResponse#from(TransactionEvent, String, String)} with {@code event},
   * {@code paymentId}, {@code paymentLink}.
   *
   * <ul>
   *   <li>Then return PaymentId is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentEventResponse#from(TransactionEvent, String, String)}
   */
  @Test
  @DisplayName(
      "Test from(TransactionEvent, String, String) with 'event', 'paymentId', 'paymentLink'; then return PaymentId is '42'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentEventResponse PaymentEventResponse.from(TransactionEvent, String, String)"
  })
  void testFromWithEventPaymentIdPaymentLink_thenReturnPaymentIdIs42() {
    // Arrange and Act
    PaymentEventResponse actualFromResult =
        PaymentEventResponse.from(new TransactionEvent(), "42", "Payment Link");

    // Assert
    assertEquals("42", actualFromResult.getPaymentId());
    Link paymentLink = actualFromResult.getPaymentLink().getPaymentLink();
    assertEquals("GET", paymentLink.getMethod());
    assertEquals("Payment Link", paymentLink.getHref());
    assertNull(actualFromResult.getUpdated());
    assertNull(actualFromResult.getState());
  }

  /**
   * Test {@link PaymentEventResponse#from(PaymentEvent, String, String)} with {@code paymentEvent},
   * {@code paymentId}, {@code paymentLink}.
   *
   * <ul>
   *   <li>Then return PaymentId is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentEventResponse#from(PaymentEvent, String, String)}
   */
  @Test
  @DisplayName(
      "Test from(PaymentEvent, String, String) with 'paymentEvent', 'paymentId', 'paymentLink'; then return PaymentId is '42'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentEventResponse PaymentEventResponse.from(PaymentEvent, String, String)"
  })
  void testFromWithPaymentEventPaymentIdPaymentLink_thenReturnPaymentIdIs42() {
    // Arrange and Act
    PaymentEventResponse actualFromResult =
        PaymentEventResponse.from(new PaymentEvent(), "42", "Payment Link");

    // Assert
    assertEquals("42", actualFromResult.getPaymentId());
    Link paymentLink = actualFromResult.getPaymentLink().getPaymentLink();
    assertEquals("GET", paymentLink.getMethod());
    assertEquals("Payment Link", paymentLink.getHref());
    assertNull(actualFromResult.getUpdated());
    assertNull(actualFromResult.getState());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PaymentEventResponse#toString()}
   *   <li>{@link PaymentEventResponse#getPaymentId()}
   *   <li>{@link PaymentEventResponse#getPaymentLink()}
   *   <li>{@link PaymentEventResponse#getState()}
   *   <li>{@link PaymentEventResponse#getUpdated()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String PaymentEventResponse.getPaymentId()",
    "PaymentEventLink PaymentEventResponse.getPaymentLink()",
    "PaymentState PaymentEventResponse.getState()",
    "String PaymentEventResponse.getUpdated()",
    "String PaymentEventResponse.toString()"
  })
  void testGettersAndSetters() {
    // Arrange
    PaymentEventResponse fromResult =
        PaymentEventResponse.from(new PaymentEvent(), "42", "Payment Link");

    // Act
    String actualToStringResult = fromResult.toString();
    String actualPaymentId = fromResult.getPaymentId();
    PaymentEventLink actualPaymentLink = fromResult.getPaymentLink();
    PaymentState actualState = fromResult.getState();

    // Assert
    assertEquals("42", actualPaymentId);
    Link paymentLink = actualPaymentLink.getPaymentLink();
    assertEquals("GET", paymentLink.getMethod());
    assertEquals("Payment Link", paymentLink.getHref());
    assertEquals(
        "PaymentEvent{paymentId='42', state='null', updated=null, paymentLink=PaymentEventLink{paymentLink=Link"
            + "{href='Payment Link', method='GET'}}}",
        actualToStringResult);
    assertNull(fromResult.getUpdated());
    assertNull(actualState);
  }
}
