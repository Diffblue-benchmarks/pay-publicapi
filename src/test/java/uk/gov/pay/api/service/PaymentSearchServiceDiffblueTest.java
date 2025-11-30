package uk.gov.pay.api.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import black.door.hate.HalLink;
import black.door.hate.HalRepresentation;
import black.door.hate.HalRepresentation.HalRepresentationBuilder;
import black.door.hate.HalResource;
import black.door.hate.LinkOrResource;
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
import jakarta.ws.rs.core.Response;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import org.glassfish.jersey.message.internal.OutboundJaxrsResponse;
import org.junit.Test;
import org.junit.experimental.categories.Category;
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

public class PaymentSearchServiceDiffblueTest {
  /**
   * Test {@link PaymentSearchService#searchLedgerPayments(Account, PaymentSearchParams)}.
   *
   * <p>Method under test: {@link PaymentSearchService#searchLedgerPayments(Account,
   * PaymentSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Response PaymentSearchService.searchLedgerPayments(Account, PaymentSearchParams)"
  })
  public void testSearchLedgerPayments() throws MalformedURLException {
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

    HalResource link2 = mock(HalResource.class);
    when(link2.asEmbedded(isA(String[].class)))
        .thenReturn(
            HalRepresentation.builder()
                .ignoreNullProperties(true)
                .ignoreNullResources(true)
                .build());

    HalResource link3 = mock(HalResource.class);
    when(link3.asEmbedded(isA(String[].class)))
        .thenReturn(
            HalRepresentation.builder()
                .ignoreNullProperties(true)
                .ignoreNullResources(true)
                .build());

    HalResource link4 = mock(HalResource.class);
    when(link4.asEmbedded(isA(String[].class)))
        .thenReturn(
            HalRepresentation.builder()
                .ignoreNullProperties(true)
                .ignoreNullResources(true)
                .build());

    LinkOrResource link5 = mock(LinkOrResource.class);
    when(link5.asLink())
        .thenReturn(
            HalLink.builder()
                .deprecation(
                    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL())
                .hreflang("Hreflang")
                .name("Name")
                .profile(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
                .title("Dr")
                .type("Type")
                .build());

    HalRepresentationBuilder builderResult = HalRepresentation.builder();
    builderResult.addLink("42", link5);
    builderResult.addEmbedded("Name", link4);
    builderResult.addEmbedded("", link3);
    builderResult.addEmbedded("42", link2);
    builderResult.addEmbedded(PaymentSearchParams.REFERENCE_KEY, link);

    HalRepresentationBuilder halRepresentationBuilder = mock(HalRepresentationBuilder.class);
    when(halRepresentationBuilder.build())
        .thenReturn(builderResult.ignoreNullProperties(true).ignoreNullResources(true).build());
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
    verify(link4).asEmbedded(isA(String[].class));
    verify(link3).asEmbedded(isA(String[].class));
    verify(link2).asEmbedded(isA(String[].class));
    verify(link).asEmbedded(isA(String[].class));
    verify(link5).asLink();
    verify(paginationDecorator)
        .decoratePagination(
            isA(HalRepresentationBuilder.class), isA(SearchPagination.class), eq("/v1/payments"));
    verify(paymentSearchResponse).getPayments();
    verify(ledgerService).searchPayments(isA(Account.class), isA(Map.class));
    assertTrue(actualSearchLedgerPaymentsResult instanceof OutboundJaxrsResponse);
    assertEquals(2, actualSearchLedgerPaymentsResult.getStringHeaders().size());
    String expectedString =
        String.join(
            "",
            "{\"_links\":{\"42\":{\"href\":null,\"type\":\"Type\",\"deprecation\":\"file:",
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toString(),
            "\",\"name\":\"Name\",\"profile\":\"",
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
            "\",\"title\":\"Dr\",\"hreflang\":\"Hreflang\"}},\"_embedded\":{\"\":{},\"reference\":{},\"42\":{},\"Name\":{}}}");
    assertEquals(expectedString, actualSearchLedgerPaymentsResult.getEntity());
    String expectedString2 =
        String.join(
            "",
            "{\"_links\":{\"42\":{\"href\":null,\"type\":\"Type\",\"deprecation\":\"file:",
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toString(),
            "\",\"name\":\"Name\",\"profile\":\"",
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
            "\",\"title\":\"Dr\",\"hreflang\":\"Hreflang\"}},\"_embedded\":{\"\":{},\"reference\":{},\"42\":{},\"Name\":{}}}");
    assertEquals(
        expectedString2,
        ((OutboundJaxrsResponse) actualSearchLedgerPaymentsResult).getContext().getEntity());
  }

  /**
   * Test {@link PaymentSearchService#searchLedgerPayments(Account, PaymentSearchParams)}.
   *
   * <p>Method under test: {@link PaymentSearchService#searchLedgerPayments(Account,
   * PaymentSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Response PaymentSearchService.searchLedgerPayments(Account, PaymentSearchParams)"
  })
  public void testSearchLedgerPayments2() throws MalformedURLException {
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

    HalResource link2 = mock(HalResource.class);
    when(link2.asEmbedded(isA(String[].class)))
        .thenReturn(
            HalRepresentation.builder()
                .ignoreNullProperties(true)
                .ignoreNullResources(true)
                .build());

    HalResource link3 = mock(HalResource.class);
    when(link3.asEmbedded(isA(String[].class)))
        .thenReturn(
            HalRepresentation.builder()
                .ignoreNullProperties(true)
                .ignoreNullResources(true)
                .build());

    HalResource link4 = mock(HalResource.class);
    when(link4.asEmbedded(isA(String[].class)))
        .thenReturn(
            HalRepresentation.builder()
                .ignoreNullProperties(true)
                .ignoreNullResources(true)
                .build());

    LinkOrResource link5 = mock(LinkOrResource.class);
    when(link5.asLink())
        .thenReturn(
            HalLink.builder()
                .deprecation(
                    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL())
                .hreflang("Hreflang")
                .name("Name")
                .profile(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
                .title("Dr")
                .type("Type")
                .build());

    HalResource link6 = mock(HalResource.class);
    when(link6.asEmbedded(isA(String[].class)))
        .thenReturn(
            HalRepresentation.builder()
                .ignoreNullProperties(true)
                .ignoreNullResources(true)
                .build());

    LinkOrResource link7 = mock(LinkOrResource.class);
    when(link7.asLink())
        .thenReturn(
            HalLink.builder()
                .deprecation(
                    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL())
                .hreflang("Hreflang")
                .name("Name")
                .profile(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
                .title("Dr")
                .type("Type")
                .build());

    HalRepresentationBuilder builderResult = HalRepresentation.builder();
    builderResult.addLink(
        "42", Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    builderResult.addLink("Name", link7);
    builderResult.addEmbedded(PaymentSearchParams.STATE_KEY, link6);
    builderResult.addLink("42", link5);
    builderResult.addEmbedded("Name", link4);
    builderResult.addEmbedded("", link3);
    builderResult.addEmbedded("42", link2);
    builderResult.addEmbedded(PaymentSearchParams.REFERENCE_KEY, link);

    HalRepresentationBuilder halRepresentationBuilder = mock(HalRepresentationBuilder.class);
    when(halRepresentationBuilder.build())
        .thenReturn(builderResult.ignoreNullProperties(true).ignoreNullResources(true).build());
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
    verify(link6).asEmbedded(isA(String[].class));
    verify(link4).asEmbedded(isA(String[].class));
    verify(link3).asEmbedded(isA(String[].class));
    verify(link2).asEmbedded(isA(String[].class));
    verify(link).asEmbedded(isA(String[].class));
    verify(link7).asLink();
    verify(link5).asLink();
    verify(paginationDecorator)
        .decoratePagination(
            isA(HalRepresentationBuilder.class), isA(SearchPagination.class), eq("/v1/payments"));
    verify(paymentSearchResponse).getPayments();
    verify(ledgerService).searchPayments(isA(Account.class), isA(Map.class));
    assertTrue(actualSearchLedgerPaymentsResult instanceof OutboundJaxrsResponse);
    assertEquals(2, actualSearchLedgerPaymentsResult.getStringHeaders().size());
    String expectedString =
        String.join(
            "",
            "{\"_links\":{\"42\":[{\"href\":\"",
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
            "\"},{\"href\":null,\"type\":\"Type\",\"deprecation\":\"file:",
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toString(),
            "\",\"name\":\"Name\",\"profile\":\"",
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
            "\",\"title\":\"Dr\",\"hreflang\":\"Hreflang\"}],\"Name\":{\"href\":null,\"type\":\"Type\",\"deprecation\":\"file:",
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toString(),
            "\",\"name\":\"Name\",\"profile\":\"",
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
            "\",\"title\":\"Dr\",\"hreflang\":\"Hreflang\"}},\"_embedded\":{\"\":{},\"reference\":{},\"state\":{},\"42\":{},"
                + "\"Name\":{}}}");
    assertEquals(expectedString, actualSearchLedgerPaymentsResult.getEntity());
    String expectedString2 =
        String.join(
            "",
            "{\"_links\":{\"42\":[{\"href\":\"",
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
            "\"},{\"href\":null,\"type\":\"Type\",\"deprecation\":\"file:",
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toString(),
            "\",\"name\":\"Name\",\"profile\":\"",
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
            "\",\"title\":\"Dr\",\"hreflang\":\"Hreflang\"}],\"Name\":{\"href\":null,\"type\":\"Type\",\"deprecation\":\"file:",
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toString(),
            "\",\"name\":\"Name\",\"profile\":\"",
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
            "\",\"title\":\"Dr\",\"hreflang\":\"Hreflang\"}},\"_embedded\":{\"\":{},\"reference\":{},\"state\":{},\"42\":{},"
                + "\"Name\":{}}}");
    assertEquals(
        expectedString2,
        ((OutboundJaxrsResponse) actualSearchLedgerPaymentsResult).getContext().getEntity());
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Response PaymentSearchService.searchLedgerPayments(Account, PaymentSearchParams)"
  })
  public void testSearchLedgerPayments_thenReturnEntityIsEmbeddedReference() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Response PaymentSearchService.searchLedgerPayments(Account, PaymentSearchParams)"
  })
  public void testSearchLedgerPayments_thenReturnEntityIsLeftCurlyBracketRightCurlyBracket() {
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
   *   <li>Then return Entity is {@code {}}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentSearchService#searchLedgerPayments(Account,
   * PaymentSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Response PaymentSearchService.searchLedgerPayments(Account, PaymentSearchParams)"
  })
  public void testSearchLedgerPayments_thenReturnEntityIsLeftCurlyBracketRightCurlyBracket2() {
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
   *   <li>Then return Entity is {@code {"total":1,"count":3,"page":1,"results":[]}}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentSearchService#searchLedgerPayments(Account,
   * PaymentSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Response PaymentSearchService.searchLedgerPayments(Account, PaymentSearchParams)"
  })
  public void testSearchLedgerPayments_thenReturnEntityIsTotal1Count3Page1Results() {
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
