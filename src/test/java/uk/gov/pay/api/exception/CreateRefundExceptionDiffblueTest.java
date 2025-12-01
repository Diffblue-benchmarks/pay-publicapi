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

class CreateRefundExceptionDiffblueTest {
  /**
   * Test {@link CreateRefundException#CreateRefundException(Response)}.
   *
   * <p>Method under test: {@link CreateRefundException#CreateRefundException(Response)}
   */
  @Test
  @DisplayName("Test new CreateRefundException(Response)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CreateRefundException.<init>(Response)"})
  void testNewCreateRefundException() {
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
