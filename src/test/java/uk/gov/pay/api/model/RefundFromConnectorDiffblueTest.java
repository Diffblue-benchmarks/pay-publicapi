package uk.gov.pay.api.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class RefundFromConnectorDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link RefundFromConnector}
   *   <li>{@link RefundFromConnector#toString()}
   *   <li>{@link RefundFromConnector#getAmount()}
   *   <li>{@link RefundFromConnector#getCreatedDate()}
   *   <li>{@link RefundFromConnector#getRefundId()}
   *   <li>{@link RefundFromConnector#getStatus()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RefundFromConnector.<init>()",
    "Long RefundFromConnector.getAmount()",
    "String RefundFromConnector.getCreatedDate()",
    "String RefundFromConnector.getRefundId()",
    "String RefundFromConnector.getStatus()",
    "String RefundFromConnector.toString()"
  })
  void testGettersAndSetters() {
    // Arrange and Act
    RefundFromConnector actualRefundFromConnector = new RefundFromConnector();
    String actualToStringResult = actualRefundFromConnector.toString();
    Long actualAmount = actualRefundFromConnector.getAmount();
    String actualCreatedDate = actualRefundFromConnector.getCreatedDate();
    String actualRefundId = actualRefundFromConnector.getRefundId();

    // Assert
    assertEquals(
        "RefundFromConnector{refundId='null', createdDate='null', amount=null, status='null'}",
        actualToStringResult);
    assertNull(actualAmount);
    assertNull(actualCreatedDate);
    assertNull(actualRefundId);
    assertNull(actualRefundFromConnector.getStatus());
  }
}
