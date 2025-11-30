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

public class BadRequestExceptionDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link BadRequestException#BadRequestException(RequestError)}
   *   <li>{@link BadRequestException#toString()}
   *   <li>{@link BadRequestException#getRequestError()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BadRequestException.<init>(RequestError)",
    "RequestError BadRequestException.getRequestError()",
    "String BadRequestException.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    RequestError requestError =
        RequestError.aHeaderRequestError("Header", Code.CREATE_PAYMENT_ACCOUNT_ERROR, "Parameters");

    // Act
    BadRequestException actualBadRequestException = new BadRequestException(requestError);
    String actualToStringResult = actualBadRequestException.toString();
    RequestError actualRequestError = actualBadRequestException.getRequestError();

    // Assert
    assertEquals(
        "BadRequestException{requestError=RequestError{field=null, code=P0199, name=CREATE_PAYMENT_ACCOUNT_ERROR,"
            + " description='There is an error with this account. Contact support with your error code - https://www"
            + ".payments.service.gov.uk/support/ .'}}",
        actualToStringResult);
    assertNull(actualBadRequestException.getMessage());
    assertNull(actualBadRequestException.getCause());
    assertEquals(0, actualBadRequestException.getSuppressed().length);
    assertSame(requestError, actualRequestError);
  }
}
