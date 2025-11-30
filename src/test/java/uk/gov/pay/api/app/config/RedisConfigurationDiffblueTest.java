package uk.gov.pay.api.app.config;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RedisConfigurationDiffblueTest {
  /**
   * Test {@link RedisConfiguration#getUrl()}.
   *
   * <p>Method under test: {@link RedisConfiguration#getUrl()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String RedisConfiguration.getUrl()"})
  public void testGetUrl() {
    // Arrange, Act and Assert
    assertEquals("redis://null", new RedisConfiguration().getUrl());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link RedisConfiguration}
   *   <li>{@link RedisConfiguration#getReconnectDelayBase()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RedisConfiguration.<init>()",
    "long RedisConfiguration.getReconnectDelayBase()"
  })
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals(0L, new RedisConfiguration().getReconnectDelayBase());
  }
}
