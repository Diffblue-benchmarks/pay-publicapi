package uk.gov.pay.api.model;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TransactionEventsDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link TransactionEvents}
   *   <li>{@link TransactionEvents#getEvents()}
   *   <li>{@link TransactionEvents#getTransactionId()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TransactionEvents.<init>()",
    "List TransactionEvents.getEvents()",
    "java.lang.String TransactionEvents.getTransactionId()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    TransactionEvents actualTransactionEvents = new TransactionEvents();
    List<TransactionEvent> actualEvents = actualTransactionEvents.getEvents();

    // Assert
    assertNull(actualTransactionEvents.getTransactionId());
    assertNull(actualEvents);
  }
}
