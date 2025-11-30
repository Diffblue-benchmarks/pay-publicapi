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

public class SearchAgreementsExceptionDiffblueTest {
  /**
   * Test {@link SearchAgreementsException#SearchAgreementsException(Throwable)}.
   *
   * <p>Method under test: {@link SearchAgreementsException#SearchAgreementsException(Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchAgreementsException.<init>(Throwable)"})
  public void testNewSearchAgreementsException() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    SearchAgreementsException actualSearchAgreementsException =
        new SearchAgreementsException(cause);

    // Assert
    assertEquals(0, actualSearchAgreementsException.getErrorStatus());
    assertEquals(0, actualSearchAgreementsException.getSuppressed().length);
    assertSame(cause, actualSearchAgreementsException.getCause());
  }

  /**
   * Test {@link SearchAgreementsException#SearchAgreementsException(Response)}.
   *
   * <p>Method under test: {@link SearchAgreementsException#SearchAgreementsException(Response)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchAgreementsException.<init>(Response)"})
  public void testNewSearchAgreementsException2() {
    // Arrange and Act
    SearchAgreementsException actualSearchAgreementsException =
        new SearchAgreementsException(
            new OutboundJaxrsResponse(Status.OK, new OutboundMessageContext()));

    // Assert
    assertEquals(
        "OutboundJaxrsResponse{status=200, reason=OK, hasEntity=false, closed=false, buffered=false}",
        actualSearchAgreementsException.getLocalizedMessage());
    assertEquals(
        "OutboundJaxrsResponse{status=200, reason=OK, hasEntity=false, closed=false, buffered=false}",
        actualSearchAgreementsException.getMessage());
    assertNull(actualSearchAgreementsException.getReason());
    assertNull(actualSearchAgreementsException.getCause());
    assertEquals(0, actualSearchAgreementsException.getSuppressed().length);
    assertEquals(200, actualSearchAgreementsException.getErrorStatus());
    assertEquals(ErrorIdentifier.GENERIC, actualSearchAgreementsException.getErrorIdentifier());
    assertFalse(actualSearchAgreementsException.hasReason());
  }
}
