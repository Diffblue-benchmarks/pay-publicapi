package uk.gov.pay.api.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

class GetRefundExceptionDiffblueTest {
  /**
   * Test {@link GetRefundException#GetRefundException(Response)}.
   *
   * <p>Method under test: {@link GetRefundException#GetRefundException(Response)}
   */
  @Test
  @DisplayName("Test new GetRefundException(Response)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void GetRefundException.<init>(Response)"})
  void testNewGetRefundException() {
    // Arrange and Act
    GetRefundException actualGetRefundException =
        new GetRefundException(new OutboundJaxrsResponse(Status.OK, new OutboundMessageContext()));

    // Assert
    assertEquals(
        "OutboundJaxrsResponse{status=200, reason=OK, hasEntity=false, closed=false, buffered=false}",
        actualGetRefundException.getLocalizedMessage());
    assertEquals(
        "OutboundJaxrsResponse{status=200, reason=OK, hasEntity=false, closed=false, buffered=false}",
        actualGetRefundException.getMessage());
    assertNull(actualGetRefundException.getReason());
    assertNull(actualGetRefundException.getCause());
    assertEquals(0, actualGetRefundException.getSuppressed().length);
    assertEquals(200, actualGetRefundException.getErrorStatus());
    assertEquals(ErrorIdentifier.GENERIC, actualGetRefundException.getErrorIdentifier());
    assertFalse(actualGetRefundException.hasReason());
  }

  /**
   * Test {@link GetRefundException#GetRefundException(GetTransactionException)}.
   *
   * <ul>
   *   <li>Then Cause return {@link GetTransactionException}.
   * </ul>
   *
   * <p>Method under test: {@link GetRefundException#GetRefundException(GetTransactionException)}
   */
  @Test
  @DisplayName(
      "Test new GetRefundException(GetTransactionException); then Cause return GetTransactionException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void GetRefundException.<init>(GetTransactionException)"})
  void testNewGetRefundException_thenCauseReturnGetTransactionException() {
    // Arrange
    GetTransactionException exception =
        new GetTransactionException(
            new OutboundJaxrsResponse(Status.OK, new OutboundMessageContext()));

    // Act
    GetRefundException actualGetRefundException = new GetRefundException(exception);

    // Assert
    Throwable cause = actualGetRefundException.getCause();
    assertTrue(cause instanceof GetTransactionException);
    assertEquals(
        "uk.gov.pay.api.exception.GetTransactionException: OutboundJaxrsResponse{status=200, reason=OK,"
            + " hasEntity=false, closed=false, buffered=false}",
        actualGetRefundException.getLocalizedMessage());
    assertEquals(
        "uk.gov.pay.api.exception.GetTransactionException: OutboundJaxrsResponse{status=200, reason=OK,"
            + " hasEntity=false, closed=false, buffered=false}",
        actualGetRefundException.getMessage());
    assertNull(actualGetRefundException.getReason());
    assertEquals(0, actualGetRefundException.getSuppressed().length);
    assertEquals(200, actualGetRefundException.getErrorStatus());
    assertEquals(ErrorIdentifier.GENERIC, actualGetRefundException.getErrorIdentifier());
    assertFalse(actualGetRefundException.hasReason());
    assertSame(exception, cause);
  }
}
