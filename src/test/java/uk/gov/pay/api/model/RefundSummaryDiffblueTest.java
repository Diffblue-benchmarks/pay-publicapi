package uk.gov.pay.api.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class RefundSummaryDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RefundSummary#RefundSummary()}
   *   <li>{@link RefundSummary#getAmountAvailable()}
   *   <li>{@link RefundSummary#getAmountSubmitted()}
   *   <li>{@link RefundSummary#getStatus()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RefundSummary.<init>()",
    "long RefundSummary.getAmountAvailable()",
    "long RefundSummary.getAmountSubmitted()",
    "String RefundSummary.getStatus()"
  })
  void testGettersAndSetters() {
    // Arrange and Act
    RefundSummary actualRefundSummary = new RefundSummary();
    long actualAmountAvailable = actualRefundSummary.getAmountAvailable();
    long actualAmountSubmitted = actualRefundSummary.getAmountSubmitted();

    // Assert
    assertNull(actualRefundSummary.getStatus());
    assertEquals(0L, actualAmountAvailable);
    assertEquals(0L, actualAmountSubmitted);
  }

  /**
   * Test {@link RefundSummary#RefundSummary(String, long, long)}.
   *
   * <p>Method under test: {@link RefundSummary#RefundSummary(String, long, long)}
   */
  @Test
  @DisplayName("Test new RefundSummary(String, long, long)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RefundSummary.<init>(String, long, long)"})
  void testNewRefundSummary() {
    // Arrange and Act
    RefundSummary actualRefundSummary = new RefundSummary("Status", 10L, 10L);

    // Assert
    assertEquals("Status", actualRefundSummary.getStatus());
    assertEquals(10L, actualRefundSummary.getAmountAvailable());
    assertEquals(10L, actualRefundSummary.getAmountSubmitted());
  }
}
