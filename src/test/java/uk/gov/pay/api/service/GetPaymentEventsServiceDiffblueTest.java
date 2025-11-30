package uk.gov.pay.api.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.dropwizard.core.server.DefaultServerFactory;
import io.dropwizard.core.setup.AdminFactory;
import io.dropwizard.health.HealthFactory;
import io.dropwizard.logging.common.DefaultLoggingFactory;
import io.dropwizard.metrics.common.MetricsFactory;
import jakarta.ws.rs.client.Client;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import uk.gov.pay.api.app.config.PublicApiConfig;
import uk.gov.pay.api.auth.Account;
import uk.gov.pay.api.ledger.service.LedgerUriGenerator;
import uk.gov.pay.api.model.PaymentEvent;
import uk.gov.pay.api.model.PaymentEventResponse;
import uk.gov.pay.api.model.PaymentEvents;
import uk.gov.pay.api.model.PaymentEventsResponse;
import uk.gov.pay.api.model.TokenPaymentType;
import uk.gov.pay.api.model.TransactionEvent;
import uk.gov.pay.api.model.TransactionEvents;
import uk.gov.pay.api.model.links.Link;

public class GetPaymentEventsServiceDiffblueTest {
  /**
   * Test {@link GetPaymentEventsService#getPaymentEventsFromConnector(Account, String)}.
   *
   * <ul>
   *   <li>Then return Events size is one.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentEventsService#getPaymentEventsFromConnector(Account,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentEventsResponse GetPaymentEventsService.getPaymentEventsFromConnector(Account, String)"
  })
  public void testGetPaymentEventsFromConnector_thenReturnEventsSizeIsOne() {
    // Arrange
    PublicApiUriGenerator publicApiUriGenerator = mock(PublicApiUriGenerator.class);
    when(publicApiUriGenerator.getPaymentEventsURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    ArrayList<PaymentEvent> paymentEventList = new ArrayList<>();
    paymentEventList.add(new PaymentEvent());

    PaymentEvents paymentEvents = mock(PaymentEvents.class);
    when(paymentEvents.getChargeId()).thenReturn("42");
    when(paymentEvents.getEvents()).thenReturn(paymentEventList);

    ConnectorService connectorService = mock(ConnectorService.class);
    when(connectorService.getChargeEvents(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(paymentEvents);

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    LedgerService ledgerService =
        new LedgerService(mock(Client.class), new LedgerUriGenerator(configuration));

    GetPaymentEventsService getPaymentEventsService =
        new GetPaymentEventsService(publicApiUriGenerator, connectorService, ledgerService);

    // Act
    PaymentEventsResponse actualPaymentEventsFromConnector =
        getPaymentEventsService.getPaymentEventsFromConnector(
            new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(paymentEvents, atLeast(1)).getChargeId();
    verify(paymentEvents).getEvents();
    verify(connectorService).getChargeEvents(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    List<PaymentEventResponse> events = actualPaymentEventsFromConnector.getEvents();
    assertEquals(1, events.size());
    PaymentEventResponse getResult = events.get(0);
    assertEquals("42", getResult.getPaymentId());
    assertNull(getResult.getUpdated());
    assertNull(getResult.getState());
  }

  /**
   * Test {@link GetPaymentEventsService#getPaymentEventsFromConnector(Account, String)}.
   *
   * <ul>
   *   <li>Then return Events size is two.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentEventsService#getPaymentEventsFromConnector(Account,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentEventsResponse GetPaymentEventsService.getPaymentEventsFromConnector(Account, String)"
  })
  public void testGetPaymentEventsFromConnector_thenReturnEventsSizeIsTwo() {
    // Arrange
    PublicApiUriGenerator publicApiUriGenerator = mock(PublicApiUriGenerator.class);
    when(publicApiUriGenerator.getPaymentEventsURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    ArrayList<PaymentEvent> paymentEventList = new ArrayList<>();
    paymentEventList.add(new PaymentEvent());
    paymentEventList.add(new PaymentEvent());

    PaymentEvents paymentEvents = mock(PaymentEvents.class);
    when(paymentEvents.getChargeId()).thenReturn("42");
    when(paymentEvents.getEvents()).thenReturn(paymentEventList);

    ConnectorService connectorService = mock(ConnectorService.class);
    when(connectorService.getChargeEvents(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(paymentEvents);

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    LedgerService ledgerService =
        new LedgerService(mock(Client.class), new LedgerUriGenerator(configuration));

    GetPaymentEventsService getPaymentEventsService =
        new GetPaymentEventsService(publicApiUriGenerator, connectorService, ledgerService);

    // Act
    PaymentEventsResponse actualPaymentEventsFromConnector =
        getPaymentEventsService.getPaymentEventsFromConnector(
            new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(paymentEvents, atLeast(1)).getChargeId();
    verify(paymentEvents).getEvents();
    verify(connectorService).getChargeEvents(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    List<PaymentEventResponse> events = actualPaymentEventsFromConnector.getEvents();
    assertEquals(2, events.size());
    PaymentEventResponse getResult = events.get(1);
    assertEquals("42", getResult.getPaymentId());
    assertNull(getResult.getUpdated());
    assertNull(getResult.getState());
  }

  /**
   * Test {@link GetPaymentEventsService#getPaymentEventsFromConnector(Account, String)}.
   *
   * <ul>
   *   <li>Then return PaymentId is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentEventsService#getPaymentEventsFromConnector(Account,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentEventsResponse GetPaymentEventsService.getPaymentEventsFromConnector(Account, String)"
  })
  public void testGetPaymentEventsFromConnector_thenReturnPaymentIdIs42() {
    // Arrange
    PublicApiUriGenerator publicApiUriGenerator = mock(PublicApiUriGenerator.class);
    when(publicApiUriGenerator.getPaymentEventsURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    PaymentEvents paymentEvents = mock(PaymentEvents.class);
    when(paymentEvents.getChargeId()).thenReturn("42");
    when(paymentEvents.getEvents()).thenReturn(new ArrayList<>());

    ConnectorService connectorService = mock(ConnectorService.class);
    when(connectorService.getChargeEvents(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(paymentEvents);

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    LedgerService ledgerService =
        new LedgerService(mock(Client.class), new LedgerUriGenerator(configuration));

    GetPaymentEventsService getPaymentEventsService =
        new GetPaymentEventsService(publicApiUriGenerator, connectorService, ledgerService);

    // Act
    PaymentEventsResponse actualPaymentEventsFromConnector =
        getPaymentEventsService.getPaymentEventsFromConnector(
            new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(paymentEvents).getChargeId();
    verify(paymentEvents).getEvents();
    verify(connectorService).getChargeEvents(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    assertEquals("42", actualPaymentEventsFromConnector.getPaymentId());
    Link self = actualPaymentEventsFromConnector.getLinks().getSelf();
    assertEquals("GET", self.getMethod());
    assertTrue(actualPaymentEventsFromConnector.getEvents().isEmpty());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        self.getHref());
  }

  /**
   * Test {@link GetPaymentEventsService#getPaymentEventsFromLedger(Account, String)}.
   *
   * <ul>
   *   <li>Then return Events size is one.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentEventsService#getPaymentEventsFromLedger(Account,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentEventsResponse GetPaymentEventsService.getPaymentEventsFromLedger(Account, String)"
  })
  public void testGetPaymentEventsFromLedger_thenReturnEventsSizeIsOne() {
    // Arrange
    PublicApiUriGenerator publicApiUriGenerator = mock(PublicApiUriGenerator.class);
    when(publicApiUriGenerator.getPaymentEventsURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    ConnectorService connectorService =
        new ConnectorService(mock(Client.class), new ConnectorUriGenerator(configuration));

    ArrayList<TransactionEvent> transactionEventList = new ArrayList<>();
    transactionEventList.add(new TransactionEvent());

    TransactionEvents transactionEvents = mock(TransactionEvents.class);
    when(transactionEvents.getTransactionId()).thenReturn("42");
    when(transactionEvents.getEvents()).thenReturn(transactionEventList);

    LedgerService ledgerService = mock(LedgerService.class);
    when(ledgerService.getTransactionEvents(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(transactionEvents);

    GetPaymentEventsService getPaymentEventsService =
        new GetPaymentEventsService(publicApiUriGenerator, connectorService, ledgerService);

    // Act
    PaymentEventsResponse actualPaymentEventsFromLedger =
        getPaymentEventsService.getPaymentEventsFromLedger(
            new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(transactionEvents).getEvents();
    verify(transactionEvents, atLeast(1)).getTransactionId();
    verify(ledgerService).getTransactionEvents(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    List<PaymentEventResponse> events = actualPaymentEventsFromLedger.getEvents();
    assertEquals(1, events.size());
    PaymentEventResponse getResult = events.get(0);
    assertEquals("42", getResult.getPaymentId());
    assertNull(getResult.getUpdated());
    assertNull(getResult.getState());
  }

  /**
   * Test {@link GetPaymentEventsService#getPaymentEventsFromLedger(Account, String)}.
   *
   * <ul>
   *   <li>Then return Events size is two.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentEventsService#getPaymentEventsFromLedger(Account,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentEventsResponse GetPaymentEventsService.getPaymentEventsFromLedger(Account, String)"
  })
  public void testGetPaymentEventsFromLedger_thenReturnEventsSizeIsTwo() {
    // Arrange
    PublicApiUriGenerator publicApiUriGenerator = mock(PublicApiUriGenerator.class);
    when(publicApiUriGenerator.getPaymentEventsURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    ConnectorService connectorService =
        new ConnectorService(mock(Client.class), new ConnectorUriGenerator(configuration));

    ArrayList<TransactionEvent> transactionEventList = new ArrayList<>();
    transactionEventList.add(new TransactionEvent());
    transactionEventList.add(new TransactionEvent());

    TransactionEvents transactionEvents = mock(TransactionEvents.class);
    when(transactionEvents.getTransactionId()).thenReturn("42");
    when(transactionEvents.getEvents()).thenReturn(transactionEventList);

    LedgerService ledgerService = mock(LedgerService.class);
    when(ledgerService.getTransactionEvents(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(transactionEvents);

    GetPaymentEventsService getPaymentEventsService =
        new GetPaymentEventsService(publicApiUriGenerator, connectorService, ledgerService);

    // Act
    PaymentEventsResponse actualPaymentEventsFromLedger =
        getPaymentEventsService.getPaymentEventsFromLedger(
            new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(transactionEvents).getEvents();
    verify(transactionEvents, atLeast(1)).getTransactionId();
    verify(ledgerService).getTransactionEvents(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    List<PaymentEventResponse> events = actualPaymentEventsFromLedger.getEvents();
    assertEquals(2, events.size());
    PaymentEventResponse getResult = events.get(1);
    assertEquals("42", getResult.getPaymentId());
    assertNull(getResult.getUpdated());
    assertNull(getResult.getState());
  }

  /**
   * Test {@link GetPaymentEventsService#getPaymentEventsFromLedger(Account, String)}.
   *
   * <ul>
   *   <li>Then return PaymentId is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentEventsService#getPaymentEventsFromLedger(Account,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentEventsResponse GetPaymentEventsService.getPaymentEventsFromLedger(Account, String)"
  })
  public void testGetPaymentEventsFromLedger_thenReturnPaymentIdIs42() {
    // Arrange
    PublicApiUriGenerator publicApiUriGenerator = mock(PublicApiUriGenerator.class);
    when(publicApiUriGenerator.getPaymentEventsURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    ConnectorService connectorService =
        new ConnectorService(mock(Client.class), new ConnectorUriGenerator(configuration));

    TransactionEvents transactionEvents = mock(TransactionEvents.class);
    when(transactionEvents.getTransactionId()).thenReturn("42");
    when(transactionEvents.getEvents()).thenReturn(new ArrayList<>());

    LedgerService ledgerService = mock(LedgerService.class);
    when(ledgerService.getTransactionEvents(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(transactionEvents);

    GetPaymentEventsService getPaymentEventsService =
        new GetPaymentEventsService(publicApiUriGenerator, connectorService, ledgerService);

    // Act
    PaymentEventsResponse actualPaymentEventsFromLedger =
        getPaymentEventsService.getPaymentEventsFromLedger(
            new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(transactionEvents).getEvents();
    verify(transactionEvents).getTransactionId();
    verify(ledgerService).getTransactionEvents(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    assertEquals("42", actualPaymentEventsFromLedger.getPaymentId());
    Link self = actualPaymentEventsFromLedger.getLinks().getSelf();
    assertEquals("GET", self.getMethod());
    assertTrue(actualPaymentEventsFromLedger.getEvents().isEmpty());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        self.getHref());
  }

  /**
   * Test {@link GetPaymentEventsService#getPaymentEvents(Account, String)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link PaymentEvent} (default constructor).
   *   <li>Then return Events size is one.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentEventsService#getPaymentEvents(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentEventsResponse GetPaymentEventsService.getPaymentEvents(Account, String)"
  })
  public void testGetPaymentEvents_givenArrayListAddPaymentEvent_thenReturnEventsSizeIsOne() {
    // Arrange
    PublicApiUriGenerator publicApiUriGenerator = mock(PublicApiUriGenerator.class);
    when(publicApiUriGenerator.getPaymentEventsURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    ArrayList<PaymentEvent> paymentEventList = new ArrayList<>();
    paymentEventList.add(new PaymentEvent());

    PaymentEvents paymentEvents = mock(PaymentEvents.class);
    when(paymentEvents.getChargeId()).thenReturn("42");
    when(paymentEvents.getEvents()).thenReturn(paymentEventList);

    ConnectorService connectorService = mock(ConnectorService.class);
    when(connectorService.getChargeEvents(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(paymentEvents);

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    LedgerService ledgerService =
        new LedgerService(mock(Client.class), new LedgerUriGenerator(configuration));

    GetPaymentEventsService getPaymentEventsService =
        new GetPaymentEventsService(publicApiUriGenerator, connectorService, ledgerService);

    // Act
    PaymentEventsResponse actualPaymentEvents =
        getPaymentEventsService.getPaymentEvents(
            new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(paymentEvents, atLeast(1)).getChargeId();
    verify(paymentEvents).getEvents();
    verify(connectorService).getChargeEvents(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    List<PaymentEventResponse> events = actualPaymentEvents.getEvents();
    assertEquals(1, events.size());
    PaymentEventResponse getResult = events.get(0);
    assertEquals("42", getResult.getPaymentId());
    assertNull(getResult.getUpdated());
    assertNull(getResult.getState());
  }

  /**
   * Test {@link GetPaymentEventsService#getPaymentEvents(Account, String)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link PaymentEvent} (default constructor).
   *   <li>Then return Events size is two.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentEventsService#getPaymentEvents(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentEventsResponse GetPaymentEventsService.getPaymentEvents(Account, String)"
  })
  public void testGetPaymentEvents_givenArrayListAddPaymentEvent_thenReturnEventsSizeIsTwo() {
    // Arrange
    PublicApiUriGenerator publicApiUriGenerator = mock(PublicApiUriGenerator.class);
    when(publicApiUriGenerator.getPaymentEventsURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    ArrayList<PaymentEvent> paymentEventList = new ArrayList<>();
    paymentEventList.add(new PaymentEvent());
    paymentEventList.add(new PaymentEvent());

    PaymentEvents paymentEvents = mock(PaymentEvents.class);
    when(paymentEvents.getChargeId()).thenReturn("42");
    when(paymentEvents.getEvents()).thenReturn(paymentEventList);

    ConnectorService connectorService = mock(ConnectorService.class);
    when(connectorService.getChargeEvents(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(paymentEvents);

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    LedgerService ledgerService =
        new LedgerService(mock(Client.class), new LedgerUriGenerator(configuration));

    GetPaymentEventsService getPaymentEventsService =
        new GetPaymentEventsService(publicApiUriGenerator, connectorService, ledgerService);

    // Act
    PaymentEventsResponse actualPaymentEvents =
        getPaymentEventsService.getPaymentEvents(
            new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(paymentEvents, atLeast(1)).getChargeId();
    verify(paymentEvents).getEvents();
    verify(connectorService).getChargeEvents(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    List<PaymentEventResponse> events = actualPaymentEvents.getEvents();
    assertEquals(2, events.size());
    PaymentEventResponse getResult = events.get(1);
    assertEquals("42", getResult.getPaymentId());
    assertNull(getResult.getUpdated());
    assertNull(getResult.getState());
  }

  /**
   * Test {@link GetPaymentEventsService#getPaymentEvents(Account, String)}.
   *
   * <ul>
   *   <li>Then return PaymentId is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentEventsService#getPaymentEvents(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentEventsResponse GetPaymentEventsService.getPaymentEvents(Account, String)"
  })
  public void testGetPaymentEvents_thenReturnPaymentIdIs42() {
    // Arrange
    PublicApiUriGenerator publicApiUriGenerator = mock(PublicApiUriGenerator.class);
    when(publicApiUriGenerator.getPaymentEventsURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    PaymentEvents paymentEvents = mock(PaymentEvents.class);
    when(paymentEvents.getChargeId()).thenReturn("42");
    when(paymentEvents.getEvents()).thenReturn(new ArrayList<>());

    ConnectorService connectorService = mock(ConnectorService.class);
    when(connectorService.getChargeEvents(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(paymentEvents);

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    LedgerService ledgerService =
        new LedgerService(mock(Client.class), new LedgerUriGenerator(configuration));

    GetPaymentEventsService getPaymentEventsService =
        new GetPaymentEventsService(publicApiUriGenerator, connectorService, ledgerService);

    // Act
    PaymentEventsResponse actualPaymentEvents =
        getPaymentEventsService.getPaymentEvents(
            new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(paymentEvents).getChargeId();
    verify(paymentEvents).getEvents();
    verify(connectorService).getChargeEvents(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    assertEquals("42", actualPaymentEvents.getPaymentId());
    Link self = actualPaymentEvents.getLinks().getSelf();
    assertEquals("GET", self.getMethod());
    assertTrue(actualPaymentEvents.getEvents().isEmpty());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        self.getHref());
  }
}
