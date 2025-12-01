package uk.gov.pay.api.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class InternalServerExceptionDiffblueTest {
  /**
   * Test {@link InternalServerException#InternalServerException(String)}.
   *
   * <p>Method under test: {@link InternalServerException#InternalServerException(String)}
   */
  @Test
  @DisplayName("Test new InternalServerException(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void InternalServerException.<init>(String)"})
  void testNewInternalServerException() {
    // Arrange and Act
    InternalServerException actualInternalServerException =
        new InternalServerException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualInternalServerException.getMessage());
    assertNull(actualInternalServerException.getCause());
    assertEquals(0, actualInternalServerException.getSuppressed().length);
  }
}
