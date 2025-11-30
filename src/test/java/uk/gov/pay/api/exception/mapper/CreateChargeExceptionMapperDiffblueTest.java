package uk.gov.pay.api.exception.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.glassfish.jersey.message.internal.OutboundJaxrsResponse;
import org.glassfish.jersey.message.internal.OutboundMessageContext;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.pay.api.exception.CreateChargeException;
import uk.gov.pay.api.model.RequestError;

public class CreateChargeExceptionMapperDiffblueTest {
  /**
   * Test {@link CreateChargeExceptionMapper#toResponse(CreateChargeException)} with {@code
   * exception}.
   *
   * <ul>
   *   <li>Then return Entity Code is {@code P0199}.
   * </ul>
   *
   * <p>Method under test: {@link CreateChargeExceptionMapper#toResponse(CreateChargeException)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Response CreateChargeExceptionMapper.toResponse(CreateChargeException)"})
  public void testToResponseWithException_thenReturnEntityCodeIsP0199() {
    // Arrange
    CreateChargeExceptionMapper createChargeExceptionMapper = new CreateChargeExceptionMapper();

    // Act
    Response actualToResponseResult =
        createChargeExceptionMapper.toResponse(
            new CreateChargeException(
                new OutboundJaxrsResponse(Status.NOT_FOUND, new OutboundMessageContext())));

    // Assert
    assertTrue(actualToResponseResult instanceof OutboundJaxrsResponse);
    Object entity = actualToResponseResult.getEntity();
    assertTrue(entity instanceof RequestError);
    assertEquals("P0199", ((RequestError) entity).getCode());
    assertEquals(
        "There is an error with this account. Contact support with your error code - https://www.payments.service"
            + ".gov.uk/support/ .",
        ((RequestError) entity).getDescription());
    assertSame(entity, ((OutboundJaxrsResponse) actualToResponseResult).getContext().getEntity());
  }

  /**
   * Test {@link CreateChargeExceptionMapper#toResponse(CreateChargeException)} with {@code
   * exception}.
   *
   * <ul>
   *   <li>Then return Entity Description is {@code Downstream system error}.
   * </ul>
   *
   * <p>Method under test: {@link CreateChargeExceptionMapper#toResponse(CreateChargeException)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Response CreateChargeExceptionMapper.toResponse(CreateChargeException)"})
  public void testToResponseWithException_thenReturnEntityDescriptionIsDownstreamSystemError() {
    // Arrange
    CreateChargeExceptionMapper createChargeExceptionMapper = new CreateChargeExceptionMapper();

    // Act
    Response actualToResponseResult =
        createChargeExceptionMapper.toResponse(
            new CreateChargeException(
                new OutboundJaxrsResponse(Status.OK, new OutboundMessageContext())));

    // Assert
    assertTrue(actualToResponseResult instanceof OutboundJaxrsResponse);
    Object entity = actualToResponseResult.getEntity();
    assertTrue(entity instanceof RequestError);
    assertEquals("Downstream system error", ((RequestError) entity).getDescription());
    assertEquals("P0198", ((RequestError) entity).getCode());
    assertSame(entity, ((OutboundJaxrsResponse) actualToResponseResult).getContext().getEntity());
  }
}
