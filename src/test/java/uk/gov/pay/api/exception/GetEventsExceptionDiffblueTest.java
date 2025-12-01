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

class GetEventsExceptionDiffblueTest {
  /**
   * Test {@link GetEventsException#GetEventsException(Response)}.
   *
   * <p>Method under test: {@link GetEventsException#GetEventsException(Response)}
   */
  @Test
  @DisplayName("Test new GetEventsException(Response)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void GetEventsException.<init>(Response)"})
  void testNewGetEventsException() {
    // Arrange and Act
    GetEventsException actualGetEventsException =
        new GetEventsException(new OutboundJaxrsResponse(Status.OK, new OutboundMessageContext()));

    // Assert
    assertEquals(
        "OutboundJaxrsResponse{status=200, reason=OK, hasEntity=false, closed=false, buffered=false}",
        actualGetEventsException.getLocalizedMessage());
    assertEquals(
        "OutboundJaxrsResponse{status=200, reason=OK, hasEntity=false, closed=false, buffered=false}",
        actualGetEventsException.getMessage());
    assertNull(actualGetEventsException.getReason());
    assertNull(actualGetEventsException.getCause());
    assertEquals(0, actualGetEventsException.getSuppressed().length);
    assertEquals(200, actualGetEventsException.getErrorStatus());
    assertEquals(ErrorIdentifier.GENERIC, actualGetEventsException.getErrorIdentifier());
    assertFalse(actualGetEventsException.hasReason());
  }
}
