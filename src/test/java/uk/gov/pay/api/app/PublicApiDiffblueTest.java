package uk.gov.pay.api.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.core.setup.Bootstrap;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.pay.api.app.config.PublicApiConfig;

public class PublicApiDiffblueTest {
  /**
   * Test {@link PublicApi#initialize(Bootstrap)}.
   *
   * <p>Method under test: {@link PublicApi#initialize(Bootstrap)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PublicApi.initialize(Bootstrap)"})
  public void testInitialize() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PublicApi.<init>()"})
  public void testNewPublicApi() {
    // Arrange and Act
    PublicApi actualPublicApi = new PublicApi();

    // Assert
    assertEquals("PublicApi", actualPublicApi.getName());
    Class<PublicApiConfig> expectedConfigurationClass = PublicApiConfig.class;
    assertEquals(expectedConfigurationClass, actualPublicApi.getConfigurationClass());
  }
}
