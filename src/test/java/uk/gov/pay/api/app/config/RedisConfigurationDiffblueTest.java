package uk.gov.pay.api.app.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class RedisConfigurationDiffblueTest {
  /**
   * Test {@link RedisConfiguration#getUrl()}.
   *
   * <p>Method under test: {@link RedisConfiguration#getUrl()}
   */
  @Test
  @DisplayName("Test getUrl()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String RedisConfiguration.getUrl()"})
  void testGetUrl() {
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
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RedisConfiguration.<init>()",
    "long RedisConfiguration.getReconnectDelayBase()"
  })
  void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals(0L, new RedisConfiguration().getReconnectDelayBase());
  }
}
