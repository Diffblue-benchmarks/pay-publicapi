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

public class GetAgreementExceptionDiffblueTest {
  /**
   * Test {@link GetAgreementException#GetAgreementException(Response)}.
   *
   * <p>Method under test: {@link GetAgreementException#GetAgreementException(Response)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void GetAgreementException.<init>(Response)"})
  public void testNewGetAgreementException() {
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
