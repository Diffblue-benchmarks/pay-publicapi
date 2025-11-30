package uk.gov.pay.api.exception.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
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
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.pay.api.exception.AgreementValidationException;
import uk.gov.pay.api.exception.PaymentValidationException;
import uk.gov.pay.api.model.RequestError;
import uk.gov.pay.api.model.RequestError.Code;

public class JsonProcessingExceptionMapperDiffblueTest {
  /**
   * Test {@link JsonProcessingExceptionMapper#toResponse(JsonProcessingException)} with {@code
   * exception}.
   *
   * <p>Method under test: {@link JsonProcessingExceptionMapper#toResponse(JsonProcessingException)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Response JsonProcessingExceptionMapper.toResponse(JsonProcessingException)"})
  public void testToResponseWithException() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Response JsonProcessingExceptionMapper.toResponse(JsonProcessingException)"})
  public void testToResponseWithException2() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Response JsonProcessingExceptionMapper.toResponse(JsonProcessingException)"})
  public void testToResponseWithException_givenThrowable_thenEntityReturnErrorMessage() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonProcessingExceptionMapper.<init>()"})
  public void testNewJsonProcessingExceptionMapper() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JsonProcessingExceptionMapper.<init>()"})
  public void testNewJsonProcessingExceptionMapper_thenDoesNotThrow() {
    // Arrange, Act and Assert
    new JsonProcessingExceptionMapper();
  }
}
