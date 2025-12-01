package uk.gov.pay.api.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.glassfish.jersey.message.internal.OutboundJaxrsResponse;
import org.glassfish.jersey.message.internal.OutboundMessageContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.service.payments.commons.model.ErrorIdentifier;

class AuthorisationRequestExceptionDiffblueTest {
  /**
   * Test {@link AuthorisationRequestException#AuthorisationRequestException(Response)}.
   *
   * <p>Method under test: {@link
   * AuthorisationRequestException#AuthorisationRequestException(Response)}
   */
  @Test
  @DisplayName("Test new AuthorisationRequestException(Response)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AuthorisationRequestException.<init>(Response)"})
  void testNewAuthorisationRequestException() {
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
