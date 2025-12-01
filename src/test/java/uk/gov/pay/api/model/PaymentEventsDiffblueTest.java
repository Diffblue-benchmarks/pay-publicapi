package uk.gov.pay.api.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class PaymentEventsDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link PaymentEvents}
   *   <li>{@link PaymentEvents#toString()}
   *   <li>{@link PaymentEvents#getChargeId()}
   *   <li>{@link PaymentEvents#getEvents()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentEvents.<init>()",
    "String PaymentEvents.getChargeId()",
    "java.util.List PaymentEvents.getEvents()",
    "String PaymentEvents.toString()"
  })
  void testGettersAndSetters() {
    // Arrange and Act
    PaymentEvents actualPaymentEvents = new PaymentEvents();
    String actualToStringResult = actualPaymentEvents.toString();
    String actualChargeId = actualPaymentEvents.getChargeId();

    // Assert
    assertEquals("PaymentEvents{chargeId='null', events=null}", actualToStringResult);
    assertNull(actualChargeId);
    assertNull(actualPaymentEvents.getEvents());
  }
}
