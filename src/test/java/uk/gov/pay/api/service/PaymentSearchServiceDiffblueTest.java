package uk.gov.pay.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import black.door.hate.HalRepresentation;
import black.door.hate.HalRepresentation.HalRepresentationBuilder;
import black.door.hate.HalResource;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.dropwizard.core.server.DefaultServerFactory;
import io.dropwizard.core.setup.AdminFactory;
import io.dropwizard.core.setup.HealthCheckConfiguration;
import io.dropwizard.health.HealthFactory;
import io.dropwizard.logging.common.DefaultLoggingFactory;
import io.dropwizard.metrics.common.MetricsFactory;
import io.dropwizard.servlets.tasks.TaskConfiguration;
import jakarta.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Stream;
import org.glassfish.jersey.message.internal.OutboundJaxrsResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uk.gov.pay.api.app.config.PublicApiConfig;
import uk.gov.pay.api.auth.Account;
import uk.gov.pay.api.model.TokenPaymentType;
import uk.gov.pay.api.model.TransactionResponse;
import uk.gov.pay.api.model.links.SearchNavigationLinks;
import uk.gov.pay.api.model.search.PaginationDecorator;
import uk.gov.pay.api.model.search.SearchPagination;
import uk.gov.pay.api.model.search.card.PaymentSearchResponse;
import uk.gov.pay.api.service.PaymentSearchParams.Builder;

