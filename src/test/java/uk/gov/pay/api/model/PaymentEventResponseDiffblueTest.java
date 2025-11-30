package uk.gov.pay.api.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.pay.api.model.links.Link;
import uk.gov.pay.api.model.links.PaymentEventLink;

public class PaymentEventResponseDiffblueTest {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentEventResponse PaymentEventResponse.from(TransactionEvent, String, String)"
  })
  public void testFromWithEventPaymentIdPaymentLink_thenReturnPaymentIdIs42() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentEventResponse PaymentEventResponse.from(PaymentEvent, String, String)"
  })
  public void testFromWithPaymentEventPaymentIdPaymentLink_thenReturnPaymentIdIs42() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String PaymentEventResponse.getPaymentId()",
    "PaymentEventLink PaymentEventResponse.getPaymentLink()",
    "PaymentState PaymentEventResponse.getState()",
    "String PaymentEventResponse.getUpdated()",
    "String PaymentEventResponse.toString()"
  })
  public void testGettersAndSetters() {
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
