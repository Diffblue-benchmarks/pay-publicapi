package uk.gov.pay.api.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.core.UriInfo;
import java.net.URI;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import org.glassfish.jersey.internal.MapPropertiesDelegate;
import org.glassfish.jersey.message.internal.OutboundJaxrsResponse;
import org.glassfish.jersey.message.internal.OutboundMessageContext;
import org.glassfish.jersey.server.ContainerRequest;
import org.glassfish.jersey.server.internal.routing.UriRoutingContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uk.gov.pay.api.app.config.PublicApiConfig;
import uk.gov.pay.api.auth.Account;
import uk.gov.pay.api.ledger.service.LedgerUriGenerator;
import uk.gov.pay.api.model.Address;
import uk.gov.pay.api.model.AuthorisationSummary;
import uk.gov.pay.api.model.CardDetails;
import uk.gov.pay.api.model.CreateCardPaymentRequest;
import uk.gov.pay.api.model.CreateCardPaymentRequestBuilder;
import uk.gov.pay.api.model.CreatedPaymentWithAllLinks;
import uk.gov.pay.api.model.CreatedPaymentWithAllLinks.WhenCreated;
import uk.gov.pay.api.model.Exemption;
import uk.gov.pay.api.model.PaymentSettlementSummary;
import uk.gov.pay.api.model.PaymentState;
import uk.gov.pay.api.model.RefundSummary;
import uk.gov.pay.api.model.TokenPaymentType;
import uk.gov.pay.api.model.links.PaymentWithAllLinks;
import uk.gov.pay.api.model.links.PaymentWithAllLinks.PaymentWithAllLinksBuilder;
import uk.gov.pay.api.model.search.PaginationDecorator;
import uk.gov.pay.api.service.CancelPaymentService;
import uk.gov.pay.api.service.CapturePaymentService;
import uk.gov.pay.api.service.ConnectorService;
import uk.gov.pay.api.service.ConnectorUriGenerator;
import uk.gov.pay.api.service.CreatePaymentService;
import uk.gov.pay.api.service.GetPaymentEventsService;
import uk.gov.pay.api.service.GetPaymentService;
import uk.gov.pay.api.service.LedgerService;
import uk.gov.pay.api.service.PaymentSearchParams;
import uk.gov.pay.api.service.PaymentSearchService;
import uk.gov.pay.api.service.PublicApiUriGenerator;
import uk.gov.service.payments.commons.model.AgreementPaymentType;
import uk.gov.service.payments.commons.model.AuthorisationMode;
import uk.gov.service.payments.commons.model.SupportedLanguage;
import uk.gov.service.payments.commons.model.charge.ExternalMetadata;

