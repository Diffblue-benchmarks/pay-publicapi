package uk.gov.pay.api.validation;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.inject.Injector;
import jakarta.validation.ConstraintValidator;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class InjectingConstraintValidatorFactoryDiffblueTest {
  /**
   * Test {@link InjectingConstraintValidatorFactory#getInstance(Class)}.
   *
   * <p>Method under test: {@link InjectingConstraintValidatorFactory#getInstance(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ConstraintValidator InjectingConstraintValidatorFactory.getInstance(Class)"})
  public void testGetInstance() {
    // Arrange
    Injector injector = mock(Injector.class);
    when(injector.getInstance(ConstraintValidator.class))
        .thenReturn(mock(ConstraintValidator.class));
    InjectingConstraintValidatorFactory injectingConstraintValidatorFactory =
        new InjectingConstraintValidatorFactory(injector);
    Class<ConstraintValidator> forNameResult = ConstraintValidator.class;

    // Act
    injectingConstraintValidatorFactory.getInstance(
        (Class<ConstraintValidator<?, ?>>) (Class) forNameResult);

    // Assert
    verify(injector).getInstance(isA(Class.class));
  }
}
