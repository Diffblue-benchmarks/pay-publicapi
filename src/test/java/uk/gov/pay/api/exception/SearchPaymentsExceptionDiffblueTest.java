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

public class SearchPaymentsExceptionDiffblueTest {
  /**
   * Test {@link SearchPaymentsException#SearchPaymentsException(Throwable)}.
   *
   * <p>Method under test: {@link SearchPaymentsException#SearchPaymentsException(Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchPaymentsException.<init>(Throwable)"})
  public void testNewSearchPaymentsException() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchPaymentsException.<init>(Response)"})
  public void testNewSearchPaymentsException2() {
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
