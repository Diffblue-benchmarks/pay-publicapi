package uk.gov.pay.api.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PaymentEventDiffblueTest {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentEvent.<init>()",
    "PaymentState PaymentEvent.getState()",
    "String PaymentEvent.getUpdated()",
    "String PaymentEvent.toString()"
  })
  public void testGettersAndSetters() {
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
