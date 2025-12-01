package uk.gov.pay.api.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class RefundSettlementSummaryDiffblueTest {
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
   *   <li>{@link RefundSettlementSummary#RefundSettlementSummary()}
   *   <li>{@link RefundSettlementSummary#getSettledDate()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters; then return SettledDate is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RefundSettlementSummary.<init>()",
    "void RefundSettlementSummary.<init>(String)",
    "String RefundSettlementSummary.getSettledDate()"
  })
  void testGettersAndSetters_thenReturnSettledDateIsNull() {
    // Arrange, Act and Assert
    assertNull(new RefundSettlementSummary().getSettledDate());
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
   *   <li>{@link RefundSettlementSummary#RefundSettlementSummary(String)}
   *   <li>{@link RefundSettlementSummary#getSettledDate()}
   * </ul>
   */
  @Test
  @DisplayName(
      "Test getters and setters; when '2020-03-01'; then return SettledDate is '2020-03-01'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RefundSettlementSummary.<init>()",
    "void RefundSettlementSummary.<init>(String)",
    "String RefundSettlementSummary.getSettledDate()"
  })
  void testGettersAndSetters_when20200301_thenReturnSettledDateIs20200301() {
    // Arrange, Act and Assert
    assertEquals("2020-03-01", new RefundSettlementSummary("2020-03-01").getSettledDate());
  }
}
