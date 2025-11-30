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
import uk.gov.pay.api.exception.SearchRefundsException;

public class SearchRefundsExceptionMapperDiffblueTest {
  /**
   * Test {@link SearchRefundsExceptionMapper#toResponse(SearchRefundsException)} with {@code
   * exception}.
   *
   * <ul>
   *   <li>Then return Status is five hundred.
   * </ul>
   *
   * <p>Method under test: {@link SearchRefundsExceptionMapper#toResponse(SearchRefundsException)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Response SearchRefundsExceptionMapper.toResponse(SearchRefundsException)"})
  public void testToResponseWithException_thenReturnStatusIsFiveHundred() {
    // Arrange
    SearchRefundsExceptionMapper searchRefundsExceptionMapper = new SearchRefundsExceptionMapper();

    // Act
    Response actualToResponseResult =
        searchRefundsExceptionMapper.toResponse(
            new SearchRefundsException(
                new OutboundJaxrsResponse(Status.OK, new OutboundMessageContext())));

    // Assert
    StatusType statusInfo = actualToResponseResult.getStatusInfo();
    assertTrue(statusInfo instanceof Status);
    assertTrue(actualToResponseResult instanceof OutboundJaxrsResponse);
    assertEquals(500, actualToResponseResult.getStatus());
    assertEquals(Status.INTERNAL_SERVER_ERROR, statusInfo);
  }

  /**
   * Test {@link SearchRefundsExceptionMapper#toResponse(SearchRefundsException)} with {@code
   * exception}.
   *
   * <ul>
   *   <li>Then return Status is four hundred four.
   * </ul>
   *
   * <p>Method under test: {@link SearchRefundsExceptionMapper#toResponse(SearchRefundsException)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Response SearchRefundsExceptionMapper.toResponse(SearchRefundsException)"})
  public void testToResponseWithException_thenReturnStatusIsFourHundredFour() {
    // Arrange
    SearchRefundsExceptionMapper searchRefundsExceptionMapper = new SearchRefundsExceptionMapper();

    // Act
    Response actualToResponseResult =
        searchRefundsExceptionMapper.toResponse(
            new SearchRefundsException(
                new OutboundJaxrsResponse(Status.NOT_FOUND, new OutboundMessageContext())));

    // Assert
    StatusType statusInfo = actualToResponseResult.getStatusInfo();
    assertTrue(statusInfo instanceof Status);
    assertTrue(actualToResponseResult instanceof OutboundJaxrsResponse);
    assertEquals(404, actualToResponseResult.getStatus());
    assertEquals(Status.NOT_FOUND, statusInfo);
  }
}
