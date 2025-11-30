package uk.gov.pay.api.validation;

import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.pay.api.exception.PaymentValidationException;
import uk.gov.pay.api.service.PaymentSearchParams;
import uk.gov.pay.api.service.PaymentSearchParams.Builder;

public class PaymentSearchValidatorDiffblueTest {
  /**
   * Test {@link PaymentSearchValidator#validateSearchParameters(PaymentSearchParams)} with {@code
   * searchParams}.
   *
   * <p>Method under test: {@link
   * PaymentSearchValidator#validateSearchParameters(PaymentSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentSearchValidator.validateSearchParameters(PaymentSearchParams)"})
  public void testValidateSearchParametersWithSearchParams() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentSearchValidator.validateSearchParameters(PaymentSearchParams)"})
  public void testValidateSearchParametersWithSearchParams2() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentSearchValidator.validateSearchParameters(PaymentSearchParams)"})
  public void testValidateSearchParametersWithSearchParams3() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentSearchValidator.validateSearchParameters(PaymentSearchParams)"})
  public void testValidateSearchParametersWithSearchParams4() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentSearchValidator.validateSearchParameters(PaymentSearchParams)"})
  public void testValidateSearchParametersWithSearchParams5() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentSearchValidator.validateSearchParameters(PaymentSearchParams)"})
  public void testValidateSearchParametersWithSearchParams6() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentSearchValidator.validateSearchParameters(PaymentSearchParams)"})
  public void testValidateSearchParametersWithSearchParams7() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentSearchValidator.validateSearchParameters(PaymentSearchParams)"})
  public void testValidateSearchParametersWithSearchParams8() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PaymentSearchValidator.validateSearchParameters(PaymentSearchParams)"})
  public void testValidateSearchParametersWithSearchParams_thenDoesNotThrow() {
    // Arrange, Act and Assert
    PaymentSearchValidator.validateSearchParameters(new PaymentSearchParams(new Builder()));
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentSearchValidator.validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String)"
  })
  public void
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentSearchValidator.validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String)"
  })
  public void
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentSearchValidator.validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String)"
  })
  public void
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentSearchValidator.validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String)"
  })
  public void
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentSearchValidator.validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String)"
  })
  public void
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentSearchValidator.validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String)"
  })
  public void
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentSearchValidator.validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String)"
  })
  public void
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentSearchValidator.validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String)"
  })
  public void
      testValidateSearchParametersWithStateReferenceEmailCardBrandFromDateToDatePageNumberDisplaySizeFirstDigitsCardNumberLastDigitsCardNumberFromSettledDateToSettledDate8() {
    // Arrange, Act and Assert
    PaymentSearchValidator.validateSearchParameters(
        " ", "not blank", "not blank", "not blank", " ", " ", "1", "1", " ", " ", null, null);
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentSearchValidator.validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String)"
  })
  public void
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentSearchValidator.validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String)"
  })
  public void
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentSearchValidator.validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String)"
  })
  public void
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentSearchValidator.validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String)"
  })
  public void
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentSearchValidator.validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String)"
  })
  public void
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentSearchValidator.validateSearchParameters(String, String, String, String, String, String, String, String, String, String, String, String)"
  })
  public void
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
