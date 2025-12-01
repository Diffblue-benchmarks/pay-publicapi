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
import uk.gov.pay.api.exception.GetRefundException;

class GetRefundExceptionMapperDiffblueTest {
  /**
   * Test {@link GetRefundExceptionMapper#toResponse(GetRefundException)} with {@code exception}.
   *
   * <ul>
   *   <li>Then return Status is five hundred.
   * </ul>
   *
   * <p>Method under test: {@link GetRefundExceptionMapper#toResponse(GetRefundException)}
   */
  @Test
  @DisplayName(
      "Test toResponse(GetRefundException) with 'exception'; then return Status is five hundred")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Response GetRefundExceptionMapper.toResponse(GetRefundException)"})
  void testToResponseWithException_thenReturnStatusIsFiveHundred() {
    // Arrange
    GetRefundExceptionMapper getRefundExceptionMapper = new GetRefundExceptionMapper();

    // Act
    Response actualToResponseResult =
        getRefundExceptionMapper.toResponse(
            new GetRefundException(
                new OutboundJaxrsResponse(Status.OK, new OutboundMessageContext())));

    // Assert
    StatusType statusInfo = actualToResponseResult.getStatusInfo();
    assertTrue(statusInfo instanceof Status);
    assertTrue(actualToResponseResult instanceof OutboundJaxrsResponse);
    assertEquals(500, actualToResponseResult.getStatus());
    assertEquals(Status.INTERNAL_SERVER_ERROR, statusInfo);
  }

  /**
   * Test {@link GetRefundExceptionMapper#toResponse(GetRefundException)} with {@code exception}.
   *
   * <ul>
   *   <li>Then return Status is four hundred four.
   * </ul>
   *
   * <p>Method under test: {@link GetRefundExceptionMapper#toResponse(GetRefundException)}
   */
  @Test
  @DisplayName(
      "Test toResponse(GetRefundException) with 'exception'; then return Status is four hundred four")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Response GetRefundExceptionMapper.toResponse(GetRefundException)"})
  void testToResponseWithException_thenReturnStatusIsFourHundredFour() {
    // Arrange
    GetRefundExceptionMapper getRefundExceptionMapper = new GetRefundExceptionMapper();

    // Act
    Response actualToResponseResult =
        getRefundExceptionMapper.toResponse(
            new GetRefundException(
                new OutboundJaxrsResponse(Status.NOT_FOUND, new OutboundMessageContext())));

    // Assert
    StatusType statusInfo = actualToResponseResult.getStatusInfo();
    assertTrue(statusInfo instanceof Status);
    assertTrue(actualToResponseResult instanceof OutboundJaxrsResponse);
    assertEquals(404, actualToResponseResult.getStatus());
    assertEquals(Status.NOT_FOUND, statusInfo);
  }
}
