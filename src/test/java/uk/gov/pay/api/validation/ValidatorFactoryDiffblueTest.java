package uk.gov.pay.api.validation;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.inject.Injector;
import org.hibernate.validator.internal.engine.ValidatorImpl;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ValidatorFactoryDiffblueTest {
  /**
   * Test {@link ValidatorFactory#provide()}.
   *
   * <ul>
   *   <li>Given {@link
   *       InjectingConstraintValidatorFactory#InjectingConstraintValidatorFactory(Injector)} with
   *       {@link Injector}.
   * </ul>
   *
   * <p>Method under test: {@link ValidatorFactory#provide()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"jakarta.validation.Validator ValidatorFactory.provide()"})
  public void testProvide_givenInjectingConstraintValidatorFactoryWithInjector() {
    // Arrange
    InjectingConstraintValidatorFactory constraintValidatorFactory =
        new InjectingConstraintValidatorFactory(mock(Injector.class));

    // Act and Assert
    assertTrue(new ValidatorFactory(constraintValidatorFactory).provide() instanceof ValidatorImpl);
  }

  /**
   * Test {@link ValidatorFactory#provide()}.
   *
   * <ul>
   *   <li>Given {@link ValidatorFactory#ValidatorFactory(ConstraintValidatorFactory)} with
   *       constraintValidatorFactory is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ValidatorFactory#provide()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"jakarta.validation.Validator ValidatorFactory.provide()"})
  public void testProvide_givenValidatorFactoryWithConstraintValidatorFactoryIsNull() {
    // Arrange, Act and Assert
    assertTrue(new ValidatorFactory(null).provide() instanceof ValidatorImpl);
  }
}
