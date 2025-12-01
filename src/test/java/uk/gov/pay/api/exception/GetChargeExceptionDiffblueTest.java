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

class GetChargeExceptionDiffblueTest {
  /**
   * Test {@link GetChargeException#GetChargeException(Response)}.
   *
   * <p>Method under test: {@link GetChargeException#GetChargeException(Response)}
   */
  @Test
  @DisplayName("Test new GetChargeException(Response)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void GetChargeException.<init>(Response)"})
  void testNewGetChargeException() {
    // Arrange and Act
    GetChargeException actualGetChargeException =
        new GetChargeException(new OutboundJaxrsResponse(Status.OK, new OutboundMessageContext()));

    // Assert
    assertEquals(
        "OutboundJaxrsResponse{status=200, reason=OK, hasEntity=false, closed=false, buffered=false}",
        actualGetChargeException.getLocalizedMessage());
    assertEquals(
        "OutboundJaxrsResponse{status=200, reason=OK, hasEntity=false, closed=false, buffered=false}",
        actualGetChargeException.getMessage());
    assertNull(actualGetChargeException.getReason());
    assertNull(actualGetChargeException.getCause());
    assertEquals(0, actualGetChargeException.getSuppressed().length);
    assertEquals(200, actualGetChargeException.getErrorStatus());
    assertEquals(ErrorIdentifier.GENERIC, actualGetChargeException.getErrorIdentifier());
    assertFalse(actualGetChargeException.hasReason());
  }
}
