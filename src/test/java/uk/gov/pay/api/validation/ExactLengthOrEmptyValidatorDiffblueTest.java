package uk.gov.pay.api.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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

public class ExactLengthOrEmptyValidatorDiffblueTest {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean ExactLengthOrEmptyValidator.isValid(String, ConstraintValidatorContext)"
  })
  public void testIsValidWithValueContext_when42_thenReturnFalse() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean ExactLengthOrEmptyValidator.isValid(String, ConstraintValidatorContext)"
  })
  public void testIsValidWithValueContext_whenNull_thenReturnTrue() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean ExactLengthOrEmptyValidator.isValid(String, ConstraintValidatorContext)"
  })
  public void testIsValidWithValueContext_whenSpace_thenReturnTrue() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ExactLengthOrEmptyValidator.isValid(String, int)"})
  public void testIsValidWithValueLength_when42_thenReturnFalse() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ExactLengthOrEmptyValidator.isValid(String, int)"})
  public void testIsValidWithValueLength_whenNull_thenReturnTrue() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ExactLengthOrEmptyValidator.isValid(String, int)"})
  public void testIsValidWithValueLength_whenSpace_thenReturnTrue() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ExactLengthOrEmptyValidator.isValid(String, int)"})
  public void testIsValidWithValueLength_whenTwo_thenReturnTrue() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ExactLengthOrEmptyValidator.initialize(ExactLengthOrEmpty)"})
  public void testInitializeWithConstraintAnnotation() {
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
