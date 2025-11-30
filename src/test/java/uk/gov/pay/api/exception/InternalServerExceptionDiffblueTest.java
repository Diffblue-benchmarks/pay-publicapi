package uk.gov.pay.api.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class InternalServerExceptionDiffblueTest {
  /**
   * Test {@link InternalServerException#InternalServerException(String)}.
   *
   * <p>Method under test: {@link InternalServerException#InternalServerException(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InternalServerException.<init>(String)"})
  public void testNewInternalServerException() {
    // Arrange and Act
    InternalServerException actualInternalServerException =
        new InternalServerException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualInternalServerException.getMessage());
    assertNull(actualInternalServerException.getCause());
    assertEquals(0, actualInternalServerException.getSuppressed().length);
  }
}
