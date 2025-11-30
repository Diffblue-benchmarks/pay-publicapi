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

public class DisputesValidationExceptionDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DisputesValidationException#DisputesValidationException(RequestError)}
   *   <li>{@link DisputesValidationException#toString()}
   *   <li>{@link DisputesValidationException#getRequestError()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DisputesValidationException.<init>(RequestError)",
    "RequestError DisputesValidationException.getRequestError()",
    "String DisputesValidationException.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    RequestError requestError =
        RequestError.aHeaderRequestError("Header", Code.CREATE_PAYMENT_ACCOUNT_ERROR, "Parameters");

    // Act
    DisputesValidationException actualDisputesValidationException =
        new DisputesValidationException(requestError);
    String actualToStringResult = actualDisputesValidationException.toString();
    RequestError actualRequestError = actualDisputesValidationException.getRequestError();

    // Assert
    assertEquals(
        "DisputesValidationException{disputeError=RequestError{field=null, code=P0199, name=CREATE_PAYMENT"
            + "_ACCOUNT_ERROR, description='There is an error with this account. Contact support with your error code"
            + " - https://www.payments.service.gov.uk/support/ .'}}",
        actualToStringResult);
    assertNull(actualDisputesValidationException.getMessage());
    assertNull(actualDisputesValidationException.getCause());
    assertEquals(0, actualDisputesValidationException.getSuppressed().length);
    assertSame(requestError, actualRequestError);
  }
}
