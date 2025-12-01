package uk.gov.pay.api.model.search.dispute;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.net.URI;
import java.nio.file.Paths;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.model.ledger.DisputeSettlementSummary;
import uk.gov.pay.api.model.ledger.DisputeTransactionFromLedger;
import uk.gov.pay.api.model.ledger.TransactionState;
import uk.gov.pay.api.model.links.Link;

class DisputeForSearchResultDiffblueTest {
  /**
   * Test {@link DisputeForSearchResult#DisputeForSearchResult(Long, String, String, String, Long,
   * Long, String, String, DisputeSettlementSummary, String, URI)}.
   *
   * <p>Method under test: {@link DisputeForSearchResult#DisputeForSearchResult(Long, String,
   * String, String, Long, Long, String, String, DisputeSettlementSummary, String, URI)}
   */
  @Test
  @DisplayName(
      "Test new DisputeForSearchResult(Long, String, String, String, Long, Long, String, String, DisputeSettlementSummary, String, URI)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DisputeForSearchResult.<init>(Long, String, String, String, Long, Long, String, String, DisputeSettlementSummary, String, URI)"
  })
  void testNewDisputeForSearchResult() {
    // Arrange
    DisputeSettlementSummary settlementSummary = new DisputeSettlementSummary("2020-03-01");

    // Act
    DisputeForSearchResult actualDisputeForSearchResult =
        new DisputeForSearchResult(
            10L,
            "2020-03-01",
            "42",
            "2020-03-01",
            1L,
            1L,
            "42",
            "Just cause",
            settlementSummary,
            "Status",
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    // Assert
    assertEquals("2020-03-01", actualDisputeForSearchResult.getCreatedDate());
    assertEquals("2020-03-01", actualDisputeForSearchResult.getEvidenceDueDate());
    assertEquals("42", actualDisputeForSearchResult.getDisputeId());
    assertEquals("42", actualDisputeForSearchResult.getPaymentId());
    Link payment = actualDisputeForSearchResult.getLinks().getPayment();
    assertEquals("GET", payment.getMethod());
    assertEquals("Just cause", actualDisputeForSearchResult.getReason());
    assertEquals("Status", actualDisputeForSearchResult.getStatus());
    assertEquals(10L, actualDisputeForSearchResult.getAmount().longValue());
    assertEquals(1L, actualDisputeForSearchResult.getFee().longValue());
    assertEquals(1L, actualDisputeForSearchResult.getNetAmount().longValue());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        payment.getHref());
    assertSame(settlementSummary, actualDisputeForSearchResult.getSettlementSummary());
  }

  /**
   * Test {@link DisputeForSearchResult#valueOf(DisputeTransactionFromLedger, URI)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then return SettlementSummary SettledDate is {@code 2020-03-01}.
   * </ul>
   *
   * <p>Method under test: {@link DisputeForSearchResult#valueOf(DisputeTransactionFromLedger, URI)}
   */
  @Test
  @DisplayName(
      "Test valueOf(DisputeTransactionFromLedger, URI); given ten; then return SettlementSummary SettledDate is '2020-03-01'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DisputeForSearchResult DisputeForSearchResult.valueOf(DisputeTransactionFromLedger, URI)"
  })
  void testValueOf_givenTen_thenReturnSettlementSummarySettledDateIs20200301() {
    // Arrange
    DisputeTransactionFromLedger fromLedger = mock(DisputeTransactionFromLedger.class);
    when(fromLedger.getAmount()).thenReturn(10L);
    when(fromLedger.getFee()).thenReturn(1L);
    when(fromLedger.getNetAmount()).thenReturn(1L);
    when(fromLedger.getCreatedDate()).thenReturn("2020-03-01");
    when(fromLedger.getEvidenceDueDate()).thenReturn("2020-03-01");
    when(fromLedger.getParentTransactionId()).thenReturn("42");
    when(fromLedger.getReason()).thenReturn("Just cause");
    when(fromLedger.getTransactionId()).thenReturn("42");
    DisputeSettlementSummary disputeSettlementSummary = new DisputeSettlementSummary("2020-03-01");
    when(fromLedger.getSettlementSummary()).thenReturn(disputeSettlementSummary);
    when(fromLedger.getState()).thenReturn(new TransactionState("Status", true));

    // Act
    DisputeForSearchResult actualValueOfResult =
        DisputeForSearchResult.valueOf(
            fromLedger, Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    // Assert
    verify(fromLedger).getAmount();
    verify(fromLedger).getCreatedDate();
    verify(fromLedger).getEvidenceDueDate();
    verify(fromLedger).getFee();
    verify(fromLedger).getNetAmount();
    verify(fromLedger).getParentTransactionId();
    verify(fromLedger).getReason();
    verify(fromLedger).getSettlementSummary();
    verify(fromLedger).getState();
    verify(fromLedger).getTransactionId();
    DisputeSettlementSummary settlementSummary = actualValueOfResult.getSettlementSummary();
    assertEquals("2020-03-01", settlementSummary.getSettledDate());
    assertEquals("2020-03-01", actualValueOfResult.getCreatedDate());
    assertEquals("2020-03-01", actualValueOfResult.getEvidenceDueDate());
    assertEquals("42", actualValueOfResult.getDisputeId());
    assertEquals("42", actualValueOfResult.getPaymentId());
    assertEquals("Just cause", actualValueOfResult.getReason());
    assertEquals("Status", actualValueOfResult.getStatus());
    assertEquals(10L, actualValueOfResult.getAmount().longValue());
    assertEquals(1L, actualValueOfResult.getFee().longValue());
    assertEquals(1L, actualValueOfResult.getNetAmount().longValue());
    assertSame(disputeSettlementSummary, settlementSummary);
  }
}
