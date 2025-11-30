package uk.gov.pay.api.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.glassfish.jersey.message.internal.OutboundJaxrsResponse;
import org.glassfish.jersey.message.internal.OutboundMessageContext;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.service.payments.commons.model.ErrorIdentifier;

public class SearchTransactionsExceptionDiffblueTest {
  /**
   * Test {@link SearchTransactionsException#SearchTransactionsException(Throwable)}.
   *
   * <p>Method under test: {@link
   * SearchTransactionsException#SearchTransactionsException(Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchTransactionsException.<init>(Throwable)"})
  public void testNewSearchTransactionsException() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchTransactionsException.<init>(Response)"})
  public void testNewSearchTransactionsException2() {
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
