package uk.gov.pay.api.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Optional;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.service.payments.commons.model.AgreementPaymentType;
import uk.gov.service.payments.commons.model.AuthorisationMode;
import uk.gov.service.payments.commons.model.Source;
import uk.gov.service.payments.commons.model.SupportedLanguage;
import uk.gov.service.payments.commons.model.charge.ExternalMetadata;

public class CreateCardPaymentRequestDiffblueTest {
  /**
   * Test {@link
   * CreateCardPaymentRequest#CreateCardPaymentRequest(CreateCardPaymentRequestBuilder)}.
   *
   * <ul>
   *   <li>When builder.
   *   <li>Then return Description is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * CreateCardPaymentRequest#CreateCardPaymentRequest(CreateCardPaymentRequestBuilder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CreateCardPaymentRequest.<init>(CreateCardPaymentRequestBuilder)"})
  public void testNewCreateCardPaymentRequest_whenBuilder_thenReturnDescriptionIsNull() {
    // Arrange and Act
    CreateCardPaymentRequest actualCreateCardPaymentRequest =
        new CreateCardPaymentRequest(CreateCardPaymentRequestBuilder.builder());

    // Assert
    assertNull(actualCreateCardPaymentRequest.getDescription());
    assertNull(actualCreateCardPaymentRequest.getReference());
    assertNull(actualCreateCardPaymentRequest.getReturnUrl());
    assertEquals(0, actualCreateCardPaymentRequest.getAmount());
    Optional<String> agreementId = actualCreateCardPaymentRequest.getAgreementId();
    assertFalse(agreementId.isPresent());
    assertSame(agreementId, actualCreateCardPaymentRequest.getAgreementPaymentType());
    assertSame(agreementId, actualCreateCardPaymentRequest.getAuthorisationMode());
    assertSame(agreementId, actualCreateCardPaymentRequest.getDelayedCapture());
    assertSame(agreementId, actualCreateCardPaymentRequest.getEmail());
    assertSame(agreementId, actualCreateCardPaymentRequest.getInternal());
    assertSame(agreementId, actualCreateCardPaymentRequest.getLanguage());
    assertSame(agreementId, actualCreateCardPaymentRequest.getMetadata());
    assertSame(agreementId, actualCreateCardPaymentRequest.getMoto());
    assertSame(agreementId, actualCreateCardPaymentRequest.getPrefilledCardholderDetails());
    assertSame(agreementId, actualCreateCardPaymentRequest.getSetUpAgreement());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CreateCardPaymentRequest#toString()}
   *   <li>{@link CreateCardPaymentRequest#getAmount()}
   *   <li>{@link CreateCardPaymentRequest#getDescription()}
   *   <li>{@link CreateCardPaymentRequest#getReference()}
   *   <li>{@link CreateCardPaymentRequest#getReturnUrl()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int CreateCardPaymentRequest.getAmount()",
    "String CreateCardPaymentRequest.getDescription()",
    "String CreateCardPaymentRequest.getReference()",
    "String CreateCardPaymentRequest.getReturnUrl()",
    "String CreateCardPaymentRequest.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    CreateCardPaymentRequest createCardPaymentRequest =
        new CreateCardPaymentRequest(CreateCardPaymentRequestBuilder.builder());

    // Act
    String actualToStringResult = createCardPaymentRequest.toString();
    int actualAmount = createCardPaymentRequest.getAmount();
    String actualDescription = createCardPaymentRequest.getDescription();
    String actualReference = createCardPaymentRequest.getReference();

    // Assert
    assertEquals("{amount: 0, return_url: null}", actualToStringResult);
    assertNull(actualDescription);
    assertNull(actualReference);
    assertNull(createCardPaymentRequest.getReturnUrl());
    assertEquals(0, actualAmount);
  }

  /**
   * Test {@link CreateCardPaymentRequest#getLanguage()}.
   *
   * <p>Method under test: {@link CreateCardPaymentRequest#getLanguage()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional CreateCardPaymentRequest.getLanguage()"})
  public void testGetLanguage() {
    // Arrange, Act and Assert
    assertFalse(
        new CreateCardPaymentRequest(CreateCardPaymentRequestBuilder.builder())
            .getLanguage()
            .isPresent());
  }

  /**
   * Test {@link CreateCardPaymentRequest#getEmail()}.
   *
   * <p>Method under test: {@link CreateCardPaymentRequest#getEmail()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional CreateCardPaymentRequest.getEmail()"})
  public void testGetEmail() {
    // Arrange, Act and Assert
    assertFalse(
        new CreateCardPaymentRequest(CreateCardPaymentRequestBuilder.builder())
            .getEmail()
            .isPresent());
  }

  /**
   * Test {@link CreateCardPaymentRequest#getPrefilledCardholderDetails()}.
   *
   * <p>Method under test: {@link CreateCardPaymentRequest#getPrefilledCardholderDetails()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional CreateCardPaymentRequest.getPrefilledCardholderDetails()"})
  public void testGetPrefilledCardholderDetails() {
    // Arrange, Act and Assert
    assertFalse(
        new CreateCardPaymentRequest(CreateCardPaymentRequestBuilder.builder())
            .getPrefilledCardholderDetails()
            .isPresent());
  }

  /**
   * Test {@link CreateCardPaymentRequest#getDelayedCapture()}.
   *
   * <p>Method under test: {@link CreateCardPaymentRequest#getDelayedCapture()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional CreateCardPaymentRequest.getDelayedCapture()"})
  public void testGetDelayedCapture() {
    // Arrange, Act and Assert
    assertFalse(
        new CreateCardPaymentRequest(CreateCardPaymentRequestBuilder.builder())
            .getDelayedCapture()
            .isPresent());
  }

  /**
   * Test {@link CreateCardPaymentRequest#getMoto()}.
   *
   * <p>Method under test: {@link CreateCardPaymentRequest#getMoto()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional CreateCardPaymentRequest.getMoto()"})
  public void testGetMoto() {
    // Arrange, Act and Assert
    assertFalse(
        new CreateCardPaymentRequest(CreateCardPaymentRequestBuilder.builder())
            .getMoto()
            .isPresent());
  }

  /**
   * Test {@link CreateCardPaymentRequest#getMetadata()}.
   *
   * <p>Method under test: {@link CreateCardPaymentRequest#getMetadata()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional CreateCardPaymentRequest.getMetadata()"})
  public void testGetMetadata() {
    // Arrange, Act and Assert
    assertFalse(
        new CreateCardPaymentRequest(CreateCardPaymentRequestBuilder.builder())
            .getMetadata()
            .isPresent());
  }

  /**
   * Test {@link CreateCardPaymentRequest#getInternal()}.
   *
   * <p>Method under test: {@link CreateCardPaymentRequest#getInternal()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional CreateCardPaymentRequest.getInternal()"})
  public void testGetInternal() {
    // Arrange, Act and Assert
    assertFalse(
        new CreateCardPaymentRequest(CreateCardPaymentRequestBuilder.builder())
            .getInternal()
            .isPresent());
  }

  /**
   * Test {@link CreateCardPaymentRequest#getSetUpAgreement()}.
   *
   * <p>Method under test: {@link CreateCardPaymentRequest#getSetUpAgreement()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional CreateCardPaymentRequest.getSetUpAgreement()"})
  public void testGetSetUpAgreement() {
    // Arrange, Act and Assert
    assertFalse(
        new CreateCardPaymentRequest(CreateCardPaymentRequestBuilder.builder())
            .getSetUpAgreement()
            .isPresent());
  }

  /**
   * Test {@link CreateCardPaymentRequest#getAgreementId()}.
   *
   * <p>Method under test: {@link CreateCardPaymentRequest#getAgreementId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional CreateCardPaymentRequest.getAgreementId()"})
  public void testGetAgreementId() {
    // Arrange, Act and Assert
    assertFalse(
        new CreateCardPaymentRequest(CreateCardPaymentRequestBuilder.builder())
            .getAgreementId()
            .isPresent());
  }

  /**
   * Test {@link CreateCardPaymentRequest#getAuthorisationMode()}.
   *
   * <p>Method under test: {@link CreateCardPaymentRequest#getAuthorisationMode()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional CreateCardPaymentRequest.getAuthorisationMode()"})
  public void testGetAuthorisationMode() {
    // Arrange, Act and Assert
    assertFalse(
        new CreateCardPaymentRequest(CreateCardPaymentRequestBuilder.builder())
            .getAuthorisationMode()
            .isPresent());
  }

  /**
   * Test {@link CreateCardPaymentRequest#getAgreementPaymentType()}.
   *
   * <p>Method under test: {@link CreateCardPaymentRequest#getAgreementPaymentType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional CreateCardPaymentRequest.getAgreementPaymentType()"})
  public void testGetAgreementPaymentType() {
    // Arrange, Act and Assert
    assertFalse(
        new CreateCardPaymentRequest(CreateCardPaymentRequestBuilder.builder())
            .getAgreementPaymentType()
            .isPresent());
  }

  /**
   * Test {@link CreateCardPaymentRequest#toConnectorPayload()}.
   *
   * <p>Method under test: {@link CreateCardPaymentRequest#toConnectorPayload()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CreateCardPaymentRequest.toConnectorPayload()"})
  public void testToConnectorPayload() {
    // Arrange, Act and Assert
    assertEquals(
        "{\n  \"amount\" : 0\n}",
        new CreateCardPaymentRequest(new CreateCardPaymentRequestBuilder()).toConnectorPayload());
  }

  /**
   * Test {@link CreateCardPaymentRequest#toConnectorPayload()}.
   *
   * <p>Method under test: {@link CreateCardPaymentRequest#toConnectorPayload()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CreateCardPaymentRequest.toConnectorPayload()"})
  public void testToConnectorPayload2() {
    // Arrange
    CreateCardPaymentRequestBuilder builder = CreateCardPaymentRequestBuilder.builder();
    builder.cardholderName(CreateCardPaymentRequest.AMOUNT_FIELD_NAME);

    // Act and Assert
    assertEquals(
        "{\n  \"amount\" : 0,\n  \"prefilled_cardholder_details\" : {\n    \"cardholder_name\" : \"amount\"\n  }\n}",
        new CreateCardPaymentRequest(builder).toConnectorPayload());
  }

  /**
   * Test {@link CreateCardPaymentRequest#toConnectorPayload()}.
   *
   * <ul>
   *   <li>Given builder addressLine1 {@code 42 Main St}.
   *   <li>Then return a string.
   * </ul>
   *
   * <p>Method under test: {@link CreateCardPaymentRequest#toConnectorPayload()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CreateCardPaymentRequest.toConnectorPayload()"})
  public void testToConnectorPayload_givenBuilderAddressLine142MainSt_thenReturnAString() {
    // Arrange
    CreateCardPaymentRequestBuilder builder = CreateCardPaymentRequestBuilder.builder();
    builder.addressLine1("42 Main St");

    // Act and Assert
    assertEquals(
        "{\n"
            + "  \"amount\" : 0,\n"
            + "  \"prefilled_cardholder_details\" : {\n"
            + "    \"billing_address\" : {\n"
            + "      \"line1\" : \"42 Main St\",\n"
            + "      \"line2\" : null,\n"
            + "      \"postcode\" : null,\n"
            + "      \"city\" : null,\n"
            + "      \"country\" : null\n"
            + "    }\n"
            + "  }\n"
            + "}",
        new CreateCardPaymentRequest(builder).toConnectorPayload());
  }

  /**
   * Test {@link CreateCardPaymentRequest#toConnectorPayload()}.
   *
   * <ul>
   *   <li>Given builder agreementId {@code 42}.
   *   <li>Then return a string.
   * </ul>
   *
   * <p>Method under test: {@link CreateCardPaymentRequest#toConnectorPayload()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CreateCardPaymentRequest.toConnectorPayload()"})
  public void testToConnectorPayload_givenBuilderAgreementId42_thenReturnAString() {
    // Arrange
    CreateCardPaymentRequestBuilder builder = CreateCardPaymentRequestBuilder.builder();
    builder.agreementId("42");
    builder.addressLine1("42 Main St");

    // Act and Assert
    assertEquals(
        "{\n"
            + "  \"amount\" : 0,\n"
            + "  \"agreement_id\" : \"42\",\n"
            + "  \"prefilled_cardholder_details\" : {\n"
            + "    \"billing_address\" : {\n"
            + "      \"line1\" : \"42 Main St\",\n"
            + "      \"line2\" : null,\n"
            + "      \"postcode\" : null,\n"
            + "      \"city\" : null,\n"
            + "      \"country\" : null\n"
            + "    }\n"
            + "  }\n"
            + "}",
        new CreateCardPaymentRequest(builder).toConnectorPayload());
  }

  /**
   * Test {@link CreateCardPaymentRequest#toConnectorPayload()}.
   *
   * <ul>
   *   <li>Given builder agreementPaymentType {@code INSTALMENT}.
   * </ul>
   *
   * <p>Method under test: {@link CreateCardPaymentRequest#toConnectorPayload()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CreateCardPaymentRequest.toConnectorPayload()"})
  public void testToConnectorPayload_givenBuilderAgreementPaymentTypeInstalment() {
    // Arrange
    CreateCardPaymentRequestBuilder builder = CreateCardPaymentRequestBuilder.builder();
    builder.agreementPaymentType(AgreementPaymentType.INSTALMENT);
    builder.addressLine1("42 Main St");

    // Act and Assert
    assertEquals(
        "{\n"
            + "  \"amount\" : 0,\n"
            + "  \"agreement_payment_type\" : \"instalment\",\n"
            + "  \"prefilled_cardholder_details\" : {\n"
            + "    \"billing_address\" : {\n"
            + "      \"line1\" : \"42 Main St\",\n"
            + "      \"line2\" : null,\n"
            + "      \"postcode\" : null,\n"
            + "      \"city\" : null,\n"
            + "      \"country\" : null\n"
            + "    }\n"
            + "  }\n"
            + "}",
        new CreateCardPaymentRequest(builder).toConnectorPayload());
  }

  /**
   * Test {@link CreateCardPaymentRequest#toConnectorPayload()}.
   *
   * <ul>
   *   <li>Given builder authorisationMode {@code WEB}.
   *   <li>Then return a string.
   * </ul>
   *
   * <p>Method under test: {@link CreateCardPaymentRequest#toConnectorPayload()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CreateCardPaymentRequest.toConnectorPayload()"})
  public void testToConnectorPayload_givenBuilderAuthorisationModeWeb_thenReturnAString() {
    // Arrange
    CreateCardPaymentRequestBuilder builder = CreateCardPaymentRequestBuilder.builder();
    builder.authorisationMode(AuthorisationMode.WEB);
    builder.addressLine1("42 Main St");

    // Act and Assert
    assertEquals(
        "{\n"
            + "  \"amount\" : 0,\n"
            + "  \"authorisation_mode\" : \"web\",\n"
            + "  \"prefilled_cardholder_details\" : {\n"
            + "    \"billing_address\" : {\n"
            + "      \"line1\" : \"42 Main St\",\n"
            + "      \"line2\" : null,\n"
            + "      \"postcode\" : null,\n"
            + "      \"city\" : null,\n"
            + "      \"country\" : null\n"
            + "    }\n"
            + "  }\n"
            + "}",
        new CreateCardPaymentRequest(builder).toConnectorPayload());
  }

  /**
   * Test {@link CreateCardPaymentRequest#toConnectorPayload()}.
   *
   * <ul>
   *   <li>Given builder cardholderName {@link CreateCardPaymentRequest#AMOUNT_FIELD_NAME}.
   * </ul>
   *
   * <p>Method under test: {@link CreateCardPaymentRequest#toConnectorPayload()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CreateCardPaymentRequest.toConnectorPayload()"})
  public void testToConnectorPayload_givenBuilderCardholderNameAmount_field_name() {
    // Arrange
    CreateCardPaymentRequestBuilder builder = CreateCardPaymentRequestBuilder.builder();
    builder.cardholderName(CreateCardPaymentRequest.AMOUNT_FIELD_NAME);
    builder.addressLine1("42 Main St");

    // Act and Assert
    assertEquals(
        "{\n"
            + "  \"amount\" : 0,\n"
            + "  \"prefilled_cardholder_details\" : {\n"
            + "    \"cardholder_name\" : \"amount\",\n"
            + "    \"billing_address\" : {\n"
            + "      \"line1\" : \"42 Main St\",\n"
            + "      \"line2\" : null,\n"
            + "      \"postcode\" : null,\n"
            + "      \"city\" : null,\n"
            + "      \"country\" : null\n"
            + "    }\n"
            + "  }\n"
            + "}",
        new CreateCardPaymentRequest(builder).toConnectorPayload());
  }

  /**
   * Test {@link CreateCardPaymentRequest#toConnectorPayload()}.
   *
   * <ul>
   *   <li>Given builder delayedCapture {@code true}.
   *   <li>Then return a string.
   * </ul>
   *
   * <p>Method under test: {@link CreateCardPaymentRequest#toConnectorPayload()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CreateCardPaymentRequest.toConnectorPayload()"})
  public void testToConnectorPayload_givenBuilderDelayedCaptureTrue_thenReturnAString() {
    // Arrange
    CreateCardPaymentRequestBuilder builder = CreateCardPaymentRequestBuilder.builder();
    builder.delayedCapture(true);
    builder.addressLine1("42 Main St");

    // Act and Assert
    assertEquals(
        "{\n"
            + "  \"amount\" : 0,\n"
            + "  \"delayed_capture\" : true,\n"
            + "  \"prefilled_cardholder_details\" : {\n"
            + "    \"billing_address\" : {\n"
            + "      \"line1\" : \"42 Main St\",\n"
            + "      \"line2\" : null,\n"
            + "      \"postcode\" : null,\n"
            + "      \"city\" : null,\n"
            + "      \"country\" : null\n"
            + "    }\n"
            + "  }\n"
            + "}",
        new CreateCardPaymentRequest(builder).toConnectorPayload());
  }

  /**
   * Test {@link CreateCardPaymentRequest#toConnectorPayload()}.
   *
   * <ul>
   *   <li>Given builder email {@code jane.doe@example.org}.
   *   <li>Then return a string.
   * </ul>
   *
   * <p>Method under test: {@link CreateCardPaymentRequest#toConnectorPayload()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CreateCardPaymentRequest.toConnectorPayload()"})
  public void testToConnectorPayload_givenBuilderEmailJaneDoeExampleOrg_thenReturnAString() {
    // Arrange
    CreateCardPaymentRequestBuilder builder = CreateCardPaymentRequestBuilder.builder();
    builder.email("jane.doe@example.org");
    builder.addressLine1("42 Main St");

    // Act and Assert
    assertEquals(
        "{\n"
            + "  \"amount\" : 0,\n"
            + "  \"email\" : \"jane.doe@example.org\",\n"
            + "  \"prefilled_cardholder_details\" : {\n"
            + "    \"billing_address\" : {\n"
            + "      \"line1\" : \"42 Main St\",\n"
            + "      \"line2\" : null,\n"
            + "      \"postcode\" : null,\n"
            + "      \"city\" : null,\n"
            + "      \"country\" : null\n"
            + "    }\n"
            + "  }\n"
            + "}",
        new CreateCardPaymentRequest(builder).toConnectorPayload());
  }

  /**
   * Test {@link CreateCardPaymentRequest#toConnectorPayload()}.
   *
   * <ul>
   *   <li>Given builder language {@code ENGLISH}.
   *   <li>Then return a string.
   * </ul>
   *
   * <p>Method under test: {@link CreateCardPaymentRequest#toConnectorPayload()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CreateCardPaymentRequest.toConnectorPayload()"})
  public void testToConnectorPayload_givenBuilderLanguageEnglish_thenReturnAString() {
    // Arrange
    CreateCardPaymentRequestBuilder builder = CreateCardPaymentRequestBuilder.builder();
    builder.language(SupportedLanguage.ENGLISH);
    builder.addressLine1("42 Main St");

    // Act and Assert
    assertEquals(
        "{\n"
            + "  \"amount\" : 0,\n"
            + "  \"language\" : \"en\",\n"
            + "  \"prefilled_cardholder_details\" : {\n"
            + "    \"billing_address\" : {\n"
            + "      \"line1\" : \"42 Main St\",\n"
            + "      \"line2\" : null,\n"
            + "      \"postcode\" : null,\n"
            + "      \"city\" : null,\n"
            + "      \"country\" : null\n"
            + "    }\n"
            + "  }\n"
            + "}",
        new CreateCardPaymentRequest(builder).toConnectorPayload());
  }

  /**
   * Test {@link CreateCardPaymentRequest#toConnectorPayload()}.
   *
   * <ul>
   *   <li>Given builder metadata {@link ExternalMetadata#ExternalMetadata(Map)} with metadata is
   *       {@link HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link CreateCardPaymentRequest#toConnectorPayload()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CreateCardPaymentRequest.toConnectorPayload()"})
  public void testToConnectorPayload_givenBuilderMetadataExternalMetadataWithMetadataIsHashMap() {
    // Arrange
    CreateCardPaymentRequestBuilder builder = CreateCardPaymentRequestBuilder.builder();
    builder.metadata(new ExternalMetadata(new HashMap<>()));
    builder.addressLine1("42 Main St");

    // Act and Assert
    assertEquals(
        "{\n"
            + "  \"amount\" : 0,\n"
            + "  \"metadata\" : { },\n"
            + "  \"prefilled_cardholder_details\" : {\n"
            + "    \"billing_address\" : {\n"
            + "      \"line1\" : \"42 Main St\",\n"
            + "      \"line2\" : null,\n"
            + "      \"postcode\" : null,\n"
            + "      \"city\" : null,\n"
            + "      \"country\" : null\n"
            + "    }\n"
            + "  }\n"
            + "}",
        new CreateCardPaymentRequest(builder).toConnectorPayload());
  }

  /**
   * Test {@link CreateCardPaymentRequest#toConnectorPayload()}.
   *
   * <ul>
   *   <li>Given builder moto {@code true}.
   *   <li>Then return a string.
   * </ul>
   *
   * <p>Method under test: {@link CreateCardPaymentRequest#toConnectorPayload()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CreateCardPaymentRequest.toConnectorPayload()"})
  public void testToConnectorPayload_givenBuilderMotoTrue_thenReturnAString() {
    // Arrange
    CreateCardPaymentRequestBuilder builder = CreateCardPaymentRequestBuilder.builder();
    builder.moto(true);
    builder.addressLine1("42 Main St");

    // Act and Assert
    assertEquals(
        "{\n"
            + "  \"amount\" : 0,\n"
            + "  \"moto\" : true,\n"
            + "  \"prefilled_cardholder_details\" : {\n"
            + "    \"billing_address\" : {\n"
            + "      \"line1\" : \"42 Main St\",\n"
            + "      \"line2\" : null,\n"
            + "      \"postcode\" : null,\n"
            + "      \"city\" : null,\n"
            + "      \"country\" : null\n"
            + "    }\n"
            + "  }\n"
            + "}",
        new CreateCardPaymentRequest(builder).toConnectorPayload());
  }

  /**
   * Test {@link CreateCardPaymentRequest#toConnectorPayload()}.
   *
   * <ul>
   *   <li>Given builder source {@code CARD_API}.
   *   <li>Then return a string.
   * </ul>
   *
   * <p>Method under test: {@link CreateCardPaymentRequest#toConnectorPayload()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CreateCardPaymentRequest.toConnectorPayload()"})
  public void testToConnectorPayload_givenBuilderSourceCardApi_thenReturnAString() {
    // Arrange
    CreateCardPaymentRequestBuilder builder = CreateCardPaymentRequestBuilder.builder();
    builder.source(Source.CARD_API);
    builder.addressLine1("42 Main St");

    // Act and Assert
    assertEquals(
        "{\n"
            + "  \"amount\" : 0,\n"
            + "  \"source\" : \"CARD_API\",\n"
            + "  \"prefilled_cardholder_details\" : {\n"
            + "    \"billing_address\" : {\n"
            + "      \"line1\" : \"42 Main St\",\n"
            + "      \"line2\" : null,\n"
            + "      \"postcode\" : null,\n"
            + "      \"city\" : null,\n"
            + "      \"country\" : null\n"
            + "    }\n"
            + "  }\n"
            + "}",
        new CreateCardPaymentRequest(builder).toConnectorPayload());
  }

  /**
   * Test {@link CreateCardPaymentRequest#toConnectorPayload()}.
   *
   * <ul>
   *   <li>Given builder UpAgreement is {@link CreateCardPaymentRequest#AMOUNT_FIELD_NAME}.
   * </ul>
   *
   * <p>Method under test: {@link CreateCardPaymentRequest#toConnectorPayload()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CreateCardPaymentRequest.toConnectorPayload()"})
  public void testToConnectorPayload_givenBuilderUpAgreementIsAmount_field_name() {
    // Arrange
    CreateCardPaymentRequestBuilder builder = CreateCardPaymentRequestBuilder.builder();
    builder.setUpAgreement(CreateCardPaymentRequest.AMOUNT_FIELD_NAME);
    builder.addressLine1("42 Main St");

    // Act and Assert
    assertEquals(
        "{\n"
            + "  \"amount\" : 0,\n"
            + "  \"agreement_id\" : \"amount\",\n"
            + "  \"save_payment_instrument_to_agreement\" : true,\n"
            + "  \"prefilled_cardholder_details\" : {\n"
            + "    \"billing_address\" : {\n"
            + "      \"line1\" : \"42 Main St\",\n"
            + "      \"line2\" : null,\n"
            + "      \"postcode\" : null,\n"
            + "      \"city\" : null,\n"
            + "      \"country\" : null\n"
            + "    }\n"
            + "  }\n"
            + "}",
        new CreateCardPaymentRequest(builder).toConnectorPayload());
  }

  /**
   * Test {@link CreateCardPaymentRequest#toConnectorPayload()}.
   *
   * <ul>
   *   <li>Then return {@code { "amount" : 0 }}.
   * </ul>
   *
   * <p>Method under test: {@link CreateCardPaymentRequest#toConnectorPayload()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CreateCardPaymentRequest.toConnectorPayload()"})
  public void testToConnectorPayload_thenReturnAmount0() {
    // Arrange, Act and Assert
    assertEquals(
        "{\n  \"amount\" : 0\n}",
        new CreateCardPaymentRequest(CreateCardPaymentRequestBuilder.builder())
            .toConnectorPayload());
  }
}
