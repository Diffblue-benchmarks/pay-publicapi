package uk.gov.pay.api.agreement.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.dropwizard.core.server.DefaultServerFactory;
import io.dropwizard.core.setup.AdminFactory;
import io.dropwizard.health.HealthFactory;
import io.dropwizard.logging.common.DefaultLoggingFactory;
import io.dropwizard.metrics.common.MetricsFactory;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.Invocation.Builder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import java.util.ArrayList;
import java.util.List;
import org.glassfish.jersey.message.internal.OutboundJaxrsResponse;
import org.glassfish.jersey.message.internal.OutboundMessageContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uk.gov.pay.api.agreement.model.Agreement;
import uk.gov.pay.api.agreement.model.AgreementCreatedResponse;
import uk.gov.pay.api.agreement.model.AgreementLedgerResponse;
import uk.gov.pay.api.agreement.model.AgreementSearchResults;
import uk.gov.pay.api.agreement.model.CreateAgreementRequest;
import uk.gov.pay.api.agreement.service.AgreementsService;
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

class AgreementsApiResourceDiffblueTest {
  /**
   * Test {@link AgreementsApiResource#createAgreement(Account, CreateAgreementRequest)}.
   *
   * <ul>
   *   <li>Then calls {@link ConnectorService#createAgreement(Account, CreateAgreementRequest)}.
   * </ul>
   *
   * <p>Method under test: {@link AgreementsApiResource#createAgreement(Account,
   * CreateAgreementRequest)}
   */
  @Test
  @DisplayName(
      "Test createAgreement(Account, CreateAgreementRequest); then calls createAgreement(Account, CreateAgreementRequest)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Response AgreementsApiResource.createAgreement(Account, CreateAgreementRequest)"
  })
  void testCreateAgreement_thenCallsCreateAgreement() {
    // Arrange
    ConnectorService connectorService = mock(ConnectorService.class);
    when(connectorService.createAgreement(
            Mockito.<Account>any(), Mockito.<CreateAgreementRequest>any()))
        .thenReturn(new AgreementCreatedResponse("42"));

    AgreementLedgerResponse agreementLedgerResponse = new AgreementLedgerResponse();
    agreementLedgerResponse.setExternalId("42");

    LedgerService ledgerService = mock(LedgerService.class);
    when(ledgerService.getAgreement(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(agreementLedgerResponse);

    PublicApiConfig config = new PublicApiConfig();
    config.setAdminFactory(new AdminFactory());
    config.setHealthFactory(mock(HealthFactory.class));
    config.setLoggingFactory(new DefaultLoggingFactory());
    config.setMetricsFactory(new MetricsFactory());
    config.setServerFactory(new DefaultServerFactory());

    AgreementsService agreementsService =
        new AgreementsService(connectorService, ledgerService, new PaginationDecorator(config));
    AgreementsApiResource agreementsApiResource = new AgreementsApiResource(agreementsService);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    // Act
    Response actualCreateAgreementResult =
        agreementsApiResource.createAgreement(account, new CreateAgreementRequest());

    // Assert
    verify(connectorService).createAgreement(isA(Account.class), isA(CreateAgreementRequest.class));
    verify(ledgerService).getAgreement(isA(Account.class), eq("42"));
    assertTrue(actualCreateAgreementResult instanceof OutboundJaxrsResponse);
  }

  /**
   * Test {@link AgreementsApiResource#createAgreement(Account, CreateAgreementRequest)}.
   *
   * <ul>
   *   <li>Then calls {@link AgreementsService#createAgreement(Account, CreateAgreementRequest)}.
   * </ul>
   *
   * <p>Method under test: {@link AgreementsApiResource#createAgreement(Account,
   * CreateAgreementRequest)}
   */
  @Test
  @DisplayName(
      "Test createAgreement(Account, CreateAgreementRequest); then calls createAgreement(Account, CreateAgreementRequest)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Response AgreementsApiResource.createAgreement(Account, CreateAgreementRequest)"
  })
  void testCreateAgreement_thenCallsCreateAgreement2() {
    // Arrange
    AgreementLedgerResponse agreementLedgerResponse = new AgreementLedgerResponse();
    agreementLedgerResponse.setExternalId("42");

    AgreementsService agreementsService = mock(AgreementsService.class);
    when(agreementsService.getAgreement(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(agreementLedgerResponse);
    when(agreementsService.createAgreement(
            Mockito.<Account>any(), Mockito.<CreateAgreementRequest>any()))
        .thenReturn(new AgreementCreatedResponse("42"));
    AgreementsApiResource agreementsApiResource = new AgreementsApiResource(agreementsService);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    // Act
    Response actualCreateAgreementResult =
        agreementsApiResource.createAgreement(account, new CreateAgreementRequest());

    // Assert
    verify(agreementsService)
        .createAgreement(isA(Account.class), isA(CreateAgreementRequest.class));
    verify(agreementsService).getAgreement(isA(Account.class), eq("42"));
    assertTrue(actualCreateAgreementResult instanceof OutboundJaxrsResponse);
  }

  /**
   * Test {@link AgreementsApiResource#getAgreement(Account, String)}.
   *
   * <ul>
   *   <li>Then calls {@link LedgerService#getAgreement(Account, String)}.
   * </ul>
   *
   * <p>Method under test: {@link AgreementsApiResource#getAgreement(Account, String)}
   */
  @Test
  @DisplayName("Test getAgreement(Account, String); then calls getAgreement(Account, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Agreement AgreementsApiResource.getAgreement(Account, String)"})
  void testGetAgreement_thenCallsGetAgreement() {
    // Arrange
    AgreementLedgerResponse agreementLedgerResponse = new AgreementLedgerResponse();
    agreementLedgerResponse.setExternalId("42");

    LedgerService ledgerService = mock(LedgerService.class);
    when(ledgerService.getAgreement(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(agreementLedgerResponse);

    PublicApiConfig config = new PublicApiConfig();
    config.setAdminFactory(new AdminFactory());
    config.setHealthFactory(mock(HealthFactory.class));
    config.setLoggingFactory(new DefaultLoggingFactory());
    config.setMetricsFactory(new MetricsFactory());
    config.setServerFactory(new DefaultServerFactory());
    PaginationDecorator paginationDecorator = new PaginationDecorator(config);
    Client client = mock(Client.class);
    ConnectorService connectorService =
        new ConnectorService(client, new ConnectorUriGenerator(new PublicApiConfig()));

    AgreementsService agreementsService =
        new AgreementsService(connectorService, ledgerService, paginationDecorator);
    AgreementsApiResource agreementsApiResource = new AgreementsApiResource(agreementsService);

    // Act
    Agreement actualAgreement =
        agreementsApiResource.getAgreement(
            new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(ledgerService).getAgreement(isA(Account.class), eq("42"));
    assertEquals("42", actualAgreement.getExternalId());
    assertNull(actualAgreement.getCancelledDate());
    assertNull(actualAgreement.getCreatedDate());
    assertNull(actualAgreement.getDescription());
    assertNull(actualAgreement.getReference());
    assertNull(actualAgreement.getStatus());
    assertNull(actualAgreement.getUserIdentifier());
    assertNull(actualAgreement.getPaymentInstrument());
  }

  /**
   * Test {@link AgreementsApiResource#getAgreement(Account, String)}.
   *
   * <ul>
   *   <li>Then calls {@link AgreementsService#getAgreement(Account, String)}.
   * </ul>
   *
   * <p>Method under test: {@link AgreementsApiResource#getAgreement(Account, String)}
   */
  @Test
  @DisplayName("Test getAgreement(Account, String); then calls getAgreement(Account, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Agreement AgreementsApiResource.getAgreement(Account, String)"})
  void testGetAgreement_thenCallsGetAgreement2() {
    // Arrange
    AgreementLedgerResponse agreementLedgerResponse = new AgreementLedgerResponse();
    agreementLedgerResponse.setExternalId("42");

    AgreementsService agreementsService = mock(AgreementsService.class);
    when(agreementsService.getAgreement(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(agreementLedgerResponse);
    AgreementsApiResource agreementsApiResource = new AgreementsApiResource(agreementsService);

    // Act
    Agreement actualAgreement =
        agreementsApiResource.getAgreement(
            new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(agreementsService).getAgreement(isA(Account.class), eq("42"));
    assertEquals("42", actualAgreement.getExternalId());
    assertNull(actualAgreement.getCancelledDate());
    assertNull(actualAgreement.getCreatedDate());
    assertNull(actualAgreement.getDescription());
    assertNull(actualAgreement.getReference());
    assertNull(actualAgreement.getStatus());
    assertNull(actualAgreement.getUserIdentifier());
    assertNull(actualAgreement.getPaymentInstrument());
  }

  /**
   * Test {@link AgreementsApiResource#getAgreements(Account, AgreementSearchParams)}.
   *
   * <p>Method under test: {@link AgreementsApiResource#getAgreements(Account,
   * AgreementSearchParams)}
   */
  @Test
  @DisplayName("Test getAgreements(Account, AgreementSearchParams)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AgreementSearchResults AgreementsApiResource.getAgreements(Account, AgreementSearchParams)"
  })
  void testGetAgreements() {
    // Arrange
    AgreementsService agreementsService = mock(AgreementsService.class);
    ArrayList<Agreement> results = new ArrayList<>();
    AgreementSearchResults agreementSearchResults =
        new AgreementSearchResults(1, 3, 1, results, new SearchNavigationLinks());
    when(agreementsService.searchAgreements(
            Mockito.<Account>any(), Mockito.<AgreementSearchParams>any()))
        .thenReturn(agreementSearchResults);
    AgreementsApiResource agreementsApiResource = new AgreementsApiResource(agreementsService);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");
    AgreementSearchParams searchParams =
        new AgreementSearchParams("Reference", "Status", "42", "Display Size");

    // Act
    AgreementSearchResults actualAgreements =
        agreementsApiResource.getAgreements(account, searchParams);

    // Assert
    verify(agreementsService)
        .searchAgreements(isA(Account.class), isA(AgreementSearchParams.class));
    assertSame(agreementSearchResults, actualAgreements);
  }

  /**
   * Test {@link AgreementsApiResource#getAgreements(Account, AgreementSearchParams)}.
   *
   * <ul>
   *   <li>Then return Links FirstPage Href is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AgreementsApiResource#getAgreements(Account,
   * AgreementSearchParams)}
   */
  @Test
  @DisplayName(
      "Test getAgreements(Account, AgreementSearchParams); then return Links FirstPage Href is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AgreementSearchResults AgreementsApiResource.getAgreements(Account, AgreementSearchParams)"
  })
  void testGetAgreements_thenReturnLinksFirstPageHrefIsNull() {
    // Arrange
    LedgerService ledgerService = mock(LedgerService.class);
    ArrayList<AgreementLedgerResponse> results = new ArrayList<>();
    when(ledgerService.searchAgreements(
            Mockito.<Account>any(), Mockito.<AgreementSearchParams>any()))
        .thenReturn(new SearchResults<>(1, 3, 1, results, new SearchNavigationLinks()));

    PublicApiConfig config = new PublicApiConfig();
    config.setAdminFactory(new AdminFactory());
    config.setHealthFactory(mock(HealthFactory.class));
    config.setLoggingFactory(new DefaultLoggingFactory());
    config.setMetricsFactory(new MetricsFactory());
    config.setServerFactory(new DefaultServerFactory());
    PaginationDecorator paginationDecorator = new PaginationDecorator(config);
    Client client = mock(Client.class);
    ConnectorService connectorService =
        new ConnectorService(client, new ConnectorUriGenerator(new PublicApiConfig()));

    AgreementsService agreementsService =
        new AgreementsService(connectorService, ledgerService, paginationDecorator);
    AgreementsApiResource agreementsApiResource = new AgreementsApiResource(agreementsService);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");
    AgreementSearchParams searchParams =
        new AgreementSearchParams("Reference", "Status", "42", "Display Size");

    // Act
    AgreementSearchResults actualAgreements =
        agreementsApiResource.getAgreements(account, searchParams);

    // Assert
    verify(ledgerService).searchAgreements(isA(Account.class), isA(AgreementSearchParams.class));
    SearchNavigationLinks links = actualAgreements.getLinks();
    Link firstPage = links.getFirstPage();
    assertNull(firstPage.getHref());
    assertNull(firstPage.getMethod());
    assertTrue(actualAgreements.getResults().isEmpty());
    assertEquals(firstPage, links.getLastPage());
    assertEquals(firstPage, links.getNextPage());
    assertEquals(firstPage, links.getPrevPage());
    assertEquals(firstPage, links.getSelf());
  }

  /**
   * Test {@link AgreementsApiResource#getAgreements(Account, AgreementSearchParams)}.
   *
   * <ul>
   *   <li>Then return Links FirstPage is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AgreementsApiResource#getAgreements(Account,
   * AgreementSearchParams)}
   */
  @Test
  @DisplayName(
      "Test getAgreements(Account, AgreementSearchParams); then return Links FirstPage is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AgreementSearchResults AgreementsApiResource.getAgreements(Account, AgreementSearchParams)"
  })
  void testGetAgreements_thenReturnLinksFirstPageIsNull() {
    // Arrange
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
    Client client = mock(Client.class);
    ConnectorService connectorService =
        new ConnectorService(client, new ConnectorUriGenerator(new PublicApiConfig()));

    AgreementsService agreementsService =
        new AgreementsService(connectorService, ledgerService, paginationDecorator);
    AgreementsApiResource agreementsApiResource = new AgreementsApiResource(agreementsService);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");
    AgreementSearchParams searchParams =
        new AgreementSearchParams("Reference", "Status", "42", "Display Size");

    // Act
    AgreementSearchResults actualAgreements =
        agreementsApiResource.getAgreements(account, searchParams);

    // Assert
    verify(searchResults).getCount();
    verify(searchResults).getLinks();
    verify(searchResults).getPage();
    verify(searchResults).getResults();
    verify(searchResults).getTotal();
    verify(paginationDecorator)
        .transformLinksToPublicApiUri(isA(SearchNavigationLinks.class), eq("/v1/agreements"));
    verify(ledgerService).searchAgreements(isA(Account.class), isA(AgreementSearchParams.class));
    SearchNavigationLinks links = actualAgreements.getLinks();
    assertNull(links.getFirstPage());
    assertNull(links.getLastPage());
    assertNull(links.getNextPage());
    assertNull(links.getPrevPage());
    assertNull(links.getSelf());
  }

  /**
   * Test {@link AgreementsApiResource#getAgreements(Account, AgreementSearchParams)}.
   *
   * <ul>
   *   <li>Then return Results size is one.
   * </ul>
   *
   * <p>Method under test: {@link AgreementsApiResource#getAgreements(Account,
   * AgreementSearchParams)}
   */
  @Test
  @DisplayName(
      "Test getAgreements(Account, AgreementSearchParams); then return Results size is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AgreementSearchResults AgreementsApiResource.getAgreements(Account, AgreementSearchParams)"
  })
  void testGetAgreements_thenReturnResultsSizeIsOne() {
    // Arrange
    AgreementLedgerResponse agreementLedgerResponse = new AgreementLedgerResponse();
    agreementLedgerResponse.setExternalId("42");

    ArrayList<AgreementLedgerResponse> agreementLedgerResponseList = new ArrayList<>();
    agreementLedgerResponseList.add(agreementLedgerResponse);

    SearchResults<AgreementLedgerResponse> searchResults = mock(SearchResults.class);
    when(searchResults.getCount()).thenReturn(3);
    when(searchResults.getPage()).thenReturn(1);
    when(searchResults.getTotal()).thenReturn(1);
    when(searchResults.getResults()).thenReturn(agreementLedgerResponseList);
    when(searchResults.getLinks()).thenReturn(new SearchNavigationLinks());

    LedgerService ledgerService = mock(LedgerService.class);
    when(ledgerService.searchAgreements(
            Mockito.<Account>any(), Mockito.<AgreementSearchParams>any()))
        .thenReturn(searchResults);

    PublicApiConfig config = new PublicApiConfig();
    config.setAdminFactory(new AdminFactory());
    config.setHealthFactory(mock(HealthFactory.class));
    config.setLoggingFactory(new DefaultLoggingFactory());
    config.setMetricsFactory(new MetricsFactory());
    config.setServerFactory(new DefaultServerFactory());
    PaginationDecorator paginationDecorator = new PaginationDecorator(config);
    Client client = mock(Client.class);
    ConnectorService connectorService =
        new ConnectorService(client, new ConnectorUriGenerator(new PublicApiConfig()));

    AgreementsService agreementsService =
        new AgreementsService(connectorService, ledgerService, paginationDecorator);
    AgreementsApiResource agreementsApiResource = new AgreementsApiResource(agreementsService);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");
    AgreementSearchParams searchParams =
        new AgreementSearchParams("Reference", "Status", "42", "Display Size");

    // Act
    AgreementSearchResults actualAgreements =
        agreementsApiResource.getAgreements(account, searchParams);

    // Assert
    verify(searchResults).getCount();
    verify(searchResults).getLinks();
    verify(searchResults).getPage();
    verify(searchResults).getResults();
    verify(searchResults).getTotal();
    verify(ledgerService).searchAgreements(isA(Account.class), isA(AgreementSearchParams.class));
    List<Agreement> results = actualAgreements.getResults();
    assertEquals(1, results.size());
    Agreement getResult = results.get(0);
    assertEquals("42", getResult.getExternalId());
    assertNull(getResult.getCancelledDate());
    assertNull(getResult.getCreatedDate());
    assertNull(getResult.getDescription());
    assertNull(getResult.getReference());
    assertNull(getResult.getStatus());
    assertNull(getResult.getUserIdentifier());
    assertNull(getResult.getPaymentInstrument());
  }

  /**
   * Test {@link AgreementsApiResource#getAgreements(Account, AgreementSearchParams)}.
   *
   * <ul>
   *   <li>Then return Results size is two.
   * </ul>
   *
   * <p>Method under test: {@link AgreementsApiResource#getAgreements(Account,
   * AgreementSearchParams)}
   */
  @Test
  @DisplayName(
      "Test getAgreements(Account, AgreementSearchParams); then return Results size is two")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AgreementSearchResults AgreementsApiResource.getAgreements(Account, AgreementSearchParams)"
  })
  void testGetAgreements_thenReturnResultsSizeIsTwo() {
    // Arrange
    AgreementLedgerResponse agreementLedgerResponse = new AgreementLedgerResponse();
    agreementLedgerResponse.setExternalId("42");

    AgreementLedgerResponse agreementLedgerResponse2 = new AgreementLedgerResponse();
    agreementLedgerResponse2.setExternalId("/v1/agreements");

    ArrayList<AgreementLedgerResponse> agreementLedgerResponseList = new ArrayList<>();
    agreementLedgerResponseList.add(agreementLedgerResponse2);
    agreementLedgerResponseList.add(agreementLedgerResponse);

    SearchResults<AgreementLedgerResponse> searchResults = mock(SearchResults.class);
    when(searchResults.getCount()).thenReturn(3);
    when(searchResults.getPage()).thenReturn(1);
    when(searchResults.getTotal()).thenReturn(1);
    when(searchResults.getResults()).thenReturn(agreementLedgerResponseList);
    when(searchResults.getLinks()).thenReturn(new SearchNavigationLinks());

    LedgerService ledgerService = mock(LedgerService.class);
    when(ledgerService.searchAgreements(
            Mockito.<Account>any(), Mockito.<AgreementSearchParams>any()))
        .thenReturn(searchResults);

    PublicApiConfig config = new PublicApiConfig();
    config.setAdminFactory(new AdminFactory());
    config.setHealthFactory(mock(HealthFactory.class));
    config.setLoggingFactory(new DefaultLoggingFactory());
    config.setMetricsFactory(new MetricsFactory());
    config.setServerFactory(new DefaultServerFactory());
    PaginationDecorator paginationDecorator = new PaginationDecorator(config);
    Client client = mock(Client.class);
    ConnectorService connectorService =
        new ConnectorService(client, new ConnectorUriGenerator(new PublicApiConfig()));

    AgreementsService agreementsService =
        new AgreementsService(connectorService, ledgerService, paginationDecorator);
    AgreementsApiResource agreementsApiResource = new AgreementsApiResource(agreementsService);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");
    AgreementSearchParams searchParams =
        new AgreementSearchParams("Reference", "Status", "42", "Display Size");

    // Act
    AgreementSearchResults actualAgreements =
        agreementsApiResource.getAgreements(account, searchParams);

    // Assert
    verify(searchResults).getCount();
    verify(searchResults).getLinks();
    verify(searchResults).getPage();
    verify(searchResults).getResults();
    verify(searchResults).getTotal();
    verify(ledgerService).searchAgreements(isA(Account.class), isA(AgreementSearchParams.class));
    List<Agreement> results = actualAgreements.getResults();
    assertEquals(2, results.size());
    assertEquals("/v1/agreements", results.get(0).getExternalId());
    Agreement getResult = results.get(1);
    assertEquals("42", getResult.getExternalId());
    assertNull(getResult.getCancelledDate());
    assertNull(getResult.getCreatedDate());
    assertNull(getResult.getDescription());
    assertNull(getResult.getReference());
    assertNull(getResult.getStatus());
    assertNull(getResult.getUserIdentifier());
    assertNull(getResult.getPaymentInstrument());
  }

  /**
   * Test {@link AgreementsApiResource#cancelAgreement(Account, String)}.
   *
   * <ul>
   *   <li>Then calls {@link ConnectorService#cancelAgreement(Account, String)}.
   * </ul>
   *
   * <p>Method under test: {@link AgreementsApiResource#cancelAgreement(Account, String)}
   */
  @Test
  @DisplayName("Test cancelAgreement(Account, String); then calls cancelAgreement(Account, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Response AgreementsApiResource.cancelAgreement(Account, String)"})
  void testCancelAgreement_thenCallsCancelAgreement() {
    // Arrange
    ConnectorService connectorService = mock(ConnectorService.class);
    doNothing()
        .when(connectorService)
        .cancelAgreement(Mockito.<Account>any(), Mockito.<String>any());

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

    AgreementsService agreementsService =
        new AgreementsService(connectorService, ledgerService, paginationDecorator);
    AgreementsApiResource agreementsApiResource = new AgreementsApiResource(agreementsService);

    // Act
    Response actualCancelAgreementResult =
        agreementsApiResource.cancelAgreement(
            new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(connectorService).cancelAgreement(isA(Account.class), eq("42"));
    assertTrue(actualCancelAgreementResult instanceof OutboundJaxrsResponse);
  }

  /**
   * Test {@link AgreementsApiResource#cancelAgreement(Account, String)}.
   *
   * <ul>
   *   <li>Then calls {@link AgreementsService#cancelAgreement(Account, String)}.
   * </ul>
   *
   * <p>Method under test: {@link AgreementsApiResource#cancelAgreement(Account, String)}
   */
  @Test
  @DisplayName("Test cancelAgreement(Account, String); then calls cancelAgreement(Account, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Response AgreementsApiResource.cancelAgreement(Account, String)"})
  void testCancelAgreement_thenCallsCancelAgreement2() {
    // Arrange
    AgreementsService agreementsService = mock(AgreementsService.class);
    OutboundJaxrsResponse outboundJaxrsResponse =
        new OutboundJaxrsResponse(null, new OutboundMessageContext());
    when(agreementsService.cancelAgreement(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(outboundJaxrsResponse);
    AgreementsApiResource agreementsApiResource = new AgreementsApiResource(agreementsService);

    // Act
    Response actualCancelAgreementResult =
        agreementsApiResource.cancelAgreement(
            new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(agreementsService).cancelAgreement(isA(Account.class), eq("42"));
    assertTrue(actualCancelAgreementResult instanceof OutboundJaxrsResponse);
  }

  /**
   * Test {@link AgreementsApiResource#cancelAgreement(Account, String)}.
   *
   * <ul>
   *   <li>Then calls {@link Client#target(String)}.
   * </ul>
   *
   * <p>Method under test: {@link AgreementsApiResource#cancelAgreement(Account, String)}
   */
  @Test
  @DisplayName("Test cancelAgreement(Account, String); then calls target(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Response AgreementsApiResource.cancelAgreement(Account, String)"})
  void testCancelAgreement_thenCallsTarget() {
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

    PublicApiConfig config = new PublicApiConfig();
    config.setAdminFactory(new AdminFactory());
    config.setHealthFactory(mock(HealthFactory.class));
    config.setLoggingFactory(new DefaultLoggingFactory());
    config.setMetricsFactory(new MetricsFactory());
    config.setServerFactory(new DefaultServerFactory());
    PaginationDecorator paginationDecorator = new PaginationDecorator(config);
    Client client2 = mock(Client.class);
    LedgerService ledgerService =
        new LedgerService(client2, new LedgerUriGenerator(new PublicApiConfig()));

    AgreementsService agreementsService =
        new AgreementsService(connectorService, ledgerService, paginationDecorator);
    AgreementsApiResource agreementsApiResource = new AgreementsApiResource(agreementsService);

    // Act
    Response actualCancelAgreementResult =
        agreementsApiResource.cancelAgreement(
            new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(client).target("Cancel Agreement URI");
    verify(builder).post(isNull());
    verify(webTarget).request();
    verify(connectorUriGenerator).cancelAgreementURI(isA(Account.class), eq("42"));
    assertTrue(actualCancelAgreementResult instanceof OutboundJaxrsResponse);
  }
}
