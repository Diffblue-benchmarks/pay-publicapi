package uk.gov.pay.api.model.search.card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.net.URI;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.model.Address;
import uk.gov.pay.api.model.AuthorisationSummary;
import uk.gov.pay.api.model.CardDetails;
import uk.gov.pay.api.model.CardDetailsFromResponse;
import uk.gov.pay.api.model.Exemption;
import uk.gov.pay.api.model.PaymentConnectorResponseLink;
import uk.gov.pay.api.model.PaymentSettlementSummary;
import uk.gov.pay.api.model.PaymentState;
import uk.gov.pay.api.model.RefundSummary;
import uk.gov.pay.api.model.ThreeDSecure;
import uk.gov.pay.api.model.TransactionResponse;
import uk.gov.pay.api.model.Wallet;
import uk.gov.pay.api.model.links.Link;
import uk.gov.pay.api.model.links.PaymentLinksForSearch;
import uk.gov.pay.api.model.links.PostLink;
import uk.gov.service.payments.commons.model.AgreementPaymentType;
import uk.gov.service.payments.commons.model.AuthorisationMode;
import uk.gov.service.payments.commons.model.SupportedLanguage;
import uk.gov.service.payments.commons.model.charge.ExternalMetadata;

class PaymentForSearchResultDiffblueTest {
  /**
   * Test {@link PaymentForSearchResult#PaymentForSearchResult(String, long, PaymentState, String,
   * String, String, String, String, String, SupportedLanguage, boolean, boolean, RefundSummary,
   * PaymentSettlementSummary, CardDetails, List, URI, URI, URI, URI, URI, Long, Long, String,
   * ExternalMetadata, Long, Long, AuthorisationSummary, AuthorisationMode, AgreementPaymentType,
   * Exemption)}.
   *
   * <p>Method under test: {@link PaymentForSearchResult#PaymentForSearchResult(String, long,
   * PaymentState, String, String, String, String, String, String, SupportedLanguage, boolean,
   * boolean, RefundSummary, PaymentSettlementSummary, CardDetails, List, URI, URI, URI, URI, URI,
   * Long, Long, String, ExternalMetadata, Long, Long, AuthorisationSummary, AuthorisationMode,
   * AgreementPaymentType, Exemption)}
   */
  @Test
  @DisplayName(
      "Test new PaymentForSearchResult(String, long, PaymentState, String, String, String, String, String, String, SupportedLanguage, boolean, boolean, RefundSummary, PaymentSettlementSummary, CardDetails, List, URI, URI, URI, URI, URI, Long, Long, String, ExternalMetadata, Long, Long, AuthorisationSummary, AuthorisationMode, AgreementPaymentType, Exemption)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentForSearchResult.<init>(String, long, PaymentState, String, String, String, String, String, String, SupportedLanguage, boolean, boolean, RefundSummary, PaymentSettlementSummary, CardDetails, List, URI, URI, URI, URI, URI, Long, Long, String, ExternalMetadata, Long, Long, AuthorisationSummary, AuthorisationMode, AgreementPaymentType, Exemption)"
  })
  void testNewPaymentForSearchResult() {
    // Arrange
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
    URI selfLink = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    URI paymentEventsLink = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    URI paymentCancelLink = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    URI paymentRefundsLink = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    URI paymentCaptureUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    ExternalMetadata externalMetadata = new ExternalMetadata(new HashMap<>());
    AuthorisationSummary authorisationSummary = new AuthorisationSummary();

    // Act
    PaymentForSearchResult actualPaymentForSearchResult =
        new PaymentForSearchResult(
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
            selfLink,
            paymentEventsLink,
            paymentCancelLink,
            paymentRefundsLink,
            paymentCaptureUri,
            1L,
            1L,
            "42",
            externalMetadata,
            1L,
            1L,
            authorisationSummary,
            AuthorisationMode.WEB,
            AgreementPaymentType.INSTALMENT,
            new Exemption());

    // Assert
    assertSame(state, actualPaymentForSearchResult.getState());
  }

  /**
   * Test {@link PaymentForSearchResult#PaymentForSearchResult(String, long, PaymentState, String,
   * String, String, String, String, String, SupportedLanguage, boolean, boolean, RefundSummary,
   * PaymentSettlementSummary, CardDetails, List, URI, URI, URI, URI, URI, Long, Long, String,
   * ExternalMetadata, Long, Long, AuthorisationSummary, AuthorisationMode, AgreementPaymentType,
   * Exemption)}.
   *
   * <ul>
   *   <li>Then Links Capture return {@link PostLink}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentForSearchResult#PaymentForSearchResult(String, long,
   * PaymentState, String, String, String, String, String, String, SupportedLanguage, boolean,
   * boolean, RefundSummary, PaymentSettlementSummary, CardDetails, List, URI, URI, URI, URI, URI,
   * Long, Long, String, ExternalMetadata, Long, Long, AuthorisationSummary, AuthorisationMode,
   * AgreementPaymentType, Exemption)}
   */
  @Test
  @DisplayName(
      "Test new PaymentForSearchResult(String, long, PaymentState, String, String, String, String, String, String, SupportedLanguage, boolean, boolean, RefundSummary, PaymentSettlementSummary, CardDetails, List, URI, URI, URI, URI, URI, Long, Long, String, ExternalMetadata, Long, Long, AuthorisationSummary, AuthorisationMode, AgreementPaymentType, Exemption); then Links Capture return PostLink")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentForSearchResult.<init>(String, long, PaymentState, String, String, String, String, String, String, SupportedLanguage, boolean, boolean, RefundSummary, PaymentSettlementSummary, CardDetails, List, URI, URI, URI, URI, URI, Long, Long, String, ExternalMetadata, Long, Long, AuthorisationSummary, AuthorisationMode, AgreementPaymentType, Exemption)"
  })
  void testNewPaymentForSearchResult_thenLinksCaptureReturnPostLink() {
    // Arrange
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
    PaymentConnectorResponseLink paymentConnectorResponseLink =
        new PaymentConnectorResponseLink("capture", "Href", "Method", "Type", new HashMap<>());
    links.add(paymentConnectorResponseLink);
    URI selfLink = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    URI paymentEventsLink = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    URI paymentCancelLink = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    URI paymentRefundsLink = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    URI paymentCaptureUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    ExternalMetadata externalMetadata = new ExternalMetadata(new HashMap<>());
    AuthorisationSummary authorisationSummary = new AuthorisationSummary();

    // Act
    PaymentForSearchResult actualPaymentForSearchResult =
        new PaymentForSearchResult(
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
            selfLink,
            paymentEventsLink,
            paymentCancelLink,
            paymentRefundsLink,
            paymentCaptureUri,
            1L,
            1L,
            "42",
            externalMetadata,
            1L,
            1L,
            authorisationSummary,
            AuthorisationMode.AGREEMENT,
            AgreementPaymentType.INSTALMENT,
            new Exemption());

    // Assert
    Link capture = actualPaymentForSearchResult.getLinks().getCapture();
    assertTrue(capture instanceof PostLink);
    assertEquals("POST", capture.getMethod());
    assertNull(((PostLink) capture).getType());
    assertNull(((PostLink) capture).getParams());
    assertEquals(AuthorisationMode.AGREEMENT, actualPaymentForSearchResult.getAuthorisationMode());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        capture.getHref());
  }

  /**
   * Test {@link PaymentForSearchResult#PaymentForSearchResult(String, long, PaymentState, String,
   * String, String, String, String, String, SupportedLanguage, boolean, boolean, RefundSummary,
   * PaymentSettlementSummary, CardDetails, List, URI, URI, URI, URI, URI, Long, Long, String,
   * ExternalMetadata, Long, Long, AuthorisationSummary, AuthorisationMode, AgreementPaymentType,
   * Exemption)}.
   *
   * <ul>
   *   <li>Then return Links Cancel Method is {@code POST}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentForSearchResult#PaymentForSearchResult(String, long,
   * PaymentState, String, String, String, String, String, String, SupportedLanguage, boolean,
   * boolean, RefundSummary, PaymentSettlementSummary, CardDetails, List, URI, URI, URI, URI, URI,
   * Long, Long, String, ExternalMetadata, Long, Long, AuthorisationSummary, AuthorisationMode,
   * AgreementPaymentType, Exemption)}
   */
  @Test
  @DisplayName(
      "Test new PaymentForSearchResult(String, long, PaymentState, String, String, String, String, String, String, SupportedLanguage, boolean, boolean, RefundSummary, PaymentSettlementSummary, CardDetails, List, URI, URI, URI, URI, URI, Long, Long, String, ExternalMetadata, Long, Long, AuthorisationSummary, AuthorisationMode, AgreementPaymentType, Exemption); then return Links Cancel Method is 'POST'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentForSearchResult.<init>(String, long, PaymentState, String, String, String, String, String, String, SupportedLanguage, boolean, boolean, RefundSummary, PaymentSettlementSummary, CardDetails, List, URI, URI, URI, URI, URI, Long, Long, String, ExternalMetadata, Long, Long, AuthorisationSummary, AuthorisationMode, AgreementPaymentType, Exemption)"
  })
  void testNewPaymentForSearchResult_thenReturnLinksCancelMethodIsPost() {
    // Arrange
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
    URI selfLink = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    URI paymentEventsLink = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    URI paymentCancelLink = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    URI paymentRefundsLink = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    URI paymentCaptureUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    ExternalMetadata externalMetadata = new ExternalMetadata(new HashMap<>());
    AuthorisationSummary authorisationSummary = new AuthorisationSummary();

    // Act
    PaymentForSearchResult actualPaymentForSearchResult =
        new PaymentForSearchResult(
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
            selfLink,
            paymentEventsLink,
            paymentCancelLink,
            paymentRefundsLink,
            paymentCaptureUri,
            1L,
            1L,
            "42",
            externalMetadata,
            1L,
            1L,
            authorisationSummary,
            AuthorisationMode.WEB,
            AgreementPaymentType.INSTALMENT,
            new Exemption());

    // Assert
    PostLink cancel = actualPaymentForSearchResult.getLinks().getCancel();
    assertEquals("POST", cancel.getMethod());
    assertNull(cancel.getType());
    assertNull(cancel.getParams());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        cancel.getHref());
  }

  /**
   * Test {@link PaymentForSearchResult#PaymentForSearchResult(String, long, PaymentState, String,
   * String, String, String, String, String, SupportedLanguage, boolean, boolean, RefundSummary,
   * PaymentSettlementSummary, CardDetails, List, URI, URI, URI, URI, URI, Long, Long, String,
   * ExternalMetadata, Long, Long, AuthorisationSummary, AuthorisationMode, AgreementPaymentType,
   * Exemption)}.
   *
   * <ul>
   *   <li>Then return Metadata Metadata Empty.
   * </ul>
   *
   * <p>Method under test: {@link PaymentForSearchResult#PaymentForSearchResult(String, long,
   * PaymentState, String, String, String, String, String, String, SupportedLanguage, boolean,
   * boolean, RefundSummary, PaymentSettlementSummary, CardDetails, List, URI, URI, URI, URI, URI,
   * Long, Long, String, ExternalMetadata, Long, Long, AuthorisationSummary, AuthorisationMode,
   * AgreementPaymentType, Exemption)}
   */
  @Test
  @DisplayName(
      "Test new PaymentForSearchResult(String, long, PaymentState, String, String, String, String, String, String, SupportedLanguage, boolean, boolean, RefundSummary, PaymentSettlementSummary, CardDetails, List, URI, URI, URI, URI, URI, Long, Long, String, ExternalMetadata, Long, Long, AuthorisationSummary, AuthorisationMode, AgreementPaymentType, Exemption); then return Metadata Metadata Empty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentForSearchResult.<init>(String, long, PaymentState, String, String, String, String, String, String, SupportedLanguage, boolean, boolean, RefundSummary, PaymentSettlementSummary, CardDetails, List, URI, URI, URI, URI, URI, Long, Long, String, ExternalMetadata, Long, Long, AuthorisationSummary, AuthorisationMode, AgreementPaymentType, Exemption)"
  })
  void testNewPaymentForSearchResult_thenReturnMetadataMetadataEmpty() {
    // Arrange
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
    PaymentConnectorResponseLink paymentConnectorResponseLink =
        new PaymentConnectorResponseLink("GET", "GET", "GET", "GET", new HashMap<>());
    links.add(paymentConnectorResponseLink);
    URI selfLink = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    URI paymentEventsLink = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    URI paymentCancelLink = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    URI paymentRefundsLink = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    URI paymentCaptureUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    ExternalMetadata externalMetadata = new ExternalMetadata(new HashMap<>());
    AuthorisationSummary authorisationSummary = new AuthorisationSummary();

    // Act
    PaymentForSearchResult actualPaymentForSearchResult =
        new PaymentForSearchResult(
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
            selfLink,
            paymentEventsLink,
            paymentCancelLink,
            paymentRefundsLink,
            paymentCaptureUri,
            1L,
            1L,
            "42",
            externalMetadata,
            1L,
            1L,
            authorisationSummary,
            AuthorisationMode.WEB,
            AgreementPaymentType.INSTALMENT,
            new Exemption());

    // Assert
    assertTrue(actualPaymentForSearchResult.getMetadata().getMetadata().isEmpty());
    assertSame(state, actualPaymentForSearchResult.getState());
  }

  /**
   * Test {@link PaymentForSearchResult#PaymentForSearchResult(String, long, PaymentState, String,
   * String, String, String, String, String, SupportedLanguage, boolean, boolean, RefundSummary,
   * PaymentSettlementSummary, CardDetails, List, URI, URI, URI, URI, URI, Long, Long, String,
   * ExternalMetadata, Long, Long, AuthorisationSummary, AuthorisationMode, AgreementPaymentType,
   * Exemption)}.
   *
   * <ul>
   *   <li>Then return Metadata Metadata Empty.
   * </ul>
   *
   * <p>Method under test: {@link PaymentForSearchResult#PaymentForSearchResult(String, long,
   * PaymentState, String, String, String, String, String, String, SupportedLanguage, boolean,
   * boolean, RefundSummary, PaymentSettlementSummary, CardDetails, List, URI, URI, URI, URI, URI,
   * Long, Long, String, ExternalMetadata, Long, Long, AuthorisationSummary, AuthorisationMode,
   * AgreementPaymentType, Exemption)}
   */
  @Test
  @DisplayName(
      "Test new PaymentForSearchResult(String, long, PaymentState, String, String, String, String, String, String, SupportedLanguage, boolean, boolean, RefundSummary, PaymentSettlementSummary, CardDetails, List, URI, URI, URI, URI, URI, Long, Long, String, ExternalMetadata, Long, Long, AuthorisationSummary, AuthorisationMode, AgreementPaymentType, Exemption); then return Metadata Metadata Empty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentForSearchResult.<init>(String, long, PaymentState, String, String, String, String, String, String, SupportedLanguage, boolean, boolean, RefundSummary, PaymentSettlementSummary, CardDetails, List, URI, URI, URI, URI, URI, Long, Long, String, ExternalMetadata, Long, Long, AuthorisationSummary, AuthorisationMode, AgreementPaymentType, Exemption)"
  })
  void testNewPaymentForSearchResult_thenReturnMetadataMetadataEmpty2() {
    // Arrange
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
    PaymentConnectorResponseLink paymentConnectorResponseLink =
        new PaymentConnectorResponseLink("GET", "GET", "GET", "GET", new HashMap<>());
    links.add(paymentConnectorResponseLink);
    PaymentConnectorResponseLink paymentConnectorResponseLink2 =
        new PaymentConnectorResponseLink("GET", "GET", "GET", "GET", new HashMap<>());
    links.add(paymentConnectorResponseLink2);
    URI selfLink = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    URI paymentEventsLink = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    URI paymentCancelLink = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    URI paymentRefundsLink = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    URI paymentCaptureUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    ExternalMetadata externalMetadata = new ExternalMetadata(new HashMap<>());
    AuthorisationSummary authorisationSummary = new AuthorisationSummary();

    // Act
    PaymentForSearchResult actualPaymentForSearchResult =
        new PaymentForSearchResult(
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
            selfLink,
            paymentEventsLink,
            paymentCancelLink,
            paymentRefundsLink,
            paymentCaptureUri,
            1L,
            1L,
            "42",
            externalMetadata,
            1L,
            1L,
            authorisationSummary,
            AuthorisationMode.WEB,
            AgreementPaymentType.INSTALMENT,
            new Exemption());

    // Assert
    assertTrue(actualPaymentForSearchResult.getMetadata().getMetadata().isEmpty());
    assertSame(state, actualPaymentForSearchResult.getState());
  }

  /**
   * Test {@link PaymentForSearchResult#valueOf(TransactionResponse, URI, URI, URI, URI, URI)}.
   *
   * <p>Method under test: {@link PaymentForSearchResult#valueOf(TransactionResponse, URI, URI, URI,
   * URI, URI)}
   */
  @Test
  @DisplayName("Test valueOf(TransactionResponse, URI, URI, URI, URI, URI)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentForSearchResult PaymentForSearchResult.valueOf(TransactionResponse, URI, URI, URI, URI, URI)"
  })
  void testValueOf() {
    // Arrange
    ArrayList<PaymentConnectorResponseLink> paymentConnectorResponseLinkList = new ArrayList<>();
    PaymentConnectorResponseLink paymentConnectorResponseLink =
        new PaymentConnectorResponseLink("capture", "Href", "Method", "Type", new HashMap<>());
    paymentConnectorResponseLinkList.add(paymentConnectorResponseLink);

    TransactionResponse paymentResult = mock(TransactionResponse.class);
    when(paymentResult.getLinks()).thenReturn(paymentConnectorResponseLinkList);
    AuthorisationSummary authorisationSummary = new AuthorisationSummary(new ThreeDSecure(true));
    when(paymentResult.getAuthorisationSummary()).thenReturn(authorisationSummary);
    when(paymentResult.getState()).thenReturn(new PaymentState("Status", false));
    when(paymentResult.getDelayedCapture()).thenReturn(true);
    when(paymentResult.isMoto()).thenReturn(true);
    when(paymentResult.getAmount()).thenReturn(10L);
    when(paymentResult.getCorporateCardSurcharge()).thenReturn(1L);
    when(paymentResult.getFee()).thenReturn(1L);
    when(paymentResult.getNetAmount()).thenReturn(1L);
    when(paymentResult.getTotalAmount()).thenReturn(1L);
    when(paymentResult.getCreatedDate()).thenReturn("2020-03-01");
    when(paymentResult.getDescription()).thenReturn("The characteristics of someone or something");
    when(paymentResult.getEmail()).thenReturn("jane.doe@example.org");
    when(paymentResult.getGatewayTransactionId()).thenReturn("42");
    when(paymentResult.getPaymentProvider()).thenReturn("Payment Provider");
    when(paymentResult.getReference()).thenReturn("Reference");
    when(paymentResult.getReturnUrl()).thenReturn("https://example.org/example");
    when(paymentResult.getTransactionId()).thenReturn("42");
    Optional<ExternalMetadata> ofResult = Optional.of(new ExternalMetadata(new HashMap<>()));
    when(paymentResult.getMetadata()).thenReturn(ofResult);
    Optional<Wallet> ofResult2 = Optional.of(Wallet.APPLE_PAY);
    when(paymentResult.getWalletType()).thenReturn(ofResult2);
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
    when(paymentResult.getCardDetailsFromResponse()).thenReturn(cardDetailsFromResponse);
    when(paymentResult.getExemption()).thenReturn(new Exemption());
    PaymentSettlementSummary paymentSettlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    when(paymentResult.getSettlementSummary()).thenReturn(paymentSettlementSummary);
    when(paymentResult.getRefundSummary()).thenReturn(new RefundSummary("Status", 10L, 10L));
    when(paymentResult.getAgreementPaymentType()).thenReturn(AgreementPaymentType.INSTALMENT);
    when(paymentResult.getAuthorisationMode()).thenReturn(AuthorisationMode.WEB);
    when(paymentResult.getLanguage()).thenReturn(SupportedLanguage.ENGLISH);

    // Act
    PaymentForSearchResult actualValueOfResult =
        PaymentForSearchResult.valueOf(
            paymentResult,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    // Assert
    verify(paymentResult).getAgreementPaymentType();
    verify(paymentResult).getAmount();
    verify(paymentResult).getAuthorisationMode();
    verify(paymentResult).getAuthorisationSummary();
    verify(paymentResult, atLeast(1)).getCardDetailsFromResponse();
    verify(paymentResult).getCorporateCardSurcharge();
    verify(paymentResult).getCreatedDate();
    verify(paymentResult).getDelayedCapture();
    verify(paymentResult).getDescription();
    verify(paymentResult).getEmail();
    verify(paymentResult).getExemption();
    verify(paymentResult).getFee();
    verify(paymentResult).getGatewayTransactionId();
    verify(paymentResult).getLanguage();
    verify(paymentResult).getLinks();
    verify(paymentResult).getMetadata();
    verify(paymentResult).getNetAmount();
    verify(paymentResult).getPaymentProvider();
    verify(paymentResult).getReference();
    verify(paymentResult).getRefundSummary();
    verify(paymentResult).getReturnUrl();
    verify(paymentResult).getSettlementSummary();
    verify(paymentResult).getState();
    verify(paymentResult).getTotalAmount();
    verify(paymentResult).getTransactionId();
    verify(paymentResult).getWalletType();
    verify(paymentResult).isMoto();
    Optional<String> walletType = actualValueOfResult.getCardDetails().get().getWalletType();
    assertEquals("Apple Pay", walletType.get());
    assertTrue(walletType.isPresent());
    assertSame(authorisationSummary, actualValueOfResult.getAuthorisationSummary());
  }

  /**
   * Test {@link PaymentForSearchResult#valueOf(TransactionResponse, URI, URI, URI, URI, URI)}.
   *
   * <p>Method under test: {@link PaymentForSearchResult#valueOf(TransactionResponse, URI, URI, URI,
   * URI, URI)}
   */
  @Test
  @DisplayName("Test valueOf(TransactionResponse, URI, URI, URI, URI, URI)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentForSearchResult PaymentForSearchResult.valueOf(TransactionResponse, URI, URI, URI, URI, URI)"
  })
  void testValueOf2() {
    // Arrange
    ArrayList<PaymentConnectorResponseLink> paymentConnectorResponseLinkList = new ArrayList<>();
    PaymentConnectorResponseLink paymentConnectorResponseLink =
        new PaymentConnectorResponseLink("GET", "GET", "GET", "GET", new HashMap<>());
    paymentConnectorResponseLinkList.add(paymentConnectorResponseLink);
    PaymentConnectorResponseLink paymentConnectorResponseLink2 =
        new PaymentConnectorResponseLink("capture", "Href", "Method", "Type", new HashMap<>());
    paymentConnectorResponseLinkList.add(paymentConnectorResponseLink2);

    TransactionResponse paymentResult = mock(TransactionResponse.class);
    when(paymentResult.getLinks()).thenReturn(paymentConnectorResponseLinkList);
    when(paymentResult.getAuthorisationSummary())
        .thenReturn(new AuthorisationSummary(new ThreeDSecure(false)));
    when(paymentResult.getState()).thenReturn(new PaymentState("Status", false));
    when(paymentResult.getDelayedCapture()).thenReturn(true);
    when(paymentResult.isMoto()).thenReturn(true);
    when(paymentResult.getAmount()).thenReturn(10L);
    when(paymentResult.getCorporateCardSurcharge()).thenReturn(1L);
    when(paymentResult.getFee()).thenReturn(1L);
    when(paymentResult.getNetAmount()).thenReturn(1L);
    when(paymentResult.getTotalAmount()).thenReturn(1L);
    when(paymentResult.getCreatedDate()).thenReturn("2020-03-01");
    when(paymentResult.getDescription()).thenReturn("The characteristics of someone or something");
    when(paymentResult.getEmail()).thenReturn("jane.doe@example.org");
    when(paymentResult.getGatewayTransactionId()).thenReturn("42");
    when(paymentResult.getPaymentProvider()).thenReturn("Payment Provider");
    when(paymentResult.getReference()).thenReturn("Reference");
    when(paymentResult.getReturnUrl()).thenReturn("https://example.org/example");
    when(paymentResult.getTransactionId()).thenReturn("42");
    Optional<ExternalMetadata> ofResult = Optional.of(new ExternalMetadata(new HashMap<>()));
    when(paymentResult.getMetadata()).thenReturn(ofResult);
    Optional<Wallet> ofResult2 = Optional.of(Wallet.APPLE_PAY);
    when(paymentResult.getWalletType()).thenReturn(ofResult2);
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
    when(paymentResult.getCardDetailsFromResponse()).thenReturn(cardDetailsFromResponse);
    when(paymentResult.getExemption()).thenReturn(new Exemption());
    PaymentSettlementSummary paymentSettlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    when(paymentResult.getSettlementSummary()).thenReturn(paymentSettlementSummary);
    when(paymentResult.getRefundSummary()).thenReturn(new RefundSummary("Status", 10L, 10L));
    when(paymentResult.getAgreementPaymentType()).thenReturn(AgreementPaymentType.INSTALMENT);
    when(paymentResult.getAuthorisationMode()).thenReturn(AuthorisationMode.WEB);
    when(paymentResult.getLanguage()).thenReturn(SupportedLanguage.ENGLISH);

    // Act
    PaymentForSearchResult actualValueOfResult =
        PaymentForSearchResult.valueOf(
            paymentResult,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    // Assert
    verify(paymentResult).getAgreementPaymentType();
    verify(paymentResult).getAmount();
    verify(paymentResult).getAuthorisationMode();
    verify(paymentResult).getAuthorisationSummary();
    verify(paymentResult, atLeast(1)).getCardDetailsFromResponse();
    verify(paymentResult).getCorporateCardSurcharge();
    verify(paymentResult).getCreatedDate();
    verify(paymentResult).getDelayedCapture();
    verify(paymentResult).getDescription();
    verify(paymentResult).getEmail();
    verify(paymentResult).getExemption();
    verify(paymentResult).getFee();
    verify(paymentResult).getGatewayTransactionId();
    verify(paymentResult).getLanguage();
    verify(paymentResult).getLinks();
    verify(paymentResult).getMetadata();
    verify(paymentResult).getNetAmount();
    verify(paymentResult).getPaymentProvider();
    verify(paymentResult).getReference();
    verify(paymentResult).getRefundSummary();
    verify(paymentResult).getReturnUrl();
    verify(paymentResult).getSettlementSummary();
    verify(paymentResult).getState();
    verify(paymentResult).getTotalAmount();
    verify(paymentResult).getTransactionId();
    verify(paymentResult).getWalletType();
    verify(paymentResult).isMoto();
    PaymentLinksForSearch links = actualValueOfResult.getLinks();
    Link capture = links.getCapture();
    assertTrue(capture instanceof PostLink);
    Optional<String> walletType = actualValueOfResult.getCardDetails().get().getWalletType();
    assertEquals("Apple Pay", walletType.get());
    PostLink cancel = links.getCancel();
    assertEquals("POST", cancel.getMethod());
    assertNull(cancel.getType());
    assertNull(cancel.getParams());
    assertTrue(walletType.isPresent());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        cancel.getHref());
    assertEquals(cancel, capture);
  }

  /**
   * Test {@link PaymentForSearchResult#valueOf(TransactionResponse, URI, URI, URI, URI, URI)}.
   *
   * <ul>
   *   <li>Given {@code AGREEMENT}.
   *   <li>Then return Links Capture Method is {@code POST}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentForSearchResult#valueOf(TransactionResponse, URI, URI, URI,
   * URI, URI)}
   */
  @Test
  @DisplayName(
      "Test valueOf(TransactionResponse, URI, URI, URI, URI, URI); given 'AGREEMENT'; then return Links Capture Method is 'POST'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentForSearchResult PaymentForSearchResult.valueOf(TransactionResponse, URI, URI, URI, URI, URI)"
  })
  void testValueOf_givenAgreement_thenReturnLinksCaptureMethodIsPost() {
    // Arrange
    ArrayList<PaymentConnectorResponseLink> paymentConnectorResponseLinkList = new ArrayList<>();
    PaymentConnectorResponseLink paymentConnectorResponseLink =
        new PaymentConnectorResponseLink("capture", "Href", "Method", "Type", new HashMap<>());
    paymentConnectorResponseLinkList.add(paymentConnectorResponseLink);

    TransactionResponse paymentResult = mock(TransactionResponse.class);
    when(paymentResult.getLinks()).thenReturn(paymentConnectorResponseLinkList);
    when(paymentResult.getAuthorisationSummary())
        .thenReturn(new AuthorisationSummary(new ThreeDSecure(false)));
    when(paymentResult.getState()).thenReturn(new PaymentState("Status", false));
    when(paymentResult.getDelayedCapture()).thenReturn(true);
    when(paymentResult.isMoto()).thenReturn(true);
    when(paymentResult.getAmount()).thenReturn(10L);
    when(paymentResult.getCorporateCardSurcharge()).thenReturn(1L);
    when(paymentResult.getFee()).thenReturn(1L);
    when(paymentResult.getNetAmount()).thenReturn(1L);
    when(paymentResult.getTotalAmount()).thenReturn(1L);
    when(paymentResult.getCreatedDate()).thenReturn("2020-03-01");
    when(paymentResult.getDescription()).thenReturn("The characteristics of someone or something");
    when(paymentResult.getEmail()).thenReturn("jane.doe@example.org");
    when(paymentResult.getGatewayTransactionId()).thenReturn("42");
    when(paymentResult.getPaymentProvider()).thenReturn("Payment Provider");
    when(paymentResult.getReference()).thenReturn("Reference");
    when(paymentResult.getReturnUrl()).thenReturn("https://example.org/example");
    when(paymentResult.getTransactionId()).thenReturn("42");
    Optional<ExternalMetadata> ofResult = Optional.of(new ExternalMetadata(new HashMap<>()));
    when(paymentResult.getMetadata()).thenReturn(ofResult);
    Optional<Wallet> ofResult2 = Optional.of(Wallet.APPLE_PAY);
    when(paymentResult.getWalletType()).thenReturn(ofResult2);
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
    when(paymentResult.getCardDetailsFromResponse()).thenReturn(cardDetailsFromResponse);
    when(paymentResult.getExemption()).thenReturn(new Exemption());
    PaymentSettlementSummary paymentSettlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    when(paymentResult.getSettlementSummary()).thenReturn(paymentSettlementSummary);
    when(paymentResult.getRefundSummary()).thenReturn(new RefundSummary("Status", 10L, 10L));
    when(paymentResult.getAgreementPaymentType()).thenReturn(AgreementPaymentType.INSTALMENT);
    when(paymentResult.getAuthorisationMode()).thenReturn(AuthorisationMode.AGREEMENT);
    when(paymentResult.getLanguage()).thenReturn(SupportedLanguage.ENGLISH);

    // Act
    PaymentForSearchResult actualValueOfResult =
        PaymentForSearchResult.valueOf(
            paymentResult,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    // Assert
    verify(paymentResult).getAgreementPaymentType();
    verify(paymentResult).getAmount();
    verify(paymentResult).getAuthorisationMode();
    verify(paymentResult).getAuthorisationSummary();
    verify(paymentResult, atLeast(1)).getCardDetailsFromResponse();
    verify(paymentResult).getCorporateCardSurcharge();
    verify(paymentResult).getCreatedDate();
    verify(paymentResult).getDelayedCapture();
    verify(paymentResult).getDescription();
    verify(paymentResult).getEmail();
    verify(paymentResult).getExemption();
    verify(paymentResult).getFee();
    verify(paymentResult).getGatewayTransactionId();
    verify(paymentResult).getLanguage();
    verify(paymentResult).getLinks();
    verify(paymentResult).getMetadata();
    verify(paymentResult).getNetAmount();
    verify(paymentResult).getPaymentProvider();
    verify(paymentResult).getReference();
    verify(paymentResult).getRefundSummary();
    verify(paymentResult).getReturnUrl();
    verify(paymentResult).getSettlementSummary();
    verify(paymentResult).getState();
    verify(paymentResult).getTotalAmount();
    verify(paymentResult).getTransactionId();
    verify(paymentResult).getWalletType();
    verify(paymentResult).isMoto();
    Link capture = actualValueOfResult.getLinks().getCapture();
    assertTrue(capture instanceof PostLink);
    assertEquals("POST", capture.getMethod());
    assertNull(((PostLink) capture).getType());
    assertNull(((PostLink) capture).getParams());
    assertEquals(AuthorisationMode.AGREEMENT, actualValueOfResult.getAuthorisationMode());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        capture.getHref());
  }

  /**
   * Test {@link PaymentForSearchResult#valueOf(TransactionResponse, URI, URI, URI, URI, URI)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.
   *   <li>Then return Links Capture is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentForSearchResult#valueOf(TransactionResponse, URI, URI, URI,
   * URI, URI)}
   */
  @Test
  @DisplayName(
      "Test valueOf(TransactionResponse, URI, URI, URI, URI, URI); given ArrayList(); then return Links Capture is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentForSearchResult PaymentForSearchResult.valueOf(TransactionResponse, URI, URI, URI, URI, URI)"
  })
  void testValueOf_givenArrayList_thenReturnLinksCaptureIsNull() {
    // Arrange
    TransactionResponse paymentResult = mock(TransactionResponse.class);
    when(paymentResult.getDelayedCapture()).thenReturn(true);
    when(paymentResult.isMoto()).thenReturn(true);
    when(paymentResult.getAmount()).thenReturn(10L);
    when(paymentResult.getCorporateCardSurcharge()).thenReturn(1L);
    when(paymentResult.getFee()).thenReturn(1L);
    when(paymentResult.getNetAmount()).thenReturn(1L);
    when(paymentResult.getTotalAmount()).thenReturn(1L);
    when(paymentResult.getCreatedDate()).thenReturn("2020-03-01");
    when(paymentResult.getDescription()).thenReturn("The characteristics of someone or something");
    when(paymentResult.getEmail()).thenReturn("jane.doe@example.org");
    when(paymentResult.getGatewayTransactionId()).thenReturn("42");
    when(paymentResult.getPaymentProvider()).thenReturn("Payment Provider");
    when(paymentResult.getReference()).thenReturn("Reference");
    when(paymentResult.getReturnUrl()).thenReturn("https://example.org/example");
    when(paymentResult.getTransactionId()).thenReturn("42");
    when(paymentResult.getLinks()).thenReturn(new ArrayList<>());
    Optional<ExternalMetadata> ofResult = Optional.of(new ExternalMetadata(new HashMap<>()));
    when(paymentResult.getMetadata()).thenReturn(ofResult);
    Optional<Wallet> ofResult2 = Optional.of(Wallet.APPLE_PAY);
    when(paymentResult.getWalletType()).thenReturn(ofResult2);
    when(paymentResult.getAuthorisationSummary()).thenReturn(new AuthorisationSummary());
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
    when(paymentResult.getCardDetailsFromResponse()).thenReturn(cardDetailsFromResponse);
    when(paymentResult.getExemption()).thenReturn(new Exemption());
    PaymentSettlementSummary paymentSettlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    when(paymentResult.getSettlementSummary()).thenReturn(paymentSettlementSummary);
    PaymentState paymentState = new PaymentState("Status", true);
    when(paymentResult.getState()).thenReturn(paymentState);
    when(paymentResult.getRefundSummary()).thenReturn(new RefundSummary("Status", 10L, 10L));
    when(paymentResult.getAgreementPaymentType()).thenReturn(AgreementPaymentType.INSTALMENT);
    when(paymentResult.getAuthorisationMode()).thenReturn(AuthorisationMode.WEB);
    when(paymentResult.getLanguage()).thenReturn(SupportedLanguage.ENGLISH);

    // Act
    PaymentForSearchResult actualValueOfResult =
        PaymentForSearchResult.valueOf(
            paymentResult,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    // Assert
    verify(paymentResult).getAgreementPaymentType();
    verify(paymentResult).getAmount();
    verify(paymentResult).getAuthorisationMode();
    verify(paymentResult).getAuthorisationSummary();
    verify(paymentResult, atLeast(1)).getCardDetailsFromResponse();
    verify(paymentResult).getCorporateCardSurcharge();
    verify(paymentResult).getCreatedDate();
    verify(paymentResult).getDelayedCapture();
    verify(paymentResult).getDescription();
    verify(paymentResult).getEmail();
    verify(paymentResult).getExemption();
    verify(paymentResult).getFee();
    verify(paymentResult).getGatewayTransactionId();
    verify(paymentResult).getLanguage();
    verify(paymentResult).getLinks();
    verify(paymentResult).getMetadata();
    verify(paymentResult).getNetAmount();
    verify(paymentResult).getPaymentProvider();
    verify(paymentResult).getReference();
    verify(paymentResult).getRefundSummary();
    verify(paymentResult).getReturnUrl();
    verify(paymentResult).getSettlementSummary();
    verify(paymentResult).getState();
    verify(paymentResult).getTotalAmount();
    verify(paymentResult).getTransactionId();
    verify(paymentResult).getWalletType();
    verify(paymentResult).isMoto();
    PaymentLinksForSearch links = actualValueOfResult.getLinks();
    assertNull(links.getCapture());
    assertNull(links.getCancel());
    PaymentState state = actualValueOfResult.getState();
    assertTrue(state.isFinished());
    assertSame(paymentState, state);
  }

  /**
   * Test {@link PaymentForSearchResult#valueOf(TransactionResponse, URI, URI, URI, URI, URI)}.
   *
   * <ul>
   *   <li>Given {@link AuthorisationSummary#AuthorisationSummary(ThreeDSecure)} with threeDSecure
   *       is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentForSearchResult#valueOf(TransactionResponse, URI, URI, URI,
   * URI, URI)}
   */
  @Test
  @DisplayName(
      "Test valueOf(TransactionResponse, URI, URI, URI, URI, URI); given AuthorisationSummary(ThreeDSecure) with threeDSecure is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentForSearchResult PaymentForSearchResult.valueOf(TransactionResponse, URI, URI, URI, URI, URI)"
  })
  void testValueOf_givenAuthorisationSummaryWithThreeDSecureIsNull() {
    // Arrange
    ArrayList<PaymentConnectorResponseLink> paymentConnectorResponseLinkList = new ArrayList<>();
    PaymentConnectorResponseLink paymentConnectorResponseLink =
        new PaymentConnectorResponseLink("capture", "Href", "Method", "Type", new HashMap<>());
    paymentConnectorResponseLinkList.add(paymentConnectorResponseLink);

    TransactionResponse paymentResult = mock(TransactionResponse.class);
    when(paymentResult.getLinks()).thenReturn(paymentConnectorResponseLinkList);
    when(paymentResult.getAuthorisationSummary()).thenReturn(new AuthorisationSummary(null));
    when(paymentResult.getState()).thenReturn(new PaymentState("Status", false));
    when(paymentResult.getDelayedCapture()).thenReturn(true);
    when(paymentResult.isMoto()).thenReturn(true);
    when(paymentResult.getAmount()).thenReturn(10L);
    when(paymentResult.getCorporateCardSurcharge()).thenReturn(1L);
    when(paymentResult.getFee()).thenReturn(1L);
    when(paymentResult.getNetAmount()).thenReturn(1L);
    when(paymentResult.getTotalAmount()).thenReturn(1L);
    when(paymentResult.getCreatedDate()).thenReturn("2020-03-01");
    when(paymentResult.getDescription()).thenReturn("The characteristics of someone or something");
    when(paymentResult.getEmail()).thenReturn("jane.doe@example.org");
    when(paymentResult.getGatewayTransactionId()).thenReturn("42");
    when(paymentResult.getPaymentProvider()).thenReturn("Payment Provider");
    when(paymentResult.getReference()).thenReturn("Reference");
    when(paymentResult.getReturnUrl()).thenReturn("https://example.org/example");
    when(paymentResult.getTransactionId()).thenReturn("42");
    Optional<ExternalMetadata> ofResult = Optional.of(new ExternalMetadata(new HashMap<>()));
    when(paymentResult.getMetadata()).thenReturn(ofResult);
    Optional<Wallet> ofResult2 = Optional.of(Wallet.APPLE_PAY);
    when(paymentResult.getWalletType()).thenReturn(ofResult2);
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
    when(paymentResult.getCardDetailsFromResponse()).thenReturn(cardDetailsFromResponse);
    when(paymentResult.getExemption()).thenReturn(new Exemption());
    PaymentSettlementSummary paymentSettlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    when(paymentResult.getSettlementSummary()).thenReturn(paymentSettlementSummary);
    when(paymentResult.getRefundSummary()).thenReturn(new RefundSummary("Status", 10L, 10L));
    when(paymentResult.getAgreementPaymentType()).thenReturn(AgreementPaymentType.INSTALMENT);
    when(paymentResult.getAuthorisationMode()).thenReturn(AuthorisationMode.WEB);
    when(paymentResult.getLanguage()).thenReturn(SupportedLanguage.ENGLISH);

    // Act
    PaymentForSearchResult actualValueOfResult =
        PaymentForSearchResult.valueOf(
            paymentResult,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    // Assert
    verify(paymentResult).getAgreementPaymentType();
    verify(paymentResult).getAmount();
    verify(paymentResult).getAuthorisationMode();
    verify(paymentResult).getAuthorisationSummary();
    verify(paymentResult, atLeast(1)).getCardDetailsFromResponse();
    verify(paymentResult).getCorporateCardSurcharge();
    verify(paymentResult).getCreatedDate();
    verify(paymentResult).getDelayedCapture();
    verify(paymentResult).getDescription();
    verify(paymentResult).getEmail();
    verify(paymentResult).getExemption();
    verify(paymentResult).getFee();
    verify(paymentResult).getGatewayTransactionId();
    verify(paymentResult).getLanguage();
    verify(paymentResult).getLinks();
    verify(paymentResult).getMetadata();
    verify(paymentResult).getNetAmount();
    verify(paymentResult).getPaymentProvider();
    verify(paymentResult).getReference();
    verify(paymentResult).getRefundSummary();
    verify(paymentResult).getReturnUrl();
    verify(paymentResult).getSettlementSummary();
    verify(paymentResult).getState();
    verify(paymentResult).getTotalAmount();
    verify(paymentResult).getTransactionId();
    verify(paymentResult).getWalletType();
    verify(paymentResult).isMoto();
    PaymentLinksForSearch links = actualValueOfResult.getLinks();
    Link capture = links.getCapture();
    assertTrue(capture instanceof PostLink);
    Optional<String> walletType = actualValueOfResult.getCardDetails().get().getWalletType();
    assertEquals("Apple Pay", walletType.get());
    PostLink cancel = links.getCancel();
    assertEquals("POST", cancel.getMethod());
    assertNull(cancel.getType());
    assertNull(cancel.getParams());
    assertTrue(walletType.isPresent());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        cancel.getHref());
    assertEquals(cancel, capture);
  }

  /**
   * Test {@link PaymentForSearchResult#valueOf(TransactionResponse, URI, URI, URI, URI, URI)}.
   *
   * <ul>
   *   <li>Given empty.
   *   <li>Then return not CardDetails WalletType Present.
   * </ul>
   *
   * <p>Method under test: {@link PaymentForSearchResult#valueOf(TransactionResponse, URI, URI, URI,
   * URI, URI)}
   */
  @Test
  @DisplayName(
      "Test valueOf(TransactionResponse, URI, URI, URI, URI, URI); given empty; then return not CardDetails WalletType Present")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentForSearchResult PaymentForSearchResult.valueOf(TransactionResponse, URI, URI, URI, URI, URI)"
  })
  void testValueOf_givenEmpty_thenReturnNotCardDetailsWalletTypePresent() {
    // Arrange
    ArrayList<PaymentConnectorResponseLink> paymentConnectorResponseLinkList = new ArrayList<>();
    PaymentConnectorResponseLink paymentConnectorResponseLink =
        new PaymentConnectorResponseLink("capture", "Href", "Method", "Type", new HashMap<>());
    paymentConnectorResponseLinkList.add(paymentConnectorResponseLink);

    TransactionResponse paymentResult = mock(TransactionResponse.class);
    when(paymentResult.getLinks()).thenReturn(paymentConnectorResponseLinkList);
    when(paymentResult.getAuthorisationSummary())
        .thenReturn(new AuthorisationSummary(new ThreeDSecure(false)));
    when(paymentResult.getState()).thenReturn(new PaymentState("Status", false));
    when(paymentResult.getDelayedCapture()).thenReturn(true);
    when(paymentResult.isMoto()).thenReturn(true);
    when(paymentResult.getAmount()).thenReturn(10L);
    when(paymentResult.getCorporateCardSurcharge()).thenReturn(1L);
    when(paymentResult.getFee()).thenReturn(1L);
    when(paymentResult.getNetAmount()).thenReturn(1L);
    when(paymentResult.getTotalAmount()).thenReturn(1L);
    when(paymentResult.getCreatedDate()).thenReturn("2020-03-01");
    when(paymentResult.getDescription()).thenReturn("The characteristics of someone or something");
    when(paymentResult.getEmail()).thenReturn("jane.doe@example.org");
    when(paymentResult.getGatewayTransactionId()).thenReturn("42");
    when(paymentResult.getPaymentProvider()).thenReturn("Payment Provider");
    when(paymentResult.getReference()).thenReturn("Reference");
    when(paymentResult.getReturnUrl()).thenReturn("https://example.org/example");
    when(paymentResult.getTransactionId()).thenReturn("42");
    Optional<ExternalMetadata> ofResult = Optional.of(new ExternalMetadata(new HashMap<>()));
    when(paymentResult.getMetadata()).thenReturn(ofResult);
    Optional<Wallet> emptyResult = Optional.empty();
    when(paymentResult.getWalletType()).thenReturn(emptyResult);
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
    when(paymentResult.getCardDetailsFromResponse()).thenReturn(cardDetailsFromResponse);
    when(paymentResult.getExemption()).thenReturn(new Exemption());
    PaymentSettlementSummary paymentSettlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    when(paymentResult.getSettlementSummary()).thenReturn(paymentSettlementSummary);
    when(paymentResult.getRefundSummary()).thenReturn(new RefundSummary("Status", 10L, 10L));
    when(paymentResult.getAgreementPaymentType()).thenReturn(AgreementPaymentType.INSTALMENT);
    when(paymentResult.getAuthorisationMode()).thenReturn(AuthorisationMode.WEB);
    when(paymentResult.getLanguage()).thenReturn(SupportedLanguage.ENGLISH);

    // Act
    PaymentForSearchResult actualValueOfResult =
        PaymentForSearchResult.valueOf(
            paymentResult,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    // Assert
    verify(paymentResult).getAgreementPaymentType();
    verify(paymentResult).getAmount();
    verify(paymentResult).getAuthorisationMode();
    verify(paymentResult).getAuthorisationSummary();
    verify(paymentResult).getCardDetailsFromResponse();
    verify(paymentResult).getCorporateCardSurcharge();
    verify(paymentResult).getCreatedDate();
    verify(paymentResult).getDelayedCapture();
    verify(paymentResult).getDescription();
    verify(paymentResult).getEmail();
    verify(paymentResult).getExemption();
    verify(paymentResult).getFee();
    verify(paymentResult).getGatewayTransactionId();
    verify(paymentResult).getLanguage();
    verify(paymentResult).getLinks();
    verify(paymentResult).getMetadata();
    verify(paymentResult).getNetAmount();
    verify(paymentResult).getPaymentProvider();
    verify(paymentResult).getReference();
    verify(paymentResult).getRefundSummary();
    verify(paymentResult).getReturnUrl();
    verify(paymentResult).getSettlementSummary();
    verify(paymentResult).getState();
    verify(paymentResult).getTotalAmount();
    verify(paymentResult).getTransactionId();
    verify(paymentResult).getWalletType();
    verify(paymentResult).isMoto();
    PaymentLinksForSearch links = actualValueOfResult.getLinks();
    Link capture = links.getCapture();
    assertTrue(capture instanceof PostLink);
    PostLink cancel = links.getCancel();
    assertEquals("POST", cancel.getMethod());
    assertNull(cancel.getType());
    assertNull(cancel.getParams());
    assertFalse(actualValueOfResult.getCardDetails().get().getWalletType().isPresent());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        cancel.getHref());
    assertEquals(cancel, capture);
  }

  /**
   * Test {@link PaymentForSearchResult#valueOf(TransactionResponse, URI, URI, URI, URI, URI)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link TransactionResponse} {@link TransactionResponse#getAuthorisationSummary()}
   *       return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentForSearchResult#valueOf(TransactionResponse, URI, URI, URI,
   * URI, URI)}
   */
  @Test
  @DisplayName(
      "Test valueOf(TransactionResponse, URI, URI, URI, URI, URI); given 'null'; when TransactionResponse getAuthorisationSummary() return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentForSearchResult PaymentForSearchResult.valueOf(TransactionResponse, URI, URI, URI, URI, URI)"
  })
  void testValueOf_givenNull_whenTransactionResponseGetAuthorisationSummaryReturnNull() {
    // Arrange
    ArrayList<PaymentConnectorResponseLink> paymentConnectorResponseLinkList = new ArrayList<>();
    PaymentConnectorResponseLink paymentConnectorResponseLink =
        new PaymentConnectorResponseLink("capture", "Href", "Method", "Type", new HashMap<>());
    paymentConnectorResponseLinkList.add(paymentConnectorResponseLink);

    TransactionResponse paymentResult = mock(TransactionResponse.class);
    when(paymentResult.getLinks()).thenReturn(paymentConnectorResponseLinkList);
    when(paymentResult.getAuthorisationSummary()).thenReturn(null);
    when(paymentResult.getState()).thenReturn(new PaymentState("Status", false));
    when(paymentResult.getDelayedCapture()).thenReturn(true);
    when(paymentResult.isMoto()).thenReturn(true);
    when(paymentResult.getAmount()).thenReturn(10L);
    when(paymentResult.getCorporateCardSurcharge()).thenReturn(1L);
    when(paymentResult.getFee()).thenReturn(1L);
    when(paymentResult.getNetAmount()).thenReturn(1L);
    when(paymentResult.getTotalAmount()).thenReturn(1L);
    when(paymentResult.getCreatedDate()).thenReturn("2020-03-01");
    when(paymentResult.getDescription()).thenReturn("The characteristics of someone or something");
    when(paymentResult.getEmail()).thenReturn("jane.doe@example.org");
    when(paymentResult.getGatewayTransactionId()).thenReturn("42");
    when(paymentResult.getPaymentProvider()).thenReturn("Payment Provider");
    when(paymentResult.getReference()).thenReturn("Reference");
    when(paymentResult.getReturnUrl()).thenReturn("https://example.org/example");
    when(paymentResult.getTransactionId()).thenReturn("42");
    Optional<ExternalMetadata> ofResult = Optional.of(new ExternalMetadata(new HashMap<>()));
    when(paymentResult.getMetadata()).thenReturn(ofResult);
    Optional<Wallet> ofResult2 = Optional.of(Wallet.APPLE_PAY);
    when(paymentResult.getWalletType()).thenReturn(ofResult2);
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
    when(paymentResult.getCardDetailsFromResponse()).thenReturn(cardDetailsFromResponse);
    when(paymentResult.getExemption()).thenReturn(new Exemption());
    PaymentSettlementSummary paymentSettlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    when(paymentResult.getSettlementSummary()).thenReturn(paymentSettlementSummary);
    when(paymentResult.getRefundSummary()).thenReturn(new RefundSummary("Status", 10L, 10L));
    when(paymentResult.getAgreementPaymentType()).thenReturn(AgreementPaymentType.INSTALMENT);
    when(paymentResult.getAuthorisationMode()).thenReturn(AuthorisationMode.WEB);
    when(paymentResult.getLanguage()).thenReturn(SupportedLanguage.ENGLISH);

    // Act
    PaymentForSearchResult actualValueOfResult =
        PaymentForSearchResult.valueOf(
            paymentResult,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    // Assert
    verify(paymentResult).getAgreementPaymentType();
    verify(paymentResult).getAmount();
    verify(paymentResult).getAuthorisationMode();
    verify(paymentResult).getAuthorisationSummary();
    verify(paymentResult, atLeast(1)).getCardDetailsFromResponse();
    verify(paymentResult).getCorporateCardSurcharge();
    verify(paymentResult).getCreatedDate();
    verify(paymentResult).getDelayedCapture();
    verify(paymentResult).getDescription();
    verify(paymentResult).getEmail();
    verify(paymentResult).getExemption();
    verify(paymentResult).getFee();
    verify(paymentResult).getGatewayTransactionId();
    verify(paymentResult).getLanguage();
    verify(paymentResult).getLinks();
    verify(paymentResult).getMetadata();
    verify(paymentResult).getNetAmount();
    verify(paymentResult).getPaymentProvider();
    verify(paymentResult).getReference();
    verify(paymentResult).getRefundSummary();
    verify(paymentResult).getReturnUrl();
    verify(paymentResult).getSettlementSummary();
    verify(paymentResult).getState();
    verify(paymentResult).getTotalAmount();
    verify(paymentResult).getTransactionId();
    verify(paymentResult).getWalletType();
    verify(paymentResult).isMoto();
    PaymentLinksForSearch links = actualValueOfResult.getLinks();
    Link capture = links.getCapture();
    assertTrue(capture instanceof PostLink);
    Optional<String> walletType = actualValueOfResult.getCardDetails().get().getWalletType();
    assertEquals("Apple Pay", walletType.get());
    PostLink cancel = links.getCancel();
    assertEquals("POST", cancel.getMethod());
    assertNull(cancel.getType());
    assertNull(cancel.getParams());
    assertTrue(walletType.isPresent());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        cancel.getHref());
    assertEquals(cancel, capture);
  }

  /**
   * Test {@link PaymentForSearchResult#valueOf(TransactionResponse, URI, URI, URI, URI, URI)}.
   *
   * <ul>
   *   <li>Then return CardBrand is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentForSearchResult#valueOf(TransactionResponse, URI, URI, URI,
   * URI, URI)}
   */
  @Test
  @DisplayName(
      "Test valueOf(TransactionResponse, URI, URI, URI, URI, URI); then return CardBrand is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentForSearchResult PaymentForSearchResult.valueOf(TransactionResponse, URI, URI, URI, URI, URI)"
  })
  void testValueOf_thenReturnCardBrandIsNull() {
    // Arrange
    ArrayList<PaymentConnectorResponseLink> paymentConnectorResponseLinkList = new ArrayList<>();
    PaymentConnectorResponseLink paymentConnectorResponseLink =
        new PaymentConnectorResponseLink("capture", "Href", "Method", "Type", new HashMap<>());
    paymentConnectorResponseLinkList.add(paymentConnectorResponseLink);

    TransactionResponse paymentResult = mock(TransactionResponse.class);
    when(paymentResult.getLinks()).thenReturn(paymentConnectorResponseLinkList);
    when(paymentResult.getAuthorisationSummary())
        .thenReturn(new AuthorisationSummary(new ThreeDSecure(false)));
    when(paymentResult.getState()).thenReturn(new PaymentState("Status", false));
    when(paymentResult.getDelayedCapture()).thenReturn(true);
    when(paymentResult.isMoto()).thenReturn(true);
    when(paymentResult.getAmount()).thenReturn(10L);
    when(paymentResult.getCorporateCardSurcharge()).thenReturn(1L);
    when(paymentResult.getFee()).thenReturn(1L);
    when(paymentResult.getNetAmount()).thenReturn(1L);
    when(paymentResult.getTotalAmount()).thenReturn(1L);
    when(paymentResult.getCreatedDate()).thenReturn("2020-03-01");
    when(paymentResult.getDescription()).thenReturn("The characteristics of someone or something");
    when(paymentResult.getEmail()).thenReturn("jane.doe@example.org");
    when(paymentResult.getGatewayTransactionId()).thenReturn("42");
    when(paymentResult.getPaymentProvider()).thenReturn("Payment Provider");
    when(paymentResult.getReference()).thenReturn("Reference");
    when(paymentResult.getReturnUrl()).thenReturn("https://example.org/example");
    when(paymentResult.getTransactionId()).thenReturn("42");
    Optional<ExternalMetadata> ofResult = Optional.of(new ExternalMetadata(new HashMap<>()));
    when(paymentResult.getMetadata()).thenReturn(ofResult);
    Optional<Wallet> ofResult2 = Optional.of(Wallet.APPLE_PAY);
    when(paymentResult.getWalletType()).thenReturn(ofResult2);
    when(paymentResult.getCardDetailsFromResponse()).thenReturn(null);
    when(paymentResult.getExemption()).thenReturn(new Exemption());
    PaymentSettlementSummary paymentSettlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    when(paymentResult.getSettlementSummary()).thenReturn(paymentSettlementSummary);
    when(paymentResult.getRefundSummary()).thenReturn(new RefundSummary("Status", 10L, 10L));
    when(paymentResult.getAgreementPaymentType()).thenReturn(AgreementPaymentType.INSTALMENT);
    when(paymentResult.getAuthorisationMode()).thenReturn(AuthorisationMode.WEB);
    when(paymentResult.getLanguage()).thenReturn(SupportedLanguage.ENGLISH);

    // Act
    PaymentForSearchResult actualValueOfResult =
        PaymentForSearchResult.valueOf(
            paymentResult,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    // Assert
    verify(paymentResult).getAgreementPaymentType();
    verify(paymentResult).getAmount();
    verify(paymentResult).getAuthorisationMode();
    verify(paymentResult).getAuthorisationSummary();
    verify(paymentResult, atLeast(1)).getCardDetailsFromResponse();
    verify(paymentResult).getCorporateCardSurcharge();
    verify(paymentResult).getCreatedDate();
    verify(paymentResult).getDelayedCapture();
    verify(paymentResult).getDescription();
    verify(paymentResult).getEmail();
    verify(paymentResult).getExemption();
    verify(paymentResult).getFee();
    verify(paymentResult).getGatewayTransactionId();
    verify(paymentResult).getLanguage();
    verify(paymentResult).getLinks();
    verify(paymentResult).getMetadata();
    verify(paymentResult).getNetAmount();
    verify(paymentResult).getPaymentProvider();
    verify(paymentResult).getReference();
    verify(paymentResult).getRefundSummary();
    verify(paymentResult).getReturnUrl();
    verify(paymentResult).getSettlementSummary();
    verify(paymentResult).getState();
    verify(paymentResult).getTotalAmount();
    verify(paymentResult).getTransactionId();
    verify(paymentResult).getWalletType();
    verify(paymentResult).isMoto();
    PaymentLinksForSearch links = actualValueOfResult.getLinks();
    Link capture = links.getCapture();
    assertTrue(capture instanceof PostLink);
    PostLink cancel = links.getCancel();
    assertEquals("POST", cancel.getMethod());
    assertNull(actualValueOfResult.getCardBrand());
    assertNull(cancel.getType());
    assertNull(cancel.getParams());
    assertFalse(actualValueOfResult.getCardDetails().isPresent());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        cancel.getHref());
    assertEquals(cancel, capture);
  }

  /**
   * Test {@link PaymentForSearchResult#valueOf(TransactionResponse, URI, URI, URI, URI, URI)}.
   *
   * <ul>
   *   <li>Then return CardDetails WalletType is {@code Apple Pay}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentForSearchResult#valueOf(TransactionResponse, URI, URI, URI,
   * URI, URI)}
   */
  @Test
  @DisplayName(
      "Test valueOf(TransactionResponse, URI, URI, URI, URI, URI); then return CardDetails WalletType is 'Apple Pay'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentForSearchResult PaymentForSearchResult.valueOf(TransactionResponse, URI, URI, URI, URI, URI)"
  })
  void testValueOf_thenReturnCardDetailsWalletTypeIsApplePay() {
    // Arrange
    ArrayList<PaymentConnectorResponseLink> paymentConnectorResponseLinkList = new ArrayList<>();
    PaymentConnectorResponseLink paymentConnectorResponseLink =
        new PaymentConnectorResponseLink("capture", "Href", "Method", "Type", new HashMap<>());
    paymentConnectorResponseLinkList.add(paymentConnectorResponseLink);

    TransactionResponse paymentResult = mock(TransactionResponse.class);
    when(paymentResult.getLinks()).thenReturn(paymentConnectorResponseLinkList);
    when(paymentResult.getAuthorisationSummary())
        .thenReturn(new AuthorisationSummary(new ThreeDSecure(false)));
    when(paymentResult.getState()).thenReturn(new PaymentState("Status", false));
    when(paymentResult.getDelayedCapture()).thenReturn(true);
    when(paymentResult.isMoto()).thenReturn(true);
    when(paymentResult.getAmount()).thenReturn(10L);
    when(paymentResult.getCorporateCardSurcharge()).thenReturn(1L);
    when(paymentResult.getFee()).thenReturn(1L);
    when(paymentResult.getNetAmount()).thenReturn(1L);
    when(paymentResult.getTotalAmount()).thenReturn(1L);
    when(paymentResult.getCreatedDate()).thenReturn("2020-03-01");
    when(paymentResult.getDescription()).thenReturn("The characteristics of someone or something");
    when(paymentResult.getEmail()).thenReturn("jane.doe@example.org");
    when(paymentResult.getGatewayTransactionId()).thenReturn("42");
    when(paymentResult.getPaymentProvider()).thenReturn("Payment Provider");
    when(paymentResult.getReference()).thenReturn("Reference");
    when(paymentResult.getReturnUrl()).thenReturn("https://example.org/example");
    when(paymentResult.getTransactionId()).thenReturn("42");
    Optional<ExternalMetadata> ofResult = Optional.of(new ExternalMetadata(new HashMap<>()));
    when(paymentResult.getMetadata()).thenReturn(ofResult);
    Optional<Wallet> ofResult2 = Optional.of(Wallet.APPLE_PAY);
    when(paymentResult.getWalletType()).thenReturn(ofResult2);
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
    when(paymentResult.getCardDetailsFromResponse()).thenReturn(cardDetailsFromResponse);
    when(paymentResult.getExemption()).thenReturn(new Exemption());
    PaymentSettlementSummary paymentSettlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    when(paymentResult.getSettlementSummary()).thenReturn(paymentSettlementSummary);
    when(paymentResult.getRefundSummary()).thenReturn(new RefundSummary("Status", 10L, 10L));
    when(paymentResult.getAgreementPaymentType()).thenReturn(AgreementPaymentType.INSTALMENT);
    when(paymentResult.getAuthorisationMode()).thenReturn(AuthorisationMode.WEB);
    when(paymentResult.getLanguage()).thenReturn(SupportedLanguage.ENGLISH);

    // Act
    PaymentForSearchResult actualValueOfResult =
        PaymentForSearchResult.valueOf(
            paymentResult,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    // Assert
    verify(paymentResult).getAgreementPaymentType();
    verify(paymentResult).getAmount();
    verify(paymentResult).getAuthorisationMode();
    verify(paymentResult).getAuthorisationSummary();
    verify(paymentResult, atLeast(1)).getCardDetailsFromResponse();
    verify(paymentResult).getCorporateCardSurcharge();
    verify(paymentResult).getCreatedDate();
    verify(paymentResult).getDelayedCapture();
    verify(paymentResult).getDescription();
    verify(paymentResult).getEmail();
    verify(paymentResult).getExemption();
    verify(paymentResult).getFee();
    verify(paymentResult).getGatewayTransactionId();
    verify(paymentResult).getLanguage();
    verify(paymentResult).getLinks();
    verify(paymentResult).getMetadata();
    verify(paymentResult).getNetAmount();
    verify(paymentResult).getPaymentProvider();
    verify(paymentResult).getReference();
    verify(paymentResult).getRefundSummary();
    verify(paymentResult).getReturnUrl();
    verify(paymentResult).getSettlementSummary();
    verify(paymentResult).getState();
    verify(paymentResult).getTotalAmount();
    verify(paymentResult).getTransactionId();
    verify(paymentResult).getWalletType();
    verify(paymentResult).isMoto();
    PaymentLinksForSearch links = actualValueOfResult.getLinks();
    Link capture = links.getCapture();
    assertTrue(capture instanceof PostLink);
    Optional<String> walletType = actualValueOfResult.getCardDetails().get().getWalletType();
    assertEquals("Apple Pay", walletType.get());
    PostLink cancel = links.getCancel();
    assertEquals("POST", cancel.getMethod());
    assertNull(cancel.getType());
    assertNull(cancel.getParams());
    assertTrue(walletType.isPresent());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        cancel.getHref());
    assertEquals(cancel, capture);
  }
}
