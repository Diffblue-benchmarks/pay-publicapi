package uk.gov.pay.api.service;

import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
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
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import uk.gov.pay.api.auth.Account;
import uk.gov.pay.api.model.TokenPaymentType;

public class CapturePaymentServiceDiffblueTest {
  /**
   * Test {@link CapturePaymentService#capture(Account, String)}.
   *
   * <p>Method under test: {@link CapturePaymentService#capture(Account, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Response CapturePaymentService.capture(Account, String)"})
  public void testCapture() {
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
