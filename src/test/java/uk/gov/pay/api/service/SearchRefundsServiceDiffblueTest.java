package uk.gov.pay.api.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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
import java.util.ArrayList;
import java.util.Map;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import uk.gov.pay.api.app.config.PublicApiConfig;
import uk.gov.pay.api.auth.Account;
import uk.gov.pay.api.model.TokenPaymentType;
import uk.gov.pay.api.model.ledger.SearchRefundsResponseFromLedger;
import uk.gov.pay.api.model.links.Link;
import uk.gov.pay.api.model.links.SearchNavigationLinks;
import uk.gov.pay.api.model.search.PaginationDecorator;
import uk.gov.pay.api.model.search.card.SearchRefundsResults;

public class SearchRefundsServiceDiffblueTest {
  /**
   * Test {@link SearchRefundsService#searchLedgerRefunds(Account, RefundsParams)}.
   *
   * <ul>
   *   <li>Then return Links FirstPage Href is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SearchRefundsService#searchLedgerRefunds(Account, RefundsParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SearchRefundsResults SearchRefundsService.searchLedgerRefunds(Account, RefundsParams)"
  })
  public void testSearchLedgerRefunds_thenReturnLinksFirstPageHrefIsNull() {
    // Arrange
    SearchRefundsResponseFromLedger searchRefundsResponseFromLedger =
        mock(SearchRefundsResponseFromLedger.class);
    when(searchRefundsResponseFromLedger.getCount()).thenReturn(3);
    when(searchRefundsResponseFromLedger.getPage()).thenReturn(1);
    when(searchRefundsResponseFromLedger.getTotal()).thenReturn(1);
    when(searchRefundsResponseFromLedger.getRefunds()).thenReturn(new ArrayList<>());
    when(searchRefundsResponseFromLedger.getLinks()).thenReturn(new SearchNavigationLinks());

    LedgerService ledgerService = mock(LedgerService.class);
    when(ledgerService.searchRefunds(Mockito.<Account>any(), Mockito.<Map<String, String>>any()))
        .thenReturn(searchRefundsResponseFromLedger);

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

    SearchRefundsService searchRefundsService =
        new SearchRefundsService(
            ledgerService, publicApiUriGenerator, new PaginationDecorator(config));
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");
    RefundsParams params =
        new RefundsParams(
            "2020-03-01", "2020-03-01", "Page", "Display Size", "2020-03-01", "2020-03-01");

    // Act
    SearchRefundsResults actualSearchLedgerRefundsResult =
        searchRefundsService.searchLedgerRefunds(account, params);

    // Assert
    verify(searchRefundsResponseFromLedger).getCount();
    verify(searchRefundsResponseFromLedger).getLinks();
    verify(searchRefundsResponseFromLedger).getPage();
    verify(searchRefundsResponseFromLedger).getRefunds();
    verify(searchRefundsResponseFromLedger).getTotal();
    verify(ledgerService).searchRefunds(isA(Account.class), isA(Map.class));
    SearchNavigationLinks links = actualSearchLedgerRefundsResult.getLinks();
    Link firstPage = links.getFirstPage();
    assertNull(firstPage.getHref());
    assertNull(firstPage.getMethod());
    assertEquals(firstPage, links.getLastPage());
    assertEquals(firstPage, links.getNextPage());
    assertEquals(firstPage, links.getPrevPage());
    assertEquals(firstPage, links.getSelf());
  }

  /**
   * Test {@link SearchRefundsService#searchLedgerRefunds(Account, RefundsParams)}.
   *
   * <ul>
   *   <li>Then return Links FirstPage is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SearchRefundsService#searchLedgerRefunds(Account, RefundsParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SearchRefundsResults SearchRefundsService.searchLedgerRefunds(Account, RefundsParams)"
  })
  public void testSearchLedgerRefunds_thenReturnLinksFirstPageIsNull() {
    // Arrange
    SearchRefundsResponseFromLedger searchRefundsResponseFromLedger =
        mock(SearchRefundsResponseFromLedger.class);
    when(searchRefundsResponseFromLedger.getCount()).thenReturn(3);
    when(searchRefundsResponseFromLedger.getPage()).thenReturn(1);
    when(searchRefundsResponseFromLedger.getTotal()).thenReturn(1);
    when(searchRefundsResponseFromLedger.getRefunds()).thenReturn(new ArrayList<>());
    when(searchRefundsResponseFromLedger.getLinks()).thenReturn(new SearchNavigationLinks());

    LedgerService ledgerService = mock(LedgerService.class);
    when(ledgerService.searchRefunds(Mockito.<Account>any(), Mockito.<Map<String, String>>any()))
        .thenReturn(searchRefundsResponseFromLedger);

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
    when(paginationDecorator.transformLinksToPublicApiUri(
            Mockito.<SearchNavigationLinks>any(), Mockito.<String>any()))
        .thenReturn(new SearchNavigationLinks());

    SearchRefundsService searchRefundsService =
        new SearchRefundsService(ledgerService, publicApiUriGenerator, paginationDecorator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");
    RefundsParams params =
        new RefundsParams(
            "2020-03-01", "2020-03-01", "Page", "Display Size", "2020-03-01", "2020-03-01");

    // Act
    SearchRefundsResults actualSearchLedgerRefundsResult =
        searchRefundsService.searchLedgerRefunds(account, params);

    // Assert
    verify(searchRefundsResponseFromLedger).getCount();
    verify(searchRefundsResponseFromLedger).getLinks();
    verify(searchRefundsResponseFromLedger).getPage();
    verify(searchRefundsResponseFromLedger).getRefunds();
    verify(searchRefundsResponseFromLedger).getTotal();
    verify(paginationDecorator)
        .transformLinksToPublicApiUri(isA(SearchNavigationLinks.class), eq("/v1/refunds"));
    verify(ledgerService).searchRefunds(isA(Account.class), isA(Map.class));
    SearchNavigationLinks links = actualSearchLedgerRefundsResult.getLinks();
    assertNull(links.getFirstPage());
    assertNull(links.getLastPage());
    assertNull(links.getNextPage());
    assertNull(links.getPrevPage());
    assertNull(links.getSelf());
  }
}
