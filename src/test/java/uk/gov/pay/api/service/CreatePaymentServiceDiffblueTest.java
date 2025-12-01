package uk.gov.pay.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.dropwizard.core.server.DefaultServerFactory;
import io.dropwizard.core.setup.AdminFactory;
import io.dropwizard.core.setup.HealthCheckConfiguration;
import io.dropwizard.health.HealthFactory;
import io.dropwizard.logging.common.DefaultLoggingFactory;
import io.dropwizard.metrics.common.MetricsFactory;
import io.dropwizard.servlets.tasks.TaskConfiguration;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.Invocation.Builder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import org.glassfish.jersey.message.internal.OutboundJaxrsResponse;
import org.glassfish.jersey.message.internal.OutboundMessageContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uk.gov.pay.api.app.config.PublicApiConfig;
import uk.gov.pay.api.auth.Account;
import uk.gov.pay.api.exception.ConnectorResponseErrorException;
import uk.gov.pay.api.exception.ConnectorResponseErrorException.ConnectorErrorResponse;
import uk.gov.pay.api.exception.CreateChargeException;
import uk.gov.pay.api.model.Address;
import uk.gov.pay.api.model.AuthorisationSummary;
import uk.gov.pay.api.model.CardDetails;
import uk.gov.pay.api.model.CardDetailsFromResponse;
import uk.gov.pay.api.model.ChargeFromResponse;
import uk.gov.pay.api.model.CreateCardPaymentRequest;
import uk.gov.pay.api.model.CreateCardPaymentRequestBuilder;
import uk.gov.pay.api.model.CreatedPaymentWithAllLinks;
import uk.gov.pay.api.model.Exemption;
import uk.gov.pay.api.model.ExemptionOutcome;
import uk.gov.pay.api.model.PaymentConnectorResponseLink;
import uk.gov.pay.api.model.PaymentSettlementSummary;
import uk.gov.pay.api.model.PaymentState;
import uk.gov.pay.api.model.RefundSummary;
import uk.gov.pay.api.model.ThreeDSecure;
import uk.gov.pay.api.model.TokenPaymentType;
import uk.gov.pay.api.model.Wallet;
import uk.gov.pay.api.model.links.PaymentWithAllLinks;
import uk.gov.pay.api.model.links.PostLink;
import uk.gov.service.payments.commons.model.AgreementPaymentType;
import uk.gov.service.payments.commons.model.AuthorisationMode;
import uk.gov.service.payments.commons.model.SupportedLanguage;
import uk.gov.service.payments.commons.model.charge.ExternalMetadata;

