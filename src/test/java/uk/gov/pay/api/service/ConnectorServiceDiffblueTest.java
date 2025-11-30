package uk.gov.pay.api.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.Invocation.Builder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import org.glassfish.jersey.message.internal.OutboundJaxrsResponse;
import org.glassfish.jersey.message.internal.OutboundMessageContext;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import uk.gov.pay.api.agreement.model.AgreementCreatedResponse;
import uk.gov.pay.api.agreement.model.CreateAgreementRequest;
import uk.gov.pay.api.auth.Account;
import uk.gov.pay.api.exception.CancelAgreementException;
import uk.gov.pay.api.exception.ConnectorResponseErrorException;
import uk.gov.pay.api.exception.ConnectorResponseErrorException.ConnectorErrorResponse;
import uk.gov.pay.api.exception.CreateAgreementException;
import uk.gov.pay.api.exception.GetChargeException;
import uk.gov.pay.api.exception.GetEventsException;
import uk.gov.pay.api.exception.GetRefundException;
import uk.gov.pay.api.model.Address;
import uk.gov.pay.api.model.AuthorisationSummary;
import uk.gov.pay.api.model.CardDetailsFromResponse;
import uk.gov.pay.api.model.Charge;
import uk.gov.pay.api.model.ChargeFromResponse;
import uk.gov.pay.api.model.Exemption;
import uk.gov.pay.api.model.ExemptionOutcome;
import uk.gov.pay.api.model.PaymentEvents;
import uk.gov.pay.api.model.PaymentSettlementSummary;
import uk.gov.pay.api.model.PaymentState;
import uk.gov.pay.api.model.RefundFromConnector;
import uk.gov.pay.api.model.RefundSummary;
import uk.gov.pay.api.model.ThreeDSecure;
import uk.gov.pay.api.model.TokenPaymentType;
import uk.gov.pay.api.model.Wallet;
import uk.gov.service.payments.commons.model.AgreementPaymentType;
import uk.gov.service.payments.commons.model.AuthorisationMode;
import uk.gov.service.payments.commons.model.SupportedLanguage;
import uk.gov.service.payments.commons.model.charge.ExternalMetadata;

