package uk.gov.pay.api.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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

class ExactLengthOrEmptyValidatorDiffblueTest {
  /**
   * Test {@link ExactLengthOrEmptyValidator#isValid(String, ConstraintValidatorContext)} with
   * {@code value}, {@code context}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ExactLengthOrEmptyValidator#isValid(String,
   * ConstraintValidatorContext)}
   */
  @Test
  @DisplayName(
      "Test isValid(String, ConstraintValidatorContext) with 'value', 'context'; when '42'; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean ExactLengthOrEmptyValidator.isValid(String, ConstraintValidatorContext)"
  })
  void testIsValidWithValueContext_when42_thenReturnFalse() {
    // Arrange
    ExactLengthOrEmptyValidator exactLengthOrEmptyValidator = new ExactLengthOrEmptyValidator();
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
    assertFalse(exactLengthOrEmptyValidator.isValid("42", context));
  }

  /**
   * Test {@link ExactLengthOrEmptyValidator#isValid(String, ConstraintValidatorContext)} with
   * {@code value}, {@code context}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ExactLengthOrEmptyValidator#isValid(String,
   * ConstraintValidatorContext)}
   */
  @Test
  @DisplayName(
      "Test isValid(String, ConstraintValidatorContext) with 'value', 'context'; when 'null'; then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean ExactLengthOrEmptyValidator.isValid(String, ConstraintValidatorContext)"
  })
  void testIsValidWithValueContext_whenNull_thenReturnTrue() {
    // Arrange
    ExactLengthOrEmptyValidator exactLengthOrEmptyValidator = new ExactLengthOrEmptyValidator();
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
    assertTrue(exactLengthOrEmptyValidator.isValid(null, context));
  }

  /**
   * Test {@link ExactLengthOrEmptyValidator#isValid(String, ConstraintValidatorContext)} with
   * {@code value}, {@code context}.
   *
   * <ul>
   *   <li>When space.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ExactLengthOrEmptyValidator#isValid(String,
   * ConstraintValidatorContext)}
   */
  @Test
  @DisplayName(
      "Test isValid(String, ConstraintValidatorContext) with 'value', 'context'; when space; then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean ExactLengthOrEmptyValidator.isValid(String, ConstraintValidatorContext)"
  })
  void testIsValidWithValueContext_whenSpace_thenReturnTrue() {
    // Arrange
    ExactLengthOrEmptyValidator exactLengthOrEmptyValidator = new ExactLengthOrEmptyValidator();
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
    assertTrue(exactLengthOrEmptyValidator.isValid(" ", context));
  }

  /**
   * Test {@link ExactLengthOrEmptyValidator#isValid(String, int)} with {@code value}, {@code
   * length}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ExactLengthOrEmptyValidator#isValid(String, int)}
   */
  @Test
  @DisplayName("Test isValid(String, int) with 'value', 'length'; when '42'; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ExactLengthOrEmptyValidator.isValid(String, int)"})
  void testIsValidWithValueLength_when42_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(ExactLengthOrEmptyValidator.isValid("42", 3));
  }

  /**
   * Test {@link ExactLengthOrEmptyValidator#isValid(String, int)} with {@code value}, {@code
   * length}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ExactLengthOrEmptyValidator#isValid(String, int)}
   */
  @Test
  @DisplayName("Test isValid(String, int) with 'value', 'length'; when 'null'; then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ExactLengthOrEmptyValidator.isValid(String, int)"})
  void testIsValidWithValueLength_whenNull_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(ExactLengthOrEmptyValidator.isValid(null, 3));
  }

  /**
   * Test {@link ExactLengthOrEmptyValidator#isValid(String, int)} with {@code value}, {@code
   * length}.
   *
   * <ul>
   *   <li>When space.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ExactLengthOrEmptyValidator#isValid(String, int)}
   */
  @Test
  @DisplayName("Test isValid(String, int) with 'value', 'length'; when space; then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ExactLengthOrEmptyValidator.isValid(String, int)"})
  void testIsValidWithValueLength_whenSpace_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(ExactLengthOrEmptyValidator.isValid(" ", 3));
  }

  /**
   * Test {@link ExactLengthOrEmptyValidator#isValid(String, int)} with {@code value}, {@code
   * length}.
   *
   * <ul>
   *   <li>When two.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ExactLengthOrEmptyValidator#isValid(String, int)}
   */
  @Test
  @DisplayName("Test isValid(String, int) with 'value', 'length'; when two; then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ExactLengthOrEmptyValidator.isValid(String, int)"})
  void testIsValidWithValueLength_whenTwo_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(ExactLengthOrEmptyValidator.isValid("42", 2));
  }

  /**
   * Test {@link ExactLengthOrEmptyValidator#initialize(ExactLengthOrEmpty)} with {@code
   * constraintAnnotation}.
   *
   * <p>Method under test: {@link ExactLengthOrEmptyValidator#initialize(ExactLengthOrEmpty)}
   */
  @Test
  @DisplayName("Test initialize(ExactLengthOrEmpty) with 'constraintAnnotation'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ExactLengthOrEmptyValidator.initialize(ExactLengthOrEmpty)"})
  void testInitializeWithConstraintAnnotation() {
    // Arrange
    ExactLengthOrEmptyValidator exactLengthOrEmptyValidator = new ExactLengthOrEmptyValidator();

    ExactLengthOrEmpty constraintAnnotation = mock(ExactLengthOrEmpty.class);
    when(constraintAnnotation.length()).thenReturn(3);

    // Act
    exactLengthOrEmptyValidator.initialize(constraintAnnotation);

    // Assert
    verify(constraintAnnotation).length();
  }
}
