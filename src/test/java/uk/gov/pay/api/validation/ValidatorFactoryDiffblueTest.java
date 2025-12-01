package uk.gov.pay.api.validation;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.inject.Injector;
import org.hibernate.validator.internal.engine.ValidatorImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ValidatorFactoryDiffblueTest {
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
  @DisplayName("Test provide(); given InjectingConstraintValidatorFactory(Injector) with Injector")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"jakarta.validation.Validator ValidatorFactory.provide()"})
  void testProvide_givenInjectingConstraintValidatorFactoryWithInjector() {
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
  @DisplayName(
      "Test provide(); given ValidatorFactory(ConstraintValidatorFactory) with constraintValidatorFactory is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"jakarta.validation.Validator ValidatorFactory.provide()"})
  void testProvide_givenValidatorFactoryWithConstraintValidatorFactoryIsNull() {
    // Arrange, Act and Assert
    assertTrue(new ValidatorFactory(null).provide() instanceof ValidatorImpl);
  }
}
