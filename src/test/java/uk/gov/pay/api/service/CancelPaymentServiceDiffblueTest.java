package uk.gov.pay.api.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
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
import uk.gov.pay.api.auth.Account;
import uk.gov.pay.api.exception.CancelChargeException;
import uk.gov.pay.api.exception.ConnectorResponseErrorException;
import uk.gov.pay.api.exception.ConnectorResponseErrorException.ConnectorErrorResponse;
import uk.gov.pay.api.model.TokenPaymentType;

public class CancelPaymentServiceDiffblueTest {
  /**
   * Test {@link CancelPaymentService#cancel(Account, String)}.
   *
   * <p>Method under test: {@link CancelPaymentService#cancel(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Response CancelPaymentService.cancel(Account, String)"})
  public void testCancel() {
    // Arrange
    Builder builder = mock(Builder.class);
    when(builder.post(Mockito.<Entity<?>>any()))
        .thenReturn(new OutboundJaxrsResponse(Status.OK, new OutboundMessageContext()));

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.cancelURI(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn("Cancel URI");

    CancelPaymentService cancelPaymentService =
        new CancelPaymentService(client, connectorUriGenerator);

    // Act and Assert
    assertThrows(
        CancelChargeException.class,
        () ->
            cancelPaymentService.cancel(new Account("42", TokenPaymentType.CARD, "ABC123"), "42"));
    verify(client).target("Cancel URI");
    verify(builder).post(isNull());
    verify(webTarget).request();
    verify(connectorUriGenerator).cancelURI(isA(Account.class), eq("42"));
  }

  /**
   * Test {@link CancelPaymentService#cancel(Account, String)}.
   *
   * <ul>
   *   <li>Given {@link OutboundJaxrsResponse} {@link OutboundJaxrsResponse#getStatus()} return one.
   *   <li>Then calls {@link OutboundJaxrsResponse#close()}.
   * </ul>
   *
   * <p>Method under test: {@link CancelPaymentService#cancel(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Response CancelPaymentService.cancel(Account, String)"})
  public void testCancel_givenOutboundJaxrsResponseGetStatusReturnOne_thenCallsClose()
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
    when(connectorUriGenerator.cancelURI(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn("Cancel URI");

    CancelPaymentService cancelPaymentService =
        new CancelPaymentService(client, connectorUriGenerator);

    // Act and Assert
    assertThrows(
        CancelChargeException.class,
        () ->
            cancelPaymentService.cancel(new Account("42", TokenPaymentType.CARD, "ABC123"), "42"));
    verify(client).target("Cancel URI");
    verify(builder).post(isNull());
    verify(webTarget).request();
    verify(outboundJaxrsResponse).close();
    verify(outboundJaxrsResponse, atLeast(1)).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(connectorUriGenerator).cancelURI(isA(Account.class), eq("42"));
  }

  /**
   * Test {@link CancelPaymentService#cancel(Account, String)}.
   *
   * <ul>
   *   <li>Then StatusInfo return {@link Status}.
   * </ul>
   *
   * <p>Method under test: {@link CancelPaymentService#cancel(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Response CancelPaymentService.cancel(Account, String)"})
  public void testCancel_thenStatusInfoReturnStatus() {
    // Arrange
    Builder builder = mock(Builder.class);
    when(builder.post(Mockito.<Entity<?>>any()))
        .thenReturn(new OutboundJaxrsResponse(Status.NO_CONTENT, new OutboundMessageContext()));

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.cancelURI(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn("Cancel URI");

    CancelPaymentService cancelPaymentService =
        new CancelPaymentService(client, connectorUriGenerator);

    // Act
    Response actualCancelResult =
        cancelPaymentService.cancel(new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(client).target("Cancel URI");
    verify(builder).post(isNull());
    verify(webTarget).request();
    verify(connectorUriGenerator).cancelURI(isA(Account.class), eq("42"));
    StatusType statusInfo = actualCancelResult.getStatusInfo();
    assertTrue(statusInfo instanceof Status);
    assertTrue(actualCancelResult instanceof OutboundJaxrsResponse);
    assertNull(actualCancelResult.getEntityTag());
    assertNull(actualCancelResult.getMediaType());
    assertNull(actualCancelResult.getEntity());
    assertNull(actualCancelResult.getLocation());
    assertNull(actualCancelResult.getDate());
    assertNull(actualCancelResult.getLastModified());
    assertNull(actualCancelResult.getLanguage());
    assertEquals(-1, actualCancelResult.getLength());
    assertEquals(204, actualCancelResult.getStatus());
    assertEquals(Status.NO_CONTENT, statusInfo);
    assertTrue(actualCancelResult.getCookies().isEmpty());
    MultivaluedMap<String, Object> headers = actualCancelResult.getHeaders();
    assertTrue(headers.isEmpty());
    assertTrue(actualCancelResult.getStringHeaders().isEmpty());
    Set<String> allowedMethods = actualCancelResult.getAllowedMethods();
    assertTrue(allowedMethods.isEmpty());
    assertSame(allowedMethods, actualCancelResult.getLinks());
    assertSame(headers, actualCancelResult.getMetadata());
  }
}
