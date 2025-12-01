package uk.gov.pay.api.exception.mapper;

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
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.Response.StatusType;
import java.util.Set;
import org.glassfish.jersey.message.internal.OutboundJaxrsResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.exception.AuthorisationRequestException;
import uk.gov.pay.api.model.RequestError;
import uk.gov.service.payments.commons.model.ErrorIdentifier;

class AuthorisationRequestExceptionMapperDiffblueTest {
  /**
   * Test {@link AuthorisationRequestExceptionMapper#toResponse(AuthorisationRequestException)} with
   * {@code exception}.
   *
   * <ul>
   *   <li>Given {@code AUTHORISATION_ERROR}.
   *   <li>Then return Entity Code is {@code P0050}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AuthorisationRequestExceptionMapper#toResponse(AuthorisationRequestException)}
   */
  @Test
  @DisplayName(
      "Test toResponse(AuthorisationRequestException) with 'exception'; given 'AUTHORISATION_ERROR'; then return Entity Code is 'P0050'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Response AuthorisationRequestExceptionMapper.toResponse(AuthorisationRequestException)"
  })
  void testToResponseWithException_givenAuthorisationError_thenReturnEntityCodeIsP0050() {
    // Arrange
    AuthorisationRequestExceptionMapper authorisationRequestExceptionMapper =
        new AuthorisationRequestExceptionMapper();

    AuthorisationRequestException exception = mock(AuthorisationRequestException.class);
    when(exception.getErrorIdentifier()).thenReturn(ErrorIdentifier.AUTHORISATION_ERROR);

    // Act
    Response actualToResponseResult = authorisationRequestExceptionMapper.toResponse(exception);

    // Assert
    verify(exception).getErrorIdentifier();
    StatusType statusInfo = actualToResponseResult.getStatusInfo();
    assertTrue(statusInfo instanceof Status);
    assertTrue(actualToResponseResult instanceof OutboundJaxrsResponse);
    Object entity = actualToResponseResult.getEntity();
    assertTrue(entity instanceof RequestError);
    assertEquals("P0050", ((RequestError) entity).getCode());
    assertEquals(
        "There was an error authorising the payment", ((RequestError) entity).getDescription());
    assertEquals(500, actualToResponseResult.getStatus());
    assertEquals(Status.INTERNAL_SERVER_ERROR, statusInfo);
  }

  /**
   * Test {@link AuthorisationRequestExceptionMapper#toResponse(AuthorisationRequestException)} with
   * {@code exception}.
   *
   * <ul>
   *   <li>Given {@code AUTHORISATION_REJECTED}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AuthorisationRequestExceptionMapper#toResponse(AuthorisationRequestException)}
   */
  @Test
  @DisplayName(
      "Test toResponse(AuthorisationRequestException) with 'exception'; given 'AUTHORISATION_REJECTED'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Response AuthorisationRequestExceptionMapper.toResponse(AuthorisationRequestException)"
  })
  void testToResponseWithException_givenAuthorisationRejected() {
    // Arrange
    AuthorisationRequestExceptionMapper authorisationRequestExceptionMapper =
        new AuthorisationRequestExceptionMapper();

    AuthorisationRequestException exception = mock(AuthorisationRequestException.class);
    when(exception.getConnectorErrorMessage()).thenReturn("An error occurred");
    when(exception.getErrorIdentifier()).thenReturn(ErrorIdentifier.AUTHORISATION_REJECTED);

    // Act
    Response actualToResponseResult = authorisationRequestExceptionMapper.toResponse(exception);

    // Assert
    verify(exception).getConnectorErrorMessage();
    verify(exception).getErrorIdentifier();
    StatusType statusInfo = actualToResponseResult.getStatusInfo();
    assertTrue(statusInfo instanceof Status);
    assertTrue(actualToResponseResult instanceof OutboundJaxrsResponse);
    Object entity = actualToResponseResult.getEntity();
    assertTrue(entity instanceof RequestError);
    assertEquals("An error occurred", ((RequestError) entity).getDescription());
    assertEquals("P0010", ((RequestError) entity).getCode());
    assertEquals(402, actualToResponseResult.getStatus());
    assertEquals(Status.PAYMENT_REQUIRED, statusInfo);
  }

  /**
   * Test {@link AuthorisationRequestExceptionMapper#toResponse(AuthorisationRequestException)} with
   * {@code exception}.
   *
   * <ul>
   *   <li>Given {@code CARD_NUMBER_REJECTED}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AuthorisationRequestExceptionMapper#toResponse(AuthorisationRequestException)}
   */
  @Test
  @DisplayName(
      "Test toResponse(AuthorisationRequestException) with 'exception'; given 'CARD_NUMBER_REJECTED'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Response AuthorisationRequestExceptionMapper.toResponse(AuthorisationRequestException)"
  })
  void testToResponseWithException_givenCardNumberRejected() {
    // Arrange
    AuthorisationRequestExceptionMapper authorisationRequestExceptionMapper =
        new AuthorisationRequestExceptionMapper();

    AuthorisationRequestException exception = mock(AuthorisationRequestException.class);
    when(exception.getConnectorErrorMessage()).thenReturn("An error occurred");
    when(exception.getErrorIdentifier()).thenReturn(ErrorIdentifier.CARD_NUMBER_REJECTED);

    // Act
    Response actualToResponseResult = authorisationRequestExceptionMapper.toResponse(exception);

    // Assert
    verify(exception).getConnectorErrorMessage();
    verify(exception).getErrorIdentifier();
    StatusType statusInfo = actualToResponseResult.getStatusInfo();
    assertTrue(statusInfo instanceof Status);
    assertTrue(actualToResponseResult instanceof OutboundJaxrsResponse);
    Object entity = actualToResponseResult.getEntity();
    assertTrue(entity instanceof RequestError);
    assertEquals("An error occurred", ((RequestError) entity).getDescription());
    assertEquals("P0010", ((RequestError) entity).getCode());
    assertEquals(402, actualToResponseResult.getStatus());
    assertEquals(Status.PAYMENT_REQUIRED, statusInfo);
  }

  /**
   * Test {@link AuthorisationRequestExceptionMapper#toResponse(AuthorisationRequestException)} with
   * {@code exception}.
   *
   * <ul>
   *   <li>Given {@code INVALID_ATTRIBUTE_VALUE}.
   *   <li>Then return EntityTag is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AuthorisationRequestExceptionMapper#toResponse(AuthorisationRequestException)}
   */
  @Test
  @DisplayName(
      "Test toResponse(AuthorisationRequestException) with 'exception'; given 'INVALID_ATTRIBUTE_VALUE'; then return EntityTag is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Response AuthorisationRequestExceptionMapper.toResponse(AuthorisationRequestException)"
  })
  void testToResponseWithException_givenInvalidAttributeValue_thenReturnEntityTagIsNull() {
    // Arrange
    AuthorisationRequestExceptionMapper authorisationRequestExceptionMapper =
        new AuthorisationRequestExceptionMapper();

    AuthorisationRequestException exception = mock(AuthorisationRequestException.class);
    when(exception.getConnectorErrorMessage()).thenReturn("An error occurred");
    when(exception.getErrorIdentifier()).thenReturn(ErrorIdentifier.INVALID_ATTRIBUTE_VALUE);

    // Act
    Response actualToResponseResult = authorisationRequestExceptionMapper.toResponse(exception);

    // Assert
    verify(exception).getConnectorErrorMessage();
    verify(exception).getErrorIdentifier();
    assertTrue(actualToResponseResult instanceof OutboundJaxrsResponse);
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
    assertSame(headers, actualToResponseResult.getMetadata());
  }

  /**
   * Test {@link AuthorisationRequestExceptionMapper#toResponse(AuthorisationRequestException)} with
   * {@code exception}.
   *
   * <ul>
   *   <li>Then return Entity Description is {@code Downstream system error}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AuthorisationRequestExceptionMapper#toResponse(AuthorisationRequestException)}
   */
  @Test
  @DisplayName(
      "Test toResponse(AuthorisationRequestException) with 'exception'; then return Entity Description is 'Downstream system error'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Response AuthorisationRequestExceptionMapper.toResponse(AuthorisationRequestException)"
  })
  void testToResponseWithException_thenReturnEntityDescriptionIsDownstreamSystemError() {
    // Arrange
    AuthorisationRequestExceptionMapper authorisationRequestExceptionMapper =
        new AuthorisationRequestExceptionMapper();

    AuthorisationRequestException exception = mock(AuthorisationRequestException.class);
    when(exception.getConnectorErrorMessage()).thenReturn("An error occurred");
    when(exception.getErrorIdentifier()).thenReturn(ErrorIdentifier.ACCOUNT_DISABLED);

    // Act
    Response actualToResponseResult = authorisationRequestExceptionMapper.toResponse(exception);

    // Assert
    verify(exception, atLeast(1)).getConnectorErrorMessage();
    verify(exception).getErrorIdentifier();
    StatusType statusInfo = actualToResponseResult.getStatusInfo();
    assertTrue(statusInfo instanceof Status);
    assertTrue(actualToResponseResult instanceof OutboundJaxrsResponse);
    Object entity = actualToResponseResult.getEntity();
    assertTrue(entity instanceof RequestError);
    assertEquals("Downstream system error", ((RequestError) entity).getDescription());
    assertEquals("P0198", ((RequestError) entity).getCode());
    assertEquals(500, actualToResponseResult.getStatus());
    assertEquals(Status.INTERNAL_SERVER_ERROR, statusInfo);
  }
}
