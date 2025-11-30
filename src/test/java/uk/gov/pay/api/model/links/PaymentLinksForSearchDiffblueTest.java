package uk.gov.pay.api.model.links;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PaymentLinksForSearchDiffblueTest {
  /**
   * Test {@link PaymentLinksForSearch#addSelf(String)}.
   *
   * <p>Method under test: {@link PaymentLinksForSearch#addSelf(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentLinksForSearch.addSelf(String)"})
  public void testAddSelf() {
    // Arrange
    PaymentLinksForSearch paymentLinksForSearch = new PaymentLinksForSearch();

    // Act
    paymentLinksForSearch.addSelf("Href");

    // Assert
    Link self = paymentLinksForSearch.getSelf();
    assertEquals("GET", self.getMethod());
    assertEquals("Href", self.getHref());
  }

  /**
   * Test {@link PaymentLinksForSearch#addEvents(String)}.
   *
   * <p>Method under test: {@link PaymentLinksForSearch#addEvents(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentLinksForSearch.addEvents(String)"})
  public void testAddEvents() {
    // Arrange
    PaymentLinksForSearch paymentLinksForSearch = new PaymentLinksForSearch();

    // Act
    paymentLinksForSearch.addEvents("Href");

    // Assert
    Link events = paymentLinksForSearch.getEvents();
    assertEquals("GET", events.getMethod());
    assertEquals("Href", events.getHref());
  }

  /**
   * Test {@link PaymentLinksForSearch#addCancel(String)}.
   *
   * <p>Method under test: {@link PaymentLinksForSearch#addCancel(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentLinksForSearch.addCancel(String)"})
  public void testAddCancel() {
    // Arrange
    PaymentLinksForSearch paymentLinksForSearch = new PaymentLinksForSearch();

    // Act
    paymentLinksForSearch.addCancel("Href");

    // Assert
    PostLink cancel = paymentLinksForSearch.getCancel();
    assertEquals("Href", cancel.getHref());
    assertEquals("POST", cancel.getMethod());
    assertNull(cancel.getType());
    assertNull(cancel.getParams());
  }

  /**
   * Test {@link PaymentLinksForSearch#addRefunds(String)}.
   *
   * <p>Method under test: {@link PaymentLinksForSearch#addRefunds(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentLinksForSearch.addRefunds(String)"})
  public void testAddRefunds() {
    // Arrange
    PaymentLinksForSearch paymentLinksForSearch = new PaymentLinksForSearch();

    // Act
    paymentLinksForSearch.addRefunds("Href");

    // Assert
    Link refunds = paymentLinksForSearch.getRefunds();
    assertEquals("GET", refunds.getMethod());
    assertEquals("Href", refunds.getHref());
  }

  /**
   * Test {@link PaymentLinksForSearch#addCapture(String)}.
   *
   * <p>Method under test: {@link PaymentLinksForSearch#addCapture(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentLinksForSearch.addCapture(String)"})
  public void testAddCapture() {
    // Arrange
    PaymentLinksForSearch paymentLinksForSearch = new PaymentLinksForSearch();

    // Act
    paymentLinksForSearch.addCapture("Href");

    // Assert
    Link capture = paymentLinksForSearch.getCapture();
    assertTrue(capture instanceof PostLink);
    assertEquals("Href", capture.getHref());
    assertEquals("POST", capture.getMethod());
    assertNull(((PostLink) capture).getType());
    assertNull(((PostLink) capture).getParams());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link PaymentLinksForSearch}
   *   <li>{@link PaymentLinksForSearch#getCancel()}
   *   <li>{@link PaymentLinksForSearch#getCapture()}
   *   <li>{@link PaymentLinksForSearch#getEvents()}
   *   <li>{@link PaymentLinksForSearch#getRefunds()}
   *   <li>{@link PaymentLinksForSearch#getSelf()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentLinksForSearch.<init>()",
    "PostLink PaymentLinksForSearch.getCancel()",
    "Link PaymentLinksForSearch.getCapture()",
    "Link PaymentLinksForSearch.getEvents()",
    "Link PaymentLinksForSearch.getRefunds()",
    "Link PaymentLinksForSearch.getSelf()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    PaymentLinksForSearch actualPaymentLinksForSearch = new PaymentLinksForSearch();
    PostLink actualCancel = actualPaymentLinksForSearch.getCancel();
    Link actualCapture = actualPaymentLinksForSearch.getCapture();
    Link actualEvents = actualPaymentLinksForSearch.getEvents();
    Link actualRefunds = actualPaymentLinksForSearch.getRefunds();

    // Assert
    assertNull(actualCapture);
    assertNull(actualEvents);
    assertNull(actualRefunds);
    assertNull(actualPaymentLinksForSearch.getSelf());
    assertNull(actualCancel);
  }
}
