package uk.gov.pay.api.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.Invocation.Builder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.message.internal.OutboundJaxrsResponse;
import org.glassfish.jersey.message.internal.OutboundMessageContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uk.gov.pay.api.auth.Account;
import uk.gov.pay.api.model.TokenPaymentType;

class CapturePaymentServiceDiffblueTest {
  /**
   * Test {@link CapturePaymentService#capture(Account, String)}.
   *
   * <p>Method under test: {@link CapturePaymentService#capture(Account, String)}
   */
  @Test
  @DisplayName("Test capture(Account, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Response CapturePaymentService.capture(Account, String)"})
  void testCapture() {
    // Arrange
    Builder builder = mock(Builder.class);
    OutboundJaxrsResponse outboundJaxrsResponse =
        new OutboundJaxrsResponse(null, new OutboundMessageContext());
    when(builder.post(Mockito.<Entity<?>>any())).thenReturn(outboundJaxrsResponse);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.captureURI(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn("Capture URI");

    CapturePaymentService capturePaymentService =
        new CapturePaymentService(client, connectorUriGenerator);

    // Act
    Response actualCaptureResult =
        capturePaymentService.capture(new Account("42", TokenPaymentType.CARD, "ABC123"), "42");

    // Assert
    verify(client).target("Capture URI");
    verify(builder).post(isA(Entity.class));
    verify(webTarget).request();
    verify(connectorUriGenerator).captureURI(isA(Account.class), eq("42"));
    assertSame(outboundJaxrsResponse, actualCaptureResult);
  }
}
