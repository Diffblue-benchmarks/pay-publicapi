package uk.gov.pay.api.ledger.resource;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uk.gov.pay.api.auth.Account;
import uk.gov.pay.api.ledger.model.SearchResults;
import uk.gov.pay.api.ledger.model.TransactionSearchParams;
import uk.gov.pay.api.ledger.service.TransactionSearchService;
import uk.gov.pay.api.model.TokenPaymentType;
import uk.gov.pay.api.model.search.card.PaymentForSearchResult;

class TransactionsResourceDiffblueTest {
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
  @DisplayName(
      "Test getTransactions(Account, TransactionSearchParams); then return SearchResults()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SearchResults TransactionsResource.getTransactions(Account, TransactionSearchParams)"
  })
  void testGetTransactions_thenReturnSearchResults() {
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
