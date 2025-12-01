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

class ZoneDateTimeValidatorDiffblueTest {
  /**
   * Test {@link ZoneDateTimeValidator#isValid(String, ConstraintValidatorContext)} with {@code
   * date}, {@code context}.
   *
   * <ul>
   *   <li>When {@code 2020-03-01}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ZoneDateTimeValidator#isValid(String, ConstraintValidatorContext)}
   */
  @Test
  @DisplayName(
      "Test isValid(String, ConstraintValidatorContext) with 'date', 'context'; when '2020-03-01'; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ZoneDateTimeValidator.isValid(String, ConstraintValidatorContext)"})
  void testIsValidWithDateContext_when20200301_thenReturnFalse() {
    // Arrange
    ZoneDateTimeValidator zoneDateTimeValidator = new ZoneDateTimeValidator();
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
    assertFalse(zoneDateTimeValidator.isValid("2020-03-01", context));
  }

  /**
   * Test {@link ZoneDateTimeValidator#isValid(String, ConstraintValidatorContext)} with {@code
   * date}, {@code context}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ZoneDateTimeValidator#isValid(String, ConstraintValidatorContext)}
   */
  @Test
  @DisplayName(
      "Test isValid(String, ConstraintValidatorContext) with 'date', 'context'; when 'null'; then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ZoneDateTimeValidator.isValid(String, ConstraintValidatorContext)"})
  void testIsValidWithDateContext_whenNull_thenReturnTrue() {
    // Arrange
    ZoneDateTimeValidator zoneDateTimeValidator = new ZoneDateTimeValidator();
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
    assertTrue(zoneDateTimeValidator.isValid(null, context));
  }
}
