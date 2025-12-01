package uk.gov.pay.api.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class PaymentSettlementSummaryDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>Then return CaptureSubmitTime is {@code null}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PaymentSettlementSummary#PaymentSettlementSummary()}
   *   <li>{@link PaymentSettlementSummary#getCaptureSubmitTime()}
   *   <li>{@link PaymentSettlementSummary#getCapturedDate()}
   *   <li>{@link PaymentSettlementSummary#getSettledDate()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters; then return CaptureSubmitTime is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentSettlementSummary.<init>()",
    "void PaymentSettlementSummary.<init>(String, String, String)",
    "String PaymentSettlementSummary.getCaptureSubmitTime()",
    "String PaymentSettlementSummary.getCapturedDate()",
    "String PaymentSettlementSummary.getSettledDate()"
  })
  void testGettersAndSetters_thenReturnCaptureSubmitTimeIsNull() {
    // Arrange and Act
    PaymentSettlementSummary actualPaymentSettlementSummary = new PaymentSettlementSummary();
    String actualCaptureSubmitTime = actualPaymentSettlementSummary.getCaptureSubmitTime();
    String actualCapturedDate = actualPaymentSettlementSummary.getCapturedDate();

    // Assert
    assertNull(actualCaptureSubmitTime);
    assertNull(actualCapturedDate);
    assertNull(actualPaymentSettlementSummary.getSettledDate());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code Capture Submit Time}.
   *   <li>Then return CapturedDate is {@code 2020-03-01}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PaymentSettlementSummary#PaymentSettlementSummary(String, String, String)}
   *   <li>{@link PaymentSettlementSummary#getCaptureSubmitTime()}
   *   <li>{@link PaymentSettlementSummary#getCapturedDate()}
   *   <li>{@link PaymentSettlementSummary#getSettledDate()}
   * </ul>
   */
  @Test
  @DisplayName(
      "Test getters and setters; when 'Capture Submit Time'; then return CapturedDate is '2020-03-01'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentSettlementSummary.<init>()",
    "void PaymentSettlementSummary.<init>(String, String, String)",
    "String PaymentSettlementSummary.getCaptureSubmitTime()",
    "String PaymentSettlementSummary.getCapturedDate()",
    "String PaymentSettlementSummary.getSettledDate()"
  })
  void testGettersAndSetters_whenCaptureSubmitTime_thenReturnCapturedDateIs20200301() {
    // Arrange and Act
    PaymentSettlementSummary actualPaymentSettlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    String actualCaptureSubmitTime = actualPaymentSettlementSummary.getCaptureSubmitTime();
    String actualCapturedDate = actualPaymentSettlementSummary.getCapturedDate();

    // Assert
    assertEquals("2020-03-01", actualCapturedDate);
    assertEquals("2020-03-01", actualPaymentSettlementSummary.getSettledDate());
    assertEquals("Capture Submit Time", actualCaptureSubmitTime);
  }
}
