package uk.gov.pay.api.exception.mapper;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import io.dropwizard.jersey.errors.ErrorMessage;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.Response.StatusType;
import java.util.Set;
import org.glassfish.jersey.message.internal.OutboundJaxrsResponse;
import org.glassfish.jersey.message.internal.OutboundMessageContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.exception.AgreementValidationException;
import uk.gov.pay.api.exception.PaymentValidationException;
import uk.gov.pay.api.model.RequestError;
import uk.gov.pay.api.model.RequestError.Code;

class JsonProcessingExceptionMapperDiffblueTest {
  /**
   * Test {@link JsonProcessingExceptionMapper#toResponse(JsonProcessingException)} with {@code
   * exception}.
   *
   * <p>Method under test: {@link JsonProcessingExceptionMapper#toResponse(JsonProcessingException)}
   */
  @Test
  @DisplayName("Test toResponse(JsonProcessingException) with 'exception'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Response JsonProcessingExceptionMapper.toResponse(JsonProcessingException)"})
  void testToResponseWithException() {
    // Arrange
    JsonProcessingExceptionMapper jsonProcessingExceptionMapper =
        new JsonProcessingExceptionMapper();

    JsonMappingException exception = new JsonMappingException("Msg");
    RequestError requestError =
        RequestError.aHeaderRequestError("Header", Code.CREATE_PAYMENT_ACCOUNT_ERROR, "Parameters");
    exception.initCause(new PaymentValidationException(requestError));

    // Act
    Response actualToResponseResult = jsonProcessingExceptionMapper.toResponse(exception);

    // Assert
    assertTrue(actualToResponseResult instanceof OutboundJaxrsResponse);
    assertNull(actualToResponseResult.getMediaType());
    OutboundMessageContext context = ((OutboundJaxrsResponse) actualToResponseResult).getContext();
    assertNull(context.getMediaType());
    assertEquals(422, actualToResponseResult.getStatus());
    assertTrue(actualToResponseResult.getHeaders().isEmpty());
    assertTrue(actualToResponseResult.getStringHeaders().isEmpty());
    assertTrue(context.getStringHeaders().isEmpty());
    Class<RequestError> expectedEntityClass = RequestError.class;
    assertEquals(expectedEntityClass, context.getEntityClass());
    assertSame(requestError, actualToResponseResult.getEntity());
    assertSame(requestError, context.getEntity());
  }

  /**
   * Test {@link JsonProcessingExceptionMapper#toResponse(JsonProcessingException)} with {@code
   * exception}.
   *
   * <p>Method under test: {@link JsonProcessingExceptionMapper#toResponse(JsonProcessingException)}
   */
  @Test
  @DisplayName("Test toResponse(JsonProcessingException) with 'exception'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Response JsonProcessingExceptionMapper.toResponse(JsonProcessingException)"})
  void testToResponseWithException2() {
    // Arrange
    JsonProcessingExceptionMapper jsonProcessingExceptionMapper =
        new JsonProcessingExceptionMapper();

    JsonMappingException exception = new JsonMappingException("Msg");
    RequestError requestError =
        RequestError.aHeaderRequestError("Header", Code.CREATE_PAYMENT_ACCOUNT_ERROR, "Parameters");
    exception.initCause(new AgreementValidationException(requestError));

    // Act
    Response actualToResponseResult = jsonProcessingExceptionMapper.toResponse(exception);

    // Assert
    assertTrue(actualToResponseResult instanceof OutboundJaxrsResponse);
    assertNull(actualToResponseResult.getMediaType());
    OutboundMessageContext context = ((OutboundJaxrsResponse) actualToResponseResult).getContext();
    assertNull(context.getMediaType());
    assertEquals(422, actualToResponseResult.getStatus());
    assertTrue(actualToResponseResult.getHeaders().isEmpty());
    assertTrue(actualToResponseResult.getStringHeaders().isEmpty());
    assertTrue(context.getStringHeaders().isEmpty());
    Class<RequestError> expectedEntityClass = RequestError.class;
    assertEquals(expectedEntityClass, context.getEntityClass());
    assertSame(requestError, actualToResponseResult.getEntity());
    assertSame(requestError, context.getEntity());
  }

  /**
   * Test {@link JsonProcessingExceptionMapper#toResponse(JsonProcessingException)} with {@code
   * exception}.
   *
   * <ul>
   *   <li>Given {@link Throwable#Throwable()}.
   *   <li>Then Entity return {@link ErrorMessage}.
   * </ul>
   *
   * <p>Method under test: {@link JsonProcessingExceptionMapper#toResponse(JsonProcessingException)}
   */
  @Test
  @DisplayName(
      "Test toResponse(JsonProcessingException) with 'exception'; given Throwable(); then Entity return ErrorMessage")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Response JsonProcessingExceptionMapper.toResponse(JsonProcessingException)"})
  void testToResponseWithException_givenThrowable_thenEntityReturnErrorMessage() {
    // Arrange
    JsonProcessingExceptionMapper jsonProcessingExceptionMapper =
        new JsonProcessingExceptionMapper();

    JsonMappingException exception = new JsonMappingException("Msg");
    exception.initCause(new Throwable());

    // Act
    Response actualToResponseResult = jsonProcessingExceptionMapper.toResponse(exception);

    // Assert
    assertTrue(actualToResponseResult.getEntity() instanceof ErrorMessage);
    StatusType statusInfo = actualToResponseResult.getStatusInfo();
    assertTrue(statusInfo instanceof Status);
    assertTrue(actualToResponseResult instanceof OutboundJaxrsResponse);
    MultivaluedMap<String, Object> headers = actualToResponseResult.getHeaders();
    assertEquals(1, headers.size());
    MultivaluedMap<String, String> stringHeaders = actualToResponseResult.getStringHeaders();
    assertEquals(1, stringHeaders.size());
    assertEquals(500, actualToResponseResult.getStatus());
    assertEquals(Status.INTERNAL_SERVER_ERROR, statusInfo);
    assertTrue(headers.containsKey("Content-Type"));
    assertTrue(stringHeaders.containsKey("Content-Type"));
  }

  /**
   * Test new {@link JsonProcessingExceptionMapper} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link
   * JsonProcessingExceptionMapper}
   */
  @Test
  @DisplayName("Test new JsonProcessingExceptionMapper (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonProcessingExceptionMapper.<init>()"})
  void testNewJsonProcessingExceptionMapper() {
    // Arrange and Act
    JsonProcessingExceptionMapper actualJsonProcessingExceptionMapper =
        new JsonProcessingExceptionMapper();
    JsonProcessingException exception = mock(JsonProcessingException.class);
    when(exception.getCause()).thenReturn(new Throwable());
    Response actualToResponseResult = actualJsonProcessingExceptionMapper.toResponse(exception);

    // Assert
    verify(exception, atLeast(1)).getCause();
    assertTrue(actualToResponseResult.getEntity() instanceof ErrorMessage);
    StatusType statusInfo = actualToResponseResult.getStatusInfo();
    assertTrue(statusInfo instanceof Status);
    assertTrue(actualToResponseResult instanceof OutboundJaxrsResponse);
    assertNull(actualToResponseResult.getEntityTag());
    assertNull(actualToResponseResult.getLocation());
    assertNull(actualToResponseResult.getDate());
    assertNull(actualToResponseResult.getLastModified());
    assertNull(actualToResponseResult.getLanguage());
    assertEquals(-1, actualToResponseResult.getLength());
    MultivaluedMap<String, Object> headers = actualToResponseResult.getHeaders();
    assertEquals(1, headers.size());
    MultivaluedMap<String, String> stringHeaders = actualToResponseResult.getStringHeaders();
    assertEquals(1, stringHeaders.size());
    assertEquals(500, actualToResponseResult.getStatus());
    assertEquals(Status.INTERNAL_SERVER_ERROR, statusInfo);
    assertTrue(headers.containsKey("Content-Type"));
    assertTrue(stringHeaders.containsKey("Content-Type"));
    assertTrue(actualToResponseResult.getCookies().isEmpty());
    Set<String> allowedMethods = actualToResponseResult.getAllowedMethods();
    assertTrue(allowedMethods.isEmpty());
    assertSame(allowedMethods, actualToResponseResult.getLinks());
    assertSame(headers, actualToResponseResult.getMetadata());
  }

  /**
   * Test new {@link JsonProcessingExceptionMapper} (default constructor).
   *
   * <ul>
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: default or parameterless constructor of {@link
   * JsonProcessingExceptionMapper}
   */
  @Test
  @DisplayName("Test new JsonProcessingExceptionMapper (default constructor); then does not throw")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonProcessingExceptionMapper.<init>()"})
  void testNewJsonProcessingExceptionMapper_thenDoesNotThrow() {
    // Arrange, Act and Assert
    assertDoesNotThrow(() -> new JsonProcessingExceptionMapper());
  }
}
