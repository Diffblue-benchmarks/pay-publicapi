package uk.gov.pay.api.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import jakarta.validation.ClockProvider;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.metadata.ConstraintDescriptor;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.hibernate.validator.messageinterpolation.ExpressionLanguageFeatureLevel;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CardFirstSixDigitsValidatorDiffblueTest {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean CardFirstSixDigitsValidator.isValid(String, ConstraintValidatorContext)"
  })
  public void testIsValidWithValueContext_when42_thenReturnFalse() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean CardFirstSixDigitsValidator.isValid(String, ConstraintValidatorContext)"
  })
  public void testIsValidWithValueContext_whenNull_thenReturnTrue() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CardFirstSixDigitsValidator.<init>()"})
  public void testNewCardFirstSixDigitsValidator() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CardFirstSixDigitsValidator.<init>()"})
  public void testNewCardFirstSixDigitsValidator2() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CardFirstSixDigitsValidator.<init>()"})
  public void testNewCardFirstSixDigitsValidator3() {
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
