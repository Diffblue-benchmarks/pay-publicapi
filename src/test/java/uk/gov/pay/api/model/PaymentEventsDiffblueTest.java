package uk.gov.pay.api.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PaymentEventsDiffblueTest {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentEvents.<init>()",
    "String PaymentEvents.getChargeId()",
    "java.util.List PaymentEvents.getEvents()",
    "String PaymentEvents.toString()"
  })
  public void testGettersAndSetters() {
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
