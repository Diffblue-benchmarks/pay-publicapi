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

public class CardExpiryValidatorDiffblueTest {
  /**
   * Test {@link CardExpiryValidator#isValid(String, ConstraintValidatorContext)} with {@code
   * value}, {@code context}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link CardExpiryValidator#isValid(String, ConstraintValidatorContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean CardExpiryValidator.isValid(String, ConstraintValidatorContext)"})
  public void testIsValidWithValueContext_when42_thenReturnFalse() {
    // Arrange
    CardExpiryValidator cardExpiryValidator = new CardExpiryValidator();
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
    assertFalse(cardExpiryValidator.isValid("42", context));
  }

  /**
   * Test {@link CardExpiryValidator#isValid(String, ConstraintValidatorContext)} with {@code
   * value}, {@code context}.
   *
   * <ul>
   *   <li>When {@code 09/99}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link CardExpiryValidator#isValid(String, ConstraintValidatorContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean CardExpiryValidator.isValid(String, ConstraintValidatorContext)"})
  public void testIsValidWithValueContext_when0999_thenReturnTrue() {
    // Arrange
    CardExpiryValidator cardExpiryValidator = new CardExpiryValidator();
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
    assertTrue(cardExpiryValidator.isValid("09/99", context));
  }

  /**
   * Test {@link CardExpiryValidator#isValid(String, ConstraintValidatorContext)} with {@code
   * value}, {@code context}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link CardExpiryValidator#isValid(String, ConstraintValidatorContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean CardExpiryValidator.isValid(String, ConstraintValidatorContext)"})
  public void testIsValidWithValueContext_whenNull_thenReturnTrue() {
    // Arrange
    CardExpiryValidator cardExpiryValidator = new CardExpiryValidator();
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
    assertTrue(cardExpiryValidator.isValid(null, context));
  }
}
