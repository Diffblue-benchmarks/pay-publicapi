package uk.gov.pay.api.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class MaxLengthValidatorDiffblueTest {
  /**
   * Test {@link MaxLengthValidator#isInvalid(String, int)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link MaxLengthValidator#isInvalid(String, int)}
   */
  @Test
  @DisplayName("Test isInvalid(String, int); when '42'; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean MaxLengthValidator.isInvalid(String, int)"})
  void testIsInvalid_when42_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(MaxLengthValidator.isInvalid("42", 3));
  }

  /**
   * Test {@link MaxLengthValidator#isInvalid(String, int)}.
   *
   * <ul>
   *   <li>When {@code not blank}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link MaxLengthValidator#isInvalid(String, int)}
   */
  @Test
  @DisplayName("Test isInvalid(String, int); when 'not blank'; then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean MaxLengthValidator.isInvalid(String, int)"})
  void testIsInvalid_whenNotBlank_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(MaxLengthValidator.isInvalid("not blank", 3));
  }

  /**
   * Test {@link MaxLengthValidator#isInvalid(String, int)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link MaxLengthValidator#isInvalid(String, int)}
   */
  @Test
  @DisplayName("Test isInvalid(String, int); when 'null'; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean MaxLengthValidator.isInvalid(String, int)"})
  void testIsInvalid_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(MaxLengthValidator.isInvalid(null, 3));
  }

  /**
   * Test {@link MaxLengthValidator#isInvalid(String, int)}.
   *
   * <ul>
   *   <li>When space.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link MaxLengthValidator#isInvalid(String, int)}
   */
  @Test
  @DisplayName("Test isInvalid(String, int); when space; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean MaxLengthValidator.isInvalid(String, int)"})
  void testIsInvalid_whenSpace_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(MaxLengthValidator.isInvalid(" ", 3));
  }
}
