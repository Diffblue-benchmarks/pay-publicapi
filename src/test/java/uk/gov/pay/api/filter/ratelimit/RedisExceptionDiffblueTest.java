package uk.gov.pay.api.filter.ratelimit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RedisExceptionDiffblueTest {
  /**
   * Test new {@link RedisException} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link RedisException}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RedisException.<init>()"})
  public void testNewRedisException() {
    // Arrange and Act
    RedisException actualRedisException = new RedisException();

    // Assert
    assertNull(actualRedisException.getMessage());
    assertNull(actualRedisException.getCause());
    assertEquals(0, actualRedisException.getSuppressed().length);
  }
}
