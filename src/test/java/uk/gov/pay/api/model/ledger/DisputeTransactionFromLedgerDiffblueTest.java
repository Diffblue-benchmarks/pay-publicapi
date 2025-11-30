package uk.gov.pay.api.model.ledger;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DisputeTransactionFromLedgerDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link DisputeTransactionFromLedger}
   *   <li>{@link DisputeTransactionFromLedger#getAmount()}
   *   <li>{@link DisputeTransactionFromLedger#getCreatedDate()}
   *   <li>{@link DisputeTransactionFromLedger#getEvidenceDueDate()}
   *   <li>{@link DisputeTransactionFromLedger#getFee()}
   *   <li>{@link DisputeTransactionFromLedger#getNetAmount()}
   *   <li>{@link DisputeTransactionFromLedger#getParentTransactionId()}
   *   <li>{@link DisputeTransactionFromLedger#getReason()}
   *   <li>{@link DisputeTransactionFromLedger#getSettlementSummary()}
   *   <li>{@link DisputeTransactionFromLedger#getState()}
   *   <li>{@link DisputeTransactionFromLedger#getTransactionId()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DisputeTransactionFromLedger.<init>()",
    "Long DisputeTransactionFromLedger.getAmount()",
    "String DisputeTransactionFromLedger.getCreatedDate()",
    "String DisputeTransactionFromLedger.getEvidenceDueDate()",
    "Long DisputeTransactionFromLedger.getFee()",
    "Long DisputeTransactionFromLedger.getNetAmount()",
    "String DisputeTransactionFromLedger.getParentTransactionId()",
    "String DisputeTransactionFromLedger.getReason()",
    "DisputeSettlementSummary DisputeTransactionFromLedger.getSettlementSummary()",
    "TransactionState DisputeTransactionFromLedger.getState()",
    "String DisputeTransactionFromLedger.getTransactionId()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DisputeTransactionFromLedger actualDisputeTransactionFromLedger =
        new DisputeTransactionFromLedger();
    Long actualAmount = actualDisputeTransactionFromLedger.getAmount();
    String actualCreatedDate = actualDisputeTransactionFromLedger.getCreatedDate();
    String actualEvidenceDueDate = actualDisputeTransactionFromLedger.getEvidenceDueDate();
    Long actualFee = actualDisputeTransactionFromLedger.getFee();
    Long actualNetAmount = actualDisputeTransactionFromLedger.getNetAmount();
    String actualParentTransactionId = actualDisputeTransactionFromLedger.getParentTransactionId();
    String actualReason = actualDisputeTransactionFromLedger.getReason();
    DisputeSettlementSummary actualSettlementSummary =
        actualDisputeTransactionFromLedger.getSettlementSummary();
    TransactionState actualState = actualDisputeTransactionFromLedger.getState();

    // Assert
    assertNull(actualAmount);
    assertNull(actualFee);
    assertNull(actualNetAmount);
    assertNull(actualCreatedDate);
    assertNull(actualEvidenceDueDate);
    assertNull(actualParentTransactionId);
    assertNull(actualReason);
    assertNull(actualDisputeTransactionFromLedger.getTransactionId());
    assertNull(actualSettlementSummary);
    assertNull(actualState);
  }
}
