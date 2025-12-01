package uk.gov.pay.api.filter.ratelimit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class RateLimitExceptionDiffblueTest {
  /**
   * Test new {@link RateLimitException} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link RateLimitException}
   */
  @Test
  @DisplayName("Test new RateLimitException (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RateLimitException.<init>()"})
  void testNewRateLimitException() {
    // Arrange and Act
    RateLimitException actualRateLimitException = new RateLimitException();

    // Assert
    assertNull(actualRateLimitException.getMessage());
    assertNull(actualRateLimitException.getCause());
    assertEquals(0, actualRateLimitException.getSuppressed().length);
  }
}
