package uk.gov.pay.api.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import jakarta.ws.rs.client.Client;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import uk.gov.pay.api.app.config.PublicApiConfig;
import uk.gov.pay.api.auth.Account;
import uk.gov.pay.api.ledger.service.LedgerUriGenerator;
import uk.gov.pay.api.model.Address;
import uk.gov.pay.api.model.AuthorisationSummary;
import uk.gov.pay.api.model.CardDetails;
import uk.gov.pay.api.model.Charge;
import uk.gov.pay.api.model.Exemption;
import uk.gov.pay.api.model.PaymentConnectorResponseLink;
import uk.gov.pay.api.model.PaymentSettlementSummary;
import uk.gov.pay.api.model.PaymentState;
import uk.gov.pay.api.model.RefundSummary;
import uk.gov.pay.api.model.TokenPaymentType;
import uk.gov.pay.api.model.links.PaymentWithAllLinks;
import uk.gov.pay.api.model.links.PaymentWithAllLinks.PaymentWithAllLinksBuilder;
import uk.gov.pay.api.model.links.PostLink;
import uk.gov.pay.api.service.ConnectorService;
import uk.gov.pay.api.service.ConnectorUriGenerator;
import uk.gov.pay.api.service.GetPaymentService;
import uk.gov.pay.api.service.LedgerService;
import uk.gov.pay.api.service.PublicApiUriGenerator;
import uk.gov.service.payments.commons.model.AgreementPaymentType;
import uk.gov.service.payments.commons.model.AuthorisationMode;
import uk.gov.service.payments.commons.model.SupportedLanguage;
import uk.gov.service.payments.commons.model.charge.ExternalMetadata;

