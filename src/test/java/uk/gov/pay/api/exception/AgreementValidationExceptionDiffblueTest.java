package uk.gov.pay.api.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.pay.api.model.RequestError;
import uk.gov.pay.api.model.RequestError.Code;

public class AgreementValidationExceptionDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link AgreementValidationException#AgreementValidationException(RequestError)}
   *   <li>{@link AgreementValidationException#toString()}
   *   <li>{@link AgreementValidationException#getRequestError()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AgreementValidationException.<init>(RequestError)",
    "RequestError AgreementValidationException.getRequestError()",
    "String AgreementValidationException.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    RequestError requestError =
        RequestError.aHeaderRequestError("Header", Code.CREATE_PAYMENT_ACCOUNT_ERROR, "Parameters");

    // Act
    AgreementValidationException actualAgreementValidationException =
        new AgreementValidationException(requestError);
    String actualToStringResult = actualAgreementValidationException.toString();
    RequestError actualRequestError = actualAgreementValidationException.getRequestError();

    // Assert
    assertEquals(
        "AgreementValidationException{requestError=RequestError{field=null, code=P0199, name=CREATE_PAYMENT"
            + "_ACCOUNT_ERROR, description='There is an error with this account. Contact support with your error code"
            + " - https://www.payments.service.gov.uk/support/ .'}}",
        actualToStringResult);
    assertNull(actualAgreementValidationException.getMessage());
    assertNull(actualAgreementValidationException.getCause());
    assertEquals(0, actualAgreementValidationException.getSuppressed().length);
    assertSame(requestError, actualRequestError);
  }
}
