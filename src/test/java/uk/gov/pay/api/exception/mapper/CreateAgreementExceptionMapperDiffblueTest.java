package uk.gov.pay.api.exception.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
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
import uk.gov.pay.api.exception.CreateAgreementException;
import uk.gov.pay.api.model.RequestError;

class CreateAgreementExceptionMapperDiffblueTest {
  /**
   * Test {@link CreateAgreementExceptionMapper#toResponse(CreateAgreementException)} with {@code
   * exception}.
   *
   * <ul>
   *   <li>Then StatusInfo return {@link Status}.
   * </ul>
   *
   * <p>Method under test: {@link
   * CreateAgreementExceptionMapper#toResponse(CreateAgreementException)}
   */
  @Test
  @DisplayName(
      "Test toResponse(CreateAgreementException) with 'exception'; then StatusInfo return Status")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Response CreateAgreementExceptionMapper.toResponse(CreateAgreementException)"
  })
  void testToResponseWithException_thenStatusInfoReturnStatus() {
    // Arrange
    CreateAgreementExceptionMapper createAgreementExceptionMapper =
        new CreateAgreementExceptionMapper();

    // Act
    Response actualToResponseResult =
        createAgreementExceptionMapper.toResponse(
            new CreateAgreementException(
                new OutboundJaxrsResponse(Status.OK, new OutboundMessageContext())));

    // Assert
    StatusType statusInfo = actualToResponseResult.getStatusInfo();
    assertTrue(statusInfo instanceof Status);
    assertTrue(actualToResponseResult instanceof OutboundJaxrsResponse);
    assertTrue(actualToResponseResult.getEntity() instanceof RequestError);
    assertNull(actualToResponseResult.getEntityTag());
    assertNull(actualToResponseResult.getMediaType());
    assertNull(actualToResponseResult.getLocation());
    assertNull(actualToResponseResult.getDate());
    assertNull(actualToResponseResult.getLastModified());
    assertNull(actualToResponseResult.getLanguage());
    assertEquals(-1, actualToResponseResult.getLength());
    assertEquals(500, actualToResponseResult.getStatus());
    assertEquals(Status.INTERNAL_SERVER_ERROR, statusInfo);
    assertTrue(actualToResponseResult.getCookies().isEmpty());
    MultivaluedMap<String, Object> headers = actualToResponseResult.getHeaders();
    assertTrue(headers.isEmpty());
    assertTrue(actualToResponseResult.getStringHeaders().isEmpty());
    Set<String> allowedMethods = actualToResponseResult.getAllowedMethods();
    assertTrue(allowedMethods.isEmpty());
    assertSame(allowedMethods, actualToResponseResult.getLinks());
    assertSame(headers, actualToResponseResult.getMetadata());
  }
}
