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

public class BadRefundsRequestExceptionDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link BadRefundsRequestException#BadRefundsRequestException(RequestError)}
   *   <li>{@link BadRefundsRequestException#toString()}
   *   <li>{@link BadRefundsRequestException#getRequestError()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BadRefundsRequestException.<init>(RequestError)",
    "RequestError BadRefundsRequestException.getRequestError()",
    "String BadRefundsRequestException.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    RequestError refundError =
        RequestError.aHeaderRequestError("Header", Code.CREATE_PAYMENT_ACCOUNT_ERROR, "Parameters");

    // Act
    BadRefundsRequestException actualBadRefundsRequestException =
        new BadRefundsRequestException(refundError);
    String actualToStringResult = actualBadRefundsRequestException.toString();
    RequestError actualRequestError = actualBadRefundsRequestException.getRequestError();

    // Assert
    assertEquals(
        "BadRefundsRequestException{refundError=RequestError{field=null, code=P0199, name=CREATE_PAYMENT_ACCOUNT"
            + "_ERROR, description='There is an error with this account. Contact support with your error code -"
            + " https://www.payments.service.gov.uk/support/ .'}}",
        actualToStringResult);
    assertNull(actualBadRefundsRequestException.getMessage());
    assertNull(actualBadRefundsRequestException.getCause());
    assertEquals(0, actualBadRefundsRequestException.getSuppressed().length);
    assertSame(refundError, actualRequestError);
  }
}
