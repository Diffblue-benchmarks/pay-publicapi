package uk.gov.pay.api.model.links;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.net.URI;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.pay.api.model.Address;
import uk.gov.pay.api.model.AuthorisationSummary;
import uk.gov.pay.api.model.CardDetails;
import uk.gov.pay.api.model.Charge;
import uk.gov.pay.api.model.Exemption;
import uk.gov.pay.api.model.PaymentConnectorResponseLink;
import uk.gov.pay.api.model.PaymentSettlementSummary;
import uk.gov.pay.api.model.PaymentState;
import uk.gov.pay.api.model.RefundSummary;
import uk.gov.pay.api.model.links.PaymentWithAllLinks.PaymentWithAllLinksBuilder;
import uk.gov.service.payments.commons.model.AgreementPaymentType;
import uk.gov.service.payments.commons.model.AuthorisationMode;
import uk.gov.service.payments.commons.model.SupportedLanguage;
import uk.gov.service.payments.commons.model.charge.ExternalMetadata;

public class PaymentWithAllLinksDiffblueTest {
  /**
   * Test {@link PaymentWithAllLinks#getLinks()}.
   *
   * <p>Method under test: {@link PaymentWithAllLinks#getLinks()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentLinks PaymentWithAllLinks.getLinks()"})
  public void testGetLinks() {
    // Arrange
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

    // Act
    PaymentLinks actualLinks =
        withSettlementSummaryResult
            .withState(new PaymentState("Status", true))
            .withTotalAmount(1L)
            .build()
            .getLinks();

    // Assert
    Link events = actualLinks.getEvents();
    assertEquals("GET", events.getMethod());
    assertNull(actualLinks.getNextUrl());
    assertNull(actualLinks.getAuthUrlPost());
    assertNull(actualLinks.getCancel());
    assertNull(actualLinks.getCapture());
    assertNull(actualLinks.getNextUrlPost());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        events.getHref());
  }

  /**
   * Test PaymentWithAllLinksBuilder {@link PaymentWithAllLinksBuilder#build()}.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PaymentWithAllLinksBuilder#build()}
   *   <li>default or parameterless constructor of {@link PaymentWithAllLinksBuilder}
   *   <li>{@link PaymentWithAllLinksBuilder#withAgreementId(String)}
   *   <li>{@link PaymentWithAllLinksBuilder#withAgreementPaymentType(AgreementPaymentType)}
   *   <li>{@link PaymentWithAllLinksBuilder#withAmount(long)}
   *   <li>{@link PaymentWithAllLinksBuilder#withAuthorisationMode(AuthorisationMode)}
   *   <li>{@link PaymentWithAllLinksBuilder#withAuthorisationSummary(AuthorisationSummary)}
   *   <li>{@link PaymentWithAllLinksBuilder#withCardDetails(CardDetails)}
   *   <li>{@link PaymentWithAllLinksBuilder#withChargeId(String)}
   *   <li>{@link PaymentWithAllLinksBuilder#withCorporateCardSurcharge(Long)}
   *   <li>{@link PaymentWithAllLinksBuilder#withCreatedDate(String)}
   *   <li>{@link PaymentWithAllLinksBuilder#withDelayedCapture(boolean)}
   *   <li>{@link PaymentWithAllLinksBuilder#withDescription(String)}
   *   <li>{@link PaymentWithAllLinksBuilder#withEmail(String)}
   *   <li>{@link PaymentWithAllLinksBuilder#withExemption(Exemption)}
   *   <li>{@link PaymentWithAllLinksBuilder#withFee(Long)}
   *   <li>{@link PaymentWithAllLinksBuilder#withLanguage(SupportedLanguage)}
   *   <li>{@link PaymentWithAllLinksBuilder#withMetadata(ExternalMetadata)}
   *   <li>{@link PaymentWithAllLinksBuilder#withMoto(boolean)}
   *   <li>{@link PaymentWithAllLinksBuilder#withNetAmount(Long)}
   *   <li>{@link PaymentWithAllLinksBuilder#withPaymentAuthorisationUri(URI)}
   *   <li>{@link PaymentWithAllLinksBuilder#withPaymentCancelUri(URI)}
   *   <li>{@link PaymentWithAllLinksBuilder#withPaymentCaptureUri(URI)}
   *   <li>{@link PaymentWithAllLinksBuilder#withPaymentConnectorResponseLinks(List)}
   *   <li>{@link PaymentWithAllLinksBuilder#withPaymentEventsUri(URI)}
   *   <li>{@link PaymentWithAllLinksBuilder#withPaymentProvider(String)}
   *   <li>{@link PaymentWithAllLinksBuilder#withPaymentRefundsUri(URI)}
   *   <li>{@link PaymentWithAllLinksBuilder#withProviderId(String)}
   *   <li>{@link PaymentWithAllLinksBuilder#withReference(String)}
   *   <li>{@link PaymentWithAllLinksBuilder#withRefundSummary(RefundSummary)}
   *   <li>{@link PaymentWithAllLinksBuilder#withReturnUrl(String)}
   *   <li>{@link PaymentWithAllLinksBuilder#withSelfLink(URI)}
   *   <li>{@link PaymentWithAllLinksBuilder#withSettlementSummary(PaymentSettlementSummary)}
   *   <li>{@link PaymentWithAllLinksBuilder#withState(PaymentState)}
   *   <li>{@link PaymentWithAllLinksBuilder#withTotalAmount(Long)}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentWithAllLinksBuilder.<init>()",
    "PaymentWithAllLinks PaymentWithAllLinksBuilder.build()",
    "PaymentWithAllLinksBuilder PaymentWithAllLinksBuilder.withAgreementId(String)",
    "PaymentWithAllLinksBuilder PaymentWithAllLinksBuilder.withAgreementPaymentType(AgreementPaymentType)",
    "PaymentWithAllLinksBuilder PaymentWithAllLinksBuilder.withAmount(long)",
    "PaymentWithAllLinksBuilder PaymentWithAllLinksBuilder.withAuthorisationMode(AuthorisationMode)",
    "PaymentWithAllLinksBuilder PaymentWithAllLinksBuilder.withAuthorisationSummary(AuthorisationSummary)",
    "PaymentWithAllLinksBuilder PaymentWithAllLinksBuilder.withCardDetails(CardDetails)",
    "PaymentWithAllLinksBuilder PaymentWithAllLinksBuilder.withChargeId(String)",
    "PaymentWithAllLinksBuilder PaymentWithAllLinksBuilder.withCorporateCardSurcharge(Long)",
    "PaymentWithAllLinksBuilder PaymentWithAllLinksBuilder.withCreatedDate(String)",
    "PaymentWithAllLinksBuilder PaymentWithAllLinksBuilder.withDelayedCapture(boolean)",
    "PaymentWithAllLinksBuilder PaymentWithAllLinksBuilder.withDescription(String)",
    "PaymentWithAllLinksBuilder PaymentWithAllLinksBuilder.withEmail(String)",
    "PaymentWithAllLinksBuilder PaymentWithAllLinksBuilder.withExemption(Exemption)",
    "PaymentWithAllLinksBuilder PaymentWithAllLinksBuilder.withFee(Long)",
    "PaymentWithAllLinksBuilder PaymentWithAllLinksBuilder.withLanguage(SupportedLanguage)",
    "PaymentWithAllLinksBuilder PaymentWithAllLinksBuilder.withMetadata(ExternalMetadata)",
    "PaymentWithAllLinksBuilder PaymentWithAllLinksBuilder.withMoto(boolean)",
    "PaymentWithAllLinksBuilder PaymentWithAllLinksBuilder.withNetAmount(Long)",
    "PaymentWithAllLinksBuilder PaymentWithAllLinksBuilder.withPaymentAuthorisationUri(URI)",
    "PaymentWithAllLinksBuilder PaymentWithAllLinksBuilder.withPaymentCancelUri(URI)",
    "PaymentWithAllLinksBuilder PaymentWithAllLinksBuilder.withPaymentCaptureUri(URI)",
    "PaymentWithAllLinksBuilder PaymentWithAllLinksBuilder.withPaymentConnectorResponseLinks(List)",
    "PaymentWithAllLinksBuilder PaymentWithAllLinksBuilder.withPaymentEventsUri(URI)",
    "PaymentWithAllLinksBuilder PaymentWithAllLinksBuilder.withPaymentProvider(String)",
    "PaymentWithAllLinksBuilder PaymentWithAllLinksBuilder.withPaymentRefundsUri(URI)",
    "PaymentWithAllLinksBuilder PaymentWithAllLinksBuilder.withProviderId(String)",
    "PaymentWithAllLinksBuilder PaymentWithAllLinksBuilder.withReference(String)",
    "PaymentWithAllLinksBuilder PaymentWithAllLinksBuilder.withRefundSummary(RefundSummary)",
    "PaymentWithAllLinksBuilder PaymentWithAllLinksBuilder.withReturnUrl(String)",
    "PaymentWithAllLinksBuilder PaymentWithAllLinksBuilder.withSelfLink(URI)",
    "PaymentWithAllLinksBuilder PaymentWithAllLinksBuilder.withSettlementSummary(PaymentSettlementSummary)",
    "PaymentWithAllLinksBuilder PaymentWithAllLinksBuilder.withState(PaymentState)",
    "PaymentWithAllLinksBuilder PaymentWithAllLinksBuilder.withTotalAmount(Long)"
  })
  public void testPaymentWithAllLinksBuilderBuild() {
    // Arrange and Act
    PaymentWithAllLinksBuilder actualWithAuthorisationModeResult =
        new PaymentWithAllLinksBuilder()
            .withAgreementId("42")
            .withAgreementPaymentType(AgreementPaymentType.INSTALMENT)
            .withAmount(10L)
            .withAuthorisationMode(AuthorisationMode.WEB);
    AuthorisationSummary authorisationSummary = new AuthorisationSummary();
    PaymentWithAllLinksBuilder actualWithAuthorisationSummaryResult =
        actualWithAuthorisationModeResult.withAuthorisationSummary(authorisationSummary);
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
    PaymentWithAllLinksBuilder actualWithEmailResult =
        actualWithAuthorisationSummaryResult
            .withCardDetails(cardDetails)
            .withChargeId("42")
            .withCorporateCardSurcharge(1L)
            .withCreatedDate("2020-03-01")
            .withDelayedCapture(true)
            .withDescription("The characteristics of someone or something")
            .withEmail("jane.doe@example.org");
    Exemption exemption = new Exemption();
    PaymentWithAllLinksBuilder actualWithLanguageResult =
        actualWithEmailResult
            .withExemption(exemption)
            .withFee(1L)
            .withLanguage(SupportedLanguage.ENGLISH);
    ExternalMetadata metadata = new ExternalMetadata(new HashMap<>());
    PaymentWithAllLinksBuilder actualWithPaymentCaptureUriResult =
        actualWithLanguageResult
            .withMetadata(metadata)
            .withMoto(true)
            .withNetAmount(1L)
            .withPaymentAuthorisationUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentCancelUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentCaptureUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    PaymentWithAllLinksBuilder actualWithReferenceResult =
        actualWithPaymentCaptureUriResult
            .withPaymentConnectorResponseLinks(new ArrayList<>())
            .withPaymentEventsUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentProvider("Payment Provider")
            .withPaymentRefundsUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withProviderId("42")
            .withReference("Reference");
    RefundSummary refundSummary = new RefundSummary("Status", 10L, 10L);
    PaymentWithAllLinksBuilder actualWithSelfLinkResult =
        actualWithReferenceResult
            .withRefundSummary(refundSummary)
            .withReturnUrl("https://example.org/example")
            .withSelfLink(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    PaymentSettlementSummary settlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    PaymentWithAllLinksBuilder actualWithSettlementSummaryResult =
        actualWithSelfLinkResult.withSettlementSummary(settlementSummary);
    PaymentState state = new PaymentState("Status", true);
    PaymentWithAllLinks actualPaymentWithAllLinks =
        actualWithSettlementSummaryResult.withState(state).withTotalAmount(1L).build();

    // Assert
    assertEquals("2020-03-01", actualPaymentWithAllLinks.getCreatedDate());
    assertEquals("42", actualPaymentWithAllLinks.getAgreementId());
    assertEquals("42", actualPaymentWithAllLinks.getPaymentId());
    assertEquals("42", actualPaymentWithAllLinks.getProviderId());
    assertEquals("Card Brand", actualPaymentWithAllLinks.getCardBrand());
    PaymentLinks links = actualPaymentWithAllLinks.getLinks();
    Link events = links.getEvents();
    assertEquals("GET", events.getMethod());
    assertEquals("Payment Provider", actualPaymentWithAllLinks.getPaymentProvider());
    assertEquals("Reference", actualPaymentWithAllLinks.getReference());
    assertEquals(
        "The characteristics of someone or something", actualPaymentWithAllLinks.getDescription());
    Optional<String> returnUrl = actualPaymentWithAllLinks.getReturnUrl();
    assertEquals("https://example.org/example", returnUrl.get());
    Optional<String> email = actualPaymentWithAllLinks.getEmail();
    assertEquals("jane.doe@example.org", email.get());
    AuthorisationSummary authorisationSummary2 =
        actualPaymentWithAllLinks.getAuthorisationSummary();
    assertNull(authorisationSummary2.getThreeDSecure());
    assertNull(links.getNextUrl());
    assertNull(links.getAuthUrlPost());
    assertNull(links.getCancel());
    assertNull(links.getCapture());
    assertNull(links.getNextUrlPost());
    assertEquals(10L, actualPaymentWithAllLinks.getAmount());
    Optional<Long> corporateCardSurcharge = actualPaymentWithAllLinks.getCorporateCardSurcharge();
    assertEquals(1L, corporateCardSurcharge.get().longValue());
    assertEquals(
        AgreementPaymentType.INSTALMENT, actualPaymentWithAllLinks.getAgreementPaymentType());
    assertEquals(AuthorisationMode.WEB, actualPaymentWithAllLinks.getAuthorisationMode());
    assertEquals(SupportedLanguage.ENGLISH, actualPaymentWithAllLinks.getLanguage());
    Optional<CardDetails> cardDetails2 = actualPaymentWithAllLinks.getCardDetails();
    assertTrue(cardDetails2.isPresent());
    assertTrue(corporateCardSurcharge.isPresent());
    assertTrue(email.isPresent());
    Optional<RefundSummary> refundSummary2 = actualPaymentWithAllLinks.getRefundSummary();
    assertTrue(refundSummary2.isPresent());
    assertTrue(returnUrl.isPresent());
    Optional<PaymentSettlementSummary> settlementSummary2 =
        actualPaymentWithAllLinks.getSettlementSummary();
    assertTrue(settlementSummary2.isPresent());
    assertTrue(actualPaymentWithAllLinks.getDelayedCapture());
    assertTrue(actualPaymentWithAllLinks.getMoto());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        events.getHref());
    assertSame(authorisationSummary, authorisationSummary2);
    assertSame(cardDetails, cardDetails2.get());
    assertSame(exemption, actualPaymentWithAllLinks.getExemption());
    assertSame(settlementSummary, settlementSummary2.get());
    assertSame(state, actualPaymentWithAllLinks.getState());
    assertSame(refundSummary, refundSummary2.get());
    assertSame(metadata, actualPaymentWithAllLinks.getMetadata());
  }

  /**
   * Test {@link PaymentWithAllLinks#valueOf(Charge, URI, URI, URI, URI, URI, URI)}.
   *
   * <p>Method under test: {@link PaymentWithAllLinks#valueOf(Charge, URI, URI, URI, URI, URI, URI)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentWithAllLinks PaymentWithAllLinks.valueOf(Charge, URI, URI, URI, URI, URI, URI)"
  })
  public void testValueOf() {
    // Arrange
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

    Charge paymentConnector =
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

    // Act
    PaymentWithAllLinks actualValueOfResult =
        PaymentWithAllLinks.valueOf(
            paymentConnector,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    // Assert
    PaymentState state2 = actualValueOfResult.getState();
    assertTrue(state2.isFinished());
    assertSame(state, state2);
  }

  /**
   * Test {@link PaymentWithAllLinks#valueOf(Charge, URI, URI, URI, URI, URI, URI)}.
   *
   * <p>Method under test: {@link PaymentWithAllLinks#valueOf(Charge, URI, URI, URI, URI, URI, URI)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentWithAllLinks PaymentWithAllLinks.valueOf(Charge, URI, URI, URI, URI, URI, URI)"
  })
  public void testValueOf2() {
    // Arrange
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

    Charge paymentConnector =
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

    // Act
    PaymentWithAllLinks actualValueOfResult =
        PaymentWithAllLinks.valueOf(
            paymentConnector,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    // Assert
    PaymentState state2 = actualValueOfResult.getState();
    assertTrue(state2.isFinished());
    assertSame(state, state2);
  }

  /**
   * Test {@link PaymentWithAllLinks#valueOf(Charge, URI, URI, URI, URI, URI, URI)}.
   *
   * <ul>
   *   <li>Then return AuthorisationMode is {@code AGREEMENT}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentWithAllLinks#valueOf(Charge, URI, URI, URI, URI, URI, URI)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentWithAllLinks PaymentWithAllLinks.valueOf(Charge, URI, URI, URI, URI, URI, URI)"
  })
  public void testValueOf_thenReturnAuthorisationModeIsAgreement() {
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
    ExternalMetadata metadata = new ExternalMetadata(new HashMap<>());
    AuthorisationSummary authorisationSummary = new AuthorisationSummary();

    Charge paymentConnector =
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
            AuthorisationMode.AGREEMENT,
            AgreementPaymentType.INSTALMENT,
            new Exemption());

    // Act
    PaymentWithAllLinks actualValueOfResult =
        PaymentWithAllLinks.valueOf(
            paymentConnector,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    // Assert
    assertEquals(AuthorisationMode.AGREEMENT, actualValueOfResult.getAuthorisationMode());
  }

  /**
   * Test {@link PaymentWithAllLinks#valueOf(Charge, URI, URI, URI, URI, URI, URI)}.
   *
   * <ul>
   *   <li>Then return Links Cancel Method is {@code POST}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentWithAllLinks#valueOf(Charge, URI, URI, URI, URI, URI, URI)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentWithAllLinks PaymentWithAllLinks.valueOf(Charge, URI, URI, URI, URI, URI, URI)"
  })
  public void testValueOf_thenReturnLinksCancelMethodIsPost() {
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
    ExternalMetadata metadata = new ExternalMetadata(new HashMap<>());
    AuthorisationSummary authorisationSummary = new AuthorisationSummary();

    Charge paymentConnector =
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

    // Act
    PaymentWithAllLinks actualValueOfResult =
        PaymentWithAllLinks.valueOf(
            paymentConnector,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    // Assert
    PostLink cancel = actualValueOfResult.getLinks().getCancel();
    assertEquals("POST", cancel.getMethod());
    assertNull(cancel.getType());
    assertNull(cancel.getParams());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        cancel.getHref());
  }

  /**
   * Test {@link PaymentWithAllLinks#valueOf(Charge, URI, URI, URI, URI, URI, URI)}.
   *
   * <ul>
   *   <li>Then return Links Capture Method is {@code POST}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentWithAllLinks#valueOf(Charge, URI, URI, URI, URI, URI, URI)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentWithAllLinks PaymentWithAllLinks.valueOf(Charge, URI, URI, URI, URI, URI, URI)"
  })
  public void testValueOf_thenReturnLinksCaptureMethodIsPost() {
    // Arrange
    ArrayList<PaymentConnectorResponseLink> links = new ArrayList<>();
    PaymentConnectorResponseLink paymentConnectorResponseLink =
        new PaymentConnectorResponseLink("capture", "GET", "GET", "GET", new HashMap<>());
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

    Charge paymentConnector =
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

    // Act
    PaymentWithAllLinks actualValueOfResult =
        PaymentWithAllLinks.valueOf(
            paymentConnector,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    // Assert
    PostLink capture = actualValueOfResult.getLinks().getCapture();
    assertEquals("POST", capture.getMethod());
    assertNull(capture.getType());
    assertNull(capture.getParams());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        capture.getHref());
  }

  /**
   * Test {@link PaymentWithAllLinks#valueOf(Charge, URI, URI, URI, URI, URI, URI)}.
   *
   * <ul>
   *   <li>When {@link PaymentState#PaymentState(String, boolean)} with {@code Status} and finished
   *       is {@code true}.
   *   <li>Then return State Finished.
   * </ul>
   *
   * <p>Method under test: {@link PaymentWithAllLinks#valueOf(Charge, URI, URI, URI, URI, URI, URI)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentWithAllLinks PaymentWithAllLinks.valueOf(Charge, URI, URI, URI, URI, URI, URI)"
  })
  public void testValueOf_whenPaymentStateWithStatusAndFinishedIsTrue_thenReturnStateFinished() {
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
    ExternalMetadata metadata = new ExternalMetadata(new HashMap<>());
    AuthorisationSummary authorisationSummary = new AuthorisationSummary();

    Charge paymentConnector =
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

    // Act
    PaymentWithAllLinks actualValueOfResult =
        PaymentWithAllLinks.valueOf(
            paymentConnector,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    // Assert
    PaymentState state2 = actualValueOfResult.getState();
    assertTrue(state2.isFinished());
    assertSame(state, state2);
  }

  /**
   * Test {@link PaymentWithAllLinks#getPaymentWithLinks(Charge, URI, URI, URI, URI, URI, URI)}.
   *
   * <p>Method under test: {@link PaymentWithAllLinks#getPaymentWithLinks(Charge, URI, URI, URI,
   * URI, URI, URI)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentWithAllLinks PaymentWithAllLinks.getPaymentWithLinks(Charge, URI, URI, URI, URI, URI, URI)"
  })
  public void testGetPaymentWithLinks() {
    // Arrange
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

    Charge paymentConnector =
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

    // Act
    PaymentWithAllLinks actualPaymentWithLinks =
        PaymentWithAllLinks.getPaymentWithLinks(
            paymentConnector,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    // Assert
    PaymentState state2 = actualPaymentWithLinks.getState();
    assertTrue(state2.isFinished());
    assertSame(state, state2);
  }

  /**
   * Test {@link PaymentWithAllLinks#getPaymentWithLinks(Charge, URI, URI, URI, URI, URI, URI)}.
   *
   * <p>Method under test: {@link PaymentWithAllLinks#getPaymentWithLinks(Charge, URI, URI, URI,
   * URI, URI, URI)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentWithAllLinks PaymentWithAllLinks.getPaymentWithLinks(Charge, URI, URI, URI, URI, URI, URI)"
  })
  public void testGetPaymentWithLinks2() {
    // Arrange
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

    Charge paymentConnector =
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

    // Act
    PaymentWithAllLinks actualPaymentWithLinks =
        PaymentWithAllLinks.getPaymentWithLinks(
            paymentConnector,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    // Assert
    PaymentState state2 = actualPaymentWithLinks.getState();
    assertTrue(state2.isFinished());
    assertSame(state, state2);
  }

  /**
   * Test {@link PaymentWithAllLinks#getPaymentWithLinks(Charge, URI, URI, URI, URI, URI, URI)}.
   *
   * <ul>
   *   <li>Then return AuthorisationMode is {@code AGREEMENT}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentWithAllLinks#getPaymentWithLinks(Charge, URI, URI, URI,
   * URI, URI, URI)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentWithAllLinks PaymentWithAllLinks.getPaymentWithLinks(Charge, URI, URI, URI, URI, URI, URI)"
  })
  public void testGetPaymentWithLinks_thenReturnAuthorisationModeIsAgreement() {
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
    ExternalMetadata metadata = new ExternalMetadata(new HashMap<>());
    AuthorisationSummary authorisationSummary = new AuthorisationSummary();

    Charge paymentConnector =
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
            AuthorisationMode.AGREEMENT,
            AgreementPaymentType.INSTALMENT,
            new Exemption());

    // Act
    PaymentWithAllLinks actualPaymentWithLinks =
        PaymentWithAllLinks.getPaymentWithLinks(
            paymentConnector,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    // Assert
    assertEquals(AuthorisationMode.AGREEMENT, actualPaymentWithLinks.getAuthorisationMode());
  }

  /**
   * Test {@link PaymentWithAllLinks#getPaymentWithLinks(Charge, URI, URI, URI, URI, URI, URI)}.
   *
   * <ul>
   *   <li>Then return Links Cancel Method is {@code POST}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentWithAllLinks#getPaymentWithLinks(Charge, URI, URI, URI,
   * URI, URI, URI)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentWithAllLinks PaymentWithAllLinks.getPaymentWithLinks(Charge, URI, URI, URI, URI, URI, URI)"
  })
  public void testGetPaymentWithLinks_thenReturnLinksCancelMethodIsPost() {
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
    ExternalMetadata metadata = new ExternalMetadata(new HashMap<>());
    AuthorisationSummary authorisationSummary = new AuthorisationSummary();

    Charge paymentConnector =
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

    // Act
    PaymentWithAllLinks actualPaymentWithLinks =
        PaymentWithAllLinks.getPaymentWithLinks(
            paymentConnector,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    // Assert
    PostLink cancel = actualPaymentWithLinks.getLinks().getCancel();
    assertEquals("POST", cancel.getMethod());
    assertNull(cancel.getType());
    assertNull(cancel.getParams());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        cancel.getHref());
  }

  /**
   * Test {@link PaymentWithAllLinks#getPaymentWithLinks(Charge, URI, URI, URI, URI, URI, URI)}.
   *
   * <ul>
   *   <li>Then return Links Capture Method is {@code POST}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentWithAllLinks#getPaymentWithLinks(Charge, URI, URI, URI,
   * URI, URI, URI)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentWithAllLinks PaymentWithAllLinks.getPaymentWithLinks(Charge, URI, URI, URI, URI, URI, URI)"
  })
  public void testGetPaymentWithLinks_thenReturnLinksCaptureMethodIsPost() {
    // Arrange
    ArrayList<PaymentConnectorResponseLink> links = new ArrayList<>();
    PaymentConnectorResponseLink paymentConnectorResponseLink =
        new PaymentConnectorResponseLink("capture", "GET", "GET", "GET", new HashMap<>());
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

    Charge paymentConnector =
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

    // Act
    PaymentWithAllLinks actualPaymentWithLinks =
        PaymentWithAllLinks.getPaymentWithLinks(
            paymentConnector,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    // Assert
    PostLink capture = actualPaymentWithLinks.getLinks().getCapture();
    assertEquals("POST", capture.getMethod());
    assertNull(capture.getType());
    assertNull(capture.getParams());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        capture.getHref());
  }

  /**
   * Test {@link PaymentWithAllLinks#getPaymentWithLinks(Charge, URI, URI, URI, URI, URI, URI)}.
   *
   * <ul>
   *   <li>Then return State Finished.
   * </ul>
   *
   * <p>Method under test: {@link PaymentWithAllLinks#getPaymentWithLinks(Charge, URI, URI, URI,
   * URI, URI, URI)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentWithAllLinks PaymentWithAllLinks.getPaymentWithLinks(Charge, URI, URI, URI, URI, URI, URI)"
  })
  public void testGetPaymentWithLinks_thenReturnStateFinished() {
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
    ExternalMetadata metadata = new ExternalMetadata(new HashMap<>());
    AuthorisationSummary authorisationSummary = new AuthorisationSummary();

    Charge paymentConnector =
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

    // Act
    PaymentWithAllLinks actualPaymentWithLinks =
        PaymentWithAllLinks.getPaymentWithLinks(
            paymentConnector,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    // Assert
    PaymentState state2 = actualPaymentWithLinks.getState();
    assertTrue(state2.isFinished());
    assertSame(state, state2);
  }
}
