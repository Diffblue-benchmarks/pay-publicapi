package uk.gov.pay.api.agreement.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
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
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.Invocation.Builder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import java.util.ArrayList;
import org.glassfish.jersey.message.internal.OutboundJaxrsResponse;
import org.glassfish.jersey.message.internal.OutboundMessageContext;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import uk.gov.pay.api.agreement.model.AgreementCreatedResponse;
import uk.gov.pay.api.agreement.model.AgreementLedgerResponse;
import uk.gov.pay.api.agreement.model.AgreementSearchResults;
import uk.gov.pay.api.agreement.model.CreateAgreementRequest;
import uk.gov.pay.api.app.config.PublicApiConfig;
import uk.gov.pay.api.auth.Account;
import uk.gov.pay.api.ledger.model.AgreementSearchParams;
import uk.gov.pay.api.ledger.model.SearchResults;
import uk.gov.pay.api.ledger.service.LedgerUriGenerator;
import uk.gov.pay.api.model.TokenPaymentType;
import uk.gov.pay.api.model.links.Link;
import uk.gov.pay.api.model.links.SearchNavigationLinks;
import uk.gov.pay.api.model.search.PaginationDecorator;
import uk.gov.pay.api.service.ConnectorService;
import uk.gov.pay.api.service.ConnectorUriGenerator;
import uk.gov.pay.api.service.LedgerService;

