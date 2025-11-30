package uk.gov.pay.api.validation;

import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.pay.api.exception.AgreementValidationException;
import uk.gov.pay.api.ledger.model.AgreementSearchParams;

public class AgreementSearchValidatorDiffblueTest {
  /**
   * Test {@link AgreementSearchValidator#validateSearchParameters(AgreementSearchParams)}.
   *
   * <p>Method under test: {@link
   * AgreementSearchValidator#validateSearchParameters(AgreementSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AgreementSearchValidator.validateSearchParameters(AgreementSearchParams)"
  })
  public void testValidateSearchParameters() {
    // Arrange
    AgreementSearchParams searchParams =
        new AgreementSearchParams("Reference", "Status", "42", "Display Size");

    // Act and Assert
    assertThrows(
        AgreementValidationException.class,
        () -> AgreementSearchValidator.validateSearchParameters(searchParams));
  }

  /**
   * Test {@link AgreementSearchValidator#validateSearchParameters(AgreementSearchParams)}.
   *
   * <p>Method under test: {@link
   * AgreementSearchValidator#validateSearchParameters(AgreementSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AgreementSearchValidator.validateSearchParameters(AgreementSearchParams)"
  })
  public void testValidateSearchParameters2() {
    // Arrange
    AgreementSearchParams searchParams =
        new AgreementSearchParams("not blank", " ", "not blank", "not blank");

    // Act and Assert
    assertThrows(
        AgreementValidationException.class,
        () -> AgreementSearchValidator.validateSearchParameters(searchParams));
  }

  /**
   * Test {@link AgreementSearchValidator#validateSearchParameters(AgreementSearchParams)}.
   *
   * <p>Method under test: {@link
   * AgreementSearchValidator#validateSearchParameters(AgreementSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AgreementSearchValidator.validateSearchParameters(AgreementSearchParams)"
  })
  public void testValidateSearchParameters3() {
    // Arrange
    AgreementSearchParams searchParams =
        new AgreementSearchParams("not blank", " ", "not blank", "1");

    // Act and Assert
    assertThrows(
        AgreementValidationException.class,
        () -> AgreementSearchValidator.validateSearchParameters(searchParams));
  }

  /**
   * Test {@link AgreementSearchValidator#validateSearchParameters(AgreementSearchParams)}.
   *
   * <p>Method under test: {@link
   * AgreementSearchValidator#validateSearchParameters(AgreementSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AgreementSearchValidator.validateSearchParameters(AgreementSearchParams)"
  })
  public void testValidateSearchParameters4() {
    // Arrange
    AgreementSearchParams searchParams =
        new AgreementSearchParams("not blank", " ", "not blank", "0");

    // Act and Assert
    assertThrows(
        AgreementValidationException.class,
        () -> AgreementSearchValidator.validateSearchParameters(searchParams));
  }

  /**
   * Test {@link AgreementSearchValidator#validateSearchParameters(AgreementSearchParams)}.
   *
   * <p>Method under test: {@link
   * AgreementSearchValidator#validateSearchParameters(AgreementSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AgreementSearchValidator.validateSearchParameters(AgreementSearchParams)"
  })
  public void testValidateSearchParameters5() {
    // Arrange
    AgreementSearchParams searchParams =
        new AgreementSearchParams("not blank", " ", "not blank", null);

    // Act and Assert
    assertThrows(
        AgreementValidationException.class,
        () -> AgreementSearchValidator.validateSearchParameters(searchParams));
  }

  /**
   * Test {@link AgreementSearchValidator#validateSearchParameters(AgreementSearchParams)}.
   *
   * <p>Method under test: {@link
   * AgreementSearchValidator#validateSearchParameters(AgreementSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AgreementSearchValidator.validateSearchParameters(AgreementSearchParams)"
  })
  public void testValidateSearchParameters6() {
    // Arrange
    AgreementSearchParams searchParams = new AgreementSearchParams("not blank", " ", "1", "1");

    // Act and Assert
    AgreementSearchValidator.validateSearchParameters(searchParams);
  }

  /**
   * Test {@link AgreementSearchValidator#validateSearchParameters(AgreementSearchParams)}.
   *
   * <p>Method under test: {@link
   * AgreementSearchValidator#validateSearchParameters(AgreementSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AgreementSearchValidator.validateSearchParameters(AgreementSearchParams)"
  })
  public void testValidateSearchParameters7() {
    // Arrange
    AgreementSearchParams searchParams =
        new AgreementSearchParams("not blank", " ", "0", "not blank");

    // Act and Assert
    assertThrows(
        AgreementValidationException.class,
        () -> AgreementSearchValidator.validateSearchParameters(searchParams));
  }

  /**
   * Test {@link AgreementSearchValidator#validateSearchParameters(AgreementSearchParams)}.
   *
   * <p>Method under test: {@link
   * AgreementSearchValidator#validateSearchParameters(AgreementSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AgreementSearchValidator.validateSearchParameters(AgreementSearchParams)"
  })
  public void testValidateSearchParameters8() {
    // Arrange
    AgreementSearchParams searchParams =
        new AgreementSearchParams("not blank", " ", null, "not blank");

    // Act and Assert
    assertThrows(
        AgreementValidationException.class,
        () -> AgreementSearchValidator.validateSearchParameters(searchParams));
  }

  /**
   * Test {@link AgreementSearchValidator#validateSearchParameters(AgreementSearchParams)}.
   *
   * <p>Method under test: {@link
   * AgreementSearchValidator#validateSearchParameters(AgreementSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AgreementSearchValidator.validateSearchParameters(AgreementSearchParams)"
  })
  public void testValidateSearchParameters9() {
    // Arrange
    AgreementSearchParams searchParams =
        new AgreementSearchParams("not blank", null, "not blank", "not blank");

    // Act and Assert
    assertThrows(
        AgreementValidationException.class,
        () -> AgreementSearchValidator.validateSearchParameters(searchParams));
  }

  /**
   * Test {@link AgreementSearchValidator#validateSearchParameters(AgreementSearchParams)}.
   *
   * <p>Method under test: {@link
   * AgreementSearchValidator#validateSearchParameters(AgreementSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AgreementSearchValidator.validateSearchParameters(AgreementSearchParams)"
  })
  public void testValidateSearchParameters10() {
    // Arrange
    AgreementSearchParams searchParams =
        new AgreementSearchParams("", "Status", "42", "Display Size");

    // Act and Assert
    assertThrows(
        AgreementValidationException.class,
        () -> AgreementSearchValidator.validateSearchParameters(searchParams));
  }

  /**
   * Test {@link AgreementSearchValidator#validateSearchParameters(AgreementSearchParams)}.
   *
   * <p>Method under test: {@link
   * AgreementSearchValidator#validateSearchParameters(AgreementSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AgreementSearchValidator.validateSearchParameters(AgreementSearchParams)"
  })
  public void testValidateSearchParameters11() {
    // Arrange
    AgreementSearchParams searchParams =
        new AgreementSearchParams("Reference", "created", "42", "Display Size");

    // Act and Assert
    assertThrows(
        AgreementValidationException.class,
        () -> AgreementSearchValidator.validateSearchParameters(searchParams));
  }

  /**
   * Test {@link AgreementSearchValidator#validateSearchParameters(AgreementSearchParams)}.
   *
   * <p>Method under test: {@link
   * AgreementSearchValidator#validateSearchParameters(AgreementSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AgreementSearchValidator.validateSearchParameters(AgreementSearchParams)"
  })
  public void testValidateSearchParameters12() {
    // Arrange
    AgreementSearchParams searchParams =
        new AgreementSearchParams("Reference", "Status", "", "Display Size");

    // Act and Assert
    assertThrows(
        AgreementValidationException.class,
        () -> AgreementSearchValidator.validateSearchParameters(searchParams));
  }

  /**
   * Test {@link AgreementSearchValidator#validateSearchParameters(AgreementSearchParams)}.
   *
   * <ul>
   *   <li>When {@link AgreementSearchParams#AgreementSearchParams()}.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link
   * AgreementSearchValidator#validateSearchParameters(AgreementSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AgreementSearchValidator.validateSearchParameters(AgreementSearchParams)"
  })
  public void testValidateSearchParameters_whenAgreementSearchParams_thenDoesNotThrow() {
    // Arrange, Act and Assert
    AgreementSearchValidator.validateSearchParameters(new AgreementSearchParams());
  }

  /**
   * Test {@link AgreementSearchValidator#validateSearchParameters(AgreementSearchParams)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then throw {@link AgreementValidationException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AgreementSearchValidator#validateSearchParameters(AgreementSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AgreementSearchValidator.validateSearchParameters(AgreementSearchParams)"
  })
  public void testValidateSearchParameters_whenNull_thenThrowAgreementValidationException() {
    // Arrange, Act and Assert
    assertThrows(
        AgreementValidationException.class,
        () -> AgreementSearchValidator.validateSearchParameters(null));
  }
}
