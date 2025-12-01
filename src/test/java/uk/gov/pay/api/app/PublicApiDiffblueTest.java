package uk.gov.pay.api.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.core.setup.Bootstrap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.app.config.PublicApiConfig;

class PublicApiDiffblueTest {
  /**
   * Test {@link PublicApi#initialize(Bootstrap)}.
   *
   * <p>Method under test: {@link PublicApi#initialize(Bootstrap)}
   */
  @Test
  @DisplayName("Test initialize(Bootstrap)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PublicApi.initialize(Bootstrap)"})
  void testInitialize() {
    // Arrange
    PublicApi publicApi = new PublicApi();
    Bootstrap<PublicApiConfig> bootstrap = new Bootstrap<>(new PublicApi());

    // Act
    publicApi.initialize(bootstrap);

    // Assert
    assertTrue(bootstrap.getConfigurationSourceProvider() instanceof SubstitutingSourceProvider);
  }

  /**
   * Test new {@link PublicApi} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link PublicApi}
   */
  @Test
  @DisplayName("Test new PublicApi (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PublicApi.<init>()"})
  void testNewPublicApi() {
    // Arrange and Act
    PublicApi actualPublicApi = new PublicApi();

    // Assert
    assertEquals("PublicApi", actualPublicApi.getName());
    Class<PublicApiConfig> expectedConfigurationClass = PublicApiConfig.class;
    assertEquals(expectedConfigurationClass, actualPublicApi.getConfigurationClass());
  }
}
