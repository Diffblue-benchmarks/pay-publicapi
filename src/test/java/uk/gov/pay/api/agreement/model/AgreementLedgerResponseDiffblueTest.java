package uk.gov.pay.api.agreement.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.agreement.model.AgreementLedgerResponse.PaymentInstrumentLedgerResponse;
import uk.gov.pay.api.agreement.model.AgreementLedgerResponse.PaymentInstrumentLedgerResponse.Builder;
import uk.gov.pay.api.model.Address;
import uk.gov.pay.api.model.CardDetailsFromResponse;

class AgreementLedgerResponseDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link AgreementLedgerResponse}
   *   <li>{@link AgreementLedgerResponse#setExternalId(String)}
   *   <li>{@link AgreementLedgerResponse#getCancelledDate()}
   *   <li>{@link AgreementLedgerResponse#getCreatedDate()}
   *   <li>{@link AgreementLedgerResponse#getDescription()}
   *   <li>{@link AgreementLedgerResponse#getExternalId()}
   *   <li>{@link AgreementLedgerResponse#getPaymentInstrument()}
   *   <li>{@link AgreementLedgerResponse#getReference()}
   *   <li>{@link AgreementLedgerResponse#getServiceId()}
   *   <li>{@link AgreementLedgerResponse#getStatus()}
   *   <li>{@link AgreementLedgerResponse#getUserIdentifier()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AgreementLedgerResponse.<init>()",
    "String AgreementLedgerResponse.getCancelledDate()",
    "String AgreementLedgerResponse.getCreatedDate()",
    "String AgreementLedgerResponse.getDescription()",
    "String AgreementLedgerResponse.getExternalId()",
    "PaymentInstrumentLedgerResponse AgreementLedgerResponse.getPaymentInstrument()",
    "String AgreementLedgerResponse.getReference()",
    "String AgreementLedgerResponse.getServiceId()",
    "String AgreementLedgerResponse.getStatus()",
    "String AgreementLedgerResponse.getUserIdentifier()",
    "void AgreementLedgerResponse.setExternalId(String)"
  })
  void testGettersAndSetters() {
    // Arrange and Act
    AgreementLedgerResponse actualAgreementLedgerResponse = new AgreementLedgerResponse();
    actualAgreementLedgerResponse.setExternalId("42");
    String actualCancelledDate = actualAgreementLedgerResponse.getCancelledDate();
    String actualCreatedDate = actualAgreementLedgerResponse.getCreatedDate();
    String actualDescription = actualAgreementLedgerResponse.getDescription();
    String actualExternalId = actualAgreementLedgerResponse.getExternalId();
    PaymentInstrumentLedgerResponse actualPaymentInstrument =
        actualAgreementLedgerResponse.getPaymentInstrument();
    String actualReference = actualAgreementLedgerResponse.getReference();
    String actualServiceId = actualAgreementLedgerResponse.getServiceId();
    String actualStatus = actualAgreementLedgerResponse.getStatus();

    // Assert
    assertEquals("42", actualExternalId);
    assertNull(actualCancelledDate);
    assertNull(actualCreatedDate);
    assertNull(actualDescription);
    assertNull(actualReference);
    assertNull(actualServiceId);
    assertNull(actualStatus);
    assertNull(actualAgreementLedgerResponse.getUserIdentifier());
    assertNull(actualPaymentInstrument);
  }

  /**
   * Test PaymentInstrumentLedgerResponse getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PaymentInstrumentLedgerResponse#PaymentInstrumentLedgerResponse()}
   *   <li>{@link PaymentInstrumentLedgerResponse#getAgreementExternalId()}
   *   <li>{@link PaymentInstrumentLedgerResponse#getCardDetails()}
   *   <li>{@link PaymentInstrumentLedgerResponse#getCreatedDate()}
   *   <li>{@link PaymentInstrumentLedgerResponse#getExternalId()}
   *   <li>{@link PaymentInstrumentLedgerResponse#getType()}
   * </ul>
   */
  @Test
  @DisplayName("Test PaymentInstrumentLedgerResponse getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentInstrumentLedgerResponse.<init>()",
    "String PaymentInstrumentLedgerResponse.getAgreementExternalId()",
    "CardDetailsFromResponse PaymentInstrumentLedgerResponse.getCardDetails()",
    "String PaymentInstrumentLedgerResponse.getCreatedDate()",
    "String PaymentInstrumentLedgerResponse.getExternalId()",
    "String PaymentInstrumentLedgerResponse.getType()"
  })
  void testPaymentInstrumentLedgerResponseGettersAndSetters() {
    // Arrange and Act
    PaymentInstrumentLedgerResponse actualPaymentInstrumentLedgerResponse =
        new PaymentInstrumentLedgerResponse();
    String actualAgreementExternalId =
        actualPaymentInstrumentLedgerResponse.getAgreementExternalId();
    CardDetailsFromResponse actualCardDetails =
        actualPaymentInstrumentLedgerResponse.getCardDetails();
    String actualCreatedDate = actualPaymentInstrumentLedgerResponse.getCreatedDate();
    String actualExternalId = actualPaymentInstrumentLedgerResponse.getExternalId();

    // Assert
    assertNull(actualAgreementExternalId);
    assertNull(actualCreatedDate);
    assertNull(actualExternalId);
    assertNull(actualPaymentInstrumentLedgerResponse.getType());
    assertNull(actualCardDetails);
  }

  /**
   * Test PaymentInstrumentLedgerResponse_Builder {@link
   * PaymentInstrumentLedgerResponse.Builder#build()}.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PaymentInstrumentLedgerResponse.Builder#build()}
   *   <li>default or parameterless constructor of {@link PaymentInstrumentLedgerResponse.Builder}
   *   <li>{@link PaymentInstrumentLedgerResponse.Builder#withAgreementExternalId(String)}
   *   <li>{@link PaymentInstrumentLedgerResponse.Builder#withCardDetails(CardDetailsFromResponse)}
   *   <li>{@link PaymentInstrumentLedgerResponse.Builder#withCreatedDate(String)}
   *   <li>{@link PaymentInstrumentLedgerResponse.Builder#withExternalId(String)}
   *   <li>{@link PaymentInstrumentLedgerResponse.Builder#withType(String)}
   * </ul>
   */
  @Test
  @DisplayName("Test PaymentInstrumentLedgerResponse_Builder build()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentInstrumentLedgerResponse.Builder.<init>()",
    "PaymentInstrumentLedgerResponse PaymentInstrumentLedgerResponse.Builder.build()",
    "PaymentInstrumentLedgerResponse.Builder PaymentInstrumentLedgerResponse.Builder.withAgreementExternalId(String)",
    "PaymentInstrumentLedgerResponse.Builder PaymentInstrumentLedgerResponse.Builder.withCardDetails(CardDetailsFromResponse)",
    "PaymentInstrumentLedgerResponse.Builder PaymentInstrumentLedgerResponse.Builder.withCreatedDate(String)",
    "PaymentInstrumentLedgerResponse.Builder PaymentInstrumentLedgerResponse.Builder.withExternalId(String)",
    "PaymentInstrumentLedgerResponse.Builder PaymentInstrumentLedgerResponse.Builder.withType(String)"
  })
  void testPaymentInstrumentLedgerResponse_BuilderBuild() {
    // Arrange and Act
    Builder actualWithAgreementExternalIdResult = new Builder().withAgreementExternalId("42");
    Address billingAddress = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");
    CardDetailsFromResponse cardDetails =
        new CardDetailsFromResponse(
            "42",
            "42",
            "Card Holder Name",
            "2020-03-01",
            billingAddress,
            "Card Brand",
            "Card Type");
    PaymentInstrumentLedgerResponse actualPaymentInstrumentLedgerResponse =
        actualWithAgreementExternalIdResult
            .withCardDetails(cardDetails)
            .withCreatedDate("2020-03-01")
            .withExternalId("0123456789ABCDEF")
            .withType("Type")
            .build();

    // Assert
    assertEquals("0123456789ABCDEF", actualPaymentInstrumentLedgerResponse.getExternalId());
    assertEquals("2020-03-01", actualPaymentInstrumentLedgerResponse.getCreatedDate());
    assertEquals("42", actualPaymentInstrumentLedgerResponse.getAgreementExternalId());
    assertEquals("Type", actualPaymentInstrumentLedgerResponse.getType());
    assertSame(cardDetails, actualPaymentInstrumentLedgerResponse.getCardDetails());
  }
}
