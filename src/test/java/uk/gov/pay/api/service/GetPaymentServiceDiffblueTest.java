package uk.gov.pay.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
import java.util.HashMap;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
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
import uk.gov.pay.api.model.links.PostLink;
import uk.gov.service.payments.commons.model.AgreementPaymentType;
import uk.gov.service.payments.commons.model.AuthorisationMode;
import uk.gov.service.payments.commons.model.SupportedLanguage;
import uk.gov.service.payments.commons.model.charge.ExternalMetadata;

class GetPaymentServiceDiffblueTest {
  /**
   * Test {@link GetPaymentService#getConnectorCharge(Account, String)}.
   *
   * <p>Method under test: {@link GetPaymentService#getConnectorCharge(Account, String)}
   */
  @Test
  @DisplayName("Test getConnectorCharge(Account, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentWithAllLinks GetPaymentService.getConnectorCharge(Account, String)"})
  void testGetConnectorCharge() {
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

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    LedgerService ledgerService =
        new LedgerService(mock(Client.class), new LedgerUriGenerator(configuration));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator, connectorService, ledgerService);

    // Act
    PaymentWithAllLinks actualConnectorCharge =
        getPaymentService.getConnectorCharge(
            new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(connectorService).getCharge(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    assertNull(actualConnectorCharge.getLinks().getCancel());
    assertTrue(actualConnectorCharge.getDelayedCapture());
    PaymentState state2 = actualConnectorCharge.getState();
    assertTrue(state2.isFinished());
    assertSame(state, state2);
  }

  /**
   * Test {@link GetPaymentService#getConnectorCharge(Account, String)}.
   *
   * <p>Method under test: {@link GetPaymentService#getConnectorCharge(Account, String)}
   */
  @Test
  @DisplayName("Test getConnectorCharge(Account, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentWithAllLinks GetPaymentService.getConnectorCharge(Account, String)"})
  void testGetConnectorCharge2() {
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

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    LedgerService ledgerService =
        new LedgerService(mock(Client.class), new LedgerUriGenerator(configuration));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator, connectorService, ledgerService);

    // Act
    PaymentWithAllLinks actualConnectorCharge =
        getPaymentService.getConnectorCharge(
            new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(connectorService).getCharge(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    assertNull(actualConnectorCharge.getLinks().getCancel());
    assertTrue(actualConnectorCharge.getDelayedCapture());
    PaymentState state2 = actualConnectorCharge.getState();
    assertTrue(state2.isFinished());
    assertSame(state, state2);
  }

  /**
   * Test {@link GetPaymentService#getConnectorCharge(Account, String)}.
   *
   * <ul>
   *   <li>Then calls {@link Charge#getAgreementId()}.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentService#getConnectorCharge(Account, String)}
   */
  @Test
  @DisplayName("Test getConnectorCharge(Account, String); then calls getAgreementId()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentWithAllLinks GetPaymentService.getConnectorCharge(Account, String)"})
  void testGetConnectorCharge_thenCallsGetAgreementId() {
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

    Charge charge = mock(Charge.class);
    when(charge.getDelayedCapture()).thenReturn(true);
    when(charge.isMoto()).thenReturn(true);
    when(charge.getAmount()).thenReturn(10L);
    when(charge.getCorporateCardSurcharge()).thenReturn(1L);
    when(charge.getFee()).thenReturn(1L);
    when(charge.getNetAmount()).thenReturn(1L);
    when(charge.getTotalAmount()).thenReturn(1L);
    when(charge.getAgreementId()).thenReturn("42");
    when(charge.getChargeId()).thenReturn("42");
    when(charge.getCreatedDate()).thenReturn("2020-03-01");
    when(charge.getDescription()).thenReturn("The characteristics of someone or something");
    when(charge.getEmail()).thenReturn("jane.doe@example.org");
    when(charge.getGatewayTransactionId()).thenReturn("42");
    when(charge.getPaymentProvider()).thenReturn("Payment Provider");
    when(charge.getReference()).thenReturn("Reference");
    when(charge.getReturnUrl()).thenReturn("https://example.org/example");
    when(charge.getLinks()).thenReturn(new ArrayList<>());
    Optional<ExternalMetadata> ofResult = Optional.of(new ExternalMetadata(new HashMap<>()));
    when(charge.getMetadata()).thenReturn(ofResult);
    when(charge.getAuthorisationSummary()).thenReturn(new AuthorisationSummary());
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
    when(charge.getCardDetails()).thenReturn(cardDetails);
    when(charge.getExemption()).thenReturn(new Exemption());
    PaymentSettlementSummary paymentSettlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    when(charge.getSettlementSummary()).thenReturn(paymentSettlementSummary);
    PaymentState paymentState = new PaymentState("Status", true);
    when(charge.getState()).thenReturn(paymentState);
    when(charge.getRefundSummary()).thenReturn(new RefundSummary("Status", 10L, 10L));
    when(charge.getAgreementPaymentType()).thenReturn(AgreementPaymentType.INSTALMENT);
    when(charge.getAuthorisationMode()).thenReturn(AuthorisationMode.WEB);
    when(charge.getLanguage()).thenReturn(SupportedLanguage.ENGLISH);

    ConnectorService connectorService = mock(ConnectorService.class);
    when(connectorService.getCharge(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(charge);

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    LedgerService ledgerService =
        new LedgerService(mock(Client.class), new LedgerUriGenerator(configuration));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator, connectorService, ledgerService);

    // Act
    PaymentWithAllLinks actualConnectorCharge =
        getPaymentService.getConnectorCharge(
            new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(charge).getAgreementId();
    verify(charge).getAgreementPaymentType();
    verify(charge).getAmount();
    verify(charge).getAuthorisationMode();
    verify(charge).getAuthorisationSummary();
    verify(charge).getCardDetails();
    verify(charge, atLeast(1)).getChargeId();
    verify(charge).getCorporateCardSurcharge();
    verify(charge).getCreatedDate();
    verify(charge).getDelayedCapture();
    verify(charge).getDescription();
    verify(charge).getEmail();
    verify(charge).getExemption();
    verify(charge).getFee();
    verify(charge).getGatewayTransactionId();
    verify(charge).getLanguage();
    verify(charge).getLinks();
    verify(charge).getMetadata();
    verify(charge).getNetAmount();
    verify(charge).getPaymentProvider();
    verify(charge).getReference();
    verify(charge).getRefundSummary();
    verify(charge).getReturnUrl();
    verify(charge).getSettlementSummary();
    verify(charge).getState();
    verify(charge).getTotalAmount();
    verify(charge).isMoto();
    verify(connectorService).getCharge(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    assertNull(actualConnectorCharge.getLinks().getCancel());
    assertTrue(actualConnectorCharge.getDelayedCapture());
    PaymentState state = actualConnectorCharge.getState();
    assertTrue(state.isFinished());
    assertSame(paymentState, state);
  }

  /**
   * Test {@link GetPaymentService#getConnectorCharge(Account, String)}.
   *
   * <ul>
   *   <li>Then return DelayedCapture.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentService#getConnectorCharge(Account, String)}
   */
  @Test
  @DisplayName("Test getConnectorCharge(Account, String); then return DelayedCapture")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentWithAllLinks GetPaymentService.getConnectorCharge(Account, String)"})
  void testGetConnectorCharge_thenReturnDelayedCapture() {
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

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    LedgerService ledgerService =
        new LedgerService(mock(Client.class), new LedgerUriGenerator(configuration));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator, connectorService, ledgerService);

    // Act
    PaymentWithAllLinks actualConnectorCharge =
        getPaymentService.getConnectorCharge(
            new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(connectorService).getCharge(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    assertNull(actualConnectorCharge.getLinks().getCancel());
    assertTrue(actualConnectorCharge.getDelayedCapture());
    PaymentState state2 = actualConnectorCharge.getState();
    assertTrue(state2.isFinished());
    assertSame(state, state2);
  }

  /**
   * Test {@link GetPaymentService#getConnectorCharge(Account, String)}.
   *
   * <ul>
   *   <li>Then return Links Cancel Method is {@code POST}.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentService#getConnectorCharge(Account, String)}
   */
  @Test
  @DisplayName(
      "Test getConnectorCharge(Account, String); then return Links Cancel Method is 'POST'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentWithAllLinks GetPaymentService.getConnectorCharge(Account, String)"})
  void testGetConnectorCharge_thenReturnLinksCancelMethodIsPost() {
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

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    LedgerService ledgerService =
        new LedgerService(mock(Client.class), new LedgerUriGenerator(configuration));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator, connectorService, ledgerService);

    // Act
    PaymentWithAllLinks actualConnectorCharge =
        getPaymentService.getConnectorCharge(
            new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(connectorService).getCharge(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    PostLink cancel = actualConnectorCharge.getLinks().getCancel();
    assertEquals("POST", cancel.getMethod());
    assertNull(cancel.getType());
    assertNull(cancel.getParams());
    PaymentState state2 = actualConnectorCharge.getState();
    assertFalse(state2.isFinished());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        cancel.getHref());
    assertSame(state, state2);
  }

  /**
   * Test {@link GetPaymentService#getConnectorCharge(Account, String)}.
   *
   * <ul>
   *   <li>Then return not DelayedCapture.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentService#getConnectorCharge(Account, String)}
   */
  @Test
  @DisplayName("Test getConnectorCharge(Account, String); then return not DelayedCapture")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentWithAllLinks GetPaymentService.getConnectorCharge(Account, String)"})
  void testGetConnectorCharge_thenReturnNotDelayedCapture() {
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
            false,
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

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    LedgerService ledgerService =
        new LedgerService(mock(Client.class), new LedgerUriGenerator(configuration));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator, connectorService, ledgerService);

    // Act
    PaymentWithAllLinks actualConnectorCharge =
        getPaymentService.getConnectorCharge(
            new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(connectorService).getCharge(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    assertNull(actualConnectorCharge.getLinks().getCancel());
    assertFalse(actualConnectorCharge.getDelayedCapture());
    PaymentState state2 = actualConnectorCharge.getState();
    assertTrue(state2.isFinished());
    assertSame(state, state2);
  }

  /**
   * Test {@link GetPaymentService#getLedgerTransaction(Account, String)}.
   *
   * <p>Method under test: {@link GetPaymentService#getLedgerTransaction(Account, String)}
   */
  @Test
  @DisplayName("Test getLedgerTransaction(Account, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentWithAllLinks GetPaymentService.getLedgerTransaction(Account, String)"})
  void testGetLedgerTransaction() {
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

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    ConnectorService connectorService =
        new ConnectorService(mock(Client.class), new ConnectorUriGenerator(configuration));

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

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator, connectorService, ledgerService);

    // Act
    PaymentWithAllLinks actualLedgerTransaction =
        getPaymentService.getLedgerTransaction(
            new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(ledgerService).getPaymentTransaction(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    assertNull(actualLedgerTransaction.getLinks().getCancel());
    assertTrue(actualLedgerTransaction.getDelayedCapture());
    PaymentState state2 = actualLedgerTransaction.getState();
    assertTrue(state2.isFinished());
    assertSame(state, state2);
  }

  /**
   * Test {@link GetPaymentService#getLedgerTransaction(Account, String)}.
   *
   * <p>Method under test: {@link GetPaymentService#getLedgerTransaction(Account, String)}
   */
  @Test
  @DisplayName("Test getLedgerTransaction(Account, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentWithAllLinks GetPaymentService.getLedgerTransaction(Account, String)"})
  void testGetLedgerTransaction2() {
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

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    ConnectorService connectorService =
        new ConnectorService(mock(Client.class), new ConnectorUriGenerator(configuration));

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

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator, connectorService, ledgerService);

    // Act
    PaymentWithAllLinks actualLedgerTransaction =
        getPaymentService.getLedgerTransaction(
            new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(ledgerService).getPaymentTransaction(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    assertNull(actualLedgerTransaction.getLinks().getCancel());
    assertTrue(actualLedgerTransaction.getDelayedCapture());
    PaymentState state2 = actualLedgerTransaction.getState();
    assertTrue(state2.isFinished());
    assertSame(state, state2);
  }

  /**
   * Test {@link GetPaymentService#getLedgerTransaction(Account, String)}.
   *
   * <ul>
   *   <li>Then calls {@link Charge#getAgreementId()}.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentService#getLedgerTransaction(Account, String)}
   */
  @Test
  @DisplayName("Test getLedgerTransaction(Account, String); then calls getAgreementId()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentWithAllLinks GetPaymentService.getLedgerTransaction(Account, String)"})
  void testGetLedgerTransaction_thenCallsGetAgreementId() {
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

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    ConnectorService connectorService =
        new ConnectorService(mock(Client.class), new ConnectorUriGenerator(configuration));

    Charge charge = mock(Charge.class);
    when(charge.getDelayedCapture()).thenReturn(true);
    when(charge.isMoto()).thenReturn(true);
    when(charge.getAmount()).thenReturn(10L);
    when(charge.getCorporateCardSurcharge()).thenReturn(1L);
    when(charge.getFee()).thenReturn(1L);
    when(charge.getNetAmount()).thenReturn(1L);
    when(charge.getTotalAmount()).thenReturn(1L);
    when(charge.getAgreementId()).thenReturn("42");
    when(charge.getChargeId()).thenReturn("42");
    when(charge.getCreatedDate()).thenReturn("2020-03-01");
    when(charge.getDescription()).thenReturn("The characteristics of someone or something");
    when(charge.getEmail()).thenReturn("jane.doe@example.org");
    when(charge.getGatewayTransactionId()).thenReturn("42");
    when(charge.getPaymentProvider()).thenReturn("Payment Provider");
    when(charge.getReference()).thenReturn("Reference");
    when(charge.getReturnUrl()).thenReturn("https://example.org/example");
    when(charge.getLinks()).thenReturn(new ArrayList<>());
    Optional<ExternalMetadata> ofResult = Optional.of(new ExternalMetadata(new HashMap<>()));
    when(charge.getMetadata()).thenReturn(ofResult);
    when(charge.getAuthorisationSummary()).thenReturn(new AuthorisationSummary());
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
    when(charge.getCardDetails()).thenReturn(cardDetails);
    when(charge.getExemption()).thenReturn(new Exemption());
    PaymentSettlementSummary paymentSettlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    when(charge.getSettlementSummary()).thenReturn(paymentSettlementSummary);
    PaymentState paymentState = new PaymentState("Status", true);
    when(charge.getState()).thenReturn(paymentState);
    when(charge.getRefundSummary()).thenReturn(new RefundSummary("Status", 10L, 10L));
    when(charge.getAgreementPaymentType()).thenReturn(AgreementPaymentType.INSTALMENT);
    when(charge.getAuthorisationMode()).thenReturn(AuthorisationMode.WEB);
    when(charge.getLanguage()).thenReturn(SupportedLanguage.ENGLISH);

    LedgerService ledgerService = mock(LedgerService.class);
    when(ledgerService.getPaymentTransaction(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(charge);

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator, connectorService, ledgerService);

    // Act
    PaymentWithAllLinks actualLedgerTransaction =
        getPaymentService.getLedgerTransaction(
            new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(charge).getAgreementId();
    verify(charge).getAgreementPaymentType();
    verify(charge).getAmount();
    verify(charge).getAuthorisationMode();
    verify(charge).getAuthorisationSummary();
    verify(charge).getCardDetails();
    verify(charge, atLeast(1)).getChargeId();
    verify(charge).getCorporateCardSurcharge();
    verify(charge).getCreatedDate();
    verify(charge).getDelayedCapture();
    verify(charge).getDescription();
    verify(charge).getEmail();
    verify(charge).getExemption();
    verify(charge).getFee();
    verify(charge).getGatewayTransactionId();
    verify(charge).getLanguage();
    verify(charge).getLinks();
    verify(charge).getMetadata();
    verify(charge).getNetAmount();
    verify(charge).getPaymentProvider();
    verify(charge).getReference();
    verify(charge).getRefundSummary();
    verify(charge).getReturnUrl();
    verify(charge).getSettlementSummary();
    verify(charge).getState();
    verify(charge).getTotalAmount();
    verify(charge).isMoto();
    verify(ledgerService).getPaymentTransaction(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    assertNull(actualLedgerTransaction.getLinks().getCancel());
    assertTrue(actualLedgerTransaction.getDelayedCapture());
    PaymentState state = actualLedgerTransaction.getState();
    assertTrue(state.isFinished());
    assertSame(paymentState, state);
  }

  /**
   * Test {@link GetPaymentService#getLedgerTransaction(Account, String)}.
   *
   * <ul>
   *   <li>Then return DelayedCapture.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentService#getLedgerTransaction(Account, String)}
   */
  @Test
  @DisplayName("Test getLedgerTransaction(Account, String); then return DelayedCapture")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentWithAllLinks GetPaymentService.getLedgerTransaction(Account, String)"})
  void testGetLedgerTransaction_thenReturnDelayedCapture() {
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

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    ConnectorService connectorService =
        new ConnectorService(mock(Client.class), new ConnectorUriGenerator(configuration));

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

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator, connectorService, ledgerService);

    // Act
    PaymentWithAllLinks actualLedgerTransaction =
        getPaymentService.getLedgerTransaction(
            new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(ledgerService).getPaymentTransaction(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    assertNull(actualLedgerTransaction.getLinks().getCancel());
    assertTrue(actualLedgerTransaction.getDelayedCapture());
    PaymentState state2 = actualLedgerTransaction.getState();
    assertTrue(state2.isFinished());
    assertSame(state, state2);
  }

  /**
   * Test {@link GetPaymentService#getLedgerTransaction(Account, String)}.
   *
   * <ul>
   *   <li>Then return Links Cancel Method is {@code POST}.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentService#getLedgerTransaction(Account, String)}
   */
  @Test
  @DisplayName(
      "Test getLedgerTransaction(Account, String); then return Links Cancel Method is 'POST'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentWithAllLinks GetPaymentService.getLedgerTransaction(Account, String)"})
  void testGetLedgerTransaction_thenReturnLinksCancelMethodIsPost() {
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

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    ConnectorService connectorService =
        new ConnectorService(mock(Client.class), new ConnectorUriGenerator(configuration));

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

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator, connectorService, ledgerService);

    // Act
    PaymentWithAllLinks actualLedgerTransaction =
        getPaymentService.getLedgerTransaction(
            new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(ledgerService).getPaymentTransaction(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    PostLink cancel = actualLedgerTransaction.getLinks().getCancel();
    assertEquals("POST", cancel.getMethod());
    assertNull(cancel.getType());
    assertNull(cancel.getParams());
    PaymentState state2 = actualLedgerTransaction.getState();
    assertFalse(state2.isFinished());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        cancel.getHref());
    assertSame(state, state2);
  }

  /**
   * Test {@link GetPaymentService#getLedgerTransaction(Account, String)}.
   *
   * <ul>
   *   <li>Then return not DelayedCapture.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentService#getLedgerTransaction(Account, String)}
   */
  @Test
  @DisplayName("Test getLedgerTransaction(Account, String); then return not DelayedCapture")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentWithAllLinks GetPaymentService.getLedgerTransaction(Account, String)"})
  void testGetLedgerTransaction_thenReturnNotDelayedCapture() {
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

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    ConnectorService connectorService =
        new ConnectorService(mock(Client.class), new ConnectorUriGenerator(configuration));

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
            false,
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

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator, connectorService, ledgerService);

    // Act
    PaymentWithAllLinks actualLedgerTransaction =
        getPaymentService.getLedgerTransaction(
            new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(ledgerService).getPaymentTransaction(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    assertNull(actualLedgerTransaction.getLinks().getCancel());
    assertFalse(actualLedgerTransaction.getDelayedCapture());
    PaymentState state2 = actualLedgerTransaction.getState();
    assertTrue(state2.isFinished());
    assertSame(state, state2);
  }

  /**
   * Test {@link GetPaymentService#getPayment(Account, String)}.
   *
   * <p>Method under test: {@link GetPaymentService#getPayment(Account, String)}
   */
  @Test
  @DisplayName("Test getPayment(Account, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentWithAllLinks GetPaymentService.getPayment(Account, String)"})
  void testGetPayment() {
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

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    LedgerService ledgerService =
        new LedgerService(mock(Client.class), new LedgerUriGenerator(configuration));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator, connectorService, ledgerService);

    // Act
    PaymentWithAllLinks actualPayment =
        getPaymentService.getPayment(new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(connectorService).getCharge(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    assertNull(actualPayment.getLinks().getCancel());
    assertTrue(actualPayment.getDelayedCapture());
    PaymentState state2 = actualPayment.getState();
    assertTrue(state2.isFinished());
    assertSame(state, state2);
  }

  /**
   * Test {@link GetPaymentService#getPayment(Account, String)}.
   *
   * <p>Method under test: {@link GetPaymentService#getPayment(Account, String)}
   */
  @Test
  @DisplayName("Test getPayment(Account, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentWithAllLinks GetPaymentService.getPayment(Account, String)"})
  void testGetPayment2() {
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

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    LedgerService ledgerService =
        new LedgerService(mock(Client.class), new LedgerUriGenerator(configuration));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator, connectorService, ledgerService);

    // Act
    PaymentWithAllLinks actualPayment =
        getPaymentService.getPayment(new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(connectorService).getCharge(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    assertNull(actualPayment.getLinks().getCancel());
    assertTrue(actualPayment.getDelayedCapture());
    PaymentState state2 = actualPayment.getState();
    assertTrue(state2.isFinished());
    assertSame(state, state2);
  }

  /**
   * Test {@link GetPaymentService#getPayment(Account, String)}.
   *
   * <ul>
   *   <li>Given {@link Charge} {@link Charge#getDelayedCapture()} return {@code true}.
   *   <li>Then calls {@link Charge#getAgreementId()}.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentService#getPayment(Account, String)}
   */
  @Test
  @DisplayName(
      "Test getPayment(Account, String); given Charge getDelayedCapture() return 'true'; then calls getAgreementId()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentWithAllLinks GetPaymentService.getPayment(Account, String)"})
  void testGetPayment_givenChargeGetDelayedCaptureReturnTrue_thenCallsGetAgreementId() {
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

    Charge charge = mock(Charge.class);
    when(charge.getDelayedCapture()).thenReturn(true);
    when(charge.isMoto()).thenReturn(true);
    when(charge.getAmount()).thenReturn(10L);
    when(charge.getCorporateCardSurcharge()).thenReturn(1L);
    when(charge.getFee()).thenReturn(1L);
    when(charge.getNetAmount()).thenReturn(1L);
    when(charge.getTotalAmount()).thenReturn(1L);
    when(charge.getAgreementId()).thenReturn("42");
    when(charge.getChargeId()).thenReturn("42");
    when(charge.getCreatedDate()).thenReturn("2020-03-01");
    when(charge.getDescription()).thenReturn("The characteristics of someone or something");
    when(charge.getEmail()).thenReturn("jane.doe@example.org");
    when(charge.getGatewayTransactionId()).thenReturn("42");
    when(charge.getPaymentProvider()).thenReturn("Payment Provider");
    when(charge.getReference()).thenReturn("Reference");
    when(charge.getReturnUrl()).thenReturn("https://example.org/example");
    when(charge.getLinks()).thenReturn(new ArrayList<>());
    Optional<ExternalMetadata> ofResult = Optional.of(new ExternalMetadata(new HashMap<>()));
    when(charge.getMetadata()).thenReturn(ofResult);
    when(charge.getAuthorisationSummary()).thenReturn(new AuthorisationSummary());
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
    when(charge.getCardDetails()).thenReturn(cardDetails);
    when(charge.getExemption()).thenReturn(new Exemption());
    PaymentSettlementSummary paymentSettlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    when(charge.getSettlementSummary()).thenReturn(paymentSettlementSummary);
    PaymentState paymentState = new PaymentState("Status", true);
    when(charge.getState()).thenReturn(paymentState);
    when(charge.getRefundSummary()).thenReturn(new RefundSummary("Status", 10L, 10L));
    when(charge.getAgreementPaymentType()).thenReturn(AgreementPaymentType.INSTALMENT);
    when(charge.getAuthorisationMode()).thenReturn(AuthorisationMode.WEB);
    when(charge.getLanguage()).thenReturn(SupportedLanguage.ENGLISH);

    ConnectorService connectorService = mock(ConnectorService.class);
    when(connectorService.getCharge(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(charge);

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    LedgerService ledgerService =
        new LedgerService(mock(Client.class), new LedgerUriGenerator(configuration));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator, connectorService, ledgerService);

    // Act
    PaymentWithAllLinks actualPayment =
        getPaymentService.getPayment(new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(charge).getAgreementId();
    verify(charge).getAgreementPaymentType();
    verify(charge).getAmount();
    verify(charge).getAuthorisationMode();
    verify(charge).getAuthorisationSummary();
    verify(charge).getCardDetails();
    verify(charge, atLeast(1)).getChargeId();
    verify(charge).getCorporateCardSurcharge();
    verify(charge).getCreatedDate();
    verify(charge).getDelayedCapture();
    verify(charge).getDescription();
    verify(charge).getEmail();
    verify(charge).getExemption();
    verify(charge).getFee();
    verify(charge).getGatewayTransactionId();
    verify(charge).getLanguage();
    verify(charge).getLinks();
    verify(charge).getMetadata();
    verify(charge).getNetAmount();
    verify(charge).getPaymentProvider();
    verify(charge).getReference();
    verify(charge).getRefundSummary();
    verify(charge).getReturnUrl();
    verify(charge).getSettlementSummary();
    verify(charge).getState();
    verify(charge).getTotalAmount();
    verify(charge).isMoto();
    verify(connectorService).getCharge(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    assertNull(actualPayment.getLinks().getCancel());
    assertTrue(actualPayment.getDelayedCapture());
    PaymentState state = actualPayment.getState();
    assertTrue(state.isFinished());
    assertSame(paymentState, state);
  }

  /**
   * Test {@link GetPaymentService#getPayment(Account, String)}.
   *
   * <ul>
   *   <li>Then return DelayedCapture.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentService#getPayment(Account, String)}
   */
  @Test
  @DisplayName("Test getPayment(Account, String); then return DelayedCapture")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentWithAllLinks GetPaymentService.getPayment(Account, String)"})
  void testGetPayment_thenReturnDelayedCapture() {
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

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    LedgerService ledgerService =
        new LedgerService(mock(Client.class), new LedgerUriGenerator(configuration));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator, connectorService, ledgerService);

    // Act
    PaymentWithAllLinks actualPayment =
        getPaymentService.getPayment(new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(connectorService).getCharge(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    assertNull(actualPayment.getLinks().getCancel());
    assertTrue(actualPayment.getDelayedCapture());
    PaymentState state2 = actualPayment.getState();
    assertTrue(state2.isFinished());
    assertSame(state, state2);
  }

  /**
   * Test {@link GetPaymentService#getPayment(Account, String)}.
   *
   * <ul>
   *   <li>Then return Links Cancel Method is {@code POST}.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentService#getPayment(Account, String)}
   */
  @Test
  @DisplayName("Test getPayment(Account, String); then return Links Cancel Method is 'POST'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentWithAllLinks GetPaymentService.getPayment(Account, String)"})
  void testGetPayment_thenReturnLinksCancelMethodIsPost() {
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

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    LedgerService ledgerService =
        new LedgerService(mock(Client.class), new LedgerUriGenerator(configuration));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator, connectorService, ledgerService);

    // Act
    PaymentWithAllLinks actualPayment =
        getPaymentService.getPayment(new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(connectorService).getCharge(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    PostLink cancel = actualPayment.getLinks().getCancel();
    assertEquals("POST", cancel.getMethod());
    assertNull(cancel.getType());
    assertNull(cancel.getParams());
    PaymentState state2 = actualPayment.getState();
    assertFalse(state2.isFinished());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        cancel.getHref());
    assertSame(state, state2);
  }

  /**
   * Test {@link GetPaymentService#getPayment(Account, String)}.
   *
   * <ul>
   *   <li>Then return not DelayedCapture.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentService#getPayment(Account, String)}
   */
  @Test
  @DisplayName("Test getPayment(Account, String); then return not DelayedCapture")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentWithAllLinks GetPaymentService.getPayment(Account, String)"})
  void testGetPayment_thenReturnNotDelayedCapture() {
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
            false,
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

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    LedgerService ledgerService =
        new LedgerService(mock(Client.class), new LedgerUriGenerator(configuration));

    GetPaymentService getPaymentService =
        new GetPaymentService(publicApiUriGenerator, connectorService, ledgerService);

    // Act
    PaymentWithAllLinks actualPayment =
        getPaymentService.getPayment(new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(connectorService).getCharge(isA(Account.class), eq("42"));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    assertNull(actualPayment.getLinks().getCancel());
    assertFalse(actualPayment.getDelayedCapture());
    PaymentState state2 = actualPayment.getState();
    assertTrue(state2.isFinished());
    assertSame(state, state2);
  }
}
