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

public class SearchRefundsExceptionDiffblueTest {
  /**
   * Test {@link SearchRefundsException#SearchRefundsException(Throwable)}.
   *
   * <p>Method under test: {@link SearchRefundsException#SearchRefundsException(Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchRefundsException.<init>(Throwable)"})
  public void testNewSearchRefundsException() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    SearchRefundsException actualSearchRefundsException = new SearchRefundsException(cause);

    // Assert
    assertEquals(0, actualSearchRefundsException.getErrorStatus());
    assertEquals(0, actualSearchRefundsException.getSuppressed().length);
    assertSame(cause, actualSearchRefundsException.getCause());
  }

  /**
   * Test {@link SearchRefundsException#SearchRefundsException(Response)}.
   *
   * <p>Method under test: {@link SearchRefundsException#SearchRefundsException(Response)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchRefundsException.<init>(Response)"})
  public void testNewSearchRefundsException2() {
    // Arrange and Act
    SearchRefundsException actualSearchRefundsException =
        new SearchRefundsException(
            new OutboundJaxrsResponse(Status.OK, new OutboundMessageContext()));

    // Assert
    assertEquals(
        "OutboundJaxrsResponse{status=200, reason=OK, hasEntity=false, closed=false, buffered=false}",
        actualSearchRefundsException.getLocalizedMessage());
    assertEquals(
        "OutboundJaxrsResponse{status=200, reason=OK, hasEntity=false, closed=false, buffered=false}",
        actualSearchRefundsException.getMessage());
    assertNull(actualSearchRefundsException.getReason());
    assertNull(actualSearchRefundsException.getCause());
    assertEquals(0, actualSearchRefundsException.getSuppressed().length);
    assertEquals(200, actualSearchRefundsException.getErrorStatus());
    assertEquals(ErrorIdentifier.GENERIC, actualSearchRefundsException.getErrorIdentifier());
    assertFalse(actualSearchRefundsException.hasReason());
  }
}
