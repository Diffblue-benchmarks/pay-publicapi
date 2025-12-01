package uk.gov.pay.api.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class URLValidatorDiffblueTest {
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
  @DisplayName("Test urlValidatorValueOf(boolean); when 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"URLValidator URLValidator.urlValidatorValueOf(boolean)"})
  void testUrlValidatorValueOf_whenFalse() {
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
  @DisplayName("Test urlValidatorValueOf(boolean); when 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"URLValidator URLValidator.urlValidatorValueOf(boolean)"})
  void testUrlValidatorValueOf_whenTrue() {
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
  @DisplayName("Test isValid(String); when '42'; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean URLValidator.isValid(String)"})
  void testIsValid_when42_thenReturnFalse() {
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
  @DisplayName("Test isValid(String); when '[::FFFF:999.999.999.999]:9U'; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean URLValidator.isValid(String)"})
  void testIsValid_whenFfff9999999999999u_thenReturnFalse() {
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
  @DisplayName("Test isValid(String); when 'https://example.org/example'; then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean URLValidator.isValid(String)"})
  void testIsValid_whenHttpsExampleOrgExample_thenReturnTrue() {
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
  @DisplayName(
      "Test isValid(String); when 'https://example.org/examplehttps://example.org/example'; then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean URLValidator.isValid(String)"})
  void testIsValid_whenHttpsExampleOrgExamplehttpsExampleOrgExample_thenReturnTrue() {
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
  @DisplayName("Test isValid(String); when 'UUhttps://example.org/example'; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean URLValidator.isValid(String)"})
  void testIsValid_whenUUhttpsExampleOrgExample_thenReturnFalse() {
    // Arrange
    URLValidator urlValidatorValueOfResult = URLValidator.urlValidatorValueOf(true);

    // Act and Assert
    assertFalse(urlValidatorValueOfResult.isValid("UUhttps://example.org/example"));
  }
}
