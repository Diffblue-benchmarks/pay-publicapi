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

public class RefundsValidationExceptionDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RefundsValidationException#RefundsValidationException(RequestError)}
   *   <li>{@link RefundsValidationException#toString()}
   *   <li>{@link RefundsValidationException#getRequestError()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RefundsValidationException.<init>(RequestError)",
    "RequestError RefundsValidationException.getRequestError()",
    "String RefundsValidationException.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    RequestError requestError =
        RequestError.aHeaderRequestError("Header", Code.CREATE_PAYMENT_ACCOUNT_ERROR, "Parameters");

    // Act
    RefundsValidationException actualRefundsValidationException =
        new RefundsValidationException(requestError);
    String actualToStringResult = actualRefundsValidationException.toString();
    RequestError actualRequestError = actualRefundsValidationException.getRequestError();

    // Assert
    assertEquals(
        "RefundsValidationException{refundError=RequestError{field=null, code=P0199, name=CREATE_PAYMENT_ACCOUNT"
            + "_ERROR, description='There is an error with this account. Contact support with your error code -"
            + " https://www.payments.service.gov.uk/support/ .'}}",
        actualToStringResult);
    assertNull(actualRefundsValidationException.getMessage());
    assertNull(actualRefundsValidationException.getCause());
    assertEquals(0, actualRefundsValidationException.getSuppressed().length);
    assertSame(requestError, actualRequestError);
  }
}
