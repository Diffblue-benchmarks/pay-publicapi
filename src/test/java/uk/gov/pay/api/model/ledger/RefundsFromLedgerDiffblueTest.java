package uk.gov.pay.api.model.ledger;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RefundsFromLedgerDiffblueTest {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RefundsFromLedger.<init>()",
    "String RefundsFromLedger.getParentTransactionId()",
    "java.util.List RefundsFromLedger.getTransactions()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    RefundsFromLedger actualRefundsFromLedger = new RefundsFromLedger();
    String actualParentTransactionId = actualRefundsFromLedger.getParentTransactionId();

    // Assert
    assertNull(actualParentTransactionId);
    assertNull(actualRefundsFromLedger.getTransactions());
  }
}
