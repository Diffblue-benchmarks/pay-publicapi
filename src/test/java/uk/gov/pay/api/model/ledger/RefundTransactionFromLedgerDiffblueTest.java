package uk.gov.pay.api.model.ledger;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.pay.api.model.RefundSettlementSummary;

public class RefundTransactionFromLedgerDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link RefundTransactionFromLedger}
   *   <li>{@link RefundTransactionFromLedger#getAmount()}
   *   <li>{@link RefundTransactionFromLedger#getCreatedDate()}
   *   <li>{@link RefundTransactionFromLedger#getDescription()}
   *   <li>{@link RefundTransactionFromLedger#getParentTransactionId()}
   *   <li>{@link RefundTransactionFromLedger#getReference()}
   *   <li>{@link RefundTransactionFromLedger#getRefundedBy()}
   *   <li>{@link RefundTransactionFromLedger#getSettlementSummary()}
   *   <li>{@link RefundTransactionFromLedger#getState()}
   *   <li>{@link RefundTransactionFromLedger#getTransactionId()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RefundTransactionFromLedger.<init>()",
    "Long RefundTransactionFromLedger.getAmount()",
    "String RefundTransactionFromLedger.getCreatedDate()",
    "String RefundTransactionFromLedger.getDescription()",
    "String RefundTransactionFromLedger.getParentTransactionId()",
    "String RefundTransactionFromLedger.getReference()",
    "String RefundTransactionFromLedger.getRefundedBy()",
    "RefundSettlementSummary RefundTransactionFromLedger.getSettlementSummary()",
    "TransactionState RefundTransactionFromLedger.getState()",
    "String RefundTransactionFromLedger.getTransactionId()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    RefundTransactionFromLedger actualRefundTransactionFromLedger =
        new RefundTransactionFromLedger();
    Long actualAmount = actualRefundTransactionFromLedger.getAmount();
    String actualCreatedDate = actualRefundTransactionFromLedger.getCreatedDate();
    String actualDescription = actualRefundTransactionFromLedger.getDescription();
    String actualParentTransactionId = actualRefundTransactionFromLedger.getParentTransactionId();
    String actualReference = actualRefundTransactionFromLedger.getReference();
    String actualRefundedBy = actualRefundTransactionFromLedger.getRefundedBy();
    RefundSettlementSummary actualSettlementSummary =
        actualRefundTransactionFromLedger.getSettlementSummary();
    TransactionState actualState = actualRefundTransactionFromLedger.getState();

    // Assert
    assertNull(actualAmount);
    assertNull(actualCreatedDate);
    assertNull(actualDescription);
    assertNull(actualParentTransactionId);
    assertNull(actualReference);
    assertNull(actualRefundedBy);
    assertNull(actualRefundTransactionFromLedger.getTransactionId());
    assertNull(actualSettlementSummary);
    assertNull(actualState);
  }
}
