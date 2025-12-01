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

class GetAgreementExceptionDiffblueTest {
  /**
   * Test {@link GetAgreementException#GetAgreementException(Response)}.
   *
   * <p>Method under test: {@link GetAgreementException#GetAgreementException(Response)}
   */
  @Test
  @DisplayName("Test new GetAgreementException(Response)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void GetAgreementException.<init>(Response)"})
  void testNewGetAgreementException() {
    // Arrange and Act
    GetAgreementException actualGetAgreementException =
        new GetAgreementException(
            new OutboundJaxrsResponse(Status.OK, new OutboundMessageContext()));

    // Assert
    assertEquals(
        "OutboundJaxrsResponse{status=200, reason=OK, hasEntity=false, closed=false, buffered=false}",
        actualGetAgreementException.getLocalizedMessage());
    assertEquals(
        "OutboundJaxrsResponse{status=200, reason=OK, hasEntity=false, closed=false, buffered=false}",
        actualGetAgreementException.getMessage());
    assertNull(actualGetAgreementException.getReason());
    assertNull(actualGetAgreementException.getCause());
    assertEquals(0, actualGetAgreementException.getSuppressed().length);
    assertEquals(200, actualGetAgreementException.getErrorStatus());
    assertEquals(ErrorIdentifier.GENERIC, actualGetAgreementException.getErrorIdentifier());
    assertFalse(actualGetAgreementException.hasReason());
  }
}
