package uk.gov.pay.api.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import java.util.ArrayList;
import java.util.List;
import org.glassfish.jersey.message.internal.OutboundJaxrsResponse;
import org.glassfish.jersey.message.internal.OutboundMessageContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.exception.ConnectorResponseErrorException.ConnectorErrorResponse;
import uk.gov.service.payments.commons.model.ErrorIdentifier;

class ConnectorResponseErrorExceptionDiffblueTest {
  /**
   * Test ConnectorErrorResponse getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ConnectorErrorResponse#ConnectorErrorResponse()}
   *   <li>{@link ConnectorErrorResponse#toString()}
   *   <li>{@link ConnectorErrorResponse#getErrorIdentifier()}
   *   <li>{@link ConnectorErrorResponse#getMessage()}
   *   <li>{@link ConnectorErrorResponse#getReason()}
   * </ul>
   */
  @Test
  @DisplayName("Test ConnectorErrorResponse getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ConnectorErrorResponse.<init>()",
    "void ConnectorErrorResponse.<init>(ErrorIdentifier, String, List)",
    "void ConnectorErrorResponse.<init>(ErrorIdentifier, List)",
    "ErrorIdentifier ConnectorErrorResponse.getErrorIdentifier()",
    "List ConnectorErrorResponse.getMessage()",
    "String ConnectorErrorResponse.getReason()",
    "String ConnectorErrorResponse.toString()"
  })
  void testConnectorErrorResponseGettersAndSetters() {
    // Arrange and Act
    ConnectorErrorResponse actualConnectorErrorResponse = new ConnectorErrorResponse();
    String actualToStringResult = actualConnectorErrorResponse.toString();
    ErrorIdentifier actualErrorIdentifier = actualConnectorErrorResponse.getErrorIdentifier();
    List<String> actualMessage = actualConnectorErrorResponse.getMessage();

    // Assert
    assertEquals(
        "ConnectorErrorResponse{error_identifier='null', reason='null', message='null'}",
        actualToStringResult);
    assertNull(actualConnectorErrorResponse.getReason());
    assertNull(actualMessage);
    assertNull(actualErrorIdentifier);
  }

  /**
   * Test ConnectorErrorResponse getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ConnectorErrorResponse#ConnectorErrorResponse(ErrorIdentifier, String, List)}
   *   <li>{@link ConnectorErrorResponse#toString()}
   *   <li>{@link ConnectorErrorResponse#getErrorIdentifier()}
   *   <li>{@link ConnectorErrorResponse#getMessage()}
   *   <li>{@link ConnectorErrorResponse#getReason()}
   * </ul>
   */
  @Test
  @DisplayName("Test ConnectorErrorResponse getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ConnectorErrorResponse.<init>()",
    "void ConnectorErrorResponse.<init>(ErrorIdentifier, String, List)",
    "void ConnectorErrorResponse.<init>(ErrorIdentifier, List)",
    "ErrorIdentifier ConnectorErrorResponse.getErrorIdentifier()",
    "List ConnectorErrorResponse.getMessage()",
    "String ConnectorErrorResponse.getReason()",
    "String ConnectorErrorResponse.toString()"
  })
  void testConnectorErrorResponseGettersAndSetters2() {
    // Arrange
    ArrayList<String> message = new ArrayList<>();

    // Act
    ConnectorErrorResponse actualConnectorErrorResponse =
        new ConnectorErrorResponse(ErrorIdentifier.ACCOUNT_DISABLED, "Just cause", message);
    String actualToStringResult = actualConnectorErrorResponse.toString();
    ErrorIdentifier actualErrorIdentifier = actualConnectorErrorResponse.getErrorIdentifier();
    List<String> actualMessage = actualConnectorErrorResponse.getMessage();

    // Assert
    assertEquals(
        "ConnectorErrorResponse{error_identifier='ACCOUNT_DISABLED', reason='Just cause', message='[]'}",
        actualToStringResult);
    assertEquals("Just cause", actualConnectorErrorResponse.getReason());
    assertEquals(ErrorIdentifier.ACCOUNT_DISABLED, actualErrorIdentifier);
    assertTrue(actualMessage.isEmpty());
    assertSame(message, actualMessage);
  }

  /**
   * Test ConnectorErrorResponse getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ConnectorErrorResponse#ConnectorErrorResponse(ErrorIdentifier, List)}
   *   <li>{@link ConnectorErrorResponse#toString()}
   *   <li>{@link ConnectorErrorResponse#getErrorIdentifier()}
   *   <li>{@link ConnectorErrorResponse#getMessage()}
   *   <li>{@link ConnectorErrorResponse#getReason()}
   * </ul>
   */
  @Test
  @DisplayName("Test ConnectorErrorResponse getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ConnectorErrorResponse.<init>()",
    "void ConnectorErrorResponse.<init>(ErrorIdentifier, String, List)",
    "void ConnectorErrorResponse.<init>(ErrorIdentifier, List)",
    "ErrorIdentifier ConnectorErrorResponse.getErrorIdentifier()",
    "List ConnectorErrorResponse.getMessage()",
    "String ConnectorErrorResponse.getReason()",
    "String ConnectorErrorResponse.toString()"
  })
  void testConnectorErrorResponseGettersAndSetters3() {
    // Arrange
    ArrayList<String> message = new ArrayList<>();

    // Act
    ConnectorErrorResponse actualConnectorErrorResponse =
        new ConnectorErrorResponse(ErrorIdentifier.ACCOUNT_DISABLED, message);
    String actualToStringResult = actualConnectorErrorResponse.toString();
    ErrorIdentifier actualErrorIdentifier = actualConnectorErrorResponse.getErrorIdentifier();
    List<String> actualMessage = actualConnectorErrorResponse.getMessage();

    // Assert
    assertEquals(
        "ConnectorErrorResponse{error_identifier='ACCOUNT_DISABLED', reason='null', message='[]'}",
        actualToStringResult);
    assertNull(actualConnectorErrorResponse.getReason());
    assertEquals(ErrorIdentifier.ACCOUNT_DISABLED, actualErrorIdentifier);
    assertTrue(actualMessage.isEmpty());
    assertSame(message, actualMessage);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ConnectorResponseErrorException#ConnectorResponseErrorException(Throwable)}
   *   <li>{@link ConnectorResponseErrorException#getErrorStatus()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ConnectorResponseErrorException.<init>(Throwable)",
    "int ConnectorResponseErrorException.getErrorStatus()"
  })
  void testGettersAndSetters() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    ConnectorResponseErrorException actualConnectorResponseErrorException =
        new ConnectorResponseErrorException(cause);

    // Assert
    assertEquals(0, actualConnectorResponseErrorException.getErrorStatus());
    assertEquals(0, actualConnectorResponseErrorException.getSuppressed().length);
    assertSame(cause, actualConnectorResponseErrorException.getCause());
  }

  /**
   * Test {@link ConnectorResponseErrorException#ConnectorResponseErrorException(Response)}.
   *
   * <p>Method under test: {@link
   * ConnectorResponseErrorException#ConnectorResponseErrorException(Response)}
   */
  @Test
  @DisplayName("Test new ConnectorResponseErrorException(Response)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ConnectorResponseErrorException.<init>(Response)"})
  void testNewConnectorResponseErrorException() {
    // Arrange and Act
    ConnectorResponseErrorException actualConnectorResponseErrorException =
        new ConnectorResponseErrorException(
            new OutboundJaxrsResponse(Status.OK, new OutboundMessageContext()));

    // Assert
    assertEquals(
        "OutboundJaxrsResponse{status=200, reason=OK, hasEntity=false, closed=false, buffered=false}",
        actualConnectorResponseErrorException.getLocalizedMessage());
    assertEquals(
        "OutboundJaxrsResponse{status=200, reason=OK, hasEntity=false, closed=false, buffered=false}",
        actualConnectorResponseErrorException.getMessage());
    assertNull(actualConnectorResponseErrorException.getReason());
    assertNull(actualConnectorResponseErrorException.getCause());
    assertEquals(0, actualConnectorResponseErrorException.getSuppressed().length);
    assertEquals(200, actualConnectorResponseErrorException.getErrorStatus());
    assertEquals(
        ErrorIdentifier.GENERIC, actualConnectorResponseErrorException.getErrorIdentifier());
    assertFalse(actualConnectorResponseErrorException.hasReason());
  }

  /**
   * Test {@link
   * ConnectorResponseErrorException#ConnectorResponseErrorException(ConnectorResponseErrorException)}.
   *
   * <p>Method under test: {@link
   * ConnectorResponseErrorException#ConnectorResponseErrorException(ConnectorResponseErrorException)}
   */
  @Test
  @DisplayName("Test new ConnectorResponseErrorException(ConnectorResponseErrorException)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ConnectorResponseErrorException.<init>(ConnectorResponseErrorException)"
  })
  void testNewConnectorResponseErrorException2() {
    // Arrange
    ConnectorResponseErrorException exception =
        new ConnectorResponseErrorException(
            new OutboundJaxrsResponse(Status.OK, new OutboundMessageContext()));

    // Act
    ConnectorResponseErrorException actualConnectorResponseErrorException =
        new ConnectorResponseErrorException(exception);

    // Assert
    Throwable cause = actualConnectorResponseErrorException.getCause();
    assertTrue(cause instanceof ConnectorResponseErrorException);
    assertEquals(
        "uk.gov.pay.api.exception.ConnectorResponseErrorException: OutboundJaxrsResponse{status=200, reason=OK,"
            + " hasEntity=false, closed=false, buffered=false}",
        actualConnectorResponseErrorException.getLocalizedMessage());
    assertEquals(
        "uk.gov.pay.api.exception.ConnectorResponseErrorException: OutboundJaxrsResponse{status=200, reason=OK,"
            + " hasEntity=false, closed=false, buffered=false}",
        actualConnectorResponseErrorException.getMessage());
    assertNull(actualConnectorResponseErrorException.getReason());
    assertEquals(0, actualConnectorResponseErrorException.getSuppressed().length);
    assertEquals(200, actualConnectorResponseErrorException.getErrorStatus());
    assertEquals(
        ErrorIdentifier.GENERIC, actualConnectorResponseErrorException.getErrorIdentifier());
    assertFalse(actualConnectorResponseErrorException.hasReason());
    assertSame(exception, cause);
  }

  /**
   * Test {@link ConnectorResponseErrorException#getErrorIdentifier()}.
   *
   * <ul>
   *   <li>Then return {@code GENERIC}.
   * </ul>
   *
   * <p>Method under test: {@link ConnectorResponseErrorException#getErrorIdentifier()}
   */
  @Test
  @DisplayName("Test getErrorIdentifier(); then return 'GENERIC'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ErrorIdentifier ConnectorResponseErrorException.getErrorIdentifier()"})
  void testGetErrorIdentifier_thenReturnGeneric() {
    // Arrange, Act and Assert
    assertEquals(
        ErrorIdentifier.GENERIC,
        new ConnectorResponseErrorException(
                new OutboundJaxrsResponse(Status.OK, new OutboundMessageContext()))
            .getErrorIdentifier());
  }

  /**
   * Test {@link ConnectorResponseErrorException#getReason()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ConnectorResponseErrorException#getReason()}
   */
  @Test
  @DisplayName("Test getReason(); then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String ConnectorResponseErrorException.getReason()"})
  void testGetReason_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(
        new ConnectorResponseErrorException(
                new OutboundJaxrsResponse(Status.OK, new OutboundMessageContext()))
            .getReason());
  }

  /**
   * Test {@link ConnectorResponseErrorException#hasReason()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ConnectorResponseErrorException#hasReason()}
   */
  @Test
  @DisplayName("Test hasReason(); then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ConnectorResponseErrorException.hasReason()"})
  void testHasReason_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(
        new ConnectorResponseErrorException(
                new OutboundJaxrsResponse(Status.OK, new OutboundMessageContext()))
            .hasReason());
  }

  /**
   * Test {@link ConnectorResponseErrorException#getMessage()}.
   *
   * <p>Method under test: {@link ConnectorResponseErrorException#getMessage()}
   */
  @Test
  @DisplayName("Test getMessage()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String ConnectorResponseErrorException.getMessage()"})
  void testGetMessage() {
    // Arrange, Act and Assert
    assertEquals(
        "OutboundJaxrsResponse{status=200, reason=OK, hasEntity=false, closed=false, buffered=false}",
        new ConnectorResponseErrorException(
                new OutboundJaxrsResponse(Status.OK, new OutboundMessageContext()))
            .getMessage());
  }

  /**
   * Test {@link ConnectorResponseErrorException#getConnectorErrorMessage()}.
   *
   * <ul>
   *   <li>Then return {@code Error deserializing connector error message}.
   * </ul>
   *
   * <p>Method under test: {@link ConnectorResponseErrorException#getConnectorErrorMessage()}
   */
  @Test
  @DisplayName(
      "Test getConnectorErrorMessage(); then return 'Error deserializing connector error message'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String ConnectorResponseErrorException.getConnectorErrorMessage()"})
  void testGetConnectorErrorMessage_thenReturnErrorDeserializingConnectorErrorMessage()
      throws ProcessingException {
    // Arrange
    ArrayList<String> message = new ArrayList<>();
    message.add("Error deserializing connector error message");
    ConnectorErrorResponse connectorErrorResponse =
        new ConnectorErrorResponse(ErrorIdentifier.ACCOUNT_DISABLED, message);

    OutboundJaxrsResponse response = mock(OutboundJaxrsResponse.class);
    when(response.getStatus()).thenReturn(1);
    when(response.readEntity(ConnectorErrorResponse.class)).thenReturn(connectorErrorResponse);
    doNothing().when(response).close();

    // Act
    String actualConnectorErrorMessage =
        new ConnectorResponseErrorException(response).getConnectorErrorMessage();

    // Assert
    verify(response).close();
    verify(response).getStatus();
    verify(response).readEntity(isA(Class.class));
    assertEquals("Error deserializing connector error message", actualConnectorErrorMessage);
  }

  /**
   * Test {@link ConnectorResponseErrorException#getConnectorErrorMessage()}.
   *
   * <ul>
   *   <li>Then throw {@link InternalServerException}.
   * </ul>
   *
   * <p>Method under test: {@link ConnectorResponseErrorException#getConnectorErrorMessage()}
   */
  @Test
  @DisplayName("Test getConnectorErrorMessage(); then throw InternalServerException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String ConnectorResponseErrorException.getConnectorErrorMessage()"})
  void testGetConnectorErrorMessage_thenThrowInternalServerException() throws ProcessingException {
    // Arrange
    OutboundJaxrsResponse response = mock(OutboundJaxrsResponse.class);
    when(response.getStatus()).thenReturn(1);
    when(response.readEntity(ConnectorErrorResponse.class))
        .thenReturn(
            new ConnectorErrorResponse(ErrorIdentifier.ACCOUNT_DISABLED, new ArrayList<>()));
    doNothing().when(response).close();

    // Act and Assert
    assertThrows(
        InternalServerException.class,
        () -> new ConnectorResponseErrorException(response).getConnectorErrorMessage());
    verify(response).close();
    verify(response).getStatus();
    verify(response).readEntity(isA(Class.class));
  }
}
