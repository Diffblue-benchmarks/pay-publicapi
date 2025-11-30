package uk.gov.pay.api.filter.ratelimit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RateLimitExceptionDiffblueTest {
  /**
   * Test new {@link RateLimitException} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link RateLimitException}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RateLimitException.<init>()"})
  public void testNewRateLimitException() {
    // Arrange and Act
    RateLimitException actualRateLimitException = new RateLimitException();

    // Assert
    assertNull(actualRateLimitException.getMessage());
    assertNull(actualRateLimitException.getCause());
    assertEquals(0, actualRateLimitException.getSuppressed().length);
  }
}
