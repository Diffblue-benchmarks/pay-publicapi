package uk.gov.pay.api.validation;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.pay.api.exception.DisputesValidationException;
import uk.gov.pay.api.model.RequestError;
import uk.gov.pay.api.model.RequestError.Code;
import uk.gov.pay.api.service.DisputesSearchParams;
import uk.gov.pay.api.service.DisputesSearchParams.Builder;

public class DisputeSearchValidatorDiffblueTest {
  /**
   * Test {@link DisputeSearchValidator#validateDisputeParameters(DisputesSearchParams)}.
   *
   * <p>Method under test: {@link
   * DisputeSearchValidator#validateDisputeParameters(DisputesSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DisputeSearchValidator.validateDisputeParameters(DisputesSearchParams)"})
  public void testValidateDisputeParameters() {
    // Arrange
    DisputesSearchParams params =
        new Builder()
            .withDisplaySize("Display Size")
            .withFromDate("2020-03-01")
            .withFromSettledDate("2020-03-01")
            .withPage("Page")
            .withStatus("Status")
            .withToDate("2020-03-01")
            .withToSettledDate("2020-03-01")
            .build();

    // Act and Assert
    assertThrows(
        DisputesValidationException.class,
        () -> DisputeSearchValidator.validateDisputeParameters(params));
  }

  /**
   * Test {@link DisputeSearchValidator#validateDisputeParameters(DisputesSearchParams)}.
   *
   * <p>Method under test: {@link
   * DisputeSearchValidator#validateDisputeParameters(DisputesSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DisputeSearchValidator.validateDisputeParameters(DisputesSearchParams)"})
  public void testValidateDisputeParameters2() {
    // Arrange
    DisputesSearchParams params = mock(DisputesSearchParams.class);
    when(params.getDisplaySize()).thenReturn("not blank");
    when(params.getFromSettledDate()).thenReturn("not blank");
    when(params.getPage()).thenReturn("not blank");
    when(params.getToSettledDate()).thenReturn("not blank");
    when(params.getFromDate()).thenReturn("2020-03-01");
    when(params.getState()).thenReturn("MD");
    when(params.getToDate()).thenReturn("2020-03-01");

    // Act and Assert
    assertThrows(
        DisputesValidationException.class,
        () -> DisputeSearchValidator.validateDisputeParameters(params));
    verify(params).getDisplaySize();
    verify(params).getFromDate();
    verify(params).getFromSettledDate();
    verify(params).getPage();
    verify(params).getState();
    verify(params).getToDate();
    verify(params).getToSettledDate();
  }

  /**
   * Test {@link DisputeSearchValidator#validateDisputeParameters(DisputesSearchParams)}.
   *
   * <p>Method under test: {@link
   * DisputeSearchValidator#validateDisputeParameters(DisputesSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DisputeSearchValidator.validateDisputeParameters(DisputesSearchParams)"})
  public void testValidateDisputeParameters3() {
    // Arrange
    DisputesSearchParams params = mock(DisputesSearchParams.class);
    when(params.getPage())
        .thenThrow(
            new DisputesValidationException(
                RequestError.aHeaderRequestError(
                    "Header", Code.CREATE_PAYMENT_ACCOUNT_ERROR, "Parameters")));

    // Act and Assert
    assertThrows(
        DisputesValidationException.class,
        () -> DisputeSearchValidator.validateDisputeParameters(params));
    verify(params).getPage();
  }

  /**
   * Test {@link DisputeSearchValidator#validateDisputeParameters(DisputesSearchParams)}.
   *
   * <p>Method under test: {@link
   * DisputeSearchValidator#validateDisputeParameters(DisputesSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DisputeSearchValidator.validateDisputeParameters(DisputesSearchParams)"})
  public void testValidateDisputeParameters4() {
    // Arrange
    DisputesSearchParams params = mock(DisputesSearchParams.class);
    when(params.getDisplaySize()).thenReturn("");
    when(params.getFromSettledDate()).thenReturn("not blank");
    when(params.getPage()).thenReturn("not blank");
    when(params.getToSettledDate()).thenReturn("not blank");
    when(params.getFromDate()).thenReturn("2020-03-01");
    when(params.getState()).thenReturn("MD");
    when(params.getToDate()).thenReturn("2020-03-01");

    // Act and Assert
    assertThrows(
        DisputesValidationException.class,
        () -> DisputeSearchValidator.validateDisputeParameters(params));
    verify(params).getDisplaySize();
    verify(params).getFromDate();
    verify(params).getFromSettledDate();
    verify(params).getPage();
    verify(params).getState();
    verify(params).getToDate();
    verify(params).getToSettledDate();
  }

  /**
   * Test {@link DisputeSearchValidator#validateDisputeParameters(DisputesSearchParams)}.
   *
   * <p>Method under test: {@link
   * DisputeSearchValidator#validateDisputeParameters(DisputesSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DisputeSearchValidator.validateDisputeParameters(DisputesSearchParams)"})
  public void testValidateDisputeParameters5() {
    // Arrange
    DisputesSearchParams params = mock(DisputesSearchParams.class);
    when(params.getDisplaySize()).thenReturn("not blank");
    when(params.getFromSettledDate()).thenReturn("");
    when(params.getPage()).thenReturn("not blank");
    when(params.getToSettledDate()).thenReturn("not blank");
    when(params.getFromDate()).thenReturn("2020-03-01");
    when(params.getState()).thenReturn("MD");
    when(params.getToDate()).thenReturn("2020-03-01");

    // Act and Assert
    assertThrows(
        DisputesValidationException.class,
        () -> DisputeSearchValidator.validateDisputeParameters(params));
    verify(params).getDisplaySize();
    verify(params).getFromDate();
    verify(params).getFromSettledDate();
    verify(params).getPage();
    verify(params).getState();
    verify(params).getToDate();
    verify(params).getToSettledDate();
  }

  /**
   * Test {@link DisputeSearchValidator#validateDisputeParameters(DisputesSearchParams)}.
   *
   * <p>Method under test: {@link
   * DisputeSearchValidator#validateDisputeParameters(DisputesSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DisputeSearchValidator.validateDisputeParameters(DisputesSearchParams)"})
  public void testValidateDisputeParameters6() {
    // Arrange
    DisputesSearchParams params = mock(DisputesSearchParams.class);
    when(params.getDisplaySize()).thenReturn("not blank");
    when(params.getFromSettledDate()).thenReturn("not blank");
    when(params.getPage()).thenReturn("not blank");
    when(params.getToSettledDate()).thenReturn("");
    when(params.getFromDate()).thenReturn("2020-03-01");
    when(params.getState()).thenReturn("MD");
    when(params.getToDate()).thenReturn("2020-03-01");

    // Act and Assert
    assertThrows(
        DisputesValidationException.class,
        () -> DisputeSearchValidator.validateDisputeParameters(params));
    verify(params).getDisplaySize();
    verify(params).getFromDate();
    verify(params).getFromSettledDate();
    verify(params).getPage();
    verify(params).getState();
    verify(params).getToDate();
    verify(params).getToSettledDate();
  }

  /**
   * Test {@link DisputeSearchValidator#validateDisputeParameters(DisputesSearchParams)}.
   *
   * <p>Method under test: {@link
   * DisputeSearchValidator#validateDisputeParameters(DisputesSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DisputeSearchValidator.validateDisputeParameters(DisputesSearchParams)"})
  public void testValidateDisputeParameters7() {
    // Arrange
    DisputesSearchParams params = mock(DisputesSearchParams.class);
    when(params.getDisplaySize()).thenReturn("not blank");
    when(params.getFromSettledDate()).thenReturn("not blank");
    when(params.getPage()).thenReturn("not blank");
    when(params.getToSettledDate()).thenReturn("not blank");
    when(params.getFromDate()).thenReturn("");
    when(params.getState()).thenReturn("MD");
    when(params.getToDate()).thenReturn("2020-03-01");

    // Act and Assert
    assertThrows(
        DisputesValidationException.class,
        () -> DisputeSearchValidator.validateDisputeParameters(params));
    verify(params).getDisplaySize();
    verify(params).getFromDate();
    verify(params).getFromSettledDate();
    verify(params).getPage();
    verify(params).getState();
    verify(params).getToDate();
    verify(params).getToSettledDate();
  }

  /**
   * Test {@link DisputeSearchValidator#validateDisputeParameters(DisputesSearchParams)}.
   *
   * <ul>
   *   <li>Given {@code 9}.
   *   <li>When {@link DisputesSearchParams} {@link DisputesSearchParams#getPage()} return {@code
   *       9}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DisputeSearchValidator#validateDisputeParameters(DisputesSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DisputeSearchValidator.validateDisputeParameters(DisputesSearchParams)"})
  public void testValidateDisputeParameters_given9_whenDisputesSearchParamsGetPageReturn9() {
    // Arrange
    DisputesSearchParams params = mock(DisputesSearchParams.class);
    when(params.getDisplaySize()).thenReturn("not blank");
    when(params.getFromSettledDate()).thenReturn("not blank");
    when(params.getPage()).thenReturn("9");
    when(params.getToSettledDate()).thenReturn("not blank");
    when(params.getFromDate()).thenReturn("2020-03-01");
    when(params.getState()).thenReturn("MD");
    when(params.getToDate()).thenReturn("2020-03-01");

    // Act and Assert
    assertThrows(
        DisputesValidationException.class,
        () -> DisputeSearchValidator.validateDisputeParameters(params));
    verify(params).getDisplaySize();
    verify(params).getFromDate();
    verify(params).getFromSettledDate();
    verify(params).getPage();
    verify(params).getState();
    verify(params).getToDate();
    verify(params).getToSettledDate();
  }

  /**
   * Test {@link DisputeSearchValidator#validateDisputeParameters(DisputesSearchParams)}.
   *
   * <ul>
   *   <li>When {@link DisputesSearchParams} {@link DisputesSearchParams#getDisplaySize()} return
   *       {@code 9}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DisputeSearchValidator#validateDisputeParameters(DisputesSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DisputeSearchValidator.validateDisputeParameters(DisputesSearchParams)"})
  public void testValidateDisputeParameters_whenDisputesSearchParamsGetDisplaySizeReturn9() {
    // Arrange
    DisputesSearchParams params = mock(DisputesSearchParams.class);
    when(params.getDisplaySize()).thenReturn("9");
    when(params.getFromSettledDate()).thenReturn("not blank");
    when(params.getPage()).thenReturn("not blank");
    when(params.getToSettledDate()).thenReturn("not blank");
    when(params.getFromDate()).thenReturn("2020-03-01");
    when(params.getState()).thenReturn("MD");
    when(params.getToDate()).thenReturn("2020-03-01");

    // Act and Assert
    assertThrows(
        DisputesValidationException.class,
        () -> DisputeSearchValidator.validateDisputeParameters(params));
    verify(params).getDisplaySize();
    verify(params).getFromDate();
    verify(params).getFromSettledDate();
    verify(params).getPage();
    verify(params).getState();
    verify(params).getToDate();
    verify(params).getToSettledDate();
  }

  /**
   * Test {@link DisputeSearchValidator#validateDisputeParameters(DisputesSearchParams)}.
   *
   * <ul>
   *   <li>When {@link DisputesSearchParams} {@link DisputesSearchParams#getPage()} return empty
   *       string.
   * </ul>
   *
   * <p>Method under test: {@link
   * DisputeSearchValidator#validateDisputeParameters(DisputesSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DisputeSearchValidator.validateDisputeParameters(DisputesSearchParams)"})
  public void testValidateDisputeParameters_whenDisputesSearchParamsGetPageReturnEmptyString() {
    // Arrange
    DisputesSearchParams params = mock(DisputesSearchParams.class);
    when(params.getDisplaySize()).thenReturn("not blank");
    when(params.getFromSettledDate()).thenReturn("not blank");
    when(params.getPage()).thenReturn("");
    when(params.getToSettledDate()).thenReturn("not blank");
    when(params.getFromDate()).thenReturn("2020-03-01");
    when(params.getState()).thenReturn("MD");
    when(params.getToDate()).thenReturn("2020-03-01");

    // Act and Assert
    assertThrows(
        DisputesValidationException.class,
        () -> DisputeSearchValidator.validateDisputeParameters(params));
    verify(params).getDisplaySize();
    verify(params).getFromDate();
    verify(params).getFromSettledDate();
    verify(params).getPage();
    verify(params).getState();
    verify(params).getToDate();
    verify(params).getToSettledDate();
  }

  /**
   * Test {@link DisputeSearchValidator#validateDisputeParameters(DisputesSearchParams)}.
   *
   * <ul>
   *   <li>When {@link DisputesSearchParams} {@link DisputesSearchParams#getState()} return empty
   *       string.
   * </ul>
   *
   * <p>Method under test: {@link
   * DisputeSearchValidator#validateDisputeParameters(DisputesSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DisputeSearchValidator.validateDisputeParameters(DisputesSearchParams)"})
  public void testValidateDisputeParameters_whenDisputesSearchParamsGetStateReturnEmptyString() {
    // Arrange
    DisputesSearchParams params = mock(DisputesSearchParams.class);
    when(params.getDisplaySize()).thenReturn("not blank");
    when(params.getFromSettledDate()).thenReturn("not blank");
    when(params.getPage()).thenReturn("not blank");
    when(params.getToSettledDate()).thenReturn("not blank");
    when(params.getFromDate()).thenReturn("2020-03-01");
    when(params.getState()).thenReturn("");
    when(params.getToDate()).thenReturn("2020-03-01");

    // Act and Assert
    assertThrows(
        DisputesValidationException.class,
        () -> DisputeSearchValidator.validateDisputeParameters(params));
    verify(params).getDisplaySize();
    verify(params).getFromDate();
    verify(params).getFromSettledDate();
    verify(params).getPage();
    verify(params).getState();
    verify(params).getToDate();
    verify(params).getToSettledDate();
  }

  /**
   * Test {@link DisputeSearchValidator#validateDisputeParameters(DisputesSearchParams)}.
   *
   * <ul>
   *   <li>When {@link DisputesSearchParams} {@link DisputesSearchParams#getToDate()} return empty
   *       string.
   * </ul>
   *
   * <p>Method under test: {@link
   * DisputeSearchValidator#validateDisputeParameters(DisputesSearchParams)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DisputeSearchValidator.validateDisputeParameters(DisputesSearchParams)"})
  public void testValidateDisputeParameters_whenDisputesSearchParamsGetToDateReturnEmptyString() {
    // Arrange
    DisputesSearchParams params = mock(DisputesSearchParams.class);
    when(params.getDisplaySize()).thenReturn("not blank");
    when(params.getFromSettledDate()).thenReturn("not blank");
    when(params.getPage()).thenReturn("not blank");
    when(params.getToSettledDate()).thenReturn("not blank");
    when(params.getFromDate()).thenReturn("2020-03-01");
    when(params.getState()).thenReturn("MD");
    when(params.getToDate()).thenReturn("");

    // Act and Assert
    assertThrows(
        DisputesValidationException.class,
        () -> DisputeSearchValidator.validateDisputeParameters(params));
    verify(params).getDisplaySize();
    verify(params).getFromDate();
    verify(params).getFromSettledDate();
    verify(params).getPage();
    verify(params).getState();
    verify(params).getToDate();
    verify(params).getToSettledDate();
  }
}
