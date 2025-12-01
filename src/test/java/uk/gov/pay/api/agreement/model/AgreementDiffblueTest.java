package uk.gov.pay.api.agreement.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.agreement.model.Agreement.PaymentInstrument;
import uk.gov.pay.api.agreement.model.AgreementLedgerResponse.PaymentInstrumentLedgerResponse;
import uk.gov.pay.api.agreement.model.AgreementLedgerResponse.PaymentInstrumentLedgerResponse.Builder;
import uk.gov.pay.api.model.Address;
import uk.gov.pay.api.model.CardDetailsFromResponse;

class AgreementDiffblueTest {
  /**
   * Test {@link Agreement#getStatus()}.
   *
   * <p>Method under test: {@link Agreement#getStatus()}
   */
  @Test
  @DisplayName("Test getStatus()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String Agreement.getStatus()"})
  void testGetStatus() {
    // Arrange
    AgreementLedgerResponse agreementLedgerResponse = new AgreementLedgerResponse();
    agreementLedgerResponse.setExternalId("42");
    Agreement fromResult = Agreement.from(agreementLedgerResponse);

    // Act and Assert
    assertNull(fromResult.getStatus());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Agreement#Agreement(String, String, String, String, String, PaymentInstrument,
   *       String, String)}
   *   <li>{@link Agreement#getCancelledDate()}
   *   <li>{@link Agreement#getCreatedDate()}
   *   <li>{@link Agreement#getDescription()}
   *   <li>{@link Agreement#getExternalId()}
   *   <li>{@link Agreement#getPaymentInstrument()}
   *   <li>{@link Agreement#getReference()}
   *   <li>{@link Agreement#getUserIdentifier()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Agreement.<init>(String, String, String, String, String, PaymentInstrument, String, String)",
    "String Agreement.getCancelledDate()",
    "String Agreement.getCreatedDate()",
    "String Agreement.getDescription()",
    "String Agreement.getExternalId()",
    "PaymentInstrument Agreement.getPaymentInstrument()",
    "String Agreement.getReference()",
    "String Agreement.getUserIdentifier()"
  })
  void testGettersAndSetters() {
    // Arrange
    Builder withAgreementExternalIdResult = new Builder().withAgreementExternalId("42");
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
    PaymentInstrumentLedgerResponse paymentInstrumentLedgerResponse =
        withAgreementExternalIdResult
            .withCardDetails(cardDetails)
            .withCreatedDate("2020-03-01")
            .withExternalId("0123456789ABCDEF")
            .withType("Type")
            .build();
    PaymentInstrument paymentInstrument = PaymentInstrument.from(paymentInstrumentLedgerResponse);

    // Act
    Agreement actualAgreement =
        new Agreement(
            "42",
            "Reference",
            "The characteristics of someone or something",
            "Status",
            "2020-03-01",
            paymentInstrument,
            "42",
            "2020-03-01");
    String actualCancelledDate = actualAgreement.getCancelledDate();
    String actualCreatedDate = actualAgreement.getCreatedDate();
    String actualDescription = actualAgreement.getDescription();
    String actualExternalId = actualAgreement.getExternalId();
    PaymentInstrument actualPaymentInstrument = actualAgreement.getPaymentInstrument();
    String actualReference = actualAgreement.getReference();

    // Assert
    assertEquals("2020-03-01", actualCancelledDate);
    assertEquals("2020-03-01", actualCreatedDate);
    assertEquals("42", actualExternalId);
    assertEquals("42", actualAgreement.getUserIdentifier());
    assertEquals("Reference", actualReference);
    assertEquals("The characteristics of someone or something", actualDescription);
    assertSame(paymentInstrument, actualPaymentInstrument);
  }

  /**
   * Test {@link Agreement#from(AgreementLedgerResponse)}.
   *
   * <p>Method under test: {@link Agreement#from(AgreementLedgerResponse)}
   */
  @Test
  @DisplayName("Test from(AgreementLedgerResponse)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Agreement Agreement.from(AgreementLedgerResponse)"})
  void testFrom() {
    // Arrange
    AgreementLedgerResponse agreementLedgerResponse = new AgreementLedgerResponse();
    agreementLedgerResponse.setExternalId("42");

    // Act
    Agreement actualFromResult = Agreement.from(agreementLedgerResponse);

    // Assert
    assertEquals("42", actualFromResult.getExternalId());
    assertNull(actualFromResult.getCancelledDate());
    assertNull(actualFromResult.getCreatedDate());
    assertNull(actualFromResult.getDescription());
    assertNull(actualFromResult.getReference());
    assertNull(actualFromResult.getStatus());
    assertNull(actualFromResult.getUserIdentifier());
    assertNull(actualFromResult.getPaymentInstrument());
  }

  /**
   * Test PaymentInstrument {@link PaymentInstrument#from(PaymentInstrumentLedgerResponse)}.
   *
   * <ul>
   *   <li>Then return CreatedDate is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentInstrument#from(PaymentInstrumentLedgerResponse)}
   */
  @Test
  @DisplayName(
      "Test PaymentInstrument from(PaymentInstrumentLedgerResponse); then return CreatedDate is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentInstrument PaymentInstrument.from(PaymentInstrumentLedgerResponse)"})
  void testPaymentInstrumentFrom_thenReturnCreatedDateIsNull() {
    // Arrange and Act
    PaymentInstrument actualFromResult =
        PaymentInstrument.from(new PaymentInstrumentLedgerResponse());

    // Assert
    assertNull(actualFromResult.getCreatedDate());
    assertNull(actualFromResult.getType());
    assertNull(actualFromResult.getCardDetails());
  }

  /**
   * Test PaymentInstrument {@link PaymentInstrument#getType()}.
   *
   * <p>Method under test: {@link PaymentInstrument#getType()}
   */
  @Test
  @DisplayName("Test PaymentInstrument getType()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String PaymentInstrument.getType()"})
  void testPaymentInstrumentGetType() {
    // Arrange
    Builder withAgreementExternalIdResult = new Builder().withAgreementExternalId("42");
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
    PaymentInstrumentLedgerResponse paymentInstrumentLedgerResponse =
        withAgreementExternalIdResult
            .withCardDetails(cardDetails)
            .withCreatedDate("2020-03-01")
            .withExternalId("0123456789ABCDEF")
            .withType("Type")
            .build();

    // Act and Assert
    assertEquals("type", PaymentInstrument.from(paymentInstrumentLedgerResponse).getType());
  }

  /**
   * Test PaymentInstrument getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PaymentInstrument#PaymentInstrument(CardDetailsFromResponse, String, String)}
   *   <li>{@link PaymentInstrument#getCardDetails()}
   *   <li>{@link PaymentInstrument#getCreatedDate()}
   * </ul>
   */
  @Test
  @DisplayName("Test PaymentInstrument getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentInstrument.<init>(CardDetailsFromResponse, String, String)",
    "CardDetailsFromResponse PaymentInstrument.getCardDetails()",
    "String PaymentInstrument.getCreatedDate()"
  })
  void testPaymentInstrumentGettersAndSetters() {
    // Arrange
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

    // Act
    PaymentInstrument actualPaymentInstrument =
        new PaymentInstrument(cardDetails, "2020-03-01", "Type");
    CardDetailsFromResponse actualCardDetails = actualPaymentInstrument.getCardDetails();

    // Assert
    assertEquals("2020-03-01", actualPaymentInstrument.getCreatedDate());
    assertSame(cardDetails, actualCardDetails);
  }
}
