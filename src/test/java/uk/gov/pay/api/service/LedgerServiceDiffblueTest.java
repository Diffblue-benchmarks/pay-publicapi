package uk.gov.pay.api.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
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
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.Invocation.Builder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.glassfish.jersey.message.internal.OutboundJaxrsResponse;
import org.glassfish.jersey.message.internal.OutboundMessageContext;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import uk.gov.pay.api.agreement.model.AgreementLedgerResponse;
import uk.gov.pay.api.auth.Account;
import uk.gov.pay.api.exception.ConnectorResponseErrorException;
import uk.gov.pay.api.exception.ConnectorResponseErrorException.ConnectorErrorResponse;
import uk.gov.pay.api.exception.GetAgreementException;
import uk.gov.pay.api.exception.GetChargeException;
import uk.gov.pay.api.exception.GetEventsException;
import uk.gov.pay.api.exception.GetRefundsException;
import uk.gov.pay.api.exception.GetTransactionException;
import uk.gov.pay.api.exception.SearchAgreementsException;
import uk.gov.pay.api.exception.SearchDisputesException;
import uk.gov.pay.api.exception.SearchPaymentsException;
import uk.gov.pay.api.exception.SearchRefundsException;
import uk.gov.pay.api.ledger.model.AgreementSearchParams;
import uk.gov.pay.api.ledger.model.SearchResults;
import uk.gov.pay.api.ledger.service.LedgerUriGenerator;
import uk.gov.pay.api.model.Address;
import uk.gov.pay.api.model.AuthorisationSummary;
import uk.gov.pay.api.model.CardDetailsFromResponse;
import uk.gov.pay.api.model.Charge;
import uk.gov.pay.api.model.Exemption;
import uk.gov.pay.api.model.ExemptionOutcome;
import uk.gov.pay.api.model.PaymentSettlementSummary;
import uk.gov.pay.api.model.PaymentState;
import uk.gov.pay.api.model.RefundSummary;
import uk.gov.pay.api.model.ThreeDSecure;
import uk.gov.pay.api.model.TokenPaymentType;
import uk.gov.pay.api.model.TransactionEvents;
import uk.gov.pay.api.model.TransactionResponse;
import uk.gov.pay.api.model.Wallet;
import uk.gov.pay.api.model.ledger.RefundTransactionFromLedger;
import uk.gov.pay.api.model.ledger.RefundsFromLedger;
import uk.gov.pay.api.model.ledger.SearchDisputesResponseFromLedger;
import uk.gov.pay.api.model.ledger.SearchRefundsResponseFromLedger;
import uk.gov.pay.api.model.search.card.PaymentSearchResponse;
import uk.gov.service.payments.commons.model.AgreementPaymentType;
import uk.gov.service.payments.commons.model.AuthorisationMode;
import uk.gov.service.payments.commons.model.SupportedLanguage;
import uk.gov.service.payments.commons.model.charge.ExternalMetadata;

