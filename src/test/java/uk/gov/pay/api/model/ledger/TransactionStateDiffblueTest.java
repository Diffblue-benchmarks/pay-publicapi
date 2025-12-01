package uk.gov.pay.api.model.ledger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class TransactionStateDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>Then return Status is {@code null}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link TransactionState#TransactionState()}
   *   <li>{@link TransactionState#getCode()}
   *   <li>{@link TransactionState#getMessage()}
   *   <li>{@link TransactionState#getStatus()}
   *   <li>{@link TransactionState#isFinished()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters; then return Status is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TransactionState.<init>()",
    "void TransactionState.<init>(String, boolean)",
    "String TransactionState.getCode()",
    "String TransactionState.getMessage()",
    "String TransactionState.getStatus()",
    "boolean TransactionState.isFinished()"
  })
  void testGettersAndSetters_thenReturnStatusIsNull() {
    // Arrange and Act
    TransactionState actualTransactionState = new TransactionState();
    String actualCode = actualTransactionState.getCode();
    String actualMessage = actualTransactionState.getMessage();
    String actualStatus = actualTransactionState.getStatus();

    // Assert
    assertNull(actualCode);
    assertNull(actualMessage);
    assertNull(actualStatus);
    assertFalse(actualTransactionState.isFinished());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code Status}.
   *   <li>Then return {@code Status}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link TransactionState#TransactionState(String, boolean)}
   *   <li>{@link TransactionState#getCode()}
   *   <li>{@link TransactionState#getMessage()}
   *   <li>{@link TransactionState#getStatus()}
   *   <li>{@link TransactionState#isFinished()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters; when 'Status'; then return 'Status'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TransactionState.<init>()",
    "void TransactionState.<init>(String, boolean)",
    "String TransactionState.getCode()",
    "String TransactionState.getMessage()",
    "String TransactionState.getStatus()",
    "boolean TransactionState.isFinished()"
  })
  void testGettersAndSetters_whenStatus_thenReturnStatus() {
    // Arrange and Act
    TransactionState actualTransactionState = new TransactionState("Status", true);
    String actualCode = actualTransactionState.getCode();
    String actualMessage = actualTransactionState.getMessage();
    String actualStatus = actualTransactionState.getStatus();

    // Assert
    assertEquals("Status", actualStatus);
    assertNull(actualCode);
    assertNull(actualMessage);
    assertTrue(actualTransactionState.isFinished());
  }
}
