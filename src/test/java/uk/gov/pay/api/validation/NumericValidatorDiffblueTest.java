package uk.gov.pay.api.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class NumericValidatorDiffblueTest {
  /**
   * Test {@link NumericValidator#isValidOrNull(String)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link NumericValidator#isValidOrNull(String)}
   */
  @Test
  @DisplayName("Test isValidOrNull(String); when '42'; then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NumericValidator.isValidOrNull(String)"})
  void testIsValidOrNull_when42_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(NumericValidator.isValidOrNull("42"));
  }

  /**
   * Test {@link NumericValidator#isValidOrNull(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link NumericValidator#isValidOrNull(String)}
   */
  @Test
  @DisplayName("Test isValidOrNull(String); when 'null'; then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NumericValidator.isValidOrNull(String)"})
  void testIsValidOrNull_whenNull_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(NumericValidator.isValidOrNull(null));
  }

  /**
   * Test {@link NumericValidator#isValidOrNull(String)}.
   *
   * <ul>
   *   <li>When space.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link NumericValidator#isValidOrNull(String)}
   */
  @Test
  @DisplayName("Test isValidOrNull(String); when space; then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NumericValidator.isValidOrNull(String)"})
  void testIsValidOrNull_whenSpace_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(NumericValidator.isValidOrNull(" "));
  }

  /**
   * Test {@link NumericValidator#isValidOrNull(String)}.
   *
   * <ul>
   *   <li>When {@code Value}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link NumericValidator#isValidOrNull(String)}
   */
  @Test
  @DisplayName("Test isValidOrNull(String); when 'Value'; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NumericValidator.isValidOrNull(String)"})
  void testIsValidOrNull_whenValue_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(NumericValidator.isValidOrNull("Value"));
  }

  /**
   * Test {@link NumericValidator#isValid(String)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link NumericValidator#isValid(String)}
   */
  @Test
  @DisplayName("Test isValid(String); when '42'; then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NumericValidator.isValid(String)"})
  void testIsValid_when42_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(NumericValidator.isValid("42"));
  }

  /**
   * Test {@link NumericValidator#isValid(String)}.
   *
   * <ul>
   *   <li>When {@code Value}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link NumericValidator#isValid(String)}
   */
  @Test
  @DisplayName("Test isValid(String); when 'Value'; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NumericValidator.isValid(String)"})
  void testIsValid_whenValue_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(NumericValidator.isValid("Value"));
  }
}
