package uk.gov.pay.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uk.gov.pay.api.app.config.PublicApiConfig;
import uk.gov.pay.api.auth.Account;
import uk.gov.pay.api.model.TokenPaymentType;
import uk.gov.pay.api.model.ledger.SearchDisputesResponseFromLedger;
import uk.gov.pay.api.model.links.SearchNavigationLinks;
import uk.gov.pay.api.model.search.PaginationDecorator;
import uk.gov.pay.api.model.search.dispute.DisputesSearchResults;

class SearchDisputesServiceDiffblueTest {
  /**
   * Test {@link SearchDisputesService#searchDisputes(Account, DisputesSearchParams)}.
   *
   * <ul>
   *   <li>Then return Page is one.
   * </ul>
   *
   * <p>Method under test: {@link SearchDisputesService#searchDisputes(Account,
   * DisputesSearchParams)}
   */
  @Test
  @DisplayName("Test searchDisputes(Account, DisputesSearchParams); then return Page is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DisputesSearchResults SearchDisputesService.searchDisputes(Account, DisputesSearchParams)"
  })
  void testSearchDisputes_thenReturnPageIsOne() {
    // Arrange
    SearchDisputesResponseFromLedger searchDisputesResponseFromLedger =
        mock(SearchDisputesResponseFromLedger.class);
    when(searchDisputesResponseFromLedger.getCount()).thenReturn(3);
    when(searchDisputesResponseFromLedger.getPage()).thenReturn(1);
    when(searchDisputesResponseFromLedger.getTotal()).thenReturn(1);
    when(searchDisputesResponseFromLedger.getDisputes()).thenReturn(new ArrayList<>());
    SearchNavigationLinks searchNavigationLinks = new SearchNavigationLinks();
    when(searchDisputesResponseFromLedger.getLinks()).thenReturn(searchNavigationLinks);

    LedgerService ledgerService = mock(LedgerService.class);
    when(ledgerService.searchDisputes(Mockito.<Account>any(), Mockito.<Map<String, String>>any()))
        .thenReturn(searchDisputesResponseFromLedger);

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

    SearchDisputesService searchDisputesService =
        new SearchDisputesService(
            ledgerService, publicApiUriGenerator, new PaginationDecorator(config));
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    HashMap<String, String> stringStringMap = new HashMap<>();
    stringStringMap.put("account_id", "Params As Map");

    DisputesSearchParams params = mock(DisputesSearchParams.class);
    when(params.getParamsAsMap()).thenReturn(stringStringMap);

    // Act
    DisputesSearchResults actualSearchDisputesResult =
        searchDisputesService.searchDisputes(account, params);

    // Assert
    verify(searchDisputesResponseFromLedger).getCount();
    verify(searchDisputesResponseFromLedger).getDisputes();
    verify(searchDisputesResponseFromLedger, atLeast(1)).getLinks();
    verify(searchDisputesResponseFromLedger).getPage();
    verify(searchDisputesResponseFromLedger).getTotal();
    verify(params).getParamsAsMap();
    verify(ledgerService).searchDisputes(isA(Account.class), isA(Map.class));
    assertEquals(1, actualSearchDisputesResult.getPage());
    assertEquals(1, actualSearchDisputesResult.getTotal());
    assertEquals(3, actualSearchDisputesResult.getCount());
    assertTrue(actualSearchDisputesResult.getResults().isEmpty());
    assertSame(searchNavigationLinks, actualSearchDisputesResult.getLinks());
  }
}
