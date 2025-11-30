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
import uk.gov.pay.api.model.ChargeFromResponse;
import uk.gov.pay.api.model.PaymentState;
import uk.gov.pay.api.model.telephone.TelephonePaymentResponse.Builder;

public class TelephonePaymentResponseDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>default or parameterless constructor of {@link Builder}
   *   <li>{@link Builder#withAmount(Long)}
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
   *   <li>{@link Builder#withPaymentId(String)}
   *   <li>{@link Builder#withPaymentOutcome(PaymentOutcome)}
   *   <li>{@link Builder#withProcessorId(String)}
   *   <li>{@link Builder#withProviderId(String)}
   *   <li>{@link Builder#withReference(String)}
   *   <li>{@link Builder#withState(PaymentState)}
   *   <li>{@link Builder#withTelephoneNumber(String)}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Builder.<init>()",
    "TelephonePaymentResponse Builder.build()",
    "Builder Builder.withAmount(Long)",
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
    "Builder Builder.withPaymentId(String)",
    "Builder Builder.withPaymentOutcome(PaymentOutcome)",
    "Builder Builder.withProcessorId(String)",
    "Builder Builder.withProviderId(String)",
    "Builder Builder.withReference(String)",
    "Builder Builder.withState(PaymentState)",
    "Builder Builder.withTelephoneNumber(String)"
  })
  public void testBuilderBuild() {
    // Arrange and Act
    Builder actualWithPaymentIdResult =
        new Builder()
            .withAmount(10L)
            .withAuthCode("Auth Code")
            .withAuthorisedDate("2020-03-01")
            .withCardExpiry("Card Expiry")
            .withCardType("Card Type")
            .withCreatedDate("2020-03-01")
            .withDescription("The characteristics of someone or something")
            .withEmailAddress("42 Main St")
            .withFirstSixDigits("First Six Digits")
            .withLastFourDigits("Last Four Digits")
            .withNameOnCard("Name On Card")
            .withPaymentId("42");
    PaymentOutcome paymentOutcome = new PaymentOutcome("Status");
    Builder actualWithReferenceResult =
        actualWithPaymentIdResult
            .withPaymentOutcome(paymentOutcome)
            .withProcessorId("42")
            .withProviderId("42")
            .withReference("Reference");
    PaymentState state = new PaymentState("Status", true);
    TelephonePaymentResponse actualTelephonePaymentResponse =
        actualWithReferenceResult.withState(state).withTelephoneNumber("6625550144").build();

    // Assert
    Optional<String> authorisedDate = actualTelephonePaymentResponse.getAuthorisedDate();
    assertEquals("2020-03-01", authorisedDate.get());
    Optional<String> emailAddress = actualTelephonePaymentResponse.getEmailAddress();
    assertEquals("42 Main St", emailAddress.get());
    assertEquals("42", actualTelephonePaymentResponse.getPaymentId());
    assertEquals("42", actualTelephonePaymentResponse.getProcessorId());
    assertEquals("42", actualTelephonePaymentResponse.getProviderId());
    Optional<String> telephoneNumber = actualTelephonePaymentResponse.getTelephoneNumber();
    assertEquals("6625550144", telephoneNumber.get());
    Optional<String> authCode = actualTelephonePaymentResponse.getAuthCode();
    assertEquals("Auth Code", authCode.get());
    assertEquals("Card Expiry", actualTelephonePaymentResponse.getCardExpiry());
    assertEquals("Card Type", actualTelephonePaymentResponse.getCardType());
    assertEquals("First Six Digits", actualTelephonePaymentResponse.getFirstSixDigits());
    assertEquals("Last Four Digits", actualTelephonePaymentResponse.getLastFourDigits());
    Optional<String> nameOnCard = actualTelephonePaymentResponse.getNameOnCard();
    assertEquals("Name On Card", nameOnCard.get());
    assertEquals("Reference", actualTelephonePaymentResponse.getReference());
    assertEquals(
        "The characteristics of someone or something",
        actualTelephonePaymentResponse.getDescription());
    assertEquals(10L, actualTelephonePaymentResponse.getAmount().longValue());
    assertTrue(authCode.isPresent());
    assertTrue(authorisedDate.isPresent());
    assertTrue(emailAddress.isPresent());
    assertTrue(nameOnCard.isPresent());
    assertTrue(telephoneNumber.isPresent());
    assertSame(state, actualTelephonePaymentResponse.getState());
    assertSame(paymentOutcome, actualTelephonePaymentResponse.getPaymentOutcome());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>Then return Amount is {@code null}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link TelephonePaymentResponse#TelephonePaymentResponse()}
   *   <li>{@link TelephonePaymentResponse#getAmount()}
   *   <li>{@link TelephonePaymentResponse#getCardExpiry()}
   *   <li>{@link TelephonePaymentResponse#getCardType()}
   *   <li>{@link TelephonePaymentResponse#getDescription()}
   *   <li>{@link TelephonePaymentResponse#getFirstSixDigits()}
   *   <li>{@link TelephonePaymentResponse#getLastFourDigits()}
   *   <li>{@link TelephonePaymentResponse#getPaymentId()}
   *   <li>{@link TelephonePaymentResponse#getPaymentOutcome()}
   *   <li>{@link TelephonePaymentResponse#getProcessorId()}
   *   <li>{@link TelephonePaymentResponse#getProviderId()}
   *   <li>{@link TelephonePaymentResponse#getReference()}
   *   <li>{@link TelephonePaymentResponse#getState()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TelephonePaymentResponse.<init>()",
    "void TelephonePaymentResponse.<init>(Long, String, String, String, String, String, String, String, PaymentOutcome, String, String, String, String, String, String, String, String, PaymentState)",
    "Long TelephonePaymentResponse.getAmount()",
    "String TelephonePaymentResponse.getCardExpiry()",
    "String TelephonePaymentResponse.getCardType()",
    "String TelephonePaymentResponse.getDescription()",
    "String TelephonePaymentResponse.getFirstSixDigits()",
    "String TelephonePaymentResponse.getLastFourDigits()",
    "String TelephonePaymentResponse.getPaymentId()",
    "PaymentOutcome TelephonePaymentResponse.getPaymentOutcome()",
    "String TelephonePaymentResponse.getProcessorId()",
    "String TelephonePaymentResponse.getProviderId()",
    "String TelephonePaymentResponse.getReference()",
    "PaymentState TelephonePaymentResponse.getState()"
  })
  public void testGettersAndSetters_thenReturnAmountIsNull() {
    // Arrange and Act
    TelephonePaymentResponse actualTelephonePaymentResponse = new TelephonePaymentResponse();
    Long actualAmount = actualTelephonePaymentResponse.getAmount();
    String actualCardExpiry = actualTelephonePaymentResponse.getCardExpiry();
    String actualCardType = actualTelephonePaymentResponse.getCardType();
    String actualDescription = actualTelephonePaymentResponse.getDescription();
    String actualFirstSixDigits = actualTelephonePaymentResponse.getFirstSixDigits();
    String actualLastFourDigits = actualTelephonePaymentResponse.getLastFourDigits();
    String actualPaymentId = actualTelephonePaymentResponse.getPaymentId();
    PaymentOutcome actualPaymentOutcome = actualTelephonePaymentResponse.getPaymentOutcome();
    String actualProcessorId = actualTelephonePaymentResponse.getProcessorId();
    String actualProviderId = actualTelephonePaymentResponse.getProviderId();
    String actualReference = actualTelephonePaymentResponse.getReference();

    // Assert
    assertNull(actualAmount);
    assertNull(actualCardExpiry);
    assertNull(actualCardType);
    assertNull(actualDescription);
    assertNull(actualFirstSixDigits);
    assertNull(actualLastFourDigits);
    assertNull(actualPaymentId);
    assertNull(actualProcessorId);
    assertNull(actualProviderId);
    assertNull(actualReference);
    assertNull(actualTelephonePaymentResponse.getState());
    assertNull(actualPaymentOutcome);
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return PaymentId is {@code 42}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link TelephonePaymentResponse#TelephonePaymentResponse(Long, String, String, String,
   *       String, String, String, String, PaymentOutcome, String, String, String, String, String,
   *       String, String, String, PaymentState)}
   *   <li>{@link TelephonePaymentResponse#getAmount()}
   *   <li>{@link TelephonePaymentResponse#getCardExpiry()}
   *   <li>{@link TelephonePaymentResponse#getCardType()}
   *   <li>{@link TelephonePaymentResponse#getDescription()}
   *   <li>{@link TelephonePaymentResponse#getFirstSixDigits()}
   *   <li>{@link TelephonePaymentResponse#getLastFourDigits()}
   *   <li>{@link TelephonePaymentResponse#getPaymentId()}
   *   <li>{@link TelephonePaymentResponse#getPaymentOutcome()}
   *   <li>{@link TelephonePaymentResponse#getProcessorId()}
   *   <li>{@link TelephonePaymentResponse#getProviderId()}
   *   <li>{@link TelephonePaymentResponse#getReference()}
   *   <li>{@link TelephonePaymentResponse#getState()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TelephonePaymentResponse.<init>()",
    "void TelephonePaymentResponse.<init>(Long, String, String, String, String, String, String, String, PaymentOutcome, String, String, String, String, String, String, String, String, PaymentState)",
    "Long TelephonePaymentResponse.getAmount()",
    "String TelephonePaymentResponse.getCardExpiry()",
    "String TelephonePaymentResponse.getCardType()",
    "String TelephonePaymentResponse.getDescription()",
    "String TelephonePaymentResponse.getFirstSixDigits()",
    "String TelephonePaymentResponse.getLastFourDigits()",
    "String TelephonePaymentResponse.getPaymentId()",
    "PaymentOutcome TelephonePaymentResponse.getPaymentOutcome()",
    "String TelephonePaymentResponse.getProcessorId()",
    "String TelephonePaymentResponse.getProviderId()",
    "String TelephonePaymentResponse.getReference()",
    "PaymentState TelephonePaymentResponse.getState()"
  })
  public void testGettersAndSetters_whenTen_thenReturnPaymentIdIs42() {
    // Arrange
    PaymentOutcome paymentOutcome = new PaymentOutcome("Status");
    PaymentState state = new PaymentState("Status", true);

    // Act
    TelephonePaymentResponse actualTelephonePaymentResponse =
        new TelephonePaymentResponse(
            10L,
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
            "6625550144",
            "42",
            state);
    Long actualAmount = actualTelephonePaymentResponse.getAmount();
    String actualCardExpiry = actualTelephonePaymentResponse.getCardExpiry();
    String actualCardType = actualTelephonePaymentResponse.getCardType();
    String actualDescription = actualTelephonePaymentResponse.getDescription();
    String actualFirstSixDigits = actualTelephonePaymentResponse.getFirstSixDigits();
    String actualLastFourDigits = actualTelephonePaymentResponse.getLastFourDigits();
    String actualPaymentId = actualTelephonePaymentResponse.getPaymentId();
    PaymentOutcome actualPaymentOutcome = actualTelephonePaymentResponse.getPaymentOutcome();
    String actualProcessorId = actualTelephonePaymentResponse.getProcessorId();
    String actualProviderId = actualTelephonePaymentResponse.getProviderId();
    String actualReference = actualTelephonePaymentResponse.getReference();
    PaymentState actualState = actualTelephonePaymentResponse.getState();

    // Assert
    assertEquals("42", actualPaymentId);
    assertEquals("42", actualProcessorId);
    assertEquals("42", actualProviderId);
    assertEquals("Card Expiry", actualCardExpiry);
    assertEquals("Card Type", actualCardType);
    assertEquals("First Six Digits", actualFirstSixDigits);
    assertEquals("Last Four Digits", actualLastFourDigits);
    assertEquals("Reference", actualReference);
    assertEquals("The characteristics of someone or something", actualDescription);
    assertEquals(10L, actualAmount.longValue());
    assertSame(state, actualState);
    assertSame(paymentOutcome, actualPaymentOutcome);
  }

  /**
   * Test {@link TelephonePaymentResponse#TelephonePaymentResponse(Builder)}.
   *
   * <ul>
   *   <li>When {@link Builder} (default constructor).
   *   <li>Then return Amount is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link TelephonePaymentResponse#TelephonePaymentResponse(Builder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TelephonePaymentResponse.<init>(Builder)"})
  public void testNewTelephonePaymentResponse_whenBuilder_thenReturnAmountIsNull() {
    // Arrange and Act
    TelephonePaymentResponse actualTelephonePaymentResponse =
        new TelephonePaymentResponse(new Builder());

    // Assert
    assertNull(actualTelephonePaymentResponse.getAmount());
    assertNull(actualTelephonePaymentResponse.getCardExpiry());
    assertNull(actualTelephonePaymentResponse.getCardType());
    assertNull(actualTelephonePaymentResponse.getDescription());
    assertNull(actualTelephonePaymentResponse.getFirstSixDigits());
    assertNull(actualTelephonePaymentResponse.getLastFourDigits());
    assertNull(actualTelephonePaymentResponse.getPaymentId());
    assertNull(actualTelephonePaymentResponse.getProcessorId());
    assertNull(actualTelephonePaymentResponse.getProviderId());
    assertNull(actualTelephonePaymentResponse.getReference());
    assertNull(actualTelephonePaymentResponse.getState());
    assertNull(actualTelephonePaymentResponse.getPaymentOutcome());
    Optional<String> authCode = actualTelephonePaymentResponse.getAuthCode();
    assertFalse(authCode.isPresent());
    assertSame(authCode, actualTelephonePaymentResponse.getAuthorisedDate());
    assertSame(authCode, actualTelephonePaymentResponse.getCreatedDate());
    assertSame(authCode, actualTelephonePaymentResponse.getEmailAddress());
    assertSame(authCode, actualTelephonePaymentResponse.getNameOnCard());
    assertSame(authCode, actualTelephonePaymentResponse.getTelephoneNumber());
  }

  /**
   * Test {@link TelephonePaymentResponse#getCreatedDate()}.
   *
   * <p>Method under test: {@link TelephonePaymentResponse#getCreatedDate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional TelephonePaymentResponse.getCreatedDate()"})
  public void testGetCreatedDate() {
    // Arrange, Act and Assert
    assertFalse(new TelephonePaymentResponse().getCreatedDate().isPresent());
  }

  /**
   * Test {@link TelephonePaymentResponse#getAuthorisedDate()}.
   *
   * <p>Method under test: {@link TelephonePaymentResponse#getAuthorisedDate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional TelephonePaymentResponse.getAuthorisedDate()"})
  public void testGetAuthorisedDate() {
    // Arrange, Act and Assert
    assertFalse(new TelephonePaymentResponse().getAuthorisedDate().isPresent());
  }

  /**
   * Test {@link TelephonePaymentResponse#getAuthCode()}.
   *
   * <p>Method under test: {@link TelephonePaymentResponse#getAuthCode()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional TelephonePaymentResponse.getAuthCode()"})
  public void testGetAuthCode() {
    // Arrange, Act and Assert
    assertFalse(new TelephonePaymentResponse().getAuthCode().isPresent());
  }

  /**
   * Test {@link TelephonePaymentResponse#getNameOnCard()}.
   *
   * <p>Method under test: {@link TelephonePaymentResponse#getNameOnCard()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional TelephonePaymentResponse.getNameOnCard()"})
  public void testGetNameOnCard() {
    // Arrange, Act and Assert
    assertFalse(new TelephonePaymentResponse().getNameOnCard().isPresent());
  }

  /**
   * Test {@link TelephonePaymentResponse#getEmailAddress()}.
   *
   * <p>Method under test: {@link TelephonePaymentResponse#getEmailAddress()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional TelephonePaymentResponse.getEmailAddress()"})
  public void testGetEmailAddress() {
    // Arrange, Act and Assert
    assertFalse(new TelephonePaymentResponse().getEmailAddress().isPresent());
  }

  /**
   * Test {@link TelephonePaymentResponse#getTelephoneNumber()}.
   *
   * <p>Method under test: {@link TelephonePaymentResponse#getTelephoneNumber()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional TelephonePaymentResponse.getTelephoneNumber()"})
  public void testGetTelephoneNumber() {
    // Arrange, Act and Assert
    assertFalse(new TelephonePaymentResponse().getTelephoneNumber().isPresent());
  }

  /**
   * Test {@link TelephonePaymentResponse#from(ChargeFromResponse)}.
   *
   * <ul>
   *   <li>When {@link ChargeFromResponse} (default constructor).
   *   <li>Then return CardType is empty string.
   * </ul>
   *
   * <p>Method under test: {@link TelephonePaymentResponse#from(ChargeFromResponse)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TelephonePaymentResponse TelephonePaymentResponse.from(ChargeFromResponse)"})
  public void testFrom_whenChargeFromResponse_thenReturnCardTypeIsEmptyString() {
    // Arrange and Act
    TelephonePaymentResponse actualFromResult =
        TelephonePaymentResponse.from(new ChargeFromResponse());

    // Assert
    assertEquals("", actualFromResult.getCardType());
    assertNull(actualFromResult.getAmount());
    assertNull(actualFromResult.getCardExpiry());
    assertNull(actualFromResult.getDescription());
    assertNull(actualFromResult.getFirstSixDigits());
    assertNull(actualFromResult.getLastFourDigits());
    assertNull(actualFromResult.getPaymentId());
    assertNull(actualFromResult.getProcessorId());
    assertNull(actualFromResult.getProviderId());
    assertNull(actualFromResult.getReference());
    assertNull(actualFromResult.getState());
    assertNull(actualFromResult.getPaymentOutcome());
    Optional<String> authCode = actualFromResult.getAuthCode();
    assertFalse(authCode.isPresent());
    assertSame(authCode, actualFromResult.getAuthorisedDate());
    assertSame(authCode, actualFromResult.getCreatedDate());
    assertSame(authCode, actualFromResult.getEmailAddress());
    assertSame(authCode, actualFromResult.getNameOnCard());
    assertSame(authCode, actualFromResult.getTelephoneNumber());
  }
}
