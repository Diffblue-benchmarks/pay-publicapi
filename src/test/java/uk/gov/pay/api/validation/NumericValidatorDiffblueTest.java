package uk.gov.pay.api.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class NumericValidatorDiffblueTest {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NumericValidator.isValidOrNull(String)"})
  public void testIsValidOrNull_when42_thenReturnTrue() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NumericValidator.isValidOrNull(String)"})
  public void testIsValidOrNull_whenNull_thenReturnTrue() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NumericValidator.isValidOrNull(String)"})
  public void testIsValidOrNull_whenSpace_thenReturnTrue() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NumericValidator.isValidOrNull(String)"})
  public void testIsValidOrNull_whenValue_thenReturnFalse() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NumericValidator.isValid(String)"})
  public void testIsValid_when42_thenReturnTrue() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NumericValidator.isValid(String)"})
  public void testIsValid_whenValue_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(NumericValidator.isValid("Value"));
  }
}
