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

public class CreateRefundExceptionDiffblueTest {
  /**
   * Test {@link CreateRefundException#CreateRefundException(Response)}.
   *
   * <p>Method under test: {@link CreateRefundException#CreateRefundException(Response)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CreateRefundException.<init>(Response)"})
  public void testNewCreateRefundException() {
    // Arrange and Act
    CreateRefundException actualCreateRefundException =
        new CreateRefundException(
            new OutboundJaxrsResponse(Status.OK, new OutboundMessageContext()));

    // Assert
    assertEquals(
        "OutboundJaxrsResponse{status=200, reason=OK, hasEntity=false, closed=false, buffered=false}",
        actualCreateRefundException.getLocalizedMessage());
    assertEquals(
        "OutboundJaxrsResponse{status=200, reason=OK, hasEntity=false, closed=false, buffered=false}",
        actualCreateRefundException.getMessage());
    assertNull(actualCreateRefundException.getReason());
    assertNull(actualCreateRefundException.getCause());
    assertEquals(0, actualCreateRefundException.getSuppressed().length);
    assertEquals(200, actualCreateRefundException.getErrorStatus());
    assertEquals(ErrorIdentifier.GENERIC, actualCreateRefundException.getErrorIdentifier());
    assertFalse(actualCreateRefundException.hasReason());
  }
}
