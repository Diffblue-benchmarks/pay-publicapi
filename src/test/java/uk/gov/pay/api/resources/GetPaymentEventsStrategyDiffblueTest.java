package uk.gov.pay.api.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import jakarta.ws.rs.client.Client;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
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
import uk.gov.pay.api.service.ConnectorService;
import uk.gov.pay.api.service.ConnectorUriGenerator;
import uk.gov.pay.api.service.GetPaymentEventsService;
import uk.gov.pay.api.service.LedgerService;
import uk.gov.pay.api.service.PublicApiUriGenerator;

class GetPaymentEventsStrategyDiffblueTest {
  /**
   * Test {@link GetPaymentEventsStrategy#executeLedgerOnlyStrategy()}.
   *
   * <ul>
   *   <li>Then return Events size is one.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentEventsStrategy#executeLedgerOnlyStrategy()}
   */
  @Test
  @DisplayName("Test executeLedgerOnlyStrategy(); then return Events size is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentEventsResponse GetPaymentEventsStrategy.executeLedgerOnlyStrategy()"})
  void testExecuteLedgerOnlyStrategy_thenReturnEventsSizeIsOne() {
    // Arrange
    PublicApiUriGenerator publicApiUriGenerator = mock(PublicApiUriGenerator.class);
    when(publicApiUriGenerator.getPaymentEventsURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    ArrayList<TransactionEvent> transactionEventList = new ArrayList<>();
    transactionEventList.add(new TransactionEvent());

    TransactionEvents transactionEvents = mock(TransactionEvents.class);
    when(transactionEvents.getTransactionId()).thenReturn("42");
    when(transactionEvents.getEvents()).thenReturn(transactionEventList);

    LedgerService ledgerService = mock(LedgerService.class);
    when(ledgerService.getTransactionEvents(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(transactionEvents);
    Client client = mock(Client.class);
    ConnectorService connectorService =
        new ConnectorService(client, new ConnectorUriGenerator(new PublicApiConfig()));

    GetPaymentEventsService getPaymentEventsService =
        new GetPaymentEventsService(publicApiUriGenerator, connectorService, ledgerService);
    GetPaymentEventsStrategy getPaymentEventsStrategy =
        new GetPaymentEventsStrategy(
            "Strategy Name",
            new Account("42", TokenPaymentType.CARD, "ABC123"),
            "42",
            getPaymentEventsService);

    // Act
    PaymentEventsResponse actualExecuteLedgerOnlyStrategyResult =
        getPaymentEventsStrategy.executeLedgerOnlyStrategy();

    // Assert
    verify(transactionEvents).getEvents();
    verify(transactionEvents, atLeast(1)).getTransactionId();
    verify(ledgerService).getTransactionEvents(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    List<PaymentEventResponse> events = actualExecuteLedgerOnlyStrategyResult.getEvents();
    assertEquals(1, events.size());
    PaymentEventResponse getResult = events.get(0);
    assertEquals("42", getResult.getPaymentId());
    assertNull(getResult.getUpdated());
    assertNull(getResult.getState());
  }

  /**
   * Test {@link GetPaymentEventsStrategy#executeLedgerOnlyStrategy()}.
   *
   * <ul>
   *   <li>Then return Events size is two.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentEventsStrategy#executeLedgerOnlyStrategy()}
   */
  @Test
  @DisplayName("Test executeLedgerOnlyStrategy(); then return Events size is two")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentEventsResponse GetPaymentEventsStrategy.executeLedgerOnlyStrategy()"})
  void testExecuteLedgerOnlyStrategy_thenReturnEventsSizeIsTwo() {
    // Arrange
    PublicApiUriGenerator publicApiUriGenerator = mock(PublicApiUriGenerator.class);
    when(publicApiUriGenerator.getPaymentEventsURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    ArrayList<TransactionEvent> transactionEventList = new ArrayList<>();
    transactionEventList.add(new TransactionEvent());
    transactionEventList.add(new TransactionEvent());

    TransactionEvents transactionEvents = mock(TransactionEvents.class);
    when(transactionEvents.getTransactionId()).thenReturn("42");
    when(transactionEvents.getEvents()).thenReturn(transactionEventList);

    LedgerService ledgerService = mock(LedgerService.class);
    when(ledgerService.getTransactionEvents(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(transactionEvents);
    Client client = mock(Client.class);
    ConnectorService connectorService =
        new ConnectorService(client, new ConnectorUriGenerator(new PublicApiConfig()));

    GetPaymentEventsService getPaymentEventsService =
        new GetPaymentEventsService(publicApiUriGenerator, connectorService, ledgerService);
    GetPaymentEventsStrategy getPaymentEventsStrategy =
        new GetPaymentEventsStrategy(
            "Strategy Name",
            new Account("42", TokenPaymentType.CARD, "ABC123"),
            "42",
            getPaymentEventsService);

    // Act
    PaymentEventsResponse actualExecuteLedgerOnlyStrategyResult =
        getPaymentEventsStrategy.executeLedgerOnlyStrategy();

    // Assert
    verify(transactionEvents).getEvents();
    verify(transactionEvents, atLeast(1)).getTransactionId();
    verify(ledgerService).getTransactionEvents(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    List<PaymentEventResponse> events = actualExecuteLedgerOnlyStrategyResult.getEvents();
    assertEquals(2, events.size());
    PaymentEventResponse getResult = events.get(1);
    assertEquals("42", getResult.getPaymentId());
    assertNull(getResult.getUpdated());
    assertNull(getResult.getState());
  }

  /**
   * Test {@link GetPaymentEventsStrategy#executeLedgerOnlyStrategy()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentEventsStrategy#executeLedgerOnlyStrategy()}
   */
  @Test
  @DisplayName("Test executeLedgerOnlyStrategy(); then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentEventsResponse GetPaymentEventsStrategy.executeLedgerOnlyStrategy()"})
  void testExecuteLedgerOnlyStrategy_thenReturnNull() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();

    GetPaymentEventsService getPaymentEventsService = mock(GetPaymentEventsService.class);
    when(getPaymentEventsService.getPaymentEventsFromLedger(
            Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(null);
    GetPaymentEventsStrategy getPaymentEventsStrategy =
        new GetPaymentEventsStrategy(
            "Strategy Name",
            new Account("42", TokenPaymentType.CARD, "ABC123"),
            "42",
            getPaymentEventsService);

    // Act
    PaymentEventsResponse actualExecuteLedgerOnlyStrategyResult =
        getPaymentEventsStrategy.executeLedgerOnlyStrategy();

    // Assert
    verify(getPaymentEventsService).getPaymentEventsFromLedger(isA(Account.class), eq("42"));
    assertNull(actualExecuteLedgerOnlyStrategyResult);
  }

  /**
   * Test {@link GetPaymentEventsStrategy#executeLedgerOnlyStrategy()}.
   *
   * <ul>
   *   <li>Then return PaymentId is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentEventsStrategy#executeLedgerOnlyStrategy()}
   */
  @Test
  @DisplayName("Test executeLedgerOnlyStrategy(); then return PaymentId is '42'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentEventsResponse GetPaymentEventsStrategy.executeLedgerOnlyStrategy()"})
  void testExecuteLedgerOnlyStrategy_thenReturnPaymentIdIs42() {
    // Arrange
    PublicApiUriGenerator publicApiUriGenerator = mock(PublicApiUriGenerator.class);
    when(publicApiUriGenerator.getPaymentEventsURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    TransactionEvents transactionEvents = mock(TransactionEvents.class);
    when(transactionEvents.getTransactionId()).thenReturn("42");
    when(transactionEvents.getEvents()).thenReturn(new ArrayList<>());

    LedgerService ledgerService = mock(LedgerService.class);
    when(ledgerService.getTransactionEvents(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(transactionEvents);
    Client client = mock(Client.class);
    ConnectorService connectorService =
        new ConnectorService(client, new ConnectorUriGenerator(new PublicApiConfig()));

    GetPaymentEventsService getPaymentEventsService =
        new GetPaymentEventsService(publicApiUriGenerator, connectorService, ledgerService);
    GetPaymentEventsStrategy getPaymentEventsStrategy =
        new GetPaymentEventsStrategy(
            "Strategy Name",
            new Account("42", TokenPaymentType.CARD, "ABC123"),
            "42",
            getPaymentEventsService);

    // Act
    PaymentEventsResponse actualExecuteLedgerOnlyStrategyResult =
        getPaymentEventsStrategy.executeLedgerOnlyStrategy();

    // Assert
    verify(transactionEvents).getEvents();
    verify(transactionEvents).getTransactionId();
    verify(ledgerService).getTransactionEvents(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    assertEquals("42", actualExecuteLedgerOnlyStrategyResult.getPaymentId());
    Link self = actualExecuteLedgerOnlyStrategyResult.getLinks().getSelf();
    assertEquals("GET", self.getMethod());
    assertTrue(actualExecuteLedgerOnlyStrategyResult.getEvents().isEmpty());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        self.getHref());
  }

  /**
   * Test {@link GetPaymentEventsStrategy#executeDefaultStrategy()}.
   *
   * <ul>
   *   <li>Then return Events size is one.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentEventsStrategy#executeDefaultStrategy()}
   */
  @Test
  @DisplayName("Test executeDefaultStrategy(); then return Events size is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentEventsResponse GetPaymentEventsStrategy.executeDefaultStrategy()"})
  void testExecuteDefaultStrategy_thenReturnEventsSizeIsOne() {
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
    Client client = mock(Client.class);
    LedgerService ledgerService =
        new LedgerService(client, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentEventsService getPaymentEventsService =
        new GetPaymentEventsService(publicApiUriGenerator, connectorService, ledgerService);
    GetPaymentEventsStrategy getPaymentEventsStrategy =
        new GetPaymentEventsStrategy(
            "Strategy Name",
            new Account("42", TokenPaymentType.CARD, "ABC123"),
            "42",
            getPaymentEventsService);

    // Act
    PaymentEventsResponse actualExecuteDefaultStrategyResult =
        getPaymentEventsStrategy.executeDefaultStrategy();

    // Assert
    verify(paymentEvents, atLeast(1)).getChargeId();
    verify(paymentEvents).getEvents();
    verify(connectorService).getChargeEvents(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    List<PaymentEventResponse> events = actualExecuteDefaultStrategyResult.getEvents();
    assertEquals(1, events.size());
    PaymentEventResponse getResult = events.get(0);
    assertEquals("42", getResult.getPaymentId());
    assertNull(getResult.getUpdated());
    assertNull(getResult.getState());
  }

  /**
   * Test {@link GetPaymentEventsStrategy#executeDefaultStrategy()}.
   *
   * <ul>
   *   <li>Then return Events size is two.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentEventsStrategy#executeDefaultStrategy()}
   */
  @Test
  @DisplayName("Test executeDefaultStrategy(); then return Events size is two")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentEventsResponse GetPaymentEventsStrategy.executeDefaultStrategy()"})
  void testExecuteDefaultStrategy_thenReturnEventsSizeIsTwo() {
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
    Client client = mock(Client.class);
    LedgerService ledgerService =
        new LedgerService(client, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentEventsService getPaymentEventsService =
        new GetPaymentEventsService(publicApiUriGenerator, connectorService, ledgerService);
    GetPaymentEventsStrategy getPaymentEventsStrategy =
        new GetPaymentEventsStrategy(
            "Strategy Name",
            new Account("42", TokenPaymentType.CARD, "ABC123"),
            "42",
            getPaymentEventsService);

    // Act
    PaymentEventsResponse actualExecuteDefaultStrategyResult =
        getPaymentEventsStrategy.executeDefaultStrategy();

    // Assert
    verify(paymentEvents, atLeast(1)).getChargeId();
    verify(paymentEvents).getEvents();
    verify(connectorService).getChargeEvents(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    List<PaymentEventResponse> events = actualExecuteDefaultStrategyResult.getEvents();
    assertEquals(2, events.size());
    PaymentEventResponse getResult = events.get(1);
    assertEquals("42", getResult.getPaymentId());
    assertNull(getResult.getUpdated());
    assertNull(getResult.getState());
  }

  /**
   * Test {@link GetPaymentEventsStrategy#executeDefaultStrategy()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentEventsStrategy#executeDefaultStrategy()}
   */
  @Test
  @DisplayName("Test executeDefaultStrategy(); then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentEventsResponse GetPaymentEventsStrategy.executeDefaultStrategy()"})
  void testExecuteDefaultStrategy_thenReturnNull() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();

    GetPaymentEventsService getPaymentEventsService = mock(GetPaymentEventsService.class);
    when(getPaymentEventsService.getPaymentEvents(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(null);
    GetPaymentEventsStrategy getPaymentEventsStrategy =
        new GetPaymentEventsStrategy(
            "Strategy Name",
            new Account("42", TokenPaymentType.CARD, "ABC123"),
            "42",
            getPaymentEventsService);

    // Act
    PaymentEventsResponse actualExecuteDefaultStrategyResult =
        getPaymentEventsStrategy.executeDefaultStrategy();

    // Assert
    verify(getPaymentEventsService).getPaymentEvents(isA(Account.class), eq("42"));
    assertNull(actualExecuteDefaultStrategyResult);
  }

  /**
   * Test {@link GetPaymentEventsStrategy#executeDefaultStrategy()}.
   *
   * <ul>
   *   <li>Then return PaymentId is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentEventsStrategy#executeDefaultStrategy()}
   */
  @Test
  @DisplayName("Test executeDefaultStrategy(); then return PaymentId is '42'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentEventsResponse GetPaymentEventsStrategy.executeDefaultStrategy()"})
  void testExecuteDefaultStrategy_thenReturnPaymentIdIs42() {
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
    Client client = mock(Client.class);
    LedgerService ledgerService =
        new LedgerService(client, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentEventsService getPaymentEventsService =
        new GetPaymentEventsService(publicApiUriGenerator, connectorService, ledgerService);
    GetPaymentEventsStrategy getPaymentEventsStrategy =
        new GetPaymentEventsStrategy(
            "Strategy Name",
            new Account("42", TokenPaymentType.CARD, "ABC123"),
            "42",
            getPaymentEventsService);

    // Act
    PaymentEventsResponse actualExecuteDefaultStrategyResult =
        getPaymentEventsStrategy.executeDefaultStrategy();

    // Assert
    verify(paymentEvents).getChargeId();
    verify(paymentEvents).getEvents();
    verify(connectorService).getChargeEvents(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    assertEquals("42", actualExecuteDefaultStrategyResult.getPaymentId());
    Link self = actualExecuteDefaultStrategyResult.getLinks().getSelf();
    assertEquals("GET", self.getMethod());
    assertTrue(actualExecuteDefaultStrategyResult.getEvents().isEmpty());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        self.getHref());
  }

  /**
   * Test {@link GetPaymentEventsStrategy#executeConnectorOnlyStrategy()}.
   *
   * <ul>
   *   <li>Then return Events size is one.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentEventsStrategy#executeConnectorOnlyStrategy()}
   */
  @Test
  @DisplayName("Test executeConnectorOnlyStrategy(); then return Events size is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentEventsResponse GetPaymentEventsStrategy.executeConnectorOnlyStrategy()"
  })
  void testExecuteConnectorOnlyStrategy_thenReturnEventsSizeIsOne() {
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
    Client client = mock(Client.class);
    LedgerService ledgerService =
        new LedgerService(client, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentEventsService getPaymentEventsService =
        new GetPaymentEventsService(publicApiUriGenerator, connectorService, ledgerService);
    GetPaymentEventsStrategy getPaymentEventsStrategy =
        new GetPaymentEventsStrategy(
            "Strategy Name",
            new Account("42", TokenPaymentType.CARD, "ABC123"),
            "42",
            getPaymentEventsService);

    // Act
    PaymentEventsResponse actualExecuteConnectorOnlyStrategyResult =
        getPaymentEventsStrategy.executeConnectorOnlyStrategy();

    // Assert
    verify(paymentEvents, atLeast(1)).getChargeId();
    verify(paymentEvents).getEvents();
    verify(connectorService).getChargeEvents(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    List<PaymentEventResponse> events = actualExecuteConnectorOnlyStrategyResult.getEvents();
    assertEquals(1, events.size());
    PaymentEventResponse getResult = events.get(0);
    assertEquals("42", getResult.getPaymentId());
    assertNull(getResult.getUpdated());
    assertNull(getResult.getState());
  }

  /**
   * Test {@link GetPaymentEventsStrategy#executeConnectorOnlyStrategy()}.
   *
   * <ul>
   *   <li>Then return Events size is two.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentEventsStrategy#executeConnectorOnlyStrategy()}
   */
  @Test
  @DisplayName("Test executeConnectorOnlyStrategy(); then return Events size is two")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentEventsResponse GetPaymentEventsStrategy.executeConnectorOnlyStrategy()"
  })
  void testExecuteConnectorOnlyStrategy_thenReturnEventsSizeIsTwo() {
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
    Client client = mock(Client.class);
    LedgerService ledgerService =
        new LedgerService(client, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentEventsService getPaymentEventsService =
        new GetPaymentEventsService(publicApiUriGenerator, connectorService, ledgerService);
    GetPaymentEventsStrategy getPaymentEventsStrategy =
        new GetPaymentEventsStrategy(
            "Strategy Name",
            new Account("42", TokenPaymentType.CARD, "ABC123"),
            "42",
            getPaymentEventsService);

    // Act
    PaymentEventsResponse actualExecuteConnectorOnlyStrategyResult =
        getPaymentEventsStrategy.executeConnectorOnlyStrategy();

    // Assert
    verify(paymentEvents, atLeast(1)).getChargeId();
    verify(paymentEvents).getEvents();
    verify(connectorService).getChargeEvents(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    List<PaymentEventResponse> events = actualExecuteConnectorOnlyStrategyResult.getEvents();
    assertEquals(2, events.size());
    PaymentEventResponse getResult = events.get(1);
    assertEquals("42", getResult.getPaymentId());
    assertNull(getResult.getUpdated());
    assertNull(getResult.getState());
  }

  /**
   * Test {@link GetPaymentEventsStrategy#executeConnectorOnlyStrategy()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentEventsStrategy#executeConnectorOnlyStrategy()}
   */
  @Test
  @DisplayName("Test executeConnectorOnlyStrategy(); then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentEventsResponse GetPaymentEventsStrategy.executeConnectorOnlyStrategy()"
  })
  void testExecuteConnectorOnlyStrategy_thenReturnNull() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();

    GetPaymentEventsService getPaymentEventsService = mock(GetPaymentEventsService.class);
    when(getPaymentEventsService.getPaymentEventsFromConnector(
            Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(null);
    GetPaymentEventsStrategy getPaymentEventsStrategy =
        new GetPaymentEventsStrategy(
            "Strategy Name",
            new Account("42", TokenPaymentType.CARD, "ABC123"),
            "42",
            getPaymentEventsService);

    // Act
    PaymentEventsResponse actualExecuteConnectorOnlyStrategyResult =
        getPaymentEventsStrategy.executeConnectorOnlyStrategy();

    // Assert
    verify(getPaymentEventsService).getPaymentEventsFromConnector(isA(Account.class), eq("42"));
    assertNull(actualExecuteConnectorOnlyStrategyResult);
  }

  /**
   * Test {@link GetPaymentEventsStrategy#executeConnectorOnlyStrategy()}.
   *
   * <ul>
   *   <li>Then return PaymentId is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentEventsStrategy#executeConnectorOnlyStrategy()}
   */
  @Test
  @DisplayName("Test executeConnectorOnlyStrategy(); then return PaymentId is '42'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentEventsResponse GetPaymentEventsStrategy.executeConnectorOnlyStrategy()"
  })
  void testExecuteConnectorOnlyStrategy_thenReturnPaymentIdIs42() {
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
    Client client = mock(Client.class);
    LedgerService ledgerService =
        new LedgerService(client, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentEventsService getPaymentEventsService =
        new GetPaymentEventsService(publicApiUriGenerator, connectorService, ledgerService);
    GetPaymentEventsStrategy getPaymentEventsStrategy =
        new GetPaymentEventsStrategy(
            "Strategy Name",
            new Account("42", TokenPaymentType.CARD, "ABC123"),
            "42",
            getPaymentEventsService);

    // Act
    PaymentEventsResponse actualExecuteConnectorOnlyStrategyResult =
        getPaymentEventsStrategy.executeConnectorOnlyStrategy();

    // Assert
    verify(paymentEvents).getChargeId();
    verify(paymentEvents).getEvents();
    verify(connectorService).getChargeEvents(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    assertEquals("42", actualExecuteConnectorOnlyStrategyResult.getPaymentId());
    Link self = actualExecuteConnectorOnlyStrategyResult.getLinks().getSelf();
    assertEquals("GET", self.getMethod());
    assertTrue(actualExecuteConnectorOnlyStrategyResult.getEvents().isEmpty());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        self.getHref());
  }
}
