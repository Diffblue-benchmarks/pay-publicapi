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

class CreateAgreementExceptionDiffblueTest {
  /**
   * Test {@link CreateAgreementException#CreateAgreementException(Response)}.
   *
   * <p>Method under test: {@link CreateAgreementException#CreateAgreementException(Response)}
   */
  @Test
  @DisplayName("Test new CreateAgreementException(Response)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CreateAgreementException.<init>(Response)"})
  void testNewCreateAgreementException() {
    // Arrange and Act
    CreateAgreementException actualCreateAgreementException =
        new CreateAgreementException(
            new OutboundJaxrsResponse(Status.OK, new OutboundMessageContext()));

    // Assert
    assertEquals(
        "OutboundJaxrsResponse{status=200, reason=OK, hasEntity=false, closed=false, buffered=false}",
        actualCreateAgreementException.getLocalizedMessage());
    assertEquals(
        "OutboundJaxrsResponse{status=200, reason=OK, hasEntity=false, closed=false, buffered=false}",
        actualCreateAgreementException.getMessage());
    assertNull(actualCreateAgreementException.getReason());
    assertNull(actualCreateAgreementException.getCause());
    assertEquals(0, actualCreateAgreementException.getSuppressed().length);
    assertEquals(200, actualCreateAgreementException.getErrorStatus());
    assertEquals(ErrorIdentifier.GENERIC, actualCreateAgreementException.getErrorIdentifier());
    assertFalse(actualCreateAgreementException.hasReason());
  }
}
