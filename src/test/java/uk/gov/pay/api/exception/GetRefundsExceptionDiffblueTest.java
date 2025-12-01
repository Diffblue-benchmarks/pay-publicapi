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

class GetRefundsExceptionDiffblueTest {
  /**
   * Test {@link GetRefundsException#GetRefundsException(Response)}.
   *
   * <p>Method under test: {@link GetRefundsException#GetRefundsException(Response)}
   */
  @Test
  @DisplayName("Test new GetRefundsException(Response)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void GetRefundsException.<init>(Response)"})
  void testNewGetRefundsException() {
    // Arrange and Act
    GetRefundsException actualGetRefundsException =
        new GetRefundsException(new OutboundJaxrsResponse(Status.OK, new OutboundMessageContext()));

    // Assert
    assertEquals(
        "OutboundJaxrsResponse{status=200, reason=OK, hasEntity=false, closed=false, buffered=false}",
        actualGetRefundsException.getLocalizedMessage());
    assertEquals(
        "OutboundJaxrsResponse{status=200, reason=OK, hasEntity=false, closed=false, buffered=false}",
        actualGetRefundsException.getMessage());
    assertNull(actualGetRefundsException.getReason());
    assertNull(actualGetRefundsException.getCause());
    assertEquals(0, actualGetRefundsException.getSuppressed().length);
    assertEquals(200, actualGetRefundsException.getErrorStatus());
    assertEquals(ErrorIdentifier.GENERIC, actualGetRefundsException.getErrorIdentifier());
    assertFalse(actualGetRefundsException.hasReason());
  }
}
