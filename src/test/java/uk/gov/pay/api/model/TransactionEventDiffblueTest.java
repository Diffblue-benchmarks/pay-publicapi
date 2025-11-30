package uk.gov.pay.api.model;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TransactionEventDiffblueTest {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TransactionEvent.<init>()",
    "PaymentState TransactionEvent.getState()",
    "java.lang.String TransactionEvent.getTimestamp()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    TransactionEvent actualTransactionEvent = new TransactionEvent();
    PaymentState actualState = actualTransactionEvent.getState();

    // Assert
    assertNull(actualTransactionEvent.getTimestamp());
    assertNull(actualState);
  }
}
