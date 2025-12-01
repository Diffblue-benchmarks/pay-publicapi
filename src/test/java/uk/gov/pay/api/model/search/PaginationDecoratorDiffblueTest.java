package uk.gov.pay.api.model.search;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import black.door.hate.HalRepresentation;
import black.door.hate.HalRepresentation.HalRepresentationBuilder;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.dropwizard.core.server.DefaultServerFactory;
import io.dropwizard.core.setup.AdminFactory;
import io.dropwizard.core.setup.HealthCheckConfiguration;
import io.dropwizard.health.HealthFactory;
import io.dropwizard.logging.common.DefaultLoggingFactory;
import io.dropwizard.metrics.common.MetricsFactory;
import io.dropwizard.servlets.tasks.TaskConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uk.gov.pay.api.app.config.PublicApiConfig;
import uk.gov.pay.api.model.links.SearchNavigationLinks;
import uk.gov.pay.api.model.search.card.PaymentSearchResponse;

class PaginationDecoratorDiffblueTest {
  /**
   * Test {@link PaginationDecorator#decoratePagination(HalRepresentationBuilder, SearchPagination,
   * String)}.
   *
   * <ul>
   *   <li>Given builder.
   *   <li>Then return {@link HalRepresentation.HalRepresentationBuilder}.
   * </ul>
   *
   * <p>Method under test: {@link
   * PaginationDecorator#decoratePagination(HalRepresentation.HalRepresentationBuilder,
   * SearchPagination, String)}
   */
  @Test
  @DisplayName(
      "Test decoratePagination(HalRepresentationBuilder, SearchPagination, String); given builder; then return HalRepresentationBuilder")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "HalRepresentation.HalRepresentationBuilder PaginationDecorator.decoratePagination(HalRepresentation.HalRepresentationBuilder, SearchPagination, String)"
  })
  void testDecoratePagination_givenBuilder_thenReturnHalRepresentationBuilder() {
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

    PublicApiConfig config = new PublicApiConfig();
    config.setAdminFactory(admin);
    config.setHealthFactory(mock(HealthFactory.class));
    config.setLoggingFactory(new DefaultLoggingFactory());
    config.setMetricsFactory(new MetricsFactory());
    config.setServerFactory(new DefaultServerFactory());
    PaginationDecorator paginationDecorator = new PaginationDecorator(config);

    HalRepresentationBuilder halRepresentationBuilder = mock(HalRepresentationBuilder.class);
    when(halRepresentationBuilder.addProperty(Mockito.<String>any(), Mockito.<Object>any()))
        .thenReturn(HalRepresentation.builder());

    // Act
    HalRepresentationBuilder actualDecoratePaginationResult =
        paginationDecorator.decoratePagination(
            halRepresentationBuilder, new PaymentSearchResponse<>(), "Path");

    // Assert
    verify(halRepresentationBuilder).addProperty(eq("count"), isA(Object.class));
    assertSame(halRepresentationBuilder, actualDecoratePaginationResult);
  }

  /**
   * Test {@link PaginationDecorator#transformLinksToPublicApiUri(SearchNavigationLinks, String)}.
   *
   * <ul>
   *   <li>Then return {@link SearchNavigationLinks} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link
   * PaginationDecorator#transformLinksToPublicApiUri(SearchNavigationLinks, String)}
   */
  @Test
  @DisplayName(
      "Test transformLinksToPublicApiUri(SearchNavigationLinks, String); then return SearchNavigationLinks (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SearchNavigationLinks PaginationDecorator.transformLinksToPublicApiUri(SearchNavigationLinks, String)"
  })
  void testTransformLinksToPublicApiUri_thenReturnSearchNavigationLinks() {
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

    PublicApiConfig config = new PublicApiConfig();
    config.setAdminFactory(admin);
    config.setHealthFactory(mock(HealthFactory.class));
    config.setLoggingFactory(new DefaultLoggingFactory());
    config.setMetricsFactory(new MetricsFactory());
    config.setServerFactory(new DefaultServerFactory());
    PaginationDecorator paginationDecorator = new PaginationDecorator(config);
    SearchNavigationLinks links = new SearchNavigationLinks();

    // Act
    SearchNavigationLinks actualTransformLinksToPublicApiUriResult =
        paginationDecorator.transformLinksToPublicApiUri(links, "Path");

    // Assert
    assertSame(links, actualTransformLinksToPublicApiUriResult);
  }
}
