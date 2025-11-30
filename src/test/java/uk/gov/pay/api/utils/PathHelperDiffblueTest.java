package uk.gov.pay.api.utils;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PathHelperDiffblueTest {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String PathHelper.getPathType(String, String)"})
  public void testGetPathType_when42_thenReturnEmptyString() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String PathHelper.getPathType(String, String)"})
  public void testGetPathType_whenCapture_thenReturnCapturePayment() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String PathHelper.getPathType(String, String)"})
  public void testGetPathType_whenEmptyString_thenReturnEmptyString() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String PathHelper.getPathType(String, String)"})
  public void testGetPathType_whenPayments_thenReturnEmptyString() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String PathHelper.getPathType(String, String)"})
  public void testGetPathType_whenPost_thenReturnCreatePayment() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String PathHelper.getPathType(String, String)"})
  public void testGetPathType_whenSlash_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", PathHelper.getPathType("/", "Method"));
  }
}
