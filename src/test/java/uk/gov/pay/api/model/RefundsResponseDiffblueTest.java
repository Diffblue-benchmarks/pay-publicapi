package uk.gov.pay.api.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.pay.api.model.RefundsResponse.EmbeddedRefunds;
import uk.gov.pay.api.model.links.Link;
import uk.gov.pay.api.model.links.RefundLinksForSearch;

public class RefundsResponseDiffblueTest {
  /**
   * Test EmbeddedRefunds getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link EmbeddedRefunds#EmbeddedRefunds(RefundsResponse)}
   *   <li>{@link EmbeddedRefunds#toString()}
   *   <li>{@link EmbeddedRefunds#getRefunds()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void EmbeddedRefunds.<init>(RefundsResponse)",
    "List EmbeddedRefunds.getRefunds()",
    "String EmbeddedRefunds.toString()"
  })
  public void testEmbeddedRefundsGettersAndSetters() {
    // Arrange
    RefundsResponse fromResult =
        RefundsResponse.from("42", new ArrayList<>(), "Self Link", "Payment Link");

    // Act
    EmbeddedRefunds actualEmbeddedRefunds = fromResult.new EmbeddedRefunds();
    String actualToStringResult = actualEmbeddedRefunds.toString();

    // Assert
    assertEquals("Embedded{refunds=null}", actualToStringResult);
    assertNull(actualEmbeddedRefunds.getRefunds());
  }

  /**
   * Test {@link RefundsResponse#from(String, List, String, String)}.
   *
   * <ul>
   *   <li>Then return Embedded Refunds is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link RefundsResponse#from(String, List, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RefundsResponse RefundsResponse.from(String, List, String, String)"})
  public void testFrom_thenReturnEmbeddedRefundsIsArrayList() {
    // Arrange
    ArrayList<RefundResponse> refundsForPayment = new ArrayList<>();
    RefundResponse fromResult =
        RefundResponse.from(
            new RefundFromConnector(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    refundsForPayment.add(fromResult);

    // Act
    RefundsResponse actualFromResult =
        RefundsResponse.from("42", refundsForPayment, "Self Link", "Payment Link");

    // Assert
    assertSame(refundsForPayment, actualFromResult.getEmbedded().getRefunds());
  }

  /**
   * Test {@link RefundsResponse#from(String, List, String, String)}.
   *
   * <ul>
   *   <li>Then return Embedded Refunds size is two.
   * </ul>
   *
   * <p>Method under test: {@link RefundsResponse#from(String, List, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RefundsResponse RefundsResponse.from(String, List, String, String)"})
  public void testFrom_thenReturnEmbeddedRefundsSizeIsTwo() {
    // Arrange
    ArrayList<RefundResponse> refundsForPayment = new ArrayList<>();
    RefundResponse fromResult =
        RefundResponse.from(
            new RefundFromConnector(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    refundsForPayment.add(fromResult);
    RefundResponse fromResult2 =
        RefundResponse.from(
            new RefundFromConnector(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    refundsForPayment.add(fromResult2);

    // Act
    RefundsResponse actualFromResult =
        RefundsResponse.from("42", refundsForPayment, "Self Link", "Payment Link");

    // Assert
    List<RefundResponse> refunds = actualFromResult.getEmbedded().getRefunds();
    assertEquals(2, refunds.size());
    assertSame(fromResult2, refunds.get(1));
  }

  /**
   * Test {@link RefundsResponse#from(String, List, String, String)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return PaymentId is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link RefundsResponse#from(String, List, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RefundsResponse RefundsResponse.from(String, List, String, String)"})
  public void testFrom_whenArrayList_thenReturnPaymentIdIs42() {
    // Arrange and Act
    RefundsResponse actualFromResult =
        RefundsResponse.from("42", new ArrayList<>(), "Self Link", "Payment Link");

    // Assert
    assertEquals("42", actualFromResult.getPaymentId());
    RefundLinksForSearch links = actualFromResult.getLinks();
    Link payment = links.getPayment();
    assertEquals("GET", payment.getMethod());
    Link self = links.getSelf();
    assertEquals("GET", self.getMethod());
    assertEquals("Payment Link", payment.getHref());
    assertEquals("Self Link", self.getHref());
    assertTrue(actualFromResult.getEmbedded().getRefunds().isEmpty());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RefundsResponse#getEmbedded()}
   *   <li>{@link RefundsResponse#getLinks()}
   *   <li>{@link RefundsResponse#getPaymentId()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "EmbeddedRefunds RefundsResponse.getEmbedded()",
    "RefundLinksForSearch RefundsResponse.getLinks()",
    "String RefundsResponse.getPaymentId()"
  })
  public void testGettersAndSetters() {
    // Arrange
    ArrayList<RefundResponse> refundsForPayment = new ArrayList<>();
    RefundsResponse fromResult =
        RefundsResponse.from("42", refundsForPayment, "Self Link", "Payment Link");

    // Act
    EmbeddedRefunds actualEmbedded = fromResult.getEmbedded();
    RefundLinksForSearch actualLinks = fromResult.getLinks();

    // Assert
    assertEquals("42", fromResult.getPaymentId());
    Link payment = actualLinks.getPayment();
    assertEquals("GET", payment.getMethod());
    Link self = actualLinks.getSelf();
    assertEquals("GET", self.getMethod());
    assertEquals("Payment Link", payment.getHref());
    assertEquals("Self Link", self.getHref());
    List<RefundResponse> refunds = actualEmbedded.getRefunds();
    assertTrue(refunds.isEmpty());
    assertSame(refundsForPayment, refunds);
  }
}
