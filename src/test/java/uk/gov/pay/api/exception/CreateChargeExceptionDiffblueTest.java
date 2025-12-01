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

class CreateChargeExceptionDiffblueTest {
  /**
   * Test {@link CreateChargeException#CreateChargeException(Response)}.
   *
   * <p>Method under test: {@link CreateChargeException#CreateChargeException(Response)}
   */
  @Test
  @DisplayName("Test new CreateChargeException(Response)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CreateChargeException.<init>(Response)"})
  void testNewCreateChargeException() {
    // Arrange and Act
    CreateChargeException actualCreateChargeException =
        new CreateChargeException(
            new OutboundJaxrsResponse(Status.OK, new OutboundMessageContext()));

    // Assert
    assertEquals(
        "OutboundJaxrsResponse{status=200, reason=OK, hasEntity=false, closed=false, buffered=false}",
        actualCreateChargeException.getLocalizedMessage());
    assertEquals(
        "OutboundJaxrsResponse{status=200, reason=OK, hasEntity=false, closed=false, buffered=false}",
        actualCreateChargeException.getMessage());
    assertNull(actualCreateChargeException.getReason());
    assertNull(actualCreateChargeException.getCause());
    assertEquals(0, actualCreateChargeException.getSuppressed().length);
    assertEquals(200, actualCreateChargeException.getErrorStatus());
    assertEquals(ErrorIdentifier.GENERIC, actualCreateChargeException.getErrorIdentifier());
    assertFalse(actualCreateChargeException.hasReason());
  }
}