public class ConnectorServiceDiffblueTest {
  /**
   * Test {@link ConnectorService#getCharge(Account, String)}.
   *
   * <p>Method under test: {@link ConnectorService#getCharge(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Charge ConnectorService.getCharge(Account, String)"})
  public void testGetCharge() throws ProcessingException {
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
    PaymentState paymentState = new PaymentState("Status", true);
    when(chargeFromResponse.getState()).thenReturn(paymentState);
    RefundSummary refundSummary = new RefundSummary("Status", 10L, 10L);
    when(chargeFromResponse.getRefundSummary()).thenReturn(refundSummary);
    when(chargeFromResponse.getAgreementPaymentType()).thenReturn(AgreementPaymentType.INSTALMENT);
    when(chargeFromResponse.getAuthorisationMode()).thenReturn(AuthorisationMode.WEB);
    when(chargeFromResponse.getLanguage()).thenReturn(SupportedLanguage.ENGLISH);

    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.readEntity(ChargeFromResponse.class)).thenReturn(chargeFromResponse);
    when(outboundJaxrsResponse.getStatus()).thenReturn(200);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());

    Builder builder = mock(Builder.class);
    when(builder.get()).thenReturn(outboundJaxrsResponse);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.chargeURI(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn("Charge URI");

    ConnectorService connectorService = new ConnectorService(client, connectorUriGenerator);

    // Act
    Charge actualCharge =
        connectorService.getCharge(new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(client).target("Charge URI");
    verify(builder).get();
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
    verify(connectorUriGenerator).chargeURI(isA(Account.class), eq("42"));
    assertEquals("2020-03-01", actualCharge.getCreatedDate());
    assertEquals("42", actualCharge.getAgreementId());
    assertEquals("42", actualCharge.getChargeId());
    assertEquals("42", actualCharge.getGatewayTransactionId());
    assertEquals("Card Brand", actualCharge.getCardBrand());
    assertEquals("Payment Provider", actualCharge.getPaymentProvider());
    assertEquals("Reference", actualCharge.getReference());
    assertEquals("The characteristics of someone or something", actualCharge.getDescription());
    assertEquals("https://example.org/example", actualCharge.getReturnUrl());
    assertEquals("jane.doe@example.org", actualCharge.getEmail());
    assertNull(actualCharge.getExemption());
    assertEquals(10L, actualCharge.getAmount().longValue());
    assertEquals(1L, actualCharge.getCorporateCardSurcharge().longValue());
    assertEquals(1L, actualCharge.getFee().longValue());
    assertEquals(1L, actualCharge.getNetAmount().longValue());
    assertEquals(1L, actualCharge.getTotalAmount().longValue());
    assertEquals(AgreementPaymentType.INSTALMENT, actualCharge.getAgreementPaymentType());
    assertEquals(AuthorisationMode.WEB, actualCharge.getAuthorisationMode());
    assertEquals(SupportedLanguage.ENGLISH, actualCharge.getLanguage());
    assertTrue(actualCharge.getDelayedCapture());
    assertTrue(actualCharge.isMoto());
    assertEquals(ofResult, actualCharge.getMetadata());
    assertSame(authorisationSummary, actualCharge.getAuthorisationSummary());
    assertSame(paymentSettlementSummary, actualCharge.getSettlementSummary());
    assertSame(paymentState, actualCharge.getState());
    assertSame(refundSummary, actualCharge.getRefundSummary());
  }

  /**
   * Test {@link ConnectorService#getCharge(Account, String)}.
   *
   * <p>Method under test: {@link ConnectorService#getCharge(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Charge ConnectorService.getCharge(Account, String)"})
  public void testGetCharge2() throws ProcessingException {
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
    Exemption exemption = new Exemption(true, "Type", new ExemptionOutcome("Result"));
    when(chargeFromResponse.getExemption()).thenReturn(exemption);
    PaymentSettlementSummary paymentSettlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    when(chargeFromResponse.getSettlementSummary()).thenReturn(paymentSettlementSummary);
    PaymentState paymentState = new PaymentState("Status", true);
    when(chargeFromResponse.getState()).thenReturn(paymentState);
    RefundSummary refundSummary = new RefundSummary("Status", 10L, 10L);
    when(chargeFromResponse.getRefundSummary()).thenReturn(refundSummary);
    when(chargeFromResponse.getAgreementPaymentType()).thenReturn(AgreementPaymentType.INSTALMENT);
    when(chargeFromResponse.getAuthorisationMode()).thenReturn(AuthorisationMode.WEB);
    when(chargeFromResponse.getLanguage()).thenReturn(SupportedLanguage.ENGLISH);

    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.readEntity(ChargeFromResponse.class)).thenReturn(chargeFromResponse);
    when(outboundJaxrsResponse.getStatus()).thenReturn(200);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());

    Builder builder = mock(Builder.class);
    when(builder.get()).thenReturn(outboundJaxrsResponse);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.chargeURI(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn("Charge URI");

    ConnectorService connectorService = new ConnectorService(client, connectorUriGenerator);

    // Act
    Charge actualCharge =
        connectorService.getCharge(new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(client).target("Charge URI");
    verify(builder).get();
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
    verify(connectorUriGenerator).chargeURI(isA(Account.class), eq("42"));
    assertEquals("2020-03-01", actualCharge.getCreatedDate());
    assertEquals("42", actualCharge.getAgreementId());
    assertEquals("42", actualCharge.getChargeId());
    assertEquals("42", actualCharge.getGatewayTransactionId());
    assertEquals("Card Brand", actualCharge.getCardBrand());
    assertEquals("Payment Provider", actualCharge.getPaymentProvider());
    assertEquals("Reference", actualCharge.getReference());
    assertEquals("The characteristics of someone or something", actualCharge.getDescription());
    assertEquals("https://example.org/example", actualCharge.getReturnUrl());
    assertEquals("jane.doe@example.org", actualCharge.getEmail());
    assertNull(actualCharge.getAuthorisationSummary());
    assertNull(actualCharge.getExemption());
    assertEquals(10L, actualCharge.getAmount().longValue());
    assertEquals(1L, actualCharge.getCorporateCardSurcharge().longValue());
    assertEquals(1L, actualCharge.getFee().longValue());
    assertEquals(1L, actualCharge.getNetAmount().longValue());
    assertEquals(1L, actualCharge.getTotalAmount().longValue());
    assertEquals(AgreementPaymentType.INSTALMENT, actualCharge.getAgreementPaymentType());
    assertEquals(AuthorisationMode.WEB, actualCharge.getAuthorisationMode());
    assertEquals(SupportedLanguage.ENGLISH, actualCharge.getLanguage());
    assertTrue(actualCharge.getDelayedCapture());
    assertTrue(actualCharge.isMoto());
    assertEquals(ofResult, actualCharge.getMetadata());
    assertSame(paymentSettlementSummary, actualCharge.getSettlementSummary());
    assertSame(paymentState, actualCharge.getState());
    assertSame(refundSummary, actualCharge.getRefundSummary());
  }

  /**
   * Test {@link ConnectorService#getCharge(Account, String)}.
   *
   * <p>Method under test: {@link ConnectorService#getCharge(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Charge ConnectorService.getCharge(Account, String)"})
  public void testGetCharge3() throws ProcessingException {
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
    Exemption exemption = new Exemption(true, "corporate", new ExemptionOutcome("Result"));
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
    when(outboundJaxrsResponse.getStatus()).thenReturn(200);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());

    Builder builder = mock(Builder.class);
    when(builder.get()).thenReturn(outboundJaxrsResponse);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.chargeURI(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn("Charge URI");

    ConnectorService connectorService = new ConnectorService(client, connectorUriGenerator);

    // Act
    Charge actualCharge =
        connectorService.getCharge(new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(client).target("Charge URI");
    verify(builder).get();
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
    verify(connectorUriGenerator).chargeURI(isA(Account.class), eq("42"));
    assertSame(exemption, actualCharge.getExemption());
  }

  /**
   * Test {@link ConnectorService#getCharge(Account, String)}.
   *
   * <p>Method under test: {@link ConnectorService#getCharge(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Charge ConnectorService.getCharge(Account, String)"})
  public void testGetCharge4() throws ProcessingException {
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
    Exemption exemption = new Exemption(true, "corporate", null);
    when(chargeFromResponse.getExemption()).thenReturn(exemption);
    PaymentSettlementSummary paymentSettlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    when(chargeFromResponse.getSettlementSummary()).thenReturn(paymentSettlementSummary);
    PaymentState paymentState = new PaymentState("Status", true);
    when(chargeFromResponse.getState()).thenReturn(paymentState);
    RefundSummary refundSummary = new RefundSummary("Status", 10L, 10L);
    when(chargeFromResponse.getRefundSummary()).thenReturn(refundSummary);
    when(chargeFromResponse.getAgreementPaymentType()).thenReturn(AgreementPaymentType.INSTALMENT);
    when(chargeFromResponse.getAuthorisationMode()).thenReturn(AuthorisationMode.WEB);
    when(chargeFromResponse.getLanguage()).thenReturn(SupportedLanguage.ENGLISH);

    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.readEntity(ChargeFromResponse.class)).thenReturn(chargeFromResponse);
    when(outboundJaxrsResponse.getStatus()).thenReturn(200);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());

    Builder builder = mock(Builder.class);
    when(builder.get()).thenReturn(outboundJaxrsResponse);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.chargeURI(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn("Charge URI");

    ConnectorService connectorService = new ConnectorService(client, connectorUriGenerator);

    // Act
    Charge actualCharge =
        connectorService.getCharge(new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(client).target("Charge URI");
    verify(builder).get();
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
    verify(connectorUriGenerator).chargeURI(isA(Account.class), eq("42"));
    assertEquals("2020-03-01", actualCharge.getCreatedDate());
    assertEquals("42", actualCharge.getAgreementId());
    assertEquals("42", actualCharge.getChargeId());
    assertEquals("42", actualCharge.getGatewayTransactionId());
    assertEquals("Card Brand", actualCharge.getCardBrand());
    assertEquals("Payment Provider", actualCharge.getPaymentProvider());
    assertEquals("Reference", actualCharge.getReference());
    assertEquals("The characteristics of someone or something", actualCharge.getDescription());
    assertEquals("https://example.org/example", actualCharge.getReturnUrl());
    assertEquals("jane.doe@example.org", actualCharge.getEmail());
    assertNull(actualCharge.getAuthorisationSummary());
    assertNull(actualCharge.getExemption());
    assertEquals(10L, actualCharge.getAmount().longValue());
    assertEquals(1L, actualCharge.getCorporateCardSurcharge().longValue());
    assertEquals(1L, actualCharge.getFee().longValue());
    assertEquals(1L, actualCharge.getNetAmount().longValue());
    assertEquals(1L, actualCharge.getTotalAmount().longValue());
    assertEquals(AgreementPaymentType.INSTALMENT, actualCharge.getAgreementPaymentType());
    assertEquals(AuthorisationMode.WEB, actualCharge.getAuthorisationMode());
    assertEquals(SupportedLanguage.ENGLISH, actualCharge.getLanguage());
    assertTrue(actualCharge.getDelayedCapture());
    assertTrue(actualCharge.isMoto());
    assertEquals(ofResult, actualCharge.getMetadata());
    assertSame(paymentSettlementSummary, actualCharge.getSettlementSummary());
    assertSame(paymentState, actualCharge.getState());
    assertSame(refundSummary, actualCharge.getRefundSummary());
  }

  /**
   * Test {@link ConnectorService#getCharge(Account, String)}.
   *
   * <ul>
   *   <li>Given {@link ChargeFromResponse} {@link ChargeFromResponse#getExemption()} return {@link
   *       Exemption#Exemption()}.
   * </ul>
   *
   * <p>Method under test: {@link ConnectorService#getCharge(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Charge ConnectorService.getCharge(Account, String)"})
  public void testGetCharge_givenChargeFromResponseGetExemptionReturnExemption()
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
    PaymentState paymentState = new PaymentState("Status", true);
    when(chargeFromResponse.getState()).thenReturn(paymentState);
    RefundSummary refundSummary = new RefundSummary("Status", 10L, 10L);
    when(chargeFromResponse.getRefundSummary()).thenReturn(refundSummary);
    when(chargeFromResponse.getAgreementPaymentType()).thenReturn(AgreementPaymentType.INSTALMENT);
    when(chargeFromResponse.getAuthorisationMode()).thenReturn(AuthorisationMode.WEB);
    when(chargeFromResponse.getLanguage()).thenReturn(SupportedLanguage.ENGLISH);

    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.readEntity(ChargeFromResponse.class)).thenReturn(chargeFromResponse);
    when(outboundJaxrsResponse.getStatus()).thenReturn(200);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());

    Builder builder = mock(Builder.class);
    when(builder.get()).thenReturn(outboundJaxrsResponse);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.chargeURI(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn("Charge URI");

    ConnectorService connectorService = new ConnectorService(client, connectorUriGenerator);

    // Act
    Charge actualCharge =
        connectorService.getCharge(new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(client).target("Charge URI");
    verify(builder).get();
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
    verify(connectorUriGenerator).chargeURI(isA(Account.class), eq("42"));
    assertEquals("2020-03-01", actualCharge.getCreatedDate());
    assertEquals("42", actualCharge.getAgreementId());
    assertEquals("42", actualCharge.getChargeId());
    assertEquals("42", actualCharge.getGatewayTransactionId());
    assertEquals("Card Brand", actualCharge.getCardBrand());
    assertEquals("Payment Provider", actualCharge.getPaymentProvider());
    assertEquals("Reference", actualCharge.getReference());
    assertEquals("The characteristics of someone or something", actualCharge.getDescription());
    assertEquals("https://example.org/example", actualCharge.getReturnUrl());
    assertEquals("jane.doe@example.org", actualCharge.getEmail());
    assertNull(actualCharge.getAuthorisationSummary());
    assertNull(actualCharge.getExemption());
    assertEquals(10L, actualCharge.getAmount().longValue());
    assertEquals(1L, actualCharge.getCorporateCardSurcharge().longValue());
    assertEquals(1L, actualCharge.getFee().longValue());
    assertEquals(1L, actualCharge.getNetAmount().longValue());
    assertEquals(1L, actualCharge.getTotalAmount().longValue());
    assertEquals(AgreementPaymentType.INSTALMENT, actualCharge.getAgreementPaymentType());
    assertEquals(AuthorisationMode.WEB, actualCharge.getAuthorisationMode());
    assertEquals(SupportedLanguage.ENGLISH, actualCharge.getLanguage());
    assertTrue(actualCharge.getDelayedCapture());
    assertTrue(actualCharge.isMoto());
    assertEquals(ofResult, actualCharge.getMetadata());
    assertSame(paymentSettlementSummary, actualCharge.getSettlementSummary());
    assertSame(paymentState, actualCharge.getState());
    assertSame(refundSummary, actualCharge.getRefundSummary());
  }

  /**
   * Test {@link ConnectorService#getCharge(Account, String)}.
   *
   * <ul>
   *   <li>Given {@link ThreeDSecure#ThreeDSecure(boolean)} with required is {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ConnectorService#getCharge(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Charge ConnectorService.getCharge(Account, String)"})
  public void testGetCharge_givenThreeDSecureWithRequiredIsFalse() throws ProcessingException {
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
    PaymentState paymentState = new PaymentState("Status", true);
    when(chargeFromResponse.getState()).thenReturn(paymentState);
    RefundSummary refundSummary = new RefundSummary("Status", 10L, 10L);
    when(chargeFromResponse.getRefundSummary()).thenReturn(refundSummary);
    when(chargeFromResponse.getAgreementPaymentType()).thenReturn(AgreementPaymentType.INSTALMENT);
    when(chargeFromResponse.getAuthorisationMode()).thenReturn(AuthorisationMode.WEB);
    when(chargeFromResponse.getLanguage()).thenReturn(SupportedLanguage.ENGLISH);

    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.readEntity(ChargeFromResponse.class)).thenReturn(chargeFromResponse);
    when(outboundJaxrsResponse.getStatus()).thenReturn(200);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());

    Builder builder = mock(Builder.class);
    when(builder.get()).thenReturn(outboundJaxrsResponse);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.chargeURI(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn("Charge URI");

    ConnectorService connectorService = new ConnectorService(client, connectorUriGenerator);

    // Act
    Charge actualCharge =
        connectorService.getCharge(new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(client).target("Charge URI");
    verify(builder).get();
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
    verify(connectorUriGenerator).chargeURI(isA(Account.class), eq("42"));
    assertEquals("2020-03-01", actualCharge.getCreatedDate());
    assertEquals("42", actualCharge.getAgreementId());
    assertEquals("42", actualCharge.getChargeId());
    assertEquals("42", actualCharge.getGatewayTransactionId());
    assertEquals("Card Brand", actualCharge.getCardBrand());
    assertEquals("Payment Provider", actualCharge.getPaymentProvider());
    assertEquals("Reference", actualCharge.getReference());
    assertEquals("The characteristics of someone or something", actualCharge.getDescription());
    assertEquals("https://example.org/example", actualCharge.getReturnUrl());
    assertEquals("jane.doe@example.org", actualCharge.getEmail());
    assertNull(actualCharge.getAuthorisationSummary());
    assertNull(actualCharge.getExemption());
    assertEquals(10L, actualCharge.getAmount().longValue());
    assertEquals(1L, actualCharge.getCorporateCardSurcharge().longValue());
    assertEquals(1L, actualCharge.getFee().longValue());
    assertEquals(1L, actualCharge.getNetAmount().longValue());
    assertEquals(1L, actualCharge.getTotalAmount().longValue());
    assertEquals(AgreementPaymentType.INSTALMENT, actualCharge.getAgreementPaymentType());
    assertEquals(AuthorisationMode.WEB, actualCharge.getAuthorisationMode());
    assertEquals(SupportedLanguage.ENGLISH, actualCharge.getLanguage());
    assertTrue(actualCharge.getDelayedCapture());
    assertTrue(actualCharge.isMoto());
    assertEquals(ofResult, actualCharge.getMetadata());
    assertSame(paymentSettlementSummary, actualCharge.getSettlementSummary());
    assertSame(paymentState, actualCharge.getState());
    assertSame(refundSummary, actualCharge.getRefundSummary());
  }

  /**
   * Test {@link ConnectorService#getCharge(Account, String)}.
   *
   * <ul>
   *   <li>Then return CardBrand is empty string.
   * </ul>
   *
   * <p>Method under test: {@link ConnectorService#getCharge(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Charge ConnectorService.getCharge(Account, String)"})
  public void testGetCharge_thenReturnCardBrandIsEmptyString() throws ProcessingException {
    // Arrange
    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.readEntity(ChargeFromResponse.class))
        .thenReturn(new ChargeFromResponse());
    when(outboundJaxrsResponse.getStatus()).thenReturn(200);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());

    Builder builder = mock(Builder.class);
    when(builder.get()).thenReturn(outboundJaxrsResponse);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.chargeURI(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn("Charge URI");

    ConnectorService connectorService = new ConnectorService(client, connectorUriGenerator);

    // Act
    Charge actualCharge =
        connectorService.getCharge(new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(client).target("Charge URI");
    verify(builder).get();
    verify(webTarget).request();
    verify(outboundJaxrsResponse).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(connectorUriGenerator).chargeURI(isA(Account.class), eq("42"));
    assertEquals("", actualCharge.getCardBrand());
    assertNull(actualCharge.getAmount());
    assertNull(actualCharge.getCorporateCardSurcharge());
    assertNull(actualCharge.getFee());
    assertNull(actualCharge.getNetAmount());
    assertNull(actualCharge.getTotalAmount());
    assertNull(actualCharge.getAgreementId());
    assertNull(actualCharge.getChargeId());
    assertNull(actualCharge.getCreatedDate());
    assertNull(actualCharge.getDescription());
    assertNull(actualCharge.getEmail());
    assertNull(actualCharge.getGatewayTransactionId());
    assertNull(actualCharge.getPaymentProvider());
    assertNull(actualCharge.getReference());
    assertNull(actualCharge.getReturnUrl());
    assertNull(actualCharge.getCardDetails());
    assertNull(actualCharge.getSettlementSummary());
    assertNull(actualCharge.getState());
    assertNull(actualCharge.getRefundSummary());
    assertNull(actualCharge.getAgreementPaymentType());
    assertNull(actualCharge.getAuthorisationMode());
    assertNull(actualCharge.getLanguage());
    assertFalse(actualCharge.getMetadata().isPresent());
    assertFalse(actualCharge.getDelayedCapture());
    assertFalse(actualCharge.isMoto());
  }

  /**
   * Test {@link ConnectorService#getCharge(Account, String)}.
   *
   * <ul>
   *   <li>Then throw {@link GetChargeException}.
   * </ul>
   *
   * <p>Method under test: {@link ConnectorService#getCharge(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Charge ConnectorService.getCharge(Account, String)"})
  public void testGetCharge_thenThrowGetChargeException() throws ProcessingException {
    // Arrange
    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.getStatus()).thenReturn(1);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());
    doNothing().when(outboundJaxrsResponse).close();

    Builder builder = mock(Builder.class);
    when(builder.get()).thenReturn(outboundJaxrsResponse);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.chargeURI(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn("Charge URI");

    ConnectorService connectorService = new ConnectorService(client, connectorUriGenerator);

    // Act and Assert
    assertThrows(
        GetChargeException.class,
        () -> connectorService.getCharge(new Account("42", TokenPaymentType.CARD, "ABC123"), "42"));
    verify(client).target("Charge URI");
    verify(builder).get();
    verify(webTarget).request();
    verify(outboundJaxrsResponse).close();
    verify(outboundJaxrsResponse, atLeast(1)).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(connectorUriGenerator).chargeURI(isA(Account.class), eq("42"));
  }

  /**
   * Test {@link ConnectorService#getChargeEvents(Account, String)}.
   *
   * <ul>
   *   <li>Then return {@link PaymentEvents} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link ConnectorService#getChargeEvents(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentEvents ConnectorService.getChargeEvents(Account, String)"})
  public void testGetChargeEvents_thenReturnPaymentEvents() throws ProcessingException {
    // Arrange
    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    PaymentEvents paymentEvents = new PaymentEvents();
    when(outboundJaxrsResponse.readEntity(PaymentEvents.class)).thenReturn(paymentEvents);
    when(outboundJaxrsResponse.getStatus()).thenReturn(200);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());

    Builder builder = mock(Builder.class);
    when(builder.get()).thenReturn(outboundJaxrsResponse);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(String[].class))).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.chargeEventsURI(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn("Charge Events URI");

    ConnectorService connectorService = new ConnectorService(client, connectorUriGenerator);

    // Act
    PaymentEvents actualChargeEvents =
        connectorService.getChargeEvents(new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(client).target("Charge Events URI");
    verify(builder2).accept(isA(String[].class));
    verify(builder).get();
    verify(webTarget).request();
    verify(outboundJaxrsResponse).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(connectorUriGenerator).chargeEventsURI(isA(Account.class), eq("42"));
    assertSame(paymentEvents, actualChargeEvents);
  }

  /**
   * Test {@link ConnectorService#getChargeEvents(Account, String)}.
   *
   * <ul>
   *   <li>Then throw {@link GetChargeException}.
   * </ul>
   *
   * <p>Method under test: {@link ConnectorService#getChargeEvents(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentEvents ConnectorService.getChargeEvents(Account, String)"})
  public void testGetChargeEvents_thenThrowGetChargeException() throws ProcessingException {
    // Arrange
    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.readEntity(PaymentEvents.class))
        .thenThrow(
            new GetChargeException(
                new OutboundJaxrsResponse(Status.OK, new OutboundMessageContext())));
    when(outboundJaxrsResponse.getStatus()).thenReturn(200);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());

    Builder builder = mock(Builder.class);
    when(builder.get()).thenReturn(outboundJaxrsResponse);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(String[].class))).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.chargeEventsURI(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn("Charge Events URI");

    ConnectorService connectorService = new ConnectorService(client, connectorUriGenerator);

    // Act and Assert
    assertThrows(
        GetChargeException.class,
        () ->
            connectorService.getChargeEvents(
                new Account("42", TokenPaymentType.CARD, "ABC123"), "42"));
    verify(client).target("Charge Events URI");
    verify(builder2).accept(isA(String[].class));
    verify(builder).get();
    verify(webTarget).request();
    verify(outboundJaxrsResponse).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(connectorUriGenerator).chargeEventsURI(isA(Account.class), eq("42"));
  }

  /**
   * Test {@link ConnectorService#getChargeEvents(Account, String)}.
   *
   * <ul>
   *   <li>Then throw {@link GetEventsException}.
   * </ul>
   *
   * <p>Method under test: {@link ConnectorService#getChargeEvents(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentEvents ConnectorService.getChargeEvents(Account, String)"})
  public void testGetChargeEvents_thenThrowGetEventsException() throws ProcessingException {
    // Arrange
    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.getStatus()).thenReturn(1);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());
    doNothing().when(outboundJaxrsResponse).close();

    Builder builder = mock(Builder.class);
    when(builder.get()).thenReturn(outboundJaxrsResponse);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(String[].class))).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.chargeEventsURI(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn("Charge Events URI");

    ConnectorService connectorService = new ConnectorService(client, connectorUriGenerator);

    // Act and Assert
    assertThrows(
        GetEventsException.class,
        () ->
            connectorService.getChargeEvents(
                new Account("42", TokenPaymentType.CARD, "ABC123"), "42"));
    verify(client).target("Charge Events URI");
    verify(builder2).accept(isA(String[].class));
    verify(builder).get();
    verify(webTarget).request();
    verify(outboundJaxrsResponse).close();
    verify(outboundJaxrsResponse, atLeast(1)).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(connectorUriGenerator).chargeEventsURI(isA(Account.class), eq("42"));
  }

  /**
   * Test {@link ConnectorService#getPaymentRefund(String, String, String)}.
   *
   * <ul>
   *   <li>Then return {@link RefundFromConnector} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link ConnectorService#getPaymentRefund(String, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RefundFromConnector ConnectorService.getPaymentRefund(String, String, String)"
  })
  public void testGetPaymentRefund_thenReturnRefundFromConnector() throws ProcessingException {
    // Arrange
    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    RefundFromConnector refundFromConnector = new RefundFromConnector();
    when(outboundJaxrsResponse.readEntity(RefundFromConnector.class))
        .thenReturn(refundFromConnector);
    when(outboundJaxrsResponse.getStatus()).thenReturn(200);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());

    Builder builder = mock(Builder.class);
    when(builder.get()).thenReturn(outboundJaxrsResponse);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(String[].class))).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.refundForPaymentURI(
            Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn("Refund For Payment URI");

    ConnectorService connectorService = new ConnectorService(client, connectorUriGenerator);

    // Act
    RefundFromConnector actualPaymentRefund = connectorService.getPaymentRefund("42", "42", "42");

    // Assert
    verify(client).target("Refund For Payment URI");
    verify(builder2).accept(isA(String[].class));
    verify(builder).get();
    verify(webTarget).request();
    verify(outboundJaxrsResponse).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(connectorUriGenerator).refundForPaymentURI("42", "42", "42");
    assertSame(refundFromConnector, actualPaymentRefund);
  }

  /**
   * Test {@link ConnectorService#getPaymentRefund(String, String, String)}.
   *
   * <ul>
   *   <li>Then throw {@link GetChargeException}.
   * </ul>
   *
   * <p>Method under test: {@link ConnectorService#getPaymentRefund(String, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RefundFromConnector ConnectorService.getPaymentRefund(String, String, String)"
  })
  public void testGetPaymentRefund_thenThrowGetChargeException() throws ProcessingException {
    // Arrange
    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.readEntity(RefundFromConnector.class))
        .thenThrow(
            new GetChargeException(
                new OutboundJaxrsResponse(Status.OK, new OutboundMessageContext())));
    when(outboundJaxrsResponse.getStatus()).thenReturn(200);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());

    Builder builder = mock(Builder.class);
    when(builder.get()).thenReturn(outboundJaxrsResponse);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(String[].class))).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.refundForPaymentURI(
            Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn("Refund For Payment URI");

    ConnectorService connectorService = new ConnectorService(client, connectorUriGenerator);

    // Act and Assert
    assertThrows(
        GetChargeException.class, () -> connectorService.getPaymentRefund("42", "42", "42"));
    verify(client).target("Refund For Payment URI");
    verify(builder2).accept(isA(String[].class));
    verify(builder).get();
    verify(webTarget).request();
    verify(outboundJaxrsResponse).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(connectorUriGenerator).refundForPaymentURI("42", "42", "42");
  }

  /**
   * Test {@link ConnectorService#getPaymentRefund(String, String, String)}.
   *
   * <ul>
   *   <li>Then throw {@link GetRefundException}.
   * </ul>
   *
   * <p>Method under test: {@link ConnectorService#getPaymentRefund(String, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RefundFromConnector ConnectorService.getPaymentRefund(String, String, String)"
  })
  public void testGetPaymentRefund_thenThrowGetRefundException() throws ProcessingException {
    // Arrange
    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.getStatus()).thenReturn(1);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());
    doNothing().when(outboundJaxrsResponse).close();

    Builder builder = mock(Builder.class);
    when(builder.get()).thenReturn(outboundJaxrsResponse);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(String[].class))).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.refundForPaymentURI(
            Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn("Refund For Payment URI");

    ConnectorService connectorService = new ConnectorService(client, connectorUriGenerator);

    // Act and Assert
    assertThrows(
        GetRefundException.class, () -> connectorService.getPaymentRefund("42", "42", "42"));
    verify(client).target("Refund For Payment URI");
    verify(builder2).accept(isA(String[].class));
    verify(builder).get();
    verify(webTarget).request();
    verify(outboundJaxrsResponse).close();
    verify(outboundJaxrsResponse, atLeast(1)).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(connectorUriGenerator).refundForPaymentURI("42", "42", "42");
  }

  /**
   * Test {@link ConnectorService#createAgreement(Account, CreateAgreementRequest)}.
   *
   * <ul>
   *   <li>Then return AgreementId is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link ConnectorService#createAgreement(Account, CreateAgreementRequest)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AgreementCreatedResponse ConnectorService.createAgreement(Account, CreateAgreementRequest)"
  })
  public void testCreateAgreement_thenReturnAgreementIdIs42() throws ProcessingException {
    // Arrange
    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    AgreementCreatedResponse agreementCreatedResponse = new AgreementCreatedResponse("42");
    when(outboundJaxrsResponse.readEntity(AgreementCreatedResponse.class))
        .thenReturn(agreementCreatedResponse);
    when(outboundJaxrsResponse.getStatus()).thenReturn(201);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());

    Builder builder = mock(Builder.class);
    when(builder.post(Mockito.<Entity<?>>any())).thenReturn(outboundJaxrsResponse);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(String[].class))).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.getAgreementURI(Mockito.<Account>any())).thenReturn("Agreement URI");

    ConnectorService connectorService = new ConnectorService(client, connectorUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    CreateAgreementRequest createAgreementRequest = mock(CreateAgreementRequest.class);
    when(createAgreementRequest.toConnectorPayload()).thenReturn("Connector Payload");

    // Act
    AgreementCreatedResponse actualCreateAgreementResult =
        connectorService.createAgreement(account, createAgreementRequest);

    // Assert
    verify(client).target("Agreement URI");
    verify(builder2).accept(isA(String[].class));
    verify(builder).post(isA(Entity.class));
    verify(webTarget).request();
    verify(outboundJaxrsResponse).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(createAgreementRequest).toConnectorPayload();
    verify(connectorUriGenerator).getAgreementURI(isA(Account.class));
    assertEquals("42", actualCreateAgreementResult.getAgreementId());
    assertSame(agreementCreatedResponse, actualCreateAgreementResult);
  }

  /**
   * Test {@link ConnectorService#createAgreement(Account, CreateAgreementRequest)}.
   *
   * <ul>
   *   <li>Then throw {@link CreateAgreementException}.
   * </ul>
   *
   * <p>Method under test: {@link ConnectorService#createAgreement(Account, CreateAgreementRequest)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AgreementCreatedResponse ConnectorService.createAgreement(Account, CreateAgreementRequest)"
  })
  public void testCreateAgreement_thenThrowCreateAgreementException() throws ProcessingException {
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

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.getAgreementURI(Mockito.<Account>any())).thenReturn("Agreement URI");

    ConnectorService connectorService = new ConnectorService(client, connectorUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    // Act and Assert
    assertThrows(
        CreateAgreementException.class,
        () -> connectorService.createAgreement(account, new CreateAgreementRequest()));
    verify(client).target("Agreement URI");
    verify(builder2).accept(isA(String[].class));
    verify(builder).post(isA(Entity.class));
    verify(webTarget).request();
    verify(outboundJaxrsResponse).close();
    verify(outboundJaxrsResponse, atLeast(1)).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(connectorUriGenerator).getAgreementURI(isA(Account.class));
  }

  /**
   * Test {@link ConnectorService#createAgreement(Account, CreateAgreementRequest)}.
   *
   * <ul>
   *   <li>Then throw {@link CreateAgreementException}.
   * </ul>
   *
   * <p>Method under test: {@link ConnectorService#createAgreement(Account, CreateAgreementRequest)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AgreementCreatedResponse ConnectorService.createAgreement(Account, CreateAgreementRequest)"
  })
  public void testCreateAgreement_thenThrowCreateAgreementException2() throws ProcessingException {
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

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.getAgreementURI(Mockito.<Account>any())).thenReturn("Agreement URI");

    ConnectorService connectorService = new ConnectorService(client, connectorUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    CreateAgreementRequest createAgreementRequest = mock(CreateAgreementRequest.class);
    when(createAgreementRequest.toConnectorPayload()).thenReturn("Connector Payload");

    // Act and Assert
    assertThrows(
        CreateAgreementException.class,
        () -> connectorService.createAgreement(account, createAgreementRequest));
    verify(client).target("Agreement URI");
    verify(builder2).accept(isA(String[].class));
    verify(builder).post(isA(Entity.class));
    verify(webTarget).request();
    verify(outboundJaxrsResponse).close();
    verify(outboundJaxrsResponse, atLeast(1)).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(createAgreementRequest).toConnectorPayload();
    verify(connectorUriGenerator).getAgreementURI(isA(Account.class));
  }

  /**
   * Test {@link ConnectorService#createAgreement(Account, CreateAgreementRequest)}.
   *
   * <ul>
   *   <li>Then throw {@link GetChargeException}.
   * </ul>
   *
   * <p>Method under test: {@link ConnectorService#createAgreement(Account, CreateAgreementRequest)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AgreementCreatedResponse ConnectorService.createAgreement(Account, CreateAgreementRequest)"
  })
  public void testCreateAgreement_thenThrowGetChargeException() throws ProcessingException {
    // Arrange
    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.readEntity(AgreementCreatedResponse.class))
        .thenThrow(
            new GetChargeException(
                new OutboundJaxrsResponse(Status.OK, new OutboundMessageContext())));
    when(outboundJaxrsResponse.getStatus()).thenReturn(201);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());

    Builder builder = mock(Builder.class);
    when(builder.post(Mockito.<Entity<?>>any())).thenReturn(outboundJaxrsResponse);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(String[].class))).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.getAgreementURI(Mockito.<Account>any())).thenReturn("Agreement URI");

    ConnectorService connectorService = new ConnectorService(client, connectorUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    CreateAgreementRequest createAgreementRequest = mock(CreateAgreementRequest.class);
    when(createAgreementRequest.toConnectorPayload()).thenReturn("Connector Payload");

    // Act and Assert
    assertThrows(
        GetChargeException.class,
        () -> connectorService.createAgreement(account, createAgreementRequest));
    verify(client).target("Agreement URI");
    verify(builder2).accept(isA(String[].class));
    verify(builder).post(isA(Entity.class));
    verify(webTarget).request();
    verify(outboundJaxrsResponse).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(createAgreementRequest).toConnectorPayload();
    verify(connectorUriGenerator).getAgreementURI(isA(Account.class));
  }

  /**
   * Test {@link ConnectorService#cancelAgreement(Account, String)}.
   *
   * <ul>
   *   <li>Given {@link OutboundJaxrsResponse} {@link OutboundJaxrsResponse#getStatus()} return two
   *       hundred four.
   * </ul>
   *
   * <p>Method under test: {@link ConnectorService#cancelAgreement(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ConnectorService.cancelAgreement(Account, String)"})
  public void testCancelAgreement_givenOutboundJaxrsResponseGetStatusReturnTwoHundredFour()
      throws ProcessingException {
    // Arrange
    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.getStatus()).thenReturn(204);
    doNothing().when(outboundJaxrsResponse).close();

    Builder builder = mock(Builder.class);
    when(builder.post(Mockito.<Entity<?>>any())).thenReturn(outboundJaxrsResponse);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.cancelAgreementURI(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn("Cancel Agreement URI");

    ConnectorService connectorService = new ConnectorService(client, connectorUriGenerator);

    // Act
    connectorService.cancelAgreement(new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(client).target("Cancel Agreement URI");
    verify(builder).post(isNull());
    verify(webTarget).request();
    verify(outboundJaxrsResponse).close();
    verify(outboundJaxrsResponse).getStatus();
    verify(connectorUriGenerator).cancelAgreementURI(isA(Account.class), eq("42"));
  }

  /**
   * Test {@link ConnectorService#cancelAgreement(Account, String)}.
   *
   * <ul>
   *   <li>Then throw {@link CancelAgreementException}.
   * </ul>
   *
   * <p>Method under test: {@link ConnectorService#cancelAgreement(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ConnectorService.cancelAgreement(Account, String)"})
  public void testCancelAgreement_thenThrowCancelAgreementException() throws ProcessingException {
    // Arrange
    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.getStatus()).thenReturn(1);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());
    doNothing().when(outboundJaxrsResponse).close();

    Builder builder = mock(Builder.class);
    when(builder.post(Mockito.<Entity<?>>any())).thenReturn(outboundJaxrsResponse);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.cancelAgreementURI(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn("Cancel Agreement URI");

    ConnectorService connectorService = new ConnectorService(client, connectorUriGenerator);

    // Act and Assert
    assertThrows(
        CancelAgreementException.class,
        () ->
            connectorService.cancelAgreement(
                new Account("42", TokenPaymentType.CARD, "ABC123"), "42"));
    verify(client).target("Cancel Agreement URI");
    verify(builder).post(isNull());
    verify(webTarget).request();
    verify(outboundJaxrsResponse).close();
    verify(outboundJaxrsResponse, atLeast(1)).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(connectorUriGenerator).cancelAgreementURI(isA(Account.class), eq("42"));
  }
}
