package uk.gov.pay.api.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
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
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.Response.StatusType;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import org.glassfish.jersey.message.internal.OutboundJaxrsResponse;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import uk.gov.pay.api.app.config.PublicApiConfig;
import uk.gov.pay.api.auth.Account;
import uk.gov.pay.api.ledger.service.LedgerUriGenerator;
import uk.gov.pay.api.model.Address;
import uk.gov.pay.api.model.AuthorisationSummary;
import uk.gov.pay.api.model.CardDetails;
import uk.gov.pay.api.model.CreatePaymentRefundRequest;
import uk.gov.pay.api.model.Exemption;
import uk.gov.pay.api.model.PaymentSettlementSummary;
import uk.gov.pay.api.model.PaymentState;
import uk.gov.pay.api.model.RefundFromConnector;
import uk.gov.pay.api.model.RefundResponse;
import uk.gov.pay.api.model.RefundSummary;
import uk.gov.pay.api.model.RefundsResponse;
import uk.gov.pay.api.model.TokenPaymentType;
import uk.gov.pay.api.model.links.Link;
import uk.gov.pay.api.model.links.PaymentWithAllLinks;
import uk.gov.pay.api.model.links.PaymentWithAllLinks.PaymentWithAllLinksBuilder;
import uk.gov.pay.api.model.links.RefundLinksForSearch;
import uk.gov.pay.api.service.ConnectorService;
import uk.gov.pay.api.service.ConnectorUriGenerator;
import uk.gov.pay.api.service.CreateRefundService;
import uk.gov.pay.api.service.GetPaymentRefundService;
import uk.gov.pay.api.service.GetPaymentRefundsService;
import uk.gov.pay.api.service.GetPaymentService;
import uk.gov.pay.api.service.LedgerService;
import uk.gov.pay.api.service.PublicApiUriGenerator;
import uk.gov.service.payments.commons.model.AgreementPaymentType;
import uk.gov.service.payments.commons.model.AuthorisationMode;
import uk.gov.service.payments.commons.model.SupportedLanguage;
import uk.gov.service.payments.commons.model.charge.ExternalMetadata;

