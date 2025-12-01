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

class GetTransactionExceptionDiffblueTest {
  /**
   * Test {@link GetTransactionException#GetTransactionException(Response)}.
   *
   * <p>Method under test: {@link GetTransactionException#GetTransactionException(Response)}
   */
  @Test
  @DisplayName("Test new GetTransactionException(Response)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void GetTransactionException.<init>(Response)"})
  void testNewGetTransactionException() {
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
