package uk.gov.pay.api.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.model.RequestError;
import uk.gov.pay.api.model.RequestError.Code;

class DisputesValidationExceptionDiffblueTest {
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
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DisputesValidationException.<init>(RequestError)",
    "RequestError DisputesValidationException.getRequestError()",
    "String DisputesValidationException.toString()"
  })
  void testGettersAndSetters() {
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