class PaymentSearchServiceDiffblueTest {
  /**
   * Test {@link PaymentSearchService#searchLedgerPayments(Account, PaymentSearchParams)}.
   *
   * <ul>
   *   <li>Then calls {@link HalRepresentation.HalRepresentationBuilder#addEmbedded(String,
   *       HalResource)}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentSearchService#searchLedgerPayments(Account,
   * PaymentSearchParams)}
   */
  @Test
  @DisplayName(
      "Test searchLedgerPayments(Account, PaymentSearchParams); then calls addEmbedded(String, HalResource)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Response PaymentSearchService.searchLedgerPayments(Account, PaymentSearchParams)"
  })
  void testSearchLedgerPayments_thenCallsAddEmbedded() {
    // Arrange
    AdminFactory admin = new AdminFactory();
    admin.setHealthChecks(new HealthCheckConfiguration());
    admin.setTasks(new TaskConfiguration());

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(admin);
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator = new PublicApiUriGenerator(configuration);

    HalRepresentationBuilder halRepresentationBuilder = mock(HalRepresentationBuilder.class);
    when(halRepresentationBuilder.build())
        .thenReturn(
            HalRepresentation.builder()
                .ignoreNullProperties(true)
                .ignoreNullResources(true)
                .build());
    when(halRepresentationBuilder.addEmbedded(Mockito.<String>any(), Mockito.<HalResource>any()))
        .thenReturn(HalRepresentation.builder());
    halRepresentationBuilder.addEmbedded(PaymentSearchParams.EMAIL_KEY, mock(HalResource.class));
    halRepresentationBuilder.addEmbedded(
        PaymentSearchParams.REFERENCE_KEY, mock(HalResource.class));

    PaginationDecorator paginationDecorator = mock(PaginationDecorator.class);
    when(paginationDecorator.decoratePagination(
            Mockito.<HalRepresentationBuilder>any(),
            Mockito.<SearchPagination>any(),
            Mockito.<String>any()))
        .thenReturn(halRepresentationBuilder);

    PaymentSearchResponse<TransactionResponse> paymentSearchResponse =
        mock(PaymentSearchResponse.class);
    when(paymentSearchResponse.getPayments()).thenReturn(new ArrayList<>());

    LedgerService ledgerService = mock(LedgerService.class);
    when(ledgerService.searchPayments(Mockito.<Account>any(), Mockito.<Map<String, String>>any()))
        .thenReturn(paymentSearchResponse);

    PaymentSearchService paymentSearchService =
        new PaymentSearchService(publicApiUriGenerator, paginationDecorator, ledgerService);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    // Act
    Response actualSearchLedgerPaymentsResult =
        paymentSearchService.searchLedgerPayments(account, new PaymentSearchParams(new Builder()));

    // Assert
    verify(halRepresentationBuilder, atLeast(1))
        .addEmbedded(Mockito.<String>any(), Mockito.<HalResource>any());
    verify(halRepresentationBuilder).build();
    verify(paginationDecorator)
        .decoratePagination(
            isA(HalRepresentationBuilder.class), isA(SearchPagination.class), eq("/v1/payments"));
    verify(paymentSearchResponse).getPayments();
    verify(ledgerService).searchPayments(isA(Account.class), isA(Map.class));
    assertTrue(actualSearchLedgerPaymentsResult instanceof OutboundJaxrsResponse);
    assertEquals("{}", actualSearchLedgerPaymentsResult.getEntity());
    assertEquals(
        "{}", ((OutboundJaxrsResponse) actualSearchLedgerPaymentsResult).getContext().getEntity());
    assertEquals(2, actualSearchLedgerPaymentsResult.getStringHeaders().size());
  }

  /**
   * Test {@link PaymentSearchService#searchLedgerPayments(Account, PaymentSearchParams)}.
   *
   * <ul>
   *   <li>Then return Entity is a string.
   * </ul>
   *
   * <p>Method under test: {@link PaymentSearchService#searchLedgerPayments(Account,
   * PaymentSearchParams)}
   */
  @Test
  @DisplayName(
      "Test searchLedgerPayments(Account, PaymentSearchParams); then return Entity is a string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Response PaymentSearchService.searchLedgerPayments(Account, PaymentSearchParams)"
  })
  void testSearchLedgerPayments_thenReturnEntityIsAString() throws URISyntaxException {
    // Arrange
    AdminFactory admin = new AdminFactory();
    admin.setHealthChecks(new HealthCheckConfiguration());
    admin.setTasks(new TaskConfiguration());

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(admin);
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator = new PublicApiUriGenerator(configuration);

    PaginationDecorator paginationDecorator = mock(PaginationDecorator.class);

    ArrayList<HalResource> halResourceList = new ArrayList<>();
    Stream<HalResource> stream = halResourceList.stream();
    HalRepresentationBuilder paginatedResult =
        HalRepresentation.paginated(
            PaymentSearchParams.REFERENCE_KEY, PaymentSearchParams.REFERENCE_KEY, stream, 1L, 3L);
    when(paginationDecorator.decoratePagination(
            Mockito.<HalRepresentationBuilder>any(),
            Mockito.<SearchPagination>any(),
            Mockito.<String>any()))
        .thenReturn(paginatedResult);

    PaymentSearchResponse<TransactionResponse> paymentSearchResponse =
        mock(PaymentSearchResponse.class);
    when(paymentSearchResponse.getPayments()).thenReturn(new ArrayList<>());

    LedgerService ledgerService = mock(LedgerService.class);
    when(ledgerService.searchPayments(Mockito.<Account>any(), Mockito.<Map<String, String>>any()))
        .thenReturn(paymentSearchResponse);

    PaymentSearchService paymentSearchService =
        new PaymentSearchService(publicApiUriGenerator, paginationDecorator, ledgerService);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    // Act
    Response actualSearchLedgerPaymentsResult =
        paymentSearchService.searchLedgerPayments(account, new PaymentSearchParams(new Builder()));

    // Assert
    verify(paginationDecorator)
        .decoratePagination(
            isA(HalRepresentationBuilder.class), isA(SearchPagination.class), eq("/v1/payments"));
    verify(paymentSearchResponse).getPayments();
    verify(ledgerService).searchPayments(isA(Account.class), isA(Map.class));
    assertTrue(actualSearchLedgerPaymentsResult instanceof OutboundJaxrsResponse);
    assertEquals(
        "{\"_links\":{\"next\":{\"href\":\"reference?page=2\"},\"self\":{\"href\":\"reference\"}},\"_embedded\":{\"reference"
            + "\":[]}}",
        actualSearchLedgerPaymentsResult.getEntity());
    assertEquals(
        "{\"_links\":{\"next\":{\"href\":\"reference?page=2\"},\"self\":{\"href\":\"reference\"}},\"_embedded\":{\"reference"
            + "\":[]}}",
        ((OutboundJaxrsResponse) actualSearchLedgerPaymentsResult).getContext().getEntity());
    assertEquals(2, actualSearchLedgerPaymentsResult.getStringHeaders().size());
  }

  /**
   * Test {@link PaymentSearchService#searchLedgerPayments(Account, PaymentSearchParams)}.
   *
   * <ul>
   *   <li>Then return Entity is {@code {"_embedded":{"reference":{}}}}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentSearchService#searchLedgerPayments(Account,
   * PaymentSearchParams)}
   */
  @Test
  @DisplayName(
      "Test searchLedgerPayments(Account, PaymentSearchParams); then return Entity is '{\"_embedded\":{\"reference\":{}}}'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Response PaymentSearchService.searchLedgerPayments(Account, PaymentSearchParams)"
  })
  void testSearchLedgerPayments_thenReturnEntityIsEmbeddedReference() {
    // Arrange
    AdminFactory admin = new AdminFactory();
    admin.setHealthChecks(new HealthCheckConfiguration());
    admin.setTasks(new TaskConfiguration());

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(admin);
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator = new PublicApiUriGenerator(configuration);

    HalResource link = mock(HalResource.class);
    when(link.asEmbedded(isA(String[].class)))
        .thenReturn(
            HalRepresentation.builder()
                .ignoreNullProperties(true)
                .ignoreNullResources(true)
                .build());

    HalRepresentationBuilder builderResult = HalRepresentation.builder();
    builderResult.addEmbedded(PaymentSearchParams.REFERENCE_KEY, link);

    PaginationDecorator paginationDecorator = mock(PaginationDecorator.class);
    when(paginationDecorator.decoratePagination(
            Mockito.<HalRepresentationBuilder>any(),
            Mockito.<SearchPagination>any(),
            Mockito.<String>any()))
        .thenReturn(builderResult);

    PaymentSearchResponse<TransactionResponse> paymentSearchResponse =
        mock(PaymentSearchResponse.class);
    when(paymentSearchResponse.getPayments()).thenReturn(new ArrayList<>());

    LedgerService ledgerService = mock(LedgerService.class);
    when(ledgerService.searchPayments(Mockito.<Account>any(), Mockito.<Map<String, String>>any()))
        .thenReturn(paymentSearchResponse);

    PaymentSearchService paymentSearchService =
        new PaymentSearchService(publicApiUriGenerator, paginationDecorator, ledgerService);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    // Act
    Response actualSearchLedgerPaymentsResult =
        paymentSearchService.searchLedgerPayments(account, new PaymentSearchParams(new Builder()));

    // Assert
    verify(link).asEmbedded(isA(String[].class));
    verify(paginationDecorator)
        .decoratePagination(
            isA(HalRepresentationBuilder.class), isA(SearchPagination.class), eq("/v1/payments"));
    verify(paymentSearchResponse).getPayments();
    verify(ledgerService).searchPayments(isA(Account.class), isA(Map.class));
    assertTrue(actualSearchLedgerPaymentsResult instanceof OutboundJaxrsResponse);
    assertEquals(
        "{\"_embedded\":{\"reference\":{}}}", actualSearchLedgerPaymentsResult.getEntity());
    assertEquals(
        "{\"_embedded\":{\"reference\":{}}}",
        ((OutboundJaxrsResponse) actualSearchLedgerPaymentsResult).getContext().getEntity());
    assertEquals(2, actualSearchLedgerPaymentsResult.getStringHeaders().size());
  }

  /**
   * Test {@link PaymentSearchService#searchLedgerPayments(Account, PaymentSearchParams)}.
   *
   * <ul>
   *   <li>Then return Entity is {@code {}}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentSearchService#searchLedgerPayments(Account,
   * PaymentSearchParams)}
   */
  @Test
  @DisplayName(
      "Test searchLedgerPayments(Account, PaymentSearchParams); then return Entity is '{}'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Response PaymentSearchService.searchLedgerPayments(Account, PaymentSearchParams)"
  })
  void testSearchLedgerPayments_thenReturnEntityIsLeftCurlyBracketRightCurlyBracket() {
    // Arrange
    AdminFactory admin = new AdminFactory();
    admin.setHealthChecks(new HealthCheckConfiguration());
    admin.setTasks(new TaskConfiguration());

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(admin);
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator = new PublicApiUriGenerator(configuration);

    PaginationDecorator paginationDecorator = mock(PaginationDecorator.class);
    when(paginationDecorator.decoratePagination(
            Mockito.<HalRepresentationBuilder>any(),
            Mockito.<SearchPagination>any(),
            Mockito.<String>any()))
        .thenReturn(HalRepresentation.builder());

    PaymentSearchResponse<TransactionResponse> paymentSearchResponse =
        mock(PaymentSearchResponse.class);
    when(paymentSearchResponse.getPayments()).thenReturn(new ArrayList<>());

    LedgerService ledgerService = mock(LedgerService.class);
    when(ledgerService.searchPayments(Mockito.<Account>any(), Mockito.<Map<String, String>>any()))
        .thenReturn(paymentSearchResponse);

    PaymentSearchService paymentSearchService =
        new PaymentSearchService(publicApiUriGenerator, paginationDecorator, ledgerService);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    // Act
    Response actualSearchLedgerPaymentsResult =
        paymentSearchService.searchLedgerPayments(account, new PaymentSearchParams(new Builder()));

    // Assert
    verify(paginationDecorator)
        .decoratePagination(
            isA(HalRepresentationBuilder.class), isA(SearchPagination.class), eq("/v1/payments"));
    verify(paymentSearchResponse).getPayments();
    verify(ledgerService).searchPayments(isA(Account.class), isA(Map.class));
    assertTrue(actualSearchLedgerPaymentsResult instanceof OutboundJaxrsResponse);
    assertEquals("{}", actualSearchLedgerPaymentsResult.getEntity());
    assertEquals(
        "{}", ((OutboundJaxrsResponse) actualSearchLedgerPaymentsResult).getContext().getEntity());
    assertEquals(2, actualSearchLedgerPaymentsResult.getStringHeaders().size());
  }

  /**
   * Test {@link PaymentSearchService#searchLedgerPayments(Account, PaymentSearchParams)}.
   *
   * <ul>
   *   <li>Then return Entity is {@code {"total":1,"count":3,"page":1,"results":[]}}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentSearchService#searchLedgerPayments(Account,
   * PaymentSearchParams)}
   */
  @Test
  @DisplayName(
      "Test searchLedgerPayments(Account, PaymentSearchParams); then return Entity is '{\"total\":1,\"count\":3,\"page\":1,\"results\":[]}'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Response PaymentSearchService.searchLedgerPayments(Account, PaymentSearchParams)"
  })
  void testSearchLedgerPayments_thenReturnEntityIsTotal1Count3Page1Results() {
    // Arrange
    AdminFactory admin = new AdminFactory();
    admin.setHealthChecks(new HealthCheckConfiguration());
    admin.setTasks(new TaskConfiguration());

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(admin);
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator = new PublicApiUriGenerator(configuration);

    AdminFactory admin2 = new AdminFactory();
    admin2.setHealthChecks(new HealthCheckConfiguration());
    admin2.setTasks(new TaskConfiguration());

    PublicApiConfig config = new PublicApiConfig();
    config.setAdminFactory(admin2);
    config.setHealthFactory(mock(HealthFactory.class));
    config.setLoggingFactory(new DefaultLoggingFactory());
    config.setMetricsFactory(new MetricsFactory());
    config.setServerFactory(new DefaultServerFactory());
    PaginationDecorator paginationDecorator = new PaginationDecorator(config);

    PaymentSearchResponse<TransactionResponse> paymentSearchResponse =
        mock(PaymentSearchResponse.class);
    when(paymentSearchResponse.getCount()).thenReturn(3);
    when(paymentSearchResponse.getPage()).thenReturn(1);
    when(paymentSearchResponse.getTotal()).thenReturn(1);
    when(paymentSearchResponse.getPayments()).thenReturn(new ArrayList<>());
    when(paymentSearchResponse.getLinks()).thenReturn(new SearchNavigationLinks());

    LedgerService ledgerService = mock(LedgerService.class);
    when(ledgerService.searchPayments(Mockito.<Account>any(), Mockito.<Map<String, String>>any()))
        .thenReturn(paymentSearchResponse);

    PaymentSearchService paymentSearchService =
        new PaymentSearchService(publicApiUriGenerator, paginationDecorator, ledgerService);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    // Act
    Response actualSearchLedgerPaymentsResult =
        paymentSearchService.searchLedgerPayments(account, new PaymentSearchParams(new Builder()));

    // Assert
    verify(paymentSearchResponse).getCount();
    verify(paymentSearchResponse).getLinks();
    verify(paymentSearchResponse).getPage();
    verify(paymentSearchResponse).getPayments();
    verify(paymentSearchResponse).getTotal();
    verify(ledgerService).searchPayments(isA(Account.class), isA(Map.class));
    assertTrue(actualSearchLedgerPaymentsResult instanceof OutboundJaxrsResponse);
    assertEquals(
        "{\"total\":1,\"count\":3,\"page\":1,\"results\":[]}",
        actualSearchLedgerPaymentsResult.getEntity());
    assertEquals(
        "{\"total\":1,\"count\":3,\"page\":1,\"results\":[]}",
        ((OutboundJaxrsResponse) actualSearchLedgerPaymentsResult).getContext().getEntity());
    assertEquals(2, actualSearchLedgerPaymentsResult.getStringHeaders().size());
  }
}
