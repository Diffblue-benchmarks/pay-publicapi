package uk.gov.pay.api.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.model.links.PaymentWithAllLinks;
import uk.gov.pay.api.model.links.PaymentWithAllLinks.PaymentWithAllLinksBuilder;
import uk.gov.service.payments.commons.model.AgreementPaymentType;
import uk.gov.service.payments.commons.model.AuthorisationMode;
import uk.gov.service.payments.commons.model.SupportedLanguage;
import uk.gov.service.payments.commons.model.charge.ExternalMetadata;

class CardPaymentDiffblueTest {
  /**
   * Test {@link CardPayment#CardPayment(String, long, PaymentState, String, String, String, String,
   * String, String, RefundSummary, PaymentSettlementSummary, CardDetails, SupportedLanguage,
   * boolean, boolean, Long, Long, String, ExternalMetadata, Long, Long, AuthorisationSummary,
   * String, AuthorisationMode, AgreementPaymentType, Exemption)}.
   *
   * <p>Method under test: {@link CardPayment#CardPayment(String, long, PaymentState, String,
   * String, String, String, String, String, RefundSummary, PaymentSettlementSummary, CardDetails,
   * SupportedLanguage, boolean, boolean, Long, Long, String, ExternalMetadata, Long, Long,
   * AuthorisationSummary, String, AuthorisationMode, AgreementPaymentType, Exemption)}
   */
  @Test
  @DisplayName(
      "Test new CardPayment(String, long, PaymentState, String, String, String, String, String, String, RefundSummary, PaymentSettlementSummary, CardDetails, SupportedLanguage, boolean, boolean, Long, Long, String, ExternalMetadata, Long, Long, AuthorisationSummary, String, AuthorisationMode, AgreementPaymentType, Exemption)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CardPayment.<init>(String, long, PaymentState, String, String, String, String, String, String, RefundSummary, PaymentSettlementSummary, CardDetails, SupportedLanguage, boolean, boolean, Long, Long, String, ExternalMetadata, Long, Long, AuthorisationSummary, String, AuthorisationMode, AgreementPaymentType, Exemption)"
  })
  void testNewCardPayment() {
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
    ExternalMetadata metadata = new ExternalMetadata(new HashMap<>());
    AuthorisationSummary authorisationSummary = new AuthorisationSummary();
    Exemption exemption = new Exemption();

    // Act
    CardPayment actualCardPayment =
        new CardPayment(
            "42",
            10L,
            state,
            "https://example.org/example",
            "The characteristics of someone or something",
            "Reference",
            "jane.doe@example.org",
            "Payment Provider",
            "2020-03-01",
            refundSummary,
            settlementSummary,
            cardDetails,
            SupportedLanguage.ENGLISH,
            true,
            true,
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
            exemption);

    // Assert
    assertEquals("2020-03-01", actualCardPayment.getCreatedDate());
    assertEquals("42", actualCardPayment.getAgreementId());
    assertEquals("42", actualCardPayment.getPaymentId());
    assertEquals("42", actualCardPayment.getProviderId());
    assertEquals("Card Brand", actualCardPayment.getCardBrand());
    assertEquals("Card Payment", actualCardPayment.paymentType);
    assertEquals("Payment Provider", actualCardPayment.getPaymentProvider());
    assertEquals("Reference", actualCardPayment.getReference());
    assertEquals("The characteristics of someone or something", actualCardPayment.getDescription());
    assertEquals("https://example.org/example", actualCardPayment.returnUrl);
    assertEquals("jane.doe@example.org", actualCardPayment.email);
    assertEquals(10L, actualCardPayment.getAmount());
    assertEquals(AgreementPaymentType.INSTALMENT, actualCardPayment.getAgreementPaymentType());
    assertEquals(AuthorisationMode.WEB, actualCardPayment.getAuthorisationMode());
    assertEquals(SupportedLanguage.ENGLISH, actualCardPayment.getLanguage());
    assertTrue(actualCardPayment.getDelayedCapture());
    assertTrue(actualCardPayment.getMoto());
    assertSame(authorisationSummary, actualCardPayment.getAuthorisationSummary());
    assertSame(exemption, actualCardPayment.getExemption());
    assertSame(state, actualCardPayment.getState());
    assertSame(metadata, actualCardPayment.getMetadata());
  }

  /**
   * Test {@link CardPayment#getCardBrand()}.
   *
   * <ul>
   *   <li>Then return {@code Card Brand}.
   * </ul>
   *
   * <p>Method under test: {@link CardPayment#getCardBrand()}
   */
  @Test
  @DisplayName("Test getCardBrand(); then return 'Card Brand'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String CardPayment.getCardBrand()"})
  void testGetCardBrand_thenReturnCardBrand() {
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
    ExternalMetadata metadata = new ExternalMetadata(new HashMap<>());
    AuthorisationSummary authorisationSummary = new AuthorisationSummary();

    CardPayment cardPayment =
        new CardPayment(
            "42",
            10L,
            state,
            "https://example.org/example",
            "The characteristics of someone or something",
            "Reference",
            "jane.doe@example.org",
            "Payment Provider",
            "2020-03-01",
            refundSummary,
            settlementSummary,
            cardDetails,
            SupportedLanguage.ENGLISH,
            true,
            true,
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

    // Act and Assert
    assertEquals("Card Brand", cardPayment.getCardBrand());
  }

  /**
   * Test {@link CardPayment#getCardBrand()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link CardPayment#getCardBrand()}
   */
  @Test
  @DisplayName("Test getCardBrand(); then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String CardPayment.getCardBrand()"})
  void testGetCardBrand_thenReturnNull() {
    // Arrange
    PaymentState state = new PaymentState("Status", true);
    RefundSummary refundSummary = new RefundSummary("Status", 10L, 10L);
    PaymentSettlementSummary settlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");
    ExternalMetadata metadata = new ExternalMetadata(new HashMap<>());
    AuthorisationSummary authorisationSummary = new AuthorisationSummary();

    CardPayment cardPayment =
        new CardPayment(
            "42",
            10L,
            state,
            "https://example.org/example",
            "The characteristics of someone or something",
            "Reference",
            "jane.doe@example.org",
            "Payment Provider",
            "2020-03-01",
            refundSummary,
            settlementSummary,
            null,
            SupportedLanguage.ENGLISH,
            true,
            true,
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

    // Act and Assert
    assertNull(cardPayment.getCardBrand());
  }

  /**
   * Test {@link CardPayment#getRefundSummary()}.
   *
   * <p>Method under test: {@link CardPayment#getRefundSummary()}
   */
  @Test
  @DisplayName("Test getRefundSummary()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional CardPayment.getRefundSummary()"})
  void testGetRefundSummary() {
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
    ExternalMetadata metadata = new ExternalMetadata(new HashMap<>());
    AuthorisationSummary authorisationSummary = new AuthorisationSummary();

    CardPayment cardPayment =
        new CardPayment(
            "42",
            10L,
            state,
            "https://example.org/example",
            "The characteristics of someone or something",
            "Reference",
            "jane.doe@example.org",
            "Payment Provider",
            "2020-03-01",
            refundSummary,
            settlementSummary,
            cardDetails,
            SupportedLanguage.ENGLISH,
            true,
            true,
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
    Optional<RefundSummary> actualRefundSummary = cardPayment.getRefundSummary();

    // Assert
    assertTrue(actualRefundSummary.isPresent());
    assertSame(refundSummary, actualRefundSummary.get());
  }

  /**
   * Test {@link CardPayment#getSettlementSummary()}.
   *
   * <p>Method under test: {@link CardPayment#getSettlementSummary()}
   */
  @Test
  @DisplayName("Test getSettlementSummary()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional CardPayment.getSettlementSummary()"})
  void testGetSettlementSummary() {
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
    ExternalMetadata metadata = new ExternalMetadata(new HashMap<>());
    AuthorisationSummary authorisationSummary = new AuthorisationSummary();

    CardPayment cardPayment =
        new CardPayment(
            "42",
            10L,
            state,
            "https://example.org/example",
            "The characteristics of someone or something",
            "Reference",
            "jane.doe@example.org",
            "Payment Provider",
            "2020-03-01",
            refundSummary,
            settlementSummary,
            cardDetails,
            SupportedLanguage.ENGLISH,
            true,
            true,
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
    Optional<PaymentSettlementSummary> actualSettlementSummary = cardPayment.getSettlementSummary();

    // Assert
    assertTrue(actualSettlementSummary.isPresent());
    assertSame(settlementSummary, actualSettlementSummary.get());
  }

  /**
   * Test {@link CardPayment#getCardDetails()}.
   *
   * <p>Method under test: {@link CardPayment#getCardDetails()}
   */
  @Test
  @DisplayName("Test getCardDetails()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional CardPayment.getCardDetails()"})
  void testGetCardDetails() {
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
    ExternalMetadata metadata = new ExternalMetadata(new HashMap<>());
    AuthorisationSummary authorisationSummary = new AuthorisationSummary();

    CardPayment cardPayment =
        new CardPayment(
            "42",
            10L,
            state,
            "https://example.org/example",
            "The characteristics of someone or something",
            "Reference",
            "jane.doe@example.org",
            "Payment Provider",
            "2020-03-01",
            refundSummary,
            settlementSummary,
            cardDetails,
            SupportedLanguage.ENGLISH,
            true,
            true,
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
    Optional<CardDetails> actualCardDetails = cardPayment.getCardDetails();

    // Assert
    assertTrue(actualCardDetails.isPresent());
    assertSame(cardDetails, actualCardDetails.get());
  }

  /**
   * Test {@link CardPayment#getCorporateCardSurcharge()}.
   *
   * <p>Method under test: {@link CardPayment#getCorporateCardSurcharge()}
   */
  @Test
  @DisplayName("Test getCorporateCardSurcharge()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional CardPayment.getCorporateCardSurcharge()"})
  void testGetCorporateCardSurcharge() {
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
    ExternalMetadata metadata = new ExternalMetadata(new HashMap<>());
    AuthorisationSummary authorisationSummary = new AuthorisationSummary();

    CardPayment cardPayment =
        new CardPayment(
            "42",
            10L,
            state,
            "https://example.org/example",
            "The characteristics of someone or something",
            "Reference",
            "jane.doe@example.org",
            "Payment Provider",
            "2020-03-01",
            refundSummary,
            settlementSummary,
            cardDetails,
            SupportedLanguage.ENGLISH,
            true,
            true,
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
    Optional<Long> actualCorporateCardSurcharge = cardPayment.getCorporateCardSurcharge();

    // Assert
    assertEquals(1L, actualCorporateCardSurcharge.get().longValue());
    assertTrue(actualCorporateCardSurcharge.isPresent());
  }

  /**
   * Test {@link CardPayment#getFee()}.
   *
   * <p>Method under test: {@link CardPayment#getFee()}
   */
  @Test
  @DisplayName("Test getFee()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional CardPayment.getFee()"})
  void testGetFee() {
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
    ExternalMetadata metadata = new ExternalMetadata(new HashMap<>());
    AuthorisationSummary authorisationSummary = new AuthorisationSummary();

    CardPayment cardPayment =
        new CardPayment(
            "42",
            10L,
            state,
            "https://example.org/example",
            "The characteristics of someone or something",
            "Reference",
            "jane.doe@example.org",
            "Payment Provider",
            "2020-03-01",
            refundSummary,
            settlementSummary,
            cardDetails,
            SupportedLanguage.ENGLISH,
            true,
            true,
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
    Optional<Long> actualFee = cardPayment.getFee();

    // Assert
    assertEquals(1L, actualFee.get().longValue());
    assertTrue(actualFee.isPresent());
  }

  /**
   * Test {@link CardPayment#getNetAmount()}.
   *
   * <p>Method under test: {@link CardPayment#getNetAmount()}
   */
  @Test
  @DisplayName("Test getNetAmount()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional CardPayment.getNetAmount()"})
  void testGetNetAmount() {
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
    ExternalMetadata metadata = new ExternalMetadata(new HashMap<>());
    AuthorisationSummary authorisationSummary = new AuthorisationSummary();

    CardPayment cardPayment =
        new CardPayment(
            "42",
            10L,
            state,
            "https://example.org/example",
            "The characteristics of someone or something",
            "Reference",
            "jane.doe@example.org",
            "Payment Provider",
            "2020-03-01",
            refundSummary,
            settlementSummary,
            cardDetails,
            SupportedLanguage.ENGLISH,
            true,
            true,
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
    Optional<Long> actualNetAmount = cardPayment.getNetAmount();

    // Assert
    assertEquals(1L, actualNetAmount.get().longValue());
    assertTrue(actualNetAmount.isPresent());
  }

  /**
   * Test {@link CardPayment#getTotalAmount()}.
   *
   * <p>Method under test: {@link CardPayment#getTotalAmount()}
   */
  @Test
  @DisplayName("Test getTotalAmount()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional CardPayment.getTotalAmount()"})
  void testGetTotalAmount() {
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
    ExternalMetadata metadata = new ExternalMetadata(new HashMap<>());
    AuthorisationSummary authorisationSummary = new AuthorisationSummary();

    CardPayment cardPayment =
        new CardPayment(
            "42",
            10L,
            state,
            "https://example.org/example",
            "The characteristics of someone or something",
            "Reference",
            "jane.doe@example.org",
            "Payment Provider",
            "2020-03-01",
            refundSummary,
            settlementSummary,
            cardDetails,
            SupportedLanguage.ENGLISH,
            true,
            true,
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
    Optional<Long> actualTotalAmount = cardPayment.getTotalAmount();

    // Assert
    assertEquals(1L, actualTotalAmount.get().longValue());
    assertTrue(actualTotalAmount.isPresent());
  }

  /**
   * Test {@link CardPayment#getReturnUrl()}.
   *
   * <p>Method under test: {@link CardPayment#getReturnUrl()}
   */
  @Test
  @DisplayName("Test getReturnUrl()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional CardPayment.getReturnUrl()"})
  void testGetReturnUrl() {
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
    ExternalMetadata metadata = new ExternalMetadata(new HashMap<>());
    AuthorisationSummary authorisationSummary = new AuthorisationSummary();

    CardPayment cardPayment =
        new CardPayment(
            "42",
            10L,
            state,
            "https://example.org/example",
            "The characteristics of someone or something",
            "Reference",
            "jane.doe@example.org",
            "Payment Provider",
            "2020-03-01",
            refundSummary,
            settlementSummary,
            cardDetails,
            SupportedLanguage.ENGLISH,
            true,
            true,
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
    Optional<String> actualReturnUrl = cardPayment.getReturnUrl();

    // Assert
    assertEquals("https://example.org/example", actualReturnUrl.get());
    assertTrue(actualReturnUrl.isPresent());
  }

  /**
   * Test {@link CardPayment#getEmail()}.
   *
   * <p>Method under test: {@link CardPayment#getEmail()}
   */
  @Test
  @DisplayName("Test getEmail()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional CardPayment.getEmail()"})
  void testGetEmail() {
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
    ExternalMetadata metadata = new ExternalMetadata(new HashMap<>());
    AuthorisationSummary authorisationSummary = new AuthorisationSummary();

    CardPayment cardPayment =
        new CardPayment(
            "42",
            10L,
            state,
            "https://example.org/example",
            "The characteristics of someone or something",
            "Reference",
            "jane.doe@example.org",
            "Payment Provider",
            "2020-03-01",
            refundSummary,
            settlementSummary,
            cardDetails,
            SupportedLanguage.ENGLISH,
            true,
            true,
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
    Optional<String> actualEmail = cardPayment.getEmail();

    // Assert
    assertEquals("jane.doe@example.org", actualEmail.get());
    assertTrue(actualEmail.isPresent());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CardPayment#toString()}
   *   <li>{@link CardPayment#getAgreementId()}
   *   <li>{@link CardPayment#getAgreementPaymentType()}
   *   <li>{@link CardPayment#getAmount()}
   *   <li>{@link CardPayment#getAuthorisationMode()}
   *   <li>{@link CardPayment#getAuthorisationSummary()}
   *   <li>{@link CardPayment#getCreatedDate()}
   *   <li>{@link CardPayment#getDelayedCapture()}
   *   <li>{@link CardPayment#getDescription()}
   *   <li>{@link CardPayment#getExemption()}
   *   <li>{@link CardPayment#getLanguage()}
   *   <li>{@link CardPayment#getMetadata()}
   *   <li>{@link CardPayment#getMoto()}
   *   <li>{@link CardPayment#getPaymentId()}
   *   <li>{@link CardPayment#getPaymentProvider()}
   *   <li>{@link CardPayment#getProviderId()}
   *   <li>{@link CardPayment#getReference()}
   *   <li>{@link CardPayment#getState()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String CardPayment.getAgreementId()",
    "AgreementPaymentType CardPayment.getAgreementPaymentType()",
    "long CardPayment.getAmount()",
    "AuthorisationMode CardPayment.getAuthorisationMode()",
    "AuthorisationSummary CardPayment.getAuthorisationSummary()",
    "String CardPayment.getCreatedDate()",
    "boolean CardPayment.getDelayedCapture()",
    "String CardPayment.getDescription()",
    "Exemption CardPayment.getExemption()",
    "SupportedLanguage CardPayment.getLanguage()",
    "ExternalMetadata CardPayment.getMetadata()",
    "boolean CardPayment.getMoto()",
    "String CardPayment.getPaymentId()",
    "String CardPayment.getPaymentProvider()",
    "String CardPayment.getProviderId()",
    "String CardPayment.getReference()",
    "PaymentState CardPayment.getState()",
    "String CardPayment.toString()"
  })
  void testGettersAndSetters() {
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

    PaymentWithAllLinksBuilder withSelfLinkResult =
        withReferenceResult
            .withRefundSummary(new RefundSummary("Status", 10L, 10L))
            .withReturnUrl("https://example.org/example")
            .withSelfLink(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    PaymentSettlementSummary settlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");

    PaymentWithAllLinksBuilder withSettlementSummaryResult =
        withSelfLinkResult.withSettlementSummary(settlementSummary);
    PaymentState state = new PaymentState("Status", true);
    PaymentWithAllLinks paymentWithAllLinks =
        withSettlementSummaryResult.withState(state).withTotalAmount(1L).build();

    // Act
    String actualToStringResult = paymentWithAllLinks.toString();
    String actualAgreementId = paymentWithAllLinks.getAgreementId();
    AgreementPaymentType actualAgreementPaymentType = paymentWithAllLinks.getAgreementPaymentType();
    long actualAmount = paymentWithAllLinks.getAmount();
    AuthorisationMode actualAuthorisationMode = paymentWithAllLinks.getAuthorisationMode();
    AuthorisationSummary actualAuthorisationSummary = paymentWithAllLinks.getAuthorisationSummary();
    String actualCreatedDate = paymentWithAllLinks.getCreatedDate();
    boolean actualDelayedCapture = paymentWithAllLinks.getDelayedCapture();
    String actualDescription = paymentWithAllLinks.getDescription();
    Exemption actualExemption = paymentWithAllLinks.getExemption();
    SupportedLanguage actualLanguage = paymentWithAllLinks.getLanguage();
    ExternalMetadata actualMetadata = paymentWithAllLinks.getMetadata();
    boolean actualMoto = paymentWithAllLinks.getMoto();
    String actualPaymentId = paymentWithAllLinks.getPaymentId();
    String actualPaymentProvider = paymentWithAllLinks.getPaymentProvider();
    String actualProviderId = paymentWithAllLinks.getProviderId();
    String actualReference = paymentWithAllLinks.getReference();

    // Assert
    assertEquals("2020-03-01", actualCreatedDate);
    assertEquals("42", actualAgreementId);
    assertEquals("42", actualPaymentId);
    assertEquals("42", actualProviderId);
    assertEquals(
        "Card Payment{paymentId='42', paymentProvider='Payment Provider', cardBrandLabel='Card Brand', amount=10,"
            + " fee=1, netAmount=1, corporateCardSurcharge='1', state='PaymentState{status='Status', finished='true',"
            + " message=null, code=null}', language='en', delayedCapture=true, moto=true, createdDate='2020-03-01',"
            + " agreementId='42'}",
        actualToStringResult);
    assertEquals("Payment Provider", actualPaymentProvider);
    assertEquals("Reference", actualReference);
    assertEquals("The characteristics of someone or something", actualDescription);
    assertNull(actualAuthorisationSummary.getThreeDSecure());
    assertEquals(10L, actualAmount);
    assertEquals(AgreementPaymentType.INSTALMENT, actualAgreementPaymentType);
    assertEquals(AuthorisationMode.WEB, actualAuthorisationMode);
    assertEquals(SupportedLanguage.ENGLISH, actualLanguage);
    assertTrue(actualDelayedCapture);
    assertTrue(actualMoto);
    assertSame(authorisationSummary, actualAuthorisationSummary);
    assertSame(exemption, actualExemption);
    assertSame(state, paymentWithAllLinks.getState());
    assertSame(metadata, actualMetadata);
  }
}
