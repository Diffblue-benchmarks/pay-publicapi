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

class CardFirstSixDigitsValidatorDiffblueTest {
  /**
   * Test {@link CardFirstSixDigitsValidator#isValid(String, ConstraintValidatorContext)} with
   * {@code value}, {@code context}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link CardFirstSixDigitsValidator#isValid(String,
   * ConstraintValidatorContext)}
   */
  @Test
  @DisplayName(
      "Test isValid(String, ConstraintValidatorContext) with 'value', 'context'; when '42'; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean CardFirstSixDigitsValidator.isValid(String, ConstraintValidatorContext)"
  })
  void testIsValidWithValueContext_when42_thenReturnFalse() {
    // Arrange
    CardFirstSixDigitsValidator cardFirstSixDigitsValidator = new CardFirstSixDigitsValidator();
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
    assertFalse(cardFirstSixDigitsValidator.isValid("42", context));
  }

  /**
   * Test {@link CardFirstSixDigitsValidator#isValid(String, ConstraintValidatorContext)} with
   * {@code value}, {@code context}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link CardFirstSixDigitsValidator#isValid(String,
   * ConstraintValidatorContext)}
   */
  @Test
  @DisplayName(
      "Test isValid(String, ConstraintValidatorContext) with 'value', 'context'; when 'null'; then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean CardFirstSixDigitsValidator.isValid(String, ConstraintValidatorContext)"
  })
  void testIsValidWithValueContext_whenNull_thenReturnTrue() {
    // Arrange
    CardFirstSixDigitsValidator cardFirstSixDigitsValidator = new CardFirstSixDigitsValidator();
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
    assertTrue(cardFirstSixDigitsValidator.isValid(null, context));
  }

  /**
   * Test new {@link CardFirstSixDigitsValidator} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link
   * CardFirstSixDigitsValidator}
   */
  @Test
  @DisplayName("Test new CardFirstSixDigitsValidator (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CardFirstSixDigitsValidator.<init>()"})
  void testNewCardFirstSixDigitsValidator() {
    // Arrange and Act
    CardFirstSixDigitsValidator actualCardFirstSixDigitsValidator =
        new CardFirstSixDigitsValidator();
    ClockProvider clockProvider = mock(ClockProvider.class);
    ConstraintValidatorContextImpl context =
        new ConstraintValidatorContextImpl(
            clockProvider,
            PathImpl.createRootPath(),
            mock(ConstraintDescriptor.class),
            "Constraint Validator Payload",
            ExpressionLanguageFeatureLevel.DEFAULT,
            ExpressionLanguageFeatureLevel.DEFAULT);

    // Assert
    assertFalse(actualCardFirstSixDigitsValidator.isValid("42", context));
  }

  /**
   * Test new {@link CardFirstSixDigitsValidator} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link
   * CardFirstSixDigitsValidator}
   */
  @Test
  @DisplayName("Test new CardFirstSixDigitsValidator (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CardFirstSixDigitsValidator.<init>()"})
  void testNewCardFirstSixDigitsValidator2() {
    // Arrange and Act
    CardFirstSixDigitsValidator actualCardFirstSixDigitsValidator =
        new CardFirstSixDigitsValidator();
    ClockProvider clockProvider = mock(ClockProvider.class);
    ConstraintValidatorContextImpl context =
        new ConstraintValidatorContextImpl(
            clockProvider,
            PathImpl.createRootPath(),
            mock(ConstraintDescriptor.class),
            "Constraint Validator Payload",
            ExpressionLanguageFeatureLevel.DEFAULT,
            ExpressionLanguageFeatureLevel.DEFAULT);

    // Assert
    assertTrue(actualCardFirstSixDigitsValidator.isValid("999999", context));
  }

  /**
   * Test new {@link CardFirstSixDigitsValidator} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link
   * CardFirstSixDigitsValidator}
   */
  @Test
  @DisplayName("Test new CardFirstSixDigitsValidator (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CardFirstSixDigitsValidator.<init>()"})
  void testNewCardFirstSixDigitsValidator3() {
    // Arrange and Act
    CardFirstSixDigitsValidator actualCardFirstSixDigitsValidator =
        new CardFirstSixDigitsValidator();
    ClockProvider clockProvider = mock(ClockProvider.class);
    ConstraintValidatorContextImpl context =
        new ConstraintValidatorContextImpl(
            clockProvider,
            PathImpl.createRootPath(),
            mock(ConstraintDescriptor.class),
            "Constraint Validator Payload",
            ExpressionLanguageFeatureLevel.DEFAULT,
            ExpressionLanguageFeatureLevel.DEFAULT);

    // Assert
    assertTrue(actualCardFirstSixDigitsValidator.isValid(null, context));
  }
}
