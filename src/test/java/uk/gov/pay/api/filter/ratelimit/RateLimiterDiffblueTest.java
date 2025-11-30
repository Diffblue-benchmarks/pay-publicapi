package uk.gov.pay.api.filter.ratelimit;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.dropwizard.core.server.DefaultServerFactory;
import io.dropwizard.core.setup.AdminFactory;
import io.dropwizard.core.setup.HealthCheckConfiguration;
import io.dropwizard.health.HealthFactory;
import io.dropwizard.logging.common.DefaultLoggingFactory;
import io.dropwizard.metrics.common.MetricsFactory;
import io.dropwizard.servlets.tasks.TaskConfiguration;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import uk.gov.pay.api.app.config.RateLimiterConfig;
import uk.gov.pay.api.filter.RateLimiterKey;

public class RateLimiterDiffblueTest {
  /**
   * Test {@link RateLimiter#checkRateOf(String, RateLimiterKey)}.
   *
   * <ul>
   *   <li>Given {@link LocalRateLimiter} {@link LocalRateLimiter#checkRateOf(String,
   *       RateLimiterKey)} does nothing.
   *   <li>Then calls {@link LocalRateLimiter#checkRateOf(String, RateLimiterKey)}.
   * </ul>
   *
   * <p>Method under test: {@link RateLimiter#checkRateOf(String, RateLimiterKey)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RateLimiter.checkRateOf(String, RateLimiterKey)"})
  public void testCheckRateOf_givenLocalRateLimiterCheckRateOfDoesNothing_thenCallsCheckRateOf()
      throws RateLimitException, RedisException {
    // Arrange
    LocalRateLimiter localRateLimiter = mock(LocalRateLimiter.class);
    doNothing()
        .when(localRateLimiter)
        .checkRateOf(Mockito.<String>any(), Mockito.<RateLimiterKey>any());

    RedisRateLimiter redisRateLimiter = mock(RedisRateLimiter.class);
    doThrow(new RedisException())
        .when(redisRateLimiter)
        .checkRateOf(Mockito.<String>any(), Mockito.<RateLimiterKey>any());

    RateLimiter rateLimiter = new RateLimiter(localRateLimiter, redisRateLimiter);

    // Act
    rateLimiter.checkRateOf("42", mock(RateLimiterKey.class));

    // Assert
    verify(localRateLimiter).checkRateOf(eq("42"), isA(RateLimiterKey.class));
    verify(redisRateLimiter).checkRateOf(eq("42"), isA(RateLimiterKey.class));
  }

  /**
   * Test {@link RateLimiter#checkRateOf(String, RateLimiterKey)}.
   *
   * <ul>
   *   <li>Given {@link RedisRateLimiter} {@link RedisRateLimiter#checkRateOf(String,
   *       RateLimiterKey)} does nothing.
   *   <li>Then calls {@link RedisRateLimiter#checkRateOf(String, RateLimiterKey)}.
   * </ul>
   *
   * <p>Method under test: {@link RateLimiter#checkRateOf(String, RateLimiterKey)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RateLimiter.checkRateOf(String, RateLimiterKey)"})
  public void testCheckRateOf_givenRedisRateLimiterCheckRateOfDoesNothing_thenCallsCheckRateOf()
      throws RateLimitException, RedisException {
    // Arrange
    AdminFactory admin = new AdminFactory();
    admin.setHealthChecks(new HealthCheckConfiguration());
    admin.setTasks(new TaskConfiguration());

    RateLimiterConfig rateLimiterConfig = new RateLimiterConfig();
    rateLimiterConfig.setAdminFactory(admin);
    rateLimiterConfig.setHealthFactory(mock(HealthFactory.class));
    rateLimiterConfig.setLoggingFactory(new DefaultLoggingFactory());
    rateLimiterConfig.setMetricsFactory(new MetricsFactory());
    rateLimiterConfig.setServerFactory(new DefaultServerFactory());
    LocalRateLimiter localRateLimiter = new LocalRateLimiter(rateLimiterConfig);

    RedisRateLimiter redisRateLimiter = mock(RedisRateLimiter.class);
    doNothing()
        .when(redisRateLimiter)
        .checkRateOf(Mockito.<String>any(), Mockito.<RateLimiterKey>any());

    RateLimiter rateLimiter = new RateLimiter(localRateLimiter, redisRateLimiter);

    // Act
    rateLimiter.checkRateOf("42", mock(RateLimiterKey.class));

    // Assert
    verify(redisRateLimiter).checkRateOf(eq("42"), isA(RateLimiterKey.class));
  }
}
