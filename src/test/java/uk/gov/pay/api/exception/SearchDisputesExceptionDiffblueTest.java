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

public class SearchDisputesExceptionDiffblueTest {
  /**
   * Test {@link SearchDisputesException#SearchDisputesException(Throwable)}.
   *
   * <p>Method under test: {@link SearchDisputesException#SearchDisputesException(Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchDisputesException.<init>(Throwable)"})
  public void testNewSearchDisputesException() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchDisputesException.<init>(Response)"})
  public void testNewSearchDisputesException2() {
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
