package uk.gov.pay.api.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.glassfish.jersey.message.internal.OutboundJaxrsResponse;
import org.glassfish.jersey.message.internal.OutboundMessageContext;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.service.payments.commons.model.ErrorIdentifier;

public class AuthorisationRequestExceptionDiffblueTest {
  /**
   * Test {@link AuthorisationRequestException#AuthorisationRequestException(Response)}.
   *
   * <p>Method under test: {@link
   * AuthorisationRequestException#AuthorisationRequestException(Response)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AuthorisationRequestException.<init>(Response)"})
  public void testNewAuthorisationRequestException() {
    // Arrange and Act
    AuthorisationRequestException actualAuthorisationRequestException =
        new AuthorisationRequestException(
            new OutboundJaxrsResponse(Status.OK, new OutboundMessageContext()));

    // Assert
    assertEquals(
        "OutboundJaxrsResponse{status=200, reason=OK, hasEntity=false, closed=false, buffered=false}",
        actualAuthorisationRequestException.getLocalizedMessage());
    assertEquals(
        "OutboundJaxrsResponse{status=200, reason=OK, hasEntity=false, closed=false, buffered=false}",
        actualAuthorisationRequestException.getMessage());
    assertNull(actualAuthorisationRequestException.getReason());
    assertNull(actualAuthorisationRequestException.getCause());
    assertEquals(0, actualAuthorisationRequestException.getSuppressed().length);
    assertEquals(200, actualAuthorisationRequestException.getErrorStatus());
    assertEquals(ErrorIdentifier.GENERIC, actualAuthorisationRequestException.getErrorIdentifier());
    assertFalse(actualAuthorisationRequestException.hasReason());
  }
}
