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

class SearchDisputesExceptionDiffblueTest {
  /**
   * Test {@link SearchDisputesException#SearchDisputesException(Throwable)}.
   *
   * <p>Method under test: {@link SearchDisputesException#SearchDisputesException(Throwable)}
   */
  @Test
  @DisplayName("Test new SearchDisputesException(Throwable)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchDisputesException.<init>(Throwable)"})
  void testNewSearchDisputesException() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    SearchDisputesException actualSearchDisputesException = new SearchDisputesException(cause);

    // Assert
    assertEquals(0, actualSearchDisputesException.getErrorStatus());
    assertEquals(0, actualSearchDisputesException.getSuppressed().length);
    assertSame(cause, actualSearchDisputesException.getCause());
  }

  /**
   * Test {@link SearchDisputesException#SearchDisputesException(Response)}.
   *
   * <p>Method under test: {@link SearchDisputesException#SearchDisputesException(Response)}
   */
  @Test
  @DisplayName("Test new SearchDisputesException(Response)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchDisputesException.<init>(Response)"})
  void testNewSearchDisputesException2() {
    // Arrange and Act
    SearchDisputesException actualSearchDisputesException =
        new SearchDisputesException(
            new OutboundJaxrsResponse(Status.OK, new OutboundMessageContext()));

    // Assert
    assertEquals(
        "OutboundJaxrsResponse{status=200, reason=OK, hasEntity=false, closed=false, buffered=false}",
        actualSearchDisputesException.getLocalizedMessage());
    assertEquals(
        "OutboundJaxrsResponse{status=200, reason=OK, hasEntity=false, closed=false, buffered=false}",
        actualSearchDisputesException.getMessage());
    assertNull(actualSearchDisputesException.getReason());
    assertNull(actualSearchDisputesException.getCause());
    assertEquals(0, actualSearchDisputesException.getSuppressed().length);
    assertEquals(200, actualSearchDisputesException.getErrorStatus());
    assertEquals(ErrorIdentifier.GENERIC, actualSearchDisputesException.getErrorIdentifier());
    assertFalse(actualSearchDisputesException.hasReason());
  }
}
