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

class SecuritytxtResourceDiffblueTest {
  /**
   * Test {@link SecuritytxtResource#redirectFromWellKnownSecuritytxt()}.
   *
   * <p>Method under test: {@link SecuritytxtResource#redirectFromWellKnownSecuritytxt()}
   */
  @Test
  @DisplayName("Test redirectFromWellKnownSecuritytxt()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Response SecuritytxtResource.redirectFromWellKnownSecuritytxt()"})
  void testRedirectFromWellKnownSecuritytxt() {
    // Arrange and Act
    Response actualRedirectFromWellKnownSecuritytxtResult =
        new SecuritytxtResource().redirectFromWellKnownSecuritytxt();

    // Assert
    StatusType statusInfo = actualRedirectFromWellKnownSecuritytxtResult.getStatusInfo();
    assertTrue(statusInfo instanceof Status);
    assertTrue(actualRedirectFromWellKnownSecuritytxtResult instanceof OutboundJaxrsResponse);
    assertNull(actualRedirectFromWellKnownSecuritytxtResult.getEntityTag());
    assertNull(actualRedirectFromWellKnownSecuritytxtResult.getMediaType());
    assertNull(actualRedirectFromWellKnownSecuritytxtResult.getEntity());
    assertNull(actualRedirectFromWellKnownSecuritytxtResult.getDate());
    assertNull(actualRedirectFromWellKnownSecuritytxtResult.getLastModified());
    assertNull(actualRedirectFromWellKnownSecuritytxtResult.getLanguage());
    assertEquals(-1, actualRedirectFromWellKnownSecuritytxtResult.getLength());
    MultivaluedMap<String, Object> headers =
        actualRedirectFromWellKnownSecuritytxtResult.getHeaders();
    assertEquals(3, headers.size());
    MultivaluedMap<String, String> stringHeaders =
        actualRedirectFromWellKnownSecuritytxtResult.getStringHeaders();
    assertEquals(3, stringHeaders.size());
    assertEquals(302, actualRedirectFromWellKnownSecuritytxtResult.getStatus());
    assertEquals(Status.FOUND, statusInfo);
    assertTrue(headers.containsKey("Cache-Control"));
    assertTrue(headers.containsKey("Expires"));
    assertTrue(headers.containsKey("Location"));
    assertTrue(stringHeaders.containsKey("Cache-Control"));
    assertTrue(stringHeaders.containsKey("Expires"));
    assertTrue(stringHeaders.containsKey("Location"));
    assertTrue(actualRedirectFromWellKnownSecuritytxtResult.getCookies().isEmpty());
    Set<String> allowedMethods = actualRedirectFromWellKnownSecuritytxtResult.getAllowedMethods();
    assertTrue(allowedMethods.isEmpty());
    assertSame(allowedMethods, actualRedirectFromWellKnownSecuritytxtResult.getLinks());
    assertSame(headers, actualRedirectFromWellKnownSecuritytxtResult.getMetadata());
  }

  /**
   * Test {@link SecuritytxtResource#redirectFromSecuritytxt()}.
   *
   * <p>Method under test: {@link SecuritytxtResource#redirectFromSecuritytxt()}
   */
  @Test
  @DisplayName("Test redirectFromSecuritytxt()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Response SecuritytxtResource.redirectFromSecuritytxt()"})
  void testRedirectFromSecuritytxt() {
    // Arrange and Act
    Response actualRedirectFromSecuritytxtResult =
        new SecuritytxtResource().redirectFromSecuritytxt();

    // Assert
    StatusType statusInfo = actualRedirectFromSecuritytxtResult.getStatusInfo();
    assertTrue(statusInfo instanceof Status);
    assertTrue(actualRedirectFromSecuritytxtResult instanceof OutboundJaxrsResponse);
    assertNull(actualRedirectFromSecuritytxtResult.getEntityTag());
    assertNull(actualRedirectFromSecuritytxtResult.getMediaType());
    assertNull(actualRedirectFromSecuritytxtResult.getEntity());
    assertNull(actualRedirectFromSecuritytxtResult.getDate());
    assertNull(actualRedirectFromSecuritytxtResult.getLastModified());
    assertNull(actualRedirectFromSecuritytxtResult.getLanguage());
    assertEquals(-1, actualRedirectFromSecuritytxtResult.getLength());
    MultivaluedMap<String, Object> headers = actualRedirectFromSecuritytxtResult.getHeaders();
    assertEquals(3, headers.size());
    MultivaluedMap<String, String> stringHeaders =
        actualRedirectFromSecuritytxtResult.getStringHeaders();
    assertEquals(3, stringHeaders.size());
    assertEquals(302, actualRedirectFromSecuritytxtResult.getStatus());
    assertEquals(Status.FOUND, statusInfo);
    assertTrue(headers.containsKey("Cache-Control"));
    assertTrue(headers.containsKey("Expires"));
    assertTrue(headers.containsKey("Location"));
    assertTrue(stringHeaders.containsKey("Cache-Control"));
    assertTrue(stringHeaders.containsKey("Expires"));
    assertTrue(stringHeaders.containsKey("Location"));
    assertTrue(actualRedirectFromSecuritytxtResult.getCookies().isEmpty());
    Set<String> allowedMethods = actualRedirectFromSecuritytxtResult.getAllowedMethods();
    assertTrue(allowedMethods.isEmpty());
    assertSame(allowedMethods, actualRedirectFromSecuritytxtResult.getLinks());
    assertSame(headers, actualRedirectFromSecuritytxtResult.getMetadata());
  }
}
