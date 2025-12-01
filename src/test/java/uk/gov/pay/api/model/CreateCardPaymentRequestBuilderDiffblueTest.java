package uk.gov.pay.api.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.service.payments.commons.model.AgreementPaymentType;
import uk.gov.service.payments.commons.model.AuthorisationMode;
import uk.gov.service.payments.commons.model.Source;
import uk.gov.service.payments.commons.model.SupportedLanguage;
import uk.gov.service.payments.commons.model.charge.ExternalMetadata;

class CreateCardPaymentRequestBuilderDiffblueTest {
  /**
   * Test {@link CreateCardPaymentRequestBuilder#builder()}.
   *
   * <p>Method under test: {@link CreateCardPaymentRequestBuilder#builder()}
   */
  @Test
  @DisplayName("Test builder()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"CreateCardPaymentRequestBuilder CreateCardPaymentRequestBuilder.builder()"})
  void testBuilder() {
    // Arrange and Act
    CreateCardPaymentRequestBuilder actualBuilderResult = CreateCardPaymentRequestBuilder.builder();

    // Assert
    assertNull(actualBuilderResult.getDelayedCapture());
    assertNull(actualBuilderResult.isMoto());
    assertNull(actualBuilderResult.getAddressLine1());
    assertNull(actualBuilderResult.getAddressLine2());
    assertNull(actualBuilderResult.getAgreementId());
    assertNull(actualBuilderResult.getCardholderName());
    assertNull(actualBuilderResult.getCity());
    assertNull(actualBuilderResult.getCountry());
    assertNull(actualBuilderResult.getDescription());
    assertNull(actualBuilderResult.getEmail());
    assertNull(actualBuilderResult.getPostcode());
    assertNull(actualBuilderResult.getReference());
    assertNull(actualBuilderResult.getReturnUrl());
    assertNull(actualBuilderResult.getSetUpAgreement());
    assertNull(actualBuilderResult.getInternal());
    assertNull(actualBuilderResult.getPrefilledCardholderDetails());
    assertNull(actualBuilderResult.getAgreementPaymentType());
    assertNull(actualBuilderResult.getAuthorisationMode());
    assertNull(actualBuilderResult.getLanguage());
    assertNull(actualBuilderResult.getMetadata());
    assertEquals(0, actualBuilderResult.getAmount());
  }

  /**
   * Test {@link CreateCardPaymentRequestBuilder#getPrefilledCardholderDetails()}.
   *
   * <ul>
   *   <li>Given builder.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link CreateCardPaymentRequestBuilder#getPrefilledCardholderDetails()}
   */
  @Test
  @DisplayName("Test getPrefilledCardholderDetails(); given builder; then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PrefilledCardholderDetails CreateCardPaymentRequestBuilder.getPrefilledCardholderDetails()"
  })
  void testGetPrefilledCardholderDetails_givenBuilder_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(CreateCardPaymentRequestBuilder.builder().getPrefilledCardholderDetails());
  }

  /**
   * Test {@link CreateCardPaymentRequestBuilder#getPrefilledCardholderDetails()}.
   *
   * <ul>
   *   <li>Then return BillingAddress City is {@code Oxford}.
   * </ul>
   *
   * <p>Method under test: {@link CreateCardPaymentRequestBuilder#getPrefilledCardholderDetails()}
   */
  @Test
  @DisplayName("Test getPrefilledCardholderDetails(); then return BillingAddress City is 'Oxford'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PrefilledCardholderDetails CreateCardPaymentRequestBuilder.getPrefilledCardholderDetails()"
  })
  void testGetPrefilledCardholderDetails_thenReturnBillingAddressCityIsOxford() {
    // Arrange
    CreateCardPaymentRequestBuilder builderResult = CreateCardPaymentRequestBuilder.builder();
    builderResult.city("Oxford");

    // Act
    PrefilledCardholderDetails actualPrefilledCardholderDetails =
        builderResult.getPrefilledCardholderDetails();

    // Assert
    Address getResult = actualPrefilledCardholderDetails.getBillingAddress().get();
    assertEquals("Oxford", getResult.getCity());
    assertNull(getResult.getCountry());
    assertNull(getResult.getLine1());
    assertNull(getResult.getLine2());
    assertNull(getResult.getPostcode());
    assertFalse(actualPrefilledCardholderDetails.getCardholderName().isPresent());
  }

  /**
   * Test {@link CreateCardPaymentRequestBuilder#getPrefilledCardholderDetails()}.
   *
   * <ul>
   *   <li>Then return BillingAddress Country is {@code GB}.
   * </ul>
   *
   * <p>Method under test: {@link CreateCardPaymentRequestBuilder#getPrefilledCardholderDetails()}
   */
  @Test
  @DisplayName("Test getPrefilledCardholderDetails(); then return BillingAddress Country is 'GB'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PrefilledCardholderDetails CreateCardPaymentRequestBuilder.getPrefilledCardholderDetails()"
  })
  void testGetPrefilledCardholderDetails_thenReturnBillingAddressCountryIsGb() {
    // Arrange
    CreateCardPaymentRequestBuilder builderResult = CreateCardPaymentRequestBuilder.builder();
    builderResult.country("GB");

    // Act
    PrefilledCardholderDetails actualPrefilledCardholderDetails =
        builderResult.getPrefilledCardholderDetails();

    // Assert
    Address getResult = actualPrefilledCardholderDetails.getBillingAddress().get();
    assertEquals("GB", getResult.getCountry());
    assertNull(getResult.getCity());
    assertNull(getResult.getLine1());
    assertNull(getResult.getLine2());
    assertNull(getResult.getPostcode());
    assertFalse(actualPrefilledCardholderDetails.getCardholderName().isPresent());
  }

  /**
   * Test {@link CreateCardPaymentRequestBuilder#getPrefilledCardholderDetails()}.
   *
   * <ul>
   *   <li>Then return BillingAddress Line1 is {@code 42 Main St}.
   * </ul>
   *
   * <p>Method under test: {@link CreateCardPaymentRequestBuilder#getPrefilledCardholderDetails()}
   */
  @Test
  @DisplayName(
      "Test getPrefilledCardholderDetails(); then return BillingAddress Line1 is '42 Main St'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PrefilledCardholderDetails CreateCardPaymentRequestBuilder.getPrefilledCardholderDetails()"
  })
  void testGetPrefilledCardholderDetails_thenReturnBillingAddressLine1Is42MainSt() {
    // Arrange
    CreateCardPaymentRequestBuilder builderResult = CreateCardPaymentRequestBuilder.builder();
    builderResult.addressLine1("42 Main St");

    // Act
    PrefilledCardholderDetails actualPrefilledCardholderDetails =
        builderResult.getPrefilledCardholderDetails();

    // Assert
    Address getResult = actualPrefilledCardholderDetails.getBillingAddress().get();
    assertEquals("42 Main St", getResult.getLine1());
    assertNull(getResult.getCity());
    assertNull(getResult.getCountry());
    assertNull(getResult.getLine2());
    assertNull(getResult.getPostcode());
    assertFalse(actualPrefilledCardholderDetails.getCardholderName().isPresent());
  }

  /**
   * Test {@link CreateCardPaymentRequestBuilder#getPrefilledCardholderDetails()}.
   *
   * <ul>
   *   <li>Then return BillingAddress Line2 is {@code 42 Main St}.
   * </ul>
   *
   * <p>Method under test: {@link CreateCardPaymentRequestBuilder#getPrefilledCardholderDetails()}
   */
  @Test
  @DisplayName(
      "Test getPrefilledCardholderDetails(); then return BillingAddress Line2 is '42 Main St'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PrefilledCardholderDetails CreateCardPaymentRequestBuilder.getPrefilledCardholderDetails()"
  })
  void testGetPrefilledCardholderDetails_thenReturnBillingAddressLine2Is42MainSt() {
    // Arrange
    CreateCardPaymentRequestBuilder builderResult = CreateCardPaymentRequestBuilder.builder();
    builderResult.addressLine2("42 Main St");

    // Act
    PrefilledCardholderDetails actualPrefilledCardholderDetails =
        builderResult.getPrefilledCardholderDetails();

    // Assert
    Address getResult = actualPrefilledCardholderDetails.getBillingAddress().get();
    assertEquals("42 Main St", getResult.getLine2());
    assertNull(getResult.getCity());
    assertNull(getResult.getCountry());
    assertNull(getResult.getLine1());
    assertNull(getResult.getPostcode());
    assertFalse(actualPrefilledCardholderDetails.getCardholderName().isPresent());
  }

  /**
   * Test {@link CreateCardPaymentRequestBuilder#getPrefilledCardholderDetails()}.
   *
   * <ul>
   *   <li>Then return BillingAddress Postcode is {@code OX1 1PT}.
   * </ul>
   *
   * <p>Method under test: {@link CreateCardPaymentRequestBuilder#getPrefilledCardholderDetails()}
   */
  @Test
  @DisplayName(
      "Test getPrefilledCardholderDetails(); then return BillingAddress Postcode is 'OX1 1PT'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PrefilledCardholderDetails CreateCardPaymentRequestBuilder.getPrefilledCardholderDetails()"
  })
  void testGetPrefilledCardholderDetails_thenReturnBillingAddressPostcodeIsOx11pt() {
    // Arrange
    CreateCardPaymentRequestBuilder builderResult = CreateCardPaymentRequestBuilder.builder();
    builderResult.postcode("OX1 1PT");

    // Act
    PrefilledCardholderDetails actualPrefilledCardholderDetails =
        builderResult.getPrefilledCardholderDetails();

    // Assert
    Address getResult = actualPrefilledCardholderDetails.getBillingAddress().get();
    assertEquals("OX1 1PT", getResult.getPostcode());
    assertNull(getResult.getCity());
    assertNull(getResult.getCountry());
    assertNull(getResult.getLine1());
    assertNull(getResult.getLine2());
    assertFalse(actualPrefilledCardholderDetails.getCardholderName().isPresent());
  }

  /**
   * Test {@link CreateCardPaymentRequestBuilder#getPrefilledCardholderDetails()}.
   *
   * <ul>
   *   <li>Then return {@code Card Holder Name}.
   * </ul>
   *
   * <p>Method under test: {@link CreateCardPaymentRequestBuilder#getPrefilledCardholderDetails()}
   */
  @Test
  @DisplayName("Test getPrefilledCardholderDetails(); then return 'Card Holder Name'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PrefilledCardholderDetails CreateCardPaymentRequestBuilder.getPrefilledCardholderDetails()"
  })
  void testGetPrefilledCardholderDetails_thenReturnCardHolderName() {
    // Arrange
    CreateCardPaymentRequestBuilder builderResult = CreateCardPaymentRequestBuilder.builder();
    builderResult.cardholderName("Card Holder Name");
    builderResult.addressLine1("42 Main St");

    // Act
    PrefilledCardholderDetails actualPrefilledCardholderDetails =
        builderResult.getPrefilledCardholderDetails();

    // Assert
    Address getResult = actualPrefilledCardholderDetails.getBillingAddress().get();
    assertEquals("42 Main St", getResult.getLine1());
    Optional<String> cardholderName = actualPrefilledCardholderDetails.getCardholderName();
    assertEquals("Card Holder Name", cardholderName.get());
    assertNull(getResult.getCity());
    assertNull(getResult.getCountry());
    assertNull(getResult.getLine2());
    assertNull(getResult.getPostcode());
    assertTrue(cardholderName.isPresent());
  }

  /**
   * Test {@link CreateCardPaymentRequestBuilder#getInternal()}.
   *
   * <ul>
   *   <li>Given builder source {@code CARD_API}.
   *   <li>Then return Source is {@code CARD_API}.
   * </ul>
   *
   * <p>Method under test: {@link CreateCardPaymentRequestBuilder#getInternal()}
   */
  @Test
  @DisplayName(
      "Test getInternal(); given builder source 'CARD_API'; then return Source is 'CARD_API'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Internal CreateCardPaymentRequestBuilder.getInternal()"})
  void testGetInternal_givenBuilderSourceCardApi_thenReturnSourceIsCardApi() {
    // Arrange
    CreateCardPaymentRequestBuilder builderResult = CreateCardPaymentRequestBuilder.builder();
    builderResult.source(Source.CARD_API);

    // Act and Assert
    Optional<Source> source = builderResult.getInternal().getSource();
    assertEquals(Source.CARD_API, source.get());
    assertTrue(source.isPresent());
  }

  /**
   * Test {@link CreateCardPaymentRequestBuilder#getInternal()}.
   *
   * <ul>
   *   <li>Given builder.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link CreateCardPaymentRequestBuilder#getInternal()}
   */
  @Test
  @DisplayName("Test getInternal(); given builder; then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Internal CreateCardPaymentRequestBuilder.getInternal()"})
  void testGetInternal_givenBuilder_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(CreateCardPaymentRequestBuilder.builder().getInternal());
  }

  /**
   * Test {@link CreateCardPaymentRequestBuilder#build()}.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CreateCardPaymentRequestBuilder#build()}
   *   <li>{@link CreateCardPaymentRequestBuilder#agreementId(String)}
   *   <li>{@link CreateCardPaymentRequestBuilder#agreementPaymentType(AgreementPaymentType)}
   *   <li>{@link CreateCardPaymentRequestBuilder#amount(int)}
   *   <li>{@link CreateCardPaymentRequestBuilder#authorisationMode(AuthorisationMode)}
   *   <li>{@link CreateCardPaymentRequestBuilder#cardholderName(String)}
   *   <li>{@link CreateCardPaymentRequestBuilder#city(String)}
   *   <li>{@link CreateCardPaymentRequestBuilder#country(String)}
   *   <li>{@link CreateCardPaymentRequestBuilder#delayedCapture(Boolean)}
   *   <li>{@link CreateCardPaymentRequestBuilder#description(String)}
   *   <li>{@link CreateCardPaymentRequestBuilder#email(String)}
   *   <li>{@link CreateCardPaymentRequestBuilder#language(SupportedLanguage)}
   *   <li>{@link CreateCardPaymentRequestBuilder#metadata(ExternalMetadata)}
   *   <li>{@link CreateCardPaymentRequestBuilder#moto(Boolean)}
   *   <li>{@link CreateCardPaymentRequestBuilder#postcode(String)}
   *   <li>{@link CreateCardPaymentRequestBuilder#reference(String)}
   *   <li>{@link CreateCardPaymentRequestBuilder#returnUrl(String)}
   *   <li>{@link CreateCardPaymentRequestBuilder#source(Source)}
   *   <li>{@link CreateCardPaymentRequestBuilder#getAgreementId()}
   *   <li>{@link CreateCardPaymentRequestBuilder#getCardholderName()}
   *   <li>{@link CreateCardPaymentRequestBuilder#getCountry()}
   *   <li>{@link CreateCardPaymentRequestBuilder#getPostcode()}
   *   <li>{@link CreateCardPaymentRequestBuilder#getCity()}
   *   <li>{@link CreateCardPaymentRequestBuilder#getReference()}
   *   <li>{@link CreateCardPaymentRequestBuilder#getDescription()}
   *   <li>{@link CreateCardPaymentRequestBuilder#getReturnUrl()}
   *   <li>{@link CreateCardPaymentRequestBuilder#getEmail()}
   *   <li>{@link CreateCardPaymentRequestBuilder#getAddressLine1()}
   *   <li>{@link CreateCardPaymentRequestBuilder#getAddressLine2()}
   *   <li>{@link CreateCardPaymentRequestBuilder#getSetUpAgreement()}
   *   <li>{@link CreateCardPaymentRequestBuilder#getAmount()}
   *   <li>{@link CreateCardPaymentRequestBuilder#getAgreementPaymentType()}
   *   <li>{@link CreateCardPaymentRequestBuilder#getAuthorisationMode()}
   *   <li>{@link CreateCardPaymentRequestBuilder#getLanguage()}
   *   <li>{@link CreateCardPaymentRequestBuilder#getDelayedCapture()}
   *   <li>{@link CreateCardPaymentRequestBuilder#isMoto()}
   *   <li>{@link CreateCardPaymentRequestBuilder#getMetadata()}
   * </ul>
   */
  @Test
  @DisplayName("Test build()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CreateCardPaymentRequestBuilder.<init>()",
    "CreateCardPaymentRequestBuilder CreateCardPaymentRequestBuilder.addressLine1(String)",
    "CreateCardPaymentRequestBuilder CreateCardPaymentRequestBuilder.addressLine2(String)",
    "CreateCardPaymentRequestBuilder CreateCardPaymentRequestBuilder.agreementId(String)",
    "CreateCardPaymentRequestBuilder CreateCardPaymentRequestBuilder.agreementPaymentType(AgreementPaymentType)",
    "CreateCardPaymentRequestBuilder CreateCardPaymentRequestBuilder.amount(int)",
    "CreateCardPaymentRequestBuilder CreateCardPaymentRequestBuilder.authorisationMode(AuthorisationMode)",
    "CreateCardPaymentRequest CreateCardPaymentRequestBuilder.build()",
    "CreateCardPaymentRequestBuilder CreateCardPaymentRequestBuilder.cardholderName(String)",
    "CreateCardPaymentRequestBuilder CreateCardPaymentRequestBuilder.city(String)",
    "CreateCardPaymentRequestBuilder CreateCardPaymentRequestBuilder.country(String)",
    "CreateCardPaymentRequestBuilder CreateCardPaymentRequestBuilder.delayedCapture(Boolean)",
    "CreateCardPaymentRequestBuilder CreateCardPaymentRequestBuilder.description(String)",
    "CreateCardPaymentRequestBuilder CreateCardPaymentRequestBuilder.email(String)",
    "String CreateCardPaymentRequestBuilder.getAddressLine1()",
    "String CreateCardPaymentRequestBuilder.getAddressLine2()",
    "String CreateCardPaymentRequestBuilder.getAgreementId()",
    "AgreementPaymentType CreateCardPaymentRequestBuilder.getAgreementPaymentType()",
    "int CreateCardPaymentRequestBuilder.getAmount()",
    "AuthorisationMode CreateCardPaymentRequestBuilder.getAuthorisationMode()",
    "String CreateCardPaymentRequestBuilder.getCardholderName()",
    "String CreateCardPaymentRequestBuilder.getCity()",
    "String CreateCardPaymentRequestBuilder.getCountry()",
    "Boolean CreateCardPaymentRequestBuilder.getDelayedCapture()",
    "String CreateCardPaymentRequestBuilder.getDescription()",
    "String CreateCardPaymentRequestBuilder.getEmail()",
    "SupportedLanguage CreateCardPaymentRequestBuilder.getLanguage()",
    "ExternalMetadata CreateCardPaymentRequestBuilder.getMetadata()",
    "String CreateCardPaymentRequestBuilder.getPostcode()",
    "String CreateCardPaymentRequestBuilder.getReference()",
    "String CreateCardPaymentRequestBuilder.getReturnUrl()",
    "String CreateCardPaymentRequestBuilder.getSetUpAgreement()",
    "Boolean CreateCardPaymentRequestBuilder.isMoto()",
    "CreateCardPaymentRequestBuilder CreateCardPaymentRequestBuilder.language(SupportedLanguage)",
    "CreateCardPaymentRequestBuilder CreateCardPaymentRequestBuilder.metadata(ExternalMetadata)",
    "CreateCardPaymentRequestBuilder CreateCardPaymentRequestBuilder.moto(Boolean)",
    "CreateCardPaymentRequestBuilder CreateCardPaymentRequestBuilder.postcode(String)",
    "CreateCardPaymentRequestBuilder CreateCardPaymentRequestBuilder.reference(String)",
    "CreateCardPaymentRequestBuilder CreateCardPaymentRequestBuilder.returnUrl(String)",
    "void CreateCardPaymentRequestBuilder.setUpAgreement(String)",
    "CreateCardPaymentRequestBuilder CreateCardPaymentRequestBuilder.source(Source)"
  })
  void testBuild() {
    // Arrange and Act
    CreateCardPaymentRequestBuilder actualBuilderResult = CreateCardPaymentRequestBuilder.builder();
    CreateCardPaymentRequestBuilder actualAgreementIdResult = actualBuilderResult.agreementId("42");
    CreateCardPaymentRequestBuilder actualAgreementPaymentTypeResult =
        actualAgreementIdResult.agreementPaymentType(AgreementPaymentType.INSTALMENT);
    CreateCardPaymentRequestBuilder actualAmountResult =
        actualAgreementPaymentTypeResult.amount(10);
    CreateCardPaymentRequestBuilder actualAuthorisationModeResult =
        actualAmountResult.authorisationMode(AuthorisationMode.WEB);
    CreateCardPaymentRequestBuilder actualCardholderNameResult =
        actualAuthorisationModeResult.cardholderName("Card Holder Name");
    CreateCardPaymentRequestBuilder actualCityResult = actualCardholderNameResult.city("Oxford");
    CreateCardPaymentRequestBuilder actualCountryResult = actualCityResult.country("GB");
    CreateCardPaymentRequestBuilder actualDelayedCaptureResult =
        actualCountryResult.delayedCapture(true);
    CreateCardPaymentRequestBuilder actualDescriptionResult =
        actualDelayedCaptureResult.description("The characteristics of someone or something");
    CreateCardPaymentRequestBuilder actualEmailResult =
        actualDescriptionResult.email("jane.doe@example.org");
    CreateCardPaymentRequestBuilder actualLanguageResult =
        actualEmailResult.language(SupportedLanguage.ENGLISH);
    ExternalMetadata metadata = new ExternalMetadata(new HashMap<>());
    CreateCardPaymentRequestBuilder actualMetadataResult = actualLanguageResult.metadata(metadata);
    CreateCardPaymentRequestBuilder actualMotoResult = actualMetadataResult.moto(true);
    CreateCardPaymentRequestBuilder actualPostcodeResult = actualMotoResult.postcode("OX1 1PT");
    CreateCardPaymentRequestBuilder actualReferenceResult =
        actualPostcodeResult.reference("Reference");
    CreateCardPaymentRequestBuilder actualReturnUrlResult =
        actualReferenceResult.returnUrl("https://example.org/example");
    CreateCardPaymentRequestBuilder actualSourceResult =
        actualReturnUrlResult.source(Source.CARD_API);
    CreateCardPaymentRequest actualCreateCardPaymentRequest = actualSourceResult.build();

    // Assert
    Optional<String> agreementId = actualCreateCardPaymentRequest.getAgreementId();
    assertEquals("42", agreementId.get());
    assertEquals("42", actualAgreementIdResult.getAgreementId());
    assertEquals("42", actualAgreementPaymentTypeResult.getAgreementId());
    assertEquals("42", actualAmountResult.getAgreementId());
    assertEquals("42", actualAuthorisationModeResult.getAgreementId());
    assertEquals("42", actualCardholderNameResult.getAgreementId());
    assertEquals("42", actualCityResult.getAgreementId());
    assertEquals("42", actualCountryResult.getAgreementId());
    assertEquals("42", actualDelayedCaptureResult.getAgreementId());
    assertEquals("42", actualDescriptionResult.getAgreementId());
    assertEquals("42", actualEmailResult.getAgreementId());
    assertEquals("42", actualLanguageResult.getAgreementId());
    assertEquals("42", actualMetadataResult.getAgreementId());
    assertEquals("42", actualMotoResult.getAgreementId());
    assertEquals("42", actualPostcodeResult.getAgreementId());
    assertEquals("42", actualReferenceResult.getAgreementId());
    assertEquals("42", actualReturnUrlResult.getAgreementId());
    assertEquals("42", actualSourceResult.getAgreementId());
    assertEquals("42", actualBuilderResult.getAgreementId());
    assertEquals("Card Holder Name", actualAgreementIdResult.getCardholderName());
    assertEquals("Card Holder Name", actualAgreementPaymentTypeResult.getCardholderName());
    assertEquals("Card Holder Name", actualAmountResult.getCardholderName());
    assertEquals("Card Holder Name", actualAuthorisationModeResult.getCardholderName());
    assertEquals("Card Holder Name", actualCardholderNameResult.getCardholderName());
    assertEquals("Card Holder Name", actualCityResult.getCardholderName());
    assertEquals("Card Holder Name", actualCountryResult.getCardholderName());
    assertEquals("Card Holder Name", actualDelayedCaptureResult.getCardholderName());
    assertEquals("Card Holder Name", actualDescriptionResult.getCardholderName());
    assertEquals("Card Holder Name", actualEmailResult.getCardholderName());
    assertEquals("Card Holder Name", actualLanguageResult.getCardholderName());
    assertEquals("Card Holder Name", actualMetadataResult.getCardholderName());
    assertEquals("Card Holder Name", actualMotoResult.getCardholderName());
    assertEquals("Card Holder Name", actualPostcodeResult.getCardholderName());
    assertEquals("Card Holder Name", actualReferenceResult.getCardholderName());
    assertEquals("Card Holder Name", actualReturnUrlResult.getCardholderName());
    assertEquals("Card Holder Name", actualSourceResult.getCardholderName());
    assertEquals("Card Holder Name", actualBuilderResult.getCardholderName());
    assertEquals("GB", actualAgreementIdResult.getCountry());
    assertEquals("GB", actualAgreementPaymentTypeResult.getCountry());
    assertEquals("GB", actualAmountResult.getCountry());
    assertEquals("GB", actualAuthorisationModeResult.getCountry());
    assertEquals("GB", actualCardholderNameResult.getCountry());
    assertEquals("GB", actualCityResult.getCountry());
    assertEquals("GB", actualCountryResult.getCountry());
    assertEquals("GB", actualDelayedCaptureResult.getCountry());
    assertEquals("GB", actualDescriptionResult.getCountry());
    assertEquals("GB", actualEmailResult.getCountry());
    assertEquals("GB", actualLanguageResult.getCountry());
    assertEquals("GB", actualMetadataResult.getCountry());
    assertEquals("GB", actualMotoResult.getCountry());
    assertEquals("GB", actualPostcodeResult.getCountry());
    assertEquals("GB", actualReferenceResult.getCountry());
    assertEquals("GB", actualReturnUrlResult.getCountry());
    assertEquals("GB", actualSourceResult.getCountry());
    assertEquals("GB", actualBuilderResult.getCountry());
    assertEquals("OX1 1PT", actualAgreementIdResult.getPostcode());
    assertEquals("OX1 1PT", actualAgreementPaymentTypeResult.getPostcode());
    assertEquals("OX1 1PT", actualAmountResult.getPostcode());
    assertEquals("OX1 1PT", actualAuthorisationModeResult.getPostcode());
    assertEquals("OX1 1PT", actualCardholderNameResult.getPostcode());
    assertEquals("OX1 1PT", actualCityResult.getPostcode());
    assertEquals("OX1 1PT", actualCountryResult.getPostcode());
    assertEquals("OX1 1PT", actualDelayedCaptureResult.getPostcode());
    assertEquals("OX1 1PT", actualDescriptionResult.getPostcode());
    assertEquals("OX1 1PT", actualEmailResult.getPostcode());
    assertEquals("OX1 1PT", actualLanguageResult.getPostcode());
    assertEquals("OX1 1PT", actualMetadataResult.getPostcode());
    assertEquals("OX1 1PT", actualMotoResult.getPostcode());
    assertEquals("OX1 1PT", actualPostcodeResult.getPostcode());
    assertEquals("OX1 1PT", actualReferenceResult.getPostcode());
    assertEquals("OX1 1PT", actualReturnUrlResult.getPostcode());
    assertEquals("OX1 1PT", actualSourceResult.getPostcode());
    assertEquals("OX1 1PT", actualBuilderResult.getPostcode());
    assertEquals("Oxford", actualAgreementIdResult.getCity());
    assertEquals("Oxford", actualAgreementPaymentTypeResult.getCity());
    assertEquals("Oxford", actualAmountResult.getCity());
    assertEquals("Oxford", actualAuthorisationModeResult.getCity());
    assertEquals("Oxford", actualCardholderNameResult.getCity());
    assertEquals("Oxford", actualCityResult.getCity());
    assertEquals("Oxford", actualCountryResult.getCity());
    assertEquals("Oxford", actualDelayedCaptureResult.getCity());
    assertEquals("Oxford", actualDescriptionResult.getCity());
    assertEquals("Oxford", actualEmailResult.getCity());
    assertEquals("Oxford", actualLanguageResult.getCity());
    assertEquals("Oxford", actualMetadataResult.getCity());
    assertEquals("Oxford", actualMotoResult.getCity());
    assertEquals("Oxford", actualPostcodeResult.getCity());
    assertEquals("Oxford", actualReferenceResult.getCity());
    assertEquals("Oxford", actualReturnUrlResult.getCity());
    assertEquals("Oxford", actualSourceResult.getCity());
    assertEquals("Oxford", actualBuilderResult.getCity());
    assertEquals("Reference", actualCreateCardPaymentRequest.getReference());
    assertEquals("Reference", actualAgreementIdResult.getReference());
    assertEquals("Reference", actualAgreementPaymentTypeResult.getReference());
    assertEquals("Reference", actualAmountResult.getReference());
    assertEquals("Reference", actualAuthorisationModeResult.getReference());
    assertEquals("Reference", actualCardholderNameResult.getReference());
    assertEquals("Reference", actualCityResult.getReference());
    assertEquals("Reference", actualCountryResult.getReference());
    assertEquals("Reference", actualDelayedCaptureResult.getReference());
    assertEquals("Reference", actualDescriptionResult.getReference());
    assertEquals("Reference", actualEmailResult.getReference());
    assertEquals("Reference", actualLanguageResult.getReference());
    assertEquals("Reference", actualMetadataResult.getReference());
    assertEquals("Reference", actualMotoResult.getReference());
    assertEquals("Reference", actualPostcodeResult.getReference());
    assertEquals("Reference", actualReferenceResult.getReference());
    assertEquals("Reference", actualReturnUrlResult.getReference());
    assertEquals("Reference", actualSourceResult.getReference());
    assertEquals("Reference", actualBuilderResult.getReference());
    assertEquals(
        "The characteristics of someone or something",
        actualCreateCardPaymentRequest.getDescription());
    assertEquals(
        "The characteristics of someone or something", actualAgreementIdResult.getDescription());
    assertEquals(
        "The characteristics of someone or something",
        actualAgreementPaymentTypeResult.getDescription());
    assertEquals(
        "The characteristics of someone or something", actualAmountResult.getDescription());
    assertEquals(
        "The characteristics of someone or something",
        actualAuthorisationModeResult.getDescription());
    assertEquals(
        "The characteristics of someone or something", actualCardholderNameResult.getDescription());
    assertEquals("The characteristics of someone or something", actualCityResult.getDescription());
    assertEquals(
        "The characteristics of someone or something", actualCountryResult.getDescription());
    assertEquals(
        "The characteristics of someone or something", actualDelayedCaptureResult.getDescription());
    assertEquals(
        "The characteristics of someone or something", actualDescriptionResult.getDescription());
    assertEquals("The characteristics of someone or something", actualEmailResult.getDescription());
    assertEquals(
        "The characteristics of someone or something", actualLanguageResult.getDescription());
    assertEquals(
        "The characteristics of someone or something", actualMetadataResult.getDescription());
    assertEquals("The characteristics of someone or something", actualMotoResult.getDescription());
    assertEquals(
        "The characteristics of someone or something", actualPostcodeResult.getDescription());
    assertEquals(
        "The characteristics of someone or something", actualReferenceResult.getDescription());
    assertEquals(
        "The characteristics of someone or something", actualReturnUrlResult.getDescription());
    assertEquals(
        "The characteristics of someone or something", actualSourceResult.getDescription());
    assertEquals(
        "The characteristics of someone or something", actualBuilderResult.getDescription());
    assertEquals("https://example.org/example", actualCreateCardPaymentRequest.getReturnUrl());
    assertEquals("https://example.org/example", actualAgreementIdResult.getReturnUrl());
    assertEquals("https://example.org/example", actualAgreementPaymentTypeResult.getReturnUrl());
    assertEquals("https://example.org/example", actualAmountResult.getReturnUrl());
    assertEquals("https://example.org/example", actualAuthorisationModeResult.getReturnUrl());
    assertEquals("https://example.org/example", actualCardholderNameResult.getReturnUrl());
    assertEquals("https://example.org/example", actualCityResult.getReturnUrl());
    assertEquals("https://example.org/example", actualCountryResult.getReturnUrl());
    assertEquals("https://example.org/example", actualDelayedCaptureResult.getReturnUrl());
    assertEquals("https://example.org/example", actualDescriptionResult.getReturnUrl());
    assertEquals("https://example.org/example", actualEmailResult.getReturnUrl());
    assertEquals("https://example.org/example", actualLanguageResult.getReturnUrl());
    assertEquals("https://example.org/example", actualMetadataResult.getReturnUrl());
    assertEquals("https://example.org/example", actualMotoResult.getReturnUrl());
    assertEquals("https://example.org/example", actualPostcodeResult.getReturnUrl());
    assertEquals("https://example.org/example", actualReferenceResult.getReturnUrl());
    assertEquals("https://example.org/example", actualReturnUrlResult.getReturnUrl());
    assertEquals("https://example.org/example", actualSourceResult.getReturnUrl());
    assertEquals("https://example.org/example", actualBuilderResult.getReturnUrl());
    Optional<String> email = actualCreateCardPaymentRequest.getEmail();
    assertEquals("jane.doe@example.org", email.get());
    assertEquals("jane.doe@example.org", actualAgreementIdResult.getEmail());
    assertEquals("jane.doe@example.org", actualAgreementPaymentTypeResult.getEmail());
    assertEquals("jane.doe@example.org", actualAmountResult.getEmail());
    assertEquals("jane.doe@example.org", actualAuthorisationModeResult.getEmail());
    assertEquals("jane.doe@example.org", actualCardholderNameResult.getEmail());
    assertEquals("jane.doe@example.org", actualCityResult.getEmail());
    assertEquals("jane.doe@example.org", actualCountryResult.getEmail());
    assertEquals("jane.doe@example.org", actualDelayedCaptureResult.getEmail());
    assertEquals("jane.doe@example.org", actualDescriptionResult.getEmail());
    assertEquals("jane.doe@example.org", actualEmailResult.getEmail());
    assertEquals("jane.doe@example.org", actualLanguageResult.getEmail());
    assertEquals("jane.doe@example.org", actualMetadataResult.getEmail());
    assertEquals("jane.doe@example.org", actualMotoResult.getEmail());
    assertEquals("jane.doe@example.org", actualPostcodeResult.getEmail());
    assertEquals("jane.doe@example.org", actualReferenceResult.getEmail());
    assertEquals("jane.doe@example.org", actualReturnUrlResult.getEmail());
    assertEquals("jane.doe@example.org", actualSourceResult.getEmail());
    assertEquals("jane.doe@example.org", actualBuilderResult.getEmail());
    assertNull(actualAgreementIdResult.getAddressLine1());
    assertNull(actualAgreementPaymentTypeResult.getAddressLine1());
    assertNull(actualAmountResult.getAddressLine1());
    assertNull(actualAuthorisationModeResult.getAddressLine1());
    assertNull(actualCardholderNameResult.getAddressLine1());
    assertNull(actualCityResult.getAddressLine1());
    assertNull(actualCountryResult.getAddressLine1());
    assertNull(actualDelayedCaptureResult.getAddressLine1());
    assertNull(actualDescriptionResult.getAddressLine1());
    assertNull(actualEmailResult.getAddressLine1());
    assertNull(actualLanguageResult.getAddressLine1());
    assertNull(actualMetadataResult.getAddressLine1());
    assertNull(actualMotoResult.getAddressLine1());
    assertNull(actualPostcodeResult.getAddressLine1());
    assertNull(actualReferenceResult.getAddressLine1());
    assertNull(actualReturnUrlResult.getAddressLine1());
    assertNull(actualSourceResult.getAddressLine1());
    assertNull(actualBuilderResult.getAddressLine1());
    assertNull(actualAgreementIdResult.getAddressLine2());
    assertNull(actualAgreementPaymentTypeResult.getAddressLine2());
    assertNull(actualAmountResult.getAddressLine2());
    assertNull(actualAuthorisationModeResult.getAddressLine2());
    assertNull(actualCardholderNameResult.getAddressLine2());
    assertNull(actualCityResult.getAddressLine2());
    assertNull(actualCountryResult.getAddressLine2());
    assertNull(actualDelayedCaptureResult.getAddressLine2());
    assertNull(actualDescriptionResult.getAddressLine2());
    assertNull(actualEmailResult.getAddressLine2());
    assertNull(actualLanguageResult.getAddressLine2());
    assertNull(actualMetadataResult.getAddressLine2());
    assertNull(actualMotoResult.getAddressLine2());
    assertNull(actualPostcodeResult.getAddressLine2());
    assertNull(actualReferenceResult.getAddressLine2());
    assertNull(actualReturnUrlResult.getAddressLine2());
    assertNull(actualSourceResult.getAddressLine2());
    assertNull(actualBuilderResult.getAddressLine2());
    assertNull(actualAgreementIdResult.getSetUpAgreement());
    assertNull(actualAgreementPaymentTypeResult.getSetUpAgreement());
    assertNull(actualAmountResult.getSetUpAgreement());
    assertNull(actualAuthorisationModeResult.getSetUpAgreement());
    assertNull(actualCardholderNameResult.getSetUpAgreement());
    assertNull(actualCityResult.getSetUpAgreement());
    assertNull(actualCountryResult.getSetUpAgreement());
    assertNull(actualDelayedCaptureResult.getSetUpAgreement());
    assertNull(actualDescriptionResult.getSetUpAgreement());
    assertNull(actualEmailResult.getSetUpAgreement());
    assertNull(actualLanguageResult.getSetUpAgreement());
    assertNull(actualMetadataResult.getSetUpAgreement());
    assertNull(actualMotoResult.getSetUpAgreement());
    assertNull(actualPostcodeResult.getSetUpAgreement());
    assertNull(actualReferenceResult.getSetUpAgreement());
    assertNull(actualReturnUrlResult.getSetUpAgreement());
    assertNull(actualSourceResult.getSetUpAgreement());
    assertNull(actualBuilderResult.getSetUpAgreement());
    assertEquals(10, actualCreateCardPaymentRequest.getAmount());
    assertEquals(10, actualAgreementIdResult.getAmount());
    assertEquals(10, actualAgreementPaymentTypeResult.getAmount());
    assertEquals(10, actualAmountResult.getAmount());
    assertEquals(10, actualAuthorisationModeResult.getAmount());
    assertEquals(10, actualCardholderNameResult.getAmount());
    assertEquals(10, actualCityResult.getAmount());
    assertEquals(10, actualCountryResult.getAmount());
    assertEquals(10, actualDelayedCaptureResult.getAmount());
    assertEquals(10, actualDescriptionResult.getAmount());
    assertEquals(10, actualEmailResult.getAmount());
    assertEquals(10, actualLanguageResult.getAmount());
    assertEquals(10, actualMetadataResult.getAmount());
    assertEquals(10, actualMotoResult.getAmount());
    assertEquals(10, actualPostcodeResult.getAmount());
    assertEquals(10, actualReferenceResult.getAmount());
    assertEquals(10, actualReturnUrlResult.getAmount());
    assertEquals(10, actualSourceResult.getAmount());
    assertEquals(10, actualBuilderResult.getAmount());
    Optional<AgreementPaymentType> agreementPaymentType =
        actualCreateCardPaymentRequest.getAgreementPaymentType();
    assertEquals(AgreementPaymentType.INSTALMENT, agreementPaymentType.get());
    assertEquals(
        AgreementPaymentType.INSTALMENT, actualAgreementIdResult.getAgreementPaymentType());
    assertEquals(
        AgreementPaymentType.INSTALMENT,
        actualAgreementPaymentTypeResult.getAgreementPaymentType());
    assertEquals(AgreementPaymentType.INSTALMENT, actualAmountResult.getAgreementPaymentType());
    assertEquals(
        AgreementPaymentType.INSTALMENT, actualAuthorisationModeResult.getAgreementPaymentType());
    assertEquals(
        AgreementPaymentType.INSTALMENT, actualCardholderNameResult.getAgreementPaymentType());
    assertEquals(AgreementPaymentType.INSTALMENT, actualCityResult.getAgreementPaymentType());
    assertEquals(AgreementPaymentType.INSTALMENT, actualCountryResult.getAgreementPaymentType());
    assertEquals(
        AgreementPaymentType.INSTALMENT, actualDelayedCaptureResult.getAgreementPaymentType());
    assertEquals(
        AgreementPaymentType.INSTALMENT, actualDescriptionResult.getAgreementPaymentType());
    assertEquals(AgreementPaymentType.INSTALMENT, actualEmailResult.getAgreementPaymentType());
    assertEquals(AgreementPaymentType.INSTALMENT, actualLanguageResult.getAgreementPaymentType());
    assertEquals(AgreementPaymentType.INSTALMENT, actualMetadataResult.getAgreementPaymentType());
    assertEquals(AgreementPaymentType.INSTALMENT, actualMotoResult.getAgreementPaymentType());
    assertEquals(AgreementPaymentType.INSTALMENT, actualPostcodeResult.getAgreementPaymentType());
    assertEquals(AgreementPaymentType.INSTALMENT, actualReferenceResult.getAgreementPaymentType());
    assertEquals(AgreementPaymentType.INSTALMENT, actualReturnUrlResult.getAgreementPaymentType());
    assertEquals(AgreementPaymentType.INSTALMENT, actualSourceResult.getAgreementPaymentType());
    assertEquals(AgreementPaymentType.INSTALMENT, actualBuilderResult.getAgreementPaymentType());
    Optional<AuthorisationMode> authorisationMode =
        actualCreateCardPaymentRequest.getAuthorisationMode();
    assertEquals(AuthorisationMode.WEB, authorisationMode.get());
    assertEquals(AuthorisationMode.WEB, actualAgreementIdResult.getAuthorisationMode());
    assertEquals(AuthorisationMode.WEB, actualAgreementPaymentTypeResult.getAuthorisationMode());
    assertEquals(AuthorisationMode.WEB, actualAmountResult.getAuthorisationMode());
    assertEquals(AuthorisationMode.WEB, actualAuthorisationModeResult.getAuthorisationMode());
    assertEquals(AuthorisationMode.WEB, actualCardholderNameResult.getAuthorisationMode());
    assertEquals(AuthorisationMode.WEB, actualCityResult.getAuthorisationMode());
    assertEquals(AuthorisationMode.WEB, actualCountryResult.getAuthorisationMode());
    assertEquals(AuthorisationMode.WEB, actualDelayedCaptureResult.getAuthorisationMode());
    assertEquals(AuthorisationMode.WEB, actualDescriptionResult.getAuthorisationMode());
    assertEquals(AuthorisationMode.WEB, actualEmailResult.getAuthorisationMode());
    assertEquals(AuthorisationMode.WEB, actualLanguageResult.getAuthorisationMode());
    assertEquals(AuthorisationMode.WEB, actualMetadataResult.getAuthorisationMode());
    assertEquals(AuthorisationMode.WEB, actualMotoResult.getAuthorisationMode());
    assertEquals(AuthorisationMode.WEB, actualPostcodeResult.getAuthorisationMode());
    assertEquals(AuthorisationMode.WEB, actualReferenceResult.getAuthorisationMode());
    assertEquals(AuthorisationMode.WEB, actualReturnUrlResult.getAuthorisationMode());
    assertEquals(AuthorisationMode.WEB, actualSourceResult.getAuthorisationMode());
    assertEquals(AuthorisationMode.WEB, actualBuilderResult.getAuthorisationMode());
    Optional<SupportedLanguage> language = actualCreateCardPaymentRequest.getLanguage();
    assertEquals(SupportedLanguage.ENGLISH, language.get());
    assertEquals(SupportedLanguage.ENGLISH, actualAgreementIdResult.getLanguage());
    assertEquals(SupportedLanguage.ENGLISH, actualAgreementPaymentTypeResult.getLanguage());
    assertEquals(SupportedLanguage.ENGLISH, actualAmountResult.getLanguage());
    assertEquals(SupportedLanguage.ENGLISH, actualAuthorisationModeResult.getLanguage());
    assertEquals(SupportedLanguage.ENGLISH, actualCardholderNameResult.getLanguage());
    assertEquals(SupportedLanguage.ENGLISH, actualCityResult.getLanguage());
    assertEquals(SupportedLanguage.ENGLISH, actualCountryResult.getLanguage());
    assertEquals(SupportedLanguage.ENGLISH, actualDelayedCaptureResult.getLanguage());
    assertEquals(SupportedLanguage.ENGLISH, actualDescriptionResult.getLanguage());
    assertEquals(SupportedLanguage.ENGLISH, actualEmailResult.getLanguage());
    assertEquals(SupportedLanguage.ENGLISH, actualLanguageResult.getLanguage());
    assertEquals(SupportedLanguage.ENGLISH, actualMetadataResult.getLanguage());
    assertEquals(SupportedLanguage.ENGLISH, actualMotoResult.getLanguage());
    assertEquals(SupportedLanguage.ENGLISH, actualPostcodeResult.getLanguage());
    assertEquals(SupportedLanguage.ENGLISH, actualReferenceResult.getLanguage());
    assertEquals(SupportedLanguage.ENGLISH, actualReturnUrlResult.getLanguage());
    assertEquals(SupportedLanguage.ENGLISH, actualSourceResult.getLanguage());
    assertEquals(SupportedLanguage.ENGLISH, actualBuilderResult.getLanguage());
    assertFalse(actualCreateCardPaymentRequest.getSetUpAgreement().isPresent());
    Optional<Boolean> delayedCapture = actualCreateCardPaymentRequest.getDelayedCapture();
    assertTrue(delayedCapture.get());
    assertTrue(agreementId.isPresent());
    assertTrue(agreementPaymentType.isPresent());
    assertTrue(authorisationMode.isPresent());
    assertTrue(delayedCapture.isPresent());
    assertTrue(email.isPresent());
    Optional<Internal> internal = actualCreateCardPaymentRequest.getInternal();
    assertTrue(internal.isPresent());
    assertTrue(language.isPresent());
    Optional<ExternalMetadata> metadata2 = actualCreateCardPaymentRequest.getMetadata();
    assertTrue(metadata2.isPresent());
    Optional<PrefilledCardholderDetails> prefilledCardholderDetails =
        actualCreateCardPaymentRequest.getPrefilledCardholderDetails();
    assertTrue(prefilledCardholderDetails.isPresent());
    assertTrue(actualAgreementIdResult.getDelayedCapture());
    assertTrue(actualAgreementPaymentTypeResult.getDelayedCapture());
    assertTrue(actualAmountResult.getDelayedCapture());
    assertTrue(actualAuthorisationModeResult.getDelayedCapture());
    assertTrue(actualCardholderNameResult.getDelayedCapture());
    assertTrue(actualCityResult.getDelayedCapture());
    assertTrue(actualCountryResult.getDelayedCapture());
    assertTrue(actualDelayedCaptureResult.getDelayedCapture());
    assertTrue(actualDescriptionResult.getDelayedCapture());
    assertTrue(actualEmailResult.getDelayedCapture());
    assertTrue(actualLanguageResult.getDelayedCapture());
    assertTrue(actualMetadataResult.getDelayedCapture());
    assertTrue(actualMotoResult.getDelayedCapture());
    assertTrue(actualPostcodeResult.getDelayedCapture());
    assertTrue(actualReferenceResult.getDelayedCapture());
    assertTrue(actualReturnUrlResult.getDelayedCapture());
    assertTrue(actualSourceResult.getDelayedCapture());
    assertTrue(actualBuilderResult.getDelayedCapture());
    assertTrue(actualAgreementIdResult.isMoto());
    assertTrue(actualAgreementPaymentTypeResult.isMoto());
    assertTrue(actualAmountResult.isMoto());
    assertTrue(actualAuthorisationModeResult.isMoto());
    assertTrue(actualCardholderNameResult.isMoto());
    assertTrue(actualCityResult.isMoto());
    assertTrue(actualCountryResult.isMoto());
    assertTrue(actualDelayedCaptureResult.isMoto());
    assertTrue(actualDescriptionResult.isMoto());
    assertTrue(actualEmailResult.isMoto());
    assertTrue(actualLanguageResult.isMoto());
    assertTrue(actualMetadataResult.isMoto());
    assertTrue(actualMotoResult.isMoto());
    assertTrue(actualPostcodeResult.isMoto());
    assertTrue(actualReferenceResult.isMoto());
    assertTrue(actualReturnUrlResult.isMoto());
    assertTrue(actualSourceResult.isMoto());
    assertTrue(actualBuilderResult.isMoto());
    assertEquals(
        actualBuilderResult.getPrefilledCardholderDetails(), prefilledCardholderDetails.get());
    assertEquals(actualBuilderResult.getInternal().getSource(), internal.get().getSource());
    assertSame(metadata, metadata2.get());
    assertSame(metadata, actualAgreementIdResult.getMetadata());
    assertSame(metadata, actualAgreementPaymentTypeResult.getMetadata());
    assertSame(metadata, actualAmountResult.getMetadata());
    assertSame(metadata, actualAuthorisationModeResult.getMetadata());
    assertSame(metadata, actualCardholderNameResult.getMetadata());
    assertSame(metadata, actualCityResult.getMetadata());
    assertSame(metadata, actualCountryResult.getMetadata());
    assertSame(metadata, actualDelayedCaptureResult.getMetadata());
    assertSame(metadata, actualDescriptionResult.getMetadata());
    assertSame(metadata, actualEmailResult.getMetadata());
    assertSame(metadata, actualLanguageResult.getMetadata());
    assertSame(metadata, actualMetadataResult.getMetadata());
    assertSame(metadata, actualMotoResult.getMetadata());
    assertSame(metadata, actualPostcodeResult.getMetadata());
    assertSame(metadata, actualReferenceResult.getMetadata());
    assertSame(metadata, actualReturnUrlResult.getMetadata());
    assertSame(metadata, actualSourceResult.getMetadata());
    assertSame(metadata, actualBuilderResult.getMetadata());
  }
}
