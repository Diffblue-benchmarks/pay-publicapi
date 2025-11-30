package uk.gov.pay.api.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class URLValidatorDiffblueTest {
  /**
   * Test {@link URLValidator#urlValidatorValueOf(boolean)}.
   *
   * <ul>
   *   <li>When {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link URLValidator#urlValidatorValueOf(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"URLValidator URLValidator.urlValidatorValueOf(boolean)"})
  public void testUrlValidatorValueOf_whenFalse() {
    // Arrange and Act
    URLValidator actualUrlValidatorValueOfResult = URLValidator.urlValidatorValueOf(false);

    // Assert
    assertTrue(actualUrlValidatorValueOfResult.isValid("https://example.org/example"));
  }

  /**
   * Test {@link URLValidator#urlValidatorValueOf(boolean)}.
   *
   * <ul>
   *   <li>When {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link URLValidator#urlValidatorValueOf(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"URLValidator URLValidator.urlValidatorValueOf(boolean)"})
  public void testUrlValidatorValueOf_whenTrue() {
    // Arrange and Act
    URLValidator actualUrlValidatorValueOfResult = URLValidator.urlValidatorValueOf(true);

    // Assert
    assertTrue(actualUrlValidatorValueOfResult.isValid("https://example.org/example"));
  }

  /**
   * Test {@link URLValidator#isValid(String)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link URLValidator#isValid(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean URLValidator.isValid(String)"})
  public void testIsValid_when42_thenReturnFalse() {
    // Arrange
    URLValidator urlValidatorValueOfResult = URLValidator.urlValidatorValueOf(true);

    // Act and Assert
    assertFalse(urlValidatorValueOfResult.isValid("42"));
  }

  /**
   * Test {@link URLValidator#isValid(String)}.
   *
   * <ul>
   *   <li>When {@code [::FFFF:999.999.999.999]:9U}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link URLValidator#isValid(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean URLValidator.isValid(String)"})
  public void testIsValid_whenFfff9999999999999u_thenReturnFalse() {
    // Arrange
    URLValidator urlValidatorValueOfResult = URLValidator.urlValidatorValueOf(true);

    // Act and Assert
    assertFalse(urlValidatorValueOfResult.isValid("[::FFFF:999.999.999.999]:9U"));
  }

  /**
   * Test {@link URLValidator#isValid(String)}.
   *
   * <ul>
   *   <li>When {@code https://example.org/example}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link URLValidator#isValid(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean URLValidator.isValid(String)"})
  public void testIsValid_whenHttpsExampleOrgExample_thenReturnTrue() {
    // Arrange
    URLValidator urlValidatorValueOfResult = URLValidator.urlValidatorValueOf(true);

    // Act and Assert
    assertTrue(urlValidatorValueOfResult.isValid("https://example.org/example"));
  }

  /**
   * Test {@link URLValidator#isValid(String)}.
   *
   * <ul>
   *   <li>When {@code https://example.org/examplehttps://example.org/example}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link URLValidator#isValid(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean URLValidator.isValid(String)"})
  public void testIsValid_whenHttpsExampleOrgExamplehttpsExampleOrgExample_thenReturnTrue() {
    // Arrange
    URLValidator urlValidatorValueOfResult = URLValidator.urlValidatorValueOf(true);

    // Act and Assert
    assertTrue(
        urlValidatorValueOfResult.isValid(
            "https://example.org/examplehttps://example.org/example"));
  }

  /**
   * Test {@link URLValidator#isValid(String)}.
   *
   * <ul>
   *   <li>When {@code UUhttps://example.org/example}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link URLValidator#isValid(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean URLValidator.isValid(String)"})
  public void testIsValid_whenUUhttpsExampleOrgExample_thenReturnFalse() {
    // Arrange
    URLValidator urlValidatorValueOfResult = URLValidator.urlValidatorValueOf(true);

    // Act and Assert
    assertFalse(urlValidatorValueOfResult.isValid("UUhttps://example.org/example"));
  }
}
