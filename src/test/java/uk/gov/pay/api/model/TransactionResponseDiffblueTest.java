package uk.gov.pay.api.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.service.payments.commons.model.AgreementPaymentType;
import uk.gov.service.payments.commons.model.AuthorisationMode;
import uk.gov.service.payments.commons.model.SupportedLanguage;

class TransactionResponseDiffblueTest {
  /**
   * Test {@link TransactionResponse#getMetadata()}.
   *
   * <p>Method under test: {@link TransactionResponse#getMetadata()}
   */
  @Test
  @DisplayName("Test getMetadata()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Optional TransactionResponse.getMetadata()"})
  void testGetMetadata() {
    // Arrange, Act and Assert
    assertFalse(new TransactionResponse().getMetadata().isPresent());
  }

  /**
   * Test {@link TransactionResponse#getCardBrand()}.
   *
   * <p>Method under test: {@link TransactionResponse#getCardBrand()}
   */
  @Test
  @DisplayName("Test getCardBrand()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String TransactionResponse.getCardBrand()"})
  void testGetCardBrand() {
    // Arrange, Act and Assert
    assertEquals("", new TransactionResponse().getCardBrand());
  }

  /**
   * Test {@link TransactionResponse#getWalletType()}.
   *
   * <p>Method under test: {@link TransactionResponse#getWalletType()}
   */
  @Test
  @DisplayName("Test getWalletType()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Optional TransactionResponse.getWalletType()"})
  void testGetWalletType() {
    // Arrange, Act and Assert
    assertFalse(new TransactionResponse().getWalletType().isPresent());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link TransactionResponse}
   *   <li>{@link TransactionResponse#getAgreementPaymentType()}
   *   <li>{@link TransactionResponse#getAmount()}
   *   <li>{@link TransactionResponse#getAuthorisationMode()}
   *   <li>{@link TransactionResponse#getAuthorisationSummary()}
   *   <li>{@link TransactionResponse#getCardDetailsFromResponse()}
   *   <li>{@link TransactionResponse#getCorporateCardSurcharge()}
   *   <li>{@link TransactionResponse#getCreatedDate()}
   *   <li>{@link TransactionResponse#getDelayedCapture()}
   *   <li>{@link TransactionResponse#getDescription()}
   *   <li>{@link TransactionResponse#getEmail()}
   *   <li>{@link TransactionResponse#getExemption()}
   *   <li>{@link TransactionResponse#getFee()}
   *   <li>{@link TransactionResponse#getGatewayTransactionId()}
   *   <li>{@link TransactionResponse#getLanguage()}
   *   <li>{@link TransactionResponse#getLinks()}
   *   <li>{@link TransactionResponse#getNetAmount()}
   *   <li>{@link TransactionResponse#getPaymentProvider()}
   *   <li>{@link TransactionResponse#getReference()}
   *   <li>{@link TransactionResponse#getRefundSummary()}
   *   <li>{@link TransactionResponse#getReturnUrl()}
   *   <li>{@link TransactionResponse#getSettlementSummary()}
   *   <li>{@link TransactionResponse#getState()}
   *   <li>{@link TransactionResponse#getTotalAmount()}
   *   <li>{@link TransactionResponse#getTransactionId()}
   *   <li>{@link TransactionResponse#isMoto()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TransactionResponse.<init>()",
    "AgreementPaymentType TransactionResponse.getAgreementPaymentType()",
    "Long TransactionResponse.getAmount()",
    "AuthorisationMode TransactionResponse.getAuthorisationMode()",
    "AuthorisationSummary TransactionResponse.getAuthorisationSummary()",
    "CardDetailsFromResponse TransactionResponse.getCardDetailsFromResponse()",
    "Long TransactionResponse.getCorporateCardSurcharge()",
    "String TransactionResponse.getCreatedDate()",
    "boolean TransactionResponse.getDelayedCapture()",
    "String TransactionResponse.getDescription()",
    "String TransactionResponse.getEmail()",
    "Exemption TransactionResponse.getExemption()",
    "Long TransactionResponse.getFee()",
    "String TransactionResponse.getGatewayTransactionId()",
    "SupportedLanguage TransactionResponse.getLanguage()",
    "List TransactionResponse.getLinks()",
    "Long TransactionResponse.getNetAmount()",
    "String TransactionResponse.getPaymentProvider()",
    "String TransactionResponse.getReference()",
    "RefundSummary TransactionResponse.getRefundSummary()",
    "String TransactionResponse.getReturnUrl()",
    "PaymentSettlementSummary TransactionResponse.getSettlementSummary()",
    "PaymentState TransactionResponse.getState()",
    "Long TransactionResponse.getTotalAmount()",
    "String TransactionResponse.getTransactionId()",
    "boolean TransactionResponse.isMoto()"
  })
  void testGettersAndSetters() {
    // Arrange and Act
    TransactionResponse actualTransactionResponse = new TransactionResponse();
    AgreementPaymentType actualAgreementPaymentType =
        actualTransactionResponse.getAgreementPaymentType();
    Long actualAmount = actualTransactionResponse.getAmount();
    AuthorisationMode actualAuthorisationMode = actualTransactionResponse.getAuthorisationMode();
    AuthorisationSummary actualAuthorisationSummary =
        actualTransactionResponse.getAuthorisationSummary();
    CardDetailsFromResponse actualCardDetailsFromResponse =
        actualTransactionResponse.getCardDetailsFromResponse();
    Long actualCorporateCardSurcharge = actualTransactionResponse.getCorporateCardSurcharge();
    String actualCreatedDate = actualTransactionResponse.getCreatedDate();
    boolean actualDelayedCapture = actualTransactionResponse.getDelayedCapture();
    String actualDescription = actualTransactionResponse.getDescription();
    String actualEmail = actualTransactionResponse.getEmail();
    Exemption actualExemption = actualTransactionResponse.getExemption();
    Long actualFee = actualTransactionResponse.getFee();
    String actualGatewayTransactionId = actualTransactionResponse.getGatewayTransactionId();
    SupportedLanguage actualLanguage = actualTransactionResponse.getLanguage();
    List<PaymentConnectorResponseLink> actualLinks = actualTransactionResponse.getLinks();
    Long actualNetAmount = actualTransactionResponse.getNetAmount();
    String actualPaymentProvider = actualTransactionResponse.getPaymentProvider();
    String actualReference = actualTransactionResponse.getReference();
    RefundSummary actualRefundSummary = actualTransactionResponse.getRefundSummary();
    String actualReturnUrl = actualTransactionResponse.getReturnUrl();
    PaymentSettlementSummary actualSettlementSummary =
        actualTransactionResponse.getSettlementSummary();
    PaymentState actualState = actualTransactionResponse.getState();
    Long actualTotalAmount = actualTransactionResponse.getTotalAmount();
    String actualTransactionId = actualTransactionResponse.getTransactionId();

    // Assert
    assertNull(actualAmount);
    assertNull(actualCorporateCardSurcharge);
    assertNull(actualFee);
    assertNull(actualNetAmount);
    assertNull(actualTotalAmount);
    assertNull(actualCreatedDate);
    assertNull(actualDescription);
    assertNull(actualEmail);
    assertNull(actualGatewayTransactionId);
    assertNull(actualPaymentProvider);
    assertNull(actualReference);
    assertNull(actualReturnUrl);
    assertNull(actualTransactionId);
    assertNull(actualAuthorisationSummary);
    assertNull(actualCardDetailsFromResponse);
    assertNull(actualExemption);
    assertNull(actualSettlementSummary);
    assertNull(actualState);
    assertNull(actualRefundSummary);
    assertNull(actualAgreementPaymentType);
    assertNull(actualAuthorisationMode);
    assertNull(actualLanguage);
    assertFalse(actualDelayedCapture);
    assertFalse(actualTransactionResponse.isMoto());
    assertTrue(actualLinks.isEmpty());
  }
}
