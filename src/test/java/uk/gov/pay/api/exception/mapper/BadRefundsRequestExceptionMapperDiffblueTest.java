package uk.gov.pay.api.exception.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.Response.StatusType;
import java.util.Set;
import org.glassfish.jersey.message.internal.OutboundJaxrsResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.exception.BadRefundsRequestException;
import uk.gov.pay.api.model.RequestError;
import uk.gov.pay.api.model.RequestError.Code;

class BadRefundsRequestExceptionMapperDiffblueTest {
  /**
   * Test {@link BadRefundsRequestExceptionMapper#toResponse(BadRefundsRequestException)} with
   * {@code exception}.
   *
   * <ul>
   *   <li>Then StatusInfo return {@link Status}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BadRefundsRequestExceptionMapper#toResponse(BadRefundsRequestException)}
   */
  @Test
  @DisplayName(
      "Test toResponse(BadRefundsRequestException) with 'exception'; then StatusInfo return Status")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Response BadRefundsRequestExceptionMapper.toResponse(BadRefundsRequestException)"
  })
  void testToResponseWithException_thenStatusInfoReturnStatus() {
    // Arrange
    BadRefundsRequestExceptionMapper badRefundsRequestExceptionMapper =
        new BadRefundsRequestExceptionMapper();
    RequestError refundError =
        RequestError.aHeaderRequestError("Header", Code.CREATE_PAYMENT_ACCOUNT_ERROR, "Parameters");

    // Act
    Response actualToResponseResult =
        badRefundsRequestExceptionMapper.toResponse(new BadRefundsRequestException(refundError));

    // Assert
    StatusType statusInfo = actualToResponseResult.getStatusInfo();
    assertTrue(statusInfo instanceof Status);
    assertTrue(actualToResponseResult instanceof OutboundJaxrsResponse);
    Object entity = actualToResponseResult.getEntity();
    assertTrue(entity instanceof RequestError);
    assertNull(actualToResponseResult.getEntityTag());
    assertNull(actualToResponseResult.getMediaType());
    assertNull(actualToResponseResult.getLocation());
    assertNull(actualToResponseResult.getDate());
    assertNull(actualToResponseResult.getLastModified());
    assertNull(actualToResponseResult.getLanguage());
    assertEquals(-1, actualToResponseResult.getLength());
    assertEquals(400, actualToResponseResult.getStatus());
    assertEquals(Status.BAD_REQUEST, statusInfo);
    assertTrue(actualToResponseResult.getCookies().isEmpty());
    MultivaluedMap<String, Object> headers = actualToResponseResult.getHeaders();
    assertTrue(headers.isEmpty());
    assertTrue(actualToResponseResult.getStringHeaders().isEmpty());
    Set<String> allowedMethods = actualToResponseResult.getAllowedMethods();
    assertTrue(allowedMethods.isEmpty());
    assertSame(allowedMethods, actualToResponseResult.getLinks());
    assertSame(refundError, entity);
    assertSame(headers, actualToResponseResult.getMetadata());
  }
}