public class GetOnePaymentStrategyDiffblueTest {
  /**
   * Test {@link GetOnePaymentStrategy#executeLedgerOnlyStrategy()}.
   *
   * <p>Method under test: {@link GetOnePaymentStrategy#executeLedgerOnlyStrategy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentWithAllLinks GetOnePaymentStrategy.executeLedgerOnlyStrategy()"})
  public void testExecuteLedgerOnlyStrategy() {
    // Arrange
    PublicApiUriGenerator publicApiUriGenerator = mock(PublicApiUriGenerator.class);
    when(publicApiUriGenerator.getPaymentAuthorisationURI())
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentCancelURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentCaptureURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentEventsURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentRefundsURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    ArrayList<PaymentConnectorResponseLink> links = new ArrayList<>();
    PaymentConnectorResponseLink paymentConnectorResponseLink =
        new PaymentConnectorResponseLink("GET", "GET", "GET", "GET", new HashMap<>());
    links.add(paymentConnectorResponseLink);
    PaymentState state = new PaymentState("Status", true);
    RefundSummary refundSummary = new RefundSummary("Status", 10L, 10L);
    PaymentSettlementSummary settlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    Address billingAddress = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");
    CardDetails cardDetails =
        new CardDetails(
            "42",
            "42",
            "Card Holder Name",
            "2020-03-01",
            billingAddress,
            "Card Brand",
            "Card Type",
            "Wallet Type");
    ExternalMetadata metadata = new ExternalMetadata(new HashMap<>());
    AuthorisationSummary authorisationSummary = new AuthorisationSummary();

    Charge charge =
        new Charge(
            "42",
            10L,
            state,
            "https://example.org/example",
            "The characteristics of someone or something",
            "Reference",
            "jane.doe@example.org",
            "Payment Provider",
            "2020-03-01",
            SupportedLanguage.ENGLISH,
            true,
            true,
            refundSummary,
            settlementSummary,
            cardDetails,
            links,
            1L,
            1L,
            "42",
            metadata,
            1L,
            1L,
            authorisationSummary,
            "42",
            AuthorisationMode.WEB,
            AgreementPaymentType.INSTALMENT,
            new Exemption());

    LedgerService ledgerService = mock(LedgerService.class);
    when(ledgerService.getPaymentTransaction(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(charge);
    Client client = mock(Client.class);
    ConnectorService connectorService =
        new ConnectorService(client, new ConnectorUriGenerator(new PublicApiConfig()));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator, connectorService, ledgerService);
    GetOnePaymentStrategy getOnePaymentStrategy =
        new GetOnePaymentStrategy(
            "Strategy",
            new Account("42", TokenPaymentType.CARD, "ABC123"),
            "42",
            getPaymentService);

    // Act
    PaymentWithAllLinks actualExecuteLedgerOnlyStrategyResult =
        getOnePaymentStrategy.executeLedgerOnlyStrategy();

    // Assert
    verify(ledgerService).getPaymentTransaction(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    assertNull(actualExecuteLedgerOnlyStrategyResult.getLinks().getCancel());
    PaymentState state2 = actualExecuteLedgerOnlyStrategyResult.getState();
    assertTrue(state2.isFinished());
    assertSame(state, state2);
  }

  /**
   * Test {@link GetOnePaymentStrategy#executeLedgerOnlyStrategy()}.
   *
   * <p>Method under test: {@link GetOnePaymentStrategy#executeLedgerOnlyStrategy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentWithAllLinks GetOnePaymentStrategy.executeLedgerOnlyStrategy()"})
  public void testExecuteLedgerOnlyStrategy2() {
    // Arrange
    PublicApiUriGenerator publicApiUriGenerator = mock(PublicApiUriGenerator.class);
    when(publicApiUriGenerator.getPaymentAuthorisationURI())
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentCancelURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentCaptureURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentEventsURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentRefundsURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    ArrayList<PaymentConnectorResponseLink> links = new ArrayList<>();
    PaymentConnectorResponseLink paymentConnectorResponseLink =
        new PaymentConnectorResponseLink("GET", "GET", "GET", "GET", new HashMap<>());
    links.add(paymentConnectorResponseLink);
    PaymentConnectorResponseLink paymentConnectorResponseLink2 =
        new PaymentConnectorResponseLink("GET", "GET", "GET", "GET", new HashMap<>());
    links.add(paymentConnectorResponseLink2);
    PaymentState state = new PaymentState("Status", true);
    RefundSummary refundSummary = new RefundSummary("Status", 10L, 10L);
    PaymentSettlementSummary settlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    Address billingAddress = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");
    CardDetails cardDetails =
        new CardDetails(
            "42",
            "42",
            "Card Holder Name",
            "2020-03-01",
            billingAddress,
            "Card Brand",
            "Card Type",
            "Wallet Type");
    ExternalMetadata metadata = new ExternalMetadata(new HashMap<>());
    AuthorisationSummary authorisationSummary = new AuthorisationSummary();

    Charge charge =
        new Charge(
            "42",
            10L,
            state,
            "https://example.org/example",
            "The characteristics of someone or something",
            "Reference",
            "jane.doe@example.org",
            "Payment Provider",
            "2020-03-01",
            SupportedLanguage.ENGLISH,
            true,
            true,
            refundSummary,
            settlementSummary,
            cardDetails,
            links,
            1L,
            1L,
            "42",
            metadata,
            1L,
            1L,
            authorisationSummary,
            "42",
            AuthorisationMode.WEB,
            AgreementPaymentType.INSTALMENT,
            new Exemption());

    LedgerService ledgerService = mock(LedgerService.class);
    when(ledgerService.getPaymentTransaction(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(charge);
    Client client = mock(Client.class);
    ConnectorService connectorService =
        new ConnectorService(client, new ConnectorUriGenerator(new PublicApiConfig()));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator, connectorService, ledgerService);
    GetOnePaymentStrategy getOnePaymentStrategy =
        new GetOnePaymentStrategy(
            "Strategy",
            new Account("42", TokenPaymentType.CARD, "ABC123"),
            "42",
            getPaymentService);

    // Act
    PaymentWithAllLinks actualExecuteLedgerOnlyStrategyResult =
        getOnePaymentStrategy.executeLedgerOnlyStrategy();

    // Assert
    verify(ledgerService).getPaymentTransaction(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    assertNull(actualExecuteLedgerOnlyStrategyResult.getLinks().getCancel());
    PaymentState state2 = actualExecuteLedgerOnlyStrategyResult.getState();
    assertTrue(state2.isFinished());
    assertSame(state, state2);
  }

  /**
   * Test {@link GetOnePaymentStrategy#executeLedgerOnlyStrategy()}.
   *
   * <ul>
   *   <li>Then calls {@link GetPaymentService#getLedgerTransaction(Account, String)}.
   * </ul>
   *
   * <p>Method under test: {@link GetOnePaymentStrategy#executeLedgerOnlyStrategy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentWithAllLinks GetOnePaymentStrategy.executeLedgerOnlyStrategy()"})
  public void testExecuteLedgerOnlyStrategy_thenCallsGetLedgerTransaction() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();

    GetPaymentService getPaymentService = mock(GetPaymentService.class);

    PaymentWithAllLinksBuilder withAuthorisationModeResult =
        new PaymentWithAllLinksBuilder()
            .withAgreementId("42")
            .withAgreementPaymentType(AgreementPaymentType.INSTALMENT)
            .withAmount(10L)
            .withAuthorisationMode(AuthorisationMode.WEB);

    PaymentWithAllLinksBuilder withAuthorisationSummaryResult =
        withAuthorisationModeResult.withAuthorisationSummary(new AuthorisationSummary());
    Address billingAddress = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");
    CardDetails cardDetails =
        new CardDetails(
            "42",
            "42",
            "Card Holder Name",
            "2020-03-01",
            billingAddress,
            "Card Brand",
            "Card Type",
            "Wallet Type");

    PaymentWithAllLinksBuilder withEmailResult =
        withAuthorisationSummaryResult
            .withCardDetails(cardDetails)
            .withChargeId("42")
            .withCorporateCardSurcharge(1L)
            .withCreatedDate("2020-03-01")
            .withDelayedCapture(true)
            .withDescription("The characteristics of someone or something")
            .withEmail("jane.doe@example.org");

    PaymentWithAllLinksBuilder withLanguageResult =
        withEmailResult
            .withExemption(new Exemption())
            .withFee(1L)
            .withLanguage(SupportedLanguage.ENGLISH);

    PaymentWithAllLinksBuilder withPaymentCaptureUriResult =
        withLanguageResult
            .withMetadata(new ExternalMetadata(new HashMap<>()))
            .withMoto(true)
            .withNetAmount(1L)
            .withPaymentAuthorisationUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentCancelUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentCaptureUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    PaymentWithAllLinksBuilder withReferenceResult =
        withPaymentCaptureUriResult
            .withPaymentConnectorResponseLinks(new ArrayList<>())
            .withPaymentEventsUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentProvider("Payment Provider")
            .withPaymentRefundsUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withProviderId("42")
            .withReference("Reference");

    PaymentWithAllLinksBuilder withSelfLinkResult =
        withReferenceResult
            .withRefundSummary(new RefundSummary("Status", 10L, 10L))
            .withReturnUrl("https://example.org/example")
            .withSelfLink(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    PaymentSettlementSummary settlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");

    PaymentWithAllLinksBuilder withSettlementSummaryResult =
        withSelfLinkResult.withSettlementSummary(settlementSummary);
    when(getPaymentService.getLedgerTransaction(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(
            withSettlementSummaryResult
                .withState(new PaymentState("Status", true))
                .withTotalAmount(1L)
                .build());
    GetOnePaymentStrategy getOnePaymentStrategy =
        new GetOnePaymentStrategy(
            "Strategy",
            new Account("42", TokenPaymentType.CARD, "ABC123"),
            "42",
            getPaymentService);

    // Act
    getOnePaymentStrategy.executeLedgerOnlyStrategy();

    // Assert
    verify(getPaymentService).getLedgerTransaction(isA(Account.class), eq("42"));
  }

  /**
   * Test {@link GetOnePaymentStrategy#executeLedgerOnlyStrategy()}.
   *
   * <ul>
   *   <li>Then return Links Cancel is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link GetOnePaymentStrategy#executeLedgerOnlyStrategy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentWithAllLinks GetOnePaymentStrategy.executeLedgerOnlyStrategy()"})
  public void testExecuteLedgerOnlyStrategy_thenReturnLinksCancelIsNull() {
    // Arrange
    PublicApiUriGenerator publicApiUriGenerator = mock(PublicApiUriGenerator.class);
    when(publicApiUriGenerator.getPaymentAuthorisationURI())
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentCancelURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentCaptureURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentEventsURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentRefundsURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    LedgerService ledgerService = mock(LedgerService.class);
    PaymentState state = new PaymentState("Status", true);
    RefundSummary refundSummary = new RefundSummary("Status", 10L, 10L);
    PaymentSettlementSummary settlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    Address billingAddress = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");
    CardDetails cardDetails =
        new CardDetails(
            "42",
            "42",
            "Card Holder Name",
            "2020-03-01",
            billingAddress,
            "Card Brand",
            "Card Type",
            "Wallet Type");
    ArrayList<PaymentConnectorResponseLink> links = new ArrayList<>();
    ExternalMetadata metadata = new ExternalMetadata(new HashMap<>());
    AuthorisationSummary authorisationSummary = new AuthorisationSummary();

    Charge charge =
        new Charge(
            "42",
            10L,
            state,
            "https://example.org/example",
            "The characteristics of someone or something",
            "Reference",
            "jane.doe@example.org",
            "Payment Provider",
            "2020-03-01",
            SupportedLanguage.ENGLISH,
            true,
            true,
            refundSummary,
            settlementSummary,
            cardDetails,
            links,
            1L,
            1L,
            "42",
            metadata,
            1L,
            1L,
            authorisationSummary,
            "42",
            AuthorisationMode.WEB,
            AgreementPaymentType.INSTALMENT,
            new Exemption());
    when(ledgerService.getPaymentTransaction(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(charge);
    Client client = mock(Client.class);
    ConnectorService connectorService =
        new ConnectorService(client, new ConnectorUriGenerator(new PublicApiConfig()));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator, connectorService, ledgerService);
    GetOnePaymentStrategy getOnePaymentStrategy =
        new GetOnePaymentStrategy(
            "Strategy",
            new Account("42", TokenPaymentType.CARD, "ABC123"),
            "42",
            getPaymentService);

    // Act
    PaymentWithAllLinks actualExecuteLedgerOnlyStrategyResult =
        getOnePaymentStrategy.executeLedgerOnlyStrategy();

    // Assert
    verify(ledgerService).getPaymentTransaction(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    assertNull(actualExecuteLedgerOnlyStrategyResult.getLinks().getCancel());
    PaymentState state2 = actualExecuteLedgerOnlyStrategyResult.getState();
    assertTrue(state2.isFinished());
    assertSame(state, state2);
  }

  /**
   * Test {@link GetOnePaymentStrategy#executeLedgerOnlyStrategy()}.
   *
   * <ul>
   *   <li>Then return Links Cancel Method is {@code POST}.
   * </ul>
   *
   * <p>Method under test: {@link GetOnePaymentStrategy#executeLedgerOnlyStrategy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentWithAllLinks GetOnePaymentStrategy.executeLedgerOnlyStrategy()"})
  public void testExecuteLedgerOnlyStrategy_thenReturnLinksCancelMethodIsPost() {
    // Arrange
    PublicApiUriGenerator publicApiUriGenerator = mock(PublicApiUriGenerator.class);
    when(publicApiUriGenerator.getPaymentAuthorisationURI())
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentCancelURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentCaptureURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentEventsURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentRefundsURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    LedgerService ledgerService = mock(LedgerService.class);
    PaymentState state = new PaymentState("Status", false);
    RefundSummary refundSummary = new RefundSummary("Status", 10L, 10L);
    PaymentSettlementSummary settlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    Address billingAddress = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");
    CardDetails cardDetails =
        new CardDetails(
            "42",
            "42",
            "Card Holder Name",
            "2020-03-01",
            billingAddress,
            "Card Brand",
            "Card Type",
            "Wallet Type");
    ArrayList<PaymentConnectorResponseLink> links = new ArrayList<>();
    ExternalMetadata metadata = new ExternalMetadata(new HashMap<>());
    AuthorisationSummary authorisationSummary = new AuthorisationSummary();

    Charge charge =
        new Charge(
            "42",
            10L,
            state,
            "https://example.org/example",
            "The characteristics of someone or something",
            "Reference",
            "jane.doe@example.org",
            "Payment Provider",
            "2020-03-01",
            SupportedLanguage.ENGLISH,
            true,
            true,
            refundSummary,
            settlementSummary,
            cardDetails,
            links,
            1L,
            1L,
            "42",
            metadata,
            1L,
            1L,
            authorisationSummary,
            "42",
            AuthorisationMode.WEB,
            AgreementPaymentType.INSTALMENT,
            new Exemption());
    when(ledgerService.getPaymentTransaction(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(charge);
    Client client = mock(Client.class);
    ConnectorService connectorService =
        new ConnectorService(client, new ConnectorUriGenerator(new PublicApiConfig()));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator, connectorService, ledgerService);
    GetOnePaymentStrategy getOnePaymentStrategy =
        new GetOnePaymentStrategy(
            "Strategy",
            new Account("42", TokenPaymentType.CARD, "ABC123"),
            "42",
            getPaymentService);

    // Act
    PaymentWithAllLinks actualExecuteLedgerOnlyStrategyResult =
        getOnePaymentStrategy.executeLedgerOnlyStrategy();

    // Assert
    verify(ledgerService).getPaymentTransaction(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    PostLink cancel = actualExecuteLedgerOnlyStrategyResult.getLinks().getCancel();
    assertEquals("POST", cancel.getMethod());
    assertNull(cancel.getType());
    assertNull(cancel.getParams());
    PaymentState state2 = actualExecuteLedgerOnlyStrategyResult.getState();
    assertFalse(state2.isFinished());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        cancel.getHref());
    assertSame(state, state2);
  }

  /**
   * Test {@link GetOnePaymentStrategy#executeDefaultStrategy()}.
   *
   * <p>Method under test: {@link GetOnePaymentStrategy#executeDefaultStrategy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentWithAllLinks GetOnePaymentStrategy.executeDefaultStrategy()"})
  public void testExecuteDefaultStrategy() {
    // Arrange
    PublicApiUriGenerator publicApiUriGenerator = mock(PublicApiUriGenerator.class);
    when(publicApiUriGenerator.getPaymentAuthorisationURI())
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentCancelURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentCaptureURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentEventsURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentRefundsURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    ArrayList<PaymentConnectorResponseLink> links = new ArrayList<>();
    PaymentConnectorResponseLink paymentConnectorResponseLink =
        new PaymentConnectorResponseLink("GET", "GET", "GET", "GET", new HashMap<>());
    links.add(paymentConnectorResponseLink);
    PaymentState state = new PaymentState("Status", true);
    RefundSummary refundSummary = new RefundSummary("Status", 10L, 10L);
    PaymentSettlementSummary settlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    Address billingAddress = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");
    CardDetails cardDetails =
        new CardDetails(
            "42",
            "42",
            "Card Holder Name",
            "2020-03-01",
            billingAddress,
            "Card Brand",
            "Card Type",
            "Wallet Type");
    ExternalMetadata metadata = new ExternalMetadata(new HashMap<>());
    AuthorisationSummary authorisationSummary = new AuthorisationSummary();

    Charge charge =
        new Charge(
            "42",
            10L,
            state,
            "https://example.org/example",
            "The characteristics of someone or something",
            "Reference",
            "jane.doe@example.org",
            "Payment Provider",
            "2020-03-01",
            SupportedLanguage.ENGLISH,
            true,
            true,
            refundSummary,
            settlementSummary,
            cardDetails,
            links,
            1L,
            1L,
            "42",
            metadata,
            1L,
            1L,
            authorisationSummary,
            "42",
            AuthorisationMode.WEB,
            AgreementPaymentType.INSTALMENT,
            new Exemption());

    ConnectorService connectorService = mock(ConnectorService.class);
    when(connectorService.getCharge(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(charge);
    Client client = mock(Client.class);
    LedgerService ledgerService =
        new LedgerService(client, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator, connectorService, ledgerService);
    GetOnePaymentStrategy getOnePaymentStrategy =
        new GetOnePaymentStrategy(
            "Strategy",
            new Account("42", TokenPaymentType.CARD, "ABC123"),
            "42",
            getPaymentService);

    // Act
    PaymentWithAllLinks actualExecuteDefaultStrategyResult =
        getOnePaymentStrategy.executeDefaultStrategy();

    // Assert
    verify(connectorService).getCharge(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    assertNull(actualExecuteDefaultStrategyResult.getLinks().getCancel());
    PaymentState state2 = actualExecuteDefaultStrategyResult.getState();
    assertTrue(state2.isFinished());
    assertSame(state, state2);
  }

  /**
   * Test {@link GetOnePaymentStrategy#executeDefaultStrategy()}.
   *
   * <p>Method under test: {@link GetOnePaymentStrategy#executeDefaultStrategy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentWithAllLinks GetOnePaymentStrategy.executeDefaultStrategy()"})
  public void testExecuteDefaultStrategy2() {
    // Arrange
    PublicApiUriGenerator publicApiUriGenerator = mock(PublicApiUriGenerator.class);
    when(publicApiUriGenerator.getPaymentAuthorisationURI())
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentCancelURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentCaptureURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentEventsURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentRefundsURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    ArrayList<PaymentConnectorResponseLink> links = new ArrayList<>();
    PaymentConnectorResponseLink paymentConnectorResponseLink =
        new PaymentConnectorResponseLink("GET", "GET", "GET", "GET", new HashMap<>());
    links.add(paymentConnectorResponseLink);
    PaymentConnectorResponseLink paymentConnectorResponseLink2 =
        new PaymentConnectorResponseLink("GET", "GET", "GET", "GET", new HashMap<>());
    links.add(paymentConnectorResponseLink2);
    PaymentState state = new PaymentState("Status", true);
    RefundSummary refundSummary = new RefundSummary("Status", 10L, 10L);
    PaymentSettlementSummary settlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    Address billingAddress = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");
    CardDetails cardDetails =
        new CardDetails(
            "42",
            "42",
            "Card Holder Name",
            "2020-03-01",
            billingAddress,
            "Card Brand",
            "Card Type",
            "Wallet Type");
    ExternalMetadata metadata = new ExternalMetadata(new HashMap<>());
    AuthorisationSummary authorisationSummary = new AuthorisationSummary();

    Charge charge =
        new Charge(
            "42",
            10L,
            state,
            "https://example.org/example",
            "The characteristics of someone or something",
            "Reference",
            "jane.doe@example.org",
            "Payment Provider",
            "2020-03-01",
            SupportedLanguage.ENGLISH,
            true,
            true,
            refundSummary,
            settlementSummary,
            cardDetails,
            links,
            1L,
            1L,
            "42",
            metadata,
            1L,
            1L,
            authorisationSummary,
            "42",
            AuthorisationMode.WEB,
            AgreementPaymentType.INSTALMENT,
            new Exemption());

    ConnectorService connectorService = mock(ConnectorService.class);
    when(connectorService.getCharge(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(charge);
    Client client = mock(Client.class);
    LedgerService ledgerService =
        new LedgerService(client, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator, connectorService, ledgerService);
    GetOnePaymentStrategy getOnePaymentStrategy =
        new GetOnePaymentStrategy(
            "Strategy",
            new Account("42", TokenPaymentType.CARD, "ABC123"),
            "42",
            getPaymentService);

    // Act
    PaymentWithAllLinks actualExecuteDefaultStrategyResult =
        getOnePaymentStrategy.executeDefaultStrategy();

    // Assert
    verify(connectorService).getCharge(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    assertNull(actualExecuteDefaultStrategyResult.getLinks().getCancel());
    PaymentState state2 = actualExecuteDefaultStrategyResult.getState();
    assertTrue(state2.isFinished());
    assertSame(state, state2);
  }

  /**
   * Test {@link GetOnePaymentStrategy#executeDefaultStrategy()}.
   *
   * <ul>
   *   <li>Then calls {@link GetPaymentService#getPayment(Account, String)}.
   * </ul>
   *
   * <p>Method under test: {@link GetOnePaymentStrategy#executeDefaultStrategy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentWithAllLinks GetOnePaymentStrategy.executeDefaultStrategy()"})
  public void testExecuteDefaultStrategy_thenCallsGetPayment() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();

    GetPaymentService getPaymentService = mock(GetPaymentService.class);

    PaymentWithAllLinksBuilder withAuthorisationModeResult =
        new PaymentWithAllLinksBuilder()
            .withAgreementId("42")
            .withAgreementPaymentType(AgreementPaymentType.INSTALMENT)
            .withAmount(10L)
            .withAuthorisationMode(AuthorisationMode.WEB);

    PaymentWithAllLinksBuilder withAuthorisationSummaryResult =
        withAuthorisationModeResult.withAuthorisationSummary(new AuthorisationSummary());
    Address billingAddress = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");
    CardDetails cardDetails =
        new CardDetails(
            "42",
            "42",
            "Card Holder Name",
            "2020-03-01",
            billingAddress,
            "Card Brand",
            "Card Type",
            "Wallet Type");

    PaymentWithAllLinksBuilder withEmailResult =
        withAuthorisationSummaryResult
            .withCardDetails(cardDetails)
            .withChargeId("42")
            .withCorporateCardSurcharge(1L)
            .withCreatedDate("2020-03-01")
            .withDelayedCapture(true)
            .withDescription("The characteristics of someone or something")
            .withEmail("jane.doe@example.org");

    PaymentWithAllLinksBuilder withLanguageResult =
        withEmailResult
            .withExemption(new Exemption())
            .withFee(1L)
            .withLanguage(SupportedLanguage.ENGLISH);

    PaymentWithAllLinksBuilder withPaymentCaptureUriResult =
        withLanguageResult
            .withMetadata(new ExternalMetadata(new HashMap<>()))
            .withMoto(true)
            .withNetAmount(1L)
            .withPaymentAuthorisationUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentCancelUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentCaptureUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    PaymentWithAllLinksBuilder withReferenceResult =
        withPaymentCaptureUriResult
            .withPaymentConnectorResponseLinks(new ArrayList<>())
            .withPaymentEventsUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentProvider("Payment Provider")
            .withPaymentRefundsUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withProviderId("42")
            .withReference("Reference");

    PaymentWithAllLinksBuilder withSelfLinkResult =
        withReferenceResult
            .withRefundSummary(new RefundSummary("Status", 10L, 10L))
            .withReturnUrl("https://example.org/example")
            .withSelfLink(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    PaymentSettlementSummary settlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");

    PaymentWithAllLinksBuilder withSettlementSummaryResult =
        withSelfLinkResult.withSettlementSummary(settlementSummary);
    when(getPaymentService.getPayment(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(
            withSettlementSummaryResult
                .withState(new PaymentState("Status", true))
                .withTotalAmount(1L)
                .build());
    GetOnePaymentStrategy getOnePaymentStrategy =
        new GetOnePaymentStrategy(
            "Strategy",
            new Account("42", TokenPaymentType.CARD, "ABC123"),
            "42",
            getPaymentService);

    // Act
    getOnePaymentStrategy.executeDefaultStrategy();

    // Assert
    verify(getPaymentService).getPayment(isA(Account.class), eq("42"));
  }

  /**
   * Test {@link GetOnePaymentStrategy#executeDefaultStrategy()}.
   *
   * <ul>
   *   <li>Then return Links Cancel is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link GetOnePaymentStrategy#executeDefaultStrategy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentWithAllLinks GetOnePaymentStrategy.executeDefaultStrategy()"})
  public void testExecuteDefaultStrategy_thenReturnLinksCancelIsNull() {
    // Arrange
    PublicApiUriGenerator publicApiUriGenerator = mock(PublicApiUriGenerator.class);
    when(publicApiUriGenerator.getPaymentAuthorisationURI())
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentCancelURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentCaptureURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentEventsURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentRefundsURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    ConnectorService connectorService = mock(ConnectorService.class);
    PaymentState state = new PaymentState("Status", true);
    RefundSummary refundSummary = new RefundSummary("Status", 10L, 10L);
    PaymentSettlementSummary settlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    Address billingAddress = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");
    CardDetails cardDetails =
        new CardDetails(
            "42",
            "42",
            "Card Holder Name",
            "2020-03-01",
            billingAddress,
            "Card Brand",
            "Card Type",
            "Wallet Type");
    ArrayList<PaymentConnectorResponseLink> links = new ArrayList<>();
    ExternalMetadata metadata = new ExternalMetadata(new HashMap<>());
    AuthorisationSummary authorisationSummary = new AuthorisationSummary();

    Charge charge =
        new Charge(
            "42",
            10L,
            state,
            "https://example.org/example",
            "The characteristics of someone or something",
            "Reference",
            "jane.doe@example.org",
            "Payment Provider",
            "2020-03-01",
            SupportedLanguage.ENGLISH,
            true,
            true,
            refundSummary,
            settlementSummary,
            cardDetails,
            links,
            1L,
            1L,
            "42",
            metadata,
            1L,
            1L,
            authorisationSummary,
            "42",
            AuthorisationMode.WEB,
            AgreementPaymentType.INSTALMENT,
            new Exemption());
    when(connectorService.getCharge(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(charge);
    Client client = mock(Client.class);
    LedgerService ledgerService =
        new LedgerService(client, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator, connectorService, ledgerService);
    GetOnePaymentStrategy getOnePaymentStrategy =
        new GetOnePaymentStrategy(
            "Strategy",
            new Account("42", TokenPaymentType.CARD, "ABC123"),
            "42",
            getPaymentService);

    // Act
    PaymentWithAllLinks actualExecuteDefaultStrategyResult =
        getOnePaymentStrategy.executeDefaultStrategy();

    // Assert
    verify(connectorService).getCharge(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    assertNull(actualExecuteDefaultStrategyResult.getLinks().getCancel());
    PaymentState state2 = actualExecuteDefaultStrategyResult.getState();
    assertTrue(state2.isFinished());
    assertSame(state, state2);
  }

  /**
   * Test {@link GetOnePaymentStrategy#executeDefaultStrategy()}.
   *
   * <ul>
   *   <li>Then return Links Cancel Method is {@code POST}.
   * </ul>
   *
   * <p>Method under test: {@link GetOnePaymentStrategy#executeDefaultStrategy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentWithAllLinks GetOnePaymentStrategy.executeDefaultStrategy()"})
  public void testExecuteDefaultStrategy_thenReturnLinksCancelMethodIsPost() {
    // Arrange
    PublicApiUriGenerator publicApiUriGenerator = mock(PublicApiUriGenerator.class);
    when(publicApiUriGenerator.getPaymentAuthorisationURI())
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentCancelURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentCaptureURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentEventsURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentRefundsURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    ConnectorService connectorService = mock(ConnectorService.class);
    PaymentState state = new PaymentState("Status", false);
    RefundSummary refundSummary = new RefundSummary("Status", 10L, 10L);
    PaymentSettlementSummary settlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    Address billingAddress = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");
    CardDetails cardDetails =
        new CardDetails(
            "42",
            "42",
            "Card Holder Name",
            "2020-03-01",
            billingAddress,
            "Card Brand",
            "Card Type",
            "Wallet Type");
    ArrayList<PaymentConnectorResponseLink> links = new ArrayList<>();
    ExternalMetadata metadata = new ExternalMetadata(new HashMap<>());
    AuthorisationSummary authorisationSummary = new AuthorisationSummary();

    Charge charge =
        new Charge(
            "42",
            10L,
            state,
            "https://example.org/example",
            "The characteristics of someone or something",
            "Reference",
            "jane.doe@example.org",
            "Payment Provider",
            "2020-03-01",
            SupportedLanguage.ENGLISH,
            true,
            true,
            refundSummary,
            settlementSummary,
            cardDetails,
            links,
            1L,
            1L,
            "42",
            metadata,
            1L,
            1L,
            authorisationSummary,
            "42",
            AuthorisationMode.WEB,
            AgreementPaymentType.INSTALMENT,
            new Exemption());
    when(connectorService.getCharge(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(charge);
    Client client = mock(Client.class);
    LedgerService ledgerService =
        new LedgerService(client, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator, connectorService, ledgerService);
    GetOnePaymentStrategy getOnePaymentStrategy =
        new GetOnePaymentStrategy(
            "Strategy",
            new Account("42", TokenPaymentType.CARD, "ABC123"),
            "42",
            getPaymentService);

    // Act
    PaymentWithAllLinks actualExecuteDefaultStrategyResult =
        getOnePaymentStrategy.executeDefaultStrategy();

    // Assert
    verify(connectorService).getCharge(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    PostLink cancel = actualExecuteDefaultStrategyResult.getLinks().getCancel();
    assertEquals("POST", cancel.getMethod());
    assertNull(cancel.getType());
    assertNull(cancel.getParams());
    PaymentState state2 = actualExecuteDefaultStrategyResult.getState();
    assertFalse(state2.isFinished());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        cancel.getHref());
    assertSame(state, state2);
  }

  /**
   * Test {@link GetOnePaymentStrategy#executeConnectorOnlyStrategy()}.
   *
   * <p>Method under test: {@link GetOnePaymentStrategy#executeConnectorOnlyStrategy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentWithAllLinks GetOnePaymentStrategy.executeConnectorOnlyStrategy()"})
  public void testExecuteConnectorOnlyStrategy() {
    // Arrange
    PublicApiUriGenerator publicApiUriGenerator = mock(PublicApiUriGenerator.class);
    when(publicApiUriGenerator.getPaymentAuthorisationURI())
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentCancelURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentCaptureURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentEventsURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentRefundsURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    ArrayList<PaymentConnectorResponseLink> links = new ArrayList<>();
    PaymentConnectorResponseLink paymentConnectorResponseLink =
        new PaymentConnectorResponseLink("GET", "GET", "GET", "GET", new HashMap<>());
    links.add(paymentConnectorResponseLink);
    PaymentState state = new PaymentState("Status", true);
    RefundSummary refundSummary = new RefundSummary("Status", 10L, 10L);
    PaymentSettlementSummary settlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    Address billingAddress = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");
    CardDetails cardDetails =
        new CardDetails(
            "42",
            "42",
            "Card Holder Name",
            "2020-03-01",
            billingAddress,
            "Card Brand",
            "Card Type",
            "Wallet Type");
    ExternalMetadata metadata = new ExternalMetadata(new HashMap<>());
    AuthorisationSummary authorisationSummary = new AuthorisationSummary();

    Charge charge =
        new Charge(
            "42",
            10L,
            state,
            "https://example.org/example",
            "The characteristics of someone or something",
            "Reference",
            "jane.doe@example.org",
            "Payment Provider",
            "2020-03-01",
            SupportedLanguage.ENGLISH,
            true,
            true,
            refundSummary,
            settlementSummary,
            cardDetails,
            links,
            1L,
            1L,
            "42",
            metadata,
            1L,
            1L,
            authorisationSummary,
            "42",
            AuthorisationMode.WEB,
            AgreementPaymentType.INSTALMENT,
            new Exemption());

    ConnectorService connectorService = mock(ConnectorService.class);
    when(connectorService.getCharge(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(charge);
    Client client = mock(Client.class);
    LedgerService ledgerService =
        new LedgerService(client, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator, connectorService, ledgerService);
    GetOnePaymentStrategy getOnePaymentStrategy =
        new GetOnePaymentStrategy(
            "Strategy",
            new Account("42", TokenPaymentType.CARD, "ABC123"),
            "42",
            getPaymentService);

    // Act
    PaymentWithAllLinks actualExecuteConnectorOnlyStrategyResult =
        getOnePaymentStrategy.executeConnectorOnlyStrategy();

    // Assert
    verify(connectorService).getCharge(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    assertNull(actualExecuteConnectorOnlyStrategyResult.getLinks().getCancel());
    PaymentState state2 = actualExecuteConnectorOnlyStrategyResult.getState();
    assertTrue(state2.isFinished());
    assertSame(state, state2);
  }

  /**
   * Test {@link GetOnePaymentStrategy#executeConnectorOnlyStrategy()}.
   *
   * <p>Method under test: {@link GetOnePaymentStrategy#executeConnectorOnlyStrategy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentWithAllLinks GetOnePaymentStrategy.executeConnectorOnlyStrategy()"})
  public void testExecuteConnectorOnlyStrategy2() {
    // Arrange
    PublicApiUriGenerator publicApiUriGenerator = mock(PublicApiUriGenerator.class);
    when(publicApiUriGenerator.getPaymentAuthorisationURI())
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentCancelURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentCaptureURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentEventsURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentRefundsURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    ArrayList<PaymentConnectorResponseLink> links = new ArrayList<>();
    PaymentConnectorResponseLink paymentConnectorResponseLink =
        new PaymentConnectorResponseLink("GET", "GET", "GET", "GET", new HashMap<>());
    links.add(paymentConnectorResponseLink);
    PaymentConnectorResponseLink paymentConnectorResponseLink2 =
        new PaymentConnectorResponseLink("GET", "GET", "GET", "GET", new HashMap<>());
    links.add(paymentConnectorResponseLink2);
    PaymentState state = new PaymentState("Status", true);
    RefundSummary refundSummary = new RefundSummary("Status", 10L, 10L);
    PaymentSettlementSummary settlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    Address billingAddress = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");
    CardDetails cardDetails =
        new CardDetails(
            "42",
            "42",
            "Card Holder Name",
            "2020-03-01",
            billingAddress,
            "Card Brand",
            "Card Type",
            "Wallet Type");
    ExternalMetadata metadata = new ExternalMetadata(new HashMap<>());
    AuthorisationSummary authorisationSummary = new AuthorisationSummary();

    Charge charge =
        new Charge(
            "42",
            10L,
            state,
            "https://example.org/example",
            "The characteristics of someone or something",
            "Reference",
            "jane.doe@example.org",
            "Payment Provider",
            "2020-03-01",
            SupportedLanguage.ENGLISH,
            true,
            true,
            refundSummary,
            settlementSummary,
            cardDetails,
            links,
            1L,
            1L,
            "42",
            metadata,
            1L,
            1L,
            authorisationSummary,
            "42",
            AuthorisationMode.WEB,
            AgreementPaymentType.INSTALMENT,
            new Exemption());

    ConnectorService connectorService = mock(ConnectorService.class);
    when(connectorService.getCharge(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(charge);
    Client client = mock(Client.class);
    LedgerService ledgerService =
        new LedgerService(client, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator, connectorService, ledgerService);
    GetOnePaymentStrategy getOnePaymentStrategy =
        new GetOnePaymentStrategy(
            "Strategy",
            new Account("42", TokenPaymentType.CARD, "ABC123"),
            "42",
            getPaymentService);

    // Act
    PaymentWithAllLinks actualExecuteConnectorOnlyStrategyResult =
        getOnePaymentStrategy.executeConnectorOnlyStrategy();

    // Assert
    verify(connectorService).getCharge(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    assertNull(actualExecuteConnectorOnlyStrategyResult.getLinks().getCancel());
    PaymentState state2 = actualExecuteConnectorOnlyStrategyResult.getState();
    assertTrue(state2.isFinished());
    assertSame(state, state2);
  }

  /**
   * Test {@link GetOnePaymentStrategy#executeConnectorOnlyStrategy()}.
   *
   * <ul>
   *   <li>Then calls {@link GetPaymentService#getConnectorCharge(Account, String)}.
   * </ul>
   *
   * <p>Method under test: {@link GetOnePaymentStrategy#executeConnectorOnlyStrategy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentWithAllLinks GetOnePaymentStrategy.executeConnectorOnlyStrategy()"})
  public void testExecuteConnectorOnlyStrategy_thenCallsGetConnectorCharge() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();

    GetPaymentService getPaymentService = mock(GetPaymentService.class);

    PaymentWithAllLinksBuilder withAuthorisationModeResult =
        new PaymentWithAllLinksBuilder()
            .withAgreementId("42")
            .withAgreementPaymentType(AgreementPaymentType.INSTALMENT)
            .withAmount(10L)
            .withAuthorisationMode(AuthorisationMode.WEB);

    PaymentWithAllLinksBuilder withAuthorisationSummaryResult =
        withAuthorisationModeResult.withAuthorisationSummary(new AuthorisationSummary());
    Address billingAddress = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");
    CardDetails cardDetails =
        new CardDetails(
            "42",
            "42",
            "Card Holder Name",
            "2020-03-01",
            billingAddress,
            "Card Brand",
            "Card Type",
            "Wallet Type");

    PaymentWithAllLinksBuilder withEmailResult =
        withAuthorisationSummaryResult
            .withCardDetails(cardDetails)
            .withChargeId("42")
            .withCorporateCardSurcharge(1L)
            .withCreatedDate("2020-03-01")
            .withDelayedCapture(true)
            .withDescription("The characteristics of someone or something")
            .withEmail("jane.doe@example.org");

    PaymentWithAllLinksBuilder withLanguageResult =
        withEmailResult
            .withExemption(new Exemption())
            .withFee(1L)
            .withLanguage(SupportedLanguage.ENGLISH);

    PaymentWithAllLinksBuilder withPaymentCaptureUriResult =
        withLanguageResult
            .withMetadata(new ExternalMetadata(new HashMap<>()))
            .withMoto(true)
            .withNetAmount(1L)
            .withPaymentAuthorisationUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentCancelUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentCaptureUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    PaymentWithAllLinksBuilder withReferenceResult =
        withPaymentCaptureUriResult
            .withPaymentConnectorResponseLinks(new ArrayList<>())
            .withPaymentEventsUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentProvider("Payment Provider")
            .withPaymentRefundsUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withProviderId("42")
            .withReference("Reference");

    PaymentWithAllLinksBuilder withSelfLinkResult =
        withReferenceResult
            .withRefundSummary(new RefundSummary("Status", 10L, 10L))
            .withReturnUrl("https://example.org/example")
            .withSelfLink(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    PaymentSettlementSummary settlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");

    PaymentWithAllLinksBuilder withSettlementSummaryResult =
        withSelfLinkResult.withSettlementSummary(settlementSummary);
    when(getPaymentService.getConnectorCharge(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(
            withSettlementSummaryResult
                .withState(new PaymentState("Status", true))
                .withTotalAmount(1L)
                .build());
    GetOnePaymentStrategy getOnePaymentStrategy =
        new GetOnePaymentStrategy(
            "Strategy",
            new Account("42", TokenPaymentType.CARD, "ABC123"),
            "42",
            getPaymentService);

    // Act
    getOnePaymentStrategy.executeConnectorOnlyStrategy();

    // Assert
    verify(getPaymentService).getConnectorCharge(isA(Account.class), eq("42"));
  }

  /**
   * Test {@link GetOnePaymentStrategy#executeConnectorOnlyStrategy()}.
   *
   * <ul>
   *   <li>Then return Links Cancel is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link GetOnePaymentStrategy#executeConnectorOnlyStrategy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentWithAllLinks GetOnePaymentStrategy.executeConnectorOnlyStrategy()"})
  public void testExecuteConnectorOnlyStrategy_thenReturnLinksCancelIsNull() {
    // Arrange
    PublicApiUriGenerator publicApiUriGenerator = mock(PublicApiUriGenerator.class);
    when(publicApiUriGenerator.getPaymentAuthorisationURI())
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentCancelURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentCaptureURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentEventsURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentRefundsURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    ConnectorService connectorService = mock(ConnectorService.class);
    PaymentState state = new PaymentState("Status", true);
    RefundSummary refundSummary = new RefundSummary("Status", 10L, 10L);
    PaymentSettlementSummary settlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    Address billingAddress = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");
    CardDetails cardDetails =
        new CardDetails(
            "42",
            "42",
            "Card Holder Name",
            "2020-03-01",
            billingAddress,
            "Card Brand",
            "Card Type",
            "Wallet Type");
    ArrayList<PaymentConnectorResponseLink> links = new ArrayList<>();
    ExternalMetadata metadata = new ExternalMetadata(new HashMap<>());
    AuthorisationSummary authorisationSummary = new AuthorisationSummary();

    Charge charge =
        new Charge(
            "42",
            10L,
            state,
            "https://example.org/example",
            "The characteristics of someone or something",
            "Reference",
            "jane.doe@example.org",
            "Payment Provider",
            "2020-03-01",
            SupportedLanguage.ENGLISH,
            true,
            true,
            refundSummary,
            settlementSummary,
            cardDetails,
            links,
            1L,
            1L,
            "42",
            metadata,
            1L,
            1L,
            authorisationSummary,
            "42",
            AuthorisationMode.WEB,
            AgreementPaymentType.INSTALMENT,
            new Exemption());
    when(connectorService.getCharge(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(charge);
    Client client = mock(Client.class);
    LedgerService ledgerService =
        new LedgerService(client, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator, connectorService, ledgerService);
    GetOnePaymentStrategy getOnePaymentStrategy =
        new GetOnePaymentStrategy(
            "Strategy",
            new Account("42", TokenPaymentType.CARD, "ABC123"),
            "42",
            getPaymentService);

    // Act
    PaymentWithAllLinks actualExecuteConnectorOnlyStrategyResult =
        getOnePaymentStrategy.executeConnectorOnlyStrategy();

    // Assert
    verify(connectorService).getCharge(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    assertNull(actualExecuteConnectorOnlyStrategyResult.getLinks().getCancel());
    PaymentState state2 = actualExecuteConnectorOnlyStrategyResult.getState();
    assertTrue(state2.isFinished());
    assertSame(state, state2);
  }

  /**
   * Test {@link GetOnePaymentStrategy#executeConnectorOnlyStrategy()}.
   *
   * <ul>
   *   <li>Then return Links Cancel Method is {@code POST}.
   * </ul>
   *
   * <p>Method under test: {@link GetOnePaymentStrategy#executeConnectorOnlyStrategy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentWithAllLinks GetOnePaymentStrategy.executeConnectorOnlyStrategy()"})
  public void testExecuteConnectorOnlyStrategy_thenReturnLinksCancelMethodIsPost() {
    // Arrange
    PublicApiUriGenerator publicApiUriGenerator = mock(PublicApiUriGenerator.class);
    when(publicApiUriGenerator.getPaymentAuthorisationURI())
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentCancelURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentCaptureURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentEventsURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentRefundsURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getPaymentURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    ConnectorService connectorService = mock(ConnectorService.class);
    PaymentState state = new PaymentState("Status", false);
    RefundSummary refundSummary = new RefundSummary("Status", 10L, 10L);
    PaymentSettlementSummary settlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    Address billingAddress = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");
    CardDetails cardDetails =
        new CardDetails(
            "42",
            "42",
            "Card Holder Name",
            "2020-03-01",
            billingAddress,
            "Card Brand",
            "Card Type",
            "Wallet Type");
    ArrayList<PaymentConnectorResponseLink> links = new ArrayList<>();
    ExternalMetadata metadata = new ExternalMetadata(new HashMap<>());
    AuthorisationSummary authorisationSummary = new AuthorisationSummary();

    Charge charge =
        new Charge(
            "42",
            10L,
            state,
            "https://example.org/example",
            "The characteristics of someone or something",
            "Reference",
            "jane.doe@example.org",
            "Payment Provider",
            "2020-03-01",
            SupportedLanguage.ENGLISH,
            true,
            true,
            refundSummary,
            settlementSummary,
            cardDetails,
            links,
            1L,
            1L,
            "42",
            metadata,
            1L,
            1L,
            authorisationSummary,
            "42",
            AuthorisationMode.WEB,
            AgreementPaymentType.INSTALMENT,
            new Exemption());
    when(connectorService.getCharge(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(charge);
    Client client = mock(Client.class);
    LedgerService ledgerService =
        new LedgerService(client, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator, connectorService, ledgerService);
    GetOnePaymentStrategy getOnePaymentStrategy =
        new GetOnePaymentStrategy(
            "Strategy",
            new Account("42", TokenPaymentType.CARD, "ABC123"),
            "42",
            getPaymentService);

    // Act
    PaymentWithAllLinks actualExecuteConnectorOnlyStrategyResult =
        getOnePaymentStrategy.executeConnectorOnlyStrategy();

    // Assert
    verify(connectorService).getCharge(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    PostLink cancel = actualExecuteConnectorOnlyStrategyResult.getLinks().getCancel();
    assertEquals("POST", cancel.getMethod());
    assertNull(cancel.getType());
    assertNull(cancel.getParams());
    PaymentState state2 = actualExecuteConnectorOnlyStrategyResult.getState();
    assertFalse(state2.isFinished());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        cancel.getHref());
    assertSame(state, state2);
  }
}
