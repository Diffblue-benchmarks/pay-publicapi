package uk.gov.pay.api.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class PaymentEventDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link PaymentEvent}
   *   <li>{@link PaymentEvent#toString()}
   *   <li>{@link PaymentEvent#getState()}
   *   <li>{@link PaymentEvent#getUpdated()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentEvent.<init>()",
    "PaymentState PaymentEvent.getState()",
    "String PaymentEvent.getUpdated()",
    "String PaymentEvent.toString()"
  })
  void testGettersAndSetters() {
    // Arrange and Act
    PaymentEvent actualPaymentEvent = new PaymentEvent();
    String actualToStringResult = actualPaymentEvent.toString();
    PaymentState actualState = actualPaymentEvent.getState();

    // Assert
    assertEquals("PaymentEvent{state='null', updated=null}", actualToStringResult);
    assertNull(actualPaymentEvent.getUpdated());
    assertNull(actualState);
  }
}
