package uk.gov.pay.api.exception.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.Response.StatusType;
import org.glassfish.jersey.message.internal.OutboundJaxrsResponse;
import org.glassfish.jersey.message.internal.OutboundMessageContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.exception.GetEventsException;

class GetEventsExceptionMapperDiffblueTest {
  /**
   * Test {@link GetEventsExceptionMapper#toResponse(GetEventsException)} with {@code exception}.
   *
   * <ul>
   *   <li>Then return Status is five hundred.
   * </ul>
   *
   * <p>Method under test: {@link GetEventsExceptionMapper#toResponse(GetEventsException)}
   */
  @Test
  @DisplayName(
      "Test toResponse(GetEventsException) with 'exception'; then return Status is five hundred")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Response GetEventsExceptionMapper.toResponse(GetEventsException)"})
  void testToResponseWithException_thenReturnStatusIsFiveHundred() {
    // Arrange
    GetEventsExceptionMapper getEventsExceptionMapper = new GetEventsExceptionMapper();

    // Act
    Response actualToResponseResult =
        getEventsExceptionMapper.toResponse(
            new GetEventsException(
                new OutboundJaxrsResponse(Status.OK, new OutboundMessageContext())));

    // Assert
    StatusType statusInfo = actualToResponseResult.getStatusInfo();
    assertTrue(statusInfo instanceof Status);
    assertTrue(actualToResponseResult instanceof OutboundJaxrsResponse);
    assertEquals(500, actualToResponseResult.getStatus());
    assertEquals(Status.INTERNAL_SERVER_ERROR, statusInfo);
  }

  /**
   * Test {@link GetEventsExceptionMapper#toResponse(GetEventsException)} with {@code exception}.
   *
   * <ul>
   *   <li>Then return Status is four hundred four.
   * </ul>
   *
   * <p>Method under test: {@link GetEventsExceptionMapper#toResponse(GetEventsException)}
   */
  @Test
  @DisplayName(
      "Test toResponse(GetEventsException) with 'exception'; then return Status is four hundred four")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Response GetEventsExceptionMapper.toResponse(GetEventsException)"})
  void testToResponseWithException_thenReturnStatusIsFourHundredFour() {
    // Arrange
    GetEventsExceptionMapper getEventsExceptionMapper = new GetEventsExceptionMapper();

    // Act
    Response actualToResponseResult =
        getEventsExceptionMapper.toResponse(
            new GetEventsException(
                new OutboundJaxrsResponse(Status.NOT_FOUND, new OutboundMessageContext())));

    // Assert
    StatusType statusInfo = actualToResponseResult.getStatusInfo();
    assertTrue(statusInfo instanceof Status);
    assertTrue(actualToResponseResult instanceof OutboundJaxrsResponse);
    assertEquals(404, actualToResponseResult.getStatus());
    assertEquals(Status.NOT_FOUND, statusInfo);
  }
}
