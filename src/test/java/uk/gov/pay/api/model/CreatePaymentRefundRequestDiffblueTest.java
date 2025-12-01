package uk.gov.pay.api.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CreatePaymentRefundRequestDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CreatePaymentRefundRequest#CreatePaymentRefundRequest()}
   *   <li>{@link CreatePaymentRefundRequest#toString()}
   *   <li>{@link CreatePaymentRefundRequest#getAmount()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CreatePaymentRefundRequest.<init>()",
    "void CreatePaymentRefundRequest.<init>(int, Integer)",
    "int CreatePaymentRefundRequest.getAmount()",
    "String CreatePaymentRefundRequest.toString()"
  })
  void testGettersAndSetters() {
    // Arrange and Act
    CreatePaymentRefundRequest actualCreatePaymentRefundRequest = new CreatePaymentRefundRequest();
    String actualToStringResult = actualCreatePaymentRefundRequest.toString();

    // Assert
    assertEquals(
        "CreatePaymentRefundRequest{amount=0, refundAmountAvailable=null}", actualToStringResult);
    assertEquals(0, actualCreatePaymentRefundRequest.getAmount());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CreatePaymentRefundRequest#CreatePaymentRefundRequest(int, Integer)}
   *   <li>{@link CreatePaymentRefundRequest#toString()}
   *   <li>{@link CreatePaymentRefundRequest#getAmount()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CreatePaymentRefundRequest.<init>()",
    "void CreatePaymentRefundRequest.<init>(int, Integer)",
    "int CreatePaymentRefundRequest.getAmount()",
    "String CreatePaymentRefundRequest.toString()"
  })
  void testGettersAndSetters2() {
    // Arrange and Act
    CreatePaymentRefundRequest actualCreatePaymentRefundRequest =
        new CreatePaymentRefundRequest(10, 1);
    String actualToStringResult = actualCreatePaymentRefundRequest.toString();

    // Assert
    assertEquals(
        "CreatePaymentRefundRequest{amount=10, refundAmountAvailable=1}", actualToStringResult);
    assertEquals(10, actualCreatePaymentRefundRequest.getAmount());
  }

  /**
   * Test {@link CreatePaymentRefundRequest#getRefundAmountAvailable()}.
   *
   * <p>Method under test: {@link CreatePaymentRefundRequest#getRefundAmountAvailable()}
   */
  @Test
  @DisplayName("Test getRefundAmountAvailable()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Optional CreatePaymentRefundRequest.getRefundAmountAvailable()"})
  void testGetRefundAmountAvailable() {
    // Arrange, Act and Assert
    assertFalse(new CreatePaymentRefundRequest().getRefundAmountAvailable().isPresent());
  }
}
