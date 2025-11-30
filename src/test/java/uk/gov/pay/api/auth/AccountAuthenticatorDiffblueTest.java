package uk.gov.pay.api.auth;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.dropwizard.core.server.DefaultServerFactory;
import io.dropwizard.core.server.ServerFactory;
import io.dropwizard.core.setup.AdminFactory;
import io.dropwizard.core.setup.HealthCheckConfiguration;
import io.dropwizard.health.HealthFactory;
import io.dropwizard.logging.common.DefaultLoggingFactory;
import io.dropwizard.logging.common.LoggingFactory;
import io.dropwizard.metrics.common.MetricsFactory;
import io.dropwizard.servlets.tasks.TaskConfiguration;
import jakarta.ws.rs.client.Client;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.pay.api.app.config.PublicApiConfig;

public class AccountAuthenticatorDiffblueTest {
  /**
   * Test {@link AccountAuthenticator#AccountAuthenticator(Client, PublicApiConfig)}.
   *
   * <ul>
   *   <li>Then {@link PublicApiConfig} (default constructor) ServerFactory {@link
   *       DefaultServerFactory}.
   * </ul>
   *
   * <p>Method under test: {@link AccountAuthenticator#AccountAuthenticator(Client,
   * PublicApiConfig)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AccountAuthenticator.<init>(Client, PublicApiConfig)"})
  public void testNewAccountAuthenticator_thenPublicApiConfigServerFactoryDefaultServerFactory() {
    // Arrange
    Client client = mock(Client.class);

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

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(admin);
    configuration.setHealthFactory(mock(HealthFactory.class));
    DefaultLoggingFactory factory = new DefaultLoggingFactory();
    configuration.setLoggingFactory(factory);
    MetricsFactory metrics = new MetricsFactory();
    configuration.setMetricsFactory(metrics);
    DefaultServerFactory factory2 = new DefaultServerFactory();
    configuration.setServerFactory(factory2);

    // Act
    new AccountAuthenticator(client, configuration);

    // Assert that nothing has changed
    ServerFactory serverFactory = configuration.getServerFactory();
    assertTrue(serverFactory instanceof DefaultServerFactory);
    LoggingFactory loggingFactory = configuration.getLoggingFactory();
    assertTrue(loggingFactory instanceof DefaultLoggingFactory);
    assertFalse(configuration.getEcsContainerMetadataUriV4().isPresent());
    assertSame(factory2, serverFactory);
    assertSame(admin, configuration.getAdminFactory());
    assertSame(factory, loggingFactory);
    assertSame(metrics, configuration.getMetricsFactory());
  }
}
