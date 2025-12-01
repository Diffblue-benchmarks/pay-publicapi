package uk.gov.pay.api.model.ledger;

import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class RefundsFromLedgerDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link RefundsFromLedger}
   *   <li>{@link RefundsFromLedger#getParentTransactionId()}
   *   <li>{@link RefundsFromLedger#getTransactions()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RefundsFromLedger.<init>()",
    "String RefundsFromLedger.getParentTransactionId()",
    "java.util.List RefundsFromLedger.getTransactions()"
  })
  void testGettersAndSetters() {
    // Arrange and Act
    RefundsFromLedger actualRefundsFromLedger = new RefundsFromLedger();
    String actualParentTransactionId = actualRefundsFromLedger.getParentTransactionId();

    // Assert
    assertNull(actualParentTransactionId);
    assertNull(actualRefundsFromLedger.getTransactions());
  }
}
