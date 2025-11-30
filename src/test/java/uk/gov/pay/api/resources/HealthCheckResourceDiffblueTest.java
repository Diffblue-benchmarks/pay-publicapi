package uk.gov.pay.api.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheck.Result;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.dropwizard.core.setup.Environment;
import jakarta.ws.rs.core.Response;
import java.util.Map;
import java.util.TreeMap;
import org.glassfish.jersey.message.internal.OutboundJaxrsResponse;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class HealthCheckResourceDiffblueTest {
  /**
   * Test {@link HealthCheckResource#healthCheck()}.
   *
   * <ul>
   *   <li>Given {@link Environment} {@link Environment#healthChecks()} return {@link
   *       HealthCheckRegistry#HealthCheckRegistry()}.
   * </ul>
   *
   * <p>Method under test: {@link HealthCheckResource#healthCheck()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Response HealthCheckResource.healthCheck()"})
  public void testHealthCheck_givenEnvironmentHealthChecksReturnHealthCheckRegistry() {
    // Arrange
    Environment environment = mock(Environment.class);
    when(environment.healthChecks()).thenReturn(new HealthCheckRegistry());

    // Act
    Response actualHealthCheckResult = new HealthCheckResource(environment).healthCheck();

    // Assert
    verify(environment).healthChecks();
    Object entity = actualHealthCheckResult.getEntity();
    assertTrue(entity instanceof Map);
    assertTrue(actualHealthCheckResult instanceof OutboundJaxrsResponse);
    assertTrue(((Map<Object, Object>) entity).isEmpty());
  }

  /**
   * Test {@link HealthCheckResource#healthCheck()}.
   *
   * <ul>
   *   <li>Then return StringHeaders is {@link TreeMap#TreeMap()}.
   * </ul>
   *
   * <p>Method under test: {@link HealthCheckResource#healthCheck()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Response HealthCheckResource.healthCheck()"})
  public void testHealthCheck_thenReturnStringHeadersIsTreeMap() {
    // Arrange
    HealthCheckRegistry healthCheckRegistry = mock(HealthCheckRegistry.class);
    TreeMap<String, Result> stringResultMap = new TreeMap<>();
    when(healthCheckRegistry.runHealthChecks()).thenReturn(stringResultMap);

    Environment environment = mock(Environment.class);
    when(environment.healthChecks()).thenReturn(healthCheckRegistry);

    // Act
    Response actualHealthCheckResult = new HealthCheckResource(environment).healthCheck();

    // Assert
    verify(healthCheckRegistry).runHealthChecks();
    verify(environment).healthChecks();
    Object entity = actualHealthCheckResult.getEntity();
    assertTrue(entity instanceof Map);
    assertTrue(actualHealthCheckResult instanceof OutboundJaxrsResponse);
    assertTrue(((Map<Object, Object>) entity).isEmpty());
    assertEquals(stringResultMap, actualHealthCheckResult.getStringHeaders());
    assertEquals(
        stringResultMap,
        ((OutboundJaxrsResponse) actualHealthCheckResult).getContext().getStringHeaders());
  }
}
