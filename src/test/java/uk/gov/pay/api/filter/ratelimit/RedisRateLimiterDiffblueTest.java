package uk.gov.pay.api.filter.ratelimit;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.inject.OutOfScopeException;
import io.dropwizard.core.server.DefaultServerFactory;
import io.dropwizard.core.setup.AdminFactory;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.core.setup.HealthCheckConfiguration;
import io.dropwizard.health.HealthFactory;
import io.dropwizard.logging.common.DefaultLoggingFactory;
import io.dropwizard.metrics.common.MetricsFactory;
import io.dropwizard.servlets.tasks.TaskConfiguration;
import io.lettuce.core.RedisClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.app.config.RateLimiterConfig;
import uk.gov.pay.api.filter.RateLimiterKey;
import uk.gov.pay.api.managed.RedisClientManager;

class RedisRateLimiterDiffblueTest {
  /**
   * Test {@link RedisRateLimiter#checkRateOf(String, RateLimiterKey)}.
   *
   * <ul>
   *   <li>Given {@code Key}.
   *   <li>Then throw {@link OutOfScopeException}.
   * </ul>
   *
   * <p>Method under test: {@link RedisRateLimiter#checkRateOf(String, RateLimiterKey)}
   */
  @Test
  @DisplayName(
      "Test checkRateOf(String, RateLimiterKey); given 'Key'; then throw OutOfScopeException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RedisRateLimiter.checkRateOf(String, RateLimiterKey)"})
  void testCheckRateOf_givenKey_thenThrowOutOfScopeException()
      throws RateLimitException, RedisException {
    // Arrange
    HealthCheckConfiguration healthChecks = new HealthCheckConfiguration();
    healthChecks.setMaxThreads(3);
    healthChecks.setMinThreads(1);
    healthChecks.setServletEnabled(true);
    healthChecks.setWorkQueueSize(3);

    TaskConfiguration tasks = new TaskConfiguration();
    tasks.setPrintStackTraceOnError(true);

    AdminFactory admin = new AdminFactory();
    admin.setHealthChecks(healthChecks);
    admin.setTasks(tasks);

    RateLimiterConfig rateLimiterConfig = new RateLimiterConfig();
    rateLimiterConfig.setAdminFactory(admin);
    rateLimiterConfig.setHealthFactory(mock(HealthFactory.class));
    rateLimiterConfig.setLoggingFactory(new DefaultLoggingFactory());
    rateLimiterConfig.setMetricsFactory(new MetricsFactory());
    rateLimiterConfig.setServerFactory(new DefaultServerFactory());
    RedisClientManager redisClientManager = new RedisClientManager(RedisClient.create());

    RedisRateLimiter redisRateLimiter =
        new RedisRateLimiter(rateLimiterConfig, redisClientManager, new Environment("Name"));

    RateLimiterKey key = mock(RateLimiterKey.class);
    when(key.getKey()).thenReturn("Key");

    // Act and Assert
    assertThrows(OutOfScopeException.class, () -> redisRateLimiter.checkRateOf("42", key));
    verify(key).getKey();
  }
}
