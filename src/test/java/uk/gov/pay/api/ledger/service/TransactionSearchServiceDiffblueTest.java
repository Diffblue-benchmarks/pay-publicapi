package uk.gov.pay.api.ledger.service;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
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
import jakarta.ws.rs.client.Client;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.pay.api.app.config.PublicApiConfig;
import uk.gov.pay.api.auth.Account;
import uk.gov.pay.api.exception.BadRequestException;
import uk.gov.pay.api.ledger.model.TransactionSearchParams;
import uk.gov.pay.api.model.TokenPaymentType;
import uk.gov.pay.api.service.PaymentUriGenerator;

public class TransactionSearchServiceDiffblueTest {
  /**
   * Test {@link TransactionSearchService#doSearch(Account, TransactionSearchParams)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then throw {@link BadRequestException}.
   * </ul>
   *
   * <p>Method under test: {@link TransactionSearchService#doSearch(Account,
   * TransactionSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "uk.gov.pay.api.ledger.model.SearchResults TransactionSearchService.doSearch(Account, TransactionSearchParams)"
  })
  public void testDoSearch_given42_thenThrowBadRequestException() {
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

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(admin);
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());

    AdminFactory admin2 = new AdminFactory();
    admin2.setHealthChecks(new HealthCheckConfiguration());
    admin2.setTasks(new TaskConfiguration());

    PublicApiConfig configuration2 = new PublicApiConfig();
    configuration2.setAdminFactory(admin2);
    configuration2.setHealthFactory(mock(HealthFactory.class));
    configuration2.setLoggingFactory(new DefaultLoggingFactory());
    configuration2.setMetricsFactory(new MetricsFactory());
    configuration2.setServerFactory(new DefaultServerFactory());
    LedgerUriGenerator ledgerUriGenerator = new LedgerUriGenerator(configuration2);
    Client client = mock(Client.class);

    TransactionSearchService transactionSearchService =
        new TransactionSearchService(
            client, configuration, ledgerUriGenerator, new PaymentUriGenerator());
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    TransactionSearchParams searchParams = new TransactionSearchParams();
    searchParams.setAccountId("42");

    // Act and Assert
    assertThrows(
        BadRequestException.class, () -> transactionSearchService.doSearch(account, searchParams));
  }
}
