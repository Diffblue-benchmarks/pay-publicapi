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

public class GetTransactionExceptionDiffblueTest {
  /**
   * Test {@link GetTransactionException#GetTransactionException(Response)}.
   *
   * <p>Method under test: {@link GetTransactionException#GetTransactionException(Response)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void GetTransactionException.<init>(Response)"})
  public void testNewGetTransactionException() {
    // Arrange and Act
    GetTransactionException actualGetTransactionException =
        new GetTransactionException(
            new OutboundJaxrsResponse(Status.OK, new OutboundMessageContext()));

    // Assert
    assertEquals(
        "OutboundJaxrsResponse{status=200, reason=OK, hasEntity=false, closed=false, buffered=false}",
        actualGetTransactionException.getLocalizedMessage());
    assertEquals(
        "OutboundJaxrsResponse{status=200, reason=OK, hasEntity=false, closed=false, buffered=false}",
        actualGetTransactionException.getMessage());
    assertNull(actualGetTransactionException.getReason());
    assertNull(actualGetTransactionException.getCause());
    assertEquals(0, actualGetTransactionException.getSuppressed().length);
    assertEquals(200, actualGetTransactionException.getErrorStatus());
    assertEquals(ErrorIdentifier.GENERIC, actualGetTransactionException.getErrorIdentifier());
    assertFalse(actualGetTransactionException.hasReason());
  }
}
