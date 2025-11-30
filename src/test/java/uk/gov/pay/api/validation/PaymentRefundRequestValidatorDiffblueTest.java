package uk.gov.pay.api.validation;

import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.pay.api.exception.PaymentValidationException;
import uk.gov.pay.api.model.CreatePaymentRefundRequest;

public class PaymentRefundRequestValidatorDiffblueTest {
  /**
   * Test {@link PaymentRefundRequestValidator#validate(CreatePaymentRefundRequest)} with {@code
   * paymentRefundRequest}.
   *
   * <p>Method under test: {@link
   * PaymentRefundRequestValidator#validate(CreatePaymentRefundRequest)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentRefundRequestValidator.validate(CreatePaymentRefundRequest)"})
  public void testValidateWithPaymentRefundRequest() {
    // Arrange
    PaymentRefundRequestValidator paymentRefundRequestValidator =
        new PaymentRefundRequestValidator();

    // Act and Assert
    assertThrows(
        PaymentValidationException.class,
        () -> paymentRefundRequestValidator.validate(new CreatePaymentRefundRequest(10000001, 1)));
  }

  /**
   * Test {@link PaymentRefundRequestValidator#validate(CreatePaymentRefundRequest)} with {@code
   * paymentRefundRequest}.
   *
   * <ul>
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link
   * PaymentRefundRequestValidator#validate(CreatePaymentRefundRequest)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentRefundRequestValidator.validate(CreatePaymentRefundRequest)"})
  public void testValidateWithPaymentRefundRequest_thenDoesNotThrow() {
    // Arrange
    PaymentRefundRequestValidator paymentRefundRequestValidator =
        new PaymentRefundRequestValidator();

    // Act and Assert
    paymentRefundRequestValidator.validate(new CreatePaymentRefundRequest(1, 1));
  }

  /**
   * Test {@link PaymentRefundRequestValidator#validate(CreatePaymentRefundRequest)} with {@code
   * paymentRefundRequest}.
   *
   * <ul>
   *   <li>When {@link CreatePaymentRefundRequest#CreatePaymentRefundRequest()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * PaymentRefundRequestValidator#validate(CreatePaymentRefundRequest)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentRefundRequestValidator.validate(CreatePaymentRefundRequest)"})
  public void testValidateWithPaymentRefundRequest_whenCreatePaymentRefundRequest() {
    // Arrange
    PaymentRefundRequestValidator paymentRefundRequestValidator =
        new PaymentRefundRequestValidator();

    // Act and Assert
    assertThrows(
        PaymentValidationException.class,
        () -> paymentRefundRequestValidator.validate(new CreatePaymentRefundRequest()));
  }
}
