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

public class CancelChargeExceptionDiffblueTest {
  /**
   * Test {@link CancelChargeException#CancelChargeException(Response)}.
   *
   * <p>Method under test: {@link CancelChargeException#CancelChargeException(Response)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CancelChargeException.<init>(Response)"})
  public void testNewCancelChargeException() {
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
