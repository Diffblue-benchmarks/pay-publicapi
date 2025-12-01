package uk.gov.pay.api.filter.ratelimit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class RedisExceptionDiffblueTest {
  /**
   * Test new {@link RedisException} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link RedisException}
   */
  @Test
  @DisplayName("Test new RedisException (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RedisException.<init>()"})
  void testNewRedisException() {
    // Arrange and Act
    RedisException actualRedisException = new RedisException();

    // Assert
    assertNull(actualRedisException.getMessage());
    assertNull(actualRedisException.getCause());
    assertEquals(0, actualRedisException.getSuppressed().length);
  }
}
