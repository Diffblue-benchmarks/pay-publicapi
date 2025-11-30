package uk.gov.pay.api.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.service.payments.commons.model.AgreementPaymentType;
import uk.gov.service.payments.commons.model.AuthorisationMode;
import uk.gov.service.payments.commons.model.SupportedLanguage;
import uk.gov.service.payments.commons.model.charge.ExternalMetadata;

public class ChargeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Charge#Charge(String, Long, PaymentState, String, String, String, String, String,
   *       String, SupportedLanguage, boolean, boolean, RefundSummary, PaymentSettlementSummary,
   *       CardDetails, List, Long, Long, String, ExternalMetadata, Long, Long,
   *       AuthorisationSummary, String, AuthorisationMode, AgreementPaymentType, Exemption)}
   *   <li>{@link Charge#getAgreementId()}
   *   <li>{@link Charge#getAgreementPaymentType()}
   *   <li>{@link Charge#getAmount()}
   *   <li>{@link Charge#getAuthorisationMode()}
   *   <li>{@link Charge#getAuthorisationSummary()}
   *   <li>{@link Charge#getCardDetails()}
   *   <li>{@link Charge#getChargeId()}
   *   <li>{@link Charge#getCorporateCardSurcharge()}
   *   <li>{@link Charge#getCreatedDate()}
   *   <li>{@link Charge#getDelayedCapture()}
   *   <li>{@link Charge#getDescription()}
   *   <li>{@link Charge#getEmail()}
   *   <li>{@link Charge#getExemption()}
   *   <li>{@link Charge#getFee()}
   *   <li>{@link Charge#getGatewayTransactionId()}
   *   <li>{@link Charge#getLanguage()}
   *   <li>{@link Charge#getLinks()}
   *   <li>{@link Charge#getNetAmount()}
   *   <li>{@link Charge#getPaymentProvider()}
   *   <li>{@link Charge#getReference()}
   *   <li>{@link Charge#getRefundSummary()}
   *   <li>{@link Charge#getReturnUrl()}
   *   <li>{@link Charge#getSettlementSummary()}
   *   <li>{@link Charge#getState()}
   *   <li>{@link Charge#getTotalAmount()}
   *   <li>{@link Charge#isMoto()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Charge.<init>(String, Long, PaymentState, String, String, String, String, String, String, SupportedLanguage, boolean, boolean, RefundSummary, PaymentSettlementSummary, CardDetails, List, Long, Long, String, ExternalMetadata, Long, Long, AuthorisationSummary, String, AuthorisationMode, AgreementPaymentType, Exemption)",
    "String Charge.getAgreementId()",
    "AgreementPaymentType Charge.getAgreementPaymentType()",
    "Long Charge.getAmount()",
    "AuthorisationMode Charge.getAuthorisationMode()",
    "AuthorisationSummary Charge.getAuthorisationSummary()",
    "CardDetails Charge.getCardDetails()",
    "String Charge.getChargeId()",
    "Long Charge.getCorporateCardSurcharge()",
    "String Charge.getCreatedDate()",
    "boolean Charge.getDelayedCapture()",
    "String Charge.getDescription()",
    "String Charge.getEmail()",
    "Exemption Charge.getExemption()",
    "Long Charge.getFee()",
    "String Charge.getGatewayTransactionId()",
    "SupportedLanguage Charge.getLanguage()",
    "List Charge.getLinks()",
    "Long Charge.getNetAmount()",
    "String Charge.getPaymentProvider()",
    "String Charge.getReference()",
    "RefundSummary Charge.getRefundSummary()",
    "String Charge.getReturnUrl()",
    "PaymentSettlementSummary Charge.getSettlementSummary()",
    "PaymentState Charge.getState()",
    "Long Charge.getTotalAmount()",
    "boolean Charge.isMoto()"
  })
  public void testGettersAndSetters() {
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
    Exemption exemption = new Exemption();

    // Act
    Charge actualCharge =
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
            exemption);
    String actualAgreementId = actualCharge.getAgreementId();
    AgreementPaymentType actualAgreementPaymentType = actualCharge.getAgreementPaymentType();
    Long actualAmount = actualCharge.getAmount();
    AuthorisationMode actualAuthorisationMode = actualCharge.getAuthorisationMode();
    AuthorisationSummary actualAuthorisationSummary = actualCharge.getAuthorisationSummary();
    CardDetails actualCardDetails = actualCharge.getCardDetails();
    String actualChargeId = actualCharge.getChargeId();
    Long actualCorporateCardSurcharge = actualCharge.getCorporateCardSurcharge();
    String actualCreatedDate = actualCharge.getCreatedDate();
    boolean actualDelayedCapture = actualCharge.getDelayedCapture();
    String actualDescription = actualCharge.getDescription();
    String actualEmail = actualCharge.getEmail();
    Exemption actualExemption = actualCharge.getExemption();
    Long actualFee = actualCharge.getFee();
    String actualGatewayTransactionId = actualCharge.getGatewayTransactionId();
    SupportedLanguage actualLanguage = actualCharge.getLanguage();
    List<PaymentConnectorResponseLink> actualLinks = actualCharge.getLinks();
    Long actualNetAmount = actualCharge.getNetAmount();
    String actualPaymentProvider = actualCharge.getPaymentProvider();
    String actualReference = actualCharge.getReference();
    RefundSummary actualRefundSummary = actualCharge.getRefundSummary();
    String actualReturnUrl = actualCharge.getReturnUrl();
    PaymentSettlementSummary actualSettlementSummary = actualCharge.getSettlementSummary();
    PaymentState actualState = actualCharge.getState();
    Long actualTotalAmount = actualCharge.getTotalAmount();
    boolean actualIsMotoResult = actualCharge.isMoto();

    // Assert
    assertEquals("2020-03-01", actualCreatedDate);
    assertEquals("42", actualAgreementId);
    assertEquals("42", actualChargeId);
    assertEquals("42", actualGatewayTransactionId);
    assertEquals("Payment Provider", actualPaymentProvider);
    assertEquals("Reference", actualReference);
    assertEquals("The characteristics of someone or something", actualDescription);
    assertEquals("https://example.org/example", actualReturnUrl);
    assertEquals("jane.doe@example.org", actualEmail);
    assertNull(actualAuthorisationSummary.getThreeDSecure());
    assertEquals(10L, actualAmount.longValue());
    assertEquals(1L, actualCorporateCardSurcharge.longValue());
    assertEquals(1L, actualFee.longValue());
    assertEquals(1L, actualNetAmount.longValue());
    assertEquals(1L, actualTotalAmount.longValue());
    assertEquals(AgreementPaymentType.INSTALMENT, actualAgreementPaymentType);
    assertEquals(AuthorisationMode.WEB, actualAuthorisationMode);
    assertEquals(SupportedLanguage.ENGLISH, actualLanguage);
    assertTrue(actualLinks.isEmpty());
    assertTrue(actualDelayedCapture);
    assertTrue(actualIsMotoResult);
    assertSame(links, actualLinks);
    assertSame(authorisationSummary, actualAuthorisationSummary);
    assertSame(cardDetails, actualCardDetails);
    assertSame(exemption, actualExemption);
    assertSame(settlementSummary, actualSettlementSummary);
    assertSame(state, actualState);
    assertSame(refundSummary, actualRefundSummary);
  }

  /**
   * Test {@link Charge#from(ChargeFromResponse)} with {@code chargeFromResponse}.
   *
   * <ul>
   *   <li>Then return CardBrand is empty string.
   * </ul>
   *
   * <p>Method under test: {@link Charge#from(ChargeFromResponse)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Charge Charge.from(ChargeFromResponse)"})
  public void testFromWithChargeFromResponse_thenReturnCardBrandIsEmptyString() {
    // Arrange and Act
    Charge actualFromResult = Charge.from(new ChargeFromResponse());

    // Assert
    assertEquals("", actualFromResult.getCardBrand());
    assertNull(actualFromResult.getAmount());
    assertNull(actualFromResult.getCorporateCardSurcharge());
    assertNull(actualFromResult.getFee());
    assertNull(actualFromResult.getNetAmount());
    assertNull(actualFromResult.getTotalAmount());
    assertNull(actualFromResult.getAgreementId());
    assertNull(actualFromResult.getChargeId());
    assertNull(actualFromResult.getCreatedDate());
    assertNull(actualFromResult.getDescription());
    assertNull(actualFromResult.getEmail());
    assertNull(actualFromResult.getGatewayTransactionId());
    assertNull(actualFromResult.getPaymentProvider());
    assertNull(actualFromResult.getReference());
    assertNull(actualFromResult.getReturnUrl());
    assertNull(actualFromResult.getAuthorisationSummary());
    assertNull(actualFromResult.getCardDetails());
    assertNull(actualFromResult.getExemption());
    assertNull(actualFromResult.getSettlementSummary());
    assertNull(actualFromResult.getState());
    assertNull(actualFromResult.getRefundSummary());
    assertNull(actualFromResult.getAgreementPaymentType());
    assertNull(actualFromResult.getAuthorisationMode());
    assertNull(actualFromResult.getLanguage());
    assertFalse(actualFromResult.getMetadata().isPresent());
    assertFalse(actualFromResult.getDelayedCapture());
    assertFalse(actualFromResult.isMoto());
    assertTrue(actualFromResult.getLinks().isEmpty());
  }

  /**
   * Test {@link Charge#from(TransactionResponse)} with {@code transactionResponse}.
   *
   * <ul>
   *   <li>Then return CardBrand is empty string.
   * </ul>
   *
   * <p>Method under test: {@link Charge#from(TransactionResponse)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Charge Charge.from(TransactionResponse)"})
  public void testFromWithTransactionResponse_thenReturnCardBrandIsEmptyString() {
    // Arrange and Act
    Charge actualFromResult = Charge.from(new TransactionResponse());

    // Assert
    assertEquals("", actualFromResult.getCardBrand());
    assertNull(actualFromResult.getAmount());
    assertNull(actualFromResult.getCorporateCardSurcharge());
    assertNull(actualFromResult.getFee());
    assertNull(actualFromResult.getNetAmount());
    assertNull(actualFromResult.getTotalAmount());
    assertNull(actualFromResult.getAgreementId());
    assertNull(actualFromResult.getChargeId());
    assertNull(actualFromResult.getCreatedDate());
    assertNull(actualFromResult.getDescription());
    assertNull(actualFromResult.getEmail());
    assertNull(actualFromResult.getGatewayTransactionId());
    assertNull(actualFromResult.getPaymentProvider());
    assertNull(actualFromResult.getReference());
    assertNull(actualFromResult.getReturnUrl());
    assertNull(actualFromResult.getAuthorisationSummary());
    assertNull(actualFromResult.getCardDetails());
    assertNull(actualFromResult.getExemption());
    assertNull(actualFromResult.getSettlementSummary());
    assertNull(actualFromResult.getState());
    assertNull(actualFromResult.getRefundSummary());
    assertNull(actualFromResult.getAgreementPaymentType());
    assertNull(actualFromResult.getAuthorisationMode());
    assertNull(actualFromResult.getLanguage());
    assertFalse(actualFromResult.getMetadata().isPresent());
    assertFalse(actualFromResult.getDelayedCapture());
    assertFalse(actualFromResult.isMoto());
    assertTrue(actualFromResult.getLinks().isEmpty());
  }

  /**
   * Test {@link Charge#getMetadata()}.
   *
   * <p>Method under test: {@link Charge#getMetadata()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Optional Charge.getMetadata()"})
  public void testGetMetadata() {
    // Arrange
    Charge fromResult = Charge.from(new ChargeFromResponse());

    // Act and Assert
    assertFalse(fromResult.getMetadata().isPresent());
  }

  /**
   * Test {@link Charge#getCardBrand()}.
   *
   * <ul>
   *   <li>Given from {@link ChargeFromResponse} (default constructor).
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link Charge#getCardBrand()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Charge.getCardBrand()"})
  public void testGetCardBrand_givenFromChargeFromResponse_thenReturnEmptyString() {
    // Arrange
    Charge fromResult = Charge.from(new ChargeFromResponse());

    // Act and Assert
    assertEquals("", fromResult.getCardBrand());
  }

  /**
   * Test {@link Charge#getCardBrand()}.
   *
   * <ul>
   *   <li>Then return {@code Card Brand}.
   * </ul>
   *
   * <p>Method under test: {@link Charge#getCardBrand()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Charge.getCardBrand()"})
  public void testGetCardBrand_thenReturnCardBrand() {
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

    // Act and Assert
    assertEquals("Card Brand", charge.getCardBrand());
  }
}
