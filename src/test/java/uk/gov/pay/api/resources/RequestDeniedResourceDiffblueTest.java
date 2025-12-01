package uk.gov.pay.api.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.Response.StatusType;
import java.util.Set;
import org.glassfish.jersey.message.internal.OutboundJaxrsResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.model.RequestError;

class RequestDeniedResourceDiffblueTest {
  /**
   * Test {@link RequestDeniedResource#requestDeniedGet(String)}.
   *
   * <p>Method under test: {@link RequestDeniedResource#requestDeniedGet(String)}
   */
  @Test
  @DisplayName("Test requestDeniedGet(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Response RequestDeniedResource.requestDeniedGet(String)"})
  void testRequestDeniedGet() {
    // Arrange and Act
    Response actualRequestDeniedGetResult =
        new RequestDeniedResource().requestDeniedGet("Naxsi Violated Rules");

    // Assert
    StatusType statusInfo = actualRequestDeniedGetResult.getStatusInfo();
    assertTrue(statusInfo instanceof Status);
    assertTrue(actualRequestDeniedGetResult instanceof OutboundJaxrsResponse);
    assertTrue(actualRequestDeniedGetResult.getEntity() instanceof RequestError);
    assertNull(actualRequestDeniedGetResult.getEntityTag());
    assertNull(actualRequestDeniedGetResult.getMediaType());
    assertNull(actualRequestDeniedGetResult.getLocation());
    assertNull(actualRequestDeniedGetResult.getDate());
    assertNull(actualRequestDeniedGetResult.getLastModified());
    assertNull(actualRequestDeniedGetResult.getLanguage());
    assertEquals(-1, actualRequestDeniedGetResult.getLength());
    assertEquals(400, actualRequestDeniedGetResult.getStatus());
    assertEquals(Status.BAD_REQUEST, statusInfo);
    assertTrue(actualRequestDeniedGetResult.getCookies().isEmpty());
    MultivaluedMap<String, Object> headers = actualRequestDeniedGetResult.getHeaders();
    assertTrue(headers.isEmpty());
    assertTrue(actualRequestDeniedGetResult.getStringHeaders().isEmpty());
    Set<String> allowedMethods = actualRequestDeniedGetResult.getAllowedMethods();
    assertTrue(allowedMethods.isEmpty());
    assertSame(allowedMethods, actualRequestDeniedGetResult.getLinks());
    assertSame(headers, actualRequestDeniedGetResult.getMetadata());
  }

  /**
   * Test {@link RequestDeniedResource#requestDeniedPost(String)}.
   *
   * <p>Method under test: {@link RequestDeniedResource#requestDeniedPost(String)}
   */
  @Test
  @DisplayName("Test requestDeniedPost(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Response RequestDeniedResource.requestDeniedPost(String)"})
  void testRequestDeniedPost() {
    // Arrange and Act
    Response actualRequestDeniedPostResult =
        new RequestDeniedResource().requestDeniedPost("Naxsi Violated Rules");

    // Assert
    StatusType statusInfo = actualRequestDeniedPostResult.getStatusInfo();
    assertTrue(statusInfo instanceof Status);
    assertTrue(actualRequestDeniedPostResult instanceof OutboundJaxrsResponse);
    assertTrue(actualRequestDeniedPostResult.getEntity() instanceof RequestError);
    assertNull(actualRequestDeniedPostResult.getEntityTag());
    assertNull(actualRequestDeniedPostResult.getMediaType());
    assertNull(actualRequestDeniedPostResult.getLocation());
    assertNull(actualRequestDeniedPostResult.getDate());
    assertNull(actualRequestDeniedPostResult.getLastModified());
    assertNull(actualRequestDeniedPostResult.getLanguage());
    assertEquals(-1, actualRequestDeniedPostResult.getLength());
    assertEquals(400, actualRequestDeniedPostResult.getStatus());
    assertEquals(Status.BAD_REQUEST, statusInfo);
    assertTrue(actualRequestDeniedPostResult.getCookies().isEmpty());
    MultivaluedMap<String, Object> headers = actualRequestDeniedPostResult.getHeaders();
    assertTrue(headers.isEmpty());
    assertTrue(actualRequestDeniedPostResult.getStringHeaders().isEmpty());
    Set<String> allowedMethods = actualRequestDeniedPostResult.getAllowedMethods();
    assertTrue(allowedMethods.isEmpty());
    assertSame(allowedMethods, actualRequestDeniedPostResult.getLinks());
    assertSame(headers, actualRequestDeniedPostResult.getMetadata());
  }

  /**
   * Test {@link RequestDeniedResource#requestDeniedPut(String)}.
   *
   * <p>Method under test: {@link RequestDeniedResource#requestDeniedPut(String)}
   */
  @Test
  @DisplayName("Test requestDeniedPut(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Response RequestDeniedResource.requestDeniedPut(String)"})
  void testRequestDeniedPut() {
    // Arrange and Act
    Response actualRequestDeniedPutResult =
        new RequestDeniedResource().requestDeniedPut("Naxsi Violated Rules");

    // Assert
    StatusType statusInfo = actualRequestDeniedPutResult.getStatusInfo();
    assertTrue(statusInfo instanceof Status);
    assertTrue(actualRequestDeniedPutResult instanceof OutboundJaxrsResponse);
    assertTrue(actualRequestDeniedPutResult.getEntity() instanceof RequestError);
    assertNull(actualRequestDeniedPutResult.getEntityTag());
    assertNull(actualRequestDeniedPutResult.getMediaType());
    assertNull(actualRequestDeniedPutResult.getLocation());
    assertNull(actualRequestDeniedPutResult.getDate());
    assertNull(actualRequestDeniedPutResult.getLastModified());
    assertNull(actualRequestDeniedPutResult.getLanguage());
    assertEquals(-1, actualRequestDeniedPutResult.getLength());
    assertEquals(400, actualRequestDeniedPutResult.getStatus());
    assertEquals(Status.BAD_REQUEST, statusInfo);
    assertTrue(actualRequestDeniedPutResult.getCookies().isEmpty());
    MultivaluedMap<String, Object> headers = actualRequestDeniedPutResult.getHeaders();
    assertTrue(headers.isEmpty());
    assertTrue(actualRequestDeniedPutResult.getStringHeaders().isEmpty());
    Set<String> allowedMethods = actualRequestDeniedPutResult.getAllowedMethods();
    assertTrue(allowedMethods.isEmpty());
    assertSame(allowedMethods, actualRequestDeniedPutResult.getLinks());
    assertSame(headers, actualRequestDeniedPutResult.getMetadata());
  }

  /**
   * Test {@link RequestDeniedResource#requestDeniedDelete(String)}.
   *
   * <p>Method under test: {@link RequestDeniedResource#requestDeniedDelete(String)}
   */
  @Test
  @DisplayName("Test requestDeniedDelete(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Response RequestDeniedResource.requestDeniedDelete(String)"})
  void testRequestDeniedDelete() {
    // Arrange and Act
    Response actualRequestDeniedDeleteResult =
        new RequestDeniedResource().requestDeniedDelete("Naxsi Violated Rules");

    // Assert
    StatusType statusInfo = actualRequestDeniedDeleteResult.getStatusInfo();
    assertTrue(statusInfo instanceof Status);
    assertTrue(actualRequestDeniedDeleteResult instanceof OutboundJaxrsResponse);
    assertTrue(actualRequestDeniedDeleteResult.getEntity() instanceof RequestError);
    assertNull(actualRequestDeniedDeleteResult.getEntityTag());
    assertNull(actualRequestDeniedDeleteResult.getMediaType());
    assertNull(actualRequestDeniedDeleteResult.getLocation());
    assertNull(actualRequestDeniedDeleteResult.getDate());
    assertNull(actualRequestDeniedDeleteResult.getLastModified());
    assertNull(actualRequestDeniedDeleteResult.getLanguage());
    assertEquals(-1, actualRequestDeniedDeleteResult.getLength());
    assertEquals(400, actualRequestDeniedDeleteResult.getStatus());
    assertEquals(Status.BAD_REQUEST, statusInfo);
    assertTrue(actualRequestDeniedDeleteResult.getCookies().isEmpty());
    MultivaluedMap<String, Object> headers = actualRequestDeniedDeleteResult.getHeaders();
    assertTrue(headers.isEmpty());
    assertTrue(actualRequestDeniedDeleteResult.getStringHeaders().isEmpty());
    Set<String> allowedMethods = actualRequestDeniedDeleteResult.getAllowedMethods();
    assertTrue(allowedMethods.isEmpty());
    assertSame(allowedMethods, actualRequestDeniedDeleteResult.getLinks());
    assertSame(headers, actualRequestDeniedDeleteResult.getMetadata());
  }
}
