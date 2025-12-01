package uk.gov.pay.api.auth;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.app.config.PublicApiConfig;

class AccountAuthenticatorDiffblueTest {
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
  @DisplayName(
      "Test new AccountAuthenticator(Client, PublicApiConfig); then PublicApiConfig (default constructor) ServerFactory DefaultServerFactory")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AccountAuthenticator.<init>(Client, PublicApiConfig)"})
  void testNewAccountAuthenticator_thenPublicApiConfigServerFactoryDefaultServerFactory() {
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