class CreatePaymentServiceDiffblueTest {
  /**
   * Test {@link CreatePaymentService#create(Account, CreateCardPaymentRequest, String)}.
   *
   * <p>Method under test: {@link CreatePaymentService#create(Account, CreateCardPaymentRequest,
   * String)}
   */
  @Test
  @DisplayName("Test create(Account, CreateCardPaymentRequest, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CreatedPaymentWithAllLinks CreatePaymentService.create(Account, CreateCardPaymentRequest, String)"
  })
  void testCreate() {
    // Arrange
    Builder builder = mock(Builder.class);
    when(builder.post(Mockito.<Entity<?>>any()))
        .thenReturn(new OutboundJaxrsResponse(Status.ACCEPTED, new OutboundMessageContext()));

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(String[].class))).thenReturn(builder);

    Builder builder3 = mock(Builder.class);
    when(builder3.headers(Mockito.<MultivaluedMap<String, Object>>any())).thenReturn(builder2);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder3);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    AdminFactory admin = new AdminFactory();
    admin.setHealthChecks(new HealthCheckConfiguration());
    admin.setTasks(new TaskConfiguration());

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(admin);
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator = new PublicApiUriGenerator(configuration);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.chargesURI(Mockito.<Account>any())).thenReturn("Charges URI");

    CreatePaymentService createPaymentService =
        new CreatePaymentService(client, publicApiUriGenerator, connectorUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    // Act and Assert
    assertThrows(
        CreateChargeException.class,
        () ->
            createPaymentService.create(
                account,
                new CreateCardPaymentRequest(CreateCardPaymentRequestBuilder.builder()),
                "Idempotency Key"));
    verify(client).target("Charges URI");
    verify(builder2).accept(isA(String[].class));
    verify(builder3).headers(isA(MultivaluedMap.class));
    verify(builder).post(isA(Entity.class));
    verify(webTarget).request();
    verify(connectorUriGenerator).chargesURI(isA(Account.class));
  }

  /**
   * Test {@link CreatePaymentService#create(Account, CreateCardPaymentRequest, String)}.
   *
   * <p>Method under test: {@link CreatePaymentService#create(Account, CreateCardPaymentRequest,
   * String)}
   */
  @Test
  @DisplayName("Test create(Account, CreateCardPaymentRequest, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CreatedPaymentWithAllLinks CreatePaymentService.create(Account, CreateCardPaymentRequest, String)"
  })
  void testCreate2() throws ProcessingException {
    // Arrange
    ArrayList<PaymentConnectorResponseLink> paymentConnectorResponseLinkList = new ArrayList<>();
    PaymentConnectorResponseLink paymentConnectorResponseLink =
        new PaymentConnectorResponseLink(
            "Idempotency-Key",
            "Idempotency-Key",
            "Idempotency-Key",
            "Idempotency-Key",
            new HashMap<>());
    paymentConnectorResponseLinkList.add(paymentConnectorResponseLink);

    ChargeFromResponse chargeFromResponse = mock(ChargeFromResponse.class);
    when(chargeFromResponse.getDelayedCapture()).thenReturn(true);
    when(chargeFromResponse.isMoto()).thenReturn(true);
    when(chargeFromResponse.getAmount()).thenReturn(10L);
    when(chargeFromResponse.getCorporateCardSurcharge()).thenReturn(1L);
    when(chargeFromResponse.getFee()).thenReturn(1L);
    when(chargeFromResponse.getNetAmount()).thenReturn(1L);
    when(chargeFromResponse.getTotalAmount()).thenReturn(1L);
    when(chargeFromResponse.getAgreementId()).thenReturn("42");
    when(chargeFromResponse.getChargeId()).thenReturn("42");
    when(chargeFromResponse.getCreatedDate()).thenReturn("2020-03-01");
    when(chargeFromResponse.getDescription())
        .thenReturn("The characteristics of someone or something");
    when(chargeFromResponse.getEmail()).thenReturn("jane.doe@example.org");
    when(chargeFromResponse.getGatewayTransactionId()).thenReturn("42");
    when(chargeFromResponse.getPaymentProvider()).thenReturn("Payment Provider");
    when(chargeFromResponse.getReference()).thenReturn("Reference");
    when(chargeFromResponse.getReturnUrl()).thenReturn("https://example.org/example");
    when(chargeFromResponse.getLinks()).thenReturn(paymentConnectorResponseLinkList);
    Optional<ExternalMetadata> ofResult = Optional.of(new ExternalMetadata(new HashMap<>()));
    when(chargeFromResponse.getMetadata()).thenReturn(ofResult);
    Optional<Wallet> ofResult2 = Optional.of(Wallet.APPLE_PAY);
    when(chargeFromResponse.getWalletType()).thenReturn(ofResult2);
    when(chargeFromResponse.getAuthorisationSummary()).thenReturn(new AuthorisationSummary());
    Address billingAddress = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");
    CardDetailsFromResponse cardDetailsFromResponse =
        new CardDetailsFromResponse(
            "42",
            "42",
            "Card Holder Name",
            "2020-03-01",
            billingAddress,
            "Card Brand",
            "Card Type");
    when(chargeFromResponse.getCardDetailsFromResponse()).thenReturn(cardDetailsFromResponse);
    when(chargeFromResponse.getExemption()).thenReturn(new Exemption());
    PaymentSettlementSummary paymentSettlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    when(chargeFromResponse.getSettlementSummary()).thenReturn(paymentSettlementSummary);
    when(chargeFromResponse.getState()).thenReturn(new PaymentState("Status", true));
    when(chargeFromResponse.getRefundSummary()).thenReturn(new RefundSummary("Status", 10L, 10L));
    when(chargeFromResponse.getAgreementPaymentType()).thenReturn(AgreementPaymentType.INSTALMENT);
    when(chargeFromResponse.getAuthorisationMode()).thenReturn(AuthorisationMode.WEB);
    when(chargeFromResponse.getLanguage()).thenReturn(SupportedLanguage.ENGLISH);

    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.readEntity(ChargeFromResponse.class)).thenReturn(chargeFromResponse);
    when(outboundJaxrsResponse.getStatus()).thenReturn(201);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());

    Builder builder = mock(Builder.class);
    when(builder.post(Mockito.<Entity<?>>any())).thenReturn(outboundJaxrsResponse);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(String[].class))).thenReturn(builder);

    Builder builder3 = mock(Builder.class);
    when(builder3.headers(Mockito.<MultivaluedMap<String, Object>>any())).thenReturn(builder2);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder3);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

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

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.chargesURI(Mockito.<Account>any())).thenReturn("Charges URI");

    CreatePaymentService createPaymentService =
        new CreatePaymentService(client, publicApiUriGenerator, connectorUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    CreateCardPaymentRequest createCardPaymentRequest = mock(CreateCardPaymentRequest.class);
    when(createCardPaymentRequest.toConnectorPayload()).thenReturn("Connector Payload");

    // Act
    CreatedPaymentWithAllLinks actualCreateResult =
        createPaymentService.create(account, createCardPaymentRequest, "Idempotency Key");

    // Assert
    verify(client).target("Charges URI");
    verify(builder2).accept(isA(String[].class));
    verify(builder3).headers(isA(MultivaluedMap.class));
    verify(builder).post(isA(Entity.class));
    verify(webTarget).request();
    verify(outboundJaxrsResponse).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(chargeFromResponse).getAgreementId();
    verify(chargeFromResponse).getAgreementPaymentType();
    verify(chargeFromResponse).getAmount();
    verify(chargeFromResponse).getAuthorisationMode();
    verify(chargeFromResponse).getAuthorisationSummary();
    verify(chargeFromResponse, atLeast(1)).getCardDetailsFromResponse();
    verify(chargeFromResponse).getChargeId();
    verify(chargeFromResponse).getCorporateCardSurcharge();
    verify(chargeFromResponse).getCreatedDate();
    verify(chargeFromResponse).getDelayedCapture();
    verify(chargeFromResponse).getDescription();
    verify(chargeFromResponse).getEmail();
    verify(chargeFromResponse).getExemption();
    verify(chargeFromResponse).getFee();
    verify(chargeFromResponse).getGatewayTransactionId();
    verify(chargeFromResponse).getLanguage();
    verify(chargeFromResponse).getLinks();
    verify(chargeFromResponse).getMetadata();
    verify(chargeFromResponse).getNetAmount();
    verify(chargeFromResponse).getPaymentProvider();
    verify(chargeFromResponse).getReference();
    verify(chargeFromResponse).getRefundSummary();
    verify(chargeFromResponse).getReturnUrl();
    verify(chargeFromResponse).getSettlementSummary();
    verify(chargeFromResponse).getState();
    verify(chargeFromResponse).getTotalAmount();
    verify(chargeFromResponse).getWalletType();
    verify(chargeFromResponse).isMoto();
    verify(createCardPaymentRequest).toConnectorPayload();
    verify(connectorUriGenerator).chargesURI(isA(Account.class));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    PaymentWithAllLinks payment = actualCreateResult.getPayment();
    Optional<CardDetails> cardDetails = payment.getCardDetails();
    CardDetails getResult = cardDetails.get();
    assertEquals("2020-03-01", getResult.getExpiryDate());
    assertEquals("42", getResult.getFirstDigitsCardNumber());
    assertEquals("42", getResult.getLastDigitsCardNumber());
    assertEquals("Card Brand", getResult.getCardBrand());
    assertEquals("Card Brand", payment.getCardBrand());
    assertEquals("Card Holder Name", getResult.getCardHolderName());
    assertEquals("Card Type", getResult.getCardType());
    assertTrue(cardDetails.isPresent());
  }

  /**
   * Test {@link CreatePaymentService#create(Account, CreateCardPaymentRequest, String)}.
   *
   * <p>Method under test: {@link CreatePaymentService#create(Account, CreateCardPaymentRequest,
   * String)}
   */
  @Test
  @DisplayName("Test create(Account, CreateCardPaymentRequest, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CreatedPaymentWithAllLinks CreatePaymentService.create(Account, CreateCardPaymentRequest, String)"
  })
  void testCreate3() throws ProcessingException {
    // Arrange
    ArrayList<PaymentConnectorResponseLink> paymentConnectorResponseLinkList = new ArrayList<>();
    PaymentConnectorResponseLink paymentConnectorResponseLink =
        new PaymentConnectorResponseLink(
            "Idempotency-Key",
            "Idempotency-Key",
            "Idempotency-Key",
            "Idempotency-Key",
            new HashMap<>());
    paymentConnectorResponseLinkList.add(paymentConnectorResponseLink);
    PaymentConnectorResponseLink paymentConnectorResponseLink2 =
        new PaymentConnectorResponseLink(
            "Idempotency-Key",
            "Idempotency-Key",
            "Idempotency-Key",
            "Idempotency-Key",
            new HashMap<>());
    paymentConnectorResponseLinkList.add(paymentConnectorResponseLink2);

    ChargeFromResponse chargeFromResponse = mock(ChargeFromResponse.class);
    when(chargeFromResponse.getDelayedCapture()).thenReturn(true);
    when(chargeFromResponse.isMoto()).thenReturn(true);
    when(chargeFromResponse.getAmount()).thenReturn(10L);
    when(chargeFromResponse.getCorporateCardSurcharge()).thenReturn(1L);
    when(chargeFromResponse.getFee()).thenReturn(1L);
    when(chargeFromResponse.getNetAmount()).thenReturn(1L);
    when(chargeFromResponse.getTotalAmount()).thenReturn(1L);
    when(chargeFromResponse.getAgreementId()).thenReturn("42");
    when(chargeFromResponse.getChargeId()).thenReturn("42");
    when(chargeFromResponse.getCreatedDate()).thenReturn("2020-03-01");
    when(chargeFromResponse.getDescription())
        .thenReturn("The characteristics of someone or something");
    when(chargeFromResponse.getEmail()).thenReturn("jane.doe@example.org");
    when(chargeFromResponse.getGatewayTransactionId()).thenReturn("42");
    when(chargeFromResponse.getPaymentProvider()).thenReturn("Payment Provider");
    when(chargeFromResponse.getReference()).thenReturn("Reference");
    when(chargeFromResponse.getReturnUrl()).thenReturn("https://example.org/example");
    when(chargeFromResponse.getLinks()).thenReturn(paymentConnectorResponseLinkList);
    Optional<ExternalMetadata> ofResult = Optional.of(new ExternalMetadata(new HashMap<>()));
    when(chargeFromResponse.getMetadata()).thenReturn(ofResult);
    Optional<Wallet> ofResult2 = Optional.of(Wallet.APPLE_PAY);
    when(chargeFromResponse.getWalletType()).thenReturn(ofResult2);
    when(chargeFromResponse.getAuthorisationSummary()).thenReturn(new AuthorisationSummary());
    Address billingAddress = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");
    CardDetailsFromResponse cardDetailsFromResponse =
        new CardDetailsFromResponse(
            "42",
            "42",
            "Card Holder Name",
            "2020-03-01",
            billingAddress,
            "Card Brand",
            "Card Type");
    when(chargeFromResponse.getCardDetailsFromResponse()).thenReturn(cardDetailsFromResponse);
    when(chargeFromResponse.getExemption()).thenReturn(new Exemption());
    PaymentSettlementSummary paymentSettlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    when(chargeFromResponse.getSettlementSummary()).thenReturn(paymentSettlementSummary);
    when(chargeFromResponse.getState()).thenReturn(new PaymentState("Status", true));
    when(chargeFromResponse.getRefundSummary()).thenReturn(new RefundSummary("Status", 10L, 10L));
    when(chargeFromResponse.getAgreementPaymentType()).thenReturn(AgreementPaymentType.INSTALMENT);
    when(chargeFromResponse.getAuthorisationMode()).thenReturn(AuthorisationMode.WEB);
    when(chargeFromResponse.getLanguage()).thenReturn(SupportedLanguage.ENGLISH);

    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.readEntity(ChargeFromResponse.class)).thenReturn(chargeFromResponse);
    when(outboundJaxrsResponse.getStatus()).thenReturn(201);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());

    Builder builder = mock(Builder.class);
    when(builder.post(Mockito.<Entity<?>>any())).thenReturn(outboundJaxrsResponse);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(String[].class))).thenReturn(builder);

    Builder builder3 = mock(Builder.class);
    when(builder3.headers(Mockito.<MultivaluedMap<String, Object>>any())).thenReturn(builder2);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder3);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

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

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.chargesURI(Mockito.<Account>any())).thenReturn("Charges URI");

    CreatePaymentService createPaymentService =
        new CreatePaymentService(client, publicApiUriGenerator, connectorUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    CreateCardPaymentRequest createCardPaymentRequest = mock(CreateCardPaymentRequest.class);
    when(createCardPaymentRequest.toConnectorPayload()).thenReturn("Connector Payload");

    // Act
    CreatedPaymentWithAllLinks actualCreateResult =
        createPaymentService.create(account, createCardPaymentRequest, "Idempotency Key");

    // Assert
    verify(client).target("Charges URI");
    verify(builder2).accept(isA(String[].class));
    verify(builder3).headers(isA(MultivaluedMap.class));
    verify(builder).post(isA(Entity.class));
    verify(webTarget).request();
    verify(outboundJaxrsResponse).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(chargeFromResponse).getAgreementId();
    verify(chargeFromResponse).getAgreementPaymentType();
    verify(chargeFromResponse).getAmount();
    verify(chargeFromResponse).getAuthorisationMode();
    verify(chargeFromResponse).getAuthorisationSummary();
    verify(chargeFromResponse, atLeast(1)).getCardDetailsFromResponse();
    verify(chargeFromResponse).getChargeId();
    verify(chargeFromResponse).getCorporateCardSurcharge();
    verify(chargeFromResponse).getCreatedDate();
    verify(chargeFromResponse).getDelayedCapture();
    verify(chargeFromResponse).getDescription();
    verify(chargeFromResponse).getEmail();
    verify(chargeFromResponse).getExemption();
    verify(chargeFromResponse).getFee();
    verify(chargeFromResponse).getGatewayTransactionId();
    verify(chargeFromResponse).getLanguage();
    verify(chargeFromResponse).getLinks();
    verify(chargeFromResponse).getMetadata();
    verify(chargeFromResponse).getNetAmount();
    verify(chargeFromResponse).getPaymentProvider();
    verify(chargeFromResponse).getReference();
    verify(chargeFromResponse).getRefundSummary();
    verify(chargeFromResponse).getReturnUrl();
    verify(chargeFromResponse).getSettlementSummary();
    verify(chargeFromResponse).getState();
    verify(chargeFromResponse).getTotalAmount();
    verify(chargeFromResponse).getWalletType();
    verify(chargeFromResponse).isMoto();
    verify(createCardPaymentRequest).toConnectorPayload();
    verify(connectorUriGenerator).chargesURI(isA(Account.class));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    PaymentWithAllLinks payment = actualCreateResult.getPayment();
    Optional<CardDetails> cardDetails = payment.getCardDetails();
    CardDetails getResult = cardDetails.get();
    assertEquals("2020-03-01", getResult.getExpiryDate());
    assertEquals("42", getResult.getFirstDigitsCardNumber());
    assertEquals("42", getResult.getLastDigitsCardNumber());
    assertEquals("Card Brand", getResult.getCardBrand());
    assertEquals("Card Brand", payment.getCardBrand());
    assertEquals("Card Holder Name", getResult.getCardHolderName());
    assertEquals("Card Type", getResult.getCardType());
    assertTrue(cardDetails.isPresent());
  }

  /**
   * Test {@link CreatePaymentService#create(Account, CreateCardPaymentRequest, String)}.
   *
   * <ul>
   *   <li>Given {@link ChargeFromResponse} {@link ChargeFromResponse#getAuthorisationSummary()}
   *       return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link CreatePaymentService#create(Account, CreateCardPaymentRequest,
   * String)}
   */
  @Test
  @DisplayName(
      "Test create(Account, CreateCardPaymentRequest, String); given ChargeFromResponse getAuthorisationSummary() return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CreatedPaymentWithAllLinks CreatePaymentService.create(Account, CreateCardPaymentRequest, String)"
  })
  void testCreate_givenChargeFromResponseGetAuthorisationSummaryReturnNull()
      throws ProcessingException {
    // Arrange
    ChargeFromResponse chargeFromResponse = mock(ChargeFromResponse.class);
    when(chargeFromResponse.getDelayedCapture()).thenReturn(true);
    when(chargeFromResponse.isMoto()).thenReturn(true);
    when(chargeFromResponse.getAmount()).thenReturn(10L);
    when(chargeFromResponse.getCorporateCardSurcharge()).thenReturn(1L);
    when(chargeFromResponse.getFee()).thenReturn(1L);
    when(chargeFromResponse.getNetAmount()).thenReturn(1L);
    when(chargeFromResponse.getTotalAmount()).thenReturn(1L);
    when(chargeFromResponse.getAgreementId()).thenReturn("42");
    when(chargeFromResponse.getChargeId()).thenReturn("42");
    when(chargeFromResponse.getCreatedDate()).thenReturn("2020-03-01");
    when(chargeFromResponse.getDescription())
        .thenReturn("The characteristics of someone or something");
    when(chargeFromResponse.getEmail()).thenReturn("jane.doe@example.org");
    when(chargeFromResponse.getGatewayTransactionId()).thenReturn("42");
    when(chargeFromResponse.getPaymentProvider()).thenReturn("Payment Provider");
    when(chargeFromResponse.getReference()).thenReturn("Reference");
    when(chargeFromResponse.getReturnUrl()).thenReturn("https://example.org/example");
    when(chargeFromResponse.getLinks()).thenReturn(new ArrayList<>());
    Optional<ExternalMetadata> ofResult = Optional.of(new ExternalMetadata(new HashMap<>()));
    when(chargeFromResponse.getMetadata()).thenReturn(ofResult);
    Optional<Wallet> ofResult2 = Optional.of(Wallet.APPLE_PAY);
    when(chargeFromResponse.getWalletType()).thenReturn(ofResult2);
    when(chargeFromResponse.getAuthorisationSummary()).thenReturn(null);
    Address billingAddress = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");
    CardDetailsFromResponse cardDetailsFromResponse =
        new CardDetailsFromResponse(
            "42",
            "42",
            "Card Holder Name",
            "2020-03-01",
            billingAddress,
            "Card Brand",
            "Card Type");
    when(chargeFromResponse.getCardDetailsFromResponse()).thenReturn(cardDetailsFromResponse);
    when(chargeFromResponse.getExemption()).thenReturn(new Exemption());
    PaymentSettlementSummary paymentSettlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    when(chargeFromResponse.getSettlementSummary()).thenReturn(paymentSettlementSummary);
    when(chargeFromResponse.getState()).thenReturn(new PaymentState("Status", true));
    when(chargeFromResponse.getRefundSummary()).thenReturn(new RefundSummary("Status", 10L, 10L));
    when(chargeFromResponse.getAgreementPaymentType()).thenReturn(AgreementPaymentType.INSTALMENT);
    when(chargeFromResponse.getAuthorisationMode()).thenReturn(AuthorisationMode.WEB);
    when(chargeFromResponse.getLanguage()).thenReturn(SupportedLanguage.ENGLISH);

    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.readEntity(ChargeFromResponse.class)).thenReturn(chargeFromResponse);
    when(outboundJaxrsResponse.getStatus()).thenReturn(201);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());

    Builder builder = mock(Builder.class);
    when(builder.post(Mockito.<Entity<?>>any())).thenReturn(outboundJaxrsResponse);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(String[].class))).thenReturn(builder);

    Builder builder3 = mock(Builder.class);
    when(builder3.headers(Mockito.<MultivaluedMap<String, Object>>any())).thenReturn(builder2);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder3);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

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

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.chargesURI(Mockito.<Account>any())).thenReturn("Charges URI");

    CreatePaymentService createPaymentService =
        new CreatePaymentService(client, publicApiUriGenerator, connectorUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    CreateCardPaymentRequest createCardPaymentRequest = mock(CreateCardPaymentRequest.class);
    when(createCardPaymentRequest.toConnectorPayload()).thenReturn("Connector Payload");

    // Act
    CreatedPaymentWithAllLinks actualCreateResult =
        createPaymentService.create(account, createCardPaymentRequest, "Idempotency Key");

    // Assert
    verify(client).target("Charges URI");
    verify(builder2).accept(isA(String[].class));
    verify(builder3).headers(isA(MultivaluedMap.class));
    verify(builder).post(isA(Entity.class));
    verify(webTarget).request();
    verify(outboundJaxrsResponse).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(chargeFromResponse).getAgreementId();
    verify(chargeFromResponse).getAgreementPaymentType();
    verify(chargeFromResponse).getAmount();
    verify(chargeFromResponse).getAuthorisationMode();
    verify(chargeFromResponse).getAuthorisationSummary();
    verify(chargeFromResponse, atLeast(1)).getCardDetailsFromResponse();
    verify(chargeFromResponse).getChargeId();
    verify(chargeFromResponse).getCorporateCardSurcharge();
    verify(chargeFromResponse).getCreatedDate();
    verify(chargeFromResponse).getDelayedCapture();
    verify(chargeFromResponse).getDescription();
    verify(chargeFromResponse).getEmail();
    verify(chargeFromResponse).getExemption();
    verify(chargeFromResponse).getFee();
    verify(chargeFromResponse).getGatewayTransactionId();
    verify(chargeFromResponse).getLanguage();
    verify(chargeFromResponse).getLinks();
    verify(chargeFromResponse).getMetadata();
    verify(chargeFromResponse).getNetAmount();
    verify(chargeFromResponse).getPaymentProvider();
    verify(chargeFromResponse).getReference();
    verify(chargeFromResponse).getRefundSummary();
    verify(chargeFromResponse).getReturnUrl();
    verify(chargeFromResponse).getSettlementSummary();
    verify(chargeFromResponse).getState();
    verify(chargeFromResponse).getTotalAmount();
    verify(chargeFromResponse).getWalletType();
    verify(chargeFromResponse).isMoto();
    verify(createCardPaymentRequest).toConnectorPayload();
    verify(connectorUriGenerator).chargesURI(isA(Account.class));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    PaymentWithAllLinks payment = actualCreateResult.getPayment();
    Optional<CardDetails> cardDetails = payment.getCardDetails();
    CardDetails getResult = cardDetails.get();
    assertEquals("2020-03-01", getResult.getExpiryDate());
    assertEquals("42", getResult.getFirstDigitsCardNumber());
    assertEquals("42", getResult.getLastDigitsCardNumber());
    assertEquals("Card Brand", getResult.getCardBrand());
    assertEquals("Card Brand", payment.getCardBrand());
    assertEquals("Card Holder Name", getResult.getCardHolderName());
    assertEquals("Card Type", getResult.getCardType());
    assertTrue(cardDetails.isPresent());
  }

  /**
   * Test {@link CreatePaymentService#create(Account, CreateCardPaymentRequest, String)}.
   *
   * <ul>
   *   <li>Given {@link ChargeFromResponse} {@link ChargeFromResponse#getExemption()} return {@code
   *       null}.
   * </ul>
   *
   * <p>Method under test: {@link CreatePaymentService#create(Account, CreateCardPaymentRequest,
   * String)}
   */
  @Test
  @DisplayName(
      "Test create(Account, CreateCardPaymentRequest, String); given ChargeFromResponse getExemption() return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CreatedPaymentWithAllLinks CreatePaymentService.create(Account, CreateCardPaymentRequest, String)"
  })
  void testCreate_givenChargeFromResponseGetExemptionReturnNull() throws ProcessingException {
    // Arrange
    ChargeFromResponse chargeFromResponse = mock(ChargeFromResponse.class);
    when(chargeFromResponse.getDelayedCapture()).thenReturn(true);
    when(chargeFromResponse.isMoto()).thenReturn(true);
    when(chargeFromResponse.getAmount()).thenReturn(10L);
    when(chargeFromResponse.getCorporateCardSurcharge()).thenReturn(1L);
    when(chargeFromResponse.getFee()).thenReturn(1L);
    when(chargeFromResponse.getNetAmount()).thenReturn(1L);
    when(chargeFromResponse.getTotalAmount()).thenReturn(1L);
    when(chargeFromResponse.getAgreementId()).thenReturn("42");
    when(chargeFromResponse.getChargeId()).thenReturn("42");
    when(chargeFromResponse.getCreatedDate()).thenReturn("2020-03-01");
    when(chargeFromResponse.getDescription())
        .thenReturn("The characteristics of someone or something");
    when(chargeFromResponse.getEmail()).thenReturn("jane.doe@example.org");
    when(chargeFromResponse.getGatewayTransactionId()).thenReturn("42");
    when(chargeFromResponse.getPaymentProvider()).thenReturn("Payment Provider");
    when(chargeFromResponse.getReference()).thenReturn("Reference");
    when(chargeFromResponse.getReturnUrl()).thenReturn("https://example.org/example");
    when(chargeFromResponse.getLinks()).thenReturn(new ArrayList<>());
    Optional<ExternalMetadata> ofResult = Optional.of(new ExternalMetadata(new HashMap<>()));
    when(chargeFromResponse.getMetadata()).thenReturn(ofResult);
    Optional<Wallet> ofResult2 = Optional.of(Wallet.APPLE_PAY);
    when(chargeFromResponse.getWalletType()).thenReturn(ofResult2);
    when(chargeFromResponse.getAuthorisationSummary()).thenReturn(new AuthorisationSummary());
    Address billingAddress = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");
    CardDetailsFromResponse cardDetailsFromResponse =
        new CardDetailsFromResponse(
            "42",
            "42",
            "Card Holder Name",
            "2020-03-01",
            billingAddress,
            "Card Brand",
            "Card Type");
    when(chargeFromResponse.getCardDetailsFromResponse()).thenReturn(cardDetailsFromResponse);
    when(chargeFromResponse.getExemption()).thenReturn(null);
    PaymentSettlementSummary paymentSettlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    when(chargeFromResponse.getSettlementSummary()).thenReturn(paymentSettlementSummary);
    when(chargeFromResponse.getState()).thenReturn(new PaymentState("Status", true));
    when(chargeFromResponse.getRefundSummary()).thenReturn(new RefundSummary("Status", 10L, 10L));
    when(chargeFromResponse.getAgreementPaymentType()).thenReturn(AgreementPaymentType.INSTALMENT);
    when(chargeFromResponse.getAuthorisationMode()).thenReturn(AuthorisationMode.WEB);
    when(chargeFromResponse.getLanguage()).thenReturn(SupportedLanguage.ENGLISH);

    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.readEntity(ChargeFromResponse.class)).thenReturn(chargeFromResponse);
    when(outboundJaxrsResponse.getStatus()).thenReturn(201);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());

    Builder builder = mock(Builder.class);
    when(builder.post(Mockito.<Entity<?>>any())).thenReturn(outboundJaxrsResponse);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(String[].class))).thenReturn(builder);

    Builder builder3 = mock(Builder.class);
    when(builder3.headers(Mockito.<MultivaluedMap<String, Object>>any())).thenReturn(builder2);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder3);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

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

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.chargesURI(Mockito.<Account>any())).thenReturn("Charges URI");

    CreatePaymentService createPaymentService =
        new CreatePaymentService(client, publicApiUriGenerator, connectorUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    CreateCardPaymentRequest createCardPaymentRequest = mock(CreateCardPaymentRequest.class);
    when(createCardPaymentRequest.toConnectorPayload()).thenReturn("Connector Payload");

    // Act
    CreatedPaymentWithAllLinks actualCreateResult =
        createPaymentService.create(account, createCardPaymentRequest, "Idempotency Key");

    // Assert
    verify(client).target("Charges URI");
    verify(builder2).accept(isA(String[].class));
    verify(builder3).headers(isA(MultivaluedMap.class));
    verify(builder).post(isA(Entity.class));
    verify(webTarget).request();
    verify(outboundJaxrsResponse).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(chargeFromResponse).getAgreementId();
    verify(chargeFromResponse).getAgreementPaymentType();
    verify(chargeFromResponse).getAmount();
    verify(chargeFromResponse).getAuthorisationMode();
    verify(chargeFromResponse).getAuthorisationSummary();
    verify(chargeFromResponse, atLeast(1)).getCardDetailsFromResponse();
    verify(chargeFromResponse).getChargeId();
    verify(chargeFromResponse).getCorporateCardSurcharge();
    verify(chargeFromResponse).getCreatedDate();
    verify(chargeFromResponse).getDelayedCapture();
    verify(chargeFromResponse).getDescription();
    verify(chargeFromResponse).getEmail();
    verify(chargeFromResponse).getExemption();
    verify(chargeFromResponse).getFee();
    verify(chargeFromResponse).getGatewayTransactionId();
    verify(chargeFromResponse).getLanguage();
    verify(chargeFromResponse).getLinks();
    verify(chargeFromResponse).getMetadata();
    verify(chargeFromResponse).getNetAmount();
    verify(chargeFromResponse).getPaymentProvider();
    verify(chargeFromResponse).getReference();
    verify(chargeFromResponse).getRefundSummary();
    verify(chargeFromResponse).getReturnUrl();
    verify(chargeFromResponse).getSettlementSummary();
    verify(chargeFromResponse).getState();
    verify(chargeFromResponse).getTotalAmount();
    verify(chargeFromResponse).getWalletType();
    verify(chargeFromResponse).isMoto();
    verify(createCardPaymentRequest).toConnectorPayload();
    verify(connectorUriGenerator).chargesURI(isA(Account.class));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    PaymentWithAllLinks payment = actualCreateResult.getPayment();
    Optional<CardDetails> cardDetails = payment.getCardDetails();
    CardDetails getResult = cardDetails.get();
    assertEquals("2020-03-01", getResult.getExpiryDate());
    assertEquals("42", getResult.getFirstDigitsCardNumber());
    assertEquals("42", getResult.getLastDigitsCardNumber());
    assertEquals("Card Brand", getResult.getCardBrand());
    assertEquals("Card Brand", payment.getCardBrand());
    assertEquals("Card Holder Name", getResult.getCardHolderName());
    assertEquals("Card Type", getResult.getCardType());
    assertTrue(cardDetails.isPresent());
  }

  /**
   * Test {@link CreatePaymentService#create(Account, CreateCardPaymentRequest, String)}.
   *
   * <ul>
   *   <li>Given {@link ExemptionOutcome#ExemptionOutcome(String)} with result is {@code
   *       Idempotency-Key}.
   * </ul>
   *
   * <p>Method under test: {@link CreatePaymentService#create(Account, CreateCardPaymentRequest,
   * String)}
   */
  @Test
  @DisplayName(
      "Test create(Account, CreateCardPaymentRequest, String); given ExemptionOutcome(String) with result is 'Idempotency-Key'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CreatedPaymentWithAllLinks CreatePaymentService.create(Account, CreateCardPaymentRequest, String)"
  })
  void testCreate_givenExemptionOutcomeWithResultIsIdempotencyKey() throws ProcessingException {
    // Arrange
    ChargeFromResponse chargeFromResponse = mock(ChargeFromResponse.class);
    when(chargeFromResponse.getDelayedCapture()).thenReturn(true);
    when(chargeFromResponse.isMoto()).thenReturn(true);
    when(chargeFromResponse.getAmount()).thenReturn(10L);
    when(chargeFromResponse.getCorporateCardSurcharge()).thenReturn(1L);
    when(chargeFromResponse.getFee()).thenReturn(1L);
    when(chargeFromResponse.getNetAmount()).thenReturn(1L);
    when(chargeFromResponse.getTotalAmount()).thenReturn(1L);
    when(chargeFromResponse.getAgreementId()).thenReturn("42");
    when(chargeFromResponse.getChargeId()).thenReturn("42");
    when(chargeFromResponse.getCreatedDate()).thenReturn("2020-03-01");
    when(chargeFromResponse.getDescription())
        .thenReturn("The characteristics of someone or something");
    when(chargeFromResponse.getEmail()).thenReturn("jane.doe@example.org");
    when(chargeFromResponse.getGatewayTransactionId()).thenReturn("42");
    when(chargeFromResponse.getPaymentProvider()).thenReturn("Payment Provider");
    when(chargeFromResponse.getReference()).thenReturn("Reference");
    when(chargeFromResponse.getReturnUrl()).thenReturn("https://example.org/example");
    when(chargeFromResponse.getLinks()).thenReturn(new ArrayList<>());
    Optional<ExternalMetadata> ofResult = Optional.of(new ExternalMetadata(new HashMap<>()));
    when(chargeFromResponse.getMetadata()).thenReturn(ofResult);
    Optional<Wallet> ofResult2 = Optional.of(Wallet.APPLE_PAY);
    when(chargeFromResponse.getWalletType()).thenReturn(ofResult2);
    when(chargeFromResponse.getAuthorisationSummary()).thenReturn(new AuthorisationSummary());
    Address billingAddress = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");
    CardDetailsFromResponse cardDetailsFromResponse =
        new CardDetailsFromResponse(
            "42",
            "42",
            "Card Holder Name",
            "2020-03-01",
            billingAddress,
            "Card Brand",
            "Card Type");
    when(chargeFromResponse.getCardDetailsFromResponse()).thenReturn(cardDetailsFromResponse);
    Exemption exemption =
        new Exemption(true, "Idempotency-Key", new ExemptionOutcome("Idempotency-Key"));
    when(chargeFromResponse.getExemption()).thenReturn(exemption);
    PaymentSettlementSummary paymentSettlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    when(chargeFromResponse.getSettlementSummary()).thenReturn(paymentSettlementSummary);
    when(chargeFromResponse.getState()).thenReturn(new PaymentState("Status", true));
    when(chargeFromResponse.getRefundSummary()).thenReturn(new RefundSummary("Status", 10L, 10L));
    when(chargeFromResponse.getAgreementPaymentType()).thenReturn(AgreementPaymentType.INSTALMENT);
    when(chargeFromResponse.getAuthorisationMode()).thenReturn(AuthorisationMode.WEB);
    when(chargeFromResponse.getLanguage()).thenReturn(SupportedLanguage.ENGLISH);

    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.readEntity(ChargeFromResponse.class)).thenReturn(chargeFromResponse);
    when(outboundJaxrsResponse.getStatus()).thenReturn(201);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());

    Builder builder = mock(Builder.class);
    when(builder.post(Mockito.<Entity<?>>any())).thenReturn(outboundJaxrsResponse);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(String[].class))).thenReturn(builder);

    Builder builder3 = mock(Builder.class);
    when(builder3.headers(Mockito.<MultivaluedMap<String, Object>>any())).thenReturn(builder2);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder3);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

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

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.chargesURI(Mockito.<Account>any())).thenReturn("Charges URI");

    CreatePaymentService createPaymentService =
        new CreatePaymentService(client, publicApiUriGenerator, connectorUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    CreateCardPaymentRequest createCardPaymentRequest = mock(CreateCardPaymentRequest.class);
    when(createCardPaymentRequest.toConnectorPayload()).thenReturn("Connector Payload");

    // Act
    CreatedPaymentWithAllLinks actualCreateResult =
        createPaymentService.create(account, createCardPaymentRequest, "Idempotency Key");

    // Assert
    verify(client).target("Charges URI");
    verify(builder2).accept(isA(String[].class));
    verify(builder3).headers(isA(MultivaluedMap.class));
    verify(builder).post(isA(Entity.class));
    verify(webTarget).request();
    verify(outboundJaxrsResponse).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(chargeFromResponse).getAgreementId();
    verify(chargeFromResponse).getAgreementPaymentType();
    verify(chargeFromResponse).getAmount();
    verify(chargeFromResponse).getAuthorisationMode();
    verify(chargeFromResponse).getAuthorisationSummary();
    verify(chargeFromResponse, atLeast(1)).getCardDetailsFromResponse();
    verify(chargeFromResponse).getChargeId();
    verify(chargeFromResponse).getCorporateCardSurcharge();
    verify(chargeFromResponse).getCreatedDate();
    verify(chargeFromResponse).getDelayedCapture();
    verify(chargeFromResponse).getDescription();
    verify(chargeFromResponse).getEmail();
    verify(chargeFromResponse).getExemption();
    verify(chargeFromResponse).getFee();
    verify(chargeFromResponse).getGatewayTransactionId();
    verify(chargeFromResponse).getLanguage();
    verify(chargeFromResponse).getLinks();
    verify(chargeFromResponse).getMetadata();
    verify(chargeFromResponse).getNetAmount();
    verify(chargeFromResponse).getPaymentProvider();
    verify(chargeFromResponse).getReference();
    verify(chargeFromResponse).getRefundSummary();
    verify(chargeFromResponse).getReturnUrl();
    verify(chargeFromResponse).getSettlementSummary();
    verify(chargeFromResponse).getState();
    verify(chargeFromResponse).getTotalAmount();
    verify(chargeFromResponse).getWalletType();
    verify(chargeFromResponse).isMoto();
    verify(createCardPaymentRequest).toConnectorPayload();
    verify(connectorUriGenerator).chargesURI(isA(Account.class));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    PaymentWithAllLinks payment = actualCreateResult.getPayment();
    Optional<CardDetails> cardDetails = payment.getCardDetails();
    CardDetails getResult = cardDetails.get();
    assertEquals("2020-03-01", getResult.getExpiryDate());
    assertEquals("42", getResult.getFirstDigitsCardNumber());
    assertEquals("42", getResult.getLastDigitsCardNumber());
    assertEquals("Card Brand", getResult.getCardBrand());
    assertEquals("Card Brand", payment.getCardBrand());
    assertEquals("Card Holder Name", getResult.getCardHolderName());
    assertEquals("Card Type", getResult.getCardType());
    assertTrue(cardDetails.isPresent());
  }

  /**
   * Test {@link CreatePaymentService#create(Account, CreateCardPaymentRequest, String)}.
   *
   * <ul>
   *   <li>Given {@link OutboundJaxrsResponse} {@link OutboundJaxrsResponse#getStatus()} return one.
   *   <li>Then calls {@link OutboundJaxrsResponse#close()}.
   * </ul>
   *
   * <p>Method under test: {@link CreatePaymentService#create(Account, CreateCardPaymentRequest,
   * String)}
   */
  @Test
  @DisplayName(
      "Test create(Account, CreateCardPaymentRequest, String); given OutboundJaxrsResponse getStatus() return one; then calls close()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CreatedPaymentWithAllLinks CreatePaymentService.create(Account, CreateCardPaymentRequest, String)"
  })
  void testCreate_givenOutboundJaxrsResponseGetStatusReturnOne_thenCallsClose()
      throws ProcessingException {
    // Arrange
    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.getStatus()).thenReturn(1);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());
    doNothing().when(outboundJaxrsResponse).close();

    Builder builder = mock(Builder.class);
    when(builder.post(Mockito.<Entity<?>>any())).thenReturn(outboundJaxrsResponse);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(String[].class))).thenReturn(builder);

    Builder builder3 = mock(Builder.class);
    when(builder3.headers(Mockito.<MultivaluedMap<String, Object>>any())).thenReturn(builder2);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder3);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    AdminFactory admin = new AdminFactory();
    admin.setHealthChecks(new HealthCheckConfiguration());
    admin.setTasks(new TaskConfiguration());

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(admin);
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator = new PublicApiUriGenerator(configuration);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.chargesURI(Mockito.<Account>any())).thenReturn("Charges URI");

    CreatePaymentService createPaymentService =
        new CreatePaymentService(client, publicApiUriGenerator, connectorUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    // Act and Assert
    assertThrows(
        CreateChargeException.class,
        () ->
            createPaymentService.create(
                account,
                new CreateCardPaymentRequest(CreateCardPaymentRequestBuilder.builder()),
                "Idempotency Key"));
    verify(client).target("Charges URI");
    verify(builder2).accept(isA(String[].class));
    verify(builder3).headers(isA(MultivaluedMap.class));
    verify(builder).post(isA(Entity.class));
    verify(webTarget).request();
    verify(outboundJaxrsResponse).close();
    verify(outboundJaxrsResponse, atLeast(1)).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(connectorUriGenerator).chargesURI(isA(Account.class));
  }

  /**
   * Test {@link CreatePaymentService#create(Account, CreateCardPaymentRequest, String)}.
   *
   * <ul>
   *   <li>Given {@link OutboundJaxrsResponse} {@link OutboundJaxrsResponse#getStatus()} return one.
   *   <li>Then calls {@link OutboundJaxrsResponse#close()}.
   * </ul>
   *
   * <p>Method under test: {@link CreatePaymentService#create(Account, CreateCardPaymentRequest,
   * String)}
   */
  @Test
  @DisplayName(
      "Test create(Account, CreateCardPaymentRequest, String); given OutboundJaxrsResponse getStatus() return one; then calls close()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CreatedPaymentWithAllLinks CreatePaymentService.create(Account, CreateCardPaymentRequest, String)"
  })
  void testCreate_givenOutboundJaxrsResponseGetStatusReturnOne_thenCallsClose2()
      throws ProcessingException {
    // Arrange
    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.getStatus()).thenReturn(1);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());
    doNothing().when(outboundJaxrsResponse).close();

    Builder builder = mock(Builder.class);
    when(builder.post(Mockito.<Entity<?>>any())).thenReturn(outboundJaxrsResponse);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(String[].class))).thenReturn(builder);

    Builder builder3 = mock(Builder.class);
    when(builder3.headers(Mockito.<MultivaluedMap<String, Object>>any())).thenReturn(builder2);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder3);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    AdminFactory admin = new AdminFactory();
    admin.setHealthChecks(new HealthCheckConfiguration());
    admin.setTasks(new TaskConfiguration());

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(admin);
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    PublicApiUriGenerator publicApiUriGenerator = new PublicApiUriGenerator(configuration);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.chargesURI(Mockito.<Account>any())).thenReturn("Charges URI");

    CreatePaymentService createPaymentService =
        new CreatePaymentService(client, publicApiUriGenerator, connectorUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    CreateCardPaymentRequest createCardPaymentRequest = mock(CreateCardPaymentRequest.class);
    when(createCardPaymentRequest.toConnectorPayload()).thenReturn("Connector Payload");

    // Act and Assert
    assertThrows(
        CreateChargeException.class,
        () -> createPaymentService.create(account, createCardPaymentRequest, "Idempotency Key"));
    verify(client).target("Charges URI");
    verify(builder2).accept(isA(String[].class));
    verify(builder3).headers(isA(MultivaluedMap.class));
    verify(builder).post(isA(Entity.class));
    verify(webTarget).request();
    verify(outboundJaxrsResponse).close();
    verify(outboundJaxrsResponse, atLeast(1)).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(createCardPaymentRequest).toConnectorPayload();
    verify(connectorUriGenerator).chargesURI(isA(Account.class));
  }

  /**
   * Test {@link CreatePaymentService#create(Account, CreateCardPaymentRequest, String)}.
   *
   * <ul>
   *   <li>Given {@link ThreeDSecure#ThreeDSecure(boolean)} with required is {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link CreatePaymentService#create(Account, CreateCardPaymentRequest,
   * String)}
   */
  @Test
  @DisplayName(
      "Test create(Account, CreateCardPaymentRequest, String); given ThreeDSecure(boolean) with required is 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CreatedPaymentWithAllLinks CreatePaymentService.create(Account, CreateCardPaymentRequest, String)"
  })
  void testCreate_givenThreeDSecureWithRequiredIsFalse() throws ProcessingException {
    // Arrange
    ChargeFromResponse chargeFromResponse = mock(ChargeFromResponse.class);
    when(chargeFromResponse.getDelayedCapture()).thenReturn(true);
    when(chargeFromResponse.isMoto()).thenReturn(true);
    when(chargeFromResponse.getAmount()).thenReturn(10L);
    when(chargeFromResponse.getCorporateCardSurcharge()).thenReturn(1L);
    when(chargeFromResponse.getFee()).thenReturn(1L);
    when(chargeFromResponse.getNetAmount()).thenReturn(1L);
    when(chargeFromResponse.getTotalAmount()).thenReturn(1L);
    when(chargeFromResponse.getAgreementId()).thenReturn("42");
    when(chargeFromResponse.getChargeId()).thenReturn("42");
    when(chargeFromResponse.getCreatedDate()).thenReturn("2020-03-01");
    when(chargeFromResponse.getDescription())
        .thenReturn("The characteristics of someone or something");
    when(chargeFromResponse.getEmail()).thenReturn("jane.doe@example.org");
    when(chargeFromResponse.getGatewayTransactionId()).thenReturn("42");
    when(chargeFromResponse.getPaymentProvider()).thenReturn("Payment Provider");
    when(chargeFromResponse.getReference()).thenReturn("Reference");
    when(chargeFromResponse.getReturnUrl()).thenReturn("https://example.org/example");
    when(chargeFromResponse.getLinks()).thenReturn(new ArrayList<>());
    Optional<ExternalMetadata> ofResult = Optional.of(new ExternalMetadata(new HashMap<>()));
    when(chargeFromResponse.getMetadata()).thenReturn(ofResult);
    Optional<Wallet> ofResult2 = Optional.of(Wallet.APPLE_PAY);
    when(chargeFromResponse.getWalletType()).thenReturn(ofResult2);
    when(chargeFromResponse.getAuthorisationSummary())
        .thenReturn(new AuthorisationSummary(new ThreeDSecure(false)));
    Address billingAddress = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");
    CardDetailsFromResponse cardDetailsFromResponse =
        new CardDetailsFromResponse(
            "42",
            "42",
            "Card Holder Name",
            "2020-03-01",
            billingAddress,
            "Card Brand",
            "Card Type");
    when(chargeFromResponse.getCardDetailsFromResponse()).thenReturn(cardDetailsFromResponse);
    when(chargeFromResponse.getExemption()).thenReturn(new Exemption());
    PaymentSettlementSummary paymentSettlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    when(chargeFromResponse.getSettlementSummary()).thenReturn(paymentSettlementSummary);
    when(chargeFromResponse.getState()).thenReturn(new PaymentState("Status", true));
    when(chargeFromResponse.getRefundSummary()).thenReturn(new RefundSummary("Status", 10L, 10L));
    when(chargeFromResponse.getAgreementPaymentType()).thenReturn(AgreementPaymentType.INSTALMENT);
    when(chargeFromResponse.getAuthorisationMode()).thenReturn(AuthorisationMode.WEB);
    when(chargeFromResponse.getLanguage()).thenReturn(SupportedLanguage.ENGLISH);

    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.readEntity(ChargeFromResponse.class)).thenReturn(chargeFromResponse);
    when(outboundJaxrsResponse.getStatus()).thenReturn(201);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());

    Builder builder = mock(Builder.class);
    when(builder.post(Mockito.<Entity<?>>any())).thenReturn(outboundJaxrsResponse);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(String[].class))).thenReturn(builder);

    Builder builder3 = mock(Builder.class);
    when(builder3.headers(Mockito.<MultivaluedMap<String, Object>>any())).thenReturn(builder2);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder3);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

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

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.chargesURI(Mockito.<Account>any())).thenReturn("Charges URI");

    CreatePaymentService createPaymentService =
        new CreatePaymentService(client, publicApiUriGenerator, connectorUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    CreateCardPaymentRequest createCardPaymentRequest = mock(CreateCardPaymentRequest.class);
    when(createCardPaymentRequest.toConnectorPayload()).thenReturn("Connector Payload");

    // Act
    CreatedPaymentWithAllLinks actualCreateResult =
        createPaymentService.create(account, createCardPaymentRequest, "Idempotency Key");

    // Assert
    verify(client).target("Charges URI");
    verify(builder2).accept(isA(String[].class));
    verify(builder3).headers(isA(MultivaluedMap.class));
    verify(builder).post(isA(Entity.class));
    verify(webTarget).request();
    verify(outboundJaxrsResponse).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(chargeFromResponse).getAgreementId();
    verify(chargeFromResponse).getAgreementPaymentType();
    verify(chargeFromResponse).getAmount();
    verify(chargeFromResponse).getAuthorisationMode();
    verify(chargeFromResponse).getAuthorisationSummary();
    verify(chargeFromResponse, atLeast(1)).getCardDetailsFromResponse();
    verify(chargeFromResponse).getChargeId();
    verify(chargeFromResponse).getCorporateCardSurcharge();
    verify(chargeFromResponse).getCreatedDate();
    verify(chargeFromResponse).getDelayedCapture();
    verify(chargeFromResponse).getDescription();
    verify(chargeFromResponse).getEmail();
    verify(chargeFromResponse).getExemption();
    verify(chargeFromResponse).getFee();
    verify(chargeFromResponse).getGatewayTransactionId();
    verify(chargeFromResponse).getLanguage();
    verify(chargeFromResponse).getLinks();
    verify(chargeFromResponse).getMetadata();
    verify(chargeFromResponse).getNetAmount();
    verify(chargeFromResponse).getPaymentProvider();
    verify(chargeFromResponse).getReference();
    verify(chargeFromResponse).getRefundSummary();
    verify(chargeFromResponse).getReturnUrl();
    verify(chargeFromResponse).getSettlementSummary();
    verify(chargeFromResponse).getState();
    verify(chargeFromResponse).getTotalAmount();
    verify(chargeFromResponse).getWalletType();
    verify(chargeFromResponse).isMoto();
    verify(createCardPaymentRequest).toConnectorPayload();
    verify(connectorUriGenerator).chargesURI(isA(Account.class));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    PaymentWithAllLinks payment = actualCreateResult.getPayment();
    Optional<CardDetails> cardDetails = payment.getCardDetails();
    CardDetails getResult = cardDetails.get();
    assertEquals("2020-03-01", getResult.getExpiryDate());
    assertEquals("42", getResult.getFirstDigitsCardNumber());
    assertEquals("42", getResult.getLastDigitsCardNumber());
    assertEquals("Card Brand", getResult.getCardBrand());
    assertEquals("Card Brand", payment.getCardBrand());
    assertEquals("Card Holder Name", getResult.getCardHolderName());
    assertEquals("Card Type", getResult.getCardType());
    assertTrue(cardDetails.isPresent());
  }

  /**
   * Test {@link CreatePaymentService#create(Account, CreateCardPaymentRequest, String)}.
   *
   * <ul>
   *   <li>Then return not Payment CardDetails WalletType Present.
   * </ul>
   *
   * <p>Method under test: {@link CreatePaymentService#create(Account, CreateCardPaymentRequest,
   * String)}
   */
  @Test
  @DisplayName(
      "Test create(Account, CreateCardPaymentRequest, String); then return not Payment CardDetails WalletType Present")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CreatedPaymentWithAllLinks CreatePaymentService.create(Account, CreateCardPaymentRequest, String)"
  })
  void testCreate_thenReturnNotPaymentCardDetailsWalletTypePresent() throws ProcessingException {
    // Arrange
    ChargeFromResponse chargeFromResponse = mock(ChargeFromResponse.class);
    when(chargeFromResponse.getDelayedCapture()).thenReturn(true);
    when(chargeFromResponse.isMoto()).thenReturn(true);
    when(chargeFromResponse.getAmount()).thenReturn(10L);
    when(chargeFromResponse.getCorporateCardSurcharge()).thenReturn(1L);
    when(chargeFromResponse.getFee()).thenReturn(1L);
    when(chargeFromResponse.getNetAmount()).thenReturn(1L);
    when(chargeFromResponse.getTotalAmount()).thenReturn(1L);
    when(chargeFromResponse.getAgreementId()).thenReturn("42");
    when(chargeFromResponse.getChargeId()).thenReturn("42");
    when(chargeFromResponse.getCreatedDate()).thenReturn("2020-03-01");
    when(chargeFromResponse.getDescription())
        .thenReturn("The characteristics of someone or something");
    when(chargeFromResponse.getEmail()).thenReturn("jane.doe@example.org");
    when(chargeFromResponse.getGatewayTransactionId()).thenReturn("42");
    when(chargeFromResponse.getPaymentProvider()).thenReturn("Payment Provider");
    when(chargeFromResponse.getReference()).thenReturn("Reference");
    when(chargeFromResponse.getReturnUrl()).thenReturn("https://example.org/example");
    when(chargeFromResponse.getLinks()).thenReturn(new ArrayList<>());
    Optional<ExternalMetadata> ofResult = Optional.of(new ExternalMetadata(new HashMap<>()));
    when(chargeFromResponse.getMetadata()).thenReturn(ofResult);
    Optional<Wallet> emptyResult = Optional.empty();
    when(chargeFromResponse.getWalletType()).thenReturn(emptyResult);
    when(chargeFromResponse.getAuthorisationSummary()).thenReturn(new AuthorisationSummary());
    Address billingAddress = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");
    CardDetailsFromResponse cardDetailsFromResponse =
        new CardDetailsFromResponse(
            "42",
            "42",
            "Card Holder Name",
            "2020-03-01",
            billingAddress,
            "Card Brand",
            "Card Type");
    when(chargeFromResponse.getCardDetailsFromResponse()).thenReturn(cardDetailsFromResponse);
    when(chargeFromResponse.getExemption()).thenReturn(new Exemption());
    PaymentSettlementSummary paymentSettlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    when(chargeFromResponse.getSettlementSummary()).thenReturn(paymentSettlementSummary);
    when(chargeFromResponse.getState()).thenReturn(new PaymentState("Status", true));
    when(chargeFromResponse.getRefundSummary()).thenReturn(new RefundSummary("Status", 10L, 10L));
    when(chargeFromResponse.getAgreementPaymentType()).thenReturn(AgreementPaymentType.INSTALMENT);
    when(chargeFromResponse.getAuthorisationMode()).thenReturn(AuthorisationMode.WEB);
    when(chargeFromResponse.getLanguage()).thenReturn(SupportedLanguage.ENGLISH);

    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.readEntity(ChargeFromResponse.class)).thenReturn(chargeFromResponse);
    when(outboundJaxrsResponse.getStatus()).thenReturn(201);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());

    Builder builder = mock(Builder.class);
    when(builder.post(Mockito.<Entity<?>>any())).thenReturn(outboundJaxrsResponse);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(String[].class))).thenReturn(builder);

    Builder builder3 = mock(Builder.class);
    when(builder3.headers(Mockito.<MultivaluedMap<String, Object>>any())).thenReturn(builder2);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder3);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

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

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.chargesURI(Mockito.<Account>any())).thenReturn("Charges URI");

    CreatePaymentService createPaymentService =
        new CreatePaymentService(client, publicApiUriGenerator, connectorUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    CreateCardPaymentRequest createCardPaymentRequest = mock(CreateCardPaymentRequest.class);
    when(createCardPaymentRequest.toConnectorPayload()).thenReturn("Connector Payload");

    // Act
    CreatedPaymentWithAllLinks actualCreateResult =
        createPaymentService.create(account, createCardPaymentRequest, "Idempotency Key");

    // Assert
    verify(client).target("Charges URI");
    verify(builder2).accept(isA(String[].class));
    verify(builder3).headers(isA(MultivaluedMap.class));
    verify(builder).post(isA(Entity.class));
    verify(webTarget).request();
    verify(outboundJaxrsResponse).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(chargeFromResponse).getAgreementId();
    verify(chargeFromResponse).getAgreementPaymentType();
    verify(chargeFromResponse).getAmount();
    verify(chargeFromResponse).getAuthorisationMode();
    verify(chargeFromResponse).getAuthorisationSummary();
    verify(chargeFromResponse).getCardDetailsFromResponse();
    verify(chargeFromResponse).getChargeId();
    verify(chargeFromResponse).getCorporateCardSurcharge();
    verify(chargeFromResponse).getCreatedDate();
    verify(chargeFromResponse).getDelayedCapture();
    verify(chargeFromResponse).getDescription();
    verify(chargeFromResponse).getEmail();
    verify(chargeFromResponse).getExemption();
    verify(chargeFromResponse).getFee();
    verify(chargeFromResponse).getGatewayTransactionId();
    verify(chargeFromResponse).getLanguage();
    verify(chargeFromResponse).getLinks();
    verify(chargeFromResponse).getMetadata();
    verify(chargeFromResponse).getNetAmount();
    verify(chargeFromResponse).getPaymentProvider();
    verify(chargeFromResponse).getReference();
    verify(chargeFromResponse).getRefundSummary();
    verify(chargeFromResponse).getReturnUrl();
    verify(chargeFromResponse).getSettlementSummary();
    verify(chargeFromResponse).getState();
    verify(chargeFromResponse).getTotalAmount();
    verify(chargeFromResponse).getWalletType();
    verify(chargeFromResponse).isMoto();
    verify(createCardPaymentRequest).toConnectorPayload();
    verify(connectorUriGenerator).chargesURI(isA(Account.class));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    PaymentWithAllLinks payment = actualCreateResult.getPayment();
    Optional<CardDetails> cardDetails = payment.getCardDetails();
    CardDetails getResult = cardDetails.get();
    assertEquals("2020-03-01", getResult.getExpiryDate());
    assertEquals("42", getResult.getFirstDigitsCardNumber());
    assertEquals("42", getResult.getLastDigitsCardNumber());
    assertEquals("Card Brand", getResult.getCardBrand());
    assertEquals("Card Brand", payment.getCardBrand());
    assertEquals("Card Holder Name", getResult.getCardHolderName());
    assertEquals("Card Type", getResult.getCardType());
    assertFalse(getResult.getWalletType().isPresent());
    assertTrue(cardDetails.isPresent());
  }

  /**
   * Test {@link CreatePaymentService#create(Account, CreateCardPaymentRequest, String)}.
   *
   * <ul>
   *   <li>Then return Payment CardBrand is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link CreatePaymentService#create(Account, CreateCardPaymentRequest,
   * String)}
   */
  @Test
  @DisplayName(
      "Test create(Account, CreateCardPaymentRequest, String); then return Payment CardBrand is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CreatedPaymentWithAllLinks CreatePaymentService.create(Account, CreateCardPaymentRequest, String)"
  })
  void testCreate_thenReturnPaymentCardBrandIsNull() throws ProcessingException {
    // Arrange
    ChargeFromResponse chargeFromResponse = mock(ChargeFromResponse.class);
    when(chargeFromResponse.getDelayedCapture()).thenReturn(true);
    when(chargeFromResponse.isMoto()).thenReturn(true);
    when(chargeFromResponse.getAmount()).thenReturn(10L);
    when(chargeFromResponse.getCorporateCardSurcharge()).thenReturn(1L);
    when(chargeFromResponse.getFee()).thenReturn(1L);
    when(chargeFromResponse.getNetAmount()).thenReturn(1L);
    when(chargeFromResponse.getTotalAmount()).thenReturn(1L);
    when(chargeFromResponse.getAgreementId()).thenReturn("42");
    when(chargeFromResponse.getChargeId()).thenReturn("42");
    when(chargeFromResponse.getCreatedDate()).thenReturn("2020-03-01");
    when(chargeFromResponse.getDescription())
        .thenReturn("The characteristics of someone or something");
    when(chargeFromResponse.getEmail()).thenReturn("jane.doe@example.org");
    when(chargeFromResponse.getGatewayTransactionId()).thenReturn("42");
    when(chargeFromResponse.getPaymentProvider()).thenReturn("Payment Provider");
    when(chargeFromResponse.getReference()).thenReturn("Reference");
    when(chargeFromResponse.getReturnUrl()).thenReturn("https://example.org/example");
    when(chargeFromResponse.getLinks()).thenReturn(new ArrayList<>());
    Optional<ExternalMetadata> ofResult = Optional.of(new ExternalMetadata(new HashMap<>()));
    when(chargeFromResponse.getMetadata()).thenReturn(ofResult);
    Optional<Wallet> ofResult2 = Optional.of(Wallet.APPLE_PAY);
    when(chargeFromResponse.getWalletType()).thenReturn(ofResult2);
    when(chargeFromResponse.getAuthorisationSummary()).thenReturn(new AuthorisationSummary());
    when(chargeFromResponse.getCardDetailsFromResponse()).thenReturn(null);
    when(chargeFromResponse.getExemption()).thenReturn(new Exemption());
    PaymentSettlementSummary paymentSettlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    when(chargeFromResponse.getSettlementSummary()).thenReturn(paymentSettlementSummary);
    PaymentState paymentState = new PaymentState("Status", true);
    when(chargeFromResponse.getState()).thenReturn(paymentState);
    when(chargeFromResponse.getRefundSummary()).thenReturn(new RefundSummary("Status", 10L, 10L));
    when(chargeFromResponse.getAgreementPaymentType()).thenReturn(AgreementPaymentType.INSTALMENT);
    when(chargeFromResponse.getAuthorisationMode()).thenReturn(AuthorisationMode.WEB);
    when(chargeFromResponse.getLanguage()).thenReturn(SupportedLanguage.ENGLISH);

    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.readEntity(ChargeFromResponse.class)).thenReturn(chargeFromResponse);
    when(outboundJaxrsResponse.getStatus()).thenReturn(201);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());

    Builder builder = mock(Builder.class);
    when(builder.post(Mockito.<Entity<?>>any())).thenReturn(outboundJaxrsResponse);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(String[].class))).thenReturn(builder);

    Builder builder3 = mock(Builder.class);
    when(builder3.headers(Mockito.<MultivaluedMap<String, Object>>any())).thenReturn(builder2);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder3);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

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

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.chargesURI(Mockito.<Account>any())).thenReturn("Charges URI");

    CreatePaymentService createPaymentService =
        new CreatePaymentService(client, publicApiUriGenerator, connectorUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    CreateCardPaymentRequest createCardPaymentRequest = mock(CreateCardPaymentRequest.class);
    when(createCardPaymentRequest.toConnectorPayload()).thenReturn("Connector Payload");

    // Act
    CreatedPaymentWithAllLinks actualCreateResult =
        createPaymentService.create(account, createCardPaymentRequest, "Idempotency Key");

    // Assert
    verify(client).target("Charges URI");
    verify(builder2).accept(isA(String[].class));
    verify(builder3).headers(isA(MultivaluedMap.class));
    verify(builder).post(isA(Entity.class));
    verify(webTarget).request();
    verify(outboundJaxrsResponse).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(chargeFromResponse).getAgreementId();
    verify(chargeFromResponse).getAgreementPaymentType();
    verify(chargeFromResponse).getAmount();
    verify(chargeFromResponse).getAuthorisationMode();
    verify(chargeFromResponse).getAuthorisationSummary();
    verify(chargeFromResponse, atLeast(1)).getCardDetailsFromResponse();
    verify(chargeFromResponse).getChargeId();
    verify(chargeFromResponse).getCorporateCardSurcharge();
    verify(chargeFromResponse).getCreatedDate();
    verify(chargeFromResponse).getDelayedCapture();
    verify(chargeFromResponse).getDescription();
    verify(chargeFromResponse).getEmail();
    verify(chargeFromResponse).getExemption();
    verify(chargeFromResponse).getFee();
    verify(chargeFromResponse).getGatewayTransactionId();
    verify(chargeFromResponse).getLanguage();
    verify(chargeFromResponse).getLinks();
    verify(chargeFromResponse).getMetadata();
    verify(chargeFromResponse).getNetAmount();
    verify(chargeFromResponse).getPaymentProvider();
    verify(chargeFromResponse).getReference();
    verify(chargeFromResponse).getRefundSummary();
    verify(chargeFromResponse).getReturnUrl();
    verify(chargeFromResponse).getSettlementSummary();
    verify(chargeFromResponse).getState();
    verify(chargeFromResponse).getTotalAmount();
    verify(chargeFromResponse).getWalletType();
    verify(chargeFromResponse).isMoto();
    verify(createCardPaymentRequest).toConnectorPayload();
    verify(connectorUriGenerator).chargesURI(isA(Account.class));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    PaymentWithAllLinks payment = actualCreateResult.getPayment();
    assertNull(payment.getCardBrand());
    assertNull(payment.getAuthorisationSummary());
    assertNull(payment.getLinks().getCancel());
    assertFalse(payment.getCardDetails().isPresent());
    PaymentState state = payment.getState();
    assertTrue(state.isFinished());
    assertSame(paymentState, state);
  }

  /**
   * Test {@link CreatePaymentService#create(Account, CreateCardPaymentRequest, String)}.
   *
   * <ul>
   *   <li>Then return Payment CardDetails ExpiryDate is {@code 2020-03-01}.
   * </ul>
   *
   * <p>Method under test: {@link CreatePaymentService#create(Account, CreateCardPaymentRequest,
   * String)}
   */
  @Test
  @DisplayName(
      "Test create(Account, CreateCardPaymentRequest, String); then return Payment CardDetails ExpiryDate is '2020-03-01'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CreatedPaymentWithAllLinks CreatePaymentService.create(Account, CreateCardPaymentRequest, String)"
  })
  void testCreate_thenReturnPaymentCardDetailsExpiryDateIs20200301() throws ProcessingException {
    // Arrange
    ChargeFromResponse chargeFromResponse = mock(ChargeFromResponse.class);
    when(chargeFromResponse.getDelayedCapture()).thenReturn(true);
    when(chargeFromResponse.isMoto()).thenReturn(true);
    when(chargeFromResponse.getAmount()).thenReturn(10L);
    when(chargeFromResponse.getCorporateCardSurcharge()).thenReturn(1L);
    when(chargeFromResponse.getFee()).thenReturn(1L);
    when(chargeFromResponse.getNetAmount()).thenReturn(1L);
    when(chargeFromResponse.getTotalAmount()).thenReturn(1L);
    when(chargeFromResponse.getAgreementId()).thenReturn("42");
    when(chargeFromResponse.getChargeId()).thenReturn("42");
    when(chargeFromResponse.getCreatedDate()).thenReturn("2020-03-01");
    when(chargeFromResponse.getDescription())
        .thenReturn("The characteristics of someone or something");
    when(chargeFromResponse.getEmail()).thenReturn("jane.doe@example.org");
    when(chargeFromResponse.getGatewayTransactionId()).thenReturn("42");
    when(chargeFromResponse.getPaymentProvider()).thenReturn("Payment Provider");
    when(chargeFromResponse.getReference()).thenReturn("Reference");
    when(chargeFromResponse.getReturnUrl()).thenReturn("https://example.org/example");
    when(chargeFromResponse.getLinks()).thenReturn(new ArrayList<>());
    Optional<ExternalMetadata> ofResult = Optional.of(new ExternalMetadata(new HashMap<>()));
    when(chargeFromResponse.getMetadata()).thenReturn(ofResult);
    Optional<Wallet> ofResult2 = Optional.of(Wallet.APPLE_PAY);
    when(chargeFromResponse.getWalletType()).thenReturn(ofResult2);
    when(chargeFromResponse.getAuthorisationSummary()).thenReturn(new AuthorisationSummary());
    Address billingAddress = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");
    CardDetailsFromResponse cardDetailsFromResponse =
        new CardDetailsFromResponse(
            "42",
            "42",
            "Card Holder Name",
            "2020-03-01",
            billingAddress,
            "Card Brand",
            "Card Type");
    when(chargeFromResponse.getCardDetailsFromResponse()).thenReturn(cardDetailsFromResponse);
    when(chargeFromResponse.getExemption()).thenReturn(new Exemption());
    PaymentSettlementSummary paymentSettlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    when(chargeFromResponse.getSettlementSummary()).thenReturn(paymentSettlementSummary);
    when(chargeFromResponse.getState()).thenReturn(new PaymentState("Status", true));
    when(chargeFromResponse.getRefundSummary()).thenReturn(new RefundSummary("Status", 10L, 10L));
    when(chargeFromResponse.getAgreementPaymentType()).thenReturn(AgreementPaymentType.INSTALMENT);
    when(chargeFromResponse.getAuthorisationMode()).thenReturn(AuthorisationMode.WEB);
    when(chargeFromResponse.getLanguage()).thenReturn(SupportedLanguage.ENGLISH);

    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.readEntity(ChargeFromResponse.class)).thenReturn(chargeFromResponse);
    when(outboundJaxrsResponse.getStatus()).thenReturn(201);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());

    Builder builder = mock(Builder.class);
    when(builder.post(Mockito.<Entity<?>>any())).thenReturn(outboundJaxrsResponse);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(String[].class))).thenReturn(builder);

    Builder builder3 = mock(Builder.class);
    when(builder3.headers(Mockito.<MultivaluedMap<String, Object>>any())).thenReturn(builder2);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder3);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

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

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.chargesURI(Mockito.<Account>any())).thenReturn("Charges URI");

    CreatePaymentService createPaymentService =
        new CreatePaymentService(client, publicApiUriGenerator, connectorUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    CreateCardPaymentRequest createCardPaymentRequest = mock(CreateCardPaymentRequest.class);
    when(createCardPaymentRequest.toConnectorPayload()).thenReturn("Connector Payload");

    // Act
    CreatedPaymentWithAllLinks actualCreateResult =
        createPaymentService.create(account, createCardPaymentRequest, "Idempotency Key");

    // Assert
    verify(client).target("Charges URI");
    verify(builder2).accept(isA(String[].class));
    verify(builder3).headers(isA(MultivaluedMap.class));
    verify(builder).post(isA(Entity.class));
    verify(webTarget).request();
    verify(outboundJaxrsResponse).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(chargeFromResponse).getAgreementId();
    verify(chargeFromResponse).getAgreementPaymentType();
    verify(chargeFromResponse).getAmount();
    verify(chargeFromResponse).getAuthorisationMode();
    verify(chargeFromResponse).getAuthorisationSummary();
    verify(chargeFromResponse, atLeast(1)).getCardDetailsFromResponse();
    verify(chargeFromResponse).getChargeId();
    verify(chargeFromResponse).getCorporateCardSurcharge();
    verify(chargeFromResponse).getCreatedDate();
    verify(chargeFromResponse).getDelayedCapture();
    verify(chargeFromResponse).getDescription();
    verify(chargeFromResponse).getEmail();
    verify(chargeFromResponse).getExemption();
    verify(chargeFromResponse).getFee();
    verify(chargeFromResponse).getGatewayTransactionId();
    verify(chargeFromResponse).getLanguage();
    verify(chargeFromResponse).getLinks();
    verify(chargeFromResponse).getMetadata();
    verify(chargeFromResponse).getNetAmount();
    verify(chargeFromResponse).getPaymentProvider();
    verify(chargeFromResponse).getReference();
    verify(chargeFromResponse).getRefundSummary();
    verify(chargeFromResponse).getReturnUrl();
    verify(chargeFromResponse).getSettlementSummary();
    verify(chargeFromResponse).getState();
    verify(chargeFromResponse).getTotalAmount();
    verify(chargeFromResponse).getWalletType();
    verify(chargeFromResponse).isMoto();
    verify(createCardPaymentRequest).toConnectorPayload();
    verify(connectorUriGenerator).chargesURI(isA(Account.class));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    PaymentWithAllLinks payment = actualCreateResult.getPayment();
    Optional<CardDetails> cardDetails = payment.getCardDetails();
    CardDetails getResult = cardDetails.get();
    assertEquals("2020-03-01", getResult.getExpiryDate());
    assertEquals("42", getResult.getFirstDigitsCardNumber());
    assertEquals("42", getResult.getLastDigitsCardNumber());
    assertEquals("Card Brand", getResult.getCardBrand());
    assertEquals("Card Brand", payment.getCardBrand());
    assertEquals("Card Holder Name", getResult.getCardHolderName());
    assertEquals("Card Type", getResult.getCardType());
    assertTrue(cardDetails.isPresent());
  }

  /**
   * Test {@link CreatePaymentService#create(Account, CreateCardPaymentRequest, String)}.
   *
   * <ul>
   *   <li>Then return Payment CardDetails WalletType is {@code Apple Pay}.
   * </ul>
   *
   * <p>Method under test: {@link CreatePaymentService#create(Account, CreateCardPaymentRequest,
   * String)}
   */
  @Test
  @DisplayName(
      "Test create(Account, CreateCardPaymentRequest, String); then return Payment CardDetails WalletType is 'Apple Pay'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CreatedPaymentWithAllLinks CreatePaymentService.create(Account, CreateCardPaymentRequest, String)"
  })
  void testCreate_thenReturnPaymentCardDetailsWalletTypeIsApplePay() throws ProcessingException {
    // Arrange
    ChargeFromResponse chargeFromResponse = mock(ChargeFromResponse.class);
    when(chargeFromResponse.getDelayedCapture()).thenReturn(true);
    when(chargeFromResponse.isMoto()).thenReturn(true);
    when(chargeFromResponse.getAmount()).thenReturn(10L);
    when(chargeFromResponse.getCorporateCardSurcharge()).thenReturn(1L);
    when(chargeFromResponse.getFee()).thenReturn(1L);
    when(chargeFromResponse.getNetAmount()).thenReturn(1L);
    when(chargeFromResponse.getTotalAmount()).thenReturn(1L);
    when(chargeFromResponse.getAgreementId()).thenReturn("42");
    when(chargeFromResponse.getChargeId()).thenReturn("42");
    when(chargeFromResponse.getCreatedDate()).thenReturn("2020-03-01");
    when(chargeFromResponse.getDescription())
        .thenReturn("The characteristics of someone or something");
    when(chargeFromResponse.getEmail()).thenReturn("jane.doe@example.org");
    when(chargeFromResponse.getGatewayTransactionId()).thenReturn("42");
    when(chargeFromResponse.getPaymentProvider()).thenReturn("Payment Provider");
    when(chargeFromResponse.getReference()).thenReturn("Reference");
    when(chargeFromResponse.getReturnUrl()).thenReturn("https://example.org/example");
    when(chargeFromResponse.getLinks()).thenReturn(new ArrayList<>());
    Optional<ExternalMetadata> ofResult = Optional.of(new ExternalMetadata(new HashMap<>()));
    when(chargeFromResponse.getMetadata()).thenReturn(ofResult);
    Optional<Wallet> ofResult2 = Optional.of(Wallet.APPLE_PAY);
    when(chargeFromResponse.getWalletType()).thenReturn(ofResult2);
    AuthorisationSummary authorisationSummary = new AuthorisationSummary(new ThreeDSecure(true));
    when(chargeFromResponse.getAuthorisationSummary()).thenReturn(authorisationSummary);
    Address billingAddress = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");
    CardDetailsFromResponse cardDetailsFromResponse =
        new CardDetailsFromResponse(
            "42",
            "42",
            "Card Holder Name",
            "2020-03-01",
            billingAddress,
            "Card Brand",
            "Card Type");
    when(chargeFromResponse.getCardDetailsFromResponse()).thenReturn(cardDetailsFromResponse);
    when(chargeFromResponse.getExemption()).thenReturn(new Exemption());
    PaymentSettlementSummary paymentSettlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    when(chargeFromResponse.getSettlementSummary()).thenReturn(paymentSettlementSummary);
    when(chargeFromResponse.getState()).thenReturn(new PaymentState("Status", true));
    when(chargeFromResponse.getRefundSummary()).thenReturn(new RefundSummary("Status", 10L, 10L));
    when(chargeFromResponse.getAgreementPaymentType()).thenReturn(AgreementPaymentType.INSTALMENT);
    when(chargeFromResponse.getAuthorisationMode()).thenReturn(AuthorisationMode.WEB);
    when(chargeFromResponse.getLanguage()).thenReturn(SupportedLanguage.ENGLISH);

    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.readEntity(ChargeFromResponse.class)).thenReturn(chargeFromResponse);
    when(outboundJaxrsResponse.getStatus()).thenReturn(201);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());

    Builder builder = mock(Builder.class);
    when(builder.post(Mockito.<Entity<?>>any())).thenReturn(outboundJaxrsResponse);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(String[].class))).thenReturn(builder);

    Builder builder3 = mock(Builder.class);
    when(builder3.headers(Mockito.<MultivaluedMap<String, Object>>any())).thenReturn(builder2);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder3);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

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

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.chargesURI(Mockito.<Account>any())).thenReturn("Charges URI");

    CreatePaymentService createPaymentService =
        new CreatePaymentService(client, publicApiUriGenerator, connectorUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    CreateCardPaymentRequest createCardPaymentRequest = mock(CreateCardPaymentRequest.class);
    when(createCardPaymentRequest.toConnectorPayload()).thenReturn("Connector Payload");

    // Act
    CreatedPaymentWithAllLinks actualCreateResult =
        createPaymentService.create(account, createCardPaymentRequest, "Idempotency Key");

    // Assert
    verify(client).target("Charges URI");
    verify(builder2).accept(isA(String[].class));
    verify(builder3).headers(isA(MultivaluedMap.class));
    verify(builder).post(isA(Entity.class));
    verify(webTarget).request();
    verify(outboundJaxrsResponse).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(chargeFromResponse).getAgreementId();
    verify(chargeFromResponse).getAgreementPaymentType();
    verify(chargeFromResponse).getAmount();
    verify(chargeFromResponse).getAuthorisationMode();
    verify(chargeFromResponse).getAuthorisationSummary();
    verify(chargeFromResponse, atLeast(1)).getCardDetailsFromResponse();
    verify(chargeFromResponse).getChargeId();
    verify(chargeFromResponse).getCorporateCardSurcharge();
    verify(chargeFromResponse).getCreatedDate();
    verify(chargeFromResponse).getDelayedCapture();
    verify(chargeFromResponse).getDescription();
    verify(chargeFromResponse).getEmail();
    verify(chargeFromResponse).getExemption();
    verify(chargeFromResponse).getFee();
    verify(chargeFromResponse).getGatewayTransactionId();
    verify(chargeFromResponse).getLanguage();
    verify(chargeFromResponse).getLinks();
    verify(chargeFromResponse).getMetadata();
    verify(chargeFromResponse).getNetAmount();
    verify(chargeFromResponse).getPaymentProvider();
    verify(chargeFromResponse).getReference();
    verify(chargeFromResponse).getRefundSummary();
    verify(chargeFromResponse).getReturnUrl();
    verify(chargeFromResponse).getSettlementSummary();
    verify(chargeFromResponse).getState();
    verify(chargeFromResponse).getTotalAmount();
    verify(chargeFromResponse).getWalletType();
    verify(chargeFromResponse).isMoto();
    verify(createCardPaymentRequest).toConnectorPayload();
    verify(connectorUriGenerator).chargesURI(isA(Account.class));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    PaymentWithAllLinks payment = actualCreateResult.getPayment();
    Optional<String> walletType = payment.getCardDetails().get().getWalletType();
    assertEquals("Apple Pay", walletType.get());
    assertTrue(walletType.isPresent());
    assertSame(authorisationSummary, payment.getAuthorisationSummary());
  }

  /**
   * Test {@link CreatePaymentService#create(Account, CreateCardPaymentRequest, String)}.
   *
   * <ul>
   *   <li>Then return Payment Links Cancel Method is {@code POST}.
   * </ul>
   *
   * <p>Method under test: {@link CreatePaymentService#create(Account, CreateCardPaymentRequest,
   * String)}
   */
  @Test
  @DisplayName(
      "Test create(Account, CreateCardPaymentRequest, String); then return Payment Links Cancel Method is 'POST'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CreatedPaymentWithAllLinks CreatePaymentService.create(Account, CreateCardPaymentRequest, String)"
  })
  void testCreate_thenReturnPaymentLinksCancelMethodIsPost() throws ProcessingException {
    // Arrange
    ChargeFromResponse chargeFromResponse = mock(ChargeFromResponse.class);
    when(chargeFromResponse.getDelayedCapture()).thenReturn(true);
    when(chargeFromResponse.isMoto()).thenReturn(true);
    when(chargeFromResponse.getAmount()).thenReturn(10L);
    when(chargeFromResponse.getCorporateCardSurcharge()).thenReturn(1L);
    when(chargeFromResponse.getFee()).thenReturn(1L);
    when(chargeFromResponse.getNetAmount()).thenReturn(1L);
    when(chargeFromResponse.getTotalAmount()).thenReturn(1L);
    when(chargeFromResponse.getAgreementId()).thenReturn("42");
    when(chargeFromResponse.getChargeId()).thenReturn("42");
    when(chargeFromResponse.getCreatedDate()).thenReturn("2020-03-01");
    when(chargeFromResponse.getDescription())
        .thenReturn("The characteristics of someone or something");
    when(chargeFromResponse.getEmail()).thenReturn("jane.doe@example.org");
    when(chargeFromResponse.getGatewayTransactionId()).thenReturn("42");
    when(chargeFromResponse.getPaymentProvider()).thenReturn("Payment Provider");
    when(chargeFromResponse.getReference()).thenReturn("Reference");
    when(chargeFromResponse.getReturnUrl()).thenReturn("https://example.org/example");
    when(chargeFromResponse.getLinks()).thenReturn(new ArrayList<>());
    Optional<ExternalMetadata> ofResult = Optional.of(new ExternalMetadata(new HashMap<>()));
    when(chargeFromResponse.getMetadata()).thenReturn(ofResult);
    Optional<Wallet> ofResult2 = Optional.of(Wallet.APPLE_PAY);
    when(chargeFromResponse.getWalletType()).thenReturn(ofResult2);
    when(chargeFromResponse.getAuthorisationSummary()).thenReturn(new AuthorisationSummary());
    Address billingAddress = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");
    CardDetailsFromResponse cardDetailsFromResponse =
        new CardDetailsFromResponse(
            "42",
            "42",
            "Card Holder Name",
            "2020-03-01",
            billingAddress,
            "Card Brand",
            "Card Type");
    when(chargeFromResponse.getCardDetailsFromResponse()).thenReturn(cardDetailsFromResponse);
    when(chargeFromResponse.getExemption()).thenReturn(new Exemption());
    PaymentSettlementSummary paymentSettlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    when(chargeFromResponse.getSettlementSummary()).thenReturn(paymentSettlementSummary);
    PaymentState paymentState = new PaymentState("Status", false);
    when(chargeFromResponse.getState()).thenReturn(paymentState);
    when(chargeFromResponse.getRefundSummary()).thenReturn(new RefundSummary("Status", 10L, 10L));
    when(chargeFromResponse.getAgreementPaymentType()).thenReturn(AgreementPaymentType.INSTALMENT);
    when(chargeFromResponse.getAuthorisationMode()).thenReturn(AuthorisationMode.WEB);
    when(chargeFromResponse.getLanguage()).thenReturn(SupportedLanguage.ENGLISH);

    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.readEntity(ChargeFromResponse.class)).thenReturn(chargeFromResponse);
    when(outboundJaxrsResponse.getStatus()).thenReturn(201);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());

    Builder builder = mock(Builder.class);
    when(builder.post(Mockito.<Entity<?>>any())).thenReturn(outboundJaxrsResponse);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(String[].class))).thenReturn(builder);

    Builder builder3 = mock(Builder.class);
    when(builder3.headers(Mockito.<MultivaluedMap<String, Object>>any())).thenReturn(builder2);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder3);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

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

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.chargesURI(Mockito.<Account>any())).thenReturn("Charges URI");

    CreatePaymentService createPaymentService =
        new CreatePaymentService(client, publicApiUriGenerator, connectorUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    CreateCardPaymentRequest createCardPaymentRequest = mock(CreateCardPaymentRequest.class);
    when(createCardPaymentRequest.toConnectorPayload()).thenReturn("Connector Payload");

    // Act
    CreatedPaymentWithAllLinks actualCreateResult =
        createPaymentService.create(account, createCardPaymentRequest, "Idempotency Key");

    // Assert
    verify(client).target("Charges URI");
    verify(builder2).accept(isA(String[].class));
    verify(builder3).headers(isA(MultivaluedMap.class));
    verify(builder).post(isA(Entity.class));
    verify(webTarget).request();
    verify(outboundJaxrsResponse).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(chargeFromResponse).getAgreementId();
    verify(chargeFromResponse).getAgreementPaymentType();
    verify(chargeFromResponse).getAmount();
    verify(chargeFromResponse).getAuthorisationMode();
    verify(chargeFromResponse).getAuthorisationSummary();
    verify(chargeFromResponse, atLeast(1)).getCardDetailsFromResponse();
    verify(chargeFromResponse).getChargeId();
    verify(chargeFromResponse).getCorporateCardSurcharge();
    verify(chargeFromResponse).getCreatedDate();
    verify(chargeFromResponse).getDelayedCapture();
    verify(chargeFromResponse).getDescription();
    verify(chargeFromResponse).getEmail();
    verify(chargeFromResponse).getExemption();
    verify(chargeFromResponse).getFee();
    verify(chargeFromResponse).getGatewayTransactionId();
    verify(chargeFromResponse).getLanguage();
    verify(chargeFromResponse).getLinks();
    verify(chargeFromResponse).getMetadata();
    verify(chargeFromResponse).getNetAmount();
    verify(chargeFromResponse).getPaymentProvider();
    verify(chargeFromResponse).getReference();
    verify(chargeFromResponse).getRefundSummary();
    verify(chargeFromResponse).getReturnUrl();
    verify(chargeFromResponse).getSettlementSummary();
    verify(chargeFromResponse).getState();
    verify(chargeFromResponse).getTotalAmount();
    verify(chargeFromResponse).getWalletType();
    verify(chargeFromResponse).isMoto();
    verify(createCardPaymentRequest).toConnectorPayload();
    verify(connectorUriGenerator).chargesURI(isA(Account.class));
    verify(publicApiUriGenerator).getPaymentAuthorisationURI();
    verify(publicApiUriGenerator).getPaymentCancelURI("42");
    verify(publicApiUriGenerator).getPaymentCaptureURI("42");
    verify(publicApiUriGenerator).getPaymentEventsURI("42");
    verify(publicApiUriGenerator).getPaymentRefundsURI("42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    PaymentWithAllLinks payment = actualCreateResult.getPayment();
    PostLink cancel = payment.getLinks().getCancel();
    assertEquals("POST", cancel.getMethod());
    assertNull(cancel.getType());
    assertNull(cancel.getParams());
    PaymentState state = payment.getState();
    assertFalse(state.isFinished());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        cancel.getHref());
    assertSame(paymentState, state);
  }
}
