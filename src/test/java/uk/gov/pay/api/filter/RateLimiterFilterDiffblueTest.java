package uk.gov.pay.api.filter;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.json.JsonMapper.Builder;
import io.dropwizard.core.server.DefaultServerFactory;
import io.dropwizard.core.setup.AdminFactory;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.health.HealthFactory;
import io.dropwizard.logging.common.DefaultLoggingFactory;
import io.dropwizard.metrics.common.MetricsFactory;
import io.lettuce.core.RedisClient;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.SecurityContext;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;
import org.glassfish.jersey.internal.MapPropertiesDelegate;
import org.glassfish.jersey.server.ContainerRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uk.gov.pay.api.app.config.RateLimiterConfig;
import uk.gov.pay.api.auth.Account;
import uk.gov.pay.api.filter.ratelimit.LocalRateLimiter;
import uk.gov.pay.api.filter.ratelimit.RateLimitException;
import uk.gov.pay.api.filter.ratelimit.RateLimiter;
import uk.gov.pay.api.filter.ratelimit.RedisRateLimiter;
import uk.gov.pay.api.managed.RedisClientManager;
import uk.gov.pay.api.model.TokenPaymentType;

class RateLimiterFilterDiffblueTest {
  /**
   * Test {@link RateLimiterFilter#filter(ContainerRequestContext)}.
   *
   * <p>Method under test: {@link RateLimiterFilter#filter(ContainerRequestContext)}
   */
  @Test
  @DisplayName("Test filter(ContainerRequestContext)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RateLimiterFilter.filter(ContainerRequestContext)"})
  void testFilter() throws IOException {
    // Arrange
    RateLimiterConfig rateLimiterConfig = new RateLimiterConfig();
    rateLimiterConfig.setAdminFactory(new AdminFactory());
    rateLimiterConfig.setHealthFactory(mock(HealthFactory.class));
    rateLimiterConfig.setLoggingFactory(new DefaultLoggingFactory());
    rateLimiterConfig.setMetricsFactory(new MetricsFactory());
    rateLimiterConfig.setServerFactory(new DefaultServerFactory());
    RateLimiter rateLimiter =
        new RateLimiter(new LocalRateLimiter(rateLimiterConfig), mock(RedisRateLimiter.class));
    JsonMapper objectMapper = JsonMapper.builder().findAndAddModules().build();

    RateLimiterFilter rateLimiterFilter = new RateLimiterFilter(rateLimiter, objectMapper);

    SecurityContext securityContext = mock(SecurityContext.class);
    when(securityContext.getUserPrincipal())
        .thenReturn(new Account("42", TokenPaymentType.CARD, "ABC123"));
    URI baseUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    URI requestUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();

    ContainerRequest requestContext =
        new ContainerRequest(
            baseUri,
            requestUri,
            "https://example.org/example",
            securityContext,
            new MapPropertiesDelegate());

    // Act
    rateLimiterFilter.filter(requestContext);

    // Assert
    verify(securityContext).getUserPrincipal();
  }

  /**
   * Test {@link RateLimiterFilter#filter(ContainerRequestContext)}.
   *
   * <p>Method under test: {@link RateLimiterFilter#filter(ContainerRequestContext)}
   */
  @Test
  @DisplayName("Test filter(ContainerRequestContext)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RateLimiterFilter.filter(ContainerRequestContext)"})
  void testFilter2() throws IOException, RateLimitException {
    // Arrange
    RateLimiter rateLimiter = mock(RateLimiter.class);
    doThrow(new RateLimitException())
        .when(rateLimiter)
        .checkRateOf(Mockito.<String>any(), Mockito.<RateLimiterKey>any());
    JsonMapper objectMapper = JsonMapper.builder().findAndAddModules().build();

    RateLimiterFilter rateLimiterFilter = new RateLimiterFilter(rateLimiter, objectMapper);

    SecurityContext securityContext = mock(SecurityContext.class);
    when(securityContext.getUserPrincipal())
        .thenReturn(new Account("42", TokenPaymentType.CARD, "ABC123"));
    URI baseUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    URI requestUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();

    ContainerRequest requestContext =
        new ContainerRequest(
            baseUri,
            requestUri,
            "https://example.org/example",
            securityContext,
            new MapPropertiesDelegate());

    // Act and Assert
    assertThrows(WebApplicationException.class, () -> rateLimiterFilter.filter(requestContext));
    verify(securityContext).getUserPrincipal();
    verify(rateLimiter).checkRateOf(eq("42"), isA(RateLimiterKey.class));
  }

  /**
   * Test {@link RateLimiterFilter#filter(ContainerRequestContext)}.
   *
   * <ul>
   *   <li>Given {@link Account#Account(String, TokenPaymentType, String)} with accountId is {@code
   *       3} and paymentType is {@code CARD} and tokenLink is {@code ABC123}.
   * </ul>
   *
   * <p>Method under test: {@link RateLimiterFilter#filter(ContainerRequestContext)}
   */
  @Test
  @DisplayName(
      "Test filter(ContainerRequestContext); given Account(String, TokenPaymentType, String) with accountId is '3' and paymentType is 'CARD' and tokenLink is 'ABC123'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RateLimiterFilter.filter(ContainerRequestContext)"})
  void testFilter_givenAccountWithAccountIdIs3AndPaymentTypeIsCardAndTokenLinkIsAbc123()
      throws IOException, RateLimitException {
    // Arrange
    RateLimiter rateLimiter = mock(RateLimiter.class);
    doThrow(new RateLimitException())
        .when(rateLimiter)
        .checkRateOf(Mockito.<String>any(), Mockito.<RateLimiterKey>any());
    JsonMapper objectMapper = JsonMapper.builder().findAndAddModules().build();

    RateLimiterFilter rateLimiterFilter = new RateLimiterFilter(rateLimiter, objectMapper);

    SecurityContext securityContext = mock(SecurityContext.class);
    when(securityContext.getUserPrincipal())
        .thenReturn(new Account("3", TokenPaymentType.CARD, "ABC123"));
    URI baseUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    URI requestUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();

    ContainerRequest requestContext =
        new ContainerRequest(
            baseUri,
            requestUri,
            "https://example.org/example",
            securityContext,
            new MapPropertiesDelegate());

    // Act and Assert
    assertThrows(WebApplicationException.class, () -> rateLimiterFilter.filter(requestContext));
    verify(securityContext).getUserPrincipal();
    verify(rateLimiter).checkRateOf(eq("3"), isA(RateLimiterKey.class));
  }

  /**
   * Test {@link RateLimiterFilter#filter(ContainerRequestContext)}.
   *
   * <ul>
   *   <li>Given builder addMixIn {@link Object} and {@link Object}.
   *   <li>Then throw {@link WebApplicationException}.
   * </ul>
   *
   * <p>Method under test: {@link RateLimiterFilter#filter(ContainerRequestContext)}
   */
  @Test
  @DisplayName(
      "Test filter(ContainerRequestContext); given builder addMixIn Object and Object; then throw WebApplicationException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RateLimiterFilter.filter(ContainerRequestContext)"})
  void testFilter_givenBuilderAddMixInObjectAndObject_thenThrowWebApplicationException()
      throws IOException, RateLimitException {
    // Arrange
    RateLimiter rateLimiter = mock(RateLimiter.class);
    doThrow(new RateLimitException())
        .when(rateLimiter)
        .checkRateOf(Mockito.<String>any(), Mockito.<RateLimiterKey>any());

    Builder builderResult = JsonMapper.builder();
    Class<Object> target = Object.class;
    Class<Object> mixinSource = Object.class;

    builderResult.addMixIn(target, mixinSource);
    JsonMapper objectMapper = builderResult.findAndAddModules().build();

    RateLimiterFilter rateLimiterFilter = new RateLimiterFilter(rateLimiter, objectMapper);

    SecurityContext securityContext = mock(SecurityContext.class);
    when(securityContext.getUserPrincipal())
        .thenReturn(new Account("42", TokenPaymentType.CARD, "ABC123"));
    URI baseUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    URI requestUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();

    ContainerRequest requestContext =
        new ContainerRequest(
            baseUri,
            requestUri,
            "https://example.org/example",
            securityContext,
            new MapPropertiesDelegate());

    // Act and Assert
    assertThrows(WebApplicationException.class, () -> rateLimiterFilter.filter(requestContext));
    verify(securityContext).getUserPrincipal();
    verify(rateLimiter).checkRateOf(eq("42"), isA(RateLimiterKey.class));
  }

  /**
   * Test {@link RateLimiterFilter#filter(ContainerRequestContext)}.
   *
   * <ul>
   *   <li>Given builder defaultLeniency {@code true}.
   *   <li>Then throw {@link WebApplicationException}.
   * </ul>
   *
   * <p>Method under test: {@link RateLimiterFilter#filter(ContainerRequestContext)}
   */
  @Test
  @DisplayName(
      "Test filter(ContainerRequestContext); given builder defaultLeniency 'true'; then throw WebApplicationException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RateLimiterFilter.filter(ContainerRequestContext)"})
  void testFilter_givenBuilderDefaultLeniencyTrue_thenThrowWebApplicationException()
      throws IOException, RateLimitException {
    // Arrange
    RateLimiter rateLimiter = mock(RateLimiter.class);
    doThrow(new RateLimitException())
        .when(rateLimiter)
        .checkRateOf(Mockito.<String>any(), Mockito.<RateLimiterKey>any());

    Builder builderResult = JsonMapper.builder();
    builderResult.defaultLeniency(true);
    JsonMapper objectMapper = builderResult.findAndAddModules().build();

    RateLimiterFilter rateLimiterFilter = new RateLimiterFilter(rateLimiter, objectMapper);

    SecurityContext securityContext = mock(SecurityContext.class);
    when(securityContext.getUserPrincipal())
        .thenReturn(new Account("42", TokenPaymentType.CARD, "ABC123"));
    URI baseUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    URI requestUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();

    ContainerRequest requestContext =
        new ContainerRequest(
            baseUri,
            requestUri,
            "https://example.org/example",
            securityContext,
            new MapPropertiesDelegate());

    // Act and Assert
    assertThrows(WebApplicationException.class, () -> rateLimiterFilter.filter(requestContext));
    verify(securityContext).getUserPrincipal();
    verify(rateLimiter).checkRateOf(eq("42"), isA(RateLimiterKey.class));
  }

  /**
   * Test {@link RateLimiterFilter#filter(ContainerRequestContext)}.
   *
   * <ul>
   *   <li>Given {@link Builder} {@link Builder#findAndAddModules()} return builder.
   *   <li>Then calls {@link Builder#findAndAddModules()}.
   * </ul>
   *
   * <p>Method under test: {@link RateLimiterFilter#filter(ContainerRequestContext)}
   */
  @Test
  @DisplayName(
      "Test filter(ContainerRequestContext); given Builder findAndAddModules() return builder; then calls findAndAddModules()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RateLimiterFilter.filter(ContainerRequestContext)"})
  void testFilter_givenBuilderFindAndAddModulesReturnBuilder_thenCallsFindAndAddModules()
      throws IOException, RateLimitException {
    // Arrange
    RateLimiter rateLimiter = mock(RateLimiter.class);
    doThrow(new RateLimitException())
        .when(rateLimiter)
        .checkRateOf(Mockito.<String>any(), Mockito.<RateLimiterKey>any());

    Builder builder = mock(Builder.class);
    when(builder.findAndAddModules()).thenReturn(JsonMapper.builder());
    JsonMapper objectMapper = builder.findAndAddModules().build();

    RateLimiterFilter rateLimiterFilter = new RateLimiterFilter(rateLimiter, objectMapper);

    SecurityContext securityContext = mock(SecurityContext.class);
    when(securityContext.getUserPrincipal())
        .thenReturn(new Account("42", TokenPaymentType.CARD, "ABC123"));
    URI baseUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    URI requestUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();

    ContainerRequest requestContext =
        new ContainerRequest(
            baseUri,
            requestUri,
            "https://example.org/example",
            securityContext,
            new MapPropertiesDelegate());

    // Act and Assert
    assertThrows(WebApplicationException.class, () -> rateLimiterFilter.filter(requestContext));
    verify(builder).findAndAddModules();
    verify(securityContext).getUserPrincipal();
    verify(rateLimiter).checkRateOf(eq("42"), isA(RateLimiterKey.class));
  }

  /**
   * Test {@link RateLimiterFilter#filter(ContainerRequestContext)}.
   *
   * <ul>
   *   <li>Given builder serializationInclusion {@code ALWAYS}.
   * </ul>
   *
   * <p>Method under test: {@link RateLimiterFilter#filter(ContainerRequestContext)}
   */
  @Test
  @DisplayName(
      "Test filter(ContainerRequestContext); given builder serializationInclusion 'ALWAYS'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RateLimiterFilter.filter(ContainerRequestContext)"})
  void testFilter_givenBuilderSerializationInclusionAlways()
      throws IOException, RateLimitException {
    // Arrange
    RateLimiter rateLimiter = mock(RateLimiter.class);
    doThrow(new RateLimitException())
        .when(rateLimiter)
        .checkRateOf(Mockito.<String>any(), Mockito.<RateLimiterKey>any());

    Builder builderResult = JsonMapper.builder();
    builderResult.serializationInclusion(Include.ALWAYS);
    JsonMapper objectMapper = builderResult.findAndAddModules().build();

    RateLimiterFilter rateLimiterFilter = new RateLimiterFilter(rateLimiter, objectMapper);

    SecurityContext securityContext = mock(SecurityContext.class);
    when(securityContext.getUserPrincipal())
        .thenReturn(new Account("42", TokenPaymentType.CARD, "ABC123"));
    URI baseUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    URI requestUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();

    ContainerRequest requestContext =
        new ContainerRequest(
            baseUri,
            requestUri,
            "https://example.org/example",
            securityContext,
            new MapPropertiesDelegate());

    // Act and Assert
    assertThrows(WebApplicationException.class, () -> rateLimiterFilter.filter(requestContext));
    verify(securityContext).getUserPrincipal();
    verify(rateLimiter).checkRateOf(eq("42"), isA(RateLimiterKey.class));
  }

  /**
   * Test {@link RateLimiterFilter#filter(ContainerRequestContext)}.
   *
   * <ul>
   *   <li>Given {@link RateLimiter} {@link RateLimiter#checkRateOf(String, RateLimiterKey)} does
   *       nothing.
   *   <li>Then calls {@link RateLimiter#checkRateOf(String, RateLimiterKey)}.
   * </ul>
   *
   * <p>Method under test: {@link RateLimiterFilter#filter(ContainerRequestContext)}
   */
  @Test
  @DisplayName(
      "Test filter(ContainerRequestContext); given RateLimiter checkRateOf(String, RateLimiterKey) does nothing; then calls checkRateOf(String, RateLimiterKey)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RateLimiterFilter.filter(ContainerRequestContext)"})
  void testFilter_givenRateLimiterCheckRateOfDoesNothing_thenCallsCheckRateOf()
      throws IOException, RateLimitException {
    // Arrange
    RateLimiter rateLimiter = mock(RateLimiter.class);
    doNothing().when(rateLimiter).checkRateOf(Mockito.<String>any(), Mockito.<RateLimiterKey>any());
    JsonMapper objectMapper = JsonMapper.builder().findAndAddModules().build();

    RateLimiterFilter rateLimiterFilter = new RateLimiterFilter(rateLimiter, objectMapper);

    SecurityContext securityContext = mock(SecurityContext.class);
    when(securityContext.getUserPrincipal())
        .thenReturn(new Account("42", TokenPaymentType.CARD, "ABC123"));
    URI baseUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    URI requestUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();

    ContainerRequest requestContext =
        new ContainerRequest(
            baseUri,
            requestUri,
            "https://example.org/example",
            securityContext,
            new MapPropertiesDelegate());

    // Act
    rateLimiterFilter.filter(requestContext);

    // Assert
    verify(securityContext).getUserPrincipal();
    verify(rateLimiter).checkRateOf(eq("42"), isA(RateLimiterKey.class));
  }

  /**
   * Test {@link RateLimiterFilter#filter(ContainerRequestContext)}.
   *
   * <ul>
   *   <li>Given {@link WebApplicationException#WebApplicationException()}.
   * </ul>
   *
   * <p>Method under test: {@link RateLimiterFilter#filter(ContainerRequestContext)}
   */
  @Test
  @DisplayName("Test filter(ContainerRequestContext); given WebApplicationException()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RateLimiterFilter.filter(ContainerRequestContext)"})
  void testFilter_givenWebApplicationException() throws IOException {
    // Arrange
    RateLimiterConfig rateLimiterConfig = new RateLimiterConfig();
    rateLimiterConfig.setAdminFactory(new AdminFactory());
    rateLimiterConfig.setHealthFactory(mock(HealthFactory.class));
    rateLimiterConfig.setLoggingFactory(new DefaultLoggingFactory());
    rateLimiterConfig.setMetricsFactory(new MetricsFactory());
    rateLimiterConfig.setServerFactory(new DefaultServerFactory());
    LocalRateLimiter localRateLimiter = new LocalRateLimiter(rateLimiterConfig);

    RateLimiterConfig rateLimiterConfig2 = new RateLimiterConfig();
    rateLimiterConfig2.setAdminFactory(new AdminFactory());
    rateLimiterConfig2.setHealthFactory(mock(HealthFactory.class));
    rateLimiterConfig2.setLoggingFactory(new DefaultLoggingFactory());
    rateLimiterConfig2.setMetricsFactory(new MetricsFactory());
    rateLimiterConfig2.setServerFactory(new DefaultServerFactory());
    RedisClientManager redisClientManager = new RedisClientManager(RedisClient.create());

    RedisRateLimiter redisRateLimiter =
        new RedisRateLimiter(rateLimiterConfig2, redisClientManager, new Environment("Name"));

    RateLimiter rateLimiter = new RateLimiter(localRateLimiter, redisRateLimiter);
    JsonMapper objectMapper = JsonMapper.builder().findAndAddModules().build();

    RateLimiterFilter rateLimiterFilter = new RateLimiterFilter(rateLimiter, objectMapper);

    SecurityContext securityContext = mock(SecurityContext.class);
    when(securityContext.getUserPrincipal()).thenThrow(new WebApplicationException());
    URI baseUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    URI requestUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();

    ContainerRequest requestContext =
        new ContainerRequest(
            baseUri,
            requestUri,
            "https://example.org/example",
            securityContext,
            new MapPropertiesDelegate());

    // Act and Assert
    assertThrows(WebApplicationException.class, () -> rateLimiterFilter.filter(requestContext));
    verify(securityContext).getUserPrincipal();
  }
}
