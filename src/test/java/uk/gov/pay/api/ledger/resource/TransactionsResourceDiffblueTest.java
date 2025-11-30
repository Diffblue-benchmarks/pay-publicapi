package uk.gov.pay.api.ledger.resource;

import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import uk.gov.pay.api.auth.Account;
import uk.gov.pay.api.ledger.model.SearchResults;
import uk.gov.pay.api.ledger.model.TransactionSearchParams;
import uk.gov.pay.api.ledger.service.TransactionSearchService;
import uk.gov.pay.api.model.TokenPaymentType;
import uk.gov.pay.api.model.search.card.PaymentForSearchResult;

public class TransactionsResourceDiffblueTest {
  /**
   * Test {@link TransactionsResource#getTransactions(Account, TransactionSearchParams)}.
   *
   * <ul>
   *   <li>Then return {@link SearchResults#SearchResults()}.
   * </ul>
   *
   * <p>Method under test: {@link TransactionsResource#getTransactions(Account,
   * TransactionSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SearchResults TransactionsResource.getTransactions(Account, TransactionSearchParams)"
  })
  public void testGetTransactions_thenReturnSearchResults() {
    // Arrange
    TransactionSearchService transactionSearchService = mock(TransactionSearchService.class);
    SearchResults<PaymentForSearchResult> searchResults = new SearchResults<>();
    when(transactionSearchService.doSearch(
            Mockito.<Account>any(), Mockito.<TransactionSearchParams>any()))
        .thenReturn(searchResults);
    TransactionsResource transactionsResource = new TransactionsResource(transactionSearchService);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    TransactionSearchParams searchParams = new TransactionSearchParams();
    searchParams.setAccountId("42");

    // Act
    SearchResults<PaymentForSearchResult> actualTransactions =
        transactionsResource.getTransactions(account, searchParams);

    // Assert
    verify(transactionSearchService)
        .doSearch(isA(Account.class), isA(TransactionSearchParams.class));
    assertSame(searchResults, actualTransactions);
  }
}
