package uk.gov.pay.api.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.net.URI;
import java.nio.file.Paths;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.pay.api.model.links.Link;
import uk.gov.pay.api.model.links.RefundLinksForSearch;

public class RefundResponseDiffblueTest {
  /**
   * Test {@link RefundResponse#from(RefundFromConnector, URI, URI)} with {@code
   * RefundFromConnector}, {@code URI}, {@code URI}.
   *
   * <ul>
   *   <li>Then return Links Payment Method is {@code GET}.
   * </ul>
   *
   * <p>Method under test: {@link RefundResponse#from(RefundFromConnector, URI, URI)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RefundResponse RefundResponse.from(RefundFromConnector, URI, URI)"})
  public void testFromWithRefundFromConnectorUriUri_thenReturnLinksPaymentMethodIsGet() {
    // Arrange and Act
    RefundResponse actualFromResult =
        RefundResponse.from(
            new RefundFromConnector(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    // Assert
    RefundLinksForSearch links = actualFromResult.getLinks();
    Link payment = links.getPayment();
    assertEquals("GET", payment.getMethod());
    assertNull(actualFromResult.getAmount());
    assertNull(actualFromResult.getCreatedDate());
    assertNull(actualFromResult.getRefundId());
    assertNull(actualFromResult.getStatus());
    assertNull(actualFromResult.getSettlementSummary().getSettledDate());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        payment.getHref());
    assertEquals(payment, links.getSelf());
  }

  /**
   * Test {@link RefundResponse#valueOf(RefundFromConnector, String, String)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then return CreatedDate is {@code 2020-03-01}.
   * </ul>
   *
   * <p>Method under test: {@link RefundResponse#valueOf(RefundFromConnector, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RefundResponse RefundResponse.valueOf(RefundFromConnector, String, String)"})
  public void testValueOf_givenTen_thenReturnCreatedDateIs20200301() {
    // Arrange
    RefundFromConnector refundEntity = mock(RefundFromConnector.class);
    when(refundEntity.getAmount()).thenReturn(10L);
    when(refundEntity.getCreatedDate()).thenReturn("2020-03-01");
    when(refundEntity.getStatus()).thenReturn("Status");
    when(refundEntity.getRefundId()).thenReturn("42");

    // Act
    RefundResponse actualValueOfResult =
        RefundResponse.valueOf(refundEntity, "42", "https://example.org/example");

    // Assert
    verify(refundEntity).getAmount();
    verify(refundEntity).getCreatedDate();
    verify(refundEntity, atLeast(1)).getRefundId();
    verify(refundEntity).getStatus();
    assertEquals("2020-03-01", actualValueOfResult.getCreatedDate());
    assertEquals("42", actualValueOfResult.getRefundId());
    RefundLinksForSearch links = actualValueOfResult.getLinks();
    Link payment = links.getPayment();
    assertEquals("GET", payment.getMethod());
    Link self = links.getSelf();
    assertEquals("GET", self.getMethod());
    assertEquals("Status", actualValueOfResult.getStatus());
    assertEquals("https://example.org/example/v1/payments/42", payment.getHref());
    assertEquals("https://example.org/example/v1/payments/42/refunds/42", self.getHref());
    assertNull(actualValueOfResult.getSettlementSummary());
    assertEquals(10L, actualValueOfResult.getAmount().longValue());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RefundResponse#getAmount()}
   *   <li>{@link RefundResponse#getCreatedDate()}
   *   <li>{@link RefundResponse#getLinks()}
   *   <li>{@link RefundResponse#getRefundId()}
   *   <li>{@link RefundResponse#getSettlementSummary()}
   *   <li>{@link RefundResponse#getStatus()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Long RefundResponse.getAmount()",
    "String RefundResponse.getCreatedDate()",
    "RefundLinksForSearch RefundResponse.getLinks()",
    "String RefundResponse.getRefundId()",
    "RefundSettlementSummary RefundResponse.getSettlementSummary()",
    "String RefundResponse.getStatus()"
  })
  public void testGettersAndSetters() {
    // Arrange
    RefundResponse fromResult =
        RefundResponse.from(
            new RefundFromConnector(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    // Act
    Long actualAmount = fromResult.getAmount();
    String actualCreatedDate = fromResult.getCreatedDate();
    RefundLinksForSearch actualLinks = fromResult.getLinks();
    String actualRefundId = fromResult.getRefundId();
    RefundSettlementSummary actualSettlementSummary = fromResult.getSettlementSummary();

    // Assert
    Link payment = actualLinks.getPayment();
    assertEquals("GET", payment.getMethod());
    assertNull(actualAmount);
    assertNull(actualCreatedDate);
    assertNull(actualRefundId);
    assertNull(fromResult.getStatus());
    assertNull(actualSettlementSummary.getSettledDate());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        payment.getHref());
  }
}
