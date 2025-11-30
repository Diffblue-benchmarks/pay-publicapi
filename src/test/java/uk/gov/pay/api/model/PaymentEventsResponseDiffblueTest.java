package uk.gov.pay.api.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.net.URI;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.pay.api.model.links.Link;

public class PaymentEventsResponseDiffblueTest {
  /**
   * Test {@link PaymentEventsResponse#from(PaymentEvents, URI, URI)} with {@code paymentEvents},
   * {@code paymentEventsLink}, {@code eventsLink}.
   *
   * <ul>
   *   <li>Then return Events size is one.
   * </ul>
   *
   * <p>Method under test: {@link PaymentEventsResponse#from(PaymentEvents, URI, URI)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentEventsResponse PaymentEventsResponse.from(PaymentEvents, URI, URI)"})
  public void testFromWithPaymentEventsPaymentEventsLinkEventsLink_thenReturnEventsSizeIsOne() {
    // Arrange
    ArrayList<PaymentEvent> paymentEventList = new ArrayList<>();
    paymentEventList.add(new PaymentEvent());

    PaymentEvents paymentEvents = mock(PaymentEvents.class);
    when(paymentEvents.getChargeId()).thenReturn("42");
    when(paymentEvents.getEvents()).thenReturn(paymentEventList);

    // Act
    PaymentEventsResponse actualFromResult =
        PaymentEventsResponse.from(
            paymentEvents,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    // Assert
    verify(paymentEvents, atLeast(1)).getChargeId();
    verify(paymentEvents).getEvents();
    List<PaymentEventResponse> events = actualFromResult.getEvents();
    assertEquals(1, events.size());
    PaymentEventResponse getResult = events.get(0);
    assertEquals("42", getResult.getPaymentId());
    assertNull(getResult.getUpdated());
    assertNull(getResult.getState());
  }

  /**
   * Test {@link PaymentEventsResponse#from(PaymentEvents, URI, URI)} with {@code paymentEvents},
   * {@code paymentEventsLink}, {@code eventsLink}.
   *
   * <ul>
   *   <li>Then return Events size is two.
   * </ul>
   *
   * <p>Method under test: {@link PaymentEventsResponse#from(PaymentEvents, URI, URI)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentEventsResponse PaymentEventsResponse.from(PaymentEvents, URI, URI)"})
  public void testFromWithPaymentEventsPaymentEventsLinkEventsLink_thenReturnEventsSizeIsTwo() {
    // Arrange
    ArrayList<PaymentEvent> paymentEventList = new ArrayList<>();
    paymentEventList.add(new PaymentEvent());
    paymentEventList.add(new PaymentEvent());

    PaymentEvents paymentEvents = mock(PaymentEvents.class);
    when(paymentEvents.getChargeId()).thenReturn("42");
    when(paymentEvents.getEvents()).thenReturn(paymentEventList);

    // Act
    PaymentEventsResponse actualFromResult =
        PaymentEventsResponse.from(
            paymentEvents,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    // Assert
    verify(paymentEvents, atLeast(1)).getChargeId();
    verify(paymentEvents).getEvents();
    List<PaymentEventResponse> events = actualFromResult.getEvents();
    assertEquals(2, events.size());
    PaymentEventResponse getResult = events.get(1);
    assertEquals("42", getResult.getPaymentId());
    assertNull(getResult.getUpdated());
    assertNull(getResult.getState());
  }

  /**
   * Test {@link PaymentEventsResponse#from(PaymentEvents, URI, URI)} with {@code paymentEvents},
   * {@code paymentEventsLink}, {@code eventsLink}.
   *
   * <ul>
   *   <li>Then return PaymentId is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentEventsResponse#from(PaymentEvents, URI, URI)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentEventsResponse PaymentEventsResponse.from(PaymentEvents, URI, URI)"})
  public void testFromWithPaymentEventsPaymentEventsLinkEventsLink_thenReturnPaymentIdIs42() {
    // Arrange
    PaymentEvents paymentEvents = mock(PaymentEvents.class);
    when(paymentEvents.getChargeId()).thenReturn("42");
    when(paymentEvents.getEvents()).thenReturn(new ArrayList<>());

    // Act
    PaymentEventsResponse actualFromResult =
        PaymentEventsResponse.from(
            paymentEvents,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    // Assert
    verify(paymentEvents).getChargeId();
    verify(paymentEvents).getEvents();
    assertEquals("42", actualFromResult.getPaymentId());
    Link self = actualFromResult.getLinks().getSelf();
    assertEquals("GET", self.getMethod());
    assertTrue(actualFromResult.getEvents().isEmpty());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        self.getHref());
  }

  /**
   * Test {@link PaymentEventsResponse#from(TransactionEvents, URI, URI)} with {@code
   * transactionEvents}, {@code paymentEventsLink}, {@code eventsLink}.
   *
   * <p>Method under test: {@link PaymentEventsResponse#from(TransactionEvents, URI, URI)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentEventsResponse PaymentEventsResponse.from(TransactionEvents, URI, URI)"
  })
  public void testFromWithTransactionEventsPaymentEventsLinkEventsLink() {
    // Arrange
    ArrayList<TransactionEvent> transactionEventList = new ArrayList<>();
    transactionEventList.add(new TransactionEvent());

    TransactionEvents transactionEvents = mock(TransactionEvents.class);
    when(transactionEvents.getTransactionId()).thenReturn("42");
    when(transactionEvents.getEvents()).thenReturn(transactionEventList);

    // Act
    PaymentEventsResponse actualFromResult =
        PaymentEventsResponse.from(
            transactionEvents,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    // Assert
    verify(transactionEvents).getEvents();
    verify(transactionEvents, atLeast(1)).getTransactionId();
    List<PaymentEventResponse> events = actualFromResult.getEvents();
    assertEquals(1, events.size());
    PaymentEventResponse getResult = events.get(0);
    assertEquals("42", getResult.getPaymentId());
    assertNull(getResult.getUpdated());
    assertNull(getResult.getState());
  }

  /**
   * Test {@link PaymentEventsResponse#from(TransactionEvents, URI, URI)} with {@code
   * transactionEvents}, {@code paymentEventsLink}, {@code eventsLink}.
   *
   * <p>Method under test: {@link PaymentEventsResponse#from(TransactionEvents, URI, URI)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentEventsResponse PaymentEventsResponse.from(TransactionEvents, URI, URI)"
  })
  public void testFromWithTransactionEventsPaymentEventsLinkEventsLink2() {
    // Arrange
    ArrayList<TransactionEvent> transactionEventList = new ArrayList<>();
    transactionEventList.add(new TransactionEvent());
    transactionEventList.add(new TransactionEvent());

    TransactionEvents transactionEvents = mock(TransactionEvents.class);
    when(transactionEvents.getTransactionId()).thenReturn("42");
    when(transactionEvents.getEvents()).thenReturn(transactionEventList);

    // Act
    PaymentEventsResponse actualFromResult =
        PaymentEventsResponse.from(
            transactionEvents,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    // Assert
    verify(transactionEvents).getEvents();
    verify(transactionEvents, atLeast(1)).getTransactionId();
    List<PaymentEventResponse> events = actualFromResult.getEvents();
    assertEquals(2, events.size());
    PaymentEventResponse getResult = events.get(1);
    assertEquals("42", getResult.getPaymentId());
    assertNull(getResult.getUpdated());
    assertNull(getResult.getState());
  }

  /**
   * Test {@link PaymentEventsResponse#from(TransactionEvents, URI, URI)} with {@code
   * transactionEvents}, {@code paymentEventsLink}, {@code eventsLink}.
   *
   * <ul>
   *   <li>Then return PaymentId is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentEventsResponse#from(TransactionEvents, URI, URI)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentEventsResponse PaymentEventsResponse.from(TransactionEvents, URI, URI)"
  })
  public void testFromWithTransactionEventsPaymentEventsLinkEventsLink_thenReturnPaymentIdIs42() {
    // Arrange
    TransactionEvents transactionEvents = mock(TransactionEvents.class);
    when(transactionEvents.getTransactionId()).thenReturn("42");
    when(transactionEvents.getEvents()).thenReturn(new ArrayList<>());

    // Act
    PaymentEventsResponse actualFromResult =
        PaymentEventsResponse.from(
            transactionEvents,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    // Assert
    verify(transactionEvents).getEvents();
    verify(transactionEvents).getTransactionId();
    assertEquals("42", actualFromResult.getPaymentId());
    Link self = actualFromResult.getLinks().getSelf();
    assertEquals("GET", self.getMethod());
    assertTrue(actualFromResult.getEvents().isEmpty());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        self.getHref());
  }
}
