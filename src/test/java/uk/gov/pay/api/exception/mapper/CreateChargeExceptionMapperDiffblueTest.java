package uk.gov.pay.api.exception.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.glassfish.jersey.message.internal.OutboundJaxrsResponse;
import org.glassfish.jersey.message.internal.OutboundMessageContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.exception.CreateChargeException;
import uk.gov.pay.api.model.RequestError;

class CreateChargeExceptionMapperDiffblueTest {
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
  @DisplayName(
      "Test toResponse(CreateChargeException) with 'exception'; then return Entity Code is 'P0199'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Response CreateChargeExceptionMapper.toResponse(CreateChargeException)"})
  void testToResponseWithException_thenReturnEntityCodeIsP0199() {
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
  @DisplayName(
      "Test toResponse(CreateChargeException) with 'exception'; then return Entity Description is 'Downstream system error'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Response CreateChargeExceptionMapper.toResponse(CreateChargeException)"})
  void testToResponseWithException_thenReturnEntityDescriptionIsDownstreamSystemError() {
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
