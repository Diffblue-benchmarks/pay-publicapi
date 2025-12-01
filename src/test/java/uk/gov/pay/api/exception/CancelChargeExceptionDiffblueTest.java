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

class CancelChargeExceptionDiffblueTest {
  /**
   * Test {@link CancelChargeException#CancelChargeException(Response)}.
   *
   * <p>Method under test: {@link CancelChargeException#CancelChargeException(Response)}
   */
  @Test
  @DisplayName("Test new CancelChargeException(Response)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CancelChargeException.<init>(Response)"})
  void testNewCancelChargeException() {
    // Arrange and Act
    CancelChargeException actualCancelChargeException =
        new CancelChargeException(
            new OutboundJaxrsResponse(Status.OK, new OutboundMessageContext()));

    // Assert
    assertEquals(
        "OutboundJaxrsResponse{status=200, reason=OK, hasEntity=false, closed=false, buffered=false}",
        actualCancelChargeException.getLocalizedMessage());
    assertEquals(
        "OutboundJaxrsResponse{status=200, reason=OK, hasEntity=false, closed=false, buffered=false}",
        actualCancelChargeException.getMessage());
    assertNull(actualCancelChargeException.getReason());
    assertNull(actualCancelChargeException.getCause());
    assertEquals(0, actualCancelChargeException.getSuppressed().length);
    assertEquals(200, actualCancelChargeException.getErrorStatus());
    assertEquals(ErrorIdentifier.GENERIC, actualCancelChargeException.getErrorIdentifier());
    assertFalse(actualCancelChargeException.hasReason());
  }
}
