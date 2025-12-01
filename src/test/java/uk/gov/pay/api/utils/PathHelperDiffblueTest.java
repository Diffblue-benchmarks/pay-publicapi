package uk.gov.pay.api.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class PathHelperDiffblueTest {
  /**
   * Test {@link PathHelper#getPathType(String, String)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link PathHelper#getPathType(String, String)}
   */
  @Test
  @DisplayName("Test getPathType(String, String); when '42'; then return empty string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String PathHelper.getPathType(String, String)"})
  void testGetPathType_when42_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", PathHelper.getPathType("42", "Method"));
  }

  /**
   * Test {@link PathHelper#getPathType(String, String)}.
   *
   * <ul>
   *   <li>When {@code /capture}.
   *   <li>Then return {@code capture_payment}.
   * </ul>
   *
   * <p>Method under test: {@link PathHelper#getPathType(String, String)}
   */
  @Test
  @DisplayName("Test getPathType(String, String); when '/capture'; then return 'capture_payment'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String PathHelper.getPathType(String, String)"})
  void testGetPathType_whenCapture_thenReturnCapturePayment() {
    // Arrange, Act and Assert
    assertEquals("capture_payment", PathHelper.getPathType("/capture", "Method"));
  }

  /**
   * Test {@link PathHelper#getPathType(String, String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link PathHelper#getPathType(String, String)}
   */
  @Test
  @DisplayName("Test getPathType(String, String); when empty string; then return empty string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String PathHelper.getPathType(String, String)"})
  void testGetPathType_whenEmptyString_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", PathHelper.getPathType("", "Method"));
  }

  /**
   * Test {@link PathHelper#getPathType(String, String)}.
   *
   * <ul>
   *   <li>When {@code /payments}.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link PathHelper#getPathType(String, String)}
   */
  @Test
  @DisplayName("Test getPathType(String, String); when '/payments'; then return empty string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String PathHelper.getPathType(String, String)"})
  void testGetPathType_whenPayments_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", PathHelper.getPathType("/payments", "Method"));
  }

  /**
   * Test {@link PathHelper#getPathType(String, String)}.
   *
   * <ul>
   *   <li>When {@code POST}.
   *   <li>Then return {@code create_payment}.
   * </ul>
   *
   * <p>Method under test: {@link PathHelper#getPathType(String, String)}
   */
  @Test
  @DisplayName("Test getPathType(String, String); when 'POST'; then return 'create_payment'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String PathHelper.getPathType(String, String)"})
  void testGetPathType_whenPost_thenReturnCreatePayment() {
    // Arrange, Act and Assert
    assertEquals("create_payment", PathHelper.getPathType("/payments", "POST"));
  }

  /**
   * Test {@link PathHelper#getPathType(String, String)}.
   *
   * <ul>
   *   <li>When {@code /}.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link PathHelper#getPathType(String, String)}
   */
  @Test
  @DisplayName("Test getPathType(String, String); when '/'; then return empty string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String PathHelper.getPathType(String, String)"})
  void testGetPathType_whenSlash_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", PathHelper.getPathType("/", "Method"));
  }
}
