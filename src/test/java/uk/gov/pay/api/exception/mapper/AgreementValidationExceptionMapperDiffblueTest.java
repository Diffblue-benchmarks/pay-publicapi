package uk.gov.pay.api.exception.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import java.util.Set;
import org.glassfish.jersey.message.internal.OutboundJaxrsResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.exception.AgreementValidationException;
import uk.gov.pay.api.model.RequestError;
import uk.gov.pay.api.model.RequestError.Code;

class AgreementValidationExceptionMapperDiffblueTest {
  /**
   * Test {@link AgreementValidationExceptionMapper#toResponse(AgreementValidationException)} with
   * {@code exception}.
   *
   * <ul>
   *   <li>Then return {@link OutboundJaxrsResponse}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AgreementValidationExceptionMapper#toResponse(AgreementValidationException)}
   */
  @Test
  @DisplayName(
      "Test toResponse(AgreementValidationException) with 'exception'; then return OutboundJaxrsResponse")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Response AgreementValidationExceptionMapper.toResponse(AgreementValidationException)"
  })
  void testToResponseWithException_thenReturnOutboundJaxrsResponse() {
    // Arrange
    AgreementValidationExceptionMapper agreementValidationExceptionMapper =
        new AgreementValidationExceptionMapper();
    RequestError requestError =
        RequestError.aHeaderRequestError("Header", Code.CREATE_PAYMENT_ACCOUNT_ERROR, "Parameters");

    // Act
    Response actualToResponseResult =
        agreementValidationExceptionMapper.toResponse(
            new AgreementValidationException(requestError));

    // Assert
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
    assertEquals(422, actualToResponseResult.getStatus());
    assertTrue(actualToResponseResult.getCookies().isEmpty());
    MultivaluedMap<String, Object> headers = actualToResponseResult.getHeaders();
    assertTrue(headers.isEmpty());
    assertTrue(actualToResponseResult.getStringHeaders().isEmpty());
    Set<String> allowedMethods = actualToResponseResult.getAllowedMethods();
    assertTrue(allowedMethods.isEmpty());
    assertSame(allowedMethods, actualToResponseResult.getLinks());
    assertSame(requestError, entity);
    assertSame(headers, actualToResponseResult.getMetadata());
  }
}
