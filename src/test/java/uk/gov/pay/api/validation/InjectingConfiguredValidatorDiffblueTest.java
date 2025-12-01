package uk.gov.pay.api.validation;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.dropwizard.jersey.validation.DropwizardConfiguredValidator;
import jakarta.validation.Validator;
import org.hibernate.validator.internal.engine.ValidatorImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class InjectingConfiguredValidatorDiffblueTest {
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
  @DisplayName(
      "Test new InjectingConfiguredValidator(Validator); then return forExecutables is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void InjectingConfiguredValidator.<init>(Validator)"})
  void testNewInjectingConfiguredValidator_thenReturnForExecutablesIsNull() {
    // Arrange
    DropwizardConfiguredValidator validator =
        new DropwizardConfiguredValidator(mock(ValidatorImpl.class));

    // Act and Assert
    assertNull(new InjectingConfiguredValidator(validator).forExecutables());
  }
}
