package uk.gov.pay.api.model;

import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class TransactionEventDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link TransactionEvent}
   *   <li>{@link TransactionEvent#getState()}
   *   <li>{@link TransactionEvent#getTimestamp()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TransactionEvent.<init>()",
    "PaymentState TransactionEvent.getState()",
    "java.lang.String TransactionEvent.getTimestamp()"
  })
  void testGettersAndSetters() {
    // Arrange and Act
    TransactionEvent actualTransactionEvent = new TransactionEvent();
    PaymentState actualState = actualTransactionEvent.getState();

    // Assert
    assertNull(actualTransactionEvent.getTimestamp());
    assertNull(actualState);
  }
}
