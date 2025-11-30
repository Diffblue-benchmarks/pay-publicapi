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

public class CreateAgreementExceptionDiffblueTest {
  /**
   * Test {@link CreateAgreementException#CreateAgreementException(Response)}.
   *
   * <p>Method under test: {@link CreateAgreementException#CreateAgreementException(Response)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CreateAgreementException.<init>(Response)"})
  public void testNewCreateAgreementException() {
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