class PaymentsResourceDiffblueTest {
  /**
   * Test {@link PaymentsResource#searchPayments(Account, String, String, String, String, String,
   * String, String, String, String, String, String, String, String, String, UriInfo)}.
   *
   * <ul>
   *   <li>When {@code Card Brand}.
   *   <li>Then return {@link OutboundJaxrsResponse}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentsResource#searchPayments(Account, String, String, String,
   * String, String, String, String, String, String, String, String, String, String, String,
   * UriInfo)}
   */
  @Test
  @DisplayName(
      "Test searchPayments(Account, String, String, String, String, String, String, String, String, String, String, String, String, String, String, UriInfo); when 'Card Brand'; then return OutboundJaxrsResponse")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Response PaymentsResource.searchPayments(Account, String, String, String, String, String, String, String, String, String, String, String, String, String, String, UriInfo)"
  })
  void testSearchPayments_whenCardBrand_thenReturnOutboundJaxrsResponse() {
    // Arrange
    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator = new PublicApiUriGenerator(configuration);

    PublicApiConfig configuration2 = new PublicApiConfig();
    configuration2.setAdminFactory(new AdminFactory());
    configuration2.setHealthFactory(mock(HealthFactory.class));
    configuration2.setLoggingFactory(new DefaultLoggingFactory());
    configuration2.setMetricsFactory(new MetricsFactory());
    configuration2.setServerFactory(new DefaultServerFactory());
    CreatePaymentService createPaymentService =
        new CreatePaymentService(
            mock(Client.class), publicApiUriGenerator, new ConnectorUriGenerator(configuration2));

    PaymentSearchService paymentSearchService = mock(PaymentSearchService.class);
    OutboundJaxrsResponse outboundJaxrsResponse =
        new OutboundJaxrsResponse(null, new OutboundMessageContext());
    when(paymentSearchService.searchLedgerPayments(
            Mockito.<Account>any(), Mockito.<PaymentSearchParams>any()))
        .thenReturn(outboundJaxrsResponse);

    AdminFactory admin = new AdminFactory();
    admin.setHealthChecks(new HealthCheckConfiguration());
    admin.setTasks(new TaskConfiguration());

    PublicApiConfig configuration3 = new PublicApiConfig();
    configuration3.setAdminFactory(admin);
    configuration3.setHealthFactory(mock(HealthFactory.class));
    configuration3.setLoggingFactory(new DefaultLoggingFactory());
    configuration3.setMetricsFactory(new MetricsFactory());
    configuration3.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator2 = new PublicApiUriGenerator(configuration3);

    PublicApiConfig configuration4 = new PublicApiConfig();
    configuration4.setAdminFactory(new AdminFactory());
    configuration4.setHealthFactory(mock(HealthFactory.class));
    configuration4.setLoggingFactory(new DefaultLoggingFactory());
    configuration4.setMetricsFactory(new MetricsFactory());
    configuration4.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator3 = new PublicApiUriGenerator(configuration4);
    Client client = mock(Client.class);
    ConnectorService connectorService =
        new ConnectorService(client, new ConnectorUriGenerator(new PublicApiConfig()));
    Client client2 = mock(Client.class);
    LedgerService ledgerService =
        new LedgerService(client2, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator3, connectorService, ledgerService);

    PublicApiConfig configuration5 = new PublicApiConfig();
    configuration5.setAdminFactory(new AdminFactory());
    configuration5.setHealthFactory(mock(HealthFactory.class));
    configuration5.setLoggingFactory(new DefaultLoggingFactory());
    configuration5.setMetricsFactory(new MetricsFactory());
    configuration5.setServerFactory(new DefaultServerFactory());
    CapturePaymentService capturePaymentService =
        new CapturePaymentService(mock(Client.class), new ConnectorUriGenerator(configuration5));

    PublicApiConfig configuration6 = new PublicApiConfig();
    configuration6.setAdminFactory(new AdminFactory());
    configuration6.setHealthFactory(mock(HealthFactory.class));
    configuration6.setLoggingFactory(new DefaultLoggingFactory());
    configuration6.setMetricsFactory(new MetricsFactory());
    configuration6.setServerFactory(new DefaultServerFactory());
    CancelPaymentService cancelPaymentService =
        new CancelPaymentService(mock(Client.class), new ConnectorUriGenerator(configuration6));

    PublicApiConfig configuration7 = new PublicApiConfig();
    configuration7.setAdminFactory(new AdminFactory());
    configuration7.setHealthFactory(mock(HealthFactory.class));
    configuration7.setLoggingFactory(new DefaultLoggingFactory());
    configuration7.setMetricsFactory(new MetricsFactory());
    configuration7.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator4 = new PublicApiUriGenerator(configuration7);
    Client client3 = mock(Client.class);
    ConnectorService connectorService2 =
        new ConnectorService(client3, new ConnectorUriGenerator(new PublicApiConfig()));
    Client client4 = mock(Client.class);
    LedgerService ledgerService2 =
        new LedgerService(client4, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentEventsService getPaymentEventsService =
        new GetPaymentEventsService(publicApiUriGenerator4, connectorService2, ledgerService2);

    PaymentsResource paymentsResource =
        new PaymentsResource(
            createPaymentService,
            paymentSearchService,
            publicApiUriGenerator2,
            getPaymentService,
            capturePaymentService,
            cancelPaymentService,
            getPaymentEventsService);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");
    URI baseUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    URI requestUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    SecurityContext securityContext = mock(SecurityContext.class);

    ContainerRequest requestContext =
        new ContainerRequest(
            baseUri,
            requestUri,
            "https://example.org/example",
            securityContext,
            new MapPropertiesDelegate());

    // Act
    Response actualSearchPaymentsResult =
        paymentsResource.searchPayments(
            account,
            "Reference",
            "jane.doe@example.org",
            "MD",
            "Card Brand",
            "2020-03-01",
            "2020-03-01",
            "42",
            "Display Size",
            "Card Holder Name",
            "42",
            "42",
            "2020-03-01",
            "2020-03-01",
            "42",
            new UriRoutingContext(requestContext));

    // Assert
    verify(paymentSearchService)
        .searchLedgerPayments(isA(Account.class), isA(PaymentSearchParams.class));
    assertTrue(actualSearchPaymentsResult instanceof OutboundJaxrsResponse);
  }

  /**
   * Test {@link PaymentsResource#searchPayments(Account, String, String, String, String, String,
   * String, String, String, String, String, String, String, String, String, UriInfo)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return {@link OutboundJaxrsResponse}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentsResource#searchPayments(Account, String, String, String,
   * String, String, String, String, String, String, String, String, String, String, String,
   * UriInfo)}
   */
  @Test
  @DisplayName(
      "Test searchPayments(Account, String, String, String, String, String, String, String, String, String, String, String, String, String, String, UriInfo); when empty string; then return OutboundJaxrsResponse")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Response PaymentsResource.searchPayments(Account, String, String, String, String, String, String, String, String, String, String, String, String, String, String, UriInfo)"
  })
  void testSearchPayments_whenEmptyString_thenReturnOutboundJaxrsResponse() {
    // Arrange
    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator = new PublicApiUriGenerator(configuration);

    PublicApiConfig configuration2 = new PublicApiConfig();
    configuration2.setAdminFactory(new AdminFactory());
    configuration2.setHealthFactory(mock(HealthFactory.class));
    configuration2.setLoggingFactory(new DefaultLoggingFactory());
    configuration2.setMetricsFactory(new MetricsFactory());
    configuration2.setServerFactory(new DefaultServerFactory());
    CreatePaymentService createPaymentService =
        new CreatePaymentService(
            mock(Client.class), publicApiUriGenerator, new ConnectorUriGenerator(configuration2));

    PaymentSearchService paymentSearchService = mock(PaymentSearchService.class);
    OutboundJaxrsResponse outboundJaxrsResponse =
        new OutboundJaxrsResponse(null, new OutboundMessageContext());
    when(paymentSearchService.searchLedgerPayments(
            Mockito.<Account>any(), Mockito.<PaymentSearchParams>any()))
        .thenReturn(outboundJaxrsResponse);

    AdminFactory admin = new AdminFactory();
    admin.setHealthChecks(new HealthCheckConfiguration());
    admin.setTasks(new TaskConfiguration());

    PublicApiConfig configuration3 = new PublicApiConfig();
    configuration3.setAdminFactory(admin);
    configuration3.setHealthFactory(mock(HealthFactory.class));
    configuration3.setLoggingFactory(new DefaultLoggingFactory());
    configuration3.setMetricsFactory(new MetricsFactory());
    configuration3.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator2 = new PublicApiUriGenerator(configuration3);

    PublicApiConfig configuration4 = new PublicApiConfig();
    configuration4.setAdminFactory(new AdminFactory());
    configuration4.setHealthFactory(mock(HealthFactory.class));
    configuration4.setLoggingFactory(new DefaultLoggingFactory());
    configuration4.setMetricsFactory(new MetricsFactory());
    configuration4.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator3 = new PublicApiUriGenerator(configuration4);
    Client client = mock(Client.class);
    ConnectorService connectorService =
        new ConnectorService(client, new ConnectorUriGenerator(new PublicApiConfig()));
    Client client2 = mock(Client.class);
    LedgerService ledgerService =
        new LedgerService(client2, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator3, connectorService, ledgerService);

    PublicApiConfig configuration5 = new PublicApiConfig();
    configuration5.setAdminFactory(new AdminFactory());
    configuration5.setHealthFactory(mock(HealthFactory.class));
    configuration5.setLoggingFactory(new DefaultLoggingFactory());
    configuration5.setMetricsFactory(new MetricsFactory());
    configuration5.setServerFactory(new DefaultServerFactory());
    CapturePaymentService capturePaymentService =
        new CapturePaymentService(mock(Client.class), new ConnectorUriGenerator(configuration5));

    PublicApiConfig configuration6 = new PublicApiConfig();
    configuration6.setAdminFactory(new AdminFactory());
    configuration6.setHealthFactory(mock(HealthFactory.class));
    configuration6.setLoggingFactory(new DefaultLoggingFactory());
    configuration6.setMetricsFactory(new MetricsFactory());
    configuration6.setServerFactory(new DefaultServerFactory());
    CancelPaymentService cancelPaymentService =
        new CancelPaymentService(mock(Client.class), new ConnectorUriGenerator(configuration6));

    PublicApiConfig configuration7 = new PublicApiConfig();
    configuration7.setAdminFactory(new AdminFactory());
    configuration7.setHealthFactory(mock(HealthFactory.class));
    configuration7.setLoggingFactory(new DefaultLoggingFactory());
    configuration7.setMetricsFactory(new MetricsFactory());
    configuration7.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator4 = new PublicApiUriGenerator(configuration7);
    Client client3 = mock(Client.class);
    ConnectorService connectorService2 =
        new ConnectorService(client3, new ConnectorUriGenerator(new PublicApiConfig()));
    Client client4 = mock(Client.class);
    LedgerService ledgerService2 =
        new LedgerService(client4, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentEventsService getPaymentEventsService =
        new GetPaymentEventsService(publicApiUriGenerator4, connectorService2, ledgerService2);

    PaymentsResource paymentsResource =
        new PaymentsResource(
            createPaymentService,
            paymentSearchService,
            publicApiUriGenerator2,
            getPaymentService,
            capturePaymentService,
            cancelPaymentService,
            getPaymentEventsService);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");
    URI baseUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    URI requestUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    SecurityContext securityContext = mock(SecurityContext.class);

    ContainerRequest requestContext =
        new ContainerRequest(
            baseUri,
            requestUri,
            "https://example.org/example",
            securityContext,
            new MapPropertiesDelegate());

    // Act
    Response actualSearchPaymentsResult =
        paymentsResource.searchPayments(
            account,
            "Reference",
            "jane.doe@example.org",
            "MD",
            "",
            "2020-03-01",
            "2020-03-01",
            "42",
            "Display Size",
            "Card Holder Name",
            "42",
            "42",
            "2020-03-01",
            "2020-03-01",
            "42",
            new UriRoutingContext(requestContext));

    // Assert
    verify(paymentSearchService)
        .searchLedgerPayments(isA(Account.class), isA(PaymentSearchParams.class));
    assertTrue(actualSearchPaymentsResult instanceof OutboundJaxrsResponse);
  }

  /**
   * Test {@link PaymentsResource#searchPayments(Account, String, String, String, String, String,
   * String, String, String, String, String, String, String, String, String, UriInfo)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link OutboundJaxrsResponse}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentsResource#searchPayments(Account, String, String, String,
   * String, String, String, String, String, String, String, String, String, String, String,
   * UriInfo)}
   */
  @Test
  @DisplayName(
      "Test searchPayments(Account, String, String, String, String, String, String, String, String, String, String, String, String, String, String, UriInfo); when 'null'; then return OutboundJaxrsResponse")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Response PaymentsResource.searchPayments(Account, String, String, String, String, String, String, String, String, String, String, String, String, String, String, UriInfo)"
  })
  void testSearchPayments_whenNull_thenReturnOutboundJaxrsResponse() {
    // Arrange
    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator = new PublicApiUriGenerator(configuration);

    PublicApiConfig configuration2 = new PublicApiConfig();
    configuration2.setAdminFactory(new AdminFactory());
    configuration2.setHealthFactory(mock(HealthFactory.class));
    configuration2.setLoggingFactory(new DefaultLoggingFactory());
    configuration2.setMetricsFactory(new MetricsFactory());
    configuration2.setServerFactory(new DefaultServerFactory());
    CreatePaymentService createPaymentService =
        new CreatePaymentService(
            mock(Client.class), publicApiUriGenerator, new ConnectorUriGenerator(configuration2));

    PaymentSearchService paymentSearchService = mock(PaymentSearchService.class);
    OutboundJaxrsResponse outboundJaxrsResponse =
        new OutboundJaxrsResponse(null, new OutboundMessageContext());
    when(paymentSearchService.searchLedgerPayments(
            Mockito.<Account>any(), Mockito.<PaymentSearchParams>any()))
        .thenReturn(outboundJaxrsResponse);

    AdminFactory admin = new AdminFactory();
    admin.setHealthChecks(new HealthCheckConfiguration());
    admin.setTasks(new TaskConfiguration());

    PublicApiConfig configuration3 = new PublicApiConfig();
    configuration3.setAdminFactory(admin);
    configuration3.setHealthFactory(mock(HealthFactory.class));
    configuration3.setLoggingFactory(new DefaultLoggingFactory());
    configuration3.setMetricsFactory(new MetricsFactory());
    configuration3.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator2 = new PublicApiUriGenerator(configuration3);

    PublicApiConfig configuration4 = new PublicApiConfig();
    configuration4.setAdminFactory(new AdminFactory());
    configuration4.setHealthFactory(mock(HealthFactory.class));
    configuration4.setLoggingFactory(new DefaultLoggingFactory());
    configuration4.setMetricsFactory(new MetricsFactory());
    configuration4.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator3 = new PublicApiUriGenerator(configuration4);
    Client client = mock(Client.class);
    ConnectorService connectorService =
        new ConnectorService(client, new ConnectorUriGenerator(new PublicApiConfig()));
    Client client2 = mock(Client.class);
    LedgerService ledgerService =
        new LedgerService(client2, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator3, connectorService, ledgerService);

    PublicApiConfig configuration5 = new PublicApiConfig();
    configuration5.setAdminFactory(new AdminFactory());
    configuration5.setHealthFactory(mock(HealthFactory.class));
    configuration5.setLoggingFactory(new DefaultLoggingFactory());
    configuration5.setMetricsFactory(new MetricsFactory());
    configuration5.setServerFactory(new DefaultServerFactory());
    CapturePaymentService capturePaymentService =
        new CapturePaymentService(mock(Client.class), new ConnectorUriGenerator(configuration5));

    PublicApiConfig configuration6 = new PublicApiConfig();
    configuration6.setAdminFactory(new AdminFactory());
    configuration6.setHealthFactory(mock(HealthFactory.class));
    configuration6.setLoggingFactory(new DefaultLoggingFactory());
    configuration6.setMetricsFactory(new MetricsFactory());
    configuration6.setServerFactory(new DefaultServerFactory());
    CancelPaymentService cancelPaymentService =
        new CancelPaymentService(mock(Client.class), new ConnectorUriGenerator(configuration6));

    PublicApiConfig configuration7 = new PublicApiConfig();
    configuration7.setAdminFactory(new AdminFactory());
    configuration7.setHealthFactory(mock(HealthFactory.class));
    configuration7.setLoggingFactory(new DefaultLoggingFactory());
    configuration7.setMetricsFactory(new MetricsFactory());
    configuration7.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator4 = new PublicApiUriGenerator(configuration7);
    Client client3 = mock(Client.class);
    ConnectorService connectorService2 =
        new ConnectorService(client3, new ConnectorUriGenerator(new PublicApiConfig()));
    Client client4 = mock(Client.class);
    LedgerService ledgerService2 =
        new LedgerService(client4, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentEventsService getPaymentEventsService =
        new GetPaymentEventsService(publicApiUriGenerator4, connectorService2, ledgerService2);

    PaymentsResource paymentsResource =
        new PaymentsResource(
            createPaymentService,
            paymentSearchService,
            publicApiUriGenerator2,
            getPaymentService,
            capturePaymentService,
            cancelPaymentService,
            getPaymentEventsService);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");
    URI baseUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    URI requestUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    SecurityContext securityContext = mock(SecurityContext.class);

    ContainerRequest requestContext =
        new ContainerRequest(
            baseUri,
            requestUri,
            "https://example.org/example",
            securityContext,
            new MapPropertiesDelegate());

    // Act
    Response actualSearchPaymentsResult =
        paymentsResource.searchPayments(
            account,
            "Reference",
            "jane.doe@example.org",
            "MD",
            null,
            "2020-03-01",
            "2020-03-01",
            "42",
            "Display Size",
            "Card Holder Name",
            "42",
            "42",
            "2020-03-01",
            "2020-03-01",
            "42",
            new UriRoutingContext(requestContext));

    // Assert
    verify(paymentSearchService)
        .searchLedgerPayments(isA(Account.class), isA(PaymentSearchParams.class));
    assertTrue(actualSearchPaymentsResult instanceof OutboundJaxrsResponse);
  }

  /**
   * Test {@link PaymentsResource#searchPayments(Account, String, String, String, String, String,
   * String, String, String, String, String, String, String, String, String, UriInfo)}.
   *
   * <ul>
   *   <li>When space.
   *   <li>Then return {@link OutboundJaxrsResponse}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentsResource#searchPayments(Account, String, String, String,
   * String, String, String, String, String, String, String, String, String, String, String,
   * UriInfo)}
   */
  @Test
  @DisplayName(
      "Test searchPayments(Account, String, String, String, String, String, String, String, String, String, String, String, String, String, String, UriInfo); when space; then return OutboundJaxrsResponse")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Response PaymentsResource.searchPayments(Account, String, String, String, String, String, String, String, String, String, String, String, String, String, String, UriInfo)"
  })
  void testSearchPayments_whenSpace_thenReturnOutboundJaxrsResponse() {
    // Arrange
    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator = new PublicApiUriGenerator(configuration);

    PublicApiConfig configuration2 = new PublicApiConfig();
    configuration2.setAdminFactory(new AdminFactory());
    configuration2.setHealthFactory(mock(HealthFactory.class));
    configuration2.setLoggingFactory(new DefaultLoggingFactory());
    configuration2.setMetricsFactory(new MetricsFactory());
    configuration2.setServerFactory(new DefaultServerFactory());
    CreatePaymentService createPaymentService =
        new CreatePaymentService(
            mock(Client.class), publicApiUriGenerator, new ConnectorUriGenerator(configuration2));

    PaymentSearchService paymentSearchService = mock(PaymentSearchService.class);
    OutboundJaxrsResponse outboundJaxrsResponse =
        new OutboundJaxrsResponse(null, new OutboundMessageContext());
    when(paymentSearchService.searchLedgerPayments(
            Mockito.<Account>any(), Mockito.<PaymentSearchParams>any()))
        .thenReturn(outboundJaxrsResponse);

    AdminFactory admin = new AdminFactory();
    admin.setHealthChecks(new HealthCheckConfiguration());
    admin.setTasks(new TaskConfiguration());

    PublicApiConfig configuration3 = new PublicApiConfig();
    configuration3.setAdminFactory(admin);
    configuration3.setHealthFactory(mock(HealthFactory.class));
    configuration3.setLoggingFactory(new DefaultLoggingFactory());
    configuration3.setMetricsFactory(new MetricsFactory());
    configuration3.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator2 = new PublicApiUriGenerator(configuration3);

    PublicApiConfig configuration4 = new PublicApiConfig();
    configuration4.setAdminFactory(new AdminFactory());
    configuration4.setHealthFactory(mock(HealthFactory.class));
    configuration4.setLoggingFactory(new DefaultLoggingFactory());
    configuration4.setMetricsFactory(new MetricsFactory());
    configuration4.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator3 = new PublicApiUriGenerator(configuration4);
    Client client = mock(Client.class);
    ConnectorService connectorService =
        new ConnectorService(client, new ConnectorUriGenerator(new PublicApiConfig()));
    Client client2 = mock(Client.class);
    LedgerService ledgerService =
        new LedgerService(client2, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator3, connectorService, ledgerService);

    PublicApiConfig configuration5 = new PublicApiConfig();
    configuration5.setAdminFactory(new AdminFactory());
    configuration5.setHealthFactory(mock(HealthFactory.class));
    configuration5.setLoggingFactory(new DefaultLoggingFactory());
    configuration5.setMetricsFactory(new MetricsFactory());
    configuration5.setServerFactory(new DefaultServerFactory());
    CapturePaymentService capturePaymentService =
        new CapturePaymentService(mock(Client.class), new ConnectorUriGenerator(configuration5));

    PublicApiConfig configuration6 = new PublicApiConfig();
    configuration6.setAdminFactory(new AdminFactory());
    configuration6.setHealthFactory(mock(HealthFactory.class));
    configuration6.setLoggingFactory(new DefaultLoggingFactory());
    configuration6.setMetricsFactory(new MetricsFactory());
    configuration6.setServerFactory(new DefaultServerFactory());
    CancelPaymentService cancelPaymentService =
        new CancelPaymentService(mock(Client.class), new ConnectorUriGenerator(configuration6));

    PublicApiConfig configuration7 = new PublicApiConfig();
    configuration7.setAdminFactory(new AdminFactory());
    configuration7.setHealthFactory(mock(HealthFactory.class));
    configuration7.setLoggingFactory(new DefaultLoggingFactory());
    configuration7.setMetricsFactory(new MetricsFactory());
    configuration7.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator4 = new PublicApiUriGenerator(configuration7);
    Client client3 = mock(Client.class);
    ConnectorService connectorService2 =
        new ConnectorService(client3, new ConnectorUriGenerator(new PublicApiConfig()));
    Client client4 = mock(Client.class);
    LedgerService ledgerService2 =
        new LedgerService(client4, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentEventsService getPaymentEventsService =
        new GetPaymentEventsService(publicApiUriGenerator4, connectorService2, ledgerService2);

    PaymentsResource paymentsResource =
        new PaymentsResource(
            createPaymentService,
            paymentSearchService,
            publicApiUriGenerator2,
            getPaymentService,
            capturePaymentService,
            cancelPaymentService,
            getPaymentEventsService);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");
    URI baseUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    URI requestUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    SecurityContext securityContext = mock(SecurityContext.class);

    ContainerRequest requestContext =
        new ContainerRequest(
            baseUri,
            requestUri,
            "https://example.org/example",
            securityContext,
            new MapPropertiesDelegate());

    // Act
    Response actualSearchPaymentsResult =
        paymentsResource.searchPayments(
            account,
            "Reference",
            "jane.doe@example.org",
            "MD",
            " ",
            "2020-03-01",
            "2020-03-01",
            "42",
            "Display Size",
            "Card Holder Name",
            "42",
            "42",
            "2020-03-01",
            "2020-03-01",
            "42",
            new UriRoutingContext(requestContext));

    // Assert
    verify(paymentSearchService)
        .searchLedgerPayments(isA(Account.class), isA(PaymentSearchParams.class));
    assertTrue(actualSearchPaymentsResult instanceof OutboundJaxrsResponse);
  }

  /**
   * Test {@link PaymentsResource#createNewPayment(Account, CreateCardPaymentRequest, String)}.
   *
   * <ul>
   *   <li>Then Entity return {@link PaymentWithAllLinks}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentsResource#createNewPayment(Account,
   * CreateCardPaymentRequest, String)}
   */
  @Test
  @DisplayName(
      "Test createNewPayment(Account, CreateCardPaymentRequest, String); then Entity return PaymentWithAllLinks")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Response PaymentsResource.createNewPayment(Account, CreateCardPaymentRequest, String)"
  })
  void testCreateNewPayment_thenEntityReturnPaymentWithAllLinks() {
    // Arrange
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

    CreatedPaymentWithAllLinks createdPaymentWithAllLinks = mock(CreatedPaymentWithAllLinks.class);
    when(createdPaymentWithAllLinks.getWhenCreated()).thenReturn(WhenCreated.BRAND_NEW);

    PaymentWithAllLinksBuilder withAuthorisationModeResult2 =
        new PaymentWithAllLinksBuilder()
            .withAgreementId("42")
            .withAgreementPaymentType(AgreementPaymentType.INSTALMENT)
            .withAmount(10L)
            .withAuthorisationMode(AuthorisationMode.WEB);

    PaymentWithAllLinksBuilder withAuthorisationSummaryResult2 =
        withAuthorisationModeResult2.withAuthorisationSummary(new AuthorisationSummary());
    Address billingAddress2 = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");
    CardDetails cardDetails2 =
        new CardDetails(
            "42",
            "42",
            "Card Holder Name",
            "2020-03-01",
            billingAddress2,
            "Card Brand",
            "Card Type",
            "Wallet Type");

    PaymentWithAllLinksBuilder withEmailResult2 =
        withAuthorisationSummaryResult2
            .withCardDetails(cardDetails2)
            .withChargeId("42")
            .withCorporateCardSurcharge(1L)
            .withCreatedDate("2020-03-01")
            .withDelayedCapture(true)
            .withDescription("The characteristics of someone or something")
            .withEmail("jane.doe@example.org");

    PaymentWithAllLinksBuilder withLanguageResult2 =
        withEmailResult2
            .withExemption(new Exemption())
            .withFee(1L)
            .withLanguage(SupportedLanguage.ENGLISH);

    PaymentWithAllLinksBuilder withPaymentCaptureUriResult2 =
        withLanguageResult2
            .withMetadata(new ExternalMetadata(new HashMap<>()))
            .withMoto(true)
            .withNetAmount(1L)
            .withPaymentAuthorisationUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentCancelUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentCaptureUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    PaymentWithAllLinksBuilder withReferenceResult2 =
        withPaymentCaptureUriResult2
            .withPaymentConnectorResponseLinks(new ArrayList<>())
            .withPaymentEventsUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentProvider("Payment Provider")
            .withPaymentRefundsUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withProviderId("42")
            .withReference("Reference");

    PaymentWithAllLinksBuilder withSelfLinkResult2 =
        withReferenceResult2
            .withRefundSummary(new RefundSummary("Status", 10L, 10L))
            .withReturnUrl("https://example.org/example")
            .withSelfLink(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    PaymentSettlementSummary settlementSummary2 =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");

    PaymentWithAllLinksBuilder withSettlementSummaryResult2 =
        withSelfLinkResult2.withSettlementSummary(settlementSummary2);
    when(createdPaymentWithAllLinks.getPayment())
        .thenReturn(
            withSettlementSummaryResult2
                .withState(new PaymentState("Status", true))
                .withTotalAmount(1L)
                .build());

    CreatePaymentService createPaymentService = mock(CreatePaymentService.class);
    when(createPaymentService.create(
            Mockito.<Account>any(), Mockito.<CreateCardPaymentRequest>any(), Mockito.<String>any()))
        .thenReturn(createdPaymentWithAllLinks);

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator = new PublicApiUriGenerator(configuration);

    PublicApiConfig config = new PublicApiConfig();
    config.setAdminFactory(new AdminFactory());
    config.setHealthFactory(mock(HealthFactory.class));
    config.setLoggingFactory(new DefaultLoggingFactory());
    config.setMetricsFactory(new MetricsFactory());
    config.setServerFactory(new DefaultServerFactory());
    PaginationDecorator paginationDecorator = new PaginationDecorator(config);
    Client client = mock(Client.class);
    LedgerService ledgerService =
        new LedgerService(client, new LedgerUriGenerator(new PublicApiConfig()));

    PaymentSearchService paymentSearchService =
        new PaymentSearchService(publicApiUriGenerator, paginationDecorator, ledgerService);

    PublicApiUriGenerator publicApiUriGenerator2 = mock(PublicApiUriGenerator.class);
    when(publicApiUriGenerator2.getPaymentURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    PublicApiConfig configuration2 = new PublicApiConfig();
    configuration2.setAdminFactory(new AdminFactory());
    configuration2.setHealthFactory(mock(HealthFactory.class));
    configuration2.setLoggingFactory(new DefaultLoggingFactory());
    configuration2.setMetricsFactory(new MetricsFactory());
    configuration2.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator3 = new PublicApiUriGenerator(configuration2);
    Client client2 = mock(Client.class);
    ConnectorService connectorService =
        new ConnectorService(client2, new ConnectorUriGenerator(new PublicApiConfig()));
    Client client3 = mock(Client.class);
    LedgerService ledgerService2 =
        new LedgerService(client3, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator3, connectorService, ledgerService2);

    PublicApiConfig configuration3 = new PublicApiConfig();
    configuration3.setAdminFactory(new AdminFactory());
    configuration3.setHealthFactory(mock(HealthFactory.class));
    configuration3.setLoggingFactory(new DefaultLoggingFactory());
    configuration3.setMetricsFactory(new MetricsFactory());
    configuration3.setServerFactory(new DefaultServerFactory());
    CapturePaymentService capturePaymentService =
        new CapturePaymentService(mock(Client.class), new ConnectorUriGenerator(configuration3));

    PublicApiConfig configuration4 = new PublicApiConfig();
    configuration4.setAdminFactory(new AdminFactory());
    configuration4.setHealthFactory(mock(HealthFactory.class));
    configuration4.setLoggingFactory(new DefaultLoggingFactory());
    configuration4.setMetricsFactory(new MetricsFactory());
    configuration4.setServerFactory(new DefaultServerFactory());
    CancelPaymentService cancelPaymentService =
        new CancelPaymentService(mock(Client.class), new ConnectorUriGenerator(configuration4));

    PublicApiConfig configuration5 = new PublicApiConfig();
    configuration5.setAdminFactory(new AdminFactory());
    configuration5.setHealthFactory(mock(HealthFactory.class));
    configuration5.setLoggingFactory(new DefaultLoggingFactory());
    configuration5.setMetricsFactory(new MetricsFactory());
    configuration5.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator4 = new PublicApiUriGenerator(configuration5);
    Client client4 = mock(Client.class);
    ConnectorService connectorService2 =
        new ConnectorService(client4, new ConnectorUriGenerator(new PublicApiConfig()));
    Client client5 = mock(Client.class);
    LedgerService ledgerService3 =
        new LedgerService(client5, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentEventsService getPaymentEventsService =
        new GetPaymentEventsService(publicApiUriGenerator4, connectorService2, ledgerService3);

    PaymentsResource paymentsResource =
        new PaymentsResource(
            createPaymentService,
            paymentSearchService,
            publicApiUriGenerator2,
            getPaymentService,
            capturePaymentService,
            cancelPaymentService,
            getPaymentEventsService);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    // Act
    Response actualCreateNewPaymentResult =
        paymentsResource.createNewPayment(
            account,
            new CreateCardPaymentRequest(CreateCardPaymentRequestBuilder.builder()),
            "Idempotency Key");

    // Assert
    verify(createdPaymentWithAllLinks).getPayment();
    verify(createdPaymentWithAllLinks).getWhenCreated();
    verify(createPaymentService)
        .create(isA(Account.class), isA(CreateCardPaymentRequest.class), eq("Idempotency Key"));
    verify(publicApiUriGenerator2).getPaymentURI("42");
    assertTrue(actualCreateNewPaymentResult instanceof OutboundJaxrsResponse);
    Object entity = actualCreateNewPaymentResult.getEntity();
    assertTrue(entity instanceof PaymentWithAllLinks);
    assertEquals("Card Brand", ((PaymentWithAllLinks) entity).getCardBrand());
    assertSame(cardDetails2, ((PaymentWithAllLinks) entity).getCardDetails().get());
  }

  /**
   * Test {@link PaymentsResource#createNewPayment(Account, CreateCardPaymentRequest, String)}.
   *
   * <ul>
   *   <li>Then return Location is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentsResource#createNewPayment(Account,
   * CreateCardPaymentRequest, String)}
   */
  @Test
  @DisplayName(
      "Test createNewPayment(Account, CreateCardPaymentRequest, String); then return Location is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Response PaymentsResource.createNewPayment(Account, CreateCardPaymentRequest, String)"
  })
  void testCreateNewPayment_thenReturnLocationIsNull() {
    // Arrange
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
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();

    CreatedPaymentWithAllLinks createdPaymentWithAllLinks = mock(CreatedPaymentWithAllLinks.class);
    when(createdPaymentWithAllLinks.getWhenCreated()).thenReturn(WhenCreated.EXISTING);

    PaymentWithAllLinksBuilder withAuthorisationModeResult2 =
        new PaymentWithAllLinksBuilder()
            .withAgreementId("42")
            .withAgreementPaymentType(AgreementPaymentType.INSTALMENT)
            .withAmount(10L)
            .withAuthorisationMode(AuthorisationMode.WEB);

    PaymentWithAllLinksBuilder withEmailResult2 =
        withAuthorisationModeResult2
            .withAuthorisationSummary(new AuthorisationSummary())
            .withCardDetails(mock(CardDetails.class))
            .withChargeId("42")
            .withCorporateCardSurcharge(1L)
            .withCreatedDate("2020-03-01")
            .withDelayedCapture(true)
            .withDescription("The characteristics of someone or something")
            .withEmail("jane.doe@example.org");

    PaymentWithAllLinksBuilder withLanguageResult2 =
        withEmailResult2
            .withExemption(new Exemption())
            .withFee(1L)
            .withLanguage(SupportedLanguage.ENGLISH);

    PaymentWithAllLinksBuilder withPaymentCaptureUriResult2 =
        withLanguageResult2
            .withMetadata(new ExternalMetadata(new HashMap<>()))
            .withMoto(true)
            .withNetAmount(1L)
            .withPaymentAuthorisationUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentCancelUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentCaptureUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    PaymentWithAllLinksBuilder withReferenceResult2 =
        withPaymentCaptureUriResult2
            .withPaymentConnectorResponseLinks(new ArrayList<>())
            .withPaymentEventsUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentProvider("Payment Provider")
            .withPaymentRefundsUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withProviderId("42")
            .withReference("Reference");

    PaymentWithAllLinksBuilder withSelfLinkResult2 =
        withReferenceResult2
            .withRefundSummary(new RefundSummary("Status", 10L, 10L))
            .withReturnUrl("https://example.org/example")
            .withSelfLink(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    PaymentSettlementSummary settlementSummary2 =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");

    PaymentWithAllLinksBuilder withSettlementSummaryResult2 =
        withSelfLinkResult2.withSettlementSummary(settlementSummary2);
    when(createdPaymentWithAllLinks.getPayment())
        .thenReturn(
            withSettlementSummaryResult2
                .withState(new PaymentState("Status", true))
                .withTotalAmount(1L)
                .build());

    CreatePaymentService createPaymentService = mock(CreatePaymentService.class);
    when(createPaymentService.create(
            Mockito.<Account>any(), Mockito.<CreateCardPaymentRequest>any(), Mockito.<String>any()))
        .thenReturn(createdPaymentWithAllLinks);

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator = new PublicApiUriGenerator(configuration);

    PublicApiConfig config = new PublicApiConfig();
    config.setAdminFactory(new AdminFactory());
    config.setHealthFactory(mock(HealthFactory.class));
    config.setLoggingFactory(new DefaultLoggingFactory());
    config.setMetricsFactory(new MetricsFactory());
    config.setServerFactory(new DefaultServerFactory());
    PaginationDecorator paginationDecorator = new PaginationDecorator(config);
    Client client = mock(Client.class);
    LedgerService ledgerService =
        new LedgerService(client, new LedgerUriGenerator(new PublicApiConfig()));

    PaymentSearchService paymentSearchService =
        new PaymentSearchService(publicApiUriGenerator, paginationDecorator, ledgerService);

    PublicApiConfig configuration2 = new PublicApiConfig();
    configuration2.setAdminFactory(new AdminFactory());
    configuration2.setHealthFactory(mock(HealthFactory.class));
    configuration2.setLoggingFactory(new DefaultLoggingFactory());
    configuration2.setMetricsFactory(new MetricsFactory());
    configuration2.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator2 = new PublicApiUriGenerator(configuration2);
    Client client2 = mock(Client.class);
    ConnectorService connectorService =
        new ConnectorService(client2, new ConnectorUriGenerator(new PublicApiConfig()));
    Client client3 = mock(Client.class);
    LedgerService ledgerService2 =
        new LedgerService(client3, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator2, connectorService, ledgerService2);

    PublicApiConfig configuration3 = new PublicApiConfig();
    configuration3.setAdminFactory(new AdminFactory());
    configuration3.setHealthFactory(mock(HealthFactory.class));
    configuration3.setLoggingFactory(new DefaultLoggingFactory());
    configuration3.setMetricsFactory(new MetricsFactory());
    configuration3.setServerFactory(new DefaultServerFactory());
    CapturePaymentService capturePaymentService =
        new CapturePaymentService(mock(Client.class), new ConnectorUriGenerator(configuration3));

    PublicApiConfig configuration4 = new PublicApiConfig();
    configuration4.setAdminFactory(new AdminFactory());
    configuration4.setHealthFactory(mock(HealthFactory.class));
    configuration4.setLoggingFactory(new DefaultLoggingFactory());
    configuration4.setMetricsFactory(new MetricsFactory());
    configuration4.setServerFactory(new DefaultServerFactory());
    CancelPaymentService cancelPaymentService =
        new CancelPaymentService(mock(Client.class), new ConnectorUriGenerator(configuration4));

    PublicApiConfig configuration5 = new PublicApiConfig();
    configuration5.setAdminFactory(new AdminFactory());
    configuration5.setHealthFactory(mock(HealthFactory.class));
    configuration5.setLoggingFactory(new DefaultLoggingFactory());
    configuration5.setMetricsFactory(new MetricsFactory());
    configuration5.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator3 = new PublicApiUriGenerator(configuration5);
    Client client4 = mock(Client.class);
    ConnectorService connectorService2 =
        new ConnectorService(client4, new ConnectorUriGenerator(new PublicApiConfig()));
    Client client5 = mock(Client.class);
    LedgerService ledgerService3 =
        new LedgerService(client5, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentEventsService getPaymentEventsService =
        new GetPaymentEventsService(publicApiUriGenerator3, connectorService2, ledgerService3);

    PaymentsResource paymentsResource =
        new PaymentsResource(
            createPaymentService,
            paymentSearchService,
            mock(PublicApiUriGenerator.class),
            getPaymentService,
            capturePaymentService,
            cancelPaymentService,
            getPaymentEventsService);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    // Act
    Response actualCreateNewPaymentResult =
        paymentsResource.createNewPayment(
            account,
            new CreateCardPaymentRequest(CreateCardPaymentRequestBuilder.builder()),
            "Idempotency Key");

    // Assert
    verify(createdPaymentWithAllLinks).getPayment();
    verify(createdPaymentWithAllLinks).getWhenCreated();
    verify(createPaymentService)
        .create(isA(Account.class), isA(CreateCardPaymentRequest.class), eq("Idempotency Key"));
    StatusType statusInfo = actualCreateNewPaymentResult.getStatusInfo();
    assertTrue(statusInfo instanceof Status);
    assertTrue(actualCreateNewPaymentResult instanceof OutboundJaxrsResponse);
    assertNull(actualCreateNewPaymentResult.getLocation());
    OutboundMessageContext context =
        ((OutboundJaxrsResponse) actualCreateNewPaymentResult).getContext();
    assertNull(context.getLocation());
    MultivaluedMap<String, Object> headers = actualCreateNewPaymentResult.getHeaders();
    assertEquals(2, headers.size());
    MultivaluedMap<String, String> stringHeaders = actualCreateNewPaymentResult.getStringHeaders();
    assertEquals(2, stringHeaders.size());
    MultivaluedMap<String, String> stringHeaders2 = context.getStringHeaders();
    assertEquals(2, stringHeaders2.size());
    assertEquals(200, actualCreateNewPaymentResult.getStatus());
    assertEquals(Status.OK, statusInfo);
    assertTrue(headers.containsKey("Cache-Control"));
    assertTrue(headers.containsKey("Pragma"));
    assertTrue(stringHeaders.containsKey("Cache-Control"));
    assertTrue(stringHeaders.containsKey("Pragma"));
    assertTrue(stringHeaders2.containsKey("Cache-Control"));
    assertTrue(stringHeaders2.containsKey("Pragma"));
  }

  /**
   * Test {@link PaymentsResource#createNewPayment(Account, CreateCardPaymentRequest, String)}.
   *
   * <ul>
   *   <li>Then return Status is two hundred one.
   * </ul>
   *
   * <p>Method under test: {@link PaymentsResource#createNewPayment(Account,
   * CreateCardPaymentRequest, String)}
   */
  @Test
  @DisplayName(
      "Test createNewPayment(Account, CreateCardPaymentRequest, String); then return Status is two hundred one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Response PaymentsResource.createNewPayment(Account, CreateCardPaymentRequest, String)"
  })
  void testCreateNewPayment_thenReturnStatusIsTwoHundredOne() {
    // Arrange
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

    CreatedPaymentWithAllLinks createdPaymentWithAllLinks = mock(CreatedPaymentWithAllLinks.class);
    when(createdPaymentWithAllLinks.getWhenCreated()).thenReturn(WhenCreated.BRAND_NEW);

    PaymentWithAllLinksBuilder withAuthorisationModeResult2 =
        new PaymentWithAllLinksBuilder()
            .withAgreementId("42")
            .withAgreementPaymentType(AgreementPaymentType.INSTALMENT)
            .withAmount(10L)
            .withAuthorisationMode(AuthorisationMode.WEB);

    PaymentWithAllLinksBuilder withEmailResult2 =
        withAuthorisationModeResult2
            .withAuthorisationSummary(new AuthorisationSummary())
            .withCardDetails(mock(CardDetails.class))
            .withChargeId("42")
            .withCorporateCardSurcharge(1L)
            .withCreatedDate("2020-03-01")
            .withDelayedCapture(true)
            .withDescription("The characteristics of someone or something")
            .withEmail("jane.doe@example.org");

    PaymentWithAllLinksBuilder withLanguageResult2 =
        withEmailResult2
            .withExemption(new Exemption())
            .withFee(1L)
            .withLanguage(SupportedLanguage.ENGLISH);

    PaymentWithAllLinksBuilder withPaymentCaptureUriResult2 =
        withLanguageResult2
            .withMetadata(new ExternalMetadata(new HashMap<>()))
            .withMoto(true)
            .withNetAmount(1L)
            .withPaymentAuthorisationUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentCancelUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentCaptureUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    PaymentWithAllLinksBuilder withReferenceResult2 =
        withPaymentCaptureUriResult2
            .withPaymentConnectorResponseLinks(new ArrayList<>())
            .withPaymentEventsUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentProvider("Payment Provider")
            .withPaymentRefundsUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withProviderId("42")
            .withReference("Reference");

    PaymentWithAllLinksBuilder withSelfLinkResult2 =
        withReferenceResult2
            .withRefundSummary(new RefundSummary("Status", 10L, 10L))
            .withReturnUrl("https://example.org/example")
            .withSelfLink(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    PaymentSettlementSummary settlementSummary2 =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");

    PaymentWithAllLinksBuilder withSettlementSummaryResult2 =
        withSelfLinkResult2.withSettlementSummary(settlementSummary2);
    when(createdPaymentWithAllLinks.getPayment())
        .thenReturn(
            withSettlementSummaryResult2
                .withState(new PaymentState("Status", true))
                .withTotalAmount(1L)
                .build());

    CreatePaymentService createPaymentService = mock(CreatePaymentService.class);
    when(createPaymentService.create(
            Mockito.<Account>any(), Mockito.<CreateCardPaymentRequest>any(), Mockito.<String>any()))
        .thenReturn(createdPaymentWithAllLinks);

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator = new PublicApiUriGenerator(configuration);

    PublicApiConfig config = new PublicApiConfig();
    config.setAdminFactory(new AdminFactory());
    config.setHealthFactory(mock(HealthFactory.class));
    config.setLoggingFactory(new DefaultLoggingFactory());
    config.setMetricsFactory(new MetricsFactory());
    config.setServerFactory(new DefaultServerFactory());
    PaginationDecorator paginationDecorator = new PaginationDecorator(config);
    Client client = mock(Client.class);
    LedgerService ledgerService =
        new LedgerService(client, new LedgerUriGenerator(new PublicApiConfig()));

    PaymentSearchService paymentSearchService =
        new PaymentSearchService(publicApiUriGenerator, paginationDecorator, ledgerService);

    PublicApiUriGenerator publicApiUriGenerator2 = mock(PublicApiUriGenerator.class);
    when(publicApiUriGenerator2.getPaymentURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    PublicApiConfig configuration2 = new PublicApiConfig();
    configuration2.setAdminFactory(new AdminFactory());
    configuration2.setHealthFactory(mock(HealthFactory.class));
    configuration2.setLoggingFactory(new DefaultLoggingFactory());
    configuration2.setMetricsFactory(new MetricsFactory());
    configuration2.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator3 = new PublicApiUriGenerator(configuration2);
    Client client2 = mock(Client.class);
    ConnectorService connectorService =
        new ConnectorService(client2, new ConnectorUriGenerator(new PublicApiConfig()));
    Client client3 = mock(Client.class);
    LedgerService ledgerService2 =
        new LedgerService(client3, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator3, connectorService, ledgerService2);

    PublicApiConfig configuration3 = new PublicApiConfig();
    configuration3.setAdminFactory(new AdminFactory());
    configuration3.setHealthFactory(mock(HealthFactory.class));
    configuration3.setLoggingFactory(new DefaultLoggingFactory());
    configuration3.setMetricsFactory(new MetricsFactory());
    configuration3.setServerFactory(new DefaultServerFactory());
    CapturePaymentService capturePaymentService =
        new CapturePaymentService(mock(Client.class), new ConnectorUriGenerator(configuration3));

    PublicApiConfig configuration4 = new PublicApiConfig();
    configuration4.setAdminFactory(new AdminFactory());
    configuration4.setHealthFactory(mock(HealthFactory.class));
    configuration4.setLoggingFactory(new DefaultLoggingFactory());
    configuration4.setMetricsFactory(new MetricsFactory());
    configuration4.setServerFactory(new DefaultServerFactory());
    CancelPaymentService cancelPaymentService =
        new CancelPaymentService(mock(Client.class), new ConnectorUriGenerator(configuration4));

    PublicApiConfig configuration5 = new PublicApiConfig();
    configuration5.setAdminFactory(new AdminFactory());
    configuration5.setHealthFactory(mock(HealthFactory.class));
    configuration5.setLoggingFactory(new DefaultLoggingFactory());
    configuration5.setMetricsFactory(new MetricsFactory());
    configuration5.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator4 = new PublicApiUriGenerator(configuration5);
    Client client4 = mock(Client.class);
    ConnectorService connectorService2 =
        new ConnectorService(client4, new ConnectorUriGenerator(new PublicApiConfig()));
    Client client5 = mock(Client.class);
    LedgerService ledgerService3 =
        new LedgerService(client5, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentEventsService getPaymentEventsService =
        new GetPaymentEventsService(publicApiUriGenerator4, connectorService2, ledgerService3);

    PaymentsResource paymentsResource =
        new PaymentsResource(
            createPaymentService,
            paymentSearchService,
            publicApiUriGenerator2,
            getPaymentService,
            capturePaymentService,
            cancelPaymentService,
            getPaymentEventsService);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    // Act
    Response actualCreateNewPaymentResult =
        paymentsResource.createNewPayment(
            account,
            new CreateCardPaymentRequest(CreateCardPaymentRequestBuilder.builder()),
            "Idempotency Key");

    // Assert
    verify(createdPaymentWithAllLinks).getPayment();
    verify(createdPaymentWithAllLinks).getWhenCreated();
    verify(createPaymentService)
        .create(isA(Account.class), isA(CreateCardPaymentRequest.class), eq("Idempotency Key"));
    verify(publicApiUriGenerator2).getPaymentURI("42");
    StatusType statusInfo = actualCreateNewPaymentResult.getStatusInfo();
    assertTrue(statusInfo instanceof Status);
    assertTrue(actualCreateNewPaymentResult instanceof OutboundJaxrsResponse);
    assertEquals(201, actualCreateNewPaymentResult.getStatus());
    assertEquals(Status.CREATED, statusInfo);
  }

  /**
   * Test {@link PaymentsResource#createNewPayment(Account, CreateCardPaymentRequest, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Headers size is three.
   * </ul>
   *
   * <p>Method under test: {@link PaymentsResource#createNewPayment(Account,
   * CreateCardPaymentRequest, String)}
   */
  @Test
  @DisplayName(
      "Test createNewPayment(Account, CreateCardPaymentRequest, String); when 'null'; then return Headers size is three")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Response PaymentsResource.createNewPayment(Account, CreateCardPaymentRequest, String)"
  })
  void testCreateNewPayment_whenNull_thenReturnHeadersSizeIsThree() {
    // Arrange
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

    CreatedPaymentWithAllLinks createdPaymentWithAllLinks = mock(CreatedPaymentWithAllLinks.class);
    when(createdPaymentWithAllLinks.getWhenCreated()).thenReturn(WhenCreated.BRAND_NEW);

    PaymentWithAllLinksBuilder withAuthorisationModeResult2 =
        new PaymentWithAllLinksBuilder()
            .withAgreementId("42")
            .withAgreementPaymentType(AgreementPaymentType.INSTALMENT)
            .withAmount(10L)
            .withAuthorisationMode(AuthorisationMode.WEB);

    PaymentWithAllLinksBuilder withEmailResult2 =
        withAuthorisationModeResult2
            .withAuthorisationSummary(new AuthorisationSummary())
            .withCardDetails(mock(CardDetails.class))
            .withChargeId("42")
            .withCorporateCardSurcharge(1L)
            .withCreatedDate("2020-03-01")
            .withDelayedCapture(true)
            .withDescription("The characteristics of someone or something")
            .withEmail("jane.doe@example.org");

    PaymentWithAllLinksBuilder withLanguageResult2 =
        withEmailResult2
            .withExemption(new Exemption())
            .withFee(1L)
            .withLanguage(SupportedLanguage.ENGLISH);

    PaymentWithAllLinksBuilder withPaymentCaptureUriResult2 =
        withLanguageResult2
            .withMetadata(new ExternalMetadata(new HashMap<>()))
            .withMoto(true)
            .withNetAmount(1L)
            .withPaymentAuthorisationUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentCancelUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentCaptureUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    PaymentWithAllLinksBuilder withReferenceResult2 =
        withPaymentCaptureUriResult2
            .withPaymentConnectorResponseLinks(new ArrayList<>())
            .withPaymentEventsUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentProvider("Payment Provider")
            .withPaymentRefundsUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withProviderId("42")
            .withReference("Reference");

    PaymentWithAllLinksBuilder withSelfLinkResult2 =
        withReferenceResult2
            .withRefundSummary(new RefundSummary("Status", 10L, 10L))
            .withReturnUrl("https://example.org/example")
            .withSelfLink(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    PaymentSettlementSummary settlementSummary2 =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");

    PaymentWithAllLinksBuilder withSettlementSummaryResult2 =
        withSelfLinkResult2.withSettlementSummary(settlementSummary2);
    when(createdPaymentWithAllLinks.getPayment())
        .thenReturn(
            withSettlementSummaryResult2
                .withState(new PaymentState("Status", true))
                .withTotalAmount(1L)
                .build());

    CreatePaymentService createPaymentService = mock(CreatePaymentService.class);
    when(createPaymentService.create(
            Mockito.<Account>any(), Mockito.<CreateCardPaymentRequest>any(), Mockito.<String>any()))
        .thenReturn(createdPaymentWithAllLinks);

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator = new PublicApiUriGenerator(configuration);

    PublicApiConfig config = new PublicApiConfig();
    config.setAdminFactory(new AdminFactory());
    config.setHealthFactory(mock(HealthFactory.class));
    config.setLoggingFactory(new DefaultLoggingFactory());
    config.setMetricsFactory(new MetricsFactory());
    config.setServerFactory(new DefaultServerFactory());
    PaginationDecorator paginationDecorator = new PaginationDecorator(config);
    Client client = mock(Client.class);
    LedgerService ledgerService =
        new LedgerService(client, new LedgerUriGenerator(new PublicApiConfig()));

    PaymentSearchService paymentSearchService =
        new PaymentSearchService(publicApiUriGenerator, paginationDecorator, ledgerService);

    PublicApiUriGenerator publicApiUriGenerator2 = mock(PublicApiUriGenerator.class);
    when(publicApiUriGenerator2.getPaymentURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    PublicApiConfig configuration2 = new PublicApiConfig();
    configuration2.setAdminFactory(new AdminFactory());
    configuration2.setHealthFactory(mock(HealthFactory.class));
    configuration2.setLoggingFactory(new DefaultLoggingFactory());
    configuration2.setMetricsFactory(new MetricsFactory());
    configuration2.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator3 = new PublicApiUriGenerator(configuration2);
    Client client2 = mock(Client.class);
    ConnectorService connectorService =
        new ConnectorService(client2, new ConnectorUriGenerator(new PublicApiConfig()));
    Client client3 = mock(Client.class);
    LedgerService ledgerService2 =
        new LedgerService(client3, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator3, connectorService, ledgerService2);

    PublicApiConfig configuration3 = new PublicApiConfig();
    configuration3.setAdminFactory(new AdminFactory());
    configuration3.setHealthFactory(mock(HealthFactory.class));
    configuration3.setLoggingFactory(new DefaultLoggingFactory());
    configuration3.setMetricsFactory(new MetricsFactory());
    configuration3.setServerFactory(new DefaultServerFactory());
    CapturePaymentService capturePaymentService =
        new CapturePaymentService(mock(Client.class), new ConnectorUriGenerator(configuration3));

    PublicApiConfig configuration4 = new PublicApiConfig();
    configuration4.setAdminFactory(new AdminFactory());
    configuration4.setHealthFactory(mock(HealthFactory.class));
    configuration4.setLoggingFactory(new DefaultLoggingFactory());
    configuration4.setMetricsFactory(new MetricsFactory());
    configuration4.setServerFactory(new DefaultServerFactory());
    CancelPaymentService cancelPaymentService =
        new CancelPaymentService(mock(Client.class), new ConnectorUriGenerator(configuration4));

    PublicApiConfig configuration5 = new PublicApiConfig();
    configuration5.setAdminFactory(new AdminFactory());
    configuration5.setHealthFactory(mock(HealthFactory.class));
    configuration5.setLoggingFactory(new DefaultLoggingFactory());
    configuration5.setMetricsFactory(new MetricsFactory());
    configuration5.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator4 = new PublicApiUriGenerator(configuration5);
    Client client4 = mock(Client.class);
    ConnectorService connectorService2 =
        new ConnectorService(client4, new ConnectorUriGenerator(new PublicApiConfig()));
    Client client5 = mock(Client.class);
    LedgerService ledgerService3 =
        new LedgerService(client5, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentEventsService getPaymentEventsService =
        new GetPaymentEventsService(publicApiUriGenerator4, connectorService2, ledgerService3);

    PaymentsResource paymentsResource =
        new PaymentsResource(
            createPaymentService,
            paymentSearchService,
            publicApiUriGenerator2,
            getPaymentService,
            capturePaymentService,
            cancelPaymentService,
            getPaymentEventsService);

    // Act
    Response actualCreateNewPaymentResult =
        paymentsResource.createNewPayment(
            new Account("42", TokenPaymentType.CARD, "ABC123"), null, "Idempotency Key");

    // Assert
    verify(createdPaymentWithAllLinks).getPayment();
    verify(createdPaymentWithAllLinks).getWhenCreated();
    verify(createPaymentService).create(isA(Account.class), isNull(), eq("Idempotency Key"));
    verify(publicApiUriGenerator2).getPaymentURI("42");
    assertTrue(actualCreateNewPaymentResult instanceof OutboundJaxrsResponse);
    MultivaluedMap<String, Object> headers = actualCreateNewPaymentResult.getHeaders();
    assertEquals(3, headers.size());
    MultivaluedMap<String, String> stringHeaders = actualCreateNewPaymentResult.getStringHeaders();
    assertEquals(3, stringHeaders.size());
    assertTrue(headers.containsKey("Cache-Control"));
    assertTrue(headers.containsKey("Location"));
    assertTrue(headers.containsKey("Pragma"));
    assertTrue(stringHeaders.containsKey("Cache-Control"));
    assertTrue(stringHeaders.containsKey("Location"));
    assertTrue(stringHeaders.containsKey("Pragma"));
    assertSame(headers, actualCreateNewPaymentResult.getMetadata());
  }

  /**
   * Test {@link PaymentsResource#cancelPayment(Account, String)}.
   *
   * <p>Method under test: {@link PaymentsResource#cancelPayment(Account, String)}
   */
  @Test
  @DisplayName("Test cancelPayment(Account, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Response PaymentsResource.cancelPayment(Account, String)"})
  void testCancelPayment() {
    // Arrange
    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator = new PublicApiUriGenerator(configuration);

    PublicApiConfig configuration2 = new PublicApiConfig();
    configuration2.setAdminFactory(new AdminFactory());
    configuration2.setHealthFactory(mock(HealthFactory.class));
    configuration2.setLoggingFactory(new DefaultLoggingFactory());
    configuration2.setMetricsFactory(new MetricsFactory());
    configuration2.setServerFactory(new DefaultServerFactory());
    CreatePaymentService createPaymentService =
        new CreatePaymentService(
            mock(Client.class), publicApiUriGenerator, new ConnectorUriGenerator(configuration2));

    PublicApiConfig configuration3 = new PublicApiConfig();
    configuration3.setAdminFactory(new AdminFactory());
    configuration3.setHealthFactory(mock(HealthFactory.class));
    configuration3.setLoggingFactory(new DefaultLoggingFactory());
    configuration3.setMetricsFactory(new MetricsFactory());
    configuration3.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator2 = new PublicApiUriGenerator(configuration3);

    PublicApiConfig config = new PublicApiConfig();
    config.setAdminFactory(new AdminFactory());
    config.setHealthFactory(mock(HealthFactory.class));
    config.setLoggingFactory(new DefaultLoggingFactory());
    config.setMetricsFactory(new MetricsFactory());
    config.setServerFactory(new DefaultServerFactory());
    PaginationDecorator paginationDecorator = new PaginationDecorator(config);
    Client client = mock(Client.class);
    LedgerService ledgerService =
        new LedgerService(client, new LedgerUriGenerator(new PublicApiConfig()));

    PaymentSearchService paymentSearchService =
        new PaymentSearchService(publicApiUriGenerator2, paginationDecorator, ledgerService);

    AdminFactory admin = new AdminFactory();
    admin.setHealthChecks(new HealthCheckConfiguration());
    admin.setTasks(new TaskConfiguration());

    PublicApiConfig configuration4 = new PublicApiConfig();
    configuration4.setAdminFactory(admin);
    configuration4.setHealthFactory(mock(HealthFactory.class));
    configuration4.setLoggingFactory(new DefaultLoggingFactory());
    configuration4.setMetricsFactory(new MetricsFactory());
    configuration4.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator3 = new PublicApiUriGenerator(configuration4);

    PublicApiConfig configuration5 = new PublicApiConfig();
    configuration5.setAdminFactory(new AdminFactory());
    configuration5.setHealthFactory(mock(HealthFactory.class));
    configuration5.setLoggingFactory(new DefaultLoggingFactory());
    configuration5.setMetricsFactory(new MetricsFactory());
    configuration5.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator4 = new PublicApiUriGenerator(configuration5);
    Client client2 = mock(Client.class);
    ConnectorService connectorService =
        new ConnectorService(client2, new ConnectorUriGenerator(new PublicApiConfig()));
    Client client3 = mock(Client.class);
    LedgerService ledgerService2 =
        new LedgerService(client3, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator4, connectorService, ledgerService2);

    PublicApiConfig configuration6 = new PublicApiConfig();
    configuration6.setAdminFactory(new AdminFactory());
    configuration6.setHealthFactory(mock(HealthFactory.class));
    configuration6.setLoggingFactory(new DefaultLoggingFactory());
    configuration6.setMetricsFactory(new MetricsFactory());
    configuration6.setServerFactory(new DefaultServerFactory());
    CapturePaymentService capturePaymentService =
        new CapturePaymentService(mock(Client.class), new ConnectorUriGenerator(configuration6));

    CancelPaymentService cancelPaymentService = mock(CancelPaymentService.class);
    OutboundJaxrsResponse outboundJaxrsResponse =
        new OutboundJaxrsResponse(null, new OutboundMessageContext());
    when(cancelPaymentService.cancel(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(outboundJaxrsResponse);

    PublicApiConfig configuration7 = new PublicApiConfig();
    configuration7.setAdminFactory(new AdminFactory());
    configuration7.setHealthFactory(mock(HealthFactory.class));
    configuration7.setLoggingFactory(new DefaultLoggingFactory());
    configuration7.setMetricsFactory(new MetricsFactory());
    configuration7.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator5 = new PublicApiUriGenerator(configuration7);
    Client client4 = mock(Client.class);
    ConnectorService connectorService2 =
        new ConnectorService(client4, new ConnectorUriGenerator(new PublicApiConfig()));
    Client client5 = mock(Client.class);
    LedgerService ledgerService3 =
        new LedgerService(client5, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentEventsService getPaymentEventsService =
        new GetPaymentEventsService(publicApiUriGenerator5, connectorService2, ledgerService3);

    PaymentsResource paymentsResource =
        new PaymentsResource(
            createPaymentService,
            paymentSearchService,
            publicApiUriGenerator3,
            getPaymentService,
            capturePaymentService,
            cancelPaymentService,
            getPaymentEventsService);

    // Act
    Response actualCancelPaymentResult =
        paymentsResource.cancelPayment(new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(cancelPaymentService).cancel(isA(Account.class), eq("42"));
    assertSame(outboundJaxrsResponse, actualCancelPaymentResult);
  }

  /**
   * Test {@link PaymentsResource#capturePayment(Account, String)}.
   *
   * <ul>
   *   <li>Then calls {@link CapturePaymentService#capture(Account, String)}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentsResource#capturePayment(Account, String)}
   */
  @Test
  @DisplayName("Test capturePayment(Account, String); then calls capture(Account, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Response PaymentsResource.capturePayment(Account, String)"})
  void testCapturePayment_thenCallsCapture() {
    // Arrange
    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator = new PublicApiUriGenerator(configuration);

    PublicApiConfig configuration2 = new PublicApiConfig();
    configuration2.setAdminFactory(new AdminFactory());
    configuration2.setHealthFactory(mock(HealthFactory.class));
    configuration2.setLoggingFactory(new DefaultLoggingFactory());
    configuration2.setMetricsFactory(new MetricsFactory());
    configuration2.setServerFactory(new DefaultServerFactory());
    CreatePaymentService createPaymentService =
        new CreatePaymentService(
            mock(Client.class), publicApiUriGenerator, new ConnectorUriGenerator(configuration2));

    PublicApiConfig configuration3 = new PublicApiConfig();
    configuration3.setAdminFactory(new AdminFactory());
    configuration3.setHealthFactory(mock(HealthFactory.class));
    configuration3.setLoggingFactory(new DefaultLoggingFactory());
    configuration3.setMetricsFactory(new MetricsFactory());
    configuration3.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator2 = new PublicApiUriGenerator(configuration3);

    PublicApiConfig config = new PublicApiConfig();
    config.setAdminFactory(new AdminFactory());
    config.setHealthFactory(mock(HealthFactory.class));
    config.setLoggingFactory(new DefaultLoggingFactory());
    config.setMetricsFactory(new MetricsFactory());
    config.setServerFactory(new DefaultServerFactory());
    PaginationDecorator paginationDecorator = new PaginationDecorator(config);
    Client client = mock(Client.class);
    LedgerService ledgerService =
        new LedgerService(client, new LedgerUriGenerator(new PublicApiConfig()));

    PaymentSearchService paymentSearchService =
        new PaymentSearchService(publicApiUriGenerator2, paginationDecorator, ledgerService);

    AdminFactory admin = new AdminFactory();
    admin.setHealthChecks(new HealthCheckConfiguration());
    admin.setTasks(new TaskConfiguration());

    PublicApiConfig configuration4 = new PublicApiConfig();
    configuration4.setAdminFactory(admin);
    configuration4.setHealthFactory(mock(HealthFactory.class));
    configuration4.setLoggingFactory(new DefaultLoggingFactory());
    configuration4.setMetricsFactory(new MetricsFactory());
    configuration4.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator3 = new PublicApiUriGenerator(configuration4);

    PublicApiConfig configuration5 = new PublicApiConfig();
    configuration5.setAdminFactory(new AdminFactory());
    configuration5.setHealthFactory(mock(HealthFactory.class));
    configuration5.setLoggingFactory(new DefaultLoggingFactory());
    configuration5.setMetricsFactory(new MetricsFactory());
    configuration5.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator4 = new PublicApiUriGenerator(configuration5);
    Client client2 = mock(Client.class);
    ConnectorService connectorService =
        new ConnectorService(client2, new ConnectorUriGenerator(new PublicApiConfig()));
    Client client3 = mock(Client.class);
    LedgerService ledgerService2 =
        new LedgerService(client3, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator4, connectorService, ledgerService2);

    CapturePaymentService capturePaymentService = mock(CapturePaymentService.class);
    when(capturePaymentService.capture(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(new OutboundJaxrsResponse(Status.NO_CONTENT, new OutboundMessageContext()));

    PublicApiConfig configuration6 = new PublicApiConfig();
    configuration6.setAdminFactory(new AdminFactory());
    configuration6.setHealthFactory(mock(HealthFactory.class));
    configuration6.setLoggingFactory(new DefaultLoggingFactory());
    configuration6.setMetricsFactory(new MetricsFactory());
    configuration6.setServerFactory(new DefaultServerFactory());
    CancelPaymentService cancelPaymentService =
        new CancelPaymentService(mock(Client.class), new ConnectorUriGenerator(configuration6));

    PublicApiConfig configuration7 = new PublicApiConfig();
    configuration7.setAdminFactory(new AdminFactory());
    configuration7.setHealthFactory(mock(HealthFactory.class));
    configuration7.setLoggingFactory(new DefaultLoggingFactory());
    configuration7.setMetricsFactory(new MetricsFactory());
    configuration7.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator5 = new PublicApiUriGenerator(configuration7);
    Client client4 = mock(Client.class);
    ConnectorService connectorService2 =
        new ConnectorService(client4, new ConnectorUriGenerator(new PublicApiConfig()));
    Client client5 = mock(Client.class);
    LedgerService ledgerService3 =
        new LedgerService(client5, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentEventsService getPaymentEventsService =
        new GetPaymentEventsService(publicApiUriGenerator5, connectorService2, ledgerService3);

    PaymentsResource paymentsResource =
        new PaymentsResource(
            createPaymentService,
            paymentSearchService,
            publicApiUriGenerator3,
            getPaymentService,
            capturePaymentService,
            cancelPaymentService,
            getPaymentEventsService);

    // Act
    Response actualCapturePaymentResult =
        paymentsResource.capturePayment(new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(capturePaymentService).capture(isA(Account.class), eq("42"));
    assertTrue(actualCapturePaymentResult instanceof OutboundJaxrsResponse);
  }
}
