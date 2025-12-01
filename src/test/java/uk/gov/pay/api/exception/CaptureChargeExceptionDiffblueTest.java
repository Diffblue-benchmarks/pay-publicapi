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

class CaptureChargeExceptionDiffblueTest {
  /**
   * Test {@link CaptureChargeException#CaptureChargeException(Response)}.
   *
   * <p>Method under test: {@link CaptureChargeException#CaptureChargeException(Response)}
   */
  @Test
  @DisplayName("Test new CaptureChargeException(Response)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CaptureChargeException.<init>(Response)"})
  void testNewCaptureChargeException() {
    // Arrange and Act
    CaptureChargeException actualCaptureChargeException =
        new CaptureChargeException(
            new OutboundJaxrsResponse(Status.OK, new OutboundMessageContext()));

    // Assert
    assertEquals(
        "OutboundJaxrsResponse{status=200, reason=OK, hasEntity=false, closed=false, buffered=false}",
        actualCaptureChargeException.getLocalizedMessage());
    assertEquals(
        "OutboundJaxrsResponse{status=200, reason=OK, hasEntity=false, closed=false, buffered=false}",
        actualCaptureChargeException.getMessage());
    assertNull(actualCaptureChargeException.getReason());
    assertNull(actualCaptureChargeException.getCause());
    assertEquals(0, actualCaptureChargeException.getSuppressed().length);
    assertEquals(200, actualCaptureChargeException.getErrorStatus());
    assertEquals(ErrorIdentifier.GENERIC, actualCaptureChargeException.getErrorIdentifier());
    assertFalse(actualCaptureChargeException.hasReason());
  }
}
