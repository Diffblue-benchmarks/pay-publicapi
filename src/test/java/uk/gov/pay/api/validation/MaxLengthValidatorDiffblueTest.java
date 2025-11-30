package uk.gov.pay.api.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MaxLengthValidatorDiffblueTest {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean MaxLengthValidator.isInvalid(String, int)"})
  public void testIsInvalid_when42_thenReturnFalse() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean MaxLengthValidator.isInvalid(String, int)"})
  public void testIsInvalid_whenNotBlank_thenReturnTrue() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean MaxLengthValidator.isInvalid(String, int)"})
  public void testIsInvalid_whenNull_thenReturnFalse() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean MaxLengthValidator.isInvalid(String, int)"})
  public void testIsInvalid_whenSpace_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(MaxLengthValidator.isInvalid(" ", 3));
  }
}