public class LedgerServiceDiffblueTest {
  /**
   * Test {@link LedgerService#getPaymentTransaction(Account, String)}.
   *
   * <p>Method under test: {@link LedgerService#getPaymentTransaction(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Charge LedgerService.getPaymentTransaction(Account, String)"})
  public void testGetPaymentTransaction() {
    // Arrange
    Builder builder = mock(Builder.class);
    when(builder.get())
        .thenReturn(new OutboundJaxrsResponse(Status.CREATED, new OutboundMessageContext()));

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.transactionURI(
            Mockito.<Account>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn("Transaction URI");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);

    // Act and Assert
    assertThrows(
        GetChargeException.class,
        () ->
            ledgerService.getPaymentTransaction(
                new Account("42", TokenPaymentType.CARD, "ABC123"), "42"));
    verify(client).target("Transaction URI");
    verify(builder).get();
    verify(webTarget).request();
    verify(ledgerUriGenerator).transactionURI(isA(Account.class), eq("42"), eq("PAYMENT"));
  }

  /**
   * Test {@link LedgerService#getPaymentTransaction(Account, String)}.
   *
   * <p>Method under test: {@link LedgerService#getPaymentTransaction(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Charge LedgerService.getPaymentTransaction(Account, String)"})
  public void testGetPaymentTransaction2() {
    // Arrange
    Builder builder = mock(Builder.class);
    when(builder.get()).thenThrow(new ProcessingException("An error occurred"));

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.transactionURI(
            Mockito.<Account>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn("Transaction URI");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);

    // Act and Assert
    assertThrows(
        ProcessingException.class,
        () ->
            ledgerService.getPaymentTransaction(
                new Account("42", TokenPaymentType.CARD, "ABC123"), "42"));
    verify(client).target("Transaction URI");
    verify(builder).get();
    verify(webTarget).request();
    verify(ledgerUriGenerator).transactionURI(isA(Account.class), eq("42"), eq("PAYMENT"));
  }

  /**
   * Test {@link LedgerService#getPaymentTransaction(Account, String)}.
   *
   * <p>Method under test: {@link LedgerService#getPaymentTransaction(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Charge LedgerService.getPaymentTransaction(Account, String)"})
  public void testGetPaymentTransaction3() throws ProcessingException {
    // Arrange
    TransactionResponse transactionResponse = mock(TransactionResponse.class);
    when(transactionResponse.getDelayedCapture()).thenReturn(true);
    when(transactionResponse.isMoto()).thenReturn(true);
    when(transactionResponse.getAmount()).thenReturn(10L);
    when(transactionResponse.getCorporateCardSurcharge()).thenReturn(1L);
    when(transactionResponse.getFee()).thenReturn(1L);
    when(transactionResponse.getNetAmount()).thenReturn(1L);
    when(transactionResponse.getTotalAmount()).thenReturn(1L);
    when(transactionResponse.getCreatedDate()).thenReturn("2020-03-01");
    when(transactionResponse.getDescription())
        .thenReturn("The characteristics of someone or something");
    when(transactionResponse.getEmail()).thenReturn("jane.doe@example.org");
    when(transactionResponse.getGatewayTransactionId()).thenReturn("42");
    when(transactionResponse.getPaymentProvider()).thenReturn("Payment Provider");
    when(transactionResponse.getReference()).thenReturn("Reference");
    when(transactionResponse.getReturnUrl()).thenReturn("https://example.org/example");
    when(transactionResponse.getTransactionId()).thenReturn("42");
    when(transactionResponse.getLinks()).thenReturn(new ArrayList<>());
    Optional<ExternalMetadata> ofResult = Optional.of(new ExternalMetadata(new HashMap<>()));
    when(transactionResponse.getMetadata()).thenReturn(ofResult);
    Optional<Wallet> ofResult2 = Optional.of(Wallet.APPLE_PAY);
    when(transactionResponse.getWalletType()).thenReturn(ofResult2);
    AuthorisationSummary authorisationSummary = new AuthorisationSummary(new ThreeDSecure(true));
    when(transactionResponse.getAuthorisationSummary()).thenReturn(authorisationSummary);
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
    when(transactionResponse.getCardDetailsFromResponse()).thenReturn(cardDetailsFromResponse);
    when(transactionResponse.getExemption()).thenReturn(new Exemption());
    PaymentSettlementSummary paymentSettlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    when(transactionResponse.getSettlementSummary()).thenReturn(paymentSettlementSummary);
    PaymentState paymentState = new PaymentState("Status", true);
    when(transactionResponse.getState()).thenReturn(paymentState);
    RefundSummary refundSummary = new RefundSummary("Status", 10L, 10L);
    when(transactionResponse.getRefundSummary()).thenReturn(refundSummary);
    when(transactionResponse.getAgreementPaymentType()).thenReturn(AgreementPaymentType.INSTALMENT);
    when(transactionResponse.getAuthorisationMode()).thenReturn(AuthorisationMode.WEB);
    when(transactionResponse.getLanguage()).thenReturn(SupportedLanguage.ENGLISH);

    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.readEntity(TransactionResponse.class))
        .thenReturn(transactionResponse);
    when(outboundJaxrsResponse.getStatus()).thenReturn(200);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());

    Builder builder = mock(Builder.class);
    when(builder.get()).thenReturn(outboundJaxrsResponse);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.transactionURI(
            Mockito.<Account>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn("Transaction URI");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);

    // Act
    Charge actualPaymentTransaction =
        ledgerService.getPaymentTransaction(
            new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(client).target("Transaction URI");
    verify(builder).get();
    verify(webTarget).request();
    verify(outboundJaxrsResponse).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(ledgerUriGenerator).transactionURI(isA(Account.class), eq("42"), eq("PAYMENT"));
    verify(transactionResponse).getAgreementPaymentType();
    verify(transactionResponse).getAmount();
    verify(transactionResponse).getAuthorisationMode();
    verify(transactionResponse).getAuthorisationSummary();
    verify(transactionResponse, atLeast(1)).getCardDetailsFromResponse();
    verify(transactionResponse).getCorporateCardSurcharge();
    verify(transactionResponse).getCreatedDate();
    verify(transactionResponse).getDelayedCapture();
    verify(transactionResponse).getDescription();
    verify(transactionResponse).getEmail();
    verify(transactionResponse).getExemption();
    verify(transactionResponse).getFee();
    verify(transactionResponse).getGatewayTransactionId();
    verify(transactionResponse).getLanguage();
    verify(transactionResponse).getLinks();
    verify(transactionResponse).getMetadata();
    verify(transactionResponse).getNetAmount();
    verify(transactionResponse).getPaymentProvider();
    verify(transactionResponse).getReference();
    verify(transactionResponse).getRefundSummary();
    verify(transactionResponse).getReturnUrl();
    verify(transactionResponse).getSettlementSummary();
    verify(transactionResponse).getState();
    verify(transactionResponse).getTotalAmount();
    verify(transactionResponse).getTransactionId();
    verify(transactionResponse).getWalletType();
    verify(transactionResponse).isMoto();
    assertEquals("2020-03-01", actualPaymentTransaction.getCreatedDate());
    assertEquals("42", actualPaymentTransaction.getChargeId());
    assertEquals("42", actualPaymentTransaction.getGatewayTransactionId());
    assertEquals("Card Brand", actualPaymentTransaction.getCardBrand());
    assertEquals("Payment Provider", actualPaymentTransaction.getPaymentProvider());
    assertEquals("Reference", actualPaymentTransaction.getReference());
    assertEquals(
        "The characteristics of someone or something", actualPaymentTransaction.getDescription());
    assertEquals("https://example.org/example", actualPaymentTransaction.getReturnUrl());
    assertEquals("jane.doe@example.org", actualPaymentTransaction.getEmail());
    assertEquals(10L, actualPaymentTransaction.getAmount().longValue());
    assertEquals(1L, actualPaymentTransaction.getCorporateCardSurcharge().longValue());
    assertEquals(1L, actualPaymentTransaction.getFee().longValue());
    assertEquals(1L, actualPaymentTransaction.getNetAmount().longValue());
    assertEquals(1L, actualPaymentTransaction.getTotalAmount().longValue());
    assertEquals(
        AgreementPaymentType.INSTALMENT, actualPaymentTransaction.getAgreementPaymentType());
    assertEquals(AuthorisationMode.WEB, actualPaymentTransaction.getAuthorisationMode());
    assertEquals(SupportedLanguage.ENGLISH, actualPaymentTransaction.getLanguage());
    assertTrue(actualPaymentTransaction.getDelayedCapture());
    assertTrue(actualPaymentTransaction.isMoto());
    assertEquals(ofResult, actualPaymentTransaction.getMetadata());
    assertSame(authorisationSummary, actualPaymentTransaction.getAuthorisationSummary());
    assertSame(paymentSettlementSummary, actualPaymentTransaction.getSettlementSummary());
    assertSame(paymentState, actualPaymentTransaction.getState());
    assertSame(refundSummary, actualPaymentTransaction.getRefundSummary());
  }

  /**
   * Test {@link LedgerService#getPaymentTransaction(Account, String)}.
   *
   * <ul>
   *   <li>Given {@link ExemptionOutcome#ExemptionOutcome(String)} with result is {@code PAYMENT}.
   * </ul>
   *
   * <p>Method under test: {@link LedgerService#getPaymentTransaction(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Charge LedgerService.getPaymentTransaction(Account, String)"})
  public void testGetPaymentTransaction_givenExemptionOutcomeWithResultIsPayment()
      throws ProcessingException {
    // Arrange
    TransactionResponse transactionResponse = mock(TransactionResponse.class);
    when(transactionResponse.getDelayedCapture()).thenReturn(true);
    when(transactionResponse.isMoto()).thenReturn(true);
    when(transactionResponse.getAmount()).thenReturn(10L);
    when(transactionResponse.getCorporateCardSurcharge()).thenReturn(1L);
    when(transactionResponse.getFee()).thenReturn(1L);
    when(transactionResponse.getNetAmount()).thenReturn(1L);
    when(transactionResponse.getTotalAmount()).thenReturn(1L);
    when(transactionResponse.getCreatedDate()).thenReturn("2020-03-01");
    when(transactionResponse.getDescription())
        .thenReturn("The characteristics of someone or something");
    when(transactionResponse.getEmail()).thenReturn("jane.doe@example.org");
    when(transactionResponse.getGatewayTransactionId()).thenReturn("42");
    when(transactionResponse.getPaymentProvider()).thenReturn("Payment Provider");
    when(transactionResponse.getReference()).thenReturn("Reference");
    when(transactionResponse.getReturnUrl()).thenReturn("https://example.org/example");
    when(transactionResponse.getTransactionId()).thenReturn("42");
    when(transactionResponse.getLinks()).thenReturn(new ArrayList<>());
    Optional<ExternalMetadata> ofResult = Optional.of(new ExternalMetadata(new HashMap<>()));
    when(transactionResponse.getMetadata()).thenReturn(ofResult);
    Optional<Wallet> ofResult2 = Optional.of(Wallet.APPLE_PAY);
    when(transactionResponse.getWalletType()).thenReturn(ofResult2);
    when(transactionResponse.getAuthorisationSummary()).thenReturn(new AuthorisationSummary());
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
    when(transactionResponse.getCardDetailsFromResponse()).thenReturn(cardDetailsFromResponse);
    Exemption exemption = new Exemption(true, "PAYMENT", new ExemptionOutcome("PAYMENT"));
    when(transactionResponse.getExemption()).thenReturn(exemption);
    PaymentSettlementSummary paymentSettlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    when(transactionResponse.getSettlementSummary()).thenReturn(paymentSettlementSummary);
    PaymentState paymentState = new PaymentState("Status", true);
    when(transactionResponse.getState()).thenReturn(paymentState);
    RefundSummary refundSummary = new RefundSummary("Status", 10L, 10L);
    when(transactionResponse.getRefundSummary()).thenReturn(refundSummary);
    when(transactionResponse.getAgreementPaymentType()).thenReturn(AgreementPaymentType.INSTALMENT);
    when(transactionResponse.getAuthorisationMode()).thenReturn(AuthorisationMode.WEB);
    when(transactionResponse.getLanguage()).thenReturn(SupportedLanguage.ENGLISH);

    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.readEntity(TransactionResponse.class))
        .thenReturn(transactionResponse);
    when(outboundJaxrsResponse.getStatus()).thenReturn(200);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());

    Builder builder = mock(Builder.class);
    when(builder.get()).thenReturn(outboundJaxrsResponse);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.transactionURI(
            Mockito.<Account>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn("Transaction URI");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);

    // Act
    Charge actualPaymentTransaction =
        ledgerService.getPaymentTransaction(
            new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(client).target("Transaction URI");
    verify(builder).get();
    verify(webTarget).request();
    verify(outboundJaxrsResponse).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(ledgerUriGenerator).transactionURI(isA(Account.class), eq("42"), eq("PAYMENT"));
    verify(transactionResponse).getAgreementPaymentType();
    verify(transactionResponse).getAmount();
    verify(transactionResponse).getAuthorisationMode();
    verify(transactionResponse).getAuthorisationSummary();
    verify(transactionResponse, atLeast(1)).getCardDetailsFromResponse();
    verify(transactionResponse).getCorporateCardSurcharge();
    verify(transactionResponse).getCreatedDate();
    verify(transactionResponse).getDelayedCapture();
    verify(transactionResponse).getDescription();
    verify(transactionResponse).getEmail();
    verify(transactionResponse).getExemption();
    verify(transactionResponse).getFee();
    verify(transactionResponse).getGatewayTransactionId();
    verify(transactionResponse).getLanguage();
    verify(transactionResponse).getLinks();
    verify(transactionResponse).getMetadata();
    verify(transactionResponse).getNetAmount();
    verify(transactionResponse).getPaymentProvider();
    verify(transactionResponse).getReference();
    verify(transactionResponse).getRefundSummary();
    verify(transactionResponse).getReturnUrl();
    verify(transactionResponse).getSettlementSummary();
    verify(transactionResponse).getState();
    verify(transactionResponse).getTotalAmount();
    verify(transactionResponse).getTransactionId();
    verify(transactionResponse).getWalletType();
    verify(transactionResponse).isMoto();
    assertEquals("2020-03-01", actualPaymentTransaction.getCreatedDate());
    assertEquals("42", actualPaymentTransaction.getChargeId());
    assertEquals("42", actualPaymentTransaction.getGatewayTransactionId());
    assertEquals("Card Brand", actualPaymentTransaction.getCardBrand());
    assertEquals("Payment Provider", actualPaymentTransaction.getPaymentProvider());
    assertEquals("Reference", actualPaymentTransaction.getReference());
    assertEquals(
        "The characteristics of someone or something", actualPaymentTransaction.getDescription());
    assertEquals("https://example.org/example", actualPaymentTransaction.getReturnUrl());
    assertEquals("jane.doe@example.org", actualPaymentTransaction.getEmail());
    assertNull(actualPaymentTransaction.getAuthorisationSummary());
    assertEquals(10L, actualPaymentTransaction.getAmount().longValue());
    assertEquals(1L, actualPaymentTransaction.getCorporateCardSurcharge().longValue());
    assertEquals(1L, actualPaymentTransaction.getFee().longValue());
    assertEquals(1L, actualPaymentTransaction.getNetAmount().longValue());
    assertEquals(1L, actualPaymentTransaction.getTotalAmount().longValue());
    assertEquals(
        AgreementPaymentType.INSTALMENT, actualPaymentTransaction.getAgreementPaymentType());
    assertEquals(AuthorisationMode.WEB, actualPaymentTransaction.getAuthorisationMode());
    assertEquals(SupportedLanguage.ENGLISH, actualPaymentTransaction.getLanguage());
    assertTrue(actualPaymentTransaction.getDelayedCapture());
    assertTrue(actualPaymentTransaction.isMoto());
    assertEquals(ofResult, actualPaymentTransaction.getMetadata());
    assertSame(paymentSettlementSummary, actualPaymentTransaction.getSettlementSummary());
    assertSame(paymentState, actualPaymentTransaction.getState());
    assertSame(refundSummary, actualPaymentTransaction.getRefundSummary());
  }

  /**
   * Test {@link LedgerService#getPaymentTransaction(Account, String)}.
   *
   * <ul>
   *   <li>Then calls {@link OutboundJaxrsResponse#close()}.
   * </ul>
   *
   * <p>Method under test: {@link LedgerService#getPaymentTransaction(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Charge LedgerService.getPaymentTransaction(Account, String)"})
  public void testGetPaymentTransaction_thenCallsClose() throws ProcessingException {
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

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.transactionURI(
            Mockito.<Account>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn("Transaction URI");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);

    // Act and Assert
    assertThrows(
        GetChargeException.class,
        () ->
            ledgerService.getPaymentTransaction(
                new Account("42", TokenPaymentType.CARD, "ABC123"), "42"));
    verify(client).target("Transaction URI");
    verify(builder).get();
    verify(webTarget).request();
    verify(outboundJaxrsResponse).close();
    verify(outboundJaxrsResponse, atLeast(1)).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(ledgerUriGenerator).transactionURI(isA(Account.class), eq("42"), eq("PAYMENT"));
  }

  /**
   * Test {@link LedgerService#getPaymentTransaction(Account, String)}.
   *
   * <ul>
   *   <li>Then calls {@link AuthorisationSummary#getThreeDSecure()}.
   * </ul>
   *
   * <p>Method under test: {@link LedgerService#getPaymentTransaction(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Charge LedgerService.getPaymentTransaction(Account, String)"})
  public void testGetPaymentTransaction_thenCallsGetThreeDSecure() throws ProcessingException {
    // Arrange
    AuthorisationSummary authorisationSummary = mock(AuthorisationSummary.class);
    when(authorisationSummary.getThreeDSecure())
        .thenThrow(new ProcessingException("An error occurred"));

    TransactionResponse transactionResponse = mock(TransactionResponse.class);
    when(transactionResponse.getDelayedCapture()).thenReturn(true);
    when(transactionResponse.isMoto()).thenReturn(true);
    when(transactionResponse.getAmount()).thenReturn(10L);
    when(transactionResponse.getCorporateCardSurcharge()).thenReturn(1L);
    when(transactionResponse.getFee()).thenReturn(1L);
    when(transactionResponse.getNetAmount()).thenReturn(1L);
    when(transactionResponse.getTotalAmount()).thenReturn(1L);
    when(transactionResponse.getCreatedDate()).thenReturn("2020-03-01");
    when(transactionResponse.getDescription())
        .thenReturn("The characteristics of someone or something");
    when(transactionResponse.getEmail()).thenReturn("jane.doe@example.org");
    when(transactionResponse.getGatewayTransactionId()).thenReturn("42");
    when(transactionResponse.getPaymentProvider()).thenReturn("Payment Provider");
    when(transactionResponse.getReference()).thenReturn("Reference");
    when(transactionResponse.getReturnUrl()).thenReturn("https://example.org/example");
    when(transactionResponse.getTransactionId()).thenReturn("42");
    when(transactionResponse.getLinks()).thenReturn(new ArrayList<>());
    Optional<ExternalMetadata> ofResult = Optional.of(new ExternalMetadata(new HashMap<>()));
    when(transactionResponse.getMetadata()).thenReturn(ofResult);
    Optional<Wallet> ofResult2 = Optional.of(Wallet.APPLE_PAY);
    when(transactionResponse.getWalletType()).thenReturn(ofResult2);
    when(transactionResponse.getAuthorisationSummary()).thenReturn(authorisationSummary);
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
    when(transactionResponse.getCardDetailsFromResponse()).thenReturn(cardDetailsFromResponse);
    PaymentSettlementSummary paymentSettlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    when(transactionResponse.getSettlementSummary()).thenReturn(paymentSettlementSummary);
    when(transactionResponse.getState()).thenReturn(new PaymentState("Status", true));
    when(transactionResponse.getRefundSummary()).thenReturn(new RefundSummary("Status", 10L, 10L));
    when(transactionResponse.getLanguage()).thenReturn(SupportedLanguage.ENGLISH);

    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.readEntity(TransactionResponse.class))
        .thenReturn(transactionResponse);
    when(outboundJaxrsResponse.getStatus()).thenReturn(200);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());

    Builder builder = mock(Builder.class);
    when(builder.get()).thenReturn(outboundJaxrsResponse);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.transactionURI(
            Mockito.<Account>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn("Transaction URI");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);

    // Act and Assert
    assertThrows(
        ProcessingException.class,
        () ->
            ledgerService.getPaymentTransaction(
                new Account("42", TokenPaymentType.CARD, "ABC123"), "42"));
    verify(client).target("Transaction URI");
    verify(builder).get();
    verify(webTarget).request();
    verify(outboundJaxrsResponse).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(ledgerUriGenerator).transactionURI(isA(Account.class), eq("42"), eq("PAYMENT"));
    verify(authorisationSummary).getThreeDSecure();
    verify(transactionResponse).getAmount();
    verify(transactionResponse).getAuthorisationSummary();
    verify(transactionResponse, atLeast(1)).getCardDetailsFromResponse();
    verify(transactionResponse).getCorporateCardSurcharge();
    verify(transactionResponse).getCreatedDate();
    verify(transactionResponse).getDelayedCapture();
    verify(transactionResponse).getDescription();
    verify(transactionResponse).getEmail();
    verify(transactionResponse).getFee();
    verify(transactionResponse).getGatewayTransactionId();
    verify(transactionResponse).getLanguage();
    verify(transactionResponse).getLinks();
    verify(transactionResponse).getMetadata();
    verify(transactionResponse).getNetAmount();
    verify(transactionResponse).getPaymentProvider();
    verify(transactionResponse).getReference();
    verify(transactionResponse).getRefundSummary();
    verify(transactionResponse).getReturnUrl();
    verify(transactionResponse).getSettlementSummary();
    verify(transactionResponse).getState();
    verify(transactionResponse).getTotalAmount();
    verify(transactionResponse).getTransactionId();
    verify(transactionResponse).getWalletType();
    verify(transactionResponse).isMoto();
  }

  /**
   * Test {@link LedgerService#getPaymentTransaction(Account, String)}.
   *
   * <ul>
   *   <li>Then calls {@link Exemption#getType()}.
   * </ul>
   *
   * <p>Method under test: {@link LedgerService#getPaymentTransaction(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Charge LedgerService.getPaymentTransaction(Account, String)"})
  public void testGetPaymentTransaction_thenCallsGetType() throws ProcessingException {
    // Arrange
    Exemption exemption = mock(Exemption.class);
    when(exemption.getType()).thenThrow(new ProcessingException("An error occurred"));

    TransactionResponse transactionResponse = mock(TransactionResponse.class);
    when(transactionResponse.getDelayedCapture()).thenReturn(true);
    when(transactionResponse.isMoto()).thenReturn(true);
    when(transactionResponse.getAmount()).thenReturn(10L);
    when(transactionResponse.getCorporateCardSurcharge()).thenReturn(1L);
    when(transactionResponse.getFee()).thenReturn(1L);
    when(transactionResponse.getNetAmount()).thenReturn(1L);
    when(transactionResponse.getTotalAmount()).thenReturn(1L);
    when(transactionResponse.getCreatedDate()).thenReturn("2020-03-01");
    when(transactionResponse.getDescription())
        .thenReturn("The characteristics of someone or something");
    when(transactionResponse.getEmail()).thenReturn("jane.doe@example.org");
    when(transactionResponse.getGatewayTransactionId()).thenReturn("42");
    when(transactionResponse.getPaymentProvider()).thenReturn("Payment Provider");
    when(transactionResponse.getReference()).thenReturn("Reference");
    when(transactionResponse.getReturnUrl()).thenReturn("https://example.org/example");
    when(transactionResponse.getTransactionId()).thenReturn("42");
    when(transactionResponse.getLinks()).thenReturn(new ArrayList<>());
    Optional<ExternalMetadata> ofResult = Optional.of(new ExternalMetadata(new HashMap<>()));
    when(transactionResponse.getMetadata()).thenReturn(ofResult);
    Optional<Wallet> ofResult2 = Optional.of(Wallet.APPLE_PAY);
    when(transactionResponse.getWalletType()).thenReturn(ofResult2);
    when(transactionResponse.getAuthorisationSummary()).thenReturn(new AuthorisationSummary());
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
    when(transactionResponse.getCardDetailsFromResponse()).thenReturn(cardDetailsFromResponse);
    when(transactionResponse.getExemption()).thenReturn(exemption);
    PaymentSettlementSummary paymentSettlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    when(transactionResponse.getSettlementSummary()).thenReturn(paymentSettlementSummary);
    when(transactionResponse.getState()).thenReturn(new PaymentState("Status", true));
    when(transactionResponse.getRefundSummary()).thenReturn(new RefundSummary("Status", 10L, 10L));
    when(transactionResponse.getAgreementPaymentType()).thenReturn(AgreementPaymentType.INSTALMENT);
    when(transactionResponse.getAuthorisationMode()).thenReturn(AuthorisationMode.WEB);
    when(transactionResponse.getLanguage()).thenReturn(SupportedLanguage.ENGLISH);

    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.readEntity(TransactionResponse.class))
        .thenReturn(transactionResponse);
    when(outboundJaxrsResponse.getStatus()).thenReturn(200);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());

    Builder builder = mock(Builder.class);
    when(builder.get()).thenReturn(outboundJaxrsResponse);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.transactionURI(
            Mockito.<Account>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn("Transaction URI");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);

    // Act and Assert
    assertThrows(
        ProcessingException.class,
        () ->
            ledgerService.getPaymentTransaction(
                new Account("42", TokenPaymentType.CARD, "ABC123"), "42"));
    verify(client).target("Transaction URI");
    verify(builder).get();
    verify(webTarget).request();
    verify(outboundJaxrsResponse).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(ledgerUriGenerator).transactionURI(isA(Account.class), eq("42"), eq("PAYMENT"));
    verify(exemption).getType();
    verify(transactionResponse).getAgreementPaymentType();
    verify(transactionResponse).getAmount();
    verify(transactionResponse).getAuthorisationMode();
    verify(transactionResponse).getAuthorisationSummary();
    verify(transactionResponse, atLeast(1)).getCardDetailsFromResponse();
    verify(transactionResponse).getCorporateCardSurcharge();
    verify(transactionResponse).getCreatedDate();
    verify(transactionResponse).getDelayedCapture();
    verify(transactionResponse).getDescription();
    verify(transactionResponse).getEmail();
    verify(transactionResponse).getExemption();
    verify(transactionResponse).getFee();
    verify(transactionResponse).getGatewayTransactionId();
    verify(transactionResponse).getLanguage();
    verify(transactionResponse).getLinks();
    verify(transactionResponse).getMetadata();
    verify(transactionResponse).getNetAmount();
    verify(transactionResponse).getPaymentProvider();
    verify(transactionResponse).getReference();
    verify(transactionResponse).getRefundSummary();
    verify(transactionResponse).getReturnUrl();
    verify(transactionResponse).getSettlementSummary();
    verify(transactionResponse).getState();
    verify(transactionResponse).getTotalAmount();
    verify(transactionResponse).getTransactionId();
    verify(transactionResponse).getWalletType();
    verify(transactionResponse).isMoto();
  }

  /**
   * Test {@link LedgerService#getPaymentTransaction(Account, String)}.
   *
   * <ul>
   *   <li>Then return AuthorisationSummary is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LedgerService#getPaymentTransaction(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Charge LedgerService.getPaymentTransaction(Account, String)"})
  public void testGetPaymentTransaction_thenReturnAuthorisationSummaryIsNull()
      throws ProcessingException {
    // Arrange
    TransactionResponse transactionResponse = mock(TransactionResponse.class);
    when(transactionResponse.getDelayedCapture()).thenReturn(true);
    when(transactionResponse.isMoto()).thenReturn(true);
    when(transactionResponse.getAmount()).thenReturn(10L);
    when(transactionResponse.getCorporateCardSurcharge()).thenReturn(1L);
    when(transactionResponse.getFee()).thenReturn(1L);
    when(transactionResponse.getNetAmount()).thenReturn(1L);
    when(transactionResponse.getTotalAmount()).thenReturn(1L);
    when(transactionResponse.getCreatedDate()).thenReturn("2020-03-01");
    when(transactionResponse.getDescription())
        .thenReturn("The characteristics of someone or something");
    when(transactionResponse.getEmail()).thenReturn("jane.doe@example.org");
    when(transactionResponse.getGatewayTransactionId()).thenReturn("42");
    when(transactionResponse.getPaymentProvider()).thenReturn("Payment Provider");
    when(transactionResponse.getReference()).thenReturn("Reference");
    when(transactionResponse.getReturnUrl()).thenReturn("https://example.org/example");
    when(transactionResponse.getTransactionId()).thenReturn("42");
    when(transactionResponse.getLinks()).thenReturn(new ArrayList<>());
    Optional<ExternalMetadata> ofResult = Optional.of(new ExternalMetadata(new HashMap<>()));
    when(transactionResponse.getMetadata()).thenReturn(ofResult);
    Optional<Wallet> ofResult2 = Optional.of(Wallet.APPLE_PAY);
    when(transactionResponse.getWalletType()).thenReturn(ofResult2);
    when(transactionResponse.getAuthorisationSummary()).thenReturn(new AuthorisationSummary());
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
    when(transactionResponse.getCardDetailsFromResponse()).thenReturn(cardDetailsFromResponse);
    when(transactionResponse.getExemption()).thenReturn(new Exemption());
    PaymentSettlementSummary paymentSettlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    when(transactionResponse.getSettlementSummary()).thenReturn(paymentSettlementSummary);
    PaymentState paymentState = new PaymentState("Status", true);
    when(transactionResponse.getState()).thenReturn(paymentState);
    RefundSummary refundSummary = new RefundSummary("Status", 10L, 10L);
    when(transactionResponse.getRefundSummary()).thenReturn(refundSummary);
    when(transactionResponse.getAgreementPaymentType()).thenReturn(AgreementPaymentType.INSTALMENT);
    when(transactionResponse.getAuthorisationMode()).thenReturn(AuthorisationMode.WEB);
    when(transactionResponse.getLanguage()).thenReturn(SupportedLanguage.ENGLISH);

    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.readEntity(TransactionResponse.class))
        .thenReturn(transactionResponse);
    when(outboundJaxrsResponse.getStatus()).thenReturn(200);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());

    Builder builder = mock(Builder.class);
    when(builder.get()).thenReturn(outboundJaxrsResponse);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.transactionURI(
            Mockito.<Account>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn("Transaction URI");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);

    // Act
    Charge actualPaymentTransaction =
        ledgerService.getPaymentTransaction(
            new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(client).target("Transaction URI");
    verify(builder).get();
    verify(webTarget).request();
    verify(outboundJaxrsResponse).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(ledgerUriGenerator).transactionURI(isA(Account.class), eq("42"), eq("PAYMENT"));
    verify(transactionResponse).getAgreementPaymentType();
    verify(transactionResponse).getAmount();
    verify(transactionResponse).getAuthorisationMode();
    verify(transactionResponse).getAuthorisationSummary();
    verify(transactionResponse, atLeast(1)).getCardDetailsFromResponse();
    verify(transactionResponse).getCorporateCardSurcharge();
    verify(transactionResponse).getCreatedDate();
    verify(transactionResponse).getDelayedCapture();
    verify(transactionResponse).getDescription();
    verify(transactionResponse).getEmail();
    verify(transactionResponse).getExemption();
    verify(transactionResponse).getFee();
    verify(transactionResponse).getGatewayTransactionId();
    verify(transactionResponse).getLanguage();
    verify(transactionResponse).getLinks();
    verify(transactionResponse).getMetadata();
    verify(transactionResponse).getNetAmount();
    verify(transactionResponse).getPaymentProvider();
    verify(transactionResponse).getReference();
    verify(transactionResponse).getRefundSummary();
    verify(transactionResponse).getReturnUrl();
    verify(transactionResponse).getSettlementSummary();
    verify(transactionResponse).getState();
    verify(transactionResponse).getTotalAmount();
    verify(transactionResponse).getTransactionId();
    verify(transactionResponse).getWalletType();
    verify(transactionResponse).isMoto();
    assertEquals("2020-03-01", actualPaymentTransaction.getCreatedDate());
    assertEquals("42", actualPaymentTransaction.getChargeId());
    assertEquals("42", actualPaymentTransaction.getGatewayTransactionId());
    assertEquals("Card Brand", actualPaymentTransaction.getCardBrand());
    assertEquals("Payment Provider", actualPaymentTransaction.getPaymentProvider());
    assertEquals("Reference", actualPaymentTransaction.getReference());
    assertEquals(
        "The characteristics of someone or something", actualPaymentTransaction.getDescription());
    assertEquals("https://example.org/example", actualPaymentTransaction.getReturnUrl());
    assertEquals("jane.doe@example.org", actualPaymentTransaction.getEmail());
    assertNull(actualPaymentTransaction.getAuthorisationSummary());
    assertEquals(10L, actualPaymentTransaction.getAmount().longValue());
    assertEquals(1L, actualPaymentTransaction.getCorporateCardSurcharge().longValue());
    assertEquals(1L, actualPaymentTransaction.getFee().longValue());
    assertEquals(1L, actualPaymentTransaction.getNetAmount().longValue());
    assertEquals(1L, actualPaymentTransaction.getTotalAmount().longValue());
    assertEquals(
        AgreementPaymentType.INSTALMENT, actualPaymentTransaction.getAgreementPaymentType());
    assertEquals(AuthorisationMode.WEB, actualPaymentTransaction.getAuthorisationMode());
    assertEquals(SupportedLanguage.ENGLISH, actualPaymentTransaction.getLanguage());
    assertTrue(actualPaymentTransaction.getDelayedCapture());
    assertTrue(actualPaymentTransaction.isMoto());
    assertEquals(ofResult, actualPaymentTransaction.getMetadata());
    assertSame(paymentSettlementSummary, actualPaymentTransaction.getSettlementSummary());
    assertSame(paymentState, actualPaymentTransaction.getState());
    assertSame(refundSummary, actualPaymentTransaction.getRefundSummary());
  }

  /**
   * Test {@link LedgerService#getPaymentTransaction(Account, String)}.
   *
   * <ul>
   *   <li>Then return CardBrand is empty string.
   * </ul>
   *
   * <p>Method under test: {@link LedgerService#getPaymentTransaction(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Charge LedgerService.getPaymentTransaction(Account, String)"})
  public void testGetPaymentTransaction_thenReturnCardBrandIsEmptyString()
      throws ProcessingException {
    // Arrange
    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.readEntity(TransactionResponse.class))
        .thenReturn(new TransactionResponse());
    when(outboundJaxrsResponse.getStatus()).thenReturn(200);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());

    Builder builder = mock(Builder.class);
    when(builder.get()).thenReturn(outboundJaxrsResponse);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.transactionURI(
            Mockito.<Account>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn("Transaction URI");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);

    // Act
    Charge actualPaymentTransaction =
        ledgerService.getPaymentTransaction(
            new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(client).target("Transaction URI");
    verify(builder).get();
    verify(webTarget).request();
    verify(outboundJaxrsResponse).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(ledgerUriGenerator).transactionURI(isA(Account.class), eq("42"), eq("PAYMENT"));
    assertEquals("", actualPaymentTransaction.getCardBrand());
    assertNull(actualPaymentTransaction.getAmount());
    assertNull(actualPaymentTransaction.getCorporateCardSurcharge());
    assertNull(actualPaymentTransaction.getFee());
    assertNull(actualPaymentTransaction.getNetAmount());
    assertNull(actualPaymentTransaction.getTotalAmount());
    assertNull(actualPaymentTransaction.getChargeId());
    assertNull(actualPaymentTransaction.getCreatedDate());
    assertNull(actualPaymentTransaction.getDescription());
    assertNull(actualPaymentTransaction.getEmail());
    assertNull(actualPaymentTransaction.getGatewayTransactionId());
    assertNull(actualPaymentTransaction.getPaymentProvider());
    assertNull(actualPaymentTransaction.getReference());
    assertNull(actualPaymentTransaction.getReturnUrl());
    assertNull(actualPaymentTransaction.getCardDetails());
    assertNull(actualPaymentTransaction.getSettlementSummary());
    assertNull(actualPaymentTransaction.getState());
    assertNull(actualPaymentTransaction.getRefundSummary());
    assertNull(actualPaymentTransaction.getAgreementPaymentType());
    assertNull(actualPaymentTransaction.getAuthorisationMode());
    assertNull(actualPaymentTransaction.getLanguage());
    assertFalse(actualPaymentTransaction.getMetadata().isPresent());
    assertFalse(actualPaymentTransaction.getDelayedCapture());
    assertFalse(actualPaymentTransaction.isMoto());
  }

  /**
   * Test {@link LedgerService#getRefundTransaction(Account, String, String)}.
   *
   * <p>Method under test: {@link LedgerService#getRefundTransaction(Account, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RefundTransactionFromLedger LedgerService.getRefundTransaction(Account, String, String)"
  })
  public void testGetRefundTransaction() {
    // Arrange
    Builder builder = mock(Builder.class);
    when(builder.get())
        .thenReturn(new OutboundJaxrsResponse(Status.CREATED, new OutboundMessageContext()));

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.transactionURI(
            Mockito.<Account>any(),
            Mockito.<String>any(),
            Mockito.<String>any(),
            Mockito.<String>any()))
        .thenReturn("Transaction URI");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);

    // Act and Assert
    assertThrows(
        GetTransactionException.class,
        () ->
            ledgerService.getRefundTransaction(
                new Account("42", TokenPaymentType.CARD, "ABC123"), "42", "42"));
    verify(client).target("Transaction URI");
    verify(builder).get();
    verify(webTarget).request();
    verify(ledgerUriGenerator).transactionURI(isA(Account.class), eq("42"), eq("REFUND"), eq("42"));
  }

  /**
   * Test {@link LedgerService#getRefundTransaction(Account, String, String)}.
   *
   * <ul>
   *   <li>Then calls {@link OutboundJaxrsResponse#close()}.
   * </ul>
   *
   * <p>Method under test: {@link LedgerService#getRefundTransaction(Account, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RefundTransactionFromLedger LedgerService.getRefundTransaction(Account, String, String)"
  })
  public void testGetRefundTransaction_thenCallsClose() throws ProcessingException {
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

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.transactionURI(
            Mockito.<Account>any(),
            Mockito.<String>any(),
            Mockito.<String>any(),
            Mockito.<String>any()))
        .thenReturn("Transaction URI");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);

    // Act and Assert
    assertThrows(
        GetTransactionException.class,
        () ->
            ledgerService.getRefundTransaction(
                new Account("42", TokenPaymentType.CARD, "ABC123"), "42", "42"));
    verify(client).target("Transaction URI");
    verify(builder).get();
    verify(webTarget).request();
    verify(outboundJaxrsResponse).close();
    verify(outboundJaxrsResponse, atLeast(1)).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(ledgerUriGenerator).transactionURI(isA(Account.class), eq("42"), eq("REFUND"), eq("42"));
  }

  /**
   * Test {@link LedgerService#getRefundTransaction(Account, String, String)}.
   *
   * <ul>
   *   <li>Then return {@link RefundTransactionFromLedger} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link LedgerService#getRefundTransaction(Account, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RefundTransactionFromLedger LedgerService.getRefundTransaction(Account, String, String)"
  })
  public void testGetRefundTransaction_thenReturnRefundTransactionFromLedger()
      throws ProcessingException {
    // Arrange
    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    RefundTransactionFromLedger refundTransactionFromLedger = new RefundTransactionFromLedger();
    when(outboundJaxrsResponse.readEntity(RefundTransactionFromLedger.class))
        .thenReturn(refundTransactionFromLedger);
    when(outboundJaxrsResponse.getStatus()).thenReturn(200);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());

    Builder builder = mock(Builder.class);
    when(builder.get()).thenReturn(outboundJaxrsResponse);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.transactionURI(
            Mockito.<Account>any(),
            Mockito.<String>any(),
            Mockito.<String>any(),
            Mockito.<String>any()))
        .thenReturn("Transaction URI");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);

    // Act
    RefundTransactionFromLedger actualRefundTransaction =
        ledgerService.getRefundTransaction(
            new Account("42", TokenPaymentType.CARD, "ABC123"), "42", "42");

    // Assert
    verify(client).target("Transaction URI");
    verify(builder).get();
    verify(webTarget).request();
    verify(outboundJaxrsResponse).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(ledgerUriGenerator).transactionURI(isA(Account.class), eq("42"), eq("REFUND"), eq("42"));
    assertSame(refundTransactionFromLedger, actualRefundTransaction);
  }

  /**
   * Test {@link LedgerService#getRefundTransaction(Account, String, String)}.
   *
   * <ul>
   *   <li>Then throw {@link ProcessingException}.
   * </ul>
   *
   * <p>Method under test: {@link LedgerService#getRefundTransaction(Account, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RefundTransactionFromLedger LedgerService.getRefundTransaction(Account, String, String)"
  })
  public void testGetRefundTransaction_thenThrowProcessingException() {
    // Arrange
    Builder builder = mock(Builder.class);
    when(builder.get()).thenThrow(new ProcessingException("An error occurred"));

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.transactionURI(
            Mockito.<Account>any(),
            Mockito.<String>any(),
            Mockito.<String>any(),
            Mockito.<String>any()))
        .thenReturn("Transaction URI");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);

    // Act and Assert
    assertThrows(
        ProcessingException.class,
        () ->
            ledgerService.getRefundTransaction(
                new Account("42", TokenPaymentType.CARD, "ABC123"), "42", "42"));
    verify(client).target("Transaction URI");
    verify(builder).get();
    verify(webTarget).request();
    verify(ledgerUriGenerator).transactionURI(isA(Account.class), eq("42"), eq("REFUND"), eq("42"));
  }

  /**
   * Test {@link LedgerService#getTransactionEvents(Account, String)}.
   *
   * <p>Method under test: {@link LedgerService#getTransactionEvents(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TransactionEvents LedgerService.getTransactionEvents(Account, String)"})
  public void testGetTransactionEvents() {
    // Arrange
    Builder builder = mock(Builder.class);
    when(builder.get())
        .thenReturn(new OutboundJaxrsResponse(Status.CREATED, new OutboundMessageContext()));

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.transactionEventsURI(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn("Transaction Events URI");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);

    // Act and Assert
    assertThrows(
        GetEventsException.class,
        () ->
            ledgerService.getTransactionEvents(
                new Account("42", TokenPaymentType.CARD, "ABC123"), "42"));
    verify(client).target("Transaction Events URI");
    verify(builder).get();
    verify(webTarget).request();
    verify(ledgerUriGenerator).transactionEventsURI(isA(Account.class), eq("42"));
  }

  /**
   * Test {@link LedgerService#getTransactionEvents(Account, String)}.
   *
   * <ul>
   *   <li>Then calls {@link OutboundJaxrsResponse#close()}.
   * </ul>
   *
   * <p>Method under test: {@link LedgerService#getTransactionEvents(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TransactionEvents LedgerService.getTransactionEvents(Account, String)"})
  public void testGetTransactionEvents_thenCallsClose() throws ProcessingException {
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

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.transactionEventsURI(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn("Transaction Events URI");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);

    // Act and Assert
    assertThrows(
        GetEventsException.class,
        () ->
            ledgerService.getTransactionEvents(
                new Account("42", TokenPaymentType.CARD, "ABC123"), "42"));
    verify(client).target("Transaction Events URI");
    verify(builder).get();
    verify(webTarget).request();
    verify(outboundJaxrsResponse).close();
    verify(outboundJaxrsResponse, atLeast(1)).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(ledgerUriGenerator).transactionEventsURI(isA(Account.class), eq("42"));
  }

  /**
   * Test {@link LedgerService#getTransactionEvents(Account, String)}.
   *
   * <ul>
   *   <li>Then return {@link TransactionEvents} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link LedgerService#getTransactionEvents(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TransactionEvents LedgerService.getTransactionEvents(Account, String)"})
  public void testGetTransactionEvents_thenReturnTransactionEvents() throws ProcessingException {
    // Arrange
    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    TransactionEvents transactionEvents = new TransactionEvents();
    when(outboundJaxrsResponse.readEntity(TransactionEvents.class)).thenReturn(transactionEvents);
    when(outboundJaxrsResponse.getStatus()).thenReturn(200);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());

    Builder builder = mock(Builder.class);
    when(builder.get()).thenReturn(outboundJaxrsResponse);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.transactionEventsURI(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn("Transaction Events URI");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);

    // Act
    TransactionEvents actualTransactionEvents =
        ledgerService.getTransactionEvents(
            new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(client).target("Transaction Events URI");
    verify(builder).get();
    verify(webTarget).request();
    verify(outboundJaxrsResponse).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(ledgerUriGenerator).transactionEventsURI(isA(Account.class), eq("42"));
    assertSame(transactionEvents, actualTransactionEvents);
  }

  /**
   * Test {@link LedgerService#getTransactionEvents(Account, String)}.
   *
   * <ul>
   *   <li>Then throw {@link ProcessingException}.
   * </ul>
   *
   * <p>Method under test: {@link LedgerService#getTransactionEvents(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TransactionEvents LedgerService.getTransactionEvents(Account, String)"})
  public void testGetTransactionEvents_thenThrowProcessingException() {
    // Arrange
    Builder builder = mock(Builder.class);
    when(builder.get()).thenThrow(new ProcessingException("An error occurred"));

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.transactionEventsURI(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn("Transaction Events URI");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);

    // Act and Assert
    assertThrows(
        ProcessingException.class,
        () ->
            ledgerService.getTransactionEvents(
                new Account("42", TokenPaymentType.CARD, "ABC123"), "42"));
    verify(client).target("Transaction Events URI");
    verify(builder).get();
    verify(webTarget).request();
    verify(ledgerUriGenerator).transactionEventsURI(isA(Account.class), eq("42"));
  }

  /**
   * Test {@link LedgerService#getPaymentRefunds(String, String)}.
   *
   * <p>Method under test: {@link LedgerService#getPaymentRefunds(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RefundsFromLedger LedgerService.getPaymentRefunds(String, String)"})
  public void testGetPaymentRefunds() {
    // Arrange
    Builder builder = mock(Builder.class);
    when(builder.get())
        .thenReturn(new OutboundJaxrsResponse(Status.CREATED, new OutboundMessageContext()));

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.transactionsForTransactionURI(
            Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn("Transactions For Transaction URI");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);

    // Act and Assert
    assertThrows(GetRefundsException.class, () -> ledgerService.getPaymentRefunds("42", "42"));
    verify(client).target("Transactions For Transaction URI");
    verify(builder).get();
    verify(webTarget).request();
    verify(ledgerUriGenerator).transactionsForTransactionURI("42", "42", "REFUND");
  }

  /**
   * Test {@link LedgerService#getPaymentRefunds(String, String)}.
   *
   * <ul>
   *   <li>Then calls {@link OutboundJaxrsResponse#close()}.
   * </ul>
   *
   * <p>Method under test: {@link LedgerService#getPaymentRefunds(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RefundsFromLedger LedgerService.getPaymentRefunds(String, String)"})
  public void testGetPaymentRefunds_thenCallsClose() throws ProcessingException {
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

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.transactionsForTransactionURI(
            Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn("Transactions For Transaction URI");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);

    // Act and Assert
    assertThrows(GetRefundsException.class, () -> ledgerService.getPaymentRefunds("42", "42"));
    verify(client).target("Transactions For Transaction URI");
    verify(builder).get();
    verify(webTarget).request();
    verify(outboundJaxrsResponse).close();
    verify(outboundJaxrsResponse, atLeast(1)).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(ledgerUriGenerator).transactionsForTransactionURI("42", "42", "REFUND");
  }

  /**
   * Test {@link LedgerService#getPaymentRefunds(String, String)}.
   *
   * <ul>
   *   <li>Then return {@link RefundsFromLedger} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link LedgerService#getPaymentRefunds(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RefundsFromLedger LedgerService.getPaymentRefunds(String, String)"})
  public void testGetPaymentRefunds_thenReturnRefundsFromLedger() throws ProcessingException {
    // Arrange
    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    RefundsFromLedger refundsFromLedger = new RefundsFromLedger();
    when(outboundJaxrsResponse.readEntity(RefundsFromLedger.class)).thenReturn(refundsFromLedger);
    when(outboundJaxrsResponse.getStatus()).thenReturn(200);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());

    Builder builder = mock(Builder.class);
    when(builder.get()).thenReturn(outboundJaxrsResponse);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.transactionsForTransactionURI(
            Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn("Transactions For Transaction URI");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);

    // Act
    RefundsFromLedger actualPaymentRefunds = ledgerService.getPaymentRefunds("42", "42");

    // Assert
    verify(client).target("Transactions For Transaction URI");
    verify(builder).get();
    verify(webTarget).request();
    verify(outboundJaxrsResponse).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(ledgerUriGenerator).transactionsForTransactionURI("42", "42", "REFUND");
    assertSame(refundsFromLedger, actualPaymentRefunds);
  }

  /**
   * Test {@link LedgerService#getPaymentRefunds(String, String)}.
   *
   * <ul>
   *   <li>Then throw {@link ProcessingException}.
   * </ul>
   *
   * <p>Method under test: {@link LedgerService#getPaymentRefunds(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RefundsFromLedger LedgerService.getPaymentRefunds(String, String)"})
  public void testGetPaymentRefunds_thenThrowProcessingException() {
    // Arrange
    Builder builder = mock(Builder.class);
    when(builder.get()).thenThrow(new ProcessingException("An error occurred"));

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.transactionsForTransactionURI(
            Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn("Transactions For Transaction URI");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);

    // Act and Assert
    assertThrows(ProcessingException.class, () -> ledgerService.getPaymentRefunds("42", "42"));
    verify(client).target("Transactions For Transaction URI");
    verify(builder).get();
    verify(webTarget).request();
    verify(ledgerUriGenerator).transactionsForTransactionURI("42", "42", "REFUND");
  }

  /**
   * Test {@link LedgerService#searchRefunds(Account, Map)}.
   *
   * <p>Method under test: {@link LedgerService#searchRefunds(Account, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SearchRefundsResponseFromLedger LedgerService.searchRefunds(Account, Map)"})
  public void testSearchRefunds() {
    // Arrange
    Builder builder = mock(Builder.class);
    when(builder.get())
        .thenReturn(new OutboundJaxrsResponse(Status.CREATED, new OutboundMessageContext()));

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(MediaType[].class))).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.transactionsURIWithParams(Mockito.<Map<String, String>>any()))
        .thenReturn("Transactions URIWith Params");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    // Act and Assert
    assertThrows(
        SearchRefundsException.class, () -> ledgerService.searchRefunds(account, new HashMap<>()));
    verify(client).target("Transactions URIWith Params");
    verify(builder2).accept(isA(MediaType[].class));
    verify(builder).get();
    verify(webTarget).request();
    verify(ledgerUriGenerator).transactionsURIWithParams(isA(Map.class));
  }

  /**
   * Test {@link LedgerService#searchRefunds(Account, Map)}.
   *
   * <p>Method under test: {@link LedgerService#searchRefunds(Account, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SearchRefundsResponseFromLedger LedgerService.searchRefunds(Account, Map)"})
  public void testSearchRefunds2() throws ProcessingException {
    // Arrange
    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.readEntity(SearchRefundsResponseFromLedger.class))
        .thenThrow(new ProcessingException("An error occurred"));
    when(outboundJaxrsResponse.getStatus()).thenReturn(200);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());

    Builder builder = mock(Builder.class);
    when(builder.get()).thenReturn(outboundJaxrsResponse);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(MediaType[].class))).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.transactionsURIWithParams(Mockito.<Map<String, String>>any()))
        .thenReturn("Transactions URIWith Params");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    // Act and Assert
    assertThrows(
        SearchRefundsException.class, () -> ledgerService.searchRefunds(account, new HashMap<>()));
    verify(client).target("Transactions URIWith Params");
    verify(builder2).accept(isA(MediaType[].class));
    verify(builder).get();
    verify(webTarget).request();
    verify(outboundJaxrsResponse).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(ledgerUriGenerator).transactionsURIWithParams(isA(Map.class));
  }

  /**
   * Test {@link LedgerService#searchRefunds(Account, Map)}.
   *
   * <ul>
   *   <li>Given {@link OutboundJaxrsResponse} {@link OutboundJaxrsResponse#getStatus()} return one.
   *   <li>Then calls {@link OutboundJaxrsResponse#close()}.
   * </ul>
   *
   * <p>Method under test: {@link LedgerService#searchRefunds(Account, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SearchRefundsResponseFromLedger LedgerService.searchRefunds(Account, Map)"})
  public void testSearchRefunds_givenOutboundJaxrsResponseGetStatusReturnOne_thenCallsClose()
      throws ProcessingException {
    // Arrange
    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.getStatus()).thenReturn(1);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());
    doNothing().when(outboundJaxrsResponse).close();

    Builder builder = mock(Builder.class);
    when(builder.get()).thenReturn(outboundJaxrsResponse);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(MediaType[].class))).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.transactionsURIWithParams(Mockito.<Map<String, String>>any()))
        .thenReturn("Transactions URIWith Params");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    // Act and Assert
    assertThrows(
        SearchRefundsException.class, () -> ledgerService.searchRefunds(account, new HashMap<>()));
    verify(client).target("Transactions URIWith Params");
    verify(builder2).accept(isA(MediaType[].class));
    verify(builder).get();
    verify(webTarget).request();
    verify(outboundJaxrsResponse).close();
    verify(outboundJaxrsResponse, atLeast(1)).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(ledgerUriGenerator).transactionsURIWithParams(isA(Map.class));
  }

  /**
   * Test {@link LedgerService#searchRefunds(Account, Map)}.
   *
   * <ul>
   *   <li>Then {@link HashMap#HashMap()} size is two.
   * </ul>
   *
   * <p>Method under test: {@link LedgerService#searchRefunds(Account, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SearchRefundsResponseFromLedger LedgerService.searchRefunds(Account, Map)"})
  public void testSearchRefunds_thenHashMapSizeIsTwo() throws ProcessingException {
    // Arrange
    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    SearchRefundsResponseFromLedger searchRefundsResponseFromLedger =
        new SearchRefundsResponseFromLedger();
    when(outboundJaxrsResponse.readEntity(SearchRefundsResponseFromLedger.class))
        .thenReturn(searchRefundsResponseFromLedger);
    when(outboundJaxrsResponse.getStatus()).thenReturn(200);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());

    Builder builder = mock(Builder.class);
    when(builder.get()).thenReturn(outboundJaxrsResponse);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(MediaType[].class))).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.transactionsURIWithParams(Mockito.<Map<String, String>>any()))
        .thenReturn("Transactions URIWith Params");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");
    HashMap<String, String> paramsAsMap = new HashMap<>();

    // Act
    SearchRefundsResponseFromLedger actualSearchRefundsResult =
        ledgerService.searchRefunds(account, paramsAsMap);

    // Assert
    verify(client).target("Transactions URIWith Params");
    verify(builder2).accept(isA(MediaType[].class));
    verify(builder).get();
    verify(webTarget).request();
    verify(outboundJaxrsResponse).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(ledgerUriGenerator).transactionsURIWithParams(isA(Map.class));
    assertEquals(2, paramsAsMap.size());
    assertEquals("42", paramsAsMap.get("account_id"));
    assertEquals("REFUND", paramsAsMap.get("transaction_type"));
    assertSame(searchRefundsResponseFromLedger, actualSearchRefundsResult);
  }

  /**
   * Test {@link LedgerService#searchRefunds(Account, Map)}.
   *
   * <ul>
   *   <li>Then throw {@link ProcessingException}.
   * </ul>
   *
   * <p>Method under test: {@link LedgerService#searchRefunds(Account, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SearchRefundsResponseFromLedger LedgerService.searchRefunds(Account, Map)"})
  public void testSearchRefunds_thenThrowProcessingException() {
    // Arrange
    Builder builder = mock(Builder.class);
    when(builder.get()).thenThrow(new ProcessingException("An error occurred"));

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(MediaType[].class))).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.transactionsURIWithParams(Mockito.<Map<String, String>>any()))
        .thenReturn("Transactions URIWith Params");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    // Act and Assert
    assertThrows(
        ProcessingException.class, () -> ledgerService.searchRefunds(account, new HashMap<>()));
    verify(client).target("Transactions URIWith Params");
    verify(builder2).accept(isA(MediaType[].class));
    verify(builder).get();
    verify(webTarget).request();
    verify(ledgerUriGenerator).transactionsURIWithParams(isA(Map.class));
  }

  /**
   * Test {@link LedgerService#searchDisputes(Account, Map)}.
   *
   * <p>Method under test: {@link LedgerService#searchDisputes(Account, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SearchDisputesResponseFromLedger LedgerService.searchDisputes(Account, Map)"})
  public void testSearchDisputes() {
    // Arrange
    Builder builder = mock(Builder.class);
    when(builder.get())
        .thenReturn(new OutboundJaxrsResponse(Status.CREATED, new OutboundMessageContext()));

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(MediaType[].class))).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.transactionsURIWithParams(Mockito.<Map<String, String>>any()))
        .thenReturn("Transactions URIWith Params");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    // Act and Assert
    assertThrows(
        SearchDisputesException.class,
        () -> ledgerService.searchDisputes(account, new HashMap<>()));
    verify(client).target("Transactions URIWith Params");
    verify(builder2).accept(isA(MediaType[].class));
    verify(builder).get();
    verify(webTarget).request();
    verify(ledgerUriGenerator).transactionsURIWithParams(isA(Map.class));
  }

  /**
   * Test {@link LedgerService#searchDisputes(Account, Map)}.
   *
   * <p>Method under test: {@link LedgerService#searchDisputes(Account, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SearchDisputesResponseFromLedger LedgerService.searchDisputes(Account, Map)"})
  public void testSearchDisputes2() throws ProcessingException {
    // Arrange
    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.readEntity(SearchDisputesResponseFromLedger.class))
        .thenThrow(new ProcessingException("An error occurred"));
    when(outboundJaxrsResponse.getStatus()).thenReturn(200);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());

    Builder builder = mock(Builder.class);
    when(builder.get()).thenReturn(outboundJaxrsResponse);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(MediaType[].class))).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.transactionsURIWithParams(Mockito.<Map<String, String>>any()))
        .thenReturn("Transactions URIWith Params");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    // Act and Assert
    assertThrows(
        SearchDisputesException.class,
        () -> ledgerService.searchDisputes(account, new HashMap<>()));
    verify(client).target("Transactions URIWith Params");
    verify(builder2).accept(isA(MediaType[].class));
    verify(builder).get();
    verify(webTarget).request();
    verify(outboundJaxrsResponse).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(ledgerUriGenerator).transactionsURIWithParams(isA(Map.class));
  }

  /**
   * Test {@link LedgerService#searchDisputes(Account, Map)}.
   *
   * <ul>
   *   <li>Given {@link OutboundJaxrsResponse} {@link OutboundJaxrsResponse#getStatus()} return one.
   *   <li>Then calls {@link OutboundJaxrsResponse#close()}.
   * </ul>
   *
   * <p>Method under test: {@link LedgerService#searchDisputes(Account, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SearchDisputesResponseFromLedger LedgerService.searchDisputes(Account, Map)"})
  public void testSearchDisputes_givenOutboundJaxrsResponseGetStatusReturnOne_thenCallsClose()
      throws ProcessingException {
    // Arrange
    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.getStatus()).thenReturn(1);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());
    doNothing().when(outboundJaxrsResponse).close();

    Builder builder = mock(Builder.class);
    when(builder.get()).thenReturn(outboundJaxrsResponse);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(MediaType[].class))).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.transactionsURIWithParams(Mockito.<Map<String, String>>any()))
        .thenReturn("Transactions URIWith Params");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    // Act and Assert
    assertThrows(
        SearchDisputesException.class,
        () -> ledgerService.searchDisputes(account, new HashMap<>()));
    verify(client).target("Transactions URIWith Params");
    verify(builder2).accept(isA(MediaType[].class));
    verify(builder).get();
    verify(webTarget).request();
    verify(outboundJaxrsResponse).close();
    verify(outboundJaxrsResponse, atLeast(1)).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(ledgerUriGenerator).transactionsURIWithParams(isA(Map.class));
  }

  /**
   * Test {@link LedgerService#searchDisputes(Account, Map)}.
   *
   * <ul>
   *   <li>Then {@link HashMap#HashMap()} size is two.
   * </ul>
   *
   * <p>Method under test: {@link LedgerService#searchDisputes(Account, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SearchDisputesResponseFromLedger LedgerService.searchDisputes(Account, Map)"})
  public void testSearchDisputes_thenHashMapSizeIsTwo() throws ProcessingException {
    // Arrange
    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    SearchDisputesResponseFromLedger searchDisputesResponseFromLedger =
        new SearchDisputesResponseFromLedger();
    when(outboundJaxrsResponse.readEntity(SearchDisputesResponseFromLedger.class))
        .thenReturn(searchDisputesResponseFromLedger);
    when(outboundJaxrsResponse.getStatus()).thenReturn(200);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());

    Builder builder = mock(Builder.class);
    when(builder.get()).thenReturn(outboundJaxrsResponse);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(MediaType[].class))).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.transactionsURIWithParams(Mockito.<Map<String, String>>any()))
        .thenReturn("Transactions URIWith Params");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");
    HashMap<String, String> paramsAsMap = new HashMap<>();

    // Act
    SearchDisputesResponseFromLedger actualSearchDisputesResult =
        ledgerService.searchDisputes(account, paramsAsMap);

    // Assert
    verify(client).target("Transactions URIWith Params");
    verify(builder2).accept(isA(MediaType[].class));
    verify(builder).get();
    verify(webTarget).request();
    verify(outboundJaxrsResponse).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(ledgerUriGenerator).transactionsURIWithParams(isA(Map.class));
    assertEquals(2, paramsAsMap.size());
    assertEquals("42", paramsAsMap.get("account_id"));
    assertEquals("DISPUTE", paramsAsMap.get("transaction_type"));
    assertSame(searchDisputesResponseFromLedger, actualSearchDisputesResult);
  }

  /**
   * Test {@link LedgerService#searchDisputes(Account, Map)}.
   *
   * <ul>
   *   <li>Then throw {@link ProcessingException}.
   * </ul>
   *
   * <p>Method under test: {@link LedgerService#searchDisputes(Account, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SearchDisputesResponseFromLedger LedgerService.searchDisputes(Account, Map)"})
  public void testSearchDisputes_thenThrowProcessingException() {
    // Arrange
    Builder builder = mock(Builder.class);
    when(builder.get()).thenThrow(new ProcessingException("An error occurred"));

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(MediaType[].class))).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.transactionsURIWithParams(Mockito.<Map<String, String>>any()))
        .thenReturn("Transactions URIWith Params");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    // Act and Assert
    assertThrows(
        ProcessingException.class, () -> ledgerService.searchDisputes(account, new HashMap<>()));
    verify(client).target("Transactions URIWith Params");
    verify(builder2).accept(isA(MediaType[].class));
    verify(builder).get();
    verify(webTarget).request();
    verify(ledgerUriGenerator).transactionsURIWithParams(isA(Map.class));
  }

  /**
   * Test {@link LedgerService#searchPayments(Account, Map)}.
   *
   * <p>Method under test: {@link LedgerService#searchPayments(Account, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentSearchResponse LedgerService.searchPayments(Account, Map)"})
  public void testSearchPayments() {
    // Arrange
    Builder builder = mock(Builder.class);
    when(builder.get())
        .thenReturn(new OutboundJaxrsResponse(Status.CREATED, new OutboundMessageContext()));

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(MediaType[].class))).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.transactionsURIWithParams(Mockito.<Map<String, String>>any()))
        .thenReturn("Transactions URIWith Params");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    // Act and Assert
    assertThrows(
        SearchPaymentsException.class,
        () -> ledgerService.searchPayments(account, new HashMap<>()));
    verify(client).target("Transactions URIWith Params");
    verify(builder2).accept(isA(MediaType[].class));
    verify(builder).get();
    verify(webTarget).request();
    verify(ledgerUriGenerator).transactionsURIWithParams(isA(Map.class));
  }

  /**
   * Test {@link LedgerService#searchPayments(Account, Map)}.
   *
   * <p>Method under test: {@link LedgerService#searchPayments(Account, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentSearchResponse LedgerService.searchPayments(Account, Map)"})
  public void testSearchPayments2() throws ProcessingException {
    // Arrange
    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.readEntity(
            Mockito.<GenericType<PaymentSearchResponse<TransactionResponse>>>any()))
        .thenThrow(new ProcessingException("An error occurred"));
    when(outboundJaxrsResponse.getStatus()).thenReturn(200);

    Builder builder = mock(Builder.class);
    when(builder.get()).thenReturn(outboundJaxrsResponse);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(MediaType[].class))).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.transactionsURIWithParams(Mockito.<Map<String, String>>any()))
        .thenReturn("Transactions URIWith Params");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    // Act and Assert
    assertThrows(
        SearchPaymentsException.class,
        () -> ledgerService.searchPayments(account, new HashMap<>()));
    verify(client).target("Transactions URIWith Params");
    verify(builder2).accept(isA(MediaType[].class));
    verify(builder).get();
    verify(webTarget).request();
    verify(outboundJaxrsResponse).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(GenericType.class));
    verify(ledgerUriGenerator).transactionsURIWithParams(isA(Map.class));
  }

  /**
   * Test {@link LedgerService#searchPayments(Account, Map)}.
   *
   * <ul>
   *   <li>Given {@link OutboundJaxrsResponse} {@link OutboundJaxrsResponse#getStatus()} return one.
   *   <li>Then calls {@link OutboundJaxrsResponse#close()}.
   * </ul>
   *
   * <p>Method under test: {@link LedgerService#searchPayments(Account, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentSearchResponse LedgerService.searchPayments(Account, Map)"})
  public void testSearchPayments_givenOutboundJaxrsResponseGetStatusReturnOne_thenCallsClose()
      throws ProcessingException {
    // Arrange
    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.getStatus()).thenReturn(1);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());
    doNothing().when(outboundJaxrsResponse).close();

    Builder builder = mock(Builder.class);
    when(builder.get()).thenReturn(outboundJaxrsResponse);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(MediaType[].class))).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.transactionsURIWithParams(Mockito.<Map<String, String>>any()))
        .thenReturn("Transactions URIWith Params");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    // Act and Assert
    assertThrows(
        SearchPaymentsException.class,
        () -> ledgerService.searchPayments(account, new HashMap<>()));
    verify(client).target("Transactions URIWith Params");
    verify(builder2).accept(isA(MediaType[].class));
    verify(builder).get();
    verify(webTarget).request();
    verify(outboundJaxrsResponse).close();
    verify(outboundJaxrsResponse, atLeast(1)).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(ledgerUriGenerator).transactionsURIWithParams(isA(Map.class));
  }

  /**
   * Test {@link LedgerService#searchPayments(Account, Map)}.
   *
   * <ul>
   *   <li>Then {@link HashMap#HashMap()} size is three.
   * </ul>
   *
   * <p>Method under test: {@link LedgerService#searchPayments(Account, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentSearchResponse LedgerService.searchPayments(Account, Map)"})
  public void testSearchPayments_thenHashMapSizeIsThree() throws ProcessingException {
    // Arrange
    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    PaymentSearchResponse<TransactionResponse> paymentSearchResponse =
        new PaymentSearchResponse<>();
    when(outboundJaxrsResponse.readEntity(
            Mockito.<GenericType<PaymentSearchResponse<TransactionResponse>>>any()))
        .thenReturn(paymentSearchResponse);
    when(outboundJaxrsResponse.getStatus()).thenReturn(200);

    Builder builder = mock(Builder.class);
    when(builder.get()).thenReturn(outboundJaxrsResponse);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(MediaType[].class))).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.transactionsURIWithParams(Mockito.<Map<String, String>>any()))
        .thenReturn("Transactions URIWith Params");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");
    HashMap<String, String> paramsAsMap = new HashMap<>();

    // Act
    PaymentSearchResponse<TransactionResponse> actualSearchPaymentsResult =
        ledgerService.searchPayments(account, paramsAsMap);

    // Assert
    verify(client).target("Transactions URIWith Params");
    verify(builder2).accept(isA(MediaType[].class));
    verify(builder).get();
    verify(webTarget).request();
    verify(outboundJaxrsResponse).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(GenericType.class));
    verify(ledgerUriGenerator).transactionsURIWithParams(isA(Map.class));
    assertEquals(3, paramsAsMap.size());
    assertEquals("42", paramsAsMap.get("account_id"));
    assertEquals("PAYMENT", paramsAsMap.get("transaction_type"));
    assertEquals(
        Boolean.TRUE.toString(), paramsAsMap.get(LedgerService.PARAM_EXACT_REFERENCE_MATCH));
    assertSame(paymentSearchResponse, actualSearchPaymentsResult);
  }

  /**
   * Test {@link LedgerService#searchPayments(Account, Map)}.
   *
   * <ul>
   *   <li>Then throw {@link ProcessingException}.
   * </ul>
   *
   * <p>Method under test: {@link LedgerService#searchPayments(Account, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentSearchResponse LedgerService.searchPayments(Account, Map)"})
  public void testSearchPayments_thenThrowProcessingException() {
    // Arrange
    Builder builder = mock(Builder.class);
    when(builder.get()).thenThrow(new ProcessingException("An error occurred"));

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(MediaType[].class))).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.transactionsURIWithParams(Mockito.<Map<String, String>>any()))
        .thenReturn("Transactions URIWith Params");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    // Act and Assert
    assertThrows(
        ProcessingException.class, () -> ledgerService.searchPayments(account, new HashMap<>()));
    verify(client).target("Transactions URIWith Params");
    verify(builder2).accept(isA(MediaType[].class));
    verify(builder).get();
    verify(webTarget).request();
    verify(ledgerUriGenerator).transactionsURIWithParams(isA(Map.class));
  }

  /**
   * Test {@link LedgerService#getAgreement(Account, String)}.
   *
   * <p>Method under test: {@link LedgerService#getAgreement(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AgreementLedgerResponse LedgerService.getAgreement(Account, String)"})
  public void testGetAgreement() {
    // Arrange
    Builder builder = mock(Builder.class);
    when(builder.get())
        .thenReturn(new OutboundJaxrsResponse(Status.CREATED, new OutboundMessageContext()));

    Builder builder2 = mock(Builder.class);
    when(builder2.header(Mockito.<String>any(), Mockito.<Object>any())).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.agreementURI(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn("Agreement URI");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);

    // Act and Assert
    assertThrows(
        GetAgreementException.class,
        () -> ledgerService.getAgreement(new Account("42", TokenPaymentType.CARD, "ABC123"), "42"));
    verify(client).target("Agreement URI");
    verify(builder2).header(eq("X-Consistent"), isA(Object.class));
    verify(builder).get();
    verify(webTarget).request();
    verify(ledgerUriGenerator).agreementURI(isA(Account.class), eq("42"));
  }

  /**
   * Test {@link LedgerService#getAgreement(Account, String)}.
   *
   * <ul>
   *   <li>Given {@link OutboundJaxrsResponse} {@link OutboundJaxrsResponse#getStatus()} return one.
   *   <li>Then calls {@link OutboundJaxrsResponse#close()}.
   * </ul>
   *
   * <p>Method under test: {@link LedgerService#getAgreement(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AgreementLedgerResponse LedgerService.getAgreement(Account, String)"})
  public void testGetAgreement_givenOutboundJaxrsResponseGetStatusReturnOne_thenCallsClose()
      throws ProcessingException {
    // Arrange
    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.getStatus()).thenReturn(1);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());
    doNothing().when(outboundJaxrsResponse).close();

    Builder builder = mock(Builder.class);
    when(builder.get()).thenReturn(outboundJaxrsResponse);

    Builder builder2 = mock(Builder.class);
    when(builder2.header(Mockito.<String>any(), Mockito.<Object>any())).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.agreementURI(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn("Agreement URI");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);

    // Act and Assert
    assertThrows(
        GetAgreementException.class,
        () -> ledgerService.getAgreement(new Account("42", TokenPaymentType.CARD, "ABC123"), "42"));
    verify(client).target("Agreement URI");
    verify(builder2).header(eq("X-Consistent"), isA(Object.class));
    verify(builder).get();
    verify(webTarget).request();
    verify(outboundJaxrsResponse).close();
    verify(outboundJaxrsResponse, atLeast(1)).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(ledgerUriGenerator).agreementURI(isA(Account.class), eq("42"));
  }

  /**
   * Test {@link LedgerService#getAgreement(Account, String)}.
   *
   * <ul>
   *   <li>Then return {@link AgreementLedgerResponse} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link LedgerService#getAgreement(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AgreementLedgerResponse LedgerService.getAgreement(Account, String)"})
  public void testGetAgreement_thenReturnAgreementLedgerResponse() throws ProcessingException {
    // Arrange
    AgreementLedgerResponse agreementLedgerResponse = new AgreementLedgerResponse();
    agreementLedgerResponse.setExternalId("42");

    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.readEntity(AgreementLedgerResponse.class))
        .thenReturn(agreementLedgerResponse);
    when(outboundJaxrsResponse.getStatus()).thenReturn(200);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());

    Builder builder = mock(Builder.class);
    when(builder.get()).thenReturn(outboundJaxrsResponse);

    Builder builder2 = mock(Builder.class);
    when(builder2.header(Mockito.<String>any(), Mockito.<Object>any())).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.agreementURI(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn("Agreement URI");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);

    // Act
    AgreementLedgerResponse actualAgreement =
        ledgerService.getAgreement(new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(client).target("Agreement URI");
    verify(builder2).header(eq("X-Consistent"), isA(Object.class));
    verify(builder).get();
    verify(webTarget).request();
    verify(outboundJaxrsResponse).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(ledgerUriGenerator).agreementURI(isA(Account.class), eq("42"));
    assertSame(agreementLedgerResponse, actualAgreement);
  }

  /**
   * Test {@link LedgerService#getAgreement(Account, String)}.
   *
   * <ul>
   *   <li>Then throw {@link ProcessingException}.
   * </ul>
   *
   * <p>Method under test: {@link LedgerService#getAgreement(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AgreementLedgerResponse LedgerService.getAgreement(Account, String)"})
  public void testGetAgreement_thenThrowProcessingException() {
    // Arrange
    Builder builder = mock(Builder.class);
    when(builder.get()).thenThrow(new ProcessingException("An error occurred"));

    Builder builder2 = mock(Builder.class);
    when(builder2.header(Mockito.<String>any(), Mockito.<Object>any())).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.agreementURI(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn("Agreement URI");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);

    // Act and Assert
    assertThrows(
        ProcessingException.class,
        () -> ledgerService.getAgreement(new Account("42", TokenPaymentType.CARD, "ABC123"), "42"));
    verify(client).target("Agreement URI");
    verify(builder2).header(eq("X-Consistent"), isA(Object.class));
    verify(builder).get();
    verify(webTarget).request();
    verify(ledgerUriGenerator).agreementURI(isA(Account.class), eq("42"));
  }

  /**
   * Test {@link LedgerService#searchAgreements(Account, AgreementSearchParams)}.
   *
   * <p>Method under test: {@link LedgerService#searchAgreements(Account, AgreementSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SearchResults LedgerService.searchAgreements(Account, AgreementSearchParams)"
  })
  public void testSearchAgreements() {
    // Arrange
    Builder builder = mock(Builder.class);
    when(builder.get())
        .thenReturn(new OutboundJaxrsResponse(Status.CREATED, new OutboundMessageContext()));

    Builder builder2 = mock(Builder.class);
    when(builder2.header(Mockito.<String>any(), Mockito.<Object>any())).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.agreementsURIWithParams(Mockito.<Map<String, String>>any()))
        .thenReturn("Agreements URIWith Params");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    // Act and Assert
    assertThrows(
        SearchAgreementsException.class,
        () -> ledgerService.searchAgreements(account, new AgreementSearchParams()));
    verify(client).target("Agreements URIWith Params");
    verify(builder2).header(eq("Accept"), isA(Object.class));
    verify(builder).get();
    verify(webTarget).request();
    verify(ledgerUriGenerator).agreementsURIWithParams(isA(Map.class));
  }

  /**
   * Test {@link LedgerService#searchAgreements(Account, AgreementSearchParams)}.
   *
   * <p>Method under test: {@link LedgerService#searchAgreements(Account, AgreementSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SearchResults LedgerService.searchAgreements(Account, AgreementSearchParams)"
  })
  public void testSearchAgreements2() throws ProcessingException {
    // Arrange
    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.readEntity(
            Mockito.<GenericType<SearchResults<AgreementLedgerResponse>>>any()))
        .thenThrow(new ProcessingException("An error occurred"));
    when(outboundJaxrsResponse.getStatus()).thenReturn(200);

    Builder builder = mock(Builder.class);
    when(builder.get()).thenReturn(outboundJaxrsResponse);

    Builder builder2 = mock(Builder.class);
    when(builder2.header(Mockito.<String>any(), Mockito.<Object>any())).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.agreementsURIWithParams(Mockito.<Map<String, String>>any()))
        .thenReturn("Agreements URIWith Params");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    // Act and Assert
    assertThrows(
        SearchAgreementsException.class,
        () -> ledgerService.searchAgreements(account, new AgreementSearchParams()));
    verify(client).target("Agreements URIWith Params");
    verify(builder2).header(eq("Accept"), isA(Object.class));
    verify(builder).get();
    verify(webTarget).request();
    verify(outboundJaxrsResponse).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(GenericType.class));
    verify(ledgerUriGenerator).agreementsURIWithParams(isA(Map.class));
  }

  /**
   * Test {@link LedgerService#searchAgreements(Account, AgreementSearchParams)}.
   *
   * <p>Method under test: {@link LedgerService#searchAgreements(Account, AgreementSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SearchResults LedgerService.searchAgreements(Account, AgreementSearchParams)"
  })
  public void testSearchAgreements3() throws ProcessingException {
    // Arrange
    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    SearchResults<AgreementLedgerResponse> searchResults = new SearchResults<>();
    when(outboundJaxrsResponse.readEntity(
            Mockito.<GenericType<SearchResults<AgreementLedgerResponse>>>any()))
        .thenReturn(searchResults);
    when(outboundJaxrsResponse.getStatus()).thenReturn(200);

    Builder builder = mock(Builder.class);
    when(builder.get()).thenReturn(outboundJaxrsResponse);

    Builder builder2 = mock(Builder.class);
    when(builder2.header(Mockito.<String>any(), Mockito.<Object>any())).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.agreementsURIWithParams(Mockito.<Map<String, String>>any()))
        .thenReturn("Agreements URIWith Params");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");
    AgreementSearchParams searchParams = new AgreementSearchParams("account_id", " ", "1", "1");

    // Act
    SearchResults<AgreementLedgerResponse> actualSearchAgreementsResult =
        ledgerService.searchAgreements(account, searchParams);

    // Assert
    verify(client).target("Agreements URIWith Params");
    verify(builder2).header(eq("Accept"), isA(Object.class));
    verify(builder).get();
    verify(webTarget).request();
    verify(outboundJaxrsResponse).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(GenericType.class));
    verify(ledgerUriGenerator).agreementsURIWithParams(isA(Map.class));
    assertSame(searchResults, actualSearchAgreementsResult);
  }

  /**
   * Test {@link LedgerService#searchAgreements(Account, AgreementSearchParams)}.
   *
   * <ul>
   *   <li>Given {@link OutboundJaxrsResponse} {@link OutboundJaxrsResponse#getStatus()} return one.
   *   <li>Then calls {@link OutboundJaxrsResponse#close()}.
   * </ul>
   *
   * <p>Method under test: {@link LedgerService#searchAgreements(Account, AgreementSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SearchResults LedgerService.searchAgreements(Account, AgreementSearchParams)"
  })
  public void testSearchAgreements_givenOutboundJaxrsResponseGetStatusReturnOne_thenCallsClose()
      throws ProcessingException {
    // Arrange
    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.getStatus()).thenReturn(1);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());
    doNothing().when(outboundJaxrsResponse).close();

    Builder builder = mock(Builder.class);
    when(builder.get()).thenReturn(outboundJaxrsResponse);

    Builder builder2 = mock(Builder.class);
    when(builder2.header(Mockito.<String>any(), Mockito.<Object>any())).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.agreementsURIWithParams(Mockito.<Map<String, String>>any()))
        .thenReturn("Agreements URIWith Params");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    // Act and Assert
    assertThrows(
        SearchAgreementsException.class,
        () -> ledgerService.searchAgreements(account, new AgreementSearchParams()));
    verify(client).target("Agreements URIWith Params");
    verify(builder2).header(eq("Accept"), isA(Object.class));
    verify(builder).get();
    verify(webTarget).request();
    verify(outboundJaxrsResponse).close();
    verify(outboundJaxrsResponse, atLeast(1)).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(ledgerUriGenerator).agreementsURIWithParams(isA(Map.class));
  }

  /**
   * Test {@link LedgerService#searchAgreements(Account, AgreementSearchParams)}.
   *
   * <ul>
   *   <li>Then return {@link SearchResults#SearchResults()}.
   * </ul>
   *
   * <p>Method under test: {@link LedgerService#searchAgreements(Account, AgreementSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SearchResults LedgerService.searchAgreements(Account, AgreementSearchParams)"
  })
  public void testSearchAgreements_thenReturnSearchResults() throws ProcessingException {
    // Arrange
    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    SearchResults<AgreementLedgerResponse> searchResults = new SearchResults<>();
    when(outboundJaxrsResponse.readEntity(
            Mockito.<GenericType<SearchResults<AgreementLedgerResponse>>>any()))
        .thenReturn(searchResults);
    when(outboundJaxrsResponse.getStatus()).thenReturn(200);

    Builder builder = mock(Builder.class);
    when(builder.get()).thenReturn(outboundJaxrsResponse);

    Builder builder2 = mock(Builder.class);
    when(builder2.header(Mockito.<String>any(), Mockito.<Object>any())).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.agreementsURIWithParams(Mockito.<Map<String, String>>any()))
        .thenReturn("Agreements URIWith Params");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    // Act
    SearchResults<AgreementLedgerResponse> actualSearchAgreementsResult =
        ledgerService.searchAgreements(account, new AgreementSearchParams());

    // Assert
    verify(client).target("Agreements URIWith Params");
    verify(builder2).header(eq("Accept"), isA(Object.class));
    verify(builder).get();
    verify(webTarget).request();
    verify(outboundJaxrsResponse).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(GenericType.class));
    verify(ledgerUriGenerator).agreementsURIWithParams(isA(Map.class));
    assertSame(searchResults, actualSearchAgreementsResult);
  }

  /**
   * Test {@link LedgerService#searchAgreements(Account, AgreementSearchParams)}.
   *
   * <ul>
   *   <li>Then throw {@link ProcessingException}.
   * </ul>
   *
   * <p>Method under test: {@link LedgerService#searchAgreements(Account, AgreementSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SearchResults LedgerService.searchAgreements(Account, AgreementSearchParams)"
  })
  public void testSearchAgreements_thenThrowProcessingException() {
    // Arrange
    Builder builder = mock(Builder.class);
    when(builder.get()).thenThrow(new ProcessingException("An error occurred"));

    Builder builder2 = mock(Builder.class);
    when(builder2.header(Mockito.<String>any(), Mockito.<Object>any())).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.agreementsURIWithParams(Mockito.<Map<String, String>>any()))
        .thenReturn("Agreements URIWith Params");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    // Act and Assert
    assertThrows(
        ProcessingException.class,
        () -> ledgerService.searchAgreements(account, new AgreementSearchParams()));
    verify(client).target("Agreements URIWith Params");
    verify(builder2).header(eq("Accept"), isA(Object.class));
    verify(builder).get();
    verify(webTarget).request();
    verify(ledgerUriGenerator).agreementsURIWithParams(isA(Map.class));
  }
}
