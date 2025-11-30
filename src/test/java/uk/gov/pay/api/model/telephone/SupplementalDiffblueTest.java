package uk.gov.pay.api.model.telephone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Optional;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SupplementalDiffblueTest {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Supplemental.<init>()", "void Supplemental.<init>(String, String)"})
  public void testNewSupplemental_thenReturnNotErrorCodePresent() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Supplemental.<init>()", "void Supplemental.<init>(String, String)"})
  public void testNewSupplemental_whenAnErrorOccurred_thenReturnErrorCodeIsAnErrorOccurred() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional Supplemental.getErrorCode()"})
  public void testGetErrorCode() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional Supplemental.getErrorMessage()"})
  public void testGetErrorMessage() {
    // Arrange and Act
    Optional<String> actualErrorMessage =
        new Supplemental("An error occurred", "An error occurred").getErrorMessage();

    // Assert
    assertEquals("An error occurred", actualErrorMessage.get());
    assertTrue(actualErrorMessage.isPresent());
  }
}
