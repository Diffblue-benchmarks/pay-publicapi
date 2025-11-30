package uk.gov.pay.api.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PaymentSettlementSummaryDiffblueTest {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentSettlementSummary.<init>()",
    "void PaymentSettlementSummary.<init>(String, String, String)",
    "String PaymentSettlementSummary.getCaptureSubmitTime()",
    "String PaymentSettlementSummary.getCapturedDate()",
    "String PaymentSettlementSummary.getSettledDate()"
  })
  public void testGettersAndSetters_thenReturnCaptureSubmitTimeIsNull() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentSettlementSummary.<init>()",
    "void PaymentSettlementSummary.<init>(String, String, String)",
    "String PaymentSettlementSummary.getCaptureSubmitTime()",
    "String PaymentSettlementSummary.getCapturedDate()",
    "String PaymentSettlementSummary.getSettledDate()"
  })
  public void testGettersAndSetters_whenCaptureSubmitTime_thenReturnCapturedDateIs20200301() {
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
