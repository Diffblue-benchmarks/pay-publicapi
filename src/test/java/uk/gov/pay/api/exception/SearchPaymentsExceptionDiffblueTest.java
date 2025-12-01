package uk.gov.pay.api.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.glassfish.jersey.message.internal.OutboundJaxrsResponse;
import org.glassfish.jersey.message.internal.OutboundMessageContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.service.payments.commons.model.ErrorIdentifier;

class SearchPaymentsExceptionDiffblueTest {
  /**
   * Test {@link SearchPaymentsException#SearchPaymentsException(Throwable)}.
   *
   * <p>Method under test: {@link SearchPaymentsException#SearchPaymentsException(Throwable)}
   */
  @Test
  @DisplayName("Test new SearchPaymentsException(Throwable)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchPaymentsException.<init>(Throwable)"})
  void testNewSearchPaymentsException() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    SearchPaymentsException actualSearchPaymentsException = new SearchPaymentsException(cause);

    // Assert
    assertEquals(0, actualSearchPaymentsException.getErrorStatus());
    assertEquals(0, actualSearchPaymentsException.getSuppressed().length);
    assertSame(cause, actualSearchPaymentsException.getCause());
  }

  /**
   * Test {@link SearchPaymentsException#SearchPaymentsException(Response)}.
   *
   * <p>Method under test: {@link SearchPaymentsException#SearchPaymentsException(Response)}
   */
  @Test
  @DisplayName("Test new SearchPaymentsException(Response)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchPaymentsException.<init>(Response)"})
  void testNewSearchPaymentsException2() {
    // Arrange and Act
    SearchPaymentsException actualSearchPaymentsException =
        new SearchPaymentsException(
            new OutboundJaxrsResponse(Status.OK, new OutboundMessageContext()));

    // Assert
    assertEquals(
        "OutboundJaxrsResponse{status=200, reason=OK, hasEntity=false, closed=false, buffered=false}",
        actualSearchPaymentsException.getLocalizedMessage());
    assertEquals(
        "OutboundJaxrsResponse{status=200, reason=OK, hasEntity=false, closed=false, buffered=false}",
        actualSearchPaymentsException.getMessage());
    assertNull(actualSearchPaymentsException.getReason());
    assertNull(actualSearchPaymentsException.getCause());
    assertEquals(0, actualSearchPaymentsException.getSuppressed().length);
    assertEquals(200, actualSearchPaymentsException.getErrorStatus());
    assertEquals(ErrorIdentifier.GENERIC, actualSearchPaymentsException.getErrorIdentifier());
    assertFalse(actualSearchPaymentsException.hasReason());
  }
}
