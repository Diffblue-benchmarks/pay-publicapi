package uk.gov.pay.api.validation;

import static org.junit.jupiter.api.Assertions.assertThrows;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.exception.RefundsValidationException;
import uk.gov.pay.api.service.RefundsParams;

class RefundSearchValidatorDiffblueTest {
  /**
   * Test {@link RefundSearchValidator#validateSearchParameters(RefundsParams)}.
   *
   * <p>Method under test: {@link RefundSearchValidator#validateSearchParameters(RefundsParams)}
   */
  @Test
  @DisplayName("Test validateSearchParameters(RefundsParams)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RefundSearchValidator.validateSearchParameters(RefundsParams)"})
  void testValidateSearchParameters() {
    // Arrange
    RefundsParams params =
        new RefundsParams(
            "2020-03-01", "2020-03-01", "Page", "Display Size", "2020-03-01", "2020-03-01");

    // Act and Assert
    assertThrows(
        RefundsValidationException.class,
        () -> RefundSearchValidator.validateSearchParameters(params));
  }

  /**
   * Test {@link RefundSearchValidator#validateSearchParameters(RefundsParams)}.
   *
   * <p>Method under test: {@link RefundSearchValidator#validateSearchParameters(RefundsParams)}
   */
  @Test
  @DisplayName("Test validateSearchParameters(RefundsParams)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RefundSearchValidator.validateSearchParameters(RefundsParams)"})
  void testValidateSearchParameters2() {
    // Arrange
    RefundsParams params =
        new RefundsParams(" ", " ", "not blank", "not blank", "not blank", "not blank");

    // Act and Assert
    assertThrows(
        RefundsValidationException.class,
        () -> RefundSearchValidator.validateSearchParameters(params));
  }

  /**
   * Test {@link RefundSearchValidator#validateSearchParameters(RefundsParams)}.
   *
   * <p>Method under test: {@link RefundSearchValidator#validateSearchParameters(RefundsParams)}
   */
  @Test
  @DisplayName("Test validateSearchParameters(RefundsParams)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RefundSearchValidator.validateSearchParameters(RefundsParams)"})
  void testValidateSearchParameters3() {
    // Arrange
    RefundsParams params = new RefundsParams(" ", " ", "not blank", "1", "not blank", "not blank");

    // Act and Assert
    assertThrows(
        RefundsValidationException.class,
        () -> RefundSearchValidator.validateSearchParameters(params));
  }

  /**
   * Test {@link RefundSearchValidator#validateSearchParameters(RefundsParams)}.
   *
   * <p>Method under test: {@link RefundSearchValidator#validateSearchParameters(RefundsParams)}
   */
  @Test
  @DisplayName("Test validateSearchParameters(RefundsParams)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RefundSearchValidator.validateSearchParameters(RefundsParams)"})
  void testValidateSearchParameters4() {
    // Arrange
    RefundsParams params = new RefundsParams(" ", " ", "not blank", "0", "not blank", "not blank");

    // Act and Assert
    assertThrows(
        RefundsValidationException.class,
        () -> RefundSearchValidator.validateSearchParameters(params));
  }

  /**
   * Test {@link RefundSearchValidator#validateSearchParameters(RefundsParams)}.
   *
   * <p>Method under test: {@link RefundSearchValidator#validateSearchParameters(RefundsParams)}
   */
  @Test
  @DisplayName("Test validateSearchParameters(RefundsParams)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RefundSearchValidator.validateSearchParameters(RefundsParams)"})
  void testValidateSearchParameters5() {
    // Arrange
    RefundsParams params = new RefundsParams(" ", " ", "not blank", null, "not blank", "not blank");

    // Act and Assert
    assertThrows(
        RefundsValidationException.class,
        () -> RefundSearchValidator.validateSearchParameters(params));
  }

  /**
   * Test {@link RefundSearchValidator#validateSearchParameters(RefundsParams)}.
   *
   * <p>Method under test: {@link RefundSearchValidator#validateSearchParameters(RefundsParams)}
   */
  @Test
  @DisplayName("Test validateSearchParameters(RefundsParams)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RefundSearchValidator.validateSearchParameters(RefundsParams)"})
  void testValidateSearchParameters6() {
    // Arrange
    RefundsParams params = new RefundsParams(" ", " ", "1", "not blank", "not blank", "not blank");

    // Act and Assert
    assertThrows(
        RefundsValidationException.class,
        () -> RefundSearchValidator.validateSearchParameters(params));
  }

  /**
   * Test {@link RefundSearchValidator#validateSearchParameters(RefundsParams)}.
   *
   * <p>Method under test: {@link RefundSearchValidator#validateSearchParameters(RefundsParams)}
   */
  @Test
  @DisplayName("Test validateSearchParameters(RefundsParams)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RefundSearchValidator.validateSearchParameters(RefundsParams)"})
  void testValidateSearchParameters7() {
    // Arrange
    RefundsParams params = new RefundsParams(" ", " ", "0", "not blank", "not blank", "not blank");

    // Act and Assert
    assertThrows(
        RefundsValidationException.class,
        () -> RefundSearchValidator.validateSearchParameters(params));
  }

  /**
   * Test {@link RefundSearchValidator#validateSearchParameters(RefundsParams)}.
   *
   * <p>Method under test: {@link RefundSearchValidator#validateSearchParameters(RefundsParams)}
   */
  @Test
  @DisplayName("Test validateSearchParameters(RefundsParams)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RefundSearchValidator.validateSearchParameters(RefundsParams)"})
  void testValidateSearchParameters8() {
    // Arrange
    RefundsParams params = new RefundsParams(" ", " ", null, "not blank", "not blank", "not blank");

    // Act and Assert
    assertThrows(
        RefundsValidationException.class,
        () -> RefundSearchValidator.validateSearchParameters(params));
  }

  /**
   * Test {@link RefundSearchValidator#validateSearchParameters(RefundsParams)}.
   *
   * <p>Method under test: {@link RefundSearchValidator#validateSearchParameters(RefundsParams)}
   */
  @Test
  @DisplayName("Test validateSearchParameters(RefundsParams)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RefundSearchValidator.validateSearchParameters(RefundsParams)"})
  void testValidateSearchParameters9() {
    // Arrange
    RefundsParams params = new RefundsParams(" ", " ", "not blank", "not blank", "not blank", null);

    // Act and Assert
    assertThrows(
        RefundsValidationException.class,
        () -> RefundSearchValidator.validateSearchParameters(params));
  }

  /**
   * Test {@link RefundSearchValidator#validateSearchParameters(RefundsParams)}.
   *
   * <p>Method under test: {@link RefundSearchValidator#validateSearchParameters(RefundsParams)}
   */
  @Test
  @DisplayName("Test validateSearchParameters(RefundsParams)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RefundSearchValidator.validateSearchParameters(RefundsParams)"})
  void testValidateSearchParameters10() {
    // Arrange
    RefundsParams params =
        new RefundsParams("", "2020-03-01", "Page", "Display Size", "2020-03-01", "2020-03-01");

    // Act and Assert
    assertThrows(
        RefundsValidationException.class,
        () -> RefundSearchValidator.validateSearchParameters(params));
  }

  /**
   * Test {@link RefundSearchValidator#validateSearchParameters(RefundsParams)}.
   *
   * <p>Method under test: {@link RefundSearchValidator#validateSearchParameters(RefundsParams)}
   */
  @Test
  @DisplayName("Test validateSearchParameters(RefundsParams)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RefundSearchValidator.validateSearchParameters(RefundsParams)"})
  void testValidateSearchParameters11() {
    // Arrange
    RefundsParams params =
        new RefundsParams(
            "2020-03-01", "2020-03-01", "", "Display Size", "2020-03-01", "2020-03-01");

    // Act and Assert
    assertThrows(
        RefundsValidationException.class,
        () -> RefundSearchValidator.validateSearchParameters(params));
  }

  /**
   * Test {@link RefundSearchValidator#validateSearchParameters(RefundsParams)}.
   *
   * <p>Method under test: {@link RefundSearchValidator#validateSearchParameters(RefundsParams)}
   */
  @Test
  @DisplayName("Test validateSearchParameters(RefundsParams)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RefundSearchValidator.validateSearchParameters(RefundsParams)"})
  void testValidateSearchParameters12() {
    // Arrange
    RefundsParams params =
        new RefundsParams("2020-03-01", "2020-03-01", "Page", "Display Size", "", "2020-03-01");

    // Act and Assert
    assertThrows(
        RefundsValidationException.class,
        () -> RefundSearchValidator.validateSearchParameters(params));
  }
}
