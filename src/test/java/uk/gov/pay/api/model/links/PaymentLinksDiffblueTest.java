package uk.gov.pay.api.model.links;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.net.URI;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.model.PaymentConnectorResponseLink;

class PaymentLinksDiffblueTest {
  /**
   * Test {@link PaymentLinks#addKnownLinksValueOf(List, URI)}.
   *
   * <ul>
   *   <li>Then {@link PaymentLinks} (default constructor) AuthUrlPost Method is {@code Method}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentLinks#addKnownLinksValueOf(List, URI)}
   */
  @Test
  @DisplayName(
      "Test addKnownLinksValueOf(List, URI); then PaymentLinks (default constructor) AuthUrlPost Method is 'Method'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentLinks.addKnownLinksValueOf(List, URI)"})
  void testAddKnownLinksValueOf_thenPaymentLinksAuthUrlPostMethodIsMethod() {
    // Arrange
    PaymentLinks paymentLinks = new PaymentLinks();

    ArrayList<PaymentConnectorResponseLink> chargeLinks = new ArrayList<>();
    HashMap<String, Object> params = new HashMap<>();
    PaymentConnectorResponseLink paymentConnectorResponseLink =
        new PaymentConnectorResponseLink("auth_url_post", "Href", "Method", "Type", params);
    chargeLinks.add(paymentConnectorResponseLink);

    // Act
    paymentLinks.addKnownLinksValueOf(
        chargeLinks, Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    // Assert
    PostLink authUrlPost = paymentLinks.getAuthUrlPost();
    assertEquals("Method", authUrlPost.getMethod());
    assertEquals("Type", authUrlPost.getType());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        authUrlPost.getHref());
    assertSame(params, authUrlPost.getParams());
  }

  /**
   * Test {@link PaymentLinks#addKnownLinksValueOf(List, URI)}.
   *
   * <ul>
   *   <li>Then {@link PaymentLinks} (default constructor) Capture Href is {@code Href}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentLinks#addKnownLinksValueOf(List, URI)}
   */
  @Test
  @DisplayName(
      "Test addKnownLinksValueOf(List, URI); then PaymentLinks (default constructor) Capture Href is 'Href'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentLinks.addKnownLinksValueOf(List, URI)"})
  void testAddKnownLinksValueOf_thenPaymentLinksCaptureHrefIsHref() {
    // Arrange
    PaymentLinks paymentLinks = new PaymentLinks();

    ArrayList<PaymentConnectorResponseLink> chargeLinks = new ArrayList<>();
    PaymentConnectorResponseLink paymentConnectorResponseLink =
        new PaymentConnectorResponseLink("capture", "Href", "Method", "Type", new HashMap<>());
    chargeLinks.add(paymentConnectorResponseLink);

    // Act
    paymentLinks.addKnownLinksValueOf(
        chargeLinks, Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    // Assert
    PostLink capture = paymentLinks.getCapture();
    assertEquals("Href", capture.getHref());
    assertEquals("Method", capture.getMethod());
    assertNull(capture.getType());
    assertNull(capture.getParams());
  }

  /**
   * Test {@link PaymentLinks#addKnownLinksValueOf(List, URI)}.
   *
   * <ul>
   *   <li>Then {@link PaymentLinks} (default constructor) NextUrl Href is {@code Href}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentLinks#addKnownLinksValueOf(List, URI)}
   */
  @Test
  @DisplayName(
      "Test addKnownLinksValueOf(List, URI); then PaymentLinks (default constructor) NextUrl Href is 'Href'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentLinks.addKnownLinksValueOf(List, URI)"})
  void testAddKnownLinksValueOf_thenPaymentLinksNextUrlHrefIsHref() {
    // Arrange
    PaymentLinks paymentLinks = new PaymentLinks();

    ArrayList<PaymentConnectorResponseLink> chargeLinks = new ArrayList<>();
    PaymentConnectorResponseLink paymentConnectorResponseLink =
        new PaymentConnectorResponseLink("next_url", "Href", "Method", "Type", new HashMap<>());
    chargeLinks.add(paymentConnectorResponseLink);

    // Act
    paymentLinks.addKnownLinksValueOf(
        chargeLinks, Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    // Assert
    Link nextUrl = paymentLinks.getNextUrl();
    assertEquals("Href", nextUrl.getHref());
    assertEquals("Method", nextUrl.getMethod());
  }

  /**
   * Test {@link PaymentLinks#addKnownLinksValueOf(List, URI)}.
   *
   * <ul>
   *   <li>Then {@link PaymentLinks} (default constructor) NextUrlPost Href is {@code Href}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentLinks#addKnownLinksValueOf(List, URI)}
   */
  @Test
  @DisplayName(
      "Test addKnownLinksValueOf(List, URI); then PaymentLinks (default constructor) NextUrlPost Href is 'Href'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentLinks.addKnownLinksValueOf(List, URI)"})
  void testAddKnownLinksValueOf_thenPaymentLinksNextUrlPostHrefIsHref() {
    // Arrange
    PaymentLinks paymentLinks = new PaymentLinks();

    ArrayList<PaymentConnectorResponseLink> chargeLinks = new ArrayList<>();
    HashMap<String, Object> params = new HashMap<>();
    PaymentConnectorResponseLink paymentConnectorResponseLink =
        new PaymentConnectorResponseLink("next_url_post", "Href", "Method", "Type", params);
    chargeLinks.add(paymentConnectorResponseLink);

    // Act
    paymentLinks.addKnownLinksValueOf(
        chargeLinks, Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    // Assert
    PostLink nextUrlPost = paymentLinks.getNextUrlPost();
    assertEquals("Href", nextUrlPost.getHref());
    assertEquals("Method", nextUrlPost.getMethod());
    assertEquals("Type", nextUrlPost.getType());
    assertSame(params, nextUrlPost.getParams());
  }

  /**
   * Test {@link PaymentLinks#addSelf(String)}.
   *
   * <p>Method under test: {@link PaymentLinks#addSelf(String)}
   */
  @Test
  @DisplayName("Test addSelf(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentLinks.addSelf(String)"})
  void testAddSelf() {
    // Arrange
    PaymentLinks paymentLinks = new PaymentLinks();

    // Act
    paymentLinks.addSelf("Href");

    // Assert
    Link self = paymentLinks.getSelf();
    assertEquals("GET", self.getMethod());
    assertEquals("Href", self.getHref());
  }

  /**
   * Test {@link PaymentLinks#addEvents(String)}.
   *
   * <p>Method under test: {@link PaymentLinks#addEvents(String)}
   */
  @Test
  @DisplayName("Test addEvents(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentLinks.addEvents(String)"})
  void testAddEvents() {
    // Arrange
    PaymentLinks paymentLinks = new PaymentLinks();

    // Act
    paymentLinks.addEvents("Href");

    // Assert
    Link events = paymentLinks.getEvents();
    assertEquals("GET", events.getMethod());
    assertEquals("Href", events.getHref());
  }

  /**
   * Test {@link PaymentLinks#addRefunds(String)}.
   *
   * <p>Method under test: {@link PaymentLinks#addRefunds(String)}
   */
  @Test
  @DisplayName("Test addRefunds(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentLinks.addRefunds(String)"})
  void testAddRefunds() {
    // Arrange
    PaymentLinks paymentLinks = new PaymentLinks();

    // Act
    paymentLinks.addRefunds("Href");

    // Assert
    Link refunds = paymentLinks.getRefunds();
    assertEquals("GET", refunds.getMethod());
    assertEquals("Href", refunds.getHref());
  }

  /**
   * Test {@link PaymentLinks#addCancel(String)}.
   *
   * <p>Method under test: {@link PaymentLinks#addCancel(String)}
   */
  @Test
  @DisplayName("Test addCancel(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentLinks.addCancel(String)"})
  void testAddCancel() {
    // Arrange
    PaymentLinks paymentLinks = new PaymentLinks();

    // Act
    paymentLinks.addCancel("Href");

    // Assert
    PostLink cancel = paymentLinks.getCancel();
    assertEquals("Href", cancel.getHref());
    assertEquals("POST", cancel.getMethod());
    assertNull(cancel.getType());
    assertNull(cancel.getParams());
  }

  /**
   * Test {@link PaymentLinks#addCapture(String)}.
   *
   * <p>Method under test: {@link PaymentLinks#addCapture(String)}
   */
  @Test
  @DisplayName("Test addCapture(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentLinks.addCapture(String)"})
  void testAddCapture() {
    // Arrange
    PaymentLinks paymentLinks = new PaymentLinks();

    // Act
    paymentLinks.addCapture("Href");

    // Assert
    PostLink capture = paymentLinks.getCapture();
    assertEquals("Href", capture.getHref());
    assertEquals("POST", capture.getMethod());
    assertNull(capture.getType());
    assertNull(capture.getParams());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link PaymentLinks}
   *   <li>{@link PaymentLinks#getAuthUrlPost()}
   *   <li>{@link PaymentLinks#getCancel()}
   *   <li>{@link PaymentLinks#getCapture()}
   *   <li>{@link PaymentLinks#getEvents()}
   *   <li>{@link PaymentLinks#getNextUrl()}
   *   <li>{@link PaymentLinks#getNextUrlPost()}
   *   <li>{@link PaymentLinks#getRefunds()}
   *   <li>{@link PaymentLinks#getSelf()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentLinks.<init>()",
    "PostLink PaymentLinks.getAuthUrlPost()",
    "PostLink PaymentLinks.getCancel()",
    "PostLink PaymentLinks.getCapture()",
    "Link PaymentLinks.getEvents()",
    "Link PaymentLinks.getNextUrl()",
    "PostLink PaymentLinks.getNextUrlPost()",
    "Link PaymentLinks.getRefunds()",
    "Link PaymentLinks.getSelf()"
  })
  void testGettersAndSetters() {
    // Arrange and Act
    PaymentLinks actualPaymentLinks = new PaymentLinks();
    PostLink actualAuthUrlPost = actualPaymentLinks.getAuthUrlPost();
    PostLink actualCancel = actualPaymentLinks.getCancel();
    PostLink actualCapture = actualPaymentLinks.getCapture();
    Link actualEvents = actualPaymentLinks.getEvents();
    Link actualNextUrl = actualPaymentLinks.getNextUrl();
    PostLink actualNextUrlPost = actualPaymentLinks.getNextUrlPost();
    Link actualRefunds = actualPaymentLinks.getRefunds();

    // Assert
    assertNull(actualEvents);
    assertNull(actualNextUrl);
    assertNull(actualRefunds);
    assertNull(actualPaymentLinks.getSelf());
    assertNull(actualAuthUrlPost);
    assertNull(actualCancel);
    assertNull(actualCapture);
    assertNull(actualNextUrlPost);
  }
}
