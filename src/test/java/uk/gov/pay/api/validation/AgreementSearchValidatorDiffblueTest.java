package uk.gov.pay.api.validation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.exception.AgreementValidationException;
import uk.gov.pay.api.ledger.model.AgreementSearchParams;

class AgreementSearchValidatorDiffblueTest {
  /**
   * Test {@link AgreementSearchValidator#validateSearchParameters(AgreementSearchParams)}.
   *
   * <p>Method under test: {@link
   * AgreementSearchValidator#validateSearchParameters(AgreementSearchParams)}
   */
  @Test
  @DisplayName("Test validateSearchParameters(AgreementSearchParams)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AgreementSearchValidator.validateSearchParameters(AgreementSearchParams)"
  })
  void testValidateSearchParameters() {
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
  @DisplayName("Test validateSearchParameters(AgreementSearchParams)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AgreementSearchValidator.validateSearchParameters(AgreementSearchParams)"
  })
  void testValidateSearchParameters2() {
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
  @DisplayName("Test validateSearchParameters(AgreementSearchParams)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AgreementSearchValidator.validateSearchParameters(AgreementSearchParams)"
  })
  void testValidateSearchParameters3() {
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
  @DisplayName("Test validateSearchParameters(AgreementSearchParams)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AgreementSearchValidator.validateSearchParameters(AgreementSearchParams)"
  })
  void testValidateSearchParameters4() {
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
  @DisplayName("Test validateSearchParameters(AgreementSearchParams)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AgreementSearchValidator.validateSearchParameters(AgreementSearchParams)"
  })
  void testValidateSearchParameters5() {
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
  @DisplayName("Test validateSearchParameters(AgreementSearchParams)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AgreementSearchValidator.validateSearchParameters(AgreementSearchParams)"
  })
  void testValidateSearchParameters6() {
    // Arrange
    AgreementSearchParams searchParams = new AgreementSearchParams("not blank", " ", "1", "1");

    // Act and Assert
    assertDoesNotThrow(() -> AgreementSearchValidator.validateSearchParameters(searchParams));
  }

  /**
   * Test {@link AgreementSearchValidator#validateSearchParameters(AgreementSearchParams)}.
   *
   * <p>Method under test: {@link
   * AgreementSearchValidator#validateSearchParameters(AgreementSearchParams)}
   */
  @Test
  @DisplayName("Test validateSearchParameters(AgreementSearchParams)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AgreementSearchValidator.validateSearchParameters(AgreementSearchParams)"
  })
  void testValidateSearchParameters7() {
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
  @DisplayName("Test validateSearchParameters(AgreementSearchParams)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AgreementSearchValidator.validateSearchParameters(AgreementSearchParams)"
  })
  void testValidateSearchParameters8() {
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
  @DisplayName("Test validateSearchParameters(AgreementSearchParams)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AgreementSearchValidator.validateSearchParameters(AgreementSearchParams)"
  })
  void testValidateSearchParameters9() {
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
  @DisplayName("Test validateSearchParameters(AgreementSearchParams)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AgreementSearchValidator.validateSearchParameters(AgreementSearchParams)"
  })
  void testValidateSearchParameters10() {
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
  @DisplayName("Test validateSearchParameters(AgreementSearchParams)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AgreementSearchValidator.validateSearchParameters(AgreementSearchParams)"
  })
  void testValidateSearchParameters11() {
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
  @DisplayName("Test validateSearchParameters(AgreementSearchParams)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AgreementSearchValidator.validateSearchParameters(AgreementSearchParams)"
  })
  void testValidateSearchParameters12() {
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
  @DisplayName(
      "Test validateSearchParameters(AgreementSearchParams); when AgreementSearchParams(); then does not throw")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AgreementSearchValidator.validateSearchParameters(AgreementSearchParams)"
  })
  void testValidateSearchParameters_whenAgreementSearchParams_thenDoesNotThrow() {
    // Arrange, Act and Assert
    assertDoesNotThrow(
        () -> AgreementSearchValidator.validateSearchParameters(new AgreementSearchParams()));
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
  @DisplayName(
      "Test validateSearchParameters(AgreementSearchParams); when 'null'; then throw AgreementValidationException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AgreementSearchValidator.validateSearchParameters(AgreementSearchParams)"
  })
  void testValidateSearchParameters_whenNull_thenThrowAgreementValidationException() {
    // Arrange, Act and Assert
    assertThrows(
        AgreementValidationException.class,
        () -> AgreementSearchValidator.validateSearchParameters(null));
  }
}
