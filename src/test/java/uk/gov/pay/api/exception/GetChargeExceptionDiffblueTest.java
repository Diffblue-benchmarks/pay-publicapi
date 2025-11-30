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

public class GetChargeExceptionDiffblueTest {
  /**
   * Test {@link GetChargeException#GetChargeException(Response)}.
   *
   * <p>Method under test: {@link GetChargeException#GetChargeException(Response)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void GetChargeException.<init>(Response)"})
  public void testNewGetChargeException() {
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