public class AgreementsServiceDiffblueTest {
  /**
   * Test {@link AgreementsService#createAgreement(Account, CreateAgreementRequest)}.
   *
   * <ul>
   *   <li>Then return AgreementId is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link AgreementsService#createAgreement(Account,
   * CreateAgreementRequest)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AgreementCreatedResponse AgreementsService.createAgreement(Account, CreateAgreementRequest)"
  })
  public void testCreateAgreement_thenReturnAgreementIdIs42() {
    // Arrange
    ConnectorService connectorService = mock(ConnectorService.class);
    AgreementCreatedResponse agreementCreatedResponse = new AgreementCreatedResponse("42");
    when(connectorService.createAgreement(
            Mockito.<Account>any(), Mockito.<CreateAgreementRequest>any()))
        .thenReturn(agreementCreatedResponse);

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    LedgerService ledgerService =
        new LedgerService(mock(Client.class), new LedgerUriGenerator(configuration));

    AdminFactory admin = new AdminFactory();
    admin.setHealthChecks(new HealthCheckConfiguration());
    admin.setTasks(new TaskConfiguration());

    PublicApiConfig config = new PublicApiConfig();
    config.setAdminFactory(admin);
    config.setHealthFactory(mock(HealthFactory.class));
    config.setLoggingFactory(new DefaultLoggingFactory());
    config.setMetricsFactory(new MetricsFactory());
    config.setServerFactory(new DefaultServerFactory());

    AgreementsService agreementsService =
        new AgreementsService(connectorService, ledgerService, new PaginationDecorator(config));
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    // Act
    AgreementCreatedResponse actualCreateAgreementResult =
        agreementsService.createAgreement(account, new CreateAgreementRequest());

    // Assert
    verify(connectorService).createAgreement(isA(Account.class), isA(CreateAgreementRequest.class));
    assertEquals("42", actualCreateAgreementResult.getAgreementId());
    assertSame(agreementCreatedResponse, actualCreateAgreementResult);
  }

  /**
   * Test {@link AgreementsService#cancelAgreement(Account, String)}.
   *
   * <ul>
   *   <li>Then calls {@link ConnectorService#cancelAgreement(Account, String)}.
   * </ul>
   *
   * <p>Method under test: {@link AgreementsService#cancelAgreement(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Response AgreementsService.cancelAgreement(Account, String)"})
  public void testCancelAgreement_thenCallsCancelAgreement() {
    // Arrange
    ConnectorService connectorService = mock(ConnectorService.class);
    doNothing()
        .when(connectorService)
        .cancelAgreement(Mockito.<Account>any(), Mockito.<String>any());

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    LedgerService ledgerService =
        new LedgerService(mock(Client.class), new LedgerUriGenerator(configuration));

    AdminFactory admin = new AdminFactory();
    admin.setHealthChecks(new HealthCheckConfiguration());
    admin.setTasks(new TaskConfiguration());

    PublicApiConfig config = new PublicApiConfig();
    config.setAdminFactory(admin);
    config.setHealthFactory(mock(HealthFactory.class));
    config.setLoggingFactory(new DefaultLoggingFactory());
    config.setMetricsFactory(new MetricsFactory());
    config.setServerFactory(new DefaultServerFactory());

    AgreementsService agreementsService =
        new AgreementsService(connectorService, ledgerService, new PaginationDecorator(config));

    // Act
    Response actualCancelAgreementResult =
        agreementsService.cancelAgreement(new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(connectorService).cancelAgreement(isA(Account.class), eq("42"));
    assertTrue(actualCancelAgreementResult instanceof OutboundJaxrsResponse);
  }

  /**
   * Test {@link AgreementsService#cancelAgreement(Account, String)}.
   *
   * <ul>
   *   <li>Then calls {@link Client#target(String)}.
   * </ul>
   *
   * <p>Method under test: {@link AgreementsService#cancelAgreement(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Response AgreementsService.cancelAgreement(Account, String)"})
  public void testCancelAgreement_thenCallsTarget() {
    // Arrange
    Builder builder = mock(Builder.class);
    when(builder.post(Mockito.<Entity<?>>any()))
        .thenReturn(new OutboundJaxrsResponse(Status.NO_CONTENT, new OutboundMessageContext()));

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.cancelAgreementURI(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn("Cancel Agreement URI");

    ConnectorService connectorService = new ConnectorService(client, connectorUriGenerator);

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    LedgerService ledgerService =
        new LedgerService(mock(Client.class), new LedgerUriGenerator(configuration));

    AdminFactory admin = new AdminFactory();
    admin.setHealthChecks(new HealthCheckConfiguration());
    admin.setTasks(new TaskConfiguration());

    PublicApiConfig config = new PublicApiConfig();
    config.setAdminFactory(admin);
    config.setHealthFactory(mock(HealthFactory.class));
    config.setLoggingFactory(new DefaultLoggingFactory());
    config.setMetricsFactory(new MetricsFactory());
    config.setServerFactory(new DefaultServerFactory());

    AgreementsService agreementsService =
        new AgreementsService(connectorService, ledgerService, new PaginationDecorator(config));

    // Act
    Response actualCancelAgreementResult =
        agreementsService.cancelAgreement(new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(client).target("Cancel Agreement URI");
    verify(builder).post(isNull());
    verify(webTarget).request();
    verify(connectorUriGenerator).cancelAgreementURI(isA(Account.class), eq("42"));
    assertTrue(actualCancelAgreementResult instanceof OutboundJaxrsResponse);
  }

  /**
   * Test {@link AgreementsService#getAgreement(Account, String)}.
   *
   * <ul>
   *   <li>Then return {@link AgreementLedgerResponse} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link AgreementsService#getAgreement(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AgreementLedgerResponse AgreementsService.getAgreement(Account, String)"})
  public void testGetAgreement_thenReturnAgreementLedgerResponse() {
    // Arrange
    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    ConnectorService connectorService =
        new ConnectorService(mock(Client.class), new ConnectorUriGenerator(configuration));

    AgreementLedgerResponse agreementLedgerResponse = new AgreementLedgerResponse();
    agreementLedgerResponse.setExternalId("42");

    LedgerService ledgerService = mock(LedgerService.class);
    when(ledgerService.getAgreement(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(agreementLedgerResponse);

    AdminFactory admin = new AdminFactory();
    admin.setHealthChecks(new HealthCheckConfiguration());
    admin.setTasks(new TaskConfiguration());

    PublicApiConfig config = new PublicApiConfig();
    config.setAdminFactory(admin);
    config.setHealthFactory(mock(HealthFactory.class));
    config.setLoggingFactory(new DefaultLoggingFactory());
    config.setMetricsFactory(new MetricsFactory());
    config.setServerFactory(new DefaultServerFactory());

    AgreementsService agreementsService =
        new AgreementsService(connectorService, ledgerService, new PaginationDecorator(config));

    // Act
    AgreementLedgerResponse actualAgreement =
        agreementsService.getAgreement(new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(ledgerService).getAgreement(isA(Account.class), eq("42"));
    assertSame(agreementLedgerResponse, actualAgreement);
  }

  /**
   * Test {@link AgreementsService#searchAgreements(Account, AgreementSearchParams)}.
   *
   * <ul>
   *   <li>Then return Links FirstPage Href is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AgreementsService#searchAgreements(Account,
   * AgreementSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AgreementSearchResults AgreementsService.searchAgreements(Account, AgreementSearchParams)"
  })
  public void testSearchAgreements_thenReturnLinksFirstPageHrefIsNull() {
    // Arrange
    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    ConnectorService connectorService =
        new ConnectorService(mock(Client.class), new ConnectorUriGenerator(configuration));

    LedgerService ledgerService = mock(LedgerService.class);
    ArrayList<AgreementLedgerResponse> results = new ArrayList<>();
    when(ledgerService.searchAgreements(
            Mockito.<Account>any(), Mockito.<AgreementSearchParams>any()))
        .thenReturn(new SearchResults<>(1, 3, 1, results, new SearchNavigationLinks()));

    AdminFactory admin = new AdminFactory();
    admin.setHealthChecks(new HealthCheckConfiguration());
    admin.setTasks(new TaskConfiguration());

    PublicApiConfig config = new PublicApiConfig();
    config.setAdminFactory(admin);
    config.setHealthFactory(mock(HealthFactory.class));
    config.setLoggingFactory(new DefaultLoggingFactory());
    config.setMetricsFactory(new MetricsFactory());
    config.setServerFactory(new DefaultServerFactory());

    AgreementsService agreementsService =
        new AgreementsService(connectorService, ledgerService, new PaginationDecorator(config));
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");
    AgreementSearchParams params =
        new AgreementSearchParams("Reference", "Status", "42", "Display Size");

    // Act
    AgreementSearchResults actualSearchAgreementsResult =
        agreementsService.searchAgreements(account, params);

    // Assert
    verify(ledgerService).searchAgreements(isA(Account.class), isA(AgreementSearchParams.class));
    SearchNavigationLinks links = actualSearchAgreementsResult.getLinks();
    Link firstPage = links.getFirstPage();
    assertNull(firstPage.getHref());
    assertNull(firstPage.getMethod());
    assertEquals(firstPage, links.getLastPage());
    assertEquals(firstPage, links.getNextPage());
    assertEquals(firstPage, links.getPrevPage());
    assertEquals(firstPage, links.getSelf());
  }

  /**
   * Test {@link AgreementsService#searchAgreements(Account, AgreementSearchParams)}.
   *
   * <ul>
   *   <li>Then return Links FirstPage is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AgreementsService#searchAgreements(Account,
   * AgreementSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AgreementSearchResults AgreementsService.searchAgreements(Account, AgreementSearchParams)"
  })
  public void testSearchAgreements_thenReturnLinksFirstPageIsNull() {
    // Arrange
    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    ConnectorService connectorService =
        new ConnectorService(mock(Client.class), new ConnectorUriGenerator(configuration));

    SearchResults<AgreementLedgerResponse> searchResults = mock(SearchResults.class);
    when(searchResults.getCount()).thenReturn(3);
    when(searchResults.getPage()).thenReturn(1);
    when(searchResults.getTotal()).thenReturn(1);
    when(searchResults.getResults()).thenReturn(new ArrayList<>());
    when(searchResults.getLinks()).thenReturn(mock(SearchNavigationLinks.class));

    LedgerService ledgerService = mock(LedgerService.class);
    when(ledgerService.searchAgreements(
            Mockito.<Account>any(), Mockito.<AgreementSearchParams>any()))
        .thenReturn(searchResults);

    PaginationDecorator paginationDecorator = mock(PaginationDecorator.class);
    when(paginationDecorator.transformLinksToPublicApiUri(
            Mockito.<SearchNavigationLinks>any(), Mockito.<String>any()))
        .thenReturn(new SearchNavigationLinks());

    AgreementsService agreementsService =
        new AgreementsService(connectorService, ledgerService, paginationDecorator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");
    AgreementSearchParams params =
        new AgreementSearchParams("Reference", "Status", "42", "Display Size");

    // Act
    AgreementSearchResults actualSearchAgreementsResult =
        agreementsService.searchAgreements(account, params);

    // Assert
    verify(searchResults).getCount();
    verify(searchResults).getLinks();
    verify(searchResults).getPage();
    verify(searchResults).getResults();
    verify(searchResults).getTotal();
    verify(paginationDecorator)
        .transformLinksToPublicApiUri(isA(SearchNavigationLinks.class), eq("/v1/agreements"));
    verify(ledgerService).searchAgreements(isA(Account.class), isA(AgreementSearchParams.class));
    SearchNavigationLinks links = actualSearchAgreementsResult.getLinks();
    assertNull(links.getFirstPage());
    assertNull(links.getLastPage());
    assertNull(links.getNextPage());
    assertNull(links.getPrevPage());
    assertNull(links.getSelf());
  }
}
