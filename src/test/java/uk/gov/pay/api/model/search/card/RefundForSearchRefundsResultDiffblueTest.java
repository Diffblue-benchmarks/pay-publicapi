package uk.gov.pay.api.model.search.card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.net.URI;
import java.nio.file.Paths;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.model.RefundSettlementSummary;
import uk.gov.pay.api.model.links.Link;
import uk.gov.pay.api.model.links.RefundLinksForSearch;

class RefundForSearchRefundsResultDiffblueTest {
  /**
   * Test {@link RefundForSearchRefundsResult#RefundForSearchRefundsResult()}.
   *
   * <p>Method under test: {@link RefundForSearchRefundsResult#RefundForSearchRefundsResult()}
   */
  @Test
  @DisplayName("Test new RefundForSearchRefundsResult()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RefundForSearchRefundsResult.<init>()"})
  void testNewRefundForSearchRefundsResult() {
    // Arrange and Act
    RefundForSearchRefundsResult actualRefundForSearchRefundsResult =
        new RefundForSearchRefundsResult();

    // Assert
    assertNull(actualRefundForSearchRefundsResult.getAmount());
    assertNull(actualRefundForSearchRefundsResult.getChargeId());
    assertNull(actualRefundForSearchRefundsResult.getCreatedDate());
    assertNull(actualRefundForSearchRefundsResult.getRefundId());
    assertNull(actualRefundForSearchRefundsResult.getStatus());
    assertNull(actualRefundForSearchRefundsResult.getSettlementSummary());
    RefundLinksForSearch links = actualRefundForSearchRefundsResult.getLinks();
    assertNull(links.getPayment());
    assertNull(links.getSelf());
  }

  /**
   * Test {@link RefundForSearchRefundsResult#RefundForSearchRefundsResult(String, String, String,
   * String, Long, URI, URI, RefundSettlementSummary)}.
   *
   * <p>Method under test: {@link RefundForSearchRefundsResult#RefundForSearchRefundsResult(String,
   * String, String, String, Long, URI, URI, RefundSettlementSummary)}
   */
  @Test
  @DisplayName(
      "Test new RefundForSearchRefundsResult(String, String, String, String, Long, URI, URI, RefundSettlementSummary)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RefundForSearchRefundsResult.<init>(String, String, String, String, Long, URI, URI, RefundSettlementSummary)"
  })
  void testNewRefundForSearchRefundsResult2() {
    // Arrange
    URI paymentURI = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    URI refundsURI = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    RefundSettlementSummary settlementSummary = new RefundSettlementSummary("2020-03-01");

    // Act
    RefundForSearchRefundsResult actualRefundForSearchRefundsResult =
        new RefundForSearchRefundsResult(
            "42", "2020-03-01", "Status", "42", 10L, paymentURI, refundsURI, settlementSummary);

    // Assert
    assertEquals("2020-03-01", actualRefundForSearchRefundsResult.getCreatedDate());
    assertEquals("42", actualRefundForSearchRefundsResult.getChargeId());
    assertEquals("42", actualRefundForSearchRefundsResult.getRefundId());
    RefundLinksForSearch links = actualRefundForSearchRefundsResult.getLinks();
    Link payment = links.getPayment();
    assertEquals("GET", payment.getMethod());
    assertEquals("Status", actualRefundForSearchRefundsResult.getStatus());
    assertEquals(10L, actualRefundForSearchRefundsResult.getAmount().longValue());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        payment.getHref());
    assertEquals(payment, links.getSelf());
    assertSame(settlementSummary, actualRefundForSearchRefundsResult.getSettlementSummary());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RefundForSearchRefundsResult#setAmount(Long)}
   *   <li>{@link RefundForSearchRefundsResult#setChargeId(String)}
   *   <li>{@link RefundForSearchRefundsResult#toString()}
   *   <li>{@link RefundForSearchRefundsResult#getAmount()}
   *   <li>{@link RefundForSearchRefundsResult#getChargeId()}
   *   <li>{@link RefundForSearchRefundsResult#getCreatedDate()}
   *   <li>{@link RefundForSearchRefundsResult#getLinks()}
   *   <li>{@link RefundForSearchRefundsResult#getRefundId()}
   *   <li>{@link RefundForSearchRefundsResult#getSettlementSummary()}
   *   <li>{@link RefundForSearchRefundsResult#getStatus()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Long RefundForSearchRefundsResult.getAmount()",
    "String RefundForSearchRefundsResult.getChargeId()",
    "String RefundForSearchRefundsResult.getCreatedDate()",
    "RefundLinksForSearch RefundForSearchRefundsResult.getLinks()",
    "String RefundForSearchRefundsResult.getRefundId()",
    "RefundSettlementSummary RefundForSearchRefundsResult.getSettlementSummary()",
    "String RefundForSearchRefundsResult.getStatus()",
    "void RefundForSearchRefundsResult.setAmount(Long)",
    "void RefundForSearchRefundsResult.setChargeId(String)",
    "String RefundForSearchRefundsResult.toString()"
  })
  void testGettersAndSetters() {
    // Arrange
    RefundForSearchRefundsResult refundForSearchRefundsResult = new RefundForSearchRefundsResult();

    // Act
    refundForSearchRefundsResult.setAmount(10L);
    refundForSearchRefundsResult.setChargeId("42");
    refundForSearchRefundsResult.toString();
    Long actualAmount = refundForSearchRefundsResult.getAmount();
    String actualChargeId = refundForSearchRefundsResult.getChargeId();
    String actualCreatedDate = refundForSearchRefundsResult.getCreatedDate();
    RefundLinksForSearch actualLinks = refundForSearchRefundsResult.getLinks();
    String actualRefundId = refundForSearchRefundsResult.getRefundId();
    RefundSettlementSummary actualSettlementSummary =
        refundForSearchRefundsResult.getSettlementSummary();

    // Assert
    assertEquals("42", actualChargeId);
    assertNull(actualCreatedDate);
    assertNull(actualRefundId);
    assertNull(refundForSearchRefundsResult.getStatus());
    assertNull(actualSettlementSummary);
    assertNull(actualLinks.getPayment());
    assertNull(actualLinks.getSelf());
    assertEquals(10L, actualAmount.longValue());
  }
}
