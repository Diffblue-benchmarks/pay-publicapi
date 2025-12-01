package uk.gov.pay.api.validation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.exception.PaymentValidationException;
import uk.gov.pay.api.model.CreatePaymentRefundRequest;

class PaymentRefundRequestValidatorDiffblueTest {
  /**
   * Test {@link PaymentRefundRequestValidator#validate(CreatePaymentRefundRequest)} with {@code
   * paymentRefundRequest}.
   *
   * <p>Method under test: {@link
   * PaymentRefundRequestValidator#validate(CreatePaymentRefundRequest)}
   */
  @Test
  @DisplayName("Test validate(CreatePaymentRefundRequest) with 'paymentRefundRequest'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentRefundRequestValidator.validate(CreatePaymentRefundRequest)"})
  void testValidateWithPaymentRefundRequest() {
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
  @DisplayName(
      "Test validate(CreatePaymentRefundRequest) with 'paymentRefundRequest'; then does not throw")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentRefundRequestValidator.validate(CreatePaymentRefundRequest)"})
  void testValidateWithPaymentRefundRequest_thenDoesNotThrow() {
    // Arrange
    PaymentRefundRequestValidator paymentRefundRequestValidator =
        new PaymentRefundRequestValidator();

    // Act and Assert
    assertDoesNotThrow(
        () -> paymentRefundRequestValidator.validate(new CreatePaymentRefundRequest(1, 1)));
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
  @DisplayName(
      "Test validate(CreatePaymentRefundRequest) with 'paymentRefundRequest'; when CreatePaymentRefundRequest()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentRefundRequestValidator.validate(CreatePaymentRefundRequest)"})
  void testValidateWithPaymentRefundRequest_whenCreatePaymentRefundRequest() {
    // Arrange
    PaymentRefundRequestValidator paymentRefundRequestValidator =
        new PaymentRefundRequestValidator();

    // Act and Assert
    assertThrows(
        PaymentValidationException.class,
        () -> paymentRefundRequestValidator.validate(new CreatePaymentRefundRequest()));
  }
}
