package uk.gov.pay.api.model.ledger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class DisputeSettlementSummaryDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>Then return SettledDate is {@code null}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DisputeSettlementSummary#DisputeSettlementSummary()}
   *   <li>{@link DisputeSettlementSummary#getSettledDate()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters; then return SettledDate is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DisputeSettlementSummary.<init>()",
    "void DisputeSettlementSummary.<init>(String)",
    "String DisputeSettlementSummary.getSettledDate()"
  })
  void testGettersAndSetters_thenReturnSettledDateIsNull() {
    // Arrange, Act and Assert
    assertNull(new DisputeSettlementSummary().getSettledDate());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code 2020-03-01}.
   *   <li>Then return SettledDate is {@code 2020-03-01}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DisputeSettlementSummary#DisputeSettlementSummary(String)}
   *   <li>{@link DisputeSettlementSummary#getSettledDate()}
   * </ul>
   */
  @Test
  @DisplayName(
      "Test getters and setters; when '2020-03-01'; then return SettledDate is '2020-03-01'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DisputeSettlementSummary.<init>()",
    "void DisputeSettlementSummary.<init>(String)",
    "String DisputeSettlementSummary.getSettledDate()"
  })
  void testGettersAndSetters_when20200301_thenReturnSettledDateIs20200301() {
    // Arrange, Act and Assert
    assertEquals("2020-03-01", new DisputeSettlementSummary("2020-03-01").getSettledDate());
  }
}
