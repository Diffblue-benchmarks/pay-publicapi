package uk.gov.pay.api.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CreatePaymentRefundRequestDiffblueTest {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CreatePaymentRefundRequest.<init>()",
    "void CreatePaymentRefundRequest.<init>(int, Integer)",
    "int CreatePaymentRefundRequest.getAmount()",
    "String CreatePaymentRefundRequest.toString()"
  })
  public void testGettersAndSetters() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CreatePaymentRefundRequest.<init>()",
    "void CreatePaymentRefundRequest.<init>(int, Integer)",
    "int CreatePaymentRefundRequest.getAmount()",
    "String CreatePaymentRefundRequest.toString()"
  })
  public void testGettersAndSetters2() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Optional CreatePaymentRefundRequest.getRefundAmountAvailable()"})
  public void testGetRefundAmountAvailable() {
    // Arrange, Act and Assert
    assertFalse(new CreatePaymentRefundRequest().getRefundAmountAvailable().isPresent());
  }
}
