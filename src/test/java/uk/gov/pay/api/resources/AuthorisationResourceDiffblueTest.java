package uk.gov.pay.api.resources;

import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.message.internal.OutboundJaxrsResponse;
import org.glassfish.jersey.message.internal.OutboundMessageContext;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import uk.gov.pay.api.model.AuthorisationRequest;
import uk.gov.pay.api.service.AuthorisationService;

public class AuthorisationResourceDiffblueTest {
  /**
   * Test {@link AuthorisationResource#authorisePayment(AuthorisationRequest)}.
   *
   * <p>Method under test: {@link AuthorisationResource#authorisePayment(AuthorisationRequest)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Response AuthorisationResource.authorisePayment(AuthorisationRequest)"})
  public void testAuthorisePayment() {
    // Arrange
    AuthorisationService authorisationService = mock(AuthorisationService.class);
    OutboundJaxrsResponse outboundJaxrsResponse =
        new OutboundJaxrsResponse(null, new OutboundMessageContext());
    when(authorisationService.authoriseRequest(Mockito.<AuthorisationRequest>any()))
        .thenReturn(outboundJaxrsResponse);
    AuthorisationResource authorisationResource = new AuthorisationResource(authorisationService);
    AuthorisationRequest authorisationRequest =
        new AuthorisationRequest("ABC123", "42", "Cvc", "2020-03-01", "Cardholder Name");

    // Act
    Response actualAuthorisePaymentResult =
        authorisationResource.authorisePayment(authorisationRequest);

    // Assert
    verify(authorisationService).authoriseRequest(isA(AuthorisationRequest.class));
    assertSame(outboundJaxrsResponse, actualAuthorisePaymentResult);
  }
}
