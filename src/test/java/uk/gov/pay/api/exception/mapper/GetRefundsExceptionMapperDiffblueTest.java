package uk.gov.pay.api.exception.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.Response.StatusType;
import org.glassfish.jersey.message.internal.OutboundJaxrsResponse;
import org.glassfish.jersey.message.internal.OutboundMessageContext;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.pay.api.exception.GetRefundsException;

public class GetRefundsExceptionMapperDiffblueTest {
  /**
   * Test {@link GetRefundsExceptionMapper#toResponse(GetRefundsException)} with {@code exception}.
   *
   * <ul>
   *   <li>Then return Status is five hundred.
   * </ul>
   *
   * <p>Method under test: {@link GetRefundsExceptionMapper#toResponse(GetRefundsException)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Response GetRefundsExceptionMapper.toResponse(GetRefundsException)"})
  public void testToResponseWithException_thenReturnStatusIsFiveHundred() {
    // Arrange
    GetRefundsExceptionMapper getRefundsExceptionMapper = new GetRefundsExceptionMapper();

    // Act
    Response actualToResponseResult =
        getRefundsExceptionMapper.toResponse(
            new GetRefundsException(
                new OutboundJaxrsResponse(Status.OK, new OutboundMessageContext())));

    // Assert
    StatusType statusInfo = actualToResponseResult.getStatusInfo();
    assertTrue(statusInfo instanceof Status);
    assertTrue(actualToResponseResult instanceof OutboundJaxrsResponse);
    assertEquals(500, actualToResponseResult.getStatus());
    assertEquals(Status.INTERNAL_SERVER_ERROR, statusInfo);
  }

  /**
   * Test {@link GetRefundsExceptionMapper#toResponse(GetRefundsException)} with {@code exception}.
   *
   * <ul>
   *   <li>Then return Status is four hundred four.
   * </ul>
   *
   * <p>Method under test: {@link GetRefundsExceptionMapper#toResponse(GetRefundsException)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Response GetRefundsExceptionMapper.toResponse(GetRefundsException)"})
  public void testToResponseWithException_thenReturnStatusIsFourHundredFour() {
    // Arrange
    GetRefundsExceptionMapper getRefundsExceptionMapper = new GetRefundsExceptionMapper();

    // Act
    Response actualToResponseResult =
        getRefundsExceptionMapper.toResponse(
            new GetRefundsException(
                new OutboundJaxrsResponse(Status.NOT_FOUND, new OutboundMessageContext())));

    // Assert
    StatusType statusInfo = actualToResponseResult.getStatusInfo();
    assertTrue(statusInfo instanceof Status);
    assertTrue(actualToResponseResult instanceof OutboundJaxrsResponse);
    assertEquals(404, actualToResponseResult.getStatus());
    assertEquals(Status.NOT_FOUND, statusInfo);
  }
}
