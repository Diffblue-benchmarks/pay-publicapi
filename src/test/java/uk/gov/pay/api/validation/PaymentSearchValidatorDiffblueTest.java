package uk.gov.pay.api.validation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.exception.PaymentValidationException;
import uk.gov.pay.api.service.PaymentSearchParams;
import uk.gov.pay.api.service.PaymentSearchParams.Builder;

class PaymentSearchValidatorDiffblueTest {
  /**
   * Test {@link PaymentSearchValidator#validateSearchParameters(PaymentSearchParams)} with {@code
   * searchParams}.
   *
   * <p>Method under test: {@link
   * PaymentSearchValidator#validateSearchParameters(PaymentSearchParams)}
   */
  @Test
  @DisplayName("Test validateSearchParameters(PaymentSearchParams) with 'searchParams'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentSearchValidator.validateSearchParameters(PaymentSearchParams)"})
  void testValidateSearchParametersWithSearchParams() {
    // Arrange
    PaymentSearchParams searchParams =
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

    // Act and Assert
    assertThrows(
        PaymentValidationException.class,
        () -> PaymentSearchValidator.validateSearchParameters(searchParams));
  }

  /**
   * Test {@link PaymentSearchValidator#validateSearchParameters(PaymentSearchParams)} with {@code
   * searchParams}.
   *
   * <p>Method under test: {@link
   * PaymentSearchValidator#validateSearchParameters(PaymentSearchParams)}
   */
  @Test
  @DisplayName("Test validateSearchParameters(PaymentSearchParams) with 'searchParams'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentSearchValidator.validateSearchParameters(PaymentSearchParams)"})
  void testValidateSearchParametersWithSearchParams2() {
    // Arrange
    PaymentSearchParams searchParams =
        new Builder()
            .withAgreementId("42")
            .withCardBrand("first_digits_card_number")
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

    // Act and Assert
    assertThrows(
        PaymentValidationException.class,
        () -> PaymentSearchValidator.validateSearchParameters(searchParams));
  }

  /**
   * Test {@link PaymentSearchValidator#validateSearchParameters(PaymentSearchParams)} with {@code
   * searchParams}.
   *
   * <p>Method under test: {@link
   * PaymentSearchValidator#validateSearchParameters(PaymentSearchParams)}
   */
  @Test
  @DisplayName("Test validateSearchParameters(PaymentSearchParams) with 'searchParams'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentSearchValidator.validateSearchParameters(PaymentSearchParams)"})
  void testValidateSearchParametersWithSearchParams3() {
    // Arrange
    PaymentSearchParams searchParams =
        new Builder()
            .withAgreementId("42")
            .withCardBrand("Card Brand")
            .withCardHolderName("Card Holder Name")
            .withDisplaySize("9")
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

    // Act and Assert
    assertThrows(
        PaymentValidationException.class,
        () -> PaymentSearchValidator.validateSearchParameters(searchParams));
  }

  /**
   * Test {@link PaymentSearchValidator#validateSearchParameters(PaymentSearchParams)} with {@code
   * searchParams}.
   *
   * <p>Method under test: {@link
   * PaymentSearchValidator#validateSearchParameters(PaymentSearchParams)}
   */
  @Test
  @DisplayName("Test validateSearchParameters(PaymentSearchParams) with 'searchParams'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentSearchValidator.validateSearchParameters(PaymentSearchParams)"})
  void testValidateSearchParametersWithSearchParams4() {
    // Arrange
    PaymentSearchParams searchParams =
        new Builder()
            .withAgreementId("42")
            .withCardBrand("Card Brand")
            .withCardHolderName("Card Holder Name")
            .withDisplaySize("")
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

    // Act and Assert
    assertThrows(
        PaymentValidationException.class,
        () -> PaymentSearchValidator.validateSearchParameters(searchParams));
  }

  /**
   * Test {@link PaymentSearchValidator#validateSearchParameters(PaymentSearchParams)} with {@code
   * searchParams}.
   *
   * <p>Method under test: {@link
   * PaymentSearchValidator#validateSearchParameters(PaymentSearchParams)}
   */
  @Test
  @DisplayName("Test validateSearchParameters(PaymentSearchParams) with 'searchParams'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentSearchValidator.validateSearchParameters(PaymentSearchParams)"})
  void testValidateSearchParametersWithSearchParams5() {
    // Arrange
    PaymentSearchParams searchParams =
        new Builder()
            .withAgreementId("42")
            .withCardBrand("Card Brand")
            .withCardHolderName("Card Holder Name")
            .withDisplaySize("Display Size")
            .withEmail("jane.doe@example.org")
            .withFirstDigitsCardNumber("")
            .withFromDate("2020-03-01")
            .withFromSettledDate("2020-03-01")
            .withLastDigitsCardNumber("42")
            .withPageNumber("42")
            .withReference("Reference")
            .withState("MD")
            .withToDate("2020-03-01")
            .withToSettledDate("2020-03-01")
            .build();

    // Act and Assert
    assertThrows(
        PaymentValidationException.class,
        () -> PaymentSearchValidator.validateSearchParameters(searchParams));
  }

  /**
   * Test {@link PaymentSearchValidator#validateSearchParameters(PaymentSearchParams)} with {@code
   * searchParams}.
   *
   * <p>Method under test: {@link
   * PaymentSearchValidator#validateSearchParameters(PaymentSearchParams)}
   */
  @Test
  @DisplayName("Test validateSearchParameters(PaymentSearchParams) with 'searchParams'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentSearchValidator.validateSearchParameters(PaymentSearchParams)"})
  void testValidateSearchParametersWithSearchParams6() {
    // Arrange
    PaymentSearchParams searchParams =
        new Builder()
            .withAgreementId("42")
            .withCardBrand("Card Brand")
            .withCardHolderName("Card Holder Name")
            .withDisplaySize("Display Size")
            .withEmail("jane.doe@example.org")
            .withFirstDigitsCardNumber("42")
            .withFromDate("2020-03-01")
            .withFromSettledDate("2020/03/01")
            .withLastDigitsCardNumber("42")
            .withPageNumber("42")
            .withReference("Reference")
            .withState("MD")
            .withToDate("2020-03-01")
            .withToSettledDate("2020-03-01")
            .build();

    // Act and Assert
    assertThrows(
        PaymentValidationException.class,
        () -> PaymentSearchValidator.validateSearchParameters(searchParams));
  }

  /**
   * Test {@link PaymentSearchValidator#validateSearchParameters(PaymentSearchParams)} with {@code
   * searchParams}.
   *
   * <p>Method under test: {@link
   * PaymentSearchValidator#validateSearchParameters(PaymentSearchParams)}
   */
  @Test
  @DisplayName("Test validateSearchParameters(PaymentSearchParams) with 'searchParams'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentSearchValidator.validateSearchParameters(PaymentSearchParams)"})
  void testValidateSearchParametersWithSearchParams7() {
    // Arrange
    PaymentSearchParams searchParams =
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
            .withPageNumber("state")
            .withReference("Reference")
            .withState("MD")
            .withToDate("2020-03-01")
            .withToSettledDate("2020-03-01")
            .build();

    // Act and Assert
    assertThrows(
        PaymentValidationException.class,
        () -> PaymentSearchValidator.validateSearchParameters(searchParams));
  }

  /**
   * Test {@link PaymentSearchValidator#validateSearchParameters(PaymentSearchParams)} with {@code
   * searchParams}.
   *
   * <p>Method under test: {@link
   * PaymentSearchValidator#validateSearchParameters(PaymentSearchParams)}
   */
  @Test
  @DisplayName("Test validateSearchParameters(PaymentSearchParams) with 'searchParams'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentSearchValidator.validateSearchParameters(PaymentSearchParams)"})
  void testValidateSearchParametersWithSearchParams8() {
    // Arrange
    PaymentSearchParams searchParams =
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
            .withToSettledDate("2020/03/01")
            .build();

    // Act and Assert
    assertThrows(
        PaymentValidationException.class,
        () -> PaymentSearchValidator.validateSearchParameters(searchParams));
  }

  /**
   * Test {@link PaymentSearchValidator#validateSearchParameters(PaymentSearchParams)} with {@code
   * searchParams}.
   *
   * <ul>
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link
   * PaymentSearchValidator#validateSearchParameters(PaymentSearchParams)}
   */
  @Test
  @DisplayName(
      "Test validateSearchParameters(PaymentSearchParams) with 'searchParams'; then does not throw")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentSearchValidator.validateSearchParameters(PaymentSearchParams)"})
  void testValidateSearchParametersWithSearchParams_thenDoesNotThrow() {
    // Arrange, Act and Assert
    assertDoesNotThrow(
        () ->
            PaymentSearchValidator.validateSearchParameters(
                new PaymentSearchParams(new Builder())));
  }

  /**
   * Test {@link PaymentSearchValidator#validateSearchParameters(String, String, String, String,
   * String, String, String, String, String, String, String, String)} with {@code state}, {@code
   * reference}, {@code email}, {@code cardBrand}, {@code fromDate}, {@code toDate}, {@code
   * pageNumber}, {@code displaySize}, {@code firstDigitsCardNumber}, {@code lastDigitsCardNumber},
   * {@code fromSettledDate}, {@code toSettledDate}.
   *
   * <p>Method under test: {@link PaymentSearchValidator#validateSearchParameters(String, String,
   * String, String, String, String, String, String, String, String, String, String)}
   */
  @Test
  @DisplayName(
      "Test validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String) with 'state', 'reference', 'email', 'cardBrand', 'fromDate', 'toDate', 'pageNumber', 'displaySize', 'firstDigitsCardNumber', 'lastDigitsCardNumber', 'fromSettledDate', 'toSettledDate'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentSearchValidator.validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String)"
  })
  void
      testValidateSearchParametersWithStateReferenceEmailCardBrandFromDateToDatePageNumberDisplaySizeFirstDigitsCardNumberLastDigitsCardNumberFromSettledDateToSettledDate() {
    // Arrange, Act and Assert
    assertThrows(
        PaymentValidationException.class,
        () ->
            PaymentSearchValidator.validateSearchParameters(
                "MD",
                "Reference",
                "jane.doe@example.org",
                "Card Brand",
                "2020-03-01",
                "2020-03-01",
                "42",
                "Display Size",
                "42",
                "42",
                "2020-03-01",
                "2020-03-01"));
  }

  /**
   * Test {@link PaymentSearchValidator#validateSearchParameters(String, String, String, String,
   * String, String, String, String, String, String, String, String)} with {@code state}, {@code
   * reference}, {@code email}, {@code cardBrand}, {@code fromDate}, {@code toDate}, {@code
   * pageNumber}, {@code displaySize}, {@code firstDigitsCardNumber}, {@code lastDigitsCardNumber},
   * {@code fromSettledDate}, {@code toSettledDate}.
   *
   * <p>Method under test: {@link PaymentSearchValidator#validateSearchParameters(String, String,
   * String, String, String, String, String, String, String, String, String, String)}
   */
  @Test
  @DisplayName(
      "Test validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String) with 'state', 'reference', 'email', 'cardBrand', 'fromDate', 'toDate', 'pageNumber', 'displaySize', 'firstDigitsCardNumber', 'lastDigitsCardNumber', 'fromSettledDate', 'toSettledDate'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentSearchValidator.validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String)"
  })
  void
      testValidateSearchParametersWithStateReferenceEmailCardBrandFromDateToDatePageNumberDisplaySizeFirstDigitsCardNumberLastDigitsCardNumberFromSettledDateToSettledDate2() {
    // Arrange, Act and Assert
    assertThrows(
        PaymentValidationException.class,
        () ->
            PaymentSearchValidator.validateSearchParameters(
                " ",
                "not blank",
                "not blank",
                "not blank",
                " ",
                " ",
                "not blank",
                "not blank",
                " ",
                " ",
                "not blank",
                "not blank"));
  }

  /**
   * Test {@link PaymentSearchValidator#validateSearchParameters(String, String, String, String,
   * String, String, String, String, String, String, String, String)} with {@code state}, {@code
   * reference}, {@code email}, {@code cardBrand}, {@code fromDate}, {@code toDate}, {@code
   * pageNumber}, {@code displaySize}, {@code firstDigitsCardNumber}, {@code lastDigitsCardNumber},
   * {@code fromSettledDate}, {@code toSettledDate}.
   *
   * <p>Method under test: {@link PaymentSearchValidator#validateSearchParameters(String, String,
   * String, String, String, String, String, String, String, String, String, String)}
   */
  @Test
  @DisplayName(
      "Test validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String) with 'state', 'reference', 'email', 'cardBrand', 'fromDate', 'toDate', 'pageNumber', 'displaySize', 'firstDigitsCardNumber', 'lastDigitsCardNumber', 'fromSettledDate', 'toSettledDate'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentSearchValidator.validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String)"
  })
  void
      testValidateSearchParametersWithStateReferenceEmailCardBrandFromDateToDatePageNumberDisplaySizeFirstDigitsCardNumberLastDigitsCardNumberFromSettledDateToSettledDate3() {
    // Arrange, Act and Assert
    assertThrows(
        PaymentValidationException.class,
        () ->
            PaymentSearchValidator.validateSearchParameters(
                " ",
                "not blank",
                "not blank",
                "not blank",
                " ",
                " ",
                "not blank",
                "not blank",
                " ",
                " ",
                "not blank",
                null));
  }

  /**
   * Test {@link PaymentSearchValidator#validateSearchParameters(String, String, String, String,
   * String, String, String, String, String, String, String, String)} with {@code state}, {@code
   * reference}, {@code email}, {@code cardBrand}, {@code fromDate}, {@code toDate}, {@code
   * pageNumber}, {@code displaySize}, {@code firstDigitsCardNumber}, {@code lastDigitsCardNumber},
   * {@code fromSettledDate}, {@code toSettledDate}.
   *
   * <p>Method under test: {@link PaymentSearchValidator#validateSearchParameters(String, String,
   * String, String, String, String, String, String, String, String, String, String)}
   */
  @Test
  @DisplayName(
      "Test validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String) with 'state', 'reference', 'email', 'cardBrand', 'fromDate', 'toDate', 'pageNumber', 'displaySize', 'firstDigitsCardNumber', 'lastDigitsCardNumber', 'fromSettledDate', 'toSettledDate'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentSearchValidator.validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String)"
  })
  void
      testValidateSearchParametersWithStateReferenceEmailCardBrandFromDateToDatePageNumberDisplaySizeFirstDigitsCardNumberLastDigitsCardNumberFromSettledDateToSettledDate4() {
    // Arrange, Act and Assert
    assertThrows(
        PaymentValidationException.class,
        () ->
            PaymentSearchValidator.validateSearchParameters(
                " ",
                "not blank",
                "not blank",
                "not blank",
                " ",
                " ",
                "not blank",
                "not blank",
                " ",
                " ",
                null,
                "not blank"));
  }

  /**
   * Test {@link PaymentSearchValidator#validateSearchParameters(String, String, String, String,
   * String, String, String, String, String, String, String, String)} with {@code state}, {@code
   * reference}, {@code email}, {@code cardBrand}, {@code fromDate}, {@code toDate}, {@code
   * pageNumber}, {@code displaySize}, {@code firstDigitsCardNumber}, {@code lastDigitsCardNumber},
   * {@code fromSettledDate}, {@code toSettledDate}.
   *
   * <p>Method under test: {@link PaymentSearchValidator#validateSearchParameters(String, String,
   * String, String, String, String, String, String, String, String, String, String)}
   */
  @Test
  @DisplayName(
      "Test validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String) with 'state', 'reference', 'email', 'cardBrand', 'fromDate', 'toDate', 'pageNumber', 'displaySize', 'firstDigitsCardNumber', 'lastDigitsCardNumber', 'fromSettledDate', 'toSettledDate'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentSearchValidator.validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String)"
  })
  void
      testValidateSearchParametersWithStateReferenceEmailCardBrandFromDateToDatePageNumberDisplaySizeFirstDigitsCardNumberLastDigitsCardNumberFromSettledDateToSettledDate5() {
    // Arrange, Act and Assert
    assertThrows(
        PaymentValidationException.class,
        () ->
            PaymentSearchValidator.validateSearchParameters(
                " ",
                "not blank",
                "not blank",
                "not blank",
                " ",
                " ",
                "not blank",
                "1",
                " ",
                " ",
                "not blank",
                "not blank"));
  }

  /**
   * Test {@link PaymentSearchValidator#validateSearchParameters(String, String, String, String,
   * String, String, String, String, String, String, String, String)} with {@code state}, {@code
   * reference}, {@code email}, {@code cardBrand}, {@code fromDate}, {@code toDate}, {@code
   * pageNumber}, {@code displaySize}, {@code firstDigitsCardNumber}, {@code lastDigitsCardNumber},
   * {@code fromSettledDate}, {@code toSettledDate}.
   *
   * <p>Method under test: {@link PaymentSearchValidator#validateSearchParameters(String, String,
   * String, String, String, String, String, String, String, String, String, String)}
   */
  @Test
  @DisplayName(
      "Test validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String) with 'state', 'reference', 'email', 'cardBrand', 'fromDate', 'toDate', 'pageNumber', 'displaySize', 'firstDigitsCardNumber', 'lastDigitsCardNumber', 'fromSettledDate', 'toSettledDate'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentSearchValidator.validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String)"
  })
  void
      testValidateSearchParametersWithStateReferenceEmailCardBrandFromDateToDatePageNumberDisplaySizeFirstDigitsCardNumberLastDigitsCardNumberFromSettledDateToSettledDate6() {
    // Arrange, Act and Assert
    assertThrows(
        PaymentValidationException.class,
        () ->
            PaymentSearchValidator.validateSearchParameters(
                " ",
                "not blank",
                "not blank",
                "not blank",
                " ",
                " ",
                "not blank",
                "0",
                " ",
                " ",
                "not blank",
                "not blank"));
  }

  /**
   * Test {@link PaymentSearchValidator#validateSearchParameters(String, String, String, String,
   * String, String, String, String, String, String, String, String)} with {@code state}, {@code
   * reference}, {@code email}, {@code cardBrand}, {@code fromDate}, {@code toDate}, {@code
   * pageNumber}, {@code displaySize}, {@code firstDigitsCardNumber}, {@code lastDigitsCardNumber},
   * {@code fromSettledDate}, {@code toSettledDate}.
   *
   * <p>Method under test: {@link PaymentSearchValidator#validateSearchParameters(String, String,
   * String, String, String, String, String, String, String, String, String, String)}
   */
  @Test
  @DisplayName(
      "Test validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String) with 'state', 'reference', 'email', 'cardBrand', 'fromDate', 'toDate', 'pageNumber', 'displaySize', 'firstDigitsCardNumber', 'lastDigitsCardNumber', 'fromSettledDate', 'toSettledDate'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentSearchValidator.validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String)"
  })
  void
      testValidateSearchParametersWithStateReferenceEmailCardBrandFromDateToDatePageNumberDisplaySizeFirstDigitsCardNumberLastDigitsCardNumberFromSettledDateToSettledDate7() {
    // Arrange, Act and Assert
    assertThrows(
        PaymentValidationException.class,
        () ->
            PaymentSearchValidator.validateSearchParameters(
                " ",
                "not blank",
                "not blank",
                "not blank",
                " ",
                " ",
                "not blank",
                null,
                " ",
                " ",
                "not blank",
                "not blank"));
  }

  /**
   * Test {@link PaymentSearchValidator#validateSearchParameters(String, String, String, String,
   * String, String, String, String, String, String, String, String)} with {@code state}, {@code
   * reference}, {@code email}, {@code cardBrand}, {@code fromDate}, {@code toDate}, {@code
   * pageNumber}, {@code displaySize}, {@code firstDigitsCardNumber}, {@code lastDigitsCardNumber},
   * {@code fromSettledDate}, {@code toSettledDate}.
   *
   * <p>Method under test: {@link PaymentSearchValidator#validateSearchParameters(String, String,
   * String, String, String, String, String, String, String, String, String, String)}
   */
  @Test
  @DisplayName(
      "Test validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String) with 'state', 'reference', 'email', 'cardBrand', 'fromDate', 'toDate', 'pageNumber', 'displaySize', 'firstDigitsCardNumber', 'lastDigitsCardNumber', 'fromSettledDate', 'toSettledDate'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentSearchValidator.validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String)"
  })
  void
      testValidateSearchParametersWithStateReferenceEmailCardBrandFromDateToDatePageNumberDisplaySizeFirstDigitsCardNumberLastDigitsCardNumberFromSettledDateToSettledDate8() {
    // Arrange, Act and Assert
    assertDoesNotThrow(
        () ->
            PaymentSearchValidator.validateSearchParameters(
                " ",
                "not blank",
                "not blank",
                "not blank",
                " ",
                " ",
                "1",
                "1",
                " ",
                " ",
                null,
                null));
  }

  /**
   * Test {@link PaymentSearchValidator#validateSearchParameters(String, String, String, String,
   * String, String, String, String, String, String, String, String)} with {@code state}, {@code
   * reference}, {@code email}, {@code cardBrand}, {@code fromDate}, {@code toDate}, {@code
   * pageNumber}, {@code displaySize}, {@code firstDigitsCardNumber}, {@code lastDigitsCardNumber},
   * {@code fromSettledDate}, {@code toSettledDate}.
   *
   * <p>Method under test: {@link PaymentSearchValidator#validateSearchParameters(String, String,
   * String, String, String, String, String, String, String, String, String, String)}
   */
  @Test
  @DisplayName(
      "Test validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String) with 'state', 'reference', 'email', 'cardBrand', 'fromDate', 'toDate', 'pageNumber', 'displaySize', 'firstDigitsCardNumber', 'lastDigitsCardNumber', 'fromSettledDate', 'toSettledDate'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentSearchValidator.validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String)"
  })
  void
      testValidateSearchParametersWithStateReferenceEmailCardBrandFromDateToDatePageNumberDisplaySizeFirstDigitsCardNumberLastDigitsCardNumberFromSettledDateToSettledDate9() {
    // Arrange, Act and Assert
    assertThrows(
        PaymentValidationException.class,
        () ->
            PaymentSearchValidator.validateSearchParameters(
                " ",
                "not blank",
                "not blank",
                "not blank",
                " ",
                " ",
                "0",
                "not blank",
                " ",
                " ",
                "not blank",
                "not blank"));
  }

  /**
   * Test {@link PaymentSearchValidator#validateSearchParameters(String, String, String, String,
   * String, String, String, String, String, String, String, String)} with {@code state}, {@code
   * reference}, {@code email}, {@code cardBrand}, {@code fromDate}, {@code toDate}, {@code
   * pageNumber}, {@code displaySize}, {@code firstDigitsCardNumber}, {@code lastDigitsCardNumber},
   * {@code fromSettledDate}, {@code toSettledDate}.
   *
   * <p>Method under test: {@link PaymentSearchValidator#validateSearchParameters(String, String,
   * String, String, String, String, String, String, String, String, String, String)}
   */
  @Test
  @DisplayName(
      "Test validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String) with 'state', 'reference', 'email', 'cardBrand', 'fromDate', 'toDate', 'pageNumber', 'displaySize', 'firstDigitsCardNumber', 'lastDigitsCardNumber', 'fromSettledDate', 'toSettledDate'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentSearchValidator.validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String)"
  })
  void
      testValidateSearchParametersWithStateReferenceEmailCardBrandFromDateToDatePageNumberDisplaySizeFirstDigitsCardNumberLastDigitsCardNumberFromSettledDateToSettledDate10() {
    // Arrange, Act and Assert
    assertThrows(
        PaymentValidationException.class,
        () ->
            PaymentSearchValidator.validateSearchParameters(
                "cancelled",
                "Reference",
                "jane.doe@example.org",
                "Card Brand",
                "2020-03-01",
                "2020-03-01",
                "42",
                "Display Size",
                "42",
                "42",
                "2020-03-01",
                "2020-03-01"));
  }

  /**
   * Test {@link PaymentSearchValidator#validateSearchParameters(String, String, String, String,
   * String, String, String, String, String, String, String, String)} with {@code state}, {@code
   * reference}, {@code email}, {@code cardBrand}, {@code fromDate}, {@code toDate}, {@code
   * pageNumber}, {@code displaySize}, {@code firstDigitsCardNumber}, {@code lastDigitsCardNumber},
   * {@code fromSettledDate}, {@code toSettledDate}.
   *
   * <p>Method under test: {@link PaymentSearchValidator#validateSearchParameters(String, String,
   * String, String, String, String, String, String, String, String, String, String)}
   */
  @Test
  @DisplayName(
      "Test validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String) with 'state', 'reference', 'email', 'cardBrand', 'fromDate', 'toDate', 'pageNumber', 'displaySize', 'firstDigitsCardNumber', 'lastDigitsCardNumber', 'fromSettledDate', 'toSettledDate'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentSearchValidator.validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String)"
  })
  void
      testValidateSearchParametersWithStateReferenceEmailCardBrandFromDateToDatePageNumberDisplaySizeFirstDigitsCardNumberLastDigitsCardNumberFromSettledDateToSettledDate11() {
    // Arrange, Act and Assert
    assertThrows(
        PaymentValidationException.class,
        () ->
            PaymentSearchValidator.validateSearchParameters(
                "MD",
                " ",
                "jane.doe@example.org",
                "Card Brand",
                "2020-03-01",
                "2020-03-01",
                "42",
                "Display Size",
                "42",
                "42",
                "2020-03-01",
                "2020-03-01"));
  }

  /**
   * Test {@link PaymentSearchValidator#validateSearchParameters(String, String, String, String,
   * String, String, String, String, String, String, String, String)} with {@code state}, {@code
   * reference}, {@code email}, {@code cardBrand}, {@code fromDate}, {@code toDate}, {@code
   * pageNumber}, {@code displaySize}, {@code firstDigitsCardNumber}, {@code lastDigitsCardNumber},
   * {@code fromSettledDate}, {@code toSettledDate}.
   *
   * <p>Method under test: {@link PaymentSearchValidator#validateSearchParameters(String, String,
   * String, String, String, String, String, String, String, String, String, String)}
   */
  @Test
  @DisplayName(
      "Test validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String) with 'state', 'reference', 'email', 'cardBrand', 'fromDate', 'toDate', 'pageNumber', 'displaySize', 'firstDigitsCardNumber', 'lastDigitsCardNumber', 'fromSettledDate', 'toSettledDate'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentSearchValidator.validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String)"
  })
  void
      testValidateSearchParametersWithStateReferenceEmailCardBrandFromDateToDatePageNumberDisplaySizeFirstDigitsCardNumberLastDigitsCardNumberFromSettledDateToSettledDate12() {
    // Arrange, Act and Assert
    assertThrows(
        PaymentValidationException.class,
        () ->
            PaymentSearchValidator.validateSearchParameters(
                "MD",
                "",
                "jane.doe@example.org",
                "Card Brand",
                "2020-03-01",
                "2020-03-01",
                "42",
                "Display Size",
                "42",
                "42",
                "2020-03-01",
                "2020-03-01"));
  }

  /**
   * Test {@link PaymentSearchValidator#validateSearchParameters(String, String, String, String,
   * String, String, String, String, String, String, String, String)} with {@code state}, {@code
   * reference}, {@code email}, {@code cardBrand}, {@code fromDate}, {@code toDate}, {@code
   * pageNumber}, {@code displaySize}, {@code firstDigitsCardNumber}, {@code lastDigitsCardNumber},
   * {@code fromSettledDate}, {@code toSettledDate}.
   *
   * <p>Method under test: {@link PaymentSearchValidator#validateSearchParameters(String, String,
   * String, String, String, String, String, String, String, String, String, String)}
   */
  @Test
  @DisplayName(
      "Test validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String) with 'state', 'reference', 'email', 'cardBrand', 'fromDate', 'toDate', 'pageNumber', 'displaySize', 'firstDigitsCardNumber', 'lastDigitsCardNumber', 'fromSettledDate', 'toSettledDate'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentSearchValidator.validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String)"
  })
  void
      testValidateSearchParametersWithStateReferenceEmailCardBrandFromDateToDatePageNumberDisplaySizeFirstDigitsCardNumberLastDigitsCardNumberFromSettledDateToSettledDate13() {
    // Arrange, Act and Assert
    assertThrows(
        PaymentValidationException.class,
        () ->
            PaymentSearchValidator.validateSearchParameters(
                "MD",
                "Reference",
                "jane.doe@example.org",
                "first_digits_card_number",
                "2020-03-01",
                "2020-03-01",
                "42",
                "Display Size",
                "42",
                "42",
                "2020-03-01",
                "2020-03-01"));
  }

  /**
   * Test {@link PaymentSearchValidator#validateSearchParameters(String, String, String, String,
   * String, String, String, String, String, String, String, String)} with {@code state}, {@code
   * reference}, {@code email}, {@code cardBrand}, {@code fromDate}, {@code toDate}, {@code
   * pageNumber}, {@code displaySize}, {@code firstDigitsCardNumber}, {@code lastDigitsCardNumber},
   * {@code fromSettledDate}, {@code toSettledDate}.
   *
   * <p>Method under test: {@link PaymentSearchValidator#validateSearchParameters(String, String,
   * String, String, String, String, String, String, String, String, String, String)}
   */
  @Test
  @DisplayName(
      "Test validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String) with 'state', 'reference', 'email', 'cardBrand', 'fromDate', 'toDate', 'pageNumber', 'displaySize', 'firstDigitsCardNumber', 'lastDigitsCardNumber', 'fromSettledDate', 'toSettledDate'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentSearchValidator.validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String)"
  })
  void
      testValidateSearchParametersWithStateReferenceEmailCardBrandFromDateToDatePageNumberDisplaySizeFirstDigitsCardNumberLastDigitsCardNumberFromSettledDateToSettledDate14() {
    // Arrange, Act and Assert
    assertThrows(
        PaymentValidationException.class,
        () ->
            PaymentSearchValidator.validateSearchParameters(
                "MD",
                "Reference",
                "jane.doe@example.org",
                "Card Brand",
                "2020-03-01",
                "2020-03-01",
                " ",
                "Display Size",
                "42",
                "42",
                "2020-03-01",
                "2020-03-01"));
  }
}