public class PaymentRefundsResourceDiffblueTest {
  /**
   * Test {@link PaymentRefundsResource#getRefunds(Account, String)}.
   *
   * <p>Method under test: {@link PaymentRefundsResource#getRefunds(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RefundsResponse PaymentRefundsResource.getRefunds(Account, String)"})
  public void testGetRefunds() {
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

    GetPaymentRefundsService getPaymentRefundsService = mock(GetPaymentRefundsService.class);
    RefundsResponse fromResult =
        RefundsResponse.from("42", new ArrayList<>(), "Self Link", "Payment Link");
    when(getPaymentRefundsService.getLedgerTransactionTransactions(
            Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(fromResult);

    PublicApiConfig configuration2 = new PublicApiConfig();
    configuration2.setAdminFactory(new AdminFactory());
    configuration2.setHealthFactory(mock(HealthFactory.class));
    configuration2.setLoggingFactory(new DefaultLoggingFactory());
    configuration2.setMetricsFactory(new MetricsFactory());
    configuration2.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator = new PublicApiUriGenerator(configuration2);
    Client client = mock(Client.class);
    ConnectorService connectorService =
        new ConnectorService(client, new ConnectorUriGenerator(new PublicApiConfig()));
    Client client2 = mock(Client.class);
    LedgerService ledgerService =
        new LedgerService(client2, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentRefundService getPaymentRefundService =
        new GetPaymentRefundService(connectorService, ledgerService, publicApiUriGenerator);

    PublicApiConfig configuration3 = new PublicApiConfig();
    configuration3.setAdminFactory(new AdminFactory());
    configuration3.setHealthFactory(mock(HealthFactory.class));
    configuration3.setLoggingFactory(new DefaultLoggingFactory());
    configuration3.setMetricsFactory(new MetricsFactory());
    configuration3.setServerFactory(new DefaultServerFactory());
    ConnectorService connectorService2 =
        new ConnectorService(mock(Client.class), new ConnectorUriGenerator(configuration3));

    PublicApiConfig configuration4 = new PublicApiConfig();
    configuration4.setAdminFactory(new AdminFactory());
    configuration4.setHealthFactory(mock(HealthFactory.class));
    configuration4.setLoggingFactory(new DefaultLoggingFactory());
    configuration4.setMetricsFactory(new MetricsFactory());
    configuration4.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator2 = new PublicApiUriGenerator(configuration4);
    Client client3 = mock(Client.class);
    ConnectorService connectorService3 =
        new ConnectorService(client3, new ConnectorUriGenerator(new PublicApiConfig()));
    Client client4 = mock(Client.class);
    LedgerService ledgerService2 =
        new LedgerService(client4, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator2, connectorService3, ledgerService2);

    AdminFactory admin2 = new AdminFactory();
    admin2.setHealthChecks(new HealthCheckConfiguration());
    admin2.setTasks(new TaskConfiguration());

    PublicApiConfig configuration5 = new PublicApiConfig();
    configuration5.setAdminFactory(admin2);
    configuration5.setHealthFactory(mock(HealthFactory.class));
    configuration5.setLoggingFactory(new DefaultLoggingFactory());
    configuration5.setMetricsFactory(new MetricsFactory());
    configuration5.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator3 = new PublicApiUriGenerator(new PublicApiConfig());
    ConnectorService connectorService4 = new ConnectorService(mock(Client.class), null);
    LedgerService ledgerService3 = new LedgerService(mock(Client.class), null);

    GetPaymentService getPaymentService2 =
        new GetPaymentService(publicApiUriGenerator3, connectorService4, ledgerService3);

    CreateRefundService createRefundService =
        new CreateRefundService(getPaymentService2, mock(Client.class), configuration5);

    PaymentRefundsResource paymentRefundsResource =
        new PaymentRefundsResource(
            configuration,
            getPaymentRefundsService,
            getPaymentRefundService,
            connectorService2,
            getPaymentService,
            createRefundService);

    // Act
    RefundsResponse actualRefunds =
        paymentRefundsResource.getRefunds(new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(getPaymentRefundsService).getLedgerTransactionTransactions(isA(Account.class), eq("42"));
    assertSame(fromResult, actualRefunds);
  }

  /**
   * Test {@link PaymentRefundsResource#getRefundById(Account, String, String, String)}.
   *
   * <ul>
   *   <li>Then calls {@link GetPaymentRefundService#getConnectorPaymentRefund(Account, String,
   *       String)}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentRefundsResource#getRefundById(Account, String, String,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RefundResponse PaymentRefundsResource.getRefundById(Account, String, String, String)"
  })
  public void testGetRefundById_thenCallsGetConnectorPaymentRefund() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();

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

    PublicApiConfig configuration2 = new PublicApiConfig();
    configuration2.setAdminFactory(new AdminFactory());
    configuration2.setHealthFactory(mock(HealthFactory.class));
    configuration2.setLoggingFactory(new DefaultLoggingFactory());
    configuration2.setMetricsFactory(new MetricsFactory());
    configuration2.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator = new PublicApiUriGenerator(configuration2);
    Client client = mock(Client.class);
    LedgerService ledgerService =
        new LedgerService(client, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentRefundsService getPaymentRefundsService =
        new GetPaymentRefundsService(ledgerService, publicApiUriGenerator);

    GetPaymentRefundService getPaymentRefundService = mock(GetPaymentRefundService.class);
    when(getPaymentRefundService.getConnectorPaymentRefund(
            Mockito.<Account>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(null);

    PublicApiConfig configuration3 = new PublicApiConfig();
    configuration3.setAdminFactory(new AdminFactory());
    configuration3.setHealthFactory(mock(HealthFactory.class));
    configuration3.setLoggingFactory(new DefaultLoggingFactory());
    configuration3.setMetricsFactory(new MetricsFactory());
    configuration3.setServerFactory(new DefaultServerFactory());
    ConnectorService connectorService =
        new ConnectorService(mock(Client.class), new ConnectorUriGenerator(configuration3));

    PublicApiConfig configuration4 = new PublicApiConfig();
    configuration4.setAdminFactory(new AdminFactory());
    configuration4.setHealthFactory(mock(HealthFactory.class));
    configuration4.setLoggingFactory(new DefaultLoggingFactory());
    configuration4.setMetricsFactory(new MetricsFactory());
    configuration4.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator2 = new PublicApiUriGenerator(configuration4);
    Client client2 = mock(Client.class);
    ConnectorService connectorService2 =
        new ConnectorService(client2, new ConnectorUriGenerator(new PublicApiConfig()));
    Client client3 = mock(Client.class);
    LedgerService ledgerService2 =
        new LedgerService(client3, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator2, connectorService2, ledgerService2);

    AdminFactory admin2 = new AdminFactory();
    admin2.setHealthChecks(new HealthCheckConfiguration());
    admin2.setTasks(new TaskConfiguration());

    PublicApiConfig configuration5 = new PublicApiConfig();
    configuration5.setAdminFactory(admin2);
    configuration5.setHealthFactory(mock(HealthFactory.class));
    configuration5.setLoggingFactory(new DefaultLoggingFactory());
    configuration5.setMetricsFactory(new MetricsFactory());
    configuration5.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator3 = new PublicApiUriGenerator(new PublicApiConfig());
    ConnectorService connectorService3 = new ConnectorService(mock(Client.class), null);
    LedgerService ledgerService3 = new LedgerService(mock(Client.class), null);

    GetPaymentService getPaymentService2 =
        new GetPaymentService(publicApiUriGenerator3, connectorService3, ledgerService3);

    CreateRefundService createRefundService =
        new CreateRefundService(getPaymentService2, mock(Client.class), configuration5);

    PaymentRefundsResource paymentRefundsResource =
        new PaymentRefundsResource(
            configuration,
            getPaymentRefundsService,
            getPaymentRefundService,
            connectorService,
            getPaymentService,
            createRefundService);

    // Act
    RefundResponse actualRefundById =
        paymentRefundsResource.getRefundById(
            new Account("42", TokenPaymentType.CARD, "ABC123"), "42", "42", "connector-only");

    // Assert
    verify(getPaymentRefundService)
        .getConnectorPaymentRefund(isA(Account.class), eq("42"), eq("42"));
    assertNull(actualRefundById);
  }

  /**
   * Test {@link PaymentRefundsResource#getRefundById(Account, String, String, String)}.
   *
   * <ul>
   *   <li>Then calls {@link GetPaymentRefundService#getLedgerPaymentRefund(Account, String,
   *       String)}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentRefundsResource#getRefundById(Account, String, String,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RefundResponse PaymentRefundsResource.getRefundById(Account, String, String, String)"
  })
  public void testGetRefundById_thenCallsGetLedgerPaymentRefund() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();

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

    PublicApiConfig configuration2 = new PublicApiConfig();
    configuration2.setAdminFactory(new AdminFactory());
    configuration2.setHealthFactory(mock(HealthFactory.class));
    configuration2.setLoggingFactory(new DefaultLoggingFactory());
    configuration2.setMetricsFactory(new MetricsFactory());
    configuration2.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator = new PublicApiUriGenerator(configuration2);
    Client client = mock(Client.class);
    LedgerService ledgerService =
        new LedgerService(client, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentRefundsService getPaymentRefundsService =
        new GetPaymentRefundsService(ledgerService, publicApiUriGenerator);

    GetPaymentRefundService getPaymentRefundService = mock(GetPaymentRefundService.class);
    when(getPaymentRefundService.getLedgerPaymentRefund(
            Mockito.<Account>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(null);

    PublicApiConfig configuration3 = new PublicApiConfig();
    configuration3.setAdminFactory(new AdminFactory());
    configuration3.setHealthFactory(mock(HealthFactory.class));
    configuration3.setLoggingFactory(new DefaultLoggingFactory());
    configuration3.setMetricsFactory(new MetricsFactory());
    configuration3.setServerFactory(new DefaultServerFactory());
    ConnectorService connectorService =
        new ConnectorService(mock(Client.class), new ConnectorUriGenerator(configuration3));

    PublicApiConfig configuration4 = new PublicApiConfig();
    configuration4.setAdminFactory(new AdminFactory());
    configuration4.setHealthFactory(mock(HealthFactory.class));
    configuration4.setLoggingFactory(new DefaultLoggingFactory());
    configuration4.setMetricsFactory(new MetricsFactory());
    configuration4.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator2 = new PublicApiUriGenerator(configuration4);
    Client client2 = mock(Client.class);
    ConnectorService connectorService2 =
        new ConnectorService(client2, new ConnectorUriGenerator(new PublicApiConfig()));
    Client client3 = mock(Client.class);
    LedgerService ledgerService2 =
        new LedgerService(client3, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator2, connectorService2, ledgerService2);

    AdminFactory admin2 = new AdminFactory();
    admin2.setHealthChecks(new HealthCheckConfiguration());
    admin2.setTasks(new TaskConfiguration());

    PublicApiConfig configuration5 = new PublicApiConfig();
    configuration5.setAdminFactory(admin2);
    configuration5.setHealthFactory(mock(HealthFactory.class));
    configuration5.setLoggingFactory(new DefaultLoggingFactory());
    configuration5.setMetricsFactory(new MetricsFactory());
    configuration5.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator3 = new PublicApiUriGenerator(new PublicApiConfig());
    ConnectorService connectorService3 = new ConnectorService(mock(Client.class), null);
    LedgerService ledgerService3 = new LedgerService(mock(Client.class), null);

    GetPaymentService getPaymentService2 =
        new GetPaymentService(publicApiUriGenerator3, connectorService3, ledgerService3);

    CreateRefundService createRefundService =
        new CreateRefundService(getPaymentService2, mock(Client.class), configuration5);

    PaymentRefundsResource paymentRefundsResource =
        new PaymentRefundsResource(
            configuration,
            getPaymentRefundsService,
            getPaymentRefundService,
            connectorService,
            getPaymentService,
            createRefundService);

    // Act
    RefundResponse actualRefundById =
        paymentRefundsResource.getRefundById(
            new Account("42", TokenPaymentType.CARD, "ABC123"), "42", "42", "ledger-only");

    // Assert
    verify(getPaymentRefundService).getLedgerPaymentRefund(isA(Account.class), eq("42"), eq("42"));
    assertNull(actualRefundById);
  }

  /**
   * Test {@link PaymentRefundsResource#getRefundById(Account, String, String, String)}.
   *
   * <ul>
   *   <li>Then calls {@link GetPaymentRefundService#getPaymentRefund(Account, String, String)}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentRefundsResource#getRefundById(Account, String, String,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RefundResponse PaymentRefundsResource.getRefundById(Account, String, String, String)"
  })
  public void testGetRefundById_thenCallsGetPaymentRefund() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();

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

    PublicApiConfig configuration2 = new PublicApiConfig();
    configuration2.setAdminFactory(new AdminFactory());
    configuration2.setHealthFactory(mock(HealthFactory.class));
    configuration2.setLoggingFactory(new DefaultLoggingFactory());
    configuration2.setMetricsFactory(new MetricsFactory());
    configuration2.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator = new PublicApiUriGenerator(configuration2);
    Client client = mock(Client.class);
    LedgerService ledgerService =
        new LedgerService(client, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentRefundsService getPaymentRefundsService =
        new GetPaymentRefundsService(ledgerService, publicApiUriGenerator);

    GetPaymentRefundService getPaymentRefundService = mock(GetPaymentRefundService.class);
    when(getPaymentRefundService.getPaymentRefund(
            Mockito.<Account>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(null);

    PublicApiConfig configuration3 = new PublicApiConfig();
    configuration3.setAdminFactory(new AdminFactory());
    configuration3.setHealthFactory(mock(HealthFactory.class));
    configuration3.setLoggingFactory(new DefaultLoggingFactory());
    configuration3.setMetricsFactory(new MetricsFactory());
    configuration3.setServerFactory(new DefaultServerFactory());
    ConnectorService connectorService =
        new ConnectorService(mock(Client.class), new ConnectorUriGenerator(configuration3));

    PublicApiConfig configuration4 = new PublicApiConfig();
    configuration4.setAdminFactory(new AdminFactory());
    configuration4.setHealthFactory(mock(HealthFactory.class));
    configuration4.setLoggingFactory(new DefaultLoggingFactory());
    configuration4.setMetricsFactory(new MetricsFactory());
    configuration4.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator2 = new PublicApiUriGenerator(configuration4);
    Client client2 = mock(Client.class);
    ConnectorService connectorService2 =
        new ConnectorService(client2, new ConnectorUriGenerator(new PublicApiConfig()));
    Client client3 = mock(Client.class);
    LedgerService ledgerService2 =
        new LedgerService(client3, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator2, connectorService2, ledgerService2);

    AdminFactory admin2 = new AdminFactory();
    admin2.setHealthChecks(new HealthCheckConfiguration());
    admin2.setTasks(new TaskConfiguration());

    PublicApiConfig configuration5 = new PublicApiConfig();
    configuration5.setAdminFactory(admin2);
    configuration5.setHealthFactory(mock(HealthFactory.class));
    configuration5.setLoggingFactory(new DefaultLoggingFactory());
    configuration5.setMetricsFactory(new MetricsFactory());
    configuration5.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator3 = new PublicApiUriGenerator(new PublicApiConfig());
    ConnectorService connectorService3 = new ConnectorService(mock(Client.class), null);
    LedgerService ledgerService3 = new LedgerService(mock(Client.class), null);

    GetPaymentService getPaymentService2 =
        new GetPaymentService(publicApiUriGenerator3, connectorService3, ledgerService3);

    CreateRefundService createRefundService =
        new CreateRefundService(getPaymentService2, mock(Client.class), configuration5);

    PaymentRefundsResource paymentRefundsResource =
        new PaymentRefundsResource(
            configuration,
            getPaymentRefundsService,
            getPaymentRefundService,
            connectorService,
            getPaymentService,
            createRefundService);

    // Act
    RefundResponse actualRefundById =
        paymentRefundsResource.getRefundById(
            new Account("42", TokenPaymentType.CARD, "ABC123"), "42", "42", "Strategy Name");

    // Assert
    verify(getPaymentRefundService).getPaymentRefund(isA(Account.class), eq("42"), eq("42"));
    assertNull(actualRefundById);
  }

  /**
   * Test {@link PaymentRefundsResource#getRefundById(Account, String, String, String)}.
   *
   * <ul>
   *   <li>Then return Links Payment Method is {@code GET}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentRefundsResource#getRefundById(Account, String, String,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RefundResponse PaymentRefundsResource.getRefundById(Account, String, String, String)"
  })
  public void testGetRefundById_thenReturnLinksPaymentMethodIsGet() {
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

    PublicApiConfig configuration2 = new PublicApiConfig();
    configuration2.setAdminFactory(new AdminFactory());
    configuration2.setHealthFactory(mock(HealthFactory.class));
    configuration2.setLoggingFactory(new DefaultLoggingFactory());
    configuration2.setMetricsFactory(new MetricsFactory());
    configuration2.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator = new PublicApiUriGenerator(configuration2);
    Client client = mock(Client.class);
    LedgerService ledgerService =
        new LedgerService(client, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentRefundsService getPaymentRefundsService =
        new GetPaymentRefundsService(ledgerService, publicApiUriGenerator);

    ConnectorService connectorService = mock(ConnectorService.class);
    when(connectorService.getPaymentRefund(
            Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(new RefundFromConnector());

    PublicApiUriGenerator publicApiUriGenerator2 = mock(PublicApiUriGenerator.class);
    when(publicApiUriGenerator2.getPaymentURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator2.getRefundsURI(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    Client client2 = mock(Client.class);
    LedgerService ledgerService2 =
        new LedgerService(client2, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentRefundService getPaymentRefundService =
        new GetPaymentRefundService(connectorService, ledgerService2, publicApiUriGenerator2);

    PublicApiConfig configuration3 = new PublicApiConfig();
    configuration3.setAdminFactory(new AdminFactory());
    configuration3.setHealthFactory(mock(HealthFactory.class));
    configuration3.setLoggingFactory(new DefaultLoggingFactory());
    configuration3.setMetricsFactory(new MetricsFactory());
    configuration3.setServerFactory(new DefaultServerFactory());
    ConnectorService connectorService2 =
        new ConnectorService(mock(Client.class), new ConnectorUriGenerator(configuration3));

    PublicApiConfig configuration4 = new PublicApiConfig();
    configuration4.setAdminFactory(new AdminFactory());
    configuration4.setHealthFactory(mock(HealthFactory.class));
    configuration4.setLoggingFactory(new DefaultLoggingFactory());
    configuration4.setMetricsFactory(new MetricsFactory());
    configuration4.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator3 = new PublicApiUriGenerator(configuration4);
    Client client3 = mock(Client.class);
    ConnectorService connectorService3 =
        new ConnectorService(client3, new ConnectorUriGenerator(new PublicApiConfig()));
    Client client4 = mock(Client.class);
    LedgerService ledgerService3 =
        new LedgerService(client4, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator3, connectorService3, ledgerService3);

    AdminFactory admin2 = new AdminFactory();
    admin2.setHealthChecks(new HealthCheckConfiguration());
    admin2.setTasks(new TaskConfiguration());

    PublicApiConfig configuration5 = new PublicApiConfig();
    configuration5.setAdminFactory(admin2);
    configuration5.setHealthFactory(mock(HealthFactory.class));
    configuration5.setLoggingFactory(new DefaultLoggingFactory());
    configuration5.setMetricsFactory(new MetricsFactory());
    configuration5.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator4 = new PublicApiUriGenerator(new PublicApiConfig());
    ConnectorService connectorService4 = new ConnectorService(mock(Client.class), null);
    LedgerService ledgerService4 = new LedgerService(mock(Client.class), null);

    GetPaymentService getPaymentService2 =
        new GetPaymentService(publicApiUriGenerator4, connectorService4, ledgerService4);

    CreateRefundService createRefundService =
        new CreateRefundService(getPaymentService2, mock(Client.class), configuration5);

    PaymentRefundsResource paymentRefundsResource =
        new PaymentRefundsResource(
            configuration,
            getPaymentRefundsService,
            getPaymentRefundService,
            connectorService2,
            getPaymentService,
            createRefundService);

    // Act
    RefundResponse actualRefundById =
        paymentRefundsResource.getRefundById(
            new Account("42", TokenPaymentType.CARD, "ABC123"), "42", "42", "Strategy Name");

    // Assert
    verify(connectorService).getPaymentRefund("42", "42", "42");
    verify(publicApiUriGenerator2).getPaymentURI("42");
    verify(publicApiUriGenerator2).getRefundsURI("42", "42");
    RefundLinksForSearch links = actualRefundById.getLinks();
    Link payment = links.getPayment();
    assertEquals("GET", payment.getMethod());
    assertNull(actualRefundById.getAmount());
    assertNull(actualRefundById.getCreatedDate());
    assertNull(actualRefundById.getRefundId());
    assertNull(actualRefundById.getStatus());
    assertNull(actualRefundById.getSettlementSummary().getSettledDate());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        payment.getHref());
    assertEquals(payment, links.getSelf());
  }

  /**
   * Test {@link PaymentRefundsResource#getRefundById(Account, String, String, String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then calls {@link GetPaymentRefundService#getPaymentRefund(Account, String, String)}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentRefundsResource#getRefundById(Account, String, String,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RefundResponse PaymentRefundsResource.getRefundById(Account, String, String, String)"
  })
  public void testGetRefundById_whenEmptyString_thenCallsGetPaymentRefund() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();

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

    PublicApiConfig configuration2 = new PublicApiConfig();
    configuration2.setAdminFactory(new AdminFactory());
    configuration2.setHealthFactory(mock(HealthFactory.class));
    configuration2.setLoggingFactory(new DefaultLoggingFactory());
    configuration2.setMetricsFactory(new MetricsFactory());
    configuration2.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator = new PublicApiUriGenerator(configuration2);
    Client client = mock(Client.class);
    LedgerService ledgerService =
        new LedgerService(client, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentRefundsService getPaymentRefundsService =
        new GetPaymentRefundsService(ledgerService, publicApiUriGenerator);

    GetPaymentRefundService getPaymentRefundService = mock(GetPaymentRefundService.class);
    when(getPaymentRefundService.getPaymentRefund(
            Mockito.<Account>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(null);

    PublicApiConfig configuration3 = new PublicApiConfig();
    configuration3.setAdminFactory(new AdminFactory());
    configuration3.setHealthFactory(mock(HealthFactory.class));
    configuration3.setLoggingFactory(new DefaultLoggingFactory());
    configuration3.setMetricsFactory(new MetricsFactory());
    configuration3.setServerFactory(new DefaultServerFactory());
    ConnectorService connectorService =
        new ConnectorService(mock(Client.class), new ConnectorUriGenerator(configuration3));

    PublicApiConfig configuration4 = new PublicApiConfig();
    configuration4.setAdminFactory(new AdminFactory());
    configuration4.setHealthFactory(mock(HealthFactory.class));
    configuration4.setLoggingFactory(new DefaultLoggingFactory());
    configuration4.setMetricsFactory(new MetricsFactory());
    configuration4.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator2 = new PublicApiUriGenerator(configuration4);
    Client client2 = mock(Client.class);
    ConnectorService connectorService2 =
        new ConnectorService(client2, new ConnectorUriGenerator(new PublicApiConfig()));
    Client client3 = mock(Client.class);
    LedgerService ledgerService2 =
        new LedgerService(client3, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator2, connectorService2, ledgerService2);

    AdminFactory admin2 = new AdminFactory();
    admin2.setHealthChecks(new HealthCheckConfiguration());
    admin2.setTasks(new TaskConfiguration());

    PublicApiConfig configuration5 = new PublicApiConfig();
    configuration5.setAdminFactory(admin2);
    configuration5.setHealthFactory(mock(HealthFactory.class));
    configuration5.setLoggingFactory(new DefaultLoggingFactory());
    configuration5.setMetricsFactory(new MetricsFactory());
    configuration5.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator3 = new PublicApiUriGenerator(new PublicApiConfig());
    ConnectorService connectorService3 = new ConnectorService(mock(Client.class), null);
    LedgerService ledgerService3 = new LedgerService(mock(Client.class), null);

    GetPaymentService getPaymentService2 =
        new GetPaymentService(publicApiUriGenerator3, connectorService3, ledgerService3);

    CreateRefundService createRefundService =
        new CreateRefundService(getPaymentService2, mock(Client.class), configuration5);

    PaymentRefundsResource paymentRefundsResource =
        new PaymentRefundsResource(
            configuration,
            getPaymentRefundsService,
            getPaymentRefundService,
            connectorService,
            getPaymentService,
            createRefundService);

    // Act
    RefundResponse actualRefundById =
        paymentRefundsResource.getRefundById(
            new Account("42", TokenPaymentType.CARD, "ABC123"), "42", "42", "");

    // Assert
    verify(getPaymentRefundService).getPaymentRefund(isA(Account.class), eq("42"), eq("42"));
    assertNull(actualRefundById);
  }

  /**
   * Test {@link PaymentRefundsResource#submitRefund(Account, String, CreatePaymentRefundRequest)}.
   *
   * <ul>
   *   <li>Then StatusInfo return {@link Status}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentRefundsResource#submitRefund(Account, String,
   * CreatePaymentRefundRequest)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Response PaymentRefundsResource.submitRefund(Account, String, CreatePaymentRefundRequest)"
  })
  public void testSubmitRefund_thenStatusInfoReturnStatus() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();

    PaymentWithAllLinksBuilder withAuthorisationModeResult =
        new PaymentWithAllLinksBuilder()
            .withAgreementId("42")
            .withAgreementPaymentType(AgreementPaymentType.INSTALMENT)
            .withAmount(10L)
            .withAuthorisationMode(AuthorisationMode.WEB);

    PaymentWithAllLinksBuilder withAuthorisationSummaryResult =
        withAuthorisationModeResult.withAuthorisationSummary(new AuthorisationSummary());
    Address billingAddress = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");
    CardDetails cardDetails =
        new CardDetails(
            "42",
            "42",
            "Card Holder Name",
            "2020-03-01",
            billingAddress,
            "Card Brand",
            "Card Type",
            "Wallet Type");

    PaymentWithAllLinksBuilder withEmailResult =
        withAuthorisationSummaryResult
            .withCardDetails(cardDetails)
            .withChargeId("42")
            .withCorporateCardSurcharge(1L)
            .withCreatedDate("2020-03-01")
            .withDelayedCapture(true)
            .withDescription("The characteristics of someone or something")
            .withEmail("jane.doe@example.org");

    PaymentWithAllLinksBuilder withLanguageResult =
        withEmailResult
            .withExemption(new Exemption())
            .withFee(1L)
            .withLanguage(SupportedLanguage.ENGLISH);

    PaymentWithAllLinksBuilder withPaymentCaptureUriResult =
        withLanguageResult
            .withMetadata(new ExternalMetadata(new HashMap<>()))
            .withMoto(true)
            .withNetAmount(1L)
            .withPaymentAuthorisationUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentCancelUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentCaptureUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    PaymentWithAllLinksBuilder withReferenceResult =
        withPaymentCaptureUriResult
            .withPaymentConnectorResponseLinks(new ArrayList<>())
            .withPaymentEventsUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentProvider("Payment Provider")
            .withPaymentRefundsUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withProviderId("42")
            .withReference("Reference");

    PaymentWithAllLinksBuilder withSelfLinkResult =
        withReferenceResult
            .withRefundSummary(new RefundSummary("Status", 10L, 10L))
            .withReturnUrl("https://example.org/example")
            .withSelfLink(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    PaymentSettlementSummary settlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");

    PaymentWithAllLinksBuilder withSettlementSummaryResult =
        withSelfLinkResult.withSettlementSummary(settlementSummary);
    withSettlementSummaryResult
        .withState(new PaymentState("Status", true))
        .withTotalAmount(1L)
        .build();

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

    PublicApiConfig configuration2 = new PublicApiConfig();
    configuration2.setAdminFactory(new AdminFactory());
    configuration2.setHealthFactory(mock(HealthFactory.class));
    configuration2.setLoggingFactory(new DefaultLoggingFactory());
    configuration2.setMetricsFactory(new MetricsFactory());
    configuration2.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator = new PublicApiUriGenerator(configuration2);
    Client client = mock(Client.class);
    LedgerService ledgerService =
        new LedgerService(client, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentRefundsService getPaymentRefundsService =
        new GetPaymentRefundsService(ledgerService, publicApiUriGenerator);

    PublicApiConfig configuration3 = new PublicApiConfig();
    configuration3.setAdminFactory(new AdminFactory());
    configuration3.setHealthFactory(mock(HealthFactory.class));
    configuration3.setLoggingFactory(new DefaultLoggingFactory());
    configuration3.setMetricsFactory(new MetricsFactory());
    configuration3.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator2 = new PublicApiUriGenerator(configuration3);
    Client client2 = mock(Client.class);
    ConnectorService connectorService =
        new ConnectorService(client2, new ConnectorUriGenerator(new PublicApiConfig()));
    Client client3 = mock(Client.class);
    LedgerService ledgerService2 =
        new LedgerService(client3, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentRefundService getPaymentRefundService =
        new GetPaymentRefundService(connectorService, ledgerService2, publicApiUriGenerator2);

    PublicApiConfig configuration4 = new PublicApiConfig();
    configuration4.setAdminFactory(new AdminFactory());
    configuration4.setHealthFactory(mock(HealthFactory.class));
    configuration4.setLoggingFactory(new DefaultLoggingFactory());
    configuration4.setMetricsFactory(new MetricsFactory());
    configuration4.setServerFactory(new DefaultServerFactory());
    ConnectorService connectorService2 =
        new ConnectorService(mock(Client.class), new ConnectorUriGenerator(configuration4));

    PublicApiConfig configuration5 = new PublicApiConfig();
    configuration5.setAdminFactory(new AdminFactory());
    configuration5.setHealthFactory(mock(HealthFactory.class));
    configuration5.setLoggingFactory(new DefaultLoggingFactory());
    configuration5.setMetricsFactory(new MetricsFactory());
    configuration5.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator3 = new PublicApiUriGenerator(configuration5);
    Client client4 = mock(Client.class);
    ConnectorService connectorService3 =
        new ConnectorService(client4, new ConnectorUriGenerator(new PublicApiConfig()));
    Client client5 = mock(Client.class);
    LedgerService ledgerService3 =
        new LedgerService(client5, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator3, connectorService3, ledgerService3);

    CreateRefundService createRefundService = mock(CreateRefundService.class);
    when(createRefundService.createRefund(
            Mockito.<Account>any(),
            Mockito.<String>any(),
            Mockito.<CreatePaymentRefundRequest>any()))
        .thenReturn(null);

    PaymentRefundsResource paymentRefundsResource =
        new PaymentRefundsResource(
            configuration,
            getPaymentRefundsService,
            getPaymentRefundService,
            connectorService2,
            getPaymentService,
            createRefundService);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    // Act
    Response actualSubmitRefundResult =
        paymentRefundsResource.submitRefund(account, "42", new CreatePaymentRefundRequest());

    // Assert
    verify(createRefundService)
        .createRefund(isA(Account.class), eq("42"), isA(CreatePaymentRefundRequest.class));
    StatusType statusInfo = actualSubmitRefundResult.getStatusInfo();
    assertTrue(statusInfo instanceof Status);
    assertTrue(actualSubmitRefundResult instanceof OutboundJaxrsResponse);
    assertNull(actualSubmitRefundResult.getEntityTag());
    assertNull(actualSubmitRefundResult.getMediaType());
    assertNull(actualSubmitRefundResult.getEntity());
    assertNull(actualSubmitRefundResult.getLocation());
    assertNull(actualSubmitRefundResult.getDate());
    assertNull(actualSubmitRefundResult.getLastModified());
    assertNull(actualSubmitRefundResult.getLanguage());
    assertEquals(-1, actualSubmitRefundResult.getLength());
    assertEquals(202, actualSubmitRefundResult.getStatus());
    assertEquals(Status.ACCEPTED, statusInfo);
    assertTrue(actualSubmitRefundResult.getCookies().isEmpty());
    MultivaluedMap<String, Object> headers = actualSubmitRefundResult.getHeaders();
    assertTrue(headers.isEmpty());
    assertTrue(actualSubmitRefundResult.getStringHeaders().isEmpty());
    Set<String> allowedMethods = actualSubmitRefundResult.getAllowedMethods();
    assertTrue(allowedMethods.isEmpty());
    assertSame(allowedMethods, actualSubmitRefundResult.getLinks());
    assertSame(headers, actualSubmitRefundResult.getMetadata());
  }
}
