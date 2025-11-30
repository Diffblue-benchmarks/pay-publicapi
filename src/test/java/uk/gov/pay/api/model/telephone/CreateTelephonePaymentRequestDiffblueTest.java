package uk.gov.pay.api.model.telephone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Optional;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.pay.api.model.telephone.CreateTelephonePaymentRequest.Builder;

public class CreateTelephonePaymentRequestDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>default or parameterless constructor of {@link Builder}
   *   <li>{@link Builder#withAmount(int)}
   *   <li>{@link Builder#withAuthCode(String)}
   *   <li>{@link Builder#withAuthorisedDate(String)}
   *   <li>{@link Builder#withCardExpiry(String)}
   *   <li>{@link Builder#withCardType(String)}
   *   <li>{@link Builder#withCreatedDate(String)}
   *   <li>{@link Builder#withDescription(String)}
   *   <li>{@link Builder#withEmailAddress(String)}
   *   <li>{@link Builder#withFirstSixDigits(String)}
   *   <li>{@link Builder#withLastFourDigits(String)}
   *   <li>{@link Builder#withNameOnCard(String)}
   *   <li>{@link Builder#withPaymentOutcome(PaymentOutcome)}
   *   <li>{@link Builder#withProcessorId(String)}
   *   <li>{@link Builder#withProviderId(String)}
   *   <li>{@link Builder#withReference(String)}
   *   <li>{@link Builder#withTelephoneNumber(String)}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Builder.<init>()",
    "CreateTelephonePaymentRequest Builder.build()",
    "Builder Builder.withAmount(int)",
    "Builder Builder.withAuthCode(String)",
    "Builder Builder.withAuthorisedDate(String)",
    "Builder Builder.withCardExpiry(String)",
    "Builder Builder.withCardType(String)",
    "Builder Builder.withCreatedDate(String)",
    "Builder Builder.withDescription(String)",
    "Builder Builder.withEmailAddress(String)",
    "Builder Builder.withFirstSixDigits(String)",
    "Builder Builder.withLastFourDigits(String)",
    "Builder Builder.withNameOnCard(String)",
    "Builder Builder.withPaymentOutcome(PaymentOutcome)",
    "Builder Builder.withProcessorId(String)",
    "Builder Builder.withProviderId(String)",
    "Builder Builder.withReference(String)",
    "Builder Builder.withTelephoneNumber(String)"
  })
  public void testBuilderBuild() {
    // Arrange and Act
    Builder actualWithNameOnCardResult =
        new Builder()
            .withAmount(10)
            .withAuthCode("Auth Code")
            .withAuthorisedDate("2020-03-01")
            .withCardExpiry("Card Expiry")
            .withCardType("Card Type")
            .withCreatedDate("2020-03-01")
            .withDescription("The characteristics of someone or something")
            .withEmailAddress("42 Main St")
            .withFirstSixDigits("First Six Digits")
            .withLastFourDigits("Last Four Digits")
            .withNameOnCard("Name On Card");
    PaymentOutcome paymentOutcome = new PaymentOutcome("Status");
    CreateTelephonePaymentRequest actualCreateTelephonePaymentRequest =
        actualWithNameOnCardResult
            .withPaymentOutcome(paymentOutcome)
            .withProcessorId("42")
            .withProviderId("42")
            .withReference("Reference")
            .withTelephoneNumber("6625550144")
            .build();

    // Assert
    Optional<String> authorisedDate = actualCreateTelephonePaymentRequest.getAuthorisedDate();
    assertEquals("2020-03-01", authorisedDate.get());
    Optional<String> emailAddress = actualCreateTelephonePaymentRequest.getEmailAddress();
    assertEquals("42 Main St", emailAddress.get());
    assertEquals("42", actualCreateTelephonePaymentRequest.getProcessorId());
    assertEquals("42", actualCreateTelephonePaymentRequest.getProviderId());
    Optional<String> telephoneNumber = actualCreateTelephonePaymentRequest.getTelephoneNumber();
    assertEquals("6625550144", telephoneNumber.get());
    Optional<String> authCode = actualCreateTelephonePaymentRequest.getAuthCode();
    assertEquals("Auth Code", authCode.get());
    Optional<String> cardExpiry = actualCreateTelephonePaymentRequest.getCardExpiry();
    assertEquals("Card Expiry", cardExpiry.get());
    Optional<String> cardType = actualCreateTelephonePaymentRequest.getCardType();
    assertEquals("Card Type", cardType.get());
    Optional<String> firstSixDigits = actualCreateTelephonePaymentRequest.getFirstSixDigits();
    assertEquals("First Six Digits", firstSixDigits.get());
    Optional<String> lastFourDigits = actualCreateTelephonePaymentRequest.getLastFourDigits();
    assertEquals("Last Four Digits", lastFourDigits.get());
    Optional<String> nameOnCard = actualCreateTelephonePaymentRequest.getNameOnCard();
    assertEquals("Name On Card", nameOnCard.get());
    assertEquals("Reference", actualCreateTelephonePaymentRequest.getReference());
    assertEquals(
        "The characteristics of someone or something",
        actualCreateTelephonePaymentRequest.getDescription());
    assertEquals(10, actualCreateTelephonePaymentRequest.getAmount());
    assertTrue(authCode.isPresent());
    assertTrue(authorisedDate.isPresent());
    assertTrue(cardExpiry.isPresent());
    assertTrue(cardType.isPresent());
    assertTrue(emailAddress.isPresent());
    assertTrue(firstSixDigits.isPresent());
    assertTrue(lastFourDigits.isPresent());
    assertTrue(nameOnCard.isPresent());
    assertTrue(telephoneNumber.isPresent());
    assertSame(paymentOutcome, actualCreateTelephonePaymentRequest.getPaymentOutcome());
  }

  /**
   * Test {@link CreateTelephonePaymentRequest#toConnectorPayload()}.
   *
   * <p>Method under test: {@link CreateTelephonePaymentRequest#toConnectorPayload()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CreateTelephonePaymentRequest.toConnectorPayload()"})
  public void testToConnectorPayload() {
    // Arrange
    Builder withNameOnCardResult =
        new Builder()
            .withAmount(10)
            .withAuthCode("Auth Code")
            .withAuthorisedDate("2020-03-01")
            .withCardExpiry("Card Expiry")
            .withCardType("Card Type")
            .withCreatedDate("2020-03-01")
            .withDescription("The characteristics of someone or something")
            .withEmailAddress("42 Main St")
            .withFirstSixDigits("First Six Digits")
            .withLastFourDigits("Last Four Digits")
            .withNameOnCard("Name On Card");

    // Act and Assert
    assertEquals(
        "{\n"
            + "  \"amount\" : 10,\n"
            + "  \"reference\" : \"Reference\",\n"
            + "  \"description\" : \"The characteristics of someone or something\",\n"
            + "  \"processor_id\" : \"42\",\n"
            + "  \"provider_id\" : \"42\",\n"
            + "  \"payment_outcome\" : {\n"
            + "    \"status\" : \"Status\"\n"
            + "  },\n"
            + "  \"card_expiry\" : \"Card Expiry\",\n"
            + "  \"created_date\" : \"2020-03-01\",\n"
            + "  \"authorised_date\" : \"2020-03-01\",\n"
            + "  \"auth_code\" : \"Auth Code\",\n"
            + "  \"name_on_card\" : \"Name On Card\",\n"
            + "  \"email_address\" : \"42 Main St\",\n"
            + "  \"telephone_number\" : \"6625550144\",\n"
            + "  \"card_type\" : \"Card Type\",\n"
            + "  \"last_four_digits\" : \"Last Four Digits\",\n"
            + "  \"first_six_digits\" : \"First Six Digits\"\n"
            + "}",
        withNameOnCardResult
            .withPaymentOutcome(new PaymentOutcome("Status"))
            .withProcessorId("42")
            .withProviderId("42")
            .withReference("Reference")
            .withTelephoneNumber("6625550144")
            .build()
            .toConnectorPayload());
  }

  /**
   * Test {@link CreateTelephonePaymentRequest#toConnectorPayload()}.
   *
   * <p>Method under test: {@link CreateTelephonePaymentRequest#toConnectorPayload()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CreateTelephonePaymentRequest.toConnectorPayload()"})
  public void testToConnectorPayload2() {
    // Arrange
    Builder withNameOnCardResult =
        new Builder()
            .withAmount(10)
            .withAuthCode(null)
            .withAuthorisedDate("2020-03-01")
            .withCardExpiry("Card Expiry")
            .withCardType("Card Type")
            .withCreatedDate("2020-03-01")
            .withDescription("The characteristics of someone or something")
            .withEmailAddress("42 Main St")
            .withFirstSixDigits("First Six Digits")
            .withLastFourDigits("Last Four Digits")
            .withNameOnCard("Name On Card");

    // Act and Assert
    assertEquals(
        "{\n"
            + "  \"amount\" : 10,\n"
            + "  \"reference\" : \"Reference\",\n"
            + "  \"description\" : \"The characteristics of someone or something\",\n"
            + "  \"processor_id\" : \"42\",\n"
            + "  \"provider_id\" : \"42\",\n"
            + "  \"payment_outcome\" : {\n"
            + "    \"status\" : \"Status\"\n"
            + "  },\n"
            + "  \"card_expiry\" : \"Card Expiry\",\n"
            + "  \"created_date\" : \"2020-03-01\",\n"
            + "  \"authorised_date\" : \"2020-03-01\",\n"
            + "  \"name_on_card\" : \"Name On Card\",\n"
            + "  \"email_address\" : \"42 Main St\",\n"
            + "  \"telephone_number\" : \"6625550144\",\n"
            + "  \"card_type\" : \"Card Type\",\n"
            + "  \"last_four_digits\" : \"Last Four Digits\",\n"
            + "  \"first_six_digits\" : \"First Six Digits\"\n"
            + "}",
        withNameOnCardResult
            .withPaymentOutcome(new PaymentOutcome("Status"))
            .withProcessorId("42")
            .withProviderId("42")
            .withReference("Reference")
            .withTelephoneNumber("6625550144")
            .build()
            .toConnectorPayload());
  }

  /**
   * Test {@link CreateTelephonePaymentRequest#toConnectorPayload()}.
   *
   * <p>Method under test: {@link CreateTelephonePaymentRequest#toConnectorPayload()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CreateTelephonePaymentRequest.toConnectorPayload()"})
  public void testToConnectorPayload3() {
    // Arrange
    Builder withNameOnCardResult =
        new Builder()
            .withAmount(10)
            .withAuthCode("Auth Code")
            .withAuthorisedDate(null)
            .withCardExpiry("Card Expiry")
            .withCardType("Card Type")
            .withCreatedDate("2020-03-01")
            .withDescription("The characteristics of someone or something")
            .withEmailAddress("42 Main St")
            .withFirstSixDigits("First Six Digits")
            .withLastFourDigits("Last Four Digits")
            .withNameOnCard("Name On Card");

    // Act and Assert
    assertEquals(
        "{\n"
            + "  \"amount\" : 10,\n"
            + "  \"reference\" : \"Reference\",\n"
            + "  \"description\" : \"The characteristics of someone or something\",\n"
            + "  \"processor_id\" : \"42\",\n"
            + "  \"provider_id\" : \"42\",\n"
            + "  \"payment_outcome\" : {\n"
            + "    \"status\" : \"Status\"\n"
            + "  },\n"
            + "  \"card_expiry\" : \"Card Expiry\",\n"
            + "  \"created_date\" : \"2020-03-01\",\n"
            + "  \"auth_code\" : \"Auth Code\",\n"
            + "  \"name_on_card\" : \"Name On Card\",\n"
            + "  \"email_address\" : \"42 Main St\",\n"
            + "  \"telephone_number\" : \"6625550144\",\n"
            + "  \"card_type\" : \"Card Type\",\n"
            + "  \"last_four_digits\" : \"Last Four Digits\",\n"
            + "  \"first_six_digits\" : \"First Six Digits\"\n"
            + "}",
        withNameOnCardResult
            .withPaymentOutcome(new PaymentOutcome("Status"))
            .withProcessorId("42")
            .withProviderId("42")
            .withReference("Reference")
            .withTelephoneNumber("6625550144")
            .build()
            .toConnectorPayload());
  }

  /**
   * Test {@link CreateTelephonePaymentRequest#toConnectorPayload()}.
   *
   * <p>Method under test: {@link CreateTelephonePaymentRequest#toConnectorPayload()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CreateTelephonePaymentRequest.toConnectorPayload()"})
  public void testToConnectorPayload4() {
    // Arrange
    Builder withNameOnCardResult =
        new Builder()
            .withAmount(10)
            .withAuthCode("Auth Code")
            .withAuthorisedDate("2020-03-01")
            .withCardExpiry(null)
            .withCardType("Card Type")
            .withCreatedDate("2020-03-01")
            .withDescription("The characteristics of someone or something")
            .withEmailAddress("42 Main St")
            .withFirstSixDigits("First Six Digits")
            .withLastFourDigits("Last Four Digits")
            .withNameOnCard("Name On Card");

    // Act and Assert
    assertEquals(
        "{\n"
            + "  \"amount\" : 10,\n"
            + "  \"reference\" : \"Reference\",\n"
            + "  \"description\" : \"The characteristics of someone or something\",\n"
            + "  \"processor_id\" : \"42\",\n"
            + "  \"provider_id\" : \"42\",\n"
            + "  \"payment_outcome\" : {\n"
            + "    \"status\" : \"Status\"\n"
            + "  },\n"
            + "  \"created_date\" : \"2020-03-01\",\n"
            + "  \"authorised_date\" : \"2020-03-01\",\n"
            + "  \"auth_code\" : \"Auth Code\",\n"
            + "  \"name_on_card\" : \"Name On Card\",\n"
            + "  \"email_address\" : \"42 Main St\",\n"
            + "  \"telephone_number\" : \"6625550144\",\n"
            + "  \"card_type\" : \"Card Type\",\n"
            + "  \"last_four_digits\" : \"Last Four Digits\",\n"
            + "  \"first_six_digits\" : \"First Six Digits\"\n"
            + "}",
        withNameOnCardResult
            .withPaymentOutcome(new PaymentOutcome("Status"))
            .withProcessorId("42")
            .withProviderId("42")
            .withReference("Reference")
            .withTelephoneNumber("6625550144")
            .build()
            .toConnectorPayload());
  }

  /**
   * Test {@link CreateTelephonePaymentRequest#toConnectorPayload()}.
   *
   * <p>Method under test: {@link CreateTelephonePaymentRequest#toConnectorPayload()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CreateTelephonePaymentRequest.toConnectorPayload()"})
  public void testToConnectorPayload5() {
    // Arrange
    Builder withNameOnCardResult =
        new Builder()
            .withAmount(10)
            .withAuthCode("Auth Code")
            .withAuthorisedDate("2020-03-01")
            .withCardExpiry("Card Expiry")
            .withCardType(null)
            .withCreatedDate("2020-03-01")
            .withDescription("The characteristics of someone or something")
            .withEmailAddress("42 Main St")
            .withFirstSixDigits("First Six Digits")
            .withLastFourDigits("Last Four Digits")
            .withNameOnCard("Name On Card");

    // Act and Assert
    assertEquals(
        "{\n"
            + "  \"amount\" : 10,\n"
            + "  \"reference\" : \"Reference\",\n"
            + "  \"description\" : \"The characteristics of someone or something\",\n"
            + "  \"processor_id\" : \"42\",\n"
            + "  \"provider_id\" : \"42\",\n"
            + "  \"payment_outcome\" : {\n"
            + "    \"status\" : \"Status\"\n"
            + "  },\n"
            + "  \"card_expiry\" : \"Card Expiry\",\n"
            + "  \"created_date\" : \"2020-03-01\",\n"
            + "  \"authorised_date\" : \"2020-03-01\",\n"
            + "  \"auth_code\" : \"Auth Code\",\n"
            + "  \"name_on_card\" : \"Name On Card\",\n"
            + "  \"email_address\" : \"42 Main St\",\n"
            + "  \"telephone_number\" : \"6625550144\",\n"
            + "  \"last_four_digits\" : \"Last Four Digits\",\n"
            + "  \"first_six_digits\" : \"First Six Digits\"\n"
            + "}",
        withNameOnCardResult
            .withPaymentOutcome(new PaymentOutcome("Status"))
            .withProcessorId("42")
            .withProviderId("42")
            .withReference("Reference")
            .withTelephoneNumber("6625550144")
            .build()
            .toConnectorPayload());
  }

  /**
   * Test {@link CreateTelephonePaymentRequest#toConnectorPayload()}.
   *
   * <p>Method under test: {@link CreateTelephonePaymentRequest#toConnectorPayload()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CreateTelephonePaymentRequest.toConnectorPayload()"})
  public void testToConnectorPayload6() {
    // Arrange
    Builder withNameOnCardResult =
        new Builder()
            .withAmount(10)
            .withAuthCode("Auth Code")
            .withAuthorisedDate("2020-03-01")
            .withCardExpiry("Card Expiry")
            .withCardType("Card Type")
            .withCreatedDate(null)
            .withDescription("The characteristics of someone or something")
            .withEmailAddress("42 Main St")
            .withFirstSixDigits("First Six Digits")
            .withLastFourDigits("Last Four Digits")
            .withNameOnCard("Name On Card");

    // Act and Assert
    assertEquals(
        "{\n"
            + "  \"amount\" : 10,\n"
            + "  \"reference\" : \"Reference\",\n"
            + "  \"description\" : \"The characteristics of someone or something\",\n"
            + "  \"processor_id\" : \"42\",\n"
            + "  \"provider_id\" : \"42\",\n"
            + "  \"payment_outcome\" : {\n"
            + "    \"status\" : \"Status\"\n"
            + "  },\n"
            + "  \"card_expiry\" : \"Card Expiry\",\n"
            + "  \"authorised_date\" : \"2020-03-01\",\n"
            + "  \"auth_code\" : \"Auth Code\",\n"
            + "  \"name_on_card\" : \"Name On Card\",\n"
            + "  \"email_address\" : \"42 Main St\",\n"
            + "  \"telephone_number\" : \"6625550144\",\n"
            + "  \"card_type\" : \"Card Type\",\n"
            + "  \"last_four_digits\" : \"Last Four Digits\",\n"
            + "  \"first_six_digits\" : \"First Six Digits\"\n"
            + "}",
        withNameOnCardResult
            .withPaymentOutcome(new PaymentOutcome("Status"))
            .withProcessorId("42")
            .withProviderId("42")
            .withReference("Reference")
            .withTelephoneNumber("6625550144")
            .build()
            .toConnectorPayload());
  }

  /**
   * Test {@link CreateTelephonePaymentRequest#toConnectorPayload()}.
   *
   * <p>Method under test: {@link CreateTelephonePaymentRequest#toConnectorPayload()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CreateTelephonePaymentRequest.toConnectorPayload()"})
  public void testToConnectorPayload7() {
    // Arrange
    Builder withNameOnCardResult =
        new Builder()
            .withAmount(10)
            .withAuthCode("Auth Code")
            .withAuthorisedDate("2020-03-01")
            .withCardExpiry("Card Expiry")
            .withCardType("Card Type")
            .withCreatedDate("2020-03-01")
            .withDescription("The characteristics of someone or something")
            .withEmailAddress(null)
            .withFirstSixDigits("First Six Digits")
            .withLastFourDigits("Last Four Digits")
            .withNameOnCard("Name On Card");

    // Act and Assert
    assertEquals(
        "{\n"
            + "  \"amount\" : 10,\n"
            + "  \"reference\" : \"Reference\",\n"
            + "  \"description\" : \"The characteristics of someone or something\",\n"
            + "  \"processor_id\" : \"42\",\n"
            + "  \"provider_id\" : \"42\",\n"
            + "  \"payment_outcome\" : {\n"
            + "    \"status\" : \"Status\"\n"
            + "  },\n"
            + "  \"card_expiry\" : \"Card Expiry\",\n"
            + "  \"created_date\" : \"2020-03-01\",\n"
            + "  \"authorised_date\" : \"2020-03-01\",\n"
            + "  \"auth_code\" : \"Auth Code\",\n"
            + "  \"name_on_card\" : \"Name On Card\",\n"
            + "  \"telephone_number\" : \"6625550144\",\n"
            + "  \"card_type\" : \"Card Type\",\n"
            + "  \"last_four_digits\" : \"Last Four Digits\",\n"
            + "  \"first_six_digits\" : \"First Six Digits\"\n"
            + "}",
        withNameOnCardResult
            .withPaymentOutcome(new PaymentOutcome("Status"))
            .withProcessorId("42")
            .withProviderId("42")
            .withReference("Reference")
            .withTelephoneNumber("6625550144")
            .build()
            .toConnectorPayload());
  }

  /**
   * Test {@link CreateTelephonePaymentRequest#toConnectorPayload()}.
   *
   * <p>Method under test: {@link CreateTelephonePaymentRequest#toConnectorPayload()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CreateTelephonePaymentRequest.toConnectorPayload()"})
  public void testToConnectorPayload8() {
    // Arrange
    Builder withNameOnCardResult =
        new Builder()
            .withAmount(10)
            .withAuthCode("Auth Code")
            .withAuthorisedDate("2020-03-01")
            .withCardExpiry("Card Expiry")
            .withCardType("Card Type")
            .withCreatedDate("2020-03-01")
            .withDescription("The characteristics of someone or something")
            .withEmailAddress("42 Main St")
            .withFirstSixDigits(null)
            .withLastFourDigits("Last Four Digits")
            .withNameOnCard("Name On Card");

    // Act and Assert
    assertEquals(
        "{\n"
            + "  \"amount\" : 10,\n"
            + "  \"reference\" : \"Reference\",\n"
            + "  \"description\" : \"The characteristics of someone or something\",\n"
            + "  \"processor_id\" : \"42\",\n"
            + "  \"provider_id\" : \"42\",\n"
            + "  \"payment_outcome\" : {\n"
            + "    \"status\" : \"Status\"\n"
            + "  },\n"
            + "  \"card_expiry\" : \"Card Expiry\",\n"
            + "  \"created_date\" : \"2020-03-01\",\n"
            + "  \"authorised_date\" : \"2020-03-01\",\n"
            + "  \"auth_code\" : \"Auth Code\",\n"
            + "  \"name_on_card\" : \"Name On Card\",\n"
            + "  \"email_address\" : \"42 Main St\",\n"
            + "  \"telephone_number\" : \"6625550144\",\n"
            + "  \"card_type\" : \"Card Type\",\n"
            + "  \"last_four_digits\" : \"Last Four Digits\"\n"
            + "}",
        withNameOnCardResult
            .withPaymentOutcome(new PaymentOutcome("Status"))
            .withProcessorId("42")
            .withProviderId("42")
            .withReference("Reference")
            .withTelephoneNumber("6625550144")
            .build()
            .toConnectorPayload());
  }

  /**
   * Test {@link CreateTelephonePaymentRequest#toConnectorPayload()}.
   *
   * <p>Method under test: {@link CreateTelephonePaymentRequest#toConnectorPayload()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CreateTelephonePaymentRequest.toConnectorPayload()"})
  public void testToConnectorPayload9() {
    // Arrange
    Builder withNameOnCardResult =
        new Builder()
            .withAmount(10)
            .withAuthCode("Auth Code")
            .withAuthorisedDate("2020-03-01")
            .withCardExpiry("Card Expiry")
            .withCardType("Card Type")
            .withCreatedDate("2020-03-01")
            .withDescription("The characteristics of someone or something")
            .withEmailAddress("42 Main St")
            .withFirstSixDigits("First Six Digits")
            .withLastFourDigits(null)
            .withNameOnCard("Name On Card");

    // Act and Assert
    assertEquals(
        "{\n"
            + "  \"amount\" : 10,\n"
            + "  \"reference\" : \"Reference\",\n"
            + "  \"description\" : \"The characteristics of someone or something\",\n"
            + "  \"processor_id\" : \"42\",\n"
            + "  \"provider_id\" : \"42\",\n"
            + "  \"payment_outcome\" : {\n"
            + "    \"status\" : \"Status\"\n"
            + "  },\n"
            + "  \"card_expiry\" : \"Card Expiry\",\n"
            + "  \"created_date\" : \"2020-03-01\",\n"
            + "  \"authorised_date\" : \"2020-03-01\",\n"
            + "  \"auth_code\" : \"Auth Code\",\n"
            + "  \"name_on_card\" : \"Name On Card\",\n"
            + "  \"email_address\" : \"42 Main St\",\n"
            + "  \"telephone_number\" : \"6625550144\",\n"
            + "  \"card_type\" : \"Card Type\",\n"
            + "  \"first_six_digits\" : \"First Six Digits\"\n"
            + "}",
        withNameOnCardResult
            .withPaymentOutcome(new PaymentOutcome("Status"))
            .withProcessorId("42")
            .withProviderId("42")
            .withReference("Reference")
            .withTelephoneNumber("6625550144")
            .build()
            .toConnectorPayload());
  }

  /**
   * Test {@link CreateTelephonePaymentRequest#toConnectorPayload()}.
   *
   * <p>Method under test: {@link CreateTelephonePaymentRequest#toConnectorPayload()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CreateTelephonePaymentRequest.toConnectorPayload()"})
  public void testToConnectorPayload10() {
    // Arrange
    Builder withNameOnCardResult =
        new Builder()
            .withAmount(10)
            .withAuthCode("Auth Code")
            .withAuthorisedDate("2020-03-01")
            .withCardExpiry("Card Expiry")
            .withCardType("Card Type")
            .withCreatedDate("2020-03-01")
            .withDescription("The characteristics of someone or something")
            .withEmailAddress("42 Main St")
            .withFirstSixDigits("First Six Digits")
            .withLastFourDigits("Last Four Digits")
            .withNameOnCard(null);

    // Act and Assert
    assertEquals(
        "{\n"
            + "  \"amount\" : 10,\n"
            + "  \"reference\" : \"Reference\",\n"
            + "  \"description\" : \"The characteristics of someone or something\",\n"
            + "  \"processor_id\" : \"42\",\n"
            + "  \"provider_id\" : \"42\",\n"
            + "  \"payment_outcome\" : {\n"
            + "    \"status\" : \"Status\"\n"
            + "  },\n"
            + "  \"card_expiry\" : \"Card Expiry\",\n"
            + "  \"created_date\" : \"2020-03-01\",\n"
            + "  \"authorised_date\" : \"2020-03-01\",\n"
            + "  \"auth_code\" : \"Auth Code\",\n"
            + "  \"email_address\" : \"42 Main St\",\n"
            + "  \"telephone_number\" : \"6625550144\",\n"
            + "  \"card_type\" : \"Card Type\",\n"
            + "  \"last_four_digits\" : \"Last Four Digits\",\n"
            + "  \"first_six_digits\" : \"First Six Digits\"\n"
            + "}",
        withNameOnCardResult
            .withPaymentOutcome(new PaymentOutcome("Status"))
            .withProcessorId("42")
            .withProviderId("42")
            .withReference("Reference")
            .withTelephoneNumber("6625550144")
            .build()
            .toConnectorPayload());
  }

  /**
   * Test {@link CreateTelephonePaymentRequest#toConnectorPayload()}.
   *
   * <p>Method under test: {@link CreateTelephonePaymentRequest#toConnectorPayload()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CreateTelephonePaymentRequest.toConnectorPayload()"})
  public void testToConnectorPayload11() {
    // Arrange
    Builder withNameOnCardResult =
        new Builder()
            .withAmount(10)
            .withAuthCode("Auth Code")
            .withAuthorisedDate("2020-03-01")
            .withCardExpiry("Card Expiry")
            .withCardType("Card Type")
            .withCreatedDate("2020-03-01")
            .withDescription("The characteristics of someone or something")
            .withEmailAddress("42 Main St")
            .withFirstSixDigits("First Six Digits")
            .withLastFourDigits("Last Four Digits")
            .withNameOnCard("Name On Card");
    PaymentOutcome paymentOutcome =
        new PaymentOutcome(
            "amount", "amount", new Supplemental("An error occurred", "An error occurred"));

    // Act and Assert
    assertEquals(
        "{\n"
            + "  \"amount\" : 10,\n"
            + "  \"reference\" : \"Reference\",\n"
            + "  \"description\" : \"The characteristics of someone or something\",\n"
            + "  \"processor_id\" : \"42\",\n"
            + "  \"provider_id\" : \"42\",\n"
            + "  \"payment_outcome\" : {\n"
            + "    \"status\" : \"amount\",\n"
            + "    \"code\" : \"amount\",\n"
            + "    \"supplemental\" : {\n"
            + "      \"error_code\" : \"An error occurred\",\n"
            + "      \"error_message\" : \"An error occurred\"\n"
            + "    }\n"
            + "  },\n"
            + "  \"card_expiry\" : \"Card Expiry\",\n"
            + "  \"created_date\" : \"2020-03-01\",\n"
            + "  \"authorised_date\" : \"2020-03-01\",\n"
            + "  \"auth_code\" : \"Auth Code\",\n"
            + "  \"name_on_card\" : \"Name On Card\",\n"
            + "  \"email_address\" : \"42 Main St\",\n"
            + "  \"telephone_number\" : \"6625550144\",\n"
            + "  \"card_type\" : \"Card Type\",\n"
            + "  \"last_four_digits\" : \"Last Four Digits\",\n"
            + "  \"first_six_digits\" : \"First Six Digits\"\n"
            + "}",
        withNameOnCardResult
            .withPaymentOutcome(paymentOutcome)
            .withProcessorId("42")
            .withProviderId("42")
            .withReference("Reference")
            .withTelephoneNumber("6625550144")
            .build()
            .toConnectorPayload());
  }

  /**
   * Test {@link CreateTelephonePaymentRequest#toConnectorPayload()}.
   *
   * <p>Method under test: {@link CreateTelephonePaymentRequest#toConnectorPayload()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CreateTelephonePaymentRequest.toConnectorPayload()"})
  public void testToConnectorPayload12() {
    // Arrange
    Builder withNameOnCardResult =
        new Builder()
            .withAmount(10)
            .withAuthCode("Auth Code")
            .withAuthorisedDate("2020-03-01")
            .withCardExpiry("Card Expiry")
            .withCardType("Card Type")
            .withCreatedDate("2020-03-01")
            .withDescription("The characteristics of someone or something")
            .withEmailAddress("42 Main St")
            .withFirstSixDigits("First Six Digits")
            .withLastFourDigits("Last Four Digits")
            .withNameOnCard("Name On Card");

    // Act and Assert
    assertEquals(
        "{\n"
            + "  \"amount\" : 10,\n"
            + "  \"reference\" : \"Reference\",\n"
            + "  \"description\" : \"The characteristics of someone or something\",\n"
            + "  \"processor_id\" : \"42\",\n"
            + "  \"provider_id\" : \"42\",\n"
            + "  \"payment_outcome\" : {\n"
            + "    \"status\" : \"Status\"\n"
            + "  },\n"
            + "  \"card_expiry\" : \"Card Expiry\",\n"
            + "  \"created_date\" : \"2020-03-01\",\n"
            + "  \"authorised_date\" : \"2020-03-01\",\n"
            + "  \"auth_code\" : \"Auth Code\",\n"
            + "  \"name_on_card\" : \"Name On Card\",\n"
            + "  \"email_address\" : \"42 Main St\",\n"
            + "  \"card_type\" : \"Card Type\",\n"
            + "  \"last_four_digits\" : \"Last Four Digits\",\n"
            + "  \"first_six_digits\" : \"First Six Digits\"\n"
            + "}",
        withNameOnCardResult
            .withPaymentOutcome(new PaymentOutcome("Status"))
            .withProcessorId("42")
            .withProviderId("42")
            .withReference("Reference")
            .withTelephoneNumber(null)
            .build()
            .toConnectorPayload());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>Then return Description is {@code null}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CreateTelephonePaymentRequest#CreateTelephonePaymentRequest()}
   *   <li>{@link CreateTelephonePaymentRequest#getAmount()}
   *   <li>{@link CreateTelephonePaymentRequest#getDescription()}
   *   <li>{@link CreateTelephonePaymentRequest#getPaymentOutcome()}
   *   <li>{@link CreateTelephonePaymentRequest#getProcessorId()}
   *   <li>{@link CreateTelephonePaymentRequest#getProviderId()}
   *   <li>{@link CreateTelephonePaymentRequest#getReference()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CreateTelephonePaymentRequest.<init>()",
    "void CreateTelephonePaymentRequest.<init>(int, String, String, String, String, String, String, String, PaymentOutcome, String, String, String, String, String, String, String)",
    "int CreateTelephonePaymentRequest.getAmount()",
    "String CreateTelephonePaymentRequest.getDescription()",
    "PaymentOutcome CreateTelephonePaymentRequest.getPaymentOutcome()",
    "String CreateTelephonePaymentRequest.getProcessorId()",
    "String CreateTelephonePaymentRequest.getProviderId()",
    "String CreateTelephonePaymentRequest.getReference()"
  })
  public void testGettersAndSetters_thenReturnDescriptionIsNull() {
    // Arrange and Act
    CreateTelephonePaymentRequest actualCreateTelephonePaymentRequest =
        new CreateTelephonePaymentRequest();
    int actualAmount = actualCreateTelephonePaymentRequest.getAmount();
    String actualDescription = actualCreateTelephonePaymentRequest.getDescription();
    PaymentOutcome actualPaymentOutcome = actualCreateTelephonePaymentRequest.getPaymentOutcome();
    String actualProcessorId = actualCreateTelephonePaymentRequest.getProcessorId();
    String actualProviderId = actualCreateTelephonePaymentRequest.getProviderId();

    // Assert
    assertNull(actualDescription);
    assertNull(actualProcessorId);
    assertNull(actualProviderId);
    assertNull(actualCreateTelephonePaymentRequest.getReference());
    assertNull(actualPaymentOutcome);
    assertEquals(0, actualAmount);
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return ProcessorId is {@code 42}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CreateTelephonePaymentRequest#CreateTelephonePaymentRequest(int, String, String,
   *       String, String, String, String, String, PaymentOutcome, String, String, String, String,
   *       String, String, String)}
   *   <li>{@link CreateTelephonePaymentRequest#getAmount()}
   *   <li>{@link CreateTelephonePaymentRequest#getDescription()}
   *   <li>{@link CreateTelephonePaymentRequest#getPaymentOutcome()}
   *   <li>{@link CreateTelephonePaymentRequest#getProcessorId()}
   *   <li>{@link CreateTelephonePaymentRequest#getProviderId()}
   *   <li>{@link CreateTelephonePaymentRequest#getReference()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CreateTelephonePaymentRequest.<init>()",
    "void CreateTelephonePaymentRequest.<init>(int, String, String, String, String, String, String, String, PaymentOutcome, String, String, String, String, String, String, String)",
    "int CreateTelephonePaymentRequest.getAmount()",
    "String CreateTelephonePaymentRequest.getDescription()",
    "PaymentOutcome CreateTelephonePaymentRequest.getPaymentOutcome()",
    "String CreateTelephonePaymentRequest.getProcessorId()",
    "String CreateTelephonePaymentRequest.getProviderId()",
    "String CreateTelephonePaymentRequest.getReference()"
  })
  public void testGettersAndSetters_whenTen_thenReturnProcessorIdIs42() {
    // Arrange
    PaymentOutcome paymentOutcome = new PaymentOutcome("Status");

    // Act
    CreateTelephonePaymentRequest actualCreateTelephonePaymentRequest =
        new CreateTelephonePaymentRequest(
            10,
            "Reference",
            "The characteristics of someone or something",
            "2020-03-01",
            "2020-03-01",
            "42",
            "42",
            "Auth Code",
            paymentOutcome,
            "Card Type",
            "Name On Card",
            "42 Main St",
            "Card Expiry",
            "Last Four Digits",
            "First Six Digits",
            "6625550144");
    int actualAmount = actualCreateTelephonePaymentRequest.getAmount();
    String actualDescription = actualCreateTelephonePaymentRequest.getDescription();
    PaymentOutcome actualPaymentOutcome = actualCreateTelephonePaymentRequest.getPaymentOutcome();
    String actualProcessorId = actualCreateTelephonePaymentRequest.getProcessorId();
    String actualProviderId = actualCreateTelephonePaymentRequest.getProviderId();

    // Assert
    assertEquals("42", actualProcessorId);
    assertEquals("42", actualProviderId);
    assertEquals("Reference", actualCreateTelephonePaymentRequest.getReference());
    assertEquals("The characteristics of someone or something", actualDescription);
    assertEquals(10, actualAmount);
    assertSame(paymentOutcome, actualPaymentOutcome);
  }

  /**
   * Test {@link CreateTelephonePaymentRequest#CreateTelephonePaymentRequest(Builder)}.
   *
   * <ul>
   *   <li>When {@link Builder} (default constructor).
   *   <li>Then return Description is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * CreateTelephonePaymentRequest#CreateTelephonePaymentRequest(Builder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CreateTelephonePaymentRequest.<init>(Builder)"})
  public void testNewCreateTelephonePaymentRequest_whenBuilder_thenReturnDescriptionIsNull() {
    // Arrange and Act
    CreateTelephonePaymentRequest actualCreateTelephonePaymentRequest =
        new CreateTelephonePaymentRequest(new Builder());

    // Assert
    assertNull(actualCreateTelephonePaymentRequest.getDescription());
    assertNull(actualCreateTelephonePaymentRequest.getProcessorId());
    assertNull(actualCreateTelephonePaymentRequest.getProviderId());
    assertNull(actualCreateTelephonePaymentRequest.getReference());
    assertNull(actualCreateTelephonePaymentRequest.getPaymentOutcome());
    assertEquals(0, actualCreateTelephonePaymentRequest.getAmount());
    Optional<String> authCode = actualCreateTelephonePaymentRequest.getAuthCode();
    assertFalse(authCode.isPresent());
    assertSame(authCode, actualCreateTelephonePaymentRequest.getAuthorisedDate());
    assertSame(authCode, actualCreateTelephonePaymentRequest.getCardExpiry());
    assertSame(authCode, actualCreateTelephonePaymentRequest.getCardType());
    assertSame(authCode, actualCreateTelephonePaymentRequest.getCreatedDate());
    assertSame(authCode, actualCreateTelephonePaymentRequest.getEmailAddress());
    assertSame(authCode, actualCreateTelephonePaymentRequest.getFirstSixDigits());
    assertSame(authCode, actualCreateTelephonePaymentRequest.getLastFourDigits());
    assertSame(authCode, actualCreateTelephonePaymentRequest.getNameOnCard());
    assertSame(authCode, actualCreateTelephonePaymentRequest.getTelephoneNumber());
  }

  /**
   * Test {@link CreateTelephonePaymentRequest#getCreatedDate()}.
   *
   * <p>Method under test: {@link CreateTelephonePaymentRequest#getCreatedDate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional CreateTelephonePaymentRequest.getCreatedDate()"})
  public void testGetCreatedDate() {
    // Arrange, Act and Assert
    assertFalse(new CreateTelephonePaymentRequest().getCreatedDate().isPresent());
  }

  /**
   * Test {@link CreateTelephonePaymentRequest#getAuthorisedDate()}.
   *
   * <p>Method under test: {@link CreateTelephonePaymentRequest#getAuthorisedDate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional CreateTelephonePaymentRequest.getAuthorisedDate()"})
  public void testGetAuthorisedDate() {
    // Arrange, Act and Assert
    assertFalse(new CreateTelephonePaymentRequest().getAuthorisedDate().isPresent());
  }

  /**
   * Test {@link CreateTelephonePaymentRequest#getAuthCode()}.
   *
   * <p>Method under test: {@link CreateTelephonePaymentRequest#getAuthCode()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional CreateTelephonePaymentRequest.getAuthCode()"})
  public void testGetAuthCode() {
    // Arrange, Act and Assert
    assertFalse(new CreateTelephonePaymentRequest().getAuthCode().isPresent());
  }

  /**
   * Test {@link CreateTelephonePaymentRequest#getCardType()}.
   *
   * <p>Method under test: {@link CreateTelephonePaymentRequest#getCardType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional CreateTelephonePaymentRequest.getCardType()"})
  public void testGetCardType() {
    // Arrange, Act and Assert
    assertFalse(new CreateTelephonePaymentRequest().getCardType().isPresent());
  }

  /**
   * Test {@link CreateTelephonePaymentRequest#getNameOnCard()}.
   *
   * <p>Method under test: {@link CreateTelephonePaymentRequest#getNameOnCard()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional CreateTelephonePaymentRequest.getNameOnCard()"})
  public void testGetNameOnCard() {
    // Arrange, Act and Assert
    assertFalse(new CreateTelephonePaymentRequest().getNameOnCard().isPresent());
  }

  /**
   * Test {@link CreateTelephonePaymentRequest#getEmailAddress()}.
   *
   * <p>Method under test: {@link CreateTelephonePaymentRequest#getEmailAddress()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional CreateTelephonePaymentRequest.getEmailAddress()"})
  public void testGetEmailAddress() {
    // Arrange, Act and Assert
    assertFalse(new CreateTelephonePaymentRequest().getEmailAddress().isPresent());
  }

  /**
   * Test {@link CreateTelephonePaymentRequest#getCardExpiry()}.
   *
   * <p>Method under test: {@link CreateTelephonePaymentRequest#getCardExpiry()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional CreateTelephonePaymentRequest.getCardExpiry()"})
  public void testGetCardExpiry() {
    // Arrange, Act and Assert
    assertFalse(new CreateTelephonePaymentRequest().getCardExpiry().isPresent());
  }

  /**
   * Test {@link CreateTelephonePaymentRequest#getLastFourDigits()}.
   *
   * <p>Method under test: {@link CreateTelephonePaymentRequest#getLastFourDigits()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional CreateTelephonePaymentRequest.getLastFourDigits()"})
  public void testGetLastFourDigits() {
    // Arrange, Act and Assert
    assertFalse(new CreateTelephonePaymentRequest().getLastFourDigits().isPresent());
  }

  /**
   * Test {@link CreateTelephonePaymentRequest#getFirstSixDigits()}.
   *
   * <p>Method under test: {@link CreateTelephonePaymentRequest#getFirstSixDigits()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional CreateTelephonePaymentRequest.getFirstSixDigits()"})
  public void testGetFirstSixDigits() {
    // Arrange, Act and Assert
    assertFalse(new CreateTelephonePaymentRequest().getFirstSixDigits().isPresent());
  }

  /**
   * Test {@link CreateTelephonePaymentRequest#getTelephoneNumber()}.
   *
   * <p>Method under test: {@link CreateTelephonePaymentRequest#getTelephoneNumber()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional CreateTelephonePaymentRequest.getTelephoneNumber()"})
  public void testGetTelephoneNumber() {
    // Arrange, Act and Assert
    assertFalse(new CreateTelephonePaymentRequest().getTelephoneNumber().isPresent());
  }
}
