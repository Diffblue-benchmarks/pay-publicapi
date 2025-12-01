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

class SearchAgreementsExceptionDiffblueTest {
  /**
   * Test {@link SearchAgreementsException#SearchAgreementsException(Throwable)}.
   *
   * <p>Method under test: {@link SearchAgreementsException#SearchAgreementsException(Throwable)}
   */
  @Test
  @DisplayName("Test new SearchAgreementsException(Throwable)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchAgreementsException.<init>(Throwable)"})
  void testNewSearchAgreementsException() {
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
  @DisplayName("Test new SearchAgreementsException(Response)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchAgreementsException.<init>(Response)"})
  void testNewSearchAgreementsException2() {
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
