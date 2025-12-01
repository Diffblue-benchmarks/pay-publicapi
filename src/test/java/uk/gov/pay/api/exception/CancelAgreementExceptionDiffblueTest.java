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

class CancelAgreementExceptionDiffblueTest {
  /**
   * Test {@link CancelAgreementException#CancelAgreementException(Response)}.
   *
   * <p>Method under test: {@link CancelAgreementException#CancelAgreementException(Response)}
   */
  @Test
  @DisplayName("Test new CancelAgreementException(Response)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CancelAgreementException.<init>(Response)"})
  void testNewCancelAgreementException() {
    // Arrange and Act
    CancelAgreementException actualCancelAgreementException =
        new CancelAgreementException(
            new OutboundJaxrsResponse(Status.OK, new OutboundMessageContext()));

    // Assert
    assertEquals(
        "OutboundJaxrsResponse{status=200, reason=OK, hasEntity=false, closed=false, buffered=false}",
        actualCancelAgreementException.getLocalizedMessage());
    assertEquals(
        "OutboundJaxrsResponse{status=200, reason=OK, hasEntity=false, closed=false, buffered=false}",
        actualCancelAgreementException.getMessage());
    assertNull(actualCancelAgreementException.getReason());
    assertNull(actualCancelAgreementException.getCause());
    assertEquals(0, actualCancelAgreementException.getSuppressed().length);
    assertEquals(200, actualCancelAgreementException.getErrorStatus());
    assertEquals(ErrorIdentifier.GENERIC, actualCancelAgreementException.getErrorIdentifier());
    assertFalse(actualCancelAgreementException.hasReason());
  }
}
