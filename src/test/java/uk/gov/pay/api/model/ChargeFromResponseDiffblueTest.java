package uk.gov.pay.api.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.pay.api.model.telephone.PaymentOutcome;
import uk.gov.service.payments.commons.model.AgreementPaymentType;
import uk.gov.service.payments.commons.model.AuthorisationMode;
import uk.gov.service.payments.commons.model.SupportedLanguage;

public class ChargeFromResponseDiffblueTest {
  /**
   * Test {@link ChargeFromResponse#getMetadata()}.
   *
   * <p>Method under test: {@link ChargeFromResponse#getMetadata()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Optional ChargeFromResponse.getMetadata()"})
  public void testGetMetadata() {
    // Arrange, Act and Assert
    assertFalse(new ChargeFromResponse().getMetadata().isPresent());
  }

  /**
   * Test {@link ChargeFromResponse#getCardBrand()}.
   *
   * <p>Method under test: {@link ChargeFromResponse#getCardBrand()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ChargeFromResponse.getCardBrand()"})
  public void testGetCardBrand() {
    // Arrange, Act and Assert
    assertEquals("", new ChargeFromResponse().getCardBrand());
  }

  /**
   * Test {@link ChargeFromResponse#getWalletType()}.
   *
   * <p>Method under test: {@link ChargeFromResponse#getWalletType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Optional ChargeFromResponse.getWalletType()"})
  public void testGetWalletType() {
    // Arrange, Act and Assert
    assertFalse(new ChargeFromResponse().getWalletType().isPresent());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link ChargeFromResponse}
   *   <li>{@link ChargeFromResponse#getAgreementId()}
   *   <li>{@link ChargeFromResponse#getAgreementPaymentType()}
   *   <li>{@link ChargeFromResponse#getAmount()}
   *   <li>{@link ChargeFromResponse#getAuthCode()}
   *   <li>{@link ChargeFromResponse#getAuthorisationMode()}
   *   <li>{@link ChargeFromResponse#getAuthorisationSummary()}
   *   <li>{@link ChargeFromResponse#getAuthorisedDate()}
   *   <li>{@link ChargeFromResponse#getCardDetailsFromResponse()}
   *   <li>{@link ChargeFromResponse#getChargeId()}
   *   <li>{@link ChargeFromResponse#getCorporateCardSurcharge()}
   *   <li>{@link ChargeFromResponse#getCreatedDate()}
   *   <li>{@link ChargeFromResponse#getDelayedCapture()}
   *   <li>{@link ChargeFromResponse#getDescription()}
   *   <li>{@link ChargeFromResponse#getEmail()}
   *   <li>{@link ChargeFromResponse#getExemption()}
   *   <li>{@link ChargeFromResponse#getFee()}
   *   <li>{@link ChargeFromResponse#getGatewayTransactionId()}
   *   <li>{@link ChargeFromResponse#getLanguage()}
   *   <li>{@link ChargeFromResponse#getLinks()}
   *   <li>{@link ChargeFromResponse#getNetAmount()}
   *   <li>{@link ChargeFromResponse#getPaymentOutcome()}
   *   <li>{@link ChargeFromResponse#getPaymentProvider()}
   *   <li>{@link ChargeFromResponse#getProcessorId()}
   *   <li>{@link ChargeFromResponse#getProviderId()}
   *   <li>{@link ChargeFromResponse#getReference()}
   *   <li>{@link ChargeFromResponse#getRefundSummary()}
   *   <li>{@link ChargeFromResponse#getReturnUrl()}
   *   <li>{@link ChargeFromResponse#getSettlementSummary()}
   *   <li>{@link ChargeFromResponse#getState()}
   *   <li>{@link ChargeFromResponse#getTelephoneNumber()}
   *   <li>{@link ChargeFromResponse#getTotalAmount()}
   *   <li>{@link ChargeFromResponse#isMoto()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ChargeFromResponse.<init>()",
    "String ChargeFromResponse.getAgreementId()",
    "AgreementPaymentType ChargeFromResponse.getAgreementPaymentType()",
    "Long ChargeFromResponse.getAmount()",
    "String ChargeFromResponse.getAuthCode()",
    "AuthorisationMode ChargeFromResponse.getAuthorisationMode()",
    "AuthorisationSummary ChargeFromResponse.getAuthorisationSummary()",
    "String ChargeFromResponse.getAuthorisedDate()",
    "CardDetailsFromResponse ChargeFromResponse.getCardDetailsFromResponse()",
    "String ChargeFromResponse.getChargeId()",
    "Long ChargeFromResponse.getCorporateCardSurcharge()",
    "String ChargeFromResponse.getCreatedDate()",
    "boolean ChargeFromResponse.getDelayedCapture()",
    "String ChargeFromResponse.getDescription()",
    "String ChargeFromResponse.getEmail()",
    "Exemption ChargeFromResponse.getExemption()",
    "Long ChargeFromResponse.getFee()",
    "String ChargeFromResponse.getGatewayTransactionId()",
    "SupportedLanguage ChargeFromResponse.getLanguage()",
    "List ChargeFromResponse.getLinks()",
    "Long ChargeFromResponse.getNetAmount()",
    "PaymentOutcome ChargeFromResponse.getPaymentOutcome()",
    "String ChargeFromResponse.getPaymentProvider()",
    "String ChargeFromResponse.getProcessorId()",
    "String ChargeFromResponse.getProviderId()",
    "String ChargeFromResponse.getReference()",
    "RefundSummary ChargeFromResponse.getRefundSummary()",
    "String ChargeFromResponse.getReturnUrl()",
    "PaymentSettlementSummary ChargeFromResponse.getSettlementSummary()",
    "PaymentState ChargeFromResponse.getState()",
    "String ChargeFromResponse.getTelephoneNumber()",
    "Long ChargeFromResponse.getTotalAmount()",
    "boolean ChargeFromResponse.isMoto()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    ChargeFromResponse actualChargeFromResponse = new ChargeFromResponse();
    String actualAgreementId = actualChargeFromResponse.getAgreementId();
    AgreementPaymentType actualAgreementPaymentType =
        actualChargeFromResponse.getAgreementPaymentType();
    Long actualAmount = actualChargeFromResponse.getAmount();
    String actualAuthCode = actualChargeFromResponse.getAuthCode();
    AuthorisationMode actualAuthorisationMode = actualChargeFromResponse.getAuthorisationMode();
    AuthorisationSummary actualAuthorisationSummary =
        actualChargeFromResponse.getAuthorisationSummary();
    String actualAuthorisedDate = actualChargeFromResponse.getAuthorisedDate();
    CardDetailsFromResponse actualCardDetailsFromResponse =
        actualChargeFromResponse.getCardDetailsFromResponse();
    String actualChargeId = actualChargeFromResponse.getChargeId();
    Long actualCorporateCardSurcharge = actualChargeFromResponse.getCorporateCardSurcharge();
    String actualCreatedDate = actualChargeFromResponse.getCreatedDate();
    boolean actualDelayedCapture = actualChargeFromResponse.getDelayedCapture();
    String actualDescription = actualChargeFromResponse.getDescription();
    String actualEmail = actualChargeFromResponse.getEmail();
    Exemption actualExemption = actualChargeFromResponse.getExemption();
    Long actualFee = actualChargeFromResponse.getFee();
    String actualGatewayTransactionId = actualChargeFromResponse.getGatewayTransactionId();
    SupportedLanguage actualLanguage = actualChargeFromResponse.getLanguage();
    List<PaymentConnectorResponseLink> actualLinks = actualChargeFromResponse.getLinks();
    Long actualNetAmount = actualChargeFromResponse.getNetAmount();
    PaymentOutcome actualPaymentOutcome = actualChargeFromResponse.getPaymentOutcome();
    String actualPaymentProvider = actualChargeFromResponse.getPaymentProvider();
    String actualProcessorId = actualChargeFromResponse.getProcessorId();
    String actualProviderId = actualChargeFromResponse.getProviderId();
    String actualReference = actualChargeFromResponse.getReference();
    RefundSummary actualRefundSummary = actualChargeFromResponse.getRefundSummary();
    String actualReturnUrl = actualChargeFromResponse.getReturnUrl();
    PaymentSettlementSummary actualSettlementSummary =
        actualChargeFromResponse.getSettlementSummary();
    PaymentState actualState = actualChargeFromResponse.getState();
    String actualTelephoneNumber = actualChargeFromResponse.getTelephoneNumber();
    Long actualTotalAmount = actualChargeFromResponse.getTotalAmount();

    // Assert
    assertNull(actualAmount);
    assertNull(actualCorporateCardSurcharge);
    assertNull(actualFee);
    assertNull(actualNetAmount);
    assertNull(actualTotalAmount);
    assertNull(actualAgreementId);
    assertNull(actualAuthCode);
    assertNull(actualAuthorisedDate);
    assertNull(actualChargeId);
    assertNull(actualCreatedDate);
    assertNull(actualDescription);
    assertNull(actualEmail);
    assertNull(actualGatewayTransactionId);
    assertNull(actualPaymentProvider);
    assertNull(actualProcessorId);
    assertNull(actualProviderId);
    assertNull(actualReference);
    assertNull(actualReturnUrl);
    assertNull(actualTelephoneNumber);
    assertNull(actualAuthorisationSummary);
    assertNull(actualCardDetailsFromResponse);
    assertNull(actualExemption);
    assertNull(actualSettlementSummary);
    assertNull(actualState);
    assertNull(actualRefundSummary);
    assertNull(actualPaymentOutcome);
    assertNull(actualAgreementPaymentType);
    assertNull(actualAuthorisationMode);
    assertNull(actualLanguage);
    assertFalse(actualDelayedCapture);
    assertFalse(actualChargeFromResponse.isMoto());
    assertTrue(actualLinks.isEmpty());
  }
}
