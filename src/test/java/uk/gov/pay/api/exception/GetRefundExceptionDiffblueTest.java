package uk.gov.pay.api.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
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

public class GetRefundExceptionDiffblueTest {
  /**
   * Test {@link GetRefundException#GetRefundException(Response)}.
   *
   * <p>Method under test: {@link GetRefundException#GetRefundException(Response)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void GetRefundException.<init>(Response)"})
  public void testNewGetRefundException() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void GetRefundException.<init>(GetTransactionException)"})
  public void testNewGetRefundException_thenCauseReturnGetTransactionException() {
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
