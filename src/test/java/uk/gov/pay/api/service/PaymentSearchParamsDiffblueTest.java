package uk.gov.pay.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.service.PaymentSearchParams.Builder;

class PaymentSearchParamsDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>default or parameterless constructor of {@link Builder}
   *   <li>{@link Builder#withAgreementId(String)}
   *   <li>{@link Builder#withCardHolderName(String)}
   *   <li>{@link Builder#withDisplaySize(String)}
   *   <li>{@link Builder#withEmail(String)}
   *   <li>{@link Builder#withFirstDigitsCardNumber(String)}
   *   <li>{@link Builder#withFromDate(String)}
   *   <li>{@link Builder#withFromSettledDate(String)}
   *   <li>{@link Builder#withLastDigitsCardNumber(String)}
   *   <li>{@link Builder#withPageNumber(String)}
   *   <li>{@link Builder#withReference(String)}
   *   <li>{@link Builder#withState(String)}
   *   <li>{@link Builder#withToDate(String)}
   *   <li>{@link Builder#withToSettledDate(String)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Builder.<init>()",
    "PaymentSearchParams Builder.build()",
    "Builder Builder.withAgreementId(String)",
    "Builder Builder.withCardHolderName(String)",
    "Builder Builder.withDisplaySize(String)",
    "Builder Builder.withEmail(String)",
    "Builder Builder.withFirstDigitsCardNumber(String)",
    "Builder Builder.withFromDate(String)",
    "Builder Builder.withFromSettledDate(String)",
    "Builder Builder.withLastDigitsCardNumber(String)",
    "Builder Builder.withPageNumber(String)",
    "Builder Builder.withReference(String)",
    "Builder Builder.withState(String)",
    "Builder Builder.withToDate(String)",
    "Builder Builder.withToSettledDate(String)"
  })
  void testBuilderBuild() {
    // Arrange and Act
    PaymentSearchParams actualPaymentSearchParams =
        new Builder()
            .withAgreementId("42")
            .withCardBrand("Card Brand")
            .withCardHolderName("Card Holder Name")
            .withDisplaySize("Display Size")
            .withEmail("jane.doe@example.org")
            .withFirstDigitsCardNumber("42")
            .withFromDate("2020-03-01")
            .withFromSettledDate("2020-03-01")
            .withLastDigitsCardNumber("42")
            .withPageNumber("42")
            .withReference("Reference")
            .withState("MD")
            .withToDate("2020-03-01")
            .withToSettledDate("2020-03-01")
            .build();

    // Assert
    assertEquals("2020-03-01", actualPaymentSearchParams.getFromDate());
    assertEquals("2020-03-01", actualPaymentSearchParams.getFromSettledDate());
    assertEquals("2020-03-01", actualPaymentSearchParams.getToDate());
    assertEquals("2020-03-01", actualPaymentSearchParams.getToSettledDate());
    Map<String, String> paramsAsMap = actualPaymentSearchParams.getParamsAsMap();
    assertEquals(14, paramsAsMap.size());
    assertEquals("42", paramsAsMap.get(PaymentSearchParams.FIRST_DIGITS_CARD_NUMBER_KEY));
    assertEquals("42", actualPaymentSearchParams.getAgreementId());
    assertEquals("42", actualPaymentSearchParams.getFirstDigitsCardNumber());
    assertEquals("42", actualPaymentSearchParams.getLastDigitsCardNumber());
    assertEquals("42", actualPaymentSearchParams.getPageNumber());
    assertEquals("Card Holder Name", paramsAsMap.get(PaymentSearchParams.CARDHOLDER_NAME_KEY));
    assertEquals("Display Size", actualPaymentSearchParams.getDisplaySize());
    assertEquals("MD", paramsAsMap.get(PaymentSearchParams.STATE_KEY));
    assertEquals("MD", actualPaymentSearchParams.getState());
    assertEquals("Reference", paramsAsMap.get(PaymentSearchParams.REFERENCE_KEY));
    assertEquals("Reference", actualPaymentSearchParams.getReference());
    assertEquals("card brand", paramsAsMap.get(PaymentSearchParams.CARD_BRAND_KEY));
    assertEquals("card brand", actualPaymentSearchParams.getCardBrand());
    assertEquals("jane.doe@example.org", paramsAsMap.get(PaymentSearchParams.EMAIL_KEY));
    assertEquals("jane.doe@example.org", actualPaymentSearchParams.getEmail());
  }

  /**
   * Test Builder {@link Builder#withCardBrand(String)}.
   *
   * <ul>
   *   <li>Then {@link Builder} (default constructor) build ParamsAsMap {@link
   *       PaymentSearchParams#CARD_BRAND_KEY} is {@code card brand}.
   * </ul>
   *
   * <p>Method under test: {@link Builder#withCardBrand(String)}
   */
  @Test
  @DisplayName(
      "Test Builder withCardBrand(String); then Builder (default constructor) build ParamsAsMap CARD_BRAND_KEY is 'card brand'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.withCardBrand(String)"})
  void testBuilderWithCardBrand_thenBuilderBuildParamsAsMapCard_brand_keyIsCardBrand() {
    // Arrange
    Builder builder = new Builder();

    // Act
    Builder actualWithCardBrandResult = builder.withCardBrand("Card Brand");

    // Assert
    PaymentSearchParams paymentSearchParams = builder.build();
    Map<String, String> paramsAsMap = paymentSearchParams.getParamsAsMap();
    assertEquals(14, paramsAsMap.size());
    assertEquals("card brand", paramsAsMap.get(PaymentSearchParams.CARD_BRAND_KEY));
    assertEquals("card brand", paymentSearchParams.getCardBrand());
    assertTrue(paramsAsMap.containsKey(PaymentSearchParams.EMAIL_KEY));
    assertTrue(paramsAsMap.containsKey(PaymentSearchParams.REFERENCE_KEY));
    assertTrue(paramsAsMap.containsKey(PaymentSearchParams.STATE_KEY));
    assertSame(builder, actualWithCardBrandResult);
  }

  /**
   * Test Builder {@link Builder#withCardBrand(String)}.
   *
   * <ul>
   *   <li>Then {@link Builder} (default constructor) build ParamsAsMap {@link
   *       PaymentSearchParams#CARD_BRAND_KEY} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Builder#withCardBrand(String)}
   */
  @Test
  @DisplayName(
      "Test Builder withCardBrand(String); then Builder (default constructor) build ParamsAsMap CARD_BRAND_KEY is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.withCardBrand(String)"})
  void testBuilderWithCardBrand_thenBuilderBuildParamsAsMapCard_brand_keyIsNull() {
    // Arrange
    Builder builder = new Builder();

    // Act
    Builder actualWithCardBrandResult = builder.withCardBrand(null);

    // Assert
    PaymentSearchParams paymentSearchParams = builder.build();
    Map<String, String> paramsAsMap = paymentSearchParams.getParamsAsMap();
    assertEquals(14, paramsAsMap.size());
    assertNull(paramsAsMap.get(PaymentSearchParams.CARD_BRAND_KEY));
    assertNull(paymentSearchParams.getCardBrand());
    assertTrue(paramsAsMap.containsKey(PaymentSearchParams.EMAIL_KEY));
    assertTrue(paramsAsMap.containsKey(PaymentSearchParams.REFERENCE_KEY));
    assertTrue(paramsAsMap.containsKey(PaymentSearchParams.STATE_KEY));
    assertSame(builder, actualWithCardBrandResult);
  }

  /**
   * Test Builder {@link Builder#withCardBrand(String)}.
   *
   * <ul>
   *   <li>Then {@link Builder} (default constructor) build ParamsAsMap {@link
   *       PaymentSearchParams#CARD_BRAND_KEY} is space.
   * </ul>
   *
   * <p>Method under test: {@link Builder#withCardBrand(String)}
   */
  @Test
  @DisplayName(
      "Test Builder withCardBrand(String); then Builder (default constructor) build ParamsAsMap CARD_BRAND_KEY is space")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.withCardBrand(String)"})
  void testBuilderWithCardBrand_thenBuilderBuildParamsAsMapCard_brand_keyIsSpace() {
    // Arrange
    Builder builder = new Builder();

    // Act
    Builder actualWithCardBrandResult = builder.withCardBrand(" ");

    // Assert
    PaymentSearchParams paymentSearchParams = builder.build();
    Map<String, String> paramsAsMap = paymentSearchParams.getParamsAsMap();
    assertEquals(14, paramsAsMap.size());
    assertEquals(" ", paramsAsMap.get(PaymentSearchParams.CARD_BRAND_KEY));
    assertEquals(" ", paymentSearchParams.getCardBrand());
    assertTrue(paramsAsMap.containsKey(PaymentSearchParams.EMAIL_KEY));
    assertTrue(paramsAsMap.containsKey(PaymentSearchParams.REFERENCE_KEY));
    assertTrue(paramsAsMap.containsKey(PaymentSearchParams.STATE_KEY));
    assertSame(builder, actualWithCardBrandResult);
  }

  /**
   * Test {@link PaymentSearchParams#PaymentSearchParams(Builder)}.
   *
   * <ul>
   *   <li>When {@link Builder} (default constructor).
   *   <li>Then return AgreementId is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentSearchParams#PaymentSearchParams(Builder)}
   */
  @Test
  @DisplayName(
      "Test new PaymentSearchParams(Builder); when Builder (default constructor); then return AgreementId is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentSearchParams.<init>(Builder)"})
  void testNewPaymentSearchParams_whenBuilder_thenReturnAgreementIdIsNull() {
    // Arrange and Act
    PaymentSearchParams actualPaymentSearchParams = new PaymentSearchParams(new Builder());

    // Assert
    assertNull(actualPaymentSearchParams.getAgreementId());
    assertNull(actualPaymentSearchParams.getCardBrand());
    assertNull(actualPaymentSearchParams.getDisplaySize());
    assertNull(actualPaymentSearchParams.getEmail());
    assertNull(actualPaymentSearchParams.getFirstDigitsCardNumber());
    assertNull(actualPaymentSearchParams.getFromDate());
    assertNull(actualPaymentSearchParams.getFromSettledDate());
    assertNull(actualPaymentSearchParams.getLastDigitsCardNumber());
    assertNull(actualPaymentSearchParams.getPageNumber());
    assertNull(actualPaymentSearchParams.getReference());
    assertNull(actualPaymentSearchParams.getState());
    assertNull(actualPaymentSearchParams.getToDate());
    assertNull(actualPaymentSearchParams.getToSettledDate());
    Map<String, String> paramsAsMap = actualPaymentSearchParams.getParamsAsMap();
    assertEquals(14, paramsAsMap.size());
    assertTrue(paramsAsMap.containsKey(PaymentSearchParams.CARDHOLDER_NAME_KEY));
    assertTrue(paramsAsMap.containsKey(PaymentSearchParams.CARD_BRAND_KEY));
    assertTrue(paramsAsMap.containsKey(PaymentSearchParams.EMAIL_KEY));
    assertTrue(paramsAsMap.containsKey(PaymentSearchParams.FIRST_DIGITS_CARD_NUMBER_KEY));
    assertTrue(paramsAsMap.containsKey(PaymentSearchParams.REFERENCE_KEY));
    assertTrue(paramsAsMap.containsKey(PaymentSearchParams.STATE_KEY));
  }

  /**
   * Test {@link PaymentSearchParams#getParamsAsMap()}.
   *
   * <p>Method under test: {@link PaymentSearchParams#getParamsAsMap()}
   */
  @Test
  @DisplayName("Test getParamsAsMap()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Map PaymentSearchParams.getParamsAsMap()"})
  void testGetParamsAsMap() {
    // Arrange and Act
    Map<String, String> actualParamsAsMap = new PaymentSearchParams(new Builder()).getParamsAsMap();

    // Assert
    assertEquals(14, actualParamsAsMap.size());
    assertNull(actualParamsAsMap.get(PaymentSearchParams.CARDHOLDER_NAME_KEY));
    assertNull(actualParamsAsMap.get(PaymentSearchParams.CARD_BRAND_KEY));
    assertNull(actualParamsAsMap.get(PaymentSearchParams.DISPLAY_SIZE));
    assertNull(actualParamsAsMap.get(PaymentSearchParams.EMAIL_KEY));
    assertNull(actualParamsAsMap.get(PaymentSearchParams.FIRST_DIGITS_CARD_NUMBER_KEY));
    assertNull(actualParamsAsMap.get(PaymentSearchParams.FROM_DATE_KEY));
    assertNull(actualParamsAsMap.get(PaymentSearchParams.LAST_DIGITS_CARD_NUMBER_KEY));
    assertNull(actualParamsAsMap.get(PaymentSearchParams.PAGE));
    assertNull(actualParamsAsMap.get(PaymentSearchParams.REFERENCE_KEY));
    assertNull(actualParamsAsMap.get(PaymentSearchParams.STATE_KEY));
    assertNull(actualParamsAsMap.get(PaymentSearchParams.TO_DATE_KEY));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PaymentSearchParams#getAgreementId()}
   *   <li>{@link PaymentSearchParams#getCardBrand()}
   *   <li>{@link PaymentSearchParams#getDisplaySize()}
   *   <li>{@link PaymentSearchParams#getEmail()}
   *   <li>{@link PaymentSearchParams#getFirstDigitsCardNumber()}
   *   <li>{@link PaymentSearchParams#getFromDate()}
   *   <li>{@link PaymentSearchParams#getFromSettledDate()}
   *   <li>{@link PaymentSearchParams#getLastDigitsCardNumber()}
   *   <li>{@link PaymentSearchParams#getPageNumber()}
   *   <li>{@link PaymentSearchParams#getReference()}
   *   <li>{@link PaymentSearchParams#getState()}
   *   <li>{@link PaymentSearchParams#getToDate()}
   *   <li>{@link PaymentSearchParams#getToSettledDate()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String PaymentSearchParams.getAgreementId()",
    "String PaymentSearchParams.getCardBrand()",
    "String PaymentSearchParams.getDisplaySize()",
    "String PaymentSearchParams.getEmail()",
    "String PaymentSearchParams.getFirstDigitsCardNumber()",
    "String PaymentSearchParams.getFromDate()",
    "String PaymentSearchParams.getFromSettledDate()",
    "String PaymentSearchParams.getLastDigitsCardNumber()",
    "String PaymentSearchParams.getPageNumber()",
    "String PaymentSearchParams.getReference()",
    "String PaymentSearchParams.getState()",
    "String PaymentSearchParams.getToDate()",
    "String PaymentSearchParams.getToSettledDate()"
  })
  void testGettersAndSetters() {
    // Arrange
    PaymentSearchParams paymentSearchParams =
        new Builder()
            .withAgreementId("42")
            .withCardBrand("Card Brand")
            .withCardHolderName("Card Holder Name")
            .withDisplaySize("Display Size")
            .withEmail("jane.doe@example.org")
            .withFirstDigitsCardNumber("42")
            .withFromDate("2020-03-01")
            .withFromSettledDate("2020-03-01")
            .withLastDigitsCardNumber("42")
            .withPageNumber("42")
            .withReference("Reference")
            .withState("MD")
            .withToDate("2020-03-01")
            .withToSettledDate("2020-03-01")
            .build();

    // Act
    String actualAgreementId = paymentSearchParams.getAgreementId();
    String actualCardBrand = paymentSearchParams.getCardBrand();
    String actualDisplaySize = paymentSearchParams.getDisplaySize();
    String actualEmail = paymentSearchParams.getEmail();
    String actualFirstDigitsCardNumber = paymentSearchParams.getFirstDigitsCardNumber();
    String actualFromDate = paymentSearchParams.getFromDate();
    String actualFromSettledDate = paymentSearchParams.getFromSettledDate();
    String actualLastDigitsCardNumber = paymentSearchParams.getLastDigitsCardNumber();
    String actualPageNumber = paymentSearchParams.getPageNumber();
    String actualReference = paymentSearchParams.getReference();
    String actualState = paymentSearchParams.getState();
    String actualToDate = paymentSearchParams.getToDate();

    // Assert
    assertEquals("2020-03-01", actualFromDate);
    assertEquals("2020-03-01", actualFromSettledDate);
    assertEquals("2020-03-01", actualToDate);
    assertEquals("2020-03-01", paymentSearchParams.getToSettledDate());
    assertEquals("42", actualAgreementId);
    assertEquals("42", actualFirstDigitsCardNumber);
    assertEquals("42", actualLastDigitsCardNumber);
    assertEquals("42", actualPageNumber);
    assertEquals("Display Size", actualDisplaySize);
    assertEquals("MD", actualState);
    assertEquals("Reference", actualReference);
    assertEquals("card brand", actualCardBrand);
    assertEquals("jane.doe@example.org", actualEmail);
  }
}
