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

public class PaymentValidationExceptionDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PaymentValidationException#PaymentValidationException(RequestError)}
   *   <li>{@link PaymentValidationException#toString()}
   *   <li>{@link PaymentValidationException#getRequestError()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentValidationException.<init>(RequestError)",
    "RequestError PaymentValidationException.getRequestError()",
    "String PaymentValidationException.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    RequestError requestError =
        RequestError.aHeaderRequestError("Header", Code.CREATE_PAYMENT_ACCOUNT_ERROR, "Parameters");

    // Act
    PaymentValidationException actualPaymentValidationException =
        new PaymentValidationException(requestError);
    String actualToStringResult = actualPaymentValidationException.toString();
    RequestError actualRequestError = actualPaymentValidationException.getRequestError();

    // Assert
    assertEquals(
        "PaymentValidationException{requestError=RequestError{field=null, code=P0199, name=CREATE_PAYMENT_ACCOUNT"
            + "_ERROR, description='There is an error with this account. Contact support with your error code -"
            + " https://www.payments.service.gov.uk/support/ .'}}",
        actualToStringResult);
    assertNull(actualPaymentValidationException.getMessage());
    assertNull(actualPaymentValidationException.getCause());
    assertEquals(0, actualPaymentValidationException.getSuppressed().length);
    assertSame(requestError, actualRequestError);
  }
}
