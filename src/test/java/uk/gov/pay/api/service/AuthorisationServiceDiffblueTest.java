package uk.gov.pay.api.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.Invocation.Builder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.Response.StatusType;
import java.util.Set;
import org.glassfish.jersey.message.internal.OutboundJaxrsResponse;
import org.glassfish.jersey.message.internal.OutboundMessageContext;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import uk.gov.pay.api.exception.AuthorisationRequestException;
import uk.gov.pay.api.exception.ConnectorResponseErrorException;
import uk.gov.pay.api.exception.ConnectorResponseErrorException.ConnectorErrorResponse;
import uk.gov.pay.api.model.AuthorisationRequest;

public class AuthorisationServiceDiffblueTest {
  /**
   * Test {@link AuthorisationService#authoriseRequest(AuthorisationRequest)}.
   *
   * <p>Method under test: {@link AuthorisationService#authoriseRequest(AuthorisationRequest)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Response AuthorisationService.authoriseRequest(AuthorisationRequest)"})
  public void testAuthoriseRequest() {
    // Arrange
    Builder builder = mock(Builder.class);
    when(builder.post(Mockito.<Entity<?>>any()))
        .thenReturn(new OutboundJaxrsResponse(Status.OK, new OutboundMessageContext()));

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.authorisationURI()).thenReturn("JaneDoe");

    AuthorisationService authorisationService =
        new AuthorisationService(client, connectorUriGenerator);
    AuthorisationRequest authorisationRequest =
        new AuthorisationRequest("ABC123", "42", "Cvc", "2020-03-01", "Cardholder Name");

    // Act and Assert
    assertThrows(
        AuthorisationRequestException.class,
        () -> authorisationService.authoriseRequest(authorisationRequest));
    verify(client).target("JaneDoe");
    verify(builder).post(isA(Entity.class));
    verify(webTarget).request();
    verify(connectorUriGenerator).authorisationURI();
  }

  /**
   * Test {@link AuthorisationService#authoriseRequest(AuthorisationRequest)}.
   *
   * <ul>
   *   <li>Given {@link OutboundJaxrsResponse} {@link OutboundJaxrsResponse#getStatus()} return one.
   *   <li>Then calls {@link OutboundJaxrsResponse#close()}.
   * </ul>
   *
   * <p>Method under test: {@link AuthorisationService#authoriseRequest(AuthorisationRequest)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Response AuthorisationService.authoriseRequest(AuthorisationRequest)"})
  public void testAuthoriseRequest_givenOutboundJaxrsResponseGetStatusReturnOne_thenCallsClose()
      throws ProcessingException {
    // Arrange
    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.getStatus()).thenReturn(1);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());
    doNothing().when(outboundJaxrsResponse).close();

    Builder builder = mock(Builder.class);
    when(builder.post(Mockito.<Entity<?>>any())).thenReturn(outboundJaxrsResponse);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.authorisationURI()).thenReturn("JaneDoe");

    AuthorisationService authorisationService =
        new AuthorisationService(client, connectorUriGenerator);
    AuthorisationRequest authorisationRequest =
        new AuthorisationRequest("ABC123", "42", "Cvc", "2020-03-01", "Cardholder Name");

    // Act and Assert
    assertThrows(
        AuthorisationRequestException.class,
        () -> authorisationService.authoriseRequest(authorisationRequest));
    verify(client).target("JaneDoe");
    verify(builder).post(isA(Entity.class));
    verify(webTarget).request();
    verify(outboundJaxrsResponse).close();
    verify(outboundJaxrsResponse, atLeast(1)).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(connectorUriGenerator).authorisationURI();
  }

  /**
   * Test {@link AuthorisationService#authoriseRequest(AuthorisationRequest)}.
   *
   * <ul>
   *   <li>Then StatusInfo return {@link Status}.
   * </ul>
   *
   * <p>Method under test: {@link AuthorisationService#authoriseRequest(AuthorisationRequest)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Response AuthorisationService.authoriseRequest(AuthorisationRequest)"})
  public void testAuthoriseRequest_thenStatusInfoReturnStatus() {
    // Arrange
    Builder builder = mock(Builder.class);
    when(builder.post(Mockito.<Entity<?>>any()))
        .thenReturn(new OutboundJaxrsResponse(Status.NO_CONTENT, new OutboundMessageContext()));

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.authorisationURI()).thenReturn("JaneDoe");

    AuthorisationService authorisationService =
        new AuthorisationService(client, connectorUriGenerator);
    AuthorisationRequest authorisationRequest =
        new AuthorisationRequest("ABC123", "42", "Cvc", "2020-03-01", "Cardholder Name");

    // Act
    Response actualAuthoriseRequestResult =
        authorisationService.authoriseRequest(authorisationRequest);

    // Assert
    verify(client).target("JaneDoe");
    verify(builder).post(isA(Entity.class));
    verify(webTarget).request();
    verify(connectorUriGenerator).authorisationURI();
    StatusType statusInfo = actualAuthoriseRequestResult.getStatusInfo();
    assertTrue(statusInfo instanceof Status);
    assertTrue(actualAuthoriseRequestResult instanceof OutboundJaxrsResponse);
    assertNull(actualAuthoriseRequestResult.getEntityTag());
    assertNull(actualAuthoriseRequestResult.getMediaType());
    assertNull(actualAuthoriseRequestResult.getEntity());
    assertNull(actualAuthoriseRequestResult.getLocation());
    assertNull(actualAuthoriseRequestResult.getDate());
    assertNull(actualAuthoriseRequestResult.getLastModified());
    assertNull(actualAuthoriseRequestResult.getLanguage());
    assertEquals(-1, actualAuthoriseRequestResult.getLength());
    assertEquals(204, actualAuthoriseRequestResult.getStatus());
    assertEquals(Status.NO_CONTENT, statusInfo);
    assertTrue(actualAuthoriseRequestResult.getCookies().isEmpty());
    MultivaluedMap<String, Object> headers = actualAuthoriseRequestResult.getHeaders();
    assertTrue(headers.isEmpty());
    assertTrue(actualAuthoriseRequestResult.getStringHeaders().isEmpty());
    Set<String> allowedMethods = actualAuthoriseRequestResult.getAllowedMethods();
    assertTrue(allowedMethods.isEmpty());
    assertSame(allowedMethods, actualAuthoriseRequestResult.getLinks());
    assertSame(headers, actualAuthoriseRequestResult.getMetadata());
  }
}
