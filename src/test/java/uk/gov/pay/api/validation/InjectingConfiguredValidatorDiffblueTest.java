package uk.gov.pay.api.validation;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.dropwizard.jersey.validation.DropwizardConfiguredValidator;
import jakarta.validation.Validator;
import org.hibernate.validator.internal.engine.ValidatorImpl;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class InjectingConfiguredValidatorDiffblueTest {
  /**
   * Test {@link InjectingConfiguredValidator#InjectingConfiguredValidator(Validator)}.
   *
   * <ul>
   *   <li>Then return forExecutables is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * InjectingConfiguredValidator#InjectingConfiguredValidator(Validator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InjectingConfiguredValidator.<init>(Validator)"})
  public void testNewInjectingConfiguredValidator_thenReturnForExecutablesIsNull() {
    // Arrange
    DropwizardConfiguredValidator validator =
        new DropwizardConfiguredValidator(mock(ValidatorImpl.class));

    // Act and Assert
    assertNull(new InjectingConfiguredValidator(validator).forExecutables());
  }
}
