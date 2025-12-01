package uk.gov.pay.api.model;

import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class TransactionEventsDiffblueTest {
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
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TransactionEvents.<init>()",
    "List TransactionEvents.getEvents()",
    "java.lang.String TransactionEvents.getTransactionId()"
  })
  void testGettersAndSetters() {
    // Arrange and Act
    TransactionEvents actualTransactionEvents = new TransactionEvents();
    List<TransactionEvent> actualEvents = actualTransactionEvents.getEvents();

    // Assert
    assertNull(actualTransactionEvents.getTransactionId());
    assertNull(actualEvents);
  }
}
