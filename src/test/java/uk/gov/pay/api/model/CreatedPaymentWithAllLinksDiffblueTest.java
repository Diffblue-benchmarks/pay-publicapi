package uk.gov.pay.api.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.pay.api.model.CreatedPaymentWithAllLinks.WhenCreated;
import uk.gov.pay.api.model.links.Link;
import uk.gov.pay.api.model.links.PaymentLinks;
import uk.gov.pay.api.model.links.PaymentWithAllLinks;
import uk.gov.pay.api.model.links.PaymentWithAllLinks.PaymentWithAllLinksBuilder;
import uk.gov.service.payments.commons.model.AgreementPaymentType;
import uk.gov.service.payments.commons.model.AuthorisationMode;
import uk.gov.service.payments.commons.model.SupportedLanguage;
import uk.gov.service.payments.commons.model.charge.ExternalMetadata;

public class CreatedPaymentWithAllLinksDiffblueTest {
  /**
   * Test {@link CreatedPaymentWithAllLinks#of(PaymentWithAllLinks, WhenCreated)}.
   *
   * <p>Method under test: {@link CreatedPaymentWithAllLinks#of(PaymentWithAllLinks, WhenCreated)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CreatedPaymentWithAllLinks CreatedPaymentWithAllLinks.of(PaymentWithAllLinks, WhenCreated)"
  })
  public void testOf() {
    // Arrange
    PaymentWithAllLinks payment = mock(PaymentWithAllLinks.class);

    // Act
    CreatedPaymentWithAllLinks actualOfResult =
        CreatedPaymentWithAllLinks.of(payment, WhenCreated.BRAND_NEW);

    // Assert
    assertEquals(WhenCreated.BRAND_NEW, actualOfResult.getWhenCreated());
    assertSame(payment, actualOfResult.getPayment());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CreatedPaymentWithAllLinks#getPayment()}
   *   <li>{@link CreatedPaymentWithAllLinks#getWhenCreated()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PaymentWithAllLinks CreatedPaymentWithAllLinks.getPayment()",
    "WhenCreated CreatedPaymentWithAllLinks.getWhenCreated()"
  })
  public void testGettersAndSetters() {
    // Arrange
    PaymentWithAllLinksBuilder withAuthorisationModeResult =
        new PaymentWithAllLinksBuilder()
            .withAgreementId("42")
            .withAgreementPaymentType(AgreementPaymentType.INSTALMENT)
            .withAmount(10L)
            .withAuthorisationMode(AuthorisationMode.WEB);
    AuthorisationSummary authorisationSummary = new AuthorisationSummary();

    PaymentWithAllLinksBuilder withAuthorisationSummaryResult =
        withAuthorisationModeResult.withAuthorisationSummary(authorisationSummary);
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
    Exemption exemption = new Exemption();

    PaymentWithAllLinksBuilder withLanguageResult =
        withEmailResult
            .withExemption(exemption)
            .withFee(1L)
            .withLanguage(SupportedLanguage.ENGLISH);
    ExternalMetadata metadata = new ExternalMetadata(new HashMap<>());

    PaymentWithAllLinksBuilder withPaymentCaptureUriResult =
        withLanguageResult
            .withMetadata(metadata)
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
    RefundSummary refundSummary = new RefundSummary("Status", 10L, 10L);

    PaymentWithAllLinksBuilder withSelfLinkResult =
        withReferenceResult
            .withRefundSummary(refundSummary)
            .withReturnUrl("https://example.org/example")
            .withSelfLink(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    PaymentSettlementSummary settlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");

    PaymentWithAllLinksBuilder withSettlementSummaryResult =
        withSelfLinkResult.withSettlementSummary(settlementSummary);
    PaymentState state = new PaymentState("Status", true);
    PaymentWithAllLinks payment =
        withSettlementSummaryResult.withState(state).withTotalAmount(1L).build();
    CreatedPaymentWithAllLinks ofResult =
        CreatedPaymentWithAllLinks.of(payment, WhenCreated.BRAND_NEW);

    // Act
    PaymentWithAllLinks actualPayment = ofResult.getPayment();
    WhenCreated actualWhenCreated = ofResult.getWhenCreated();

    // Assert
    assertEquals("2020-03-01", actualPayment.getCreatedDate());
    assertEquals("42", actualPayment.getAgreementId());
    assertEquals("42", actualPayment.getPaymentId());
    assertEquals("42", actualPayment.getProviderId());
    assertEquals("Card Brand", actualPayment.getCardBrand());
    assertEquals("Card Payment", actualPayment.paymentType);
    PaymentLinks links = actualPayment.getLinks();
    Link events = links.getEvents();
    assertEquals("GET", events.getMethod());
    assertEquals("Payment Provider", actualPayment.getPaymentProvider());
    assertEquals("Reference", actualPayment.getReference());
    assertEquals("The characteristics of someone or something", actualPayment.getDescription());
    Optional<String> returnUrl = actualPayment.getReturnUrl();
    assertEquals("https://example.org/example", returnUrl.get());
    assertEquals("https://example.org/example", actualPayment.returnUrl);
    Optional<String> email = actualPayment.getEmail();
    assertEquals("jane.doe@example.org", email.get());
    assertEquals("jane.doe@example.org", actualPayment.email);
    AuthorisationSummary authorisationSummary2 = actualPayment.getAuthorisationSummary();
    assertNull(authorisationSummary2.getThreeDSecure());
    assertNull(links.getNextUrl());
    assertNull(links.getAuthUrlPost());
    assertNull(links.getCancel());
    assertNull(links.getCapture());
    assertNull(links.getNextUrlPost());
    assertEquals(10L, actualPayment.getAmount());
    Optional<Long> corporateCardSurcharge = actualPayment.getCorporateCardSurcharge();
    assertEquals(1L, corporateCardSurcharge.get().longValue());
    assertEquals(WhenCreated.BRAND_NEW, actualWhenCreated);
    assertEquals(AgreementPaymentType.INSTALMENT, actualPayment.getAgreementPaymentType());
    assertEquals(AuthorisationMode.WEB, actualPayment.getAuthorisationMode());
    assertEquals(SupportedLanguage.ENGLISH, actualPayment.getLanguage());
    Optional<CardDetails> cardDetails2 = actualPayment.getCardDetails();
    assertTrue(cardDetails2.isPresent());
    assertTrue(corporateCardSurcharge.isPresent());
    assertTrue(email.isPresent());
    Optional<RefundSummary> refundSummary2 = actualPayment.getRefundSummary();
    assertTrue(refundSummary2.isPresent());
    assertTrue(returnUrl.isPresent());
    Optional<PaymentSettlementSummary> settlementSummary2 = actualPayment.getSettlementSummary();
    assertTrue(settlementSummary2.isPresent());
    assertTrue(actualPayment.getDelayedCapture());
    assertTrue(actualPayment.getMoto());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        events.getHref());
    assertSame(authorisationSummary, authorisationSummary2);
    assertSame(cardDetails, cardDetails2.get());
    assertSame(exemption, actualPayment.getExemption());
    assertSame(settlementSummary, settlementSummary2.get());
    assertSame(state, actualPayment.getState());
    assertSame(refundSummary, refundSummary2.get());
    assertSame(metadata, actualPayment.getMetadata());
  }
}
