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

public class GetEventsExceptionDiffblueTest {
  /**
   * Test {@link GetEventsException#GetEventsException(Response)}.
   *
   * <p>Method under test: {@link GetEventsException#GetEventsException(Response)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void GetEventsException.<init>(Response)"})
  public void testNewGetEventsException() {
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
