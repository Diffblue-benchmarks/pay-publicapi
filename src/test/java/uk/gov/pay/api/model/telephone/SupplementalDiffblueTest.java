package uk.gov.pay.api.model.telephone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SupplementalDiffblueTest {
  /**
   * Test {@link Supplemental#Supplemental()}.
   *
   * <ul>
   *   <li>Then return not ErrorCode Present.
   * </ul>
   *
   * <p>Method under test: {@link Supplemental#Supplemental()}
   */
  @Test
  @DisplayName("Test new Supplemental(); then return not ErrorCode Present")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void Supplemental.<init>()", "void Supplemental.<init>(String, String)"})
  void testNewSupplemental_thenReturnNotErrorCodePresent() {
    // Arrange and Act
    Supplemental actualSupplemental = new Supplemental();

    // Assert
    Optional<String> errorCode = actualSupplemental.getErrorCode();
    assertFalse(errorCode.isPresent());
    assertSame(errorCode, actualSupplemental.getErrorMessage());
  }

  /**
   * Test {@link Supplemental#Supplemental(String, String)}.
   *
   * <ul>
   *   <li>When {@code An error occurred}.
   *   <li>Then return ErrorCode is {@code An error occurred}.
   * </ul>
   *
   * <p>Method under test: {@link Supplemental#Supplemental(String, String)}
   */
  @Test
  @DisplayName(
      "Test new Supplemental(String, String); when 'An error occurred'; then return ErrorCode is 'An error occurred'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void Supplemental.<init>()", "void Supplemental.<init>(String, String)"})
  void testNewSupplemental_whenAnErrorOccurred_thenReturnErrorCodeIsAnErrorOccurred() {
    // Arrange and Act
    Supplemental actualSupplemental = new Supplemental("An error occurred", "An error occurred");

    // Assert
    Optional<String> errorCode = actualSupplemental.getErrorCode();
    assertEquals("An error occurred", errorCode.get());
    assertTrue(errorCode.isPresent());
    assertEquals(errorCode, actualSupplemental.getErrorMessage());
  }

  /**
   * Test {@link Supplemental#getErrorCode()}.
   *
   * <p>Method under test: {@link Supplemental#getErrorCode()}
   */
  @Test
  @DisplayName("Test getErrorCode()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional Supplemental.getErrorCode()"})
  void testGetErrorCode() {
    // Arrange and Act
    Optional<String> actualErrorCode =
        new Supplemental("An error occurred", "An error occurred").getErrorCode();

    // Assert
    assertEquals("An error occurred", actualErrorCode.get());
    assertTrue(actualErrorCode.isPresent());
  }

  /**
   * Test {@link Supplemental#getErrorMessage()}.
   *
   * <p>Method under test: {@link Supplemental#getErrorMessage()}
   */
  @Test
  @DisplayName("Test getErrorMessage()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional Supplemental.getErrorMessage()"})
  void testGetErrorMessage() {
    // Arrange and Act
    Optional<String> actualErrorMessage =
        new Supplemental("An error occurred", "An error occurred").getErrorMessage();

    // Assert
    assertEquals("An error occurred", actualErrorMessage.get());
    assertTrue(actualErrorMessage.isPresent());
  }
}
