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
import uk.gov.pay.api.exception.PaymentValidationException;
import uk.gov.pay.api.model.RequestError;
import uk.gov.pay.api.model.RequestError.Code;

class PaymentValidationExceptionMapperDiffblueTest {
  /**
   * Test {@link PaymentValidationExceptionMapper#toResponse(PaymentValidationException)} with
   * {@code exception}.
   *
   * <ul>
   *   <li>Then return {@link OutboundJaxrsResponse}.
   * </ul>
   *
   * <p>Method under test: {@link
   * PaymentValidationExceptionMapper#toResponse(PaymentValidationException)}
   */
  @Test
  @DisplayName(
      "Test toResponse(PaymentValidationException) with 'exception'; then return OutboundJaxrsResponse")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Response PaymentValidationExceptionMapper.toResponse(PaymentValidationException)"
  })
  void testToResponseWithException_thenReturnOutboundJaxrsResponse() {
    // Arrange
    PaymentValidationExceptionMapper paymentValidationExceptionMapper =
        new PaymentValidationExceptionMapper();
    RequestError requestError =
        RequestError.aHeaderRequestError("Header", Code.CREATE_PAYMENT_ACCOUNT_ERROR, "Parameters");

    // Act
    Response actualToResponseResult =
        paymentValidationExceptionMapper.toResponse(new PaymentValidationException(requestError));

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
