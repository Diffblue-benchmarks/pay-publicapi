package uk.gov.pay.api.filter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.SecurityContext;
import java.net.URI;
import java.nio.file.Paths;
import org.glassfish.jersey.internal.MapPropertiesDelegate;
import org.glassfish.jersey.server.ContainerRequest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RateLimiterKeyDiffblueTest {
  /**
   * Test {@link RateLimiterKey#from(ContainerRequestContext, String)}.
   *
   * <p>Method under test: {@link RateLimiterKey#from(ContainerRequestContext, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RateLimiterKey RateLimiterKey.from(ContainerRequestContext, String)"})
  public void testFrom() {
    // Arrange
    URI baseUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    URI requestUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    SecurityContext securityContext = mock(SecurityContext.class);

    ContainerRequest requestContext =
        new ContainerRequest(
            baseUri,
            requestUri,
            "https://example.org/example",
            securityContext,
            new MapPropertiesDelegate());

    // Act
    RateLimiterKey actualFromResult = RateLimiterKey.from(requestContext, "42");

    // Assert
    assertEquals("https://example.org/example", actualFromResult.getKeyType());
    assertEquals("https://example.org/example", actualFromResult.getMethod());
    assertEquals("https://example.org/example-42", actualFromResult.getKey());
  }

  /**
   * Test {@link RateLimiterKey#from(ContainerRequestContext, String)}.
   *
   * <p>Method under test: {@link RateLimiterKey#from(ContainerRequestContext, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RateLimiterKey RateLimiterKey.from(ContainerRequestContext, String)"})
  public void testFrom2() {
    // Arrange
    URI baseUri = Paths.get(System.getProperty("java.io.tmpdir"), "/").toUri();
    URI requestUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    SecurityContext securityContext = mock(SecurityContext.class);

    ContainerRequest requestContext =
        new ContainerRequest(
            baseUri,
            requestUri,
            "https://example.org/example",
            securityContext,
            new MapPropertiesDelegate());

    // Act
    RateLimiterKey actualFromResult = RateLimiterKey.from(requestContext, "42");

    // Assert
    assertEquals("https://example.org/example", actualFromResult.getKeyType());
    assertEquals("https://example.org/example", actualFromResult.getMethod());
    assertEquals("https://example.org/example-42", actualFromResult.getKey());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RateLimiterKey#getKey()}
   *   <li>{@link RateLimiterKey#getKeyType()}
   *   <li>{@link RateLimiterKey#getMethod()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String RateLimiterKey.getKey()",
    "String RateLimiterKey.getKeyType()",
    "String RateLimiterKey.getMethod()"
  })
  public void testGettersAndSetters() {
    // Arrange
    URI baseUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    URI requestUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    SecurityContext securityContext = mock(SecurityContext.class);

    ContainerRequest requestContext =
        new ContainerRequest(
            baseUri,
            requestUri,
            "https://example.org/example",
            securityContext,
            new MapPropertiesDelegate());
    RateLimiterKey fromResult = RateLimiterKey.from(requestContext, "42");

    // Act
    String actualKey = fromResult.getKey();
    String actualKeyType = fromResult.getKeyType();

    // Assert
    assertEquals("https://example.org/example", actualKeyType);
    assertEquals("https://example.org/example", fromResult.getMethod());
    assertEquals("https://example.org/example-42", actualKey);
  }
}
