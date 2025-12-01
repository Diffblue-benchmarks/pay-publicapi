package uk.gov.pay.api.app.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker.Std;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.databind.ser.BeanSerializerFactory;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.Impl;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import io.dropwizard.core.server.DefaultServerFactory;
import io.dropwizard.core.setup.AdminFactory;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.core.setup.HealthCheckConfiguration;
import io.dropwizard.health.HealthFactory;
import io.dropwizard.jackson.AnnotationSensitivePropertyNamingStrategy;
import io.dropwizard.jackson.DiscoverableSubtypeResolver;
import io.dropwizard.logging.common.DefaultLoggingFactory;
import io.dropwizard.metrics.common.MetricsFactory;
import io.dropwizard.servlets.tasks.TaskConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class PublicApiModuleDiffblueTest {
  /**
   * Test {@link PublicApiModule#provideObjectMapper()}.
   *
   * <ul>
   *   <li>Then Factory return {@link MappingJsonFactory}.
   * </ul>
   *
   * <p>Method under test: {@link PublicApiModule#provideObjectMapper()}
   */
  @Test
  @DisplayName("Test provideObjectMapper(); then Factory return MappingJsonFactory")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ObjectMapper PublicApiModule.provideObjectMapper()"})
  void testProvideObjectMapper_thenFactoryReturnMappingJsonFactory() {
    // Arrange
    HealthCheckConfiguration healthChecks = new HealthCheckConfiguration();
    healthChecks.setMaxThreads(3);
    healthChecks.setMinThreads(1);
    healthChecks.setServletEnabled(true);
    healthChecks.setWorkQueueSize(3);

    TaskConfiguration tasks = new TaskConfiguration();
    tasks.setPrintStackTraceOnError(true);

    AdminFactory admin = new AdminFactory();
    admin.setHealthChecks(healthChecks);
    admin.setTasks(tasks);

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(admin);
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    PublicApiModule publicApiModule = new PublicApiModule(configuration, new Environment("Name"));

    // Act
    ObjectMapper actualProvideObjectMapperResult = publicApiModule.provideObjectMapper();

    // Assert
    JsonFactory factory = actualProvideObjectMapperResult.getFactory();
    assertTrue(factory instanceof MappingJsonFactory);
    assertTrue(
        actualProvideObjectMapperResult.getDeserializationContext()
            instanceof DefaultDeserializationContext.Impl);
    assertTrue(actualProvideObjectMapperResult.getVisibilityChecker() instanceof Std);
    assertTrue(
        actualProvideObjectMapperResult.getPolymorphicTypeValidator()
            instanceof LaissezFaireSubTypeValidator);
    assertTrue(
        actualProvideObjectMapperResult.getSerializerFactory() instanceof BeanSerializerFactory);
    assertTrue(actualProvideObjectMapperResult.getSerializerProvider() instanceof Impl);
    assertTrue(actualProvideObjectMapperResult.getSerializerProviderInstance() instanceof Impl);
    assertTrue(actualProvideObjectMapperResult.getDateFormat() instanceof StdDateFormat);
    assertTrue(
        actualProvideObjectMapperResult.getPropertyNamingStrategy()
            instanceof AnnotationSensitivePropertyNamingStrategy);
    assertTrue(
        actualProvideObjectMapperResult.getSubtypeResolver()
            instanceof DiscoverableSubtypeResolver);
    assertNull(actualProvideObjectMapperResult.getEnumNamingStrategy());
    assertNull(actualProvideObjectMapperResult.getInjectableValues());
    assertEquals(9, actualProvideObjectMapperResult.getRegisteredModuleIds().size());
    assertSame(factory, actualProvideObjectMapperResult.getJsonFactory());
  }

  /**
   * Test {@link PublicApiModule#getRateLimiterConfig()}.
   *
   * <p>Method under test: {@link PublicApiModule#getRateLimiterConfig()}
   */
  @Test
  @DisplayName("Test getRateLimiterConfig()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "uk.gov.pay.api.app.config.RateLimiterConfig PublicApiModule.getRateLimiterConfig()"
  })
  void testGetRateLimiterConfig() {
    // Arrange
    HealthCheckConfiguration healthChecks = new HealthCheckConfiguration();
    healthChecks.setMaxThreads(3);
    healthChecks.setMinThreads(1);
    healthChecks.setServletEnabled(true);
    healthChecks.setWorkQueueSize(3);

    TaskConfiguration tasks = new TaskConfiguration();
    tasks.setPrintStackTraceOnError(true);

    AdminFactory admin = new AdminFactory();
    admin.setHealthChecks(healthChecks);
    admin.setTasks(tasks);

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(admin);
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    PublicApiModule publicApiModule = new PublicApiModule(configuration, new Environment("Name"));

    // Act and Assert
    assertNull(publicApiModule.getRateLimiterConfig());
  }
}
