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

class SearchTransactionsExceptionDiffblueTest {
  /**
   * Test {@link SearchTransactionsException#SearchTransactionsException(Throwable)}.
   *
   * <p>Method under test: {@link
   * SearchTransactionsException#SearchTransactionsException(Throwable)}
   */
  @Test
  @DisplayName("Test new SearchTransactionsException(Throwable)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchTransactionsException.<init>(Throwable)"})
  void testNewSearchTransactionsException() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    SearchTransactionsException actualSearchTransactionsException =
        new SearchTransactionsException(cause);

    // Assert
    assertEquals(0, actualSearchTransactionsException.getErrorStatus());
    assertEquals(0, actualSearchTransactionsException.getSuppressed().length);
    assertSame(cause, actualSearchTransactionsException.getCause());
  }

  /**
   * Test {@link SearchTransactionsException#SearchTransactionsException(Response)}.
   *
   * <p>Method under test: {@link SearchTransactionsException#SearchTransactionsException(Response)}
   */
  @Test
  @DisplayName("Test new SearchTransactionsException(Response)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchTransactionsException.<init>(Response)"})
  void testNewSearchTransactionsException2() {
    // Arrange and Act
    SearchTransactionsException actualSearchTransactionsException =
        new SearchTransactionsException(
            new OutboundJaxrsResponse(Status.OK, new OutboundMessageContext()));

    // Assert
    assertEquals(
        "OutboundJaxrsResponse{status=200, reason=OK, hasEntity=false, closed=false, buffered=false}",
        actualSearchTransactionsException.getLocalizedMessage());
    assertEquals(
        "OutboundJaxrsResponse{status=200, reason=OK, hasEntity=false, closed=false, buffered=false}",
        actualSearchTransactionsException.getMessage());
    assertNull(actualSearchTransactionsException.getReason());
    assertNull(actualSearchTransactionsException.getCause());
    assertEquals(0, actualSearchTransactionsException.getSuppressed().length);
    assertEquals(200, actualSearchTransactionsException.getErrorStatus());
    assertEquals(ErrorIdentifier.GENERIC, actualSearchTransactionsException.getErrorIdentifier());
    assertFalse(actualSearchTransactionsException.hasReason());
  }
}
