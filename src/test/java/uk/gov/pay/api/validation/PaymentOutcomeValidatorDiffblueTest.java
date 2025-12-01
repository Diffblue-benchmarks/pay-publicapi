package uk.gov.pay.api.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import jakarta.validation.ClockProvider;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.metadata.ConstraintDescriptor;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.hibernate.validator.messageinterpolation.ExpressionLanguageFeatureLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.model.telephone.PaymentOutcome;
import uk.gov.pay.api.model.telephone.Supplemental;

class PaymentOutcomeValidatorDiffblueTest {
  /**
   * Test {@link PaymentOutcomeValidator#isValid(PaymentOutcome, ConstraintValidatorContext)} with
   * {@code paymentOutcome}, {@code context}.
   *
   * <p>Method under test: {@link PaymentOutcomeValidator#isValid(PaymentOutcome,
   * ConstraintValidatorContext)}
   */
  @Test
  @DisplayName(
      "Test isValid(PaymentOutcome, ConstraintValidatorContext) with 'paymentOutcome', 'context'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean PaymentOutcomeValidator.isValid(PaymentOutcome, ConstraintValidatorContext)"
  })
  void testIsValidWithPaymentOutcomeContext() {
    // Arrange
    PaymentOutcomeValidator paymentOutcomeValidator = new PaymentOutcomeValidator();
    PaymentOutcome paymentOutcome =
        new PaymentOutcome(
            "success", "success", new Supplemental("An error occurred", "An error occurred"));
    ClockProvider clockProvider = mock(ClockProvider.class);
    ConstraintValidatorContextImpl context =
        new ConstraintValidatorContextImpl(
            clockProvider,
            PathImpl.createRootPath(),
            mock(ConstraintDescriptor.class),
            "Constraint Validator Payload",
            ExpressionLanguageFeatureLevel.DEFAULT,
            ExpressionLanguageFeatureLevel.DEFAULT);

    // Act and Assert
    assertFalse(paymentOutcomeValidator.isValid(paymentOutcome, context));
  }

  /**
   * Test {@link PaymentOutcomeValidator#isValid(PaymentOutcome, ConstraintValidatorContext)} with
   * {@code paymentOutcome}, {@code context}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentOutcomeValidator#isValid(PaymentOutcome,
   * ConstraintValidatorContext)}
   */
  @Test
  @DisplayName(
      "Test isValid(PaymentOutcome, ConstraintValidatorContext) with 'paymentOutcome', 'context'; when 'null'; then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean PaymentOutcomeValidator.isValid(PaymentOutcome, ConstraintValidatorContext)"
  })
  void testIsValidWithPaymentOutcomeContext_whenNull_thenReturnTrue() {
    // Arrange
    PaymentOutcomeValidator paymentOutcomeValidator = new PaymentOutcomeValidator();
    ClockProvider clockProvider = mock(ClockProvider.class);
    ConstraintValidatorContextImpl context =
        new ConstraintValidatorContextImpl(
            clockProvider,
            PathImpl.createRootPath(),
            mock(ConstraintDescriptor.class),
            "Constraint Validator Payload",
            ExpressionLanguageFeatureLevel.DEFAULT,
            ExpressionLanguageFeatureLevel.DEFAULT);

    // Act and Assert
    assertTrue(paymentOutcomeValidator.isValid(null, context));
  }

  /**
   * Test {@link PaymentOutcomeValidator#isValid(PaymentOutcome, ConstraintValidatorContext)} with
   * {@code paymentOutcome}, {@code context}.
   *
   * <ul>
   *   <li>When {@link PaymentOutcome#PaymentOutcome(String)} with {@code Status}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentOutcomeValidator#isValid(PaymentOutcome,
   * ConstraintValidatorContext)}
   */
  @Test
  @DisplayName(
      "Test isValid(PaymentOutcome, ConstraintValidatorContext) with 'paymentOutcome', 'context'; when PaymentOutcome(String) with 'Status'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean PaymentOutcomeValidator.isValid(PaymentOutcome, ConstraintValidatorContext)"
  })
  void testIsValidWithPaymentOutcomeContext_whenPaymentOutcomeWithStatus() {
    // Arrange
    PaymentOutcomeValidator paymentOutcomeValidator = new PaymentOutcomeValidator();
    PaymentOutcome paymentOutcome = new PaymentOutcome("Status");
    ClockProvider clockProvider = mock(ClockProvider.class);
    ConstraintValidatorContextImpl context =
        new ConstraintValidatorContextImpl(
            clockProvider,
            PathImpl.createRootPath(),
            mock(ConstraintDescriptor.class),
            "Constraint Validator Payload",
            ExpressionLanguageFeatureLevel.DEFAULT,
            ExpressionLanguageFeatureLevel.DEFAULT);

    // Act and Assert
    assertFalse(paymentOutcomeValidator.isValid(paymentOutcome, context));
  }

  /**
   * Test {@link PaymentOutcomeValidator#isValid(PaymentOutcome, ConstraintValidatorContext)} with
   * {@code paymentOutcome}, {@code context}.
   *
   * <ul>
   *   <li>When {@link PaymentOutcome#PaymentOutcome(String)} with status is {@code failed}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentOutcomeValidator#isValid(PaymentOutcome,
   * ConstraintValidatorContext)}
   */
  @Test
  @DisplayName(
      "Test isValid(PaymentOutcome, ConstraintValidatorContext) with 'paymentOutcome', 'context'; when PaymentOutcome(String) with status is 'failed'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean PaymentOutcomeValidator.isValid(PaymentOutcome, ConstraintValidatorContext)"
  })
  void testIsValidWithPaymentOutcomeContext_whenPaymentOutcomeWithStatusIsFailed() {
    // Arrange
    PaymentOutcomeValidator paymentOutcomeValidator = new PaymentOutcomeValidator();
    PaymentOutcome paymentOutcome = new PaymentOutcome("failed");
    ClockProvider clockProvider = mock(ClockProvider.class);
    ConstraintValidatorContextImpl context =
        new ConstraintValidatorContextImpl(
            clockProvider,
            PathImpl.createRootPath(),
            mock(ConstraintDescriptor.class),
            "Constraint Validator Payload",
            ExpressionLanguageFeatureLevel.DEFAULT,
            ExpressionLanguageFeatureLevel.DEFAULT);

    // Act and Assert
    assertFalse(paymentOutcomeValidator.isValid(paymentOutcome, context));
  }

  /**
   * Test {@link PaymentOutcomeValidator#isValid(PaymentOutcome, ConstraintValidatorContext)} with
   * {@code paymentOutcome}, {@code context}.
   *
   * <ul>
   *   <li>When {@link PaymentOutcome#PaymentOutcome(String)} with status is {@code success}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentOutcomeValidator#isValid(PaymentOutcome,
   * ConstraintValidatorContext)}
   */
  @Test
  @DisplayName(
      "Test isValid(PaymentOutcome, ConstraintValidatorContext) with 'paymentOutcome', 'context'; when PaymentOutcome(String) with status is 'success'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean PaymentOutcomeValidator.isValid(PaymentOutcome, ConstraintValidatorContext)"
  })
  void testIsValidWithPaymentOutcomeContext_whenPaymentOutcomeWithStatusIsSuccess() {
    // Arrange
    PaymentOutcomeValidator paymentOutcomeValidator = new PaymentOutcomeValidator();
    PaymentOutcome paymentOutcome = new PaymentOutcome("success");
    ClockProvider clockProvider = mock(ClockProvider.class);
    ConstraintValidatorContextImpl context =
        new ConstraintValidatorContextImpl(
            clockProvider,
            PathImpl.createRootPath(),
            mock(ConstraintDescriptor.class),
            "Constraint Validator Payload",
            ExpressionLanguageFeatureLevel.DEFAULT,
            ExpressionLanguageFeatureLevel.DEFAULT);

    // Act and Assert
    assertTrue(paymentOutcomeValidator.isValid(paymentOutcome, context));
  }
}
