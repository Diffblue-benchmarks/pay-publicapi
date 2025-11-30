package uk.gov.pay.api.filter.ratelimit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
import uk.gov.pay.api.app.config.RateLimiterConfig;
import uk.gov.pay.api.filter.RateLimiterKey;

public class RateLimitManagerDiffblueTest {
  /**
   * Test {@link RateLimitManager#getAllowedNumberOfRequests(RateLimiterKey, String)}.
   *
   * <ul>
   *   <li>Given {@code Method}.
   * </ul>
   *
   * <p>Method under test: {@link RateLimitManager#getAllowedNumberOfRequests(RateLimiterKey,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int RateLimitManager.getAllowedNumberOfRequests(RateLimiterKey, String)"})
  public void testGetAllowedNumberOfRequests_givenMethod() {
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

    RateLimiterConfig config = new RateLimiterConfig();
    config.setAdminFactory(admin);
    config.setHealthFactory(mock(HealthFactory.class));
    config.setLoggingFactory(new DefaultLoggingFactory());
    config.setMetricsFactory(new MetricsFactory());
    config.setServerFactory(new DefaultServerFactory());
    RateLimitManager rateLimitManager = new RateLimitManager(config);

    RateLimiterKey rateLimiterKey = mock(RateLimiterKey.class);
    when(rateLimiterKey.getMethod()).thenReturn("Method");

    // Act
    int actualAllowedNumberOfRequests =
        rateLimitManager.getAllowedNumberOfRequests(rateLimiterKey, "3");

    // Assert
    verify(rateLimiterKey).getMethod();
    assertEquals(0, actualAllowedNumberOfRequests);
  }

  /**
   * Test {@link RateLimitManager#getAllowedNumberOfRequests(RateLimiterKey, String)}.
   *
   * <ul>
   *   <li>Given {@code POST}.
   *   <li>When {@link RateLimiterKey} {@link RateLimiterKey#getMethod()} return {@code POST}.
   * </ul>
   *
   * <p>Method under test: {@link RateLimitManager#getAllowedNumberOfRequests(RateLimiterKey,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int RateLimitManager.getAllowedNumberOfRequests(RateLimiterKey, String)"})
  public void testGetAllowedNumberOfRequests_givenPost_whenRateLimiterKeyGetMethodReturnPost() {
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

    RateLimiterConfig config = new RateLimiterConfig();
    config.setAdminFactory(admin);
    config.setHealthFactory(mock(HealthFactory.class));
    config.setLoggingFactory(new DefaultLoggingFactory());
    config.setMetricsFactory(new MetricsFactory());
    config.setServerFactory(new DefaultServerFactory());
    RateLimitManager rateLimitManager = new RateLimitManager(config);

    RateLimiterKey rateLimiterKey = mock(RateLimiterKey.class);
    when(rateLimiterKey.getMethod()).thenReturn("POST");

    // Act
    int actualAllowedNumberOfRequests =
        rateLimitManager.getAllowedNumberOfRequests(rateLimiterKey, "3");

    // Assert
    verify(rateLimiterKey).getMethod();
    assertEquals(0, actualAllowedNumberOfRequests);
  }

  /**
   * Test {@link RateLimitManager#getRateLimitInterval(String)}.
   *
   * <p>Method under test: {@link RateLimitManager#getRateLimitInterval(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int RateLimitManager.getRateLimitInterval(String)"})
  public void testGetRateLimitInterval() {
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

    RateLimiterConfig config = new RateLimiterConfig();
    config.setAdminFactory(admin);
    config.setHealthFactory(mock(HealthFactory.class));
    config.setLoggingFactory(new DefaultLoggingFactory());
    config.setMetricsFactory(new MetricsFactory());
    config.setServerFactory(new DefaultServerFactory());

    // Act and Assert
    assertEquals(0, new RateLimitManager(config).getRateLimitInterval("3"));
  }
}
